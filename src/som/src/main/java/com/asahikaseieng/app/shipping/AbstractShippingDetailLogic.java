/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForShipping;
import com.asahikaseieng.app.numbering.GetNumberLogic;
import com.asahikaseieng.dao.entity.shipping.Shipping;
import com.asahikaseieng.dao.entity.shipping.ShippingDao;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetail;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingDetailListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * ヘッダー情報更新処理抽象クラス
 * @author tosco
 */
public abstract class AbstractShippingDetailLogic {

	/** 出荷指図ヘッダテーブル用Dao */
	private ShippingDao shippingDao;

	/** 出荷指図詳細テーブル用Dao */
	private ShippingDetailDao shippingDetailDao;

	/** 出荷指図詳細画面用出荷指図詳細テーブル用Dao */
	private ShippingDetailListDao shippingDetailListDao;

	/** 発番処理用ロジッククラス */
	private GetNumberLogic getNumberLogic;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** 在庫更新用Dao **/
	private ZaiCtrlDao zaiCtrlDao;

	/**
	 * 削除処理を行う.
	 * @param frm 出荷指図詳細画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void delete(final AbstractShippingDetailForm frm, final String tantoCd)
		throws NoDataException, Exception {
		try {
			String errMsg = "errors.shipping.stock.update";
			// 在庫更新処理
			StockinoutForShipping stock = new StockinoutForShipping(zaiCtrlDao);
			try {
				// 在庫更新－出荷指図取消（全）
				if (!stock.cancelShipping(frm.getShippingNo(), tantoCd)) {
					ShippingLogicException ex = new ShippingLogicException(errMsg, "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				ShippingLogicException ex = new ShippingLogicException(errMsg, "");
				ex.setModuleCd("StockinoutForShipping");
				ex.setInsideErrCd(frm.getShippingNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}

			//出荷指図詳細データ一括削除
			int deleteNum = shippingDetailListDao.deleteByShippingNo(frm.getShippingNo());
			if (deleteNum == 0) {
				throw new NoDataException();
			}

			//出荷指図ヘッダーを削除
			Shipping delBean = new Shipping();
			//値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(delBean, frm.getShippingBean());
			int delNum = shippingDao.delete(delBean);
			if (delNum == 0) {
				// 対象データ無し
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 出荷指図詳細データのフォーマットを行う
	 * @param detailList 出荷指図詳細データリスト
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<? extends ShippingDetailList> 出荷指図詳細データリスト
	 */
	protected List< ? extends ShippingDetailList> formatDetailList(
		final List< ? extends ShippingDetailList> detailList,
		final String unitDivision, final String venderDivision, final String venderCd) {

		//数値のフォーマット
		for (ShippingDetailList detail : detailList) {
			detail.setStrShippingInstruction(checker.format(unitDivision,
				venderDivision, venderCd, detail.getShippingInstruction()));	//指図量
			detail.setStrInventoryQty(checker.format(unitDivision,
				venderDivision, venderCd, detail.getInventoryQty()));			//在庫量
			detail.setStrBackorderQty(checker.format(unitDivision,
				venderDivision, venderCd, detail.getBackorderQty()));			//発注残
			detail.setStrInspectionQty(checker.format(unitDivision,
				venderDivision, venderCd, detail.getInspectionQty()));			//検査待
			detail.setStrAssignQty(checker.format(unitDivision,
				venderDivision, venderCd, detail.getAssignQty()));				//引当残
			detail.setStrValidInventory(checker.format(unitDivision,
				venderDivision, venderCd, detail.getValidInventory()));			//有効在庫
			//指図量前回値
			detail.setStrShippingInstructionPrev(detail.getStrShippingInstruction());
		}
		return detailList;
	}

	/**
	 * 出荷指図データの登録処理を行う
	 * @param frm 出荷指図詳細画面FORM
	 * @param detailList 出荷指図詳細データリスト
	 * @param tantoCd ログイン者コード
	 * @return String 出荷番号
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	protected String insert(final AbstractShippingDetailForm frm,
			final List< ? extends ShippingDetailList> detailList,
			final String tantoCd)
		throws NoDataException, Exception {
		String shippingNo = null;
		try {
			Shipping insBean = new Shipping();

			//出荷伝票番号取得
			shippingNo = getNumberLogic.getShipping();
			insBean.setShippingNo(shippingNo);						//出荷番号
			insBean.setShippingInstructDate(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrShippingInstructDate(), "yyyy/MM/dd"));	//出荷指図日
			insBean.setScheduledShippingDate(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrScheduledShippingDate(), "yyyy/MM/dd"));	//出荷予定日
			insBean.setTantoCd(tantoCd);                  			//出荷担当者コード
			insBean.setOrderNo(frm.getOrderNo());					//受注番号
			insBean.setOrderRowNo(AecNumberUtils.convertBigDecimal(frm.getOrderRowNo()));	//行番号(受注)
			insBean.setVenderCd(frm.getVenderCd());					//得意先コード
			insBean.setDeliveryCd(frm.getDeliveryCd());				//納入先コード
			insBean.setShippingStatus(ShippingConst.SHIPPING_STATUS_FIX);	//出荷ステータス
			insBean.setItemCd(frm.getItemCd());						//品目コード
			insBean.setCarryCd(frm.getCarryCd());					//運送会社コード
			insBean.setSuggestedDeliverlimit(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrSuggestedDeliverlimit(), "yyyy/MM/dd"));		//希望納期
//			insBean.setCarryFare(AecNumberUtils.convertBigDecimal(frm.getStrCarryFare()));	//運賃
			insBean.setCarryFare(BigDecimal.ZERO);	//運賃
			insBean.setInputorCd(tantoCd);                				//登録者
			insBean.setInputDate(AecDateUtils.getCurrentTimestamp());	//登録日時
			insBean.setUpdatorCd(tantoCd);                				//更新者

			//出荷指図ヘッダ登録処理
			int insertNum = shippingDao.insert(insBean);
			if (insertNum != 1) {
				//一意制約エラー
				throw new DuplicateRuntimeException(0);
			}

			int rowNo = 1;
			for (ShippingDetailList detail : detailList) {
				ShippingDetail detailBean = new ShippingDetail();
				detailBean.setShippingNo(shippingNo);				//出荷番号
				detailBean.setShippingRowNo(new BigDecimal(rowNo));	//行番号(出荷)
				detailBean.setLotNo(detail.getLotNo());				//ロット番号
				detailBean.setShippingInstruction(AecNumberUtils.convertBigDecimal(
					detail.getStrShippingInstruction()));			//出荷指図量
				detailBean.setLocationCd(detail.getLocationCd());	//ロケーションコード
				detailBean.setInputorCd(tantoCd);                  	        //登録者
				detailBean.setInputDate(AecDateUtils.getCurrentTimestamp());    //登録日時
				detailBean.setUpdatorCd(tantoCd);                           //更新者
				//出荷指図ヘッダ登録処理
				int insertDNum = shippingDetailDao.insert(detailBean);
				if (insertDNum != 1) {
					//一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
				rowNo++;
			}

			String errMsg = "errors.shipping.stock.update";
			try {
				// 在庫更新処理
				StockinoutForShipping stock = new StockinoutForShipping(zaiCtrlDao);
				// 在庫更新－出荷指図入力（全）
				if (!stock.entryShipping(shippingNo, tantoCd)) {
					ShippingLogicException ex = new ShippingLogicException(errMsg, "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				ShippingLogicException ex = new ShippingLogicException(errMsg, "");
				ex.setModuleCd("StockinoutForShipping");
				ex.setInsideErrCd(shippingNo);
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}

		return shippingNo;
	}

	/**
	 * 出荷指図データの更新処理を行う
	 * @param frm 出荷指図詳細画面FORM
	 * @param detailList 出荷指図詳細データリスト
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	protected void update(final AbstractShippingDetailForm frm,
			final List< ? extends ShippingDetailList> detailList,
			final String tantoCd)
		throws NoDataException, Exception {

		String errMsg = "errors.shipping.stock.update";
		// 在庫更新処理
		StockinoutForShipping stock = new StockinoutForShipping(zaiCtrlDao);

		try {
			// 在庫更新－出荷指図取消（全）
			if (!stock.cancelShipping(frm.getShippingNo(), tantoCd)) {
				ShippingLogicException ex = new ShippingLogicException(errMsg, "");
				throw ex;
			}
		} catch (LogicExceptionEx e) {
			ShippingLogicException ex = new ShippingLogicException(errMsg, "");
			ex.setModuleCd("StockinoutForShipping");
			ex.setInsideErrCd(frm.getShippingNo());
			ex.setInsideErrMsg(e.getMessage());
			throw ex;
		}

		try {
			//出荷指図詳細データ一括削除
			int deleteNum = shippingDetailListDao.deleteByShippingNo(frm.getShippingNo());
			if (deleteNum == 0) {
				throw new NoDataException();
			}

			//出荷指図詳細データを全件登録
			int rowNo = 1;
			for (ShippingDetailList detail : detailList) {
				ShippingDetail updDBean = new ShippingDetail();
				IgnoreCaseBeanUtils.copyProperties(updDBean, detail);
				updDBean.setShippingRowNo(new BigDecimal(rowNo));		//行番号(出荷)
				updDBean.setShippingInstruction(AecNumberUtils.convertBigDecimal(
					detail.getStrShippingInstruction()));				//出荷指図量
				//新規追加行の場合、必要項目をセット
				if (StringUtils.isEmpty(detail.getShippingNo())) {
					updDBean.setShippingNo(frm.getShippingNo());		//出荷番号
					updDBean.setInputorCd(tantoCd);                  	//登録者
					updDBean.setInputDate(AecDateUtils.getCurrentTimestamp());	//登録日時
				}
				updDBean.setUpdatorCd(tantoCd);                         //更新者

				//出荷指図詳細登録処理
				int insertDNum = shippingDetailDao.insert(updDBean);
				if (insertDNum != 1) {
					//一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
				rowNo++;
			}

			//出荷指図ヘッダ
			Shipping updBean = new Shipping();
			//表示時に取得した値を更新用Beanにコピー
			IgnoreCaseBeanUtils.copyProperties(updBean, frm.getShippingBean());
			//入力した値を更新用Beanにコピー
			updBean.setOrderNo(frm.getOrderNo());						//受注番号
			updBean.setOrderRowNo(AecNumberUtils.convertBigDecimal(frm.getOrderRowNo()));	//行番号(受注)
			updBean.setShippingInstructDate(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrShippingInstructDate(), "yyyy/MM/dd"));		//出荷指図日
			updBean.setScheduledShippingDate(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrScheduledShippingDate(), "yyyy/MM/dd"));		//出荷予定日
			updBean.setTantoCd(tantoCd);                  				//出荷担当者コード
			updBean.setShippingStatus(ShippingConst.SHIPPING_STATUS_FIX);	//出荷ステータス
			updBean.setDeliveryCd(frm.getDeliveryCd());					//納入先コード
			updBean.setCarryCd(frm.getCarryCd());						//運送会社コード
			updBean.setSuggestedDeliverlimit(AecDateUtils.getTimestampYmdHmFormat(
				frm.getStrSuggestedDeliverlimit(), "yyyy/MM/dd"));		//希望納期
			if (!StringUtils.isEmpty(frm.getStrCarryFare())) {
				updBean.setCarryFare(AecNumberUtils.convertBigDecimal(frm.getStrCarryFare()));	//運賃
			}
			updBean.setItemCd(frm.getItemCd());                         //品目コード
			updBean.setUpdatorCd(tantoCd);                  			//更新者

			//出荷指図ヘッダー更新処理
			shippingDao.update(updBean);

			try {
				// 在庫更新－出荷指図入力（全）
				if (!stock.entryShipping(frm.getShippingNo(), tantoCd)) {
					ShippingLogicException ex = new ShippingLogicException(errMsg, "");
					throw ex;
				}
			} catch (LogicExceptionEx e) {
				ShippingLogicException ex = new ShippingLogicException(errMsg, "");
				ex.setModuleCd("StockinoutForShipping");
				ex.setInsideErrCd(frm.getShippingNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			//排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			//一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 出荷指図詳細画面用出荷指図詳細テーブル用Daoを設定する
	 * @param shippingDetailListDao 出荷指図詳細画面用出荷指図詳細テーブル用Dao
	 */
	public void setShippingDetailListDao(
			final ShippingDetailListDao shippingDetailListDao) {
		this.shippingDetailListDao = shippingDetailListDao;
	}

	/**
	 * 発番処理用ロジッククラスoを設定します。
	 * @param getNumberLogic 発番処理用ロジッククラス
	 */
	public void setGetNumberLogic(final GetNumberLogic getNumberLogic) {
		this.getNumberLogic = getNumberLogic;
	}

	/**
	 * 出荷指図ヘッダテーブル用Daoを設定します。
	 * @param shippingDao shippingDao
	 */
	public void setShippingDao(final ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	/**
	 * 出荷指図詳細テーブル用Daoを設定します。
	 * @param shippingDetailDao shippingDetailDao
	 */
	public void setShippingDetailDao(final ShippingDetailDao shippingDetailDao) {
		this.shippingDetailDao = shippingDetailDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
