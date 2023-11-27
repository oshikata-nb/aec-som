/*
 * Created on 2009/03/19
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shippingresult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.common.stockinout.StockinoutForShipping;
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.shipping.Shipping;
import com.asahikaseieng.dao.entity.shipping.ShippingDao;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetail;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetailDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProMakeSalesRecordCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailEntity;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailEntityDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 出荷実績詳細 ロジック実装クラス
 * @author tosco
 */
public class ShippingResultDetailLogicImpl implements ShippingResultDetailLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 出荷実績詳細画面用出荷ヘッダテーブル用Dao */
	private ShippingResultDetailEntityDao shippingDetailCompanyEntityDao;

	/** 出荷実績詳細画面用出荷詳細テーブル用Dao */
	private ShippingResultDetailListDao shippingResultDetailListDao;

	/** 出荷ヘッダテーブル用Dao */
	private ShippingDao shippingDao;

	/** 出荷詳細テーブル用Dao */
	private ShippingDetailDao shippingDetailDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;

	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao; // 

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 売上トランザクション自動作成 正常終了 */
	protected static final BigDecimal MAKE_SALES_RECORD_NOMAL_END = new BigDecimal(
			0);

	/** 売上トランザクション自動作成 出荷番号異常 */
	protected static final BigDecimal MAKE_SALES_RECORD_SHIPPING_NO_ERROR = new BigDecimal(
			-1);

	/** 売上トランザクション自動作成 受注番号異常 */
	protected static final BigDecimal MAKE_SALES_RECORD_ORDER_NO_ERROR = new BigDecimal(
			-2);

	/** 売上トランザクション自動作成 異常終了 */
	protected static final BigDecimal MAKE_SALES_RECORD_ERROR = new BigDecimal(
			-9);

	/** エラーログ出力用モジュールコード */
	protected static final String MAKE_SALES_RECORD_MODULE_CD = "PRO_MAKE_SALES_RECORD";

	/**
	 * コンストラクタ.出荷実績
	 */
	public ShippingResultDetailLogicImpl() {
	}

	/**
	 * 検索処理を行なう.出荷実績
	 * @param shippingNo 出荷番号
	 * @return ShippingResultDetailEntity
	 * @throws NoDataException データが存在しない例外
	 */
	public ShippingResultDetailEntity getEntity(final String shippingNo)
			throws NoDataException {
		checkParams(shippingNo);

		// 出荷実績ヘッダデータ取得
		ShippingResultDetailEntity bean = shippingDetailCompanyEntityDao
				.getEntity(shippingNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 出荷実績詳細データを取得する
	 * @param shippingNo 出荷番号
	 * @param unitDivision 単位区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<ShippingResultDetailList> 出荷実績詳細データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ShippingResultDetailList> getDetailList(
			final String shippingNo, final String unitDivision,
			final String venderDivision, final String venderCd)
			throws NoDataException {
		checkParams(shippingNo);

		// 出荷実績詳細データ取得
		List<ShippingResultDetailList> list = shippingResultDetailListDao
				.getEntity(shippingNo);
		// 数値のフォーマット
		for (ShippingResultDetailList detail : list) {
			detail.setStrShippingInstruction(checker.format(unitDivision,
				venderDivision, venderCd, detail.getShippingInstruction())); // 指図量
			detail.setStrShippingResultQuantity(checker.format(unitDivision,
				venderDivision, venderCd, detail.getShippingResultQuantity())); // 実績量
			detail.setStrInventoryQty(checker.format(unitDivision,
				venderDivision, venderCd, detail.getInventoryQty())); // 在庫量
			// 前回実績量
			detail.setStrShippingResultQuantityPrev(detail
					.getStrShippingResultQuantity());
		}

		return list;
	}

	/**
	 * 出荷実績データの更新処理を行う
	 * @param form 出荷実績詳細画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	public void update(final ShippingResultDetailForm form, final String tantoCd)
			throws NoDataException, Exception {
		// 在庫更新処理
		StockinoutForShipping stock = new StockinoutForShipping(zaiCtrlDao);
		String errMsgKey = "errors.shipping.stock.update";
		boolean compFlag = false;

		// 出荷実績情報更新
		try {
			if (!ShippingResultConst.SHIPPING_STATUS_COMPLETE.toString()
					.equals(form.getShippingStatus())) {
				try {
					// 在庫更新－出荷実績取消（全)
					if (!stock.cancelResult(form.getShippingNo(), tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					ShippingLogicException ex = new ShippingLogicException(
							errMsgKey, "");
					ex.setModuleCd("StockinoutForShipping");
					ex.setInsideErrCd(form.getShippingNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}

				// 出荷ステータスが完了以外の場合、出荷詳細を更新する
				updateDetail(form, tantoCd);
			}

			// 出荷ヘッダ
			Shipping updBean = shippingDao.getEntity(form.getShippingNo());
			if (updBean == null) {
				throw new NoDataException();
			}
			// 入力した値を更新用Beanにコピー
			if (!ShippingResultConst.SHIPPING_STATUS_COMPLETE.equals(updBean
					.getShippingStatus())) {
				List<ShippingResultDetailList> detailList = form
						.getDetailList();
				compFlag = true;
				for (ShippingResultDetailList detail : detailList) {
					if (!detail.isDeliveryCompFlag()) {
						compFlag = false;
						break;
					}
				}
				// 出荷ステータス設定
				if (compFlag) {
					// 全明細が完納の場合、「5:出荷完了」にする
					updBean
							.setShippingStatus(ShippingResultConst.SHIPPING_STATUS_COMPLETE);
					// 完納区分
					updBean
							.setDeliveryComp(ShippingResultConst.DELIVERY_COMP_COMPLETE);

					// 出荷完了日
					updBean
							.setShippingResultDate(getShippingResultDate(detailList));
				} else {
					// 「4:実績受信中」にする
					updBean
							.setShippingStatus(ShippingResultConst.SHIPPING_STATUS_RECEIVING);
				}
			}
			updBean.setTantoCd(tantoCd); // 出荷担当者コード
			updBean.setCarryFare(AecNumberUtils.convertBigDecimal(form
					.getStrCarryFare())); // 運賃
			updBean.setUpdateDate(form.getUpdateDate()); // 更新日付
			updBean.setUpdatorCd(tantoCd); // 更新者

			// 出荷ヘッダー更新処理
			shippingDao.update(updBean);

			try {
				if (!ShippingResultConst.SHIPPING_STATUS_COMPLETE.toString()
						.equals(form.getShippingStatus())) {
					// 在庫更新－出荷実績入力（全)
					if (!stock.entryResult(updBean.getShippingNo(), tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
				}
				if (compFlag) {
					// 在庫更新－出荷指図完了（全）
					if (!stock.completeShipping(updBean.getShippingNo(),
						tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
				}
			} catch (LogicExceptionEx e) {
				ShippingLogicException ex = new ShippingLogicException(
						errMsgKey, "");
				ex.setModuleCd("StockinoutForShipping");
				ex.setInsideErrCd(updBean.getShippingNo());
				ex.setInsideErrMsg(e.getMessage());
				throw ex;
			}
			String errMsg = "errors.shipping.make.sales.error";

			try {
				if (compFlag) { // 出荷完了の場合のみ売上トランザクション作成処理

					// 売上トランザクション作成処理（出荷ステータスが出荷完了になった後では無いと運賃が売上られない）
					ProMakeSalesRecordCallDto dto = new ProMakeSalesRecordCallDto();

					dto.setPStrTantoCd(tantoCd);

					// 出荷番号をセット
					dto.setPStrShippingNo(form.getShippingNo());
					// 売上トランザクション自動作成プロシージャコール処理
					procedureCallDao.proMakeSalesRecord(dto);

					form.setErrorCd(dto.getPStrErrorCd());
					form.setErrorMsg(dto.getPStrErrorMsg());

					if (MAKE_SALES_RECORD_SHIPPING_NO_ERROR.equals(dto
							.getPNumRet())) {
						errMsg = "errors.shipping.make.sales.shipping.no";
						throw new LogicExceptionEx(errMsg);
					} else if (MAKE_SALES_RECORD_ORDER_NO_ERROR.equals(dto
							.getPNumRet())) {
						errMsg = "errors.shipping.make.sales.order.no";
						throw new LogicExceptionEx(errMsg);
					} else if (MAKE_SALES_RECORD_ERROR.equals(dto.getPNumRet())) {
						throw new LogicExceptionEx(errMsg);
					}

				}
			} catch (LogicExceptionEx e) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 一番若い出荷実績日を取得
	 */
	private Timestamp getShippingResultDate(
			final List<ShippingResultDetailList> detailList) {

		String ret = null;
		if (!detailList.isEmpty() && detailList.size() > 0) {
			ret = detailList.get(0).getStrShippingResultDate();
		} else {
			ret = AecDateUtils.dateFormat(AecDateUtils.getCurrentTimestamp(),
				"yyyy/MM/dd");
		}

		// 出荷明細の出荷実績日の一番小さい日を探す
		for (ShippingResultDetailList detail : detailList) {

			if (ret.compareTo(detail.getStrShippingResultDate()) > 0) {
				ret = detail.getStrShippingResultDate();
			}
		}
		return AecDateUtils.getTimestampYmdHmFormat(ret, "yyyy/MM/dd");
	}

	/**
	 * 出荷詳細データの更新処理を行う
	 * @param form 出荷実績詳細画面FORM
	 * @param tantoCd ログイン者コード
	 * @throws NoDataException データ無し例外
	 * @throws Exception 例外
	 */
	private void updateDetail(final ShippingResultDetailForm form,
			final String tantoCd) throws NoDataException, Exception {
		try {
			// 出荷詳細データ一括削除
			int deleteNum = shippingResultDetailListDao.deleteByShippingNo(form
					.getShippingNo());
			if (deleteNum == 0) {
				throw new NoDataException();
			}

			// 出荷詳細データを全件登録
			List<ShippingResultDetailList> detailList = form.getDetailList();
			int rowNo = 1;
			for (ShippingResultDetailList detail : detailList) {
				ShippingDetail updDBean = new ShippingDetail();
				IgnoreCaseBeanUtils.copyProperties(updDBean, detail);
				updDBean.setShippingRowNo(new BigDecimal(rowNo)); // 行番号(出荷)
				updDBean.setShippingInstruction(AecNumberUtils
						.convertBigDecimal(detail.getStrShippingInstruction())); // 出荷指図量
				updDBean.setShippingResultQuantity(AecNumberUtils
						.convertBigDecimal(detail
								.getStrShippingResultQuantity())); // 出荷実績量
				updDBean.setShippingResultDate(AecDateUtils
						.getTimestampYmdHmFormat(detail
								.getStrShippingResultDate(), "yyyy/MM/dd")); // 出荷完了日
				if (detail.isDeliveryCompFlag()) { // 完納区分
					updDBean
							.setDeliveryComp(ShippingResultConst.DELIVERY_COMP_COMPLETE);
				} else {
					updDBean
							.setDeliveryComp(ShippingResultConst.DELIVERY_COMP_NOT_COMPLETE);
				}
				updDBean.setUpdatorCd(tantoCd); // 更新者
				if (StringUtils.isEmpty(detail.getShippingNo())) {
					// 新規追加行の場合、必要項目をセット
					updDBean.setShippingNo(form.getShippingNo()); // 出荷番号
					updDBean.setInputorCd(tantoCd); // 登録者
					updDBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				// 出荷詳細登録処理
				int insertDNum = shippingDetailDao.insert(updDBean);
				if (insertDNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
				rowNo++;
			}
			// 全て完納の場合、
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			// 一意制約エラー
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception {

		outPutErrorLog(MAKE_SALES_RECORD_MODULE_CD, strErrorCd, strErrorMsg,
			tantoCd);
	}

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void outPutErrorLog(final String strModuleCd,
			final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception {

		ErrorLog log = new ErrorLog();

		log.setModuleCd(strModuleCd);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		String cutMsg = strErrorMsg;
		if (StringUtils.isNotEmpty(strErrorMsg)
				&& strErrorMsg.length() > ERROR_LOG_SQL_STR_MAX_LEN) {
			cutMsg = StringUtils.substring(strErrorMsg, 0,
				ERROR_LOG_SQL_STR_MAX_LEN);
		}
		log.setSqlStr(cutMsg);

		try {
			errorLogDao.insert(log);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * パラメータチェック
	 * @param param パラメータ
	 */
	private void checkParams(final String param) {

		if (param == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 出荷実績詳細画面用出荷ヘッダテーブル用Daoを設定する
	 * @param shippingDetailCompanyEntityDao 出荷実績詳細画面用出荷ヘッダテーブル用Dao
	 */
	public void setShippingShippingResultDetailEntityDao(
			final ShippingResultDetailEntityDao shippingDetailCompanyEntityDao) {
		this.shippingDetailCompanyEntityDao = shippingDetailCompanyEntityDao;
	}

	/**
	 * 出荷実績詳細画面用出荷詳細テーブル用Daoを設定する
	 * @param shippingResultDetailListDao 出荷実績詳細画面用出荷詳細テーブル用Dao
	 */
	public void setShippingShippingResultDetailListDao(
			final ShippingResultDetailListDao shippingResultDetailListDao) {
		this.shippingResultDetailListDao = shippingResultDetailListDao;
	}

	/**
	 * 出荷ヘッダテーブル用Daoを設定します。
	 * @param shippingDao 出荷ヘッダテーブル用Dao
	 */
	public void setShippingDao(final ShippingDao shippingDao) {
		this.shippingDao = shippingDao;
	}

	/**
	 * 出荷詳細テーブル用Daoを設定します。
	 * @param shippingDetailDao 出荷詳細テーブル用Dao
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
	 * プロシージャコール用のDaoを設定します。
	 * @param procedureCallDao プロシージャコール用のDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * エラーログ出力用のDaoを設定します。
	 * @param errorLogDao エラーログ出力用
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * 在庫更新用Daoを設定します。
	 * @param zaiCtrlDao 在庫更新用Dao
	 */
	public void setZaiCtrlDao(final ZaiCtrlDao zaiCtrlDao) {
		this.zaiCtrlDao = zaiCtrlDao;
	}
}
