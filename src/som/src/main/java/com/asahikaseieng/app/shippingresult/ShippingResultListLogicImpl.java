/*
 * Created on 2009/03/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shippingresult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.app.common.stockinout.StockinoutForShipping;
import com.asahikaseieng.app.shipping.ShippingConst;
import com.asahikaseieng.app.shipping.ShippingLogicException;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.shipping.Shipping;
import com.asahikaseieng.dao.entity.shipping.ShippingDao;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetail;
import com.asahikaseieng.dao.entity.shippingdetail.ShippingDetailDao;
import com.asahikaseieng.dao.nonentity.comboboxes.shippingresult.ShippingResultCarryForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.shippingresult.ShippingResultCarryForComboboxesDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProMakeSalesRecordCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.procedurecall.zaictrl.ZaiCtrlDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListDao;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListShippingDetailList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListShippingDetailListDao;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingDetailResultListForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingDetailResultListForReportDao;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListForReportDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 出荷実績一覧 ロジック実装クラス
 * @author tosco
 */
public class ShippingResultListLogicImpl implements ShippingResultListLogic {

	/** エラーログ出力最大サイズ */
	private static final int ERROR_LOG_SQL_STR_MAX_LEN = 2000;

	/** 出荷実績共通ロジッククラス */
	private ShippingResultCommonsLogic shippingResultCommonsLogic;

	/** 出荷実績一覧画面用Dao */
	private ShippingResultListDao shippingResultListDao;

	/** 出荷実績－運送会社コンボボックス用DAO */
	private ShippingResultCarryForComboboxesDao shippingResultCarryForComboboxesDao;

	/** 出荷ヘッダーテーブル更新用Dao */
	private ShippingDao shippingDao;

	/** 出荷詳細テーブル用Dao */
	private ShippingDetailDao shippingDetailDao;

	/** 出荷実績一覧画面出荷詳細データ用Dao */
	private ShippingResultListShippingDetailListDao shippingResultListShippingDetailListDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;

	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao; // 

	/** 在庫更新用Dao * */
	private ZaiCtrlDao zaiCtrlDao;

	/** 帳票（Excel）ヘッダ用Dao */
	private ShippingResultListForReportDao shippingResultListForReportDao;

	/** 帳票（Excel）詳細用Dao */
	private ShippingDetailResultListForReportDao shippingDetailResultListForReportDao;

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
	public ShippingResultListLogicImpl() {
	}

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createCarryAllCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		shippingResultCarryForComboboxesDao.getCarryList();

		// 運送会社マスタからステータスコンボボックス用配列を取得
		List<ShippingResultCarryForComboboxes> lineList = shippingResultCarryForComboboxesDao
				.getCarryList();
		for (ShippingResultCarryForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getCarryCd());
			item.setLabales(shippingResultCommonsLogic.getCarryName(bean
					.getCarryName1(), bean.getCarryName2()));
			res.add(item);
		}

		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(ShippingConst.COMBO_ALL_VALUE);
		allItem.setLabales(ShippingConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 出荷実績一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ShippingResultList> getSearchList(
			final ShippingResultListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<ShippingResultList> list = shippingResultListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		for (ShippingResultList bean : list) {
			// 指図量のフォーマット
			bean.setStrShippingInstructionSum(checker.format(bean
					.getUnitDivision(), ShippingResultConst.VENDER_DIVISION_TS,
				bean.getVenderCd(), bean.getShippingInstructionSum()));
			// 実績量のフォーマット
			bean.setStrShippingResultQuantitySum(checker.format(bean
					.getUnitDivision(), ShippingResultConst.VENDER_DIVISION_TS,
				bean.getVenderCd(), bean.getShippingResultQuantitySum()));
			// 完了チェック状態設定
			bean.setCheckFlg(ShippingResultConst.SHIPPING_STATUS_COMPLETE
					.equals(bean.getShippingStatus()));
		}
		return list;
	}

	/**
	 * 出荷実績一覧検索処理(ヘッダ）
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultListForReport> 検索結果リスト
	 */
	public List<ShippingResultListForReport> getReportHeaderList(
			final ShippingResultListConditionForReport condition) {

		List<ShippingResultListForReport> list = shippingResultListForReportDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 出荷実績一覧検索処理(詳細）
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultListForReport> 検索結果リスト
	 */
	public List<ShippingDetailResultListForReport> getReportDetailList(
			final ShippingResultListConditionForReport condition) {

		List<ShippingDetailResultListForReport> list = shippingDetailResultListForReportDao
				.getDetailSearchList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 完了登録処理を行う
	 * @param frm 一覧データ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	public void complete(final ShippingResultListForm frm, final String tantoCd)
			throws NoDataException, Exception {
		if (frm.getSearchList() == null || frm.getSearchList().size() == 0) {
			throw new IllegalArgumentException("list == null");
		}
		// 売上トランザクション作成処理
		ProMakeSalesRecordCallDto dto = new ProMakeSalesRecordCallDto();

		String errMsg = "errors.shipping.make.sales.error";

		dto.setPStrTantoCd(tantoCd);

		// 在庫更新処理
		StockinoutForShipping stock = new StockinoutForShipping(zaiCtrlDao);
		String errMsgKey = "errors.shipping.stock.update";

		try {
			for (ShippingResultList bean : frm.getSearchList()) {
				if (!bean.isCheckFlg()
						|| ShippingResultConst.SHIPPING_STATUS_COMPLETE
								.equals(bean.getShippingStatus())) {
					// チェック無しまたは出荷完了の場合は次のデータへ
					continue;
				}
				try {
					// 在庫更新－出荷実績取消（全)
					if (!stock.cancelResult(bean.getShippingNo(), tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					ShippingLogicException ex = new ShippingLogicException(
							errMsgKey, "");
					ex.setModuleCd("StockinoutForShipping");
					ex.setInsideErrCd(bean.getShippingNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}

				// 出荷詳細データを完了にする
				completeDetail(bean.getShippingNo(), tantoCd);

				Shipping updBean = shippingDao.getEntity(bean.getShippingNo());
				if (updBean == null) {
					throw new NoDataException();
				}

				// 出荷担当者コード
				updBean.setTantoCd(tantoCd);
				// 出荷ステータス(5:出荷完了)
				updBean
						.setShippingStatus(ShippingResultConst.SHIPPING_STATUS_COMPLETE);
				// 完納区分
				updBean
						.setDeliveryComp(ShippingResultConst.DELIVERY_COMP_COMPLETE);
				// 出荷完了日
				updBean.setShippingResultDate(AecDateUtils
						.getTimestampYmdHmFormat(AecDateUtils.dateFormat(
							AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd"),
							"yyyy/MM/dd"));
				// 更新時刻
				updBean.setUpdateDate(bean.getUpdateDate());
				// 更新者
				updBean.setUpdatorCd(tantoCd);
				// 更新処理
				int updateNum = shippingDao.update(updBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new NoDataException();
				}

				try {
					// 在庫更新－出荷実績入力（全)
					if (!stock.entryResult(bean.getShippingNo(), tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
					// 在庫更新－出荷指図完了（全）
					if (!stock.completeShipping(bean.getShippingNo(), tantoCd)) {
						ShippingLogicException ex = new ShippingLogicException(
								errMsgKey, "");
						throw ex;
					}
				} catch (LogicExceptionEx e) {
					ShippingLogicException ex = new ShippingLogicException(
							errMsgKey, "");
					ex.setModuleCd("StockinoutForShipping");
					ex.setInsideErrCd(bean.getShippingNo());
					ex.setInsideErrMsg(e.getMessage());
					throw ex;
				}
				try {

					// 出荷番号をセット
					dto.setPStrShippingNo(bean.getShippingNo());
					// 売上トランザクション自動作成プロシージャコール処理（出荷ステータスが出荷完了になった後では無いと運賃が売上られない）
					procedureCallDao.proMakeSalesRecord(dto);

					frm.setErrorCd(dto.getPStrErrorCd());
					frm.setErrorMsg(dto.getPStrErrorMsg());

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

				} catch (LogicExceptionEx e) {
					throw new LogicExceptionEx(errMsg);
				}

			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 出荷詳細データの完了登録処理を行う
	 * @param shippingNo 出荷番号
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	private void completeDetail(final String shippingNo, final String tantoCd)
			throws NoDataException, Exception {
		try {
			// 出荷詳細データ取得
			List<ShippingResultListShippingDetailList> list = shippingResultListShippingDetailListDao
					.getList(shippingNo);
			if (list == null || list.isEmpty()) {
				throw new NoDataException();
			}

			for (ShippingResultListShippingDetailList detail : list) {
				if (ShippingResultConst.DELIVERY_COMP_COMPLETE.equals(detail
						.getDeliveryComp())) {
					// 完納の場合は、次の明細へ
					continue;
				}
				ShippingDetail updDBean = new ShippingDetail();
				IgnoreCaseBeanUtils.copyProperties(updDBean, detail);

				// 出荷実績量を出荷指図量とする
				updDBean.setShippingResultQuantity(detail
						.getShippingInstruction());
				// 出荷完了日
				updDBean.setShippingResultDate(AecDateUtils
						.getTimestampYmdHmFormat(AecDateUtils.dateFormat(
							AecDateUtils.getCurrentTimestamp(), "yyyy/MM/dd"),
							"yyyy/MM/dd"));
				updDBean
						.setDeliveryComp(ShippingResultConst.DELIVERY_COMP_COMPLETE);
				updDBean.setUpdatorCd(tantoCd); // 更新者
				// 更新処理
				int updateNum = shippingDetailDao.update(updDBean);
				if (updateNum == 0) {
					// 更新対象無しエラー
					throw new NoDataException();
				}
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final ShippingResultListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
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

	/* -------------------- setter -------------------- */

	/**
	 * 出荷実績共通ロジッククラスを設定します。
	 * @param shippingResultCommonsLogic 出荷実績共通ロジッククラス
	 */
	public void setShippingResultCommonLogic(
			final ShippingResultCommonsLogic shippingResultCommonsLogic) {
		this.shippingResultCommonsLogic = shippingResultCommonsLogic;
	}

	/**
	 * 出荷実績一覧画面用Daoを設定します。
	 * @param shippingResultListDao 出荷実績一覧画面用Dao
	 */
	public void setShippingResultListDao(
			final ShippingResultListDao shippingResultListDao) {
		this.shippingResultListDao = shippingResultListDao;

	}

	/**
	 * 出荷実績－運送会社コンボボックス用DAOを設定します。
	 * @param shippingResultCarryForComboboxesDao 出荷実績－運送会社コンボボックス用DAO
	 */
	public void setShippingResultCarryForComboboxesDao(
			final ShippingResultCarryForComboboxesDao shippingResultCarryForComboboxesDao) {
		this.shippingResultCarryForComboboxesDao = shippingResultCarryForComboboxesDao;

	}

	/**
	 * 出荷実績テーブル更新用Daoを設定します。
	 * @param shippingDao 出荷実績テーブル更新用Dao
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
	 * 出荷実績一覧画面出荷詳細データ用Daoを設定します。
	 * @param shippingResultListShippingDetailListDao 出荷実績一覧画面出荷詳細データ用Dao
	 */
	public void setShippingResultListShippingDetailListDao(
			final ShippingResultListShippingDetailListDao shippingResultListShippingDetailListDao) {
		this.shippingResultListShippingDetailListDao = shippingResultListShippingDetailListDao;
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

	/**
	 * shippingDetailResultListForReportDaoを設定します。
	 * @param shippingDetailResultListForReportDao
	 *            shippingDetailResultListForReportDao
	 */
	public void setShippingDetailResultListForReportDao(
			final ShippingDetailResultListForReportDao shippingDetailResultListForReportDao) {
		this.shippingDetailResultListForReportDao = shippingDetailResultListForReportDao;
	}

	/**
	 * shippingResultListForReportDaoを設定します。
	 * @param shippingResultListForReportDao shippingResultListForReportDao
	 */
	public void setShippingResultListForReportDao(
			final ShippingResultListForReportDao shippingResultListForReportDao) {
		this.shippingResultListForReportDao = shippingResultListForReportDao;
	}
}
