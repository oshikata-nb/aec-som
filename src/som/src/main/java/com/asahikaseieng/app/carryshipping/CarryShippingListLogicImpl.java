/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.carryshipping;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.entity.shippingtemp.ShippingTemp;
import com.asahikaseieng.dao.entity.shippingtemp.ShippingTempDao;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingList;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingListDao;
import com.asahikaseieng.dao.nonentity.carryshipping.CarryShippingPagerCondition;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListForReport;
import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListForReportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCarryShippingOrderMakeCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProCarryShippingOrderSendCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 運送店別出荷指図画面 ロジック実装クラス
 * @author tosco
 */
public class CarryShippingListLogicImpl implements CarryShippingListLogic {

	/** 運送店別出荷指図画面用のDao */
	private CarryShippingListDao carryShippingListDao;

	/** 出荷指図ヘッダ(計装IFアクセス用)のDao */
	private ShippingTempDao shippingTempDao;

	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao; // 

	/** 帳票Excel用Dao */
	private CarryShippingListForReportDao carryShippingListForReportDao;

	/** 発行済み */
	private static final String ISSUE = "1";

	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;

	/** 運送店別出荷指図作成処理 正常終了 */
	protected static final BigDecimal CARRYSHIPPING_MAKE_NOMAL_END = new BigDecimal(
			0);

	/** 運送店別出荷指図作成処理 積出ナンバー異常 */
	protected static final BigDecimal CARRYSHIPPING_MAKE_SENDINGOFFNO_ERROR = new BigDecimal(
			-1);

	/** 運送店別出荷指図作成信処理 フォークランコード異常 */
	protected static final BigDecimal CARRYSHIPPING_MAKE_FLANCD_ERROR = new BigDecimal(
			-2);

	/** 運送店別出荷指図作成処理 異常 */
	protected static final BigDecimal CARRYSHIPPING_MAKE_ERROR = new BigDecimal(
			-9);

	/** 運送店別出荷指図送信処理 異常 */
	protected static final BigDecimal CARRYSHIPPING_SEND_ERROR = new BigDecimal(
			-9);

	/** エラーログ出力用モジュールコード */
	protected static final String CARRYSHIPPING_MAKE_MODULE_CD = "PRO_CARRY_SHIPPING_ORDER_MAKE";

	/**
	 * コンストラクタ.
	 */
	public CarryShippingListLogicImpl() {
	}

	/**
	 * 運送店別出荷指図指図作成を行う.
	 * @param frm 運送店別出荷指図画面 Formクラス
	 * @param tantoCd ログイン者コード
	 */
	public void shippingOrderMake(final CarryShippingListForm frm,
			final String tantoCd) {

		// 運送店別出荷指図作成処理
		ProCarryShippingOrderMakeCallDto dto = new ProCarryShippingOrderMakeCallDto();

		dto.setPStrShippingDate(frm.getSrhScheduledShippingDate());
		dto.setPStrTantoCd(tantoCd);
		String errMsg = "errors.carryshipping.make.error";

		try {

			procedureCallDao.funCarryShippingOrderMake(dto);

			frm.setErrorCd(dto.getPStrErrorCd());
			frm.setErrorMsg(dto.getPStrErrorMsg());

			if (CARRYSHIPPING_MAKE_SENDINGOFFNO_ERROR.equals(dto.getPNumRet())) {
				errMsg = "errors.carryshipping.make.number.orver";
				throw new LogicExceptionEx(errMsg);
			} else if (CARRYSHIPPING_MAKE_FLANCD_ERROR.equals(dto.getPNumRet())) {
				errMsg = "errors.carryshipping.make.flancd.error";
				throw new LogicExceptionEx(errMsg);
			} else if (CARRYSHIPPING_MAKE_ERROR.equals(dto.getPNumRet())) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(errMsg);
		}
	}

	// /**
	// * 運送店別出荷指図指図送信を行う.
	// * @param frm 運送店別出荷指図画面 Formクラス
	// * @param tantoCd ログイン者コード
	// */
	// public void shippingOrderSend(final CarryShippingListForm frm,
	// final String tantoCd) {
	//
	// // 運送店別出荷指図作成処理
	// ProCarryShippingOrderSendCallDto dto = new
	// ProCarryShippingOrderSendCallDto();
	//
	// /** 出荷予定日 */
	// dto.setPStrShippingDate(frm.getSrhScheduledShippingDate());
	// /** 担当者コード */
	// dto.setPStrTantoCd(tantoCd);
	// String errMsg = "errors.carryshipping.send.error";
	//
	// // 検索結果分ループ
	// for (CarryShippingList bean : frm.getSearchList()) {
	// // チェックボックスにチェックが入っていたら
	// if (bean.isCarryShippingCheckBox()) {
	//
	// /** 担当者コード */
	// dto.setPStrSendingOffNo(bean.getSendingOffNumber());
	// /** 運送会社コード */
	// dto.setPStrCarryCd(bean.getCarryCd());
	// try {
	//
	// procedureCallDao.proCarryShippingOrderSend(dto);
	//
	// frm.setErrorCd(dto.getPStrErrorCd());
	// frm.setErrorMsg(dto.getPStrErrorMsg());
	//
	// if (CARRYSHIPPING_SEND_ERROR.equals(dto.getPNumRet())) {
	// throw new LogicExceptionEx(errMsg);
	// }
	//
	// } catch (LogicExceptionEx e) {
	// throw new LogicExceptionEx(errMsg);
	// }
	// }
	// }
	//
	// }

	/**
	 * 運送店別出荷指図画面 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<CarryShippingList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<CarryShippingList> getSearchList(
			final CarryShippingPagerCondition condition) throws NoDataException {
		// パラメータチェック
		checkParams(condition);

		// 一覧検索
		List<CarryShippingList> list = carryShippingListDao
				.getSearchList(condition);
		// 検索結果があるか？
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 検索結果分ループ
		for (CarryShippingList bean : list) {
			// 発行済みフラグが発行済みの場合はチェックを入れて非活性
			// 未発行の場合は、デフォルトチェックで表示
			bean.setCarryShippingCheckBox(Boolean.TRUE);

			// 運送店名・工場名設定
			if (bean.getCarryName1() != null) {
				if (bean.getCarryName2() != null) {
					// Name2がある場合は、Name1 + "_" + Name2
					bean.setCarryName(bean.getCarryName1() + "_"
							+ bean.getCarryName2());
				} else {
					// Name2がない場合は、Name1
					bean.setCarryName(bean.getCarryName1());
				}
			}
		}

		return list;
	}

	/**
	 * 帳票Excel 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<CarryShippingListForReport> 検索結果リスト
	 */
	public List<CarryShippingListForReport> getReportList(
			final CarryShippingListConditionForReport condition) {

		// 一覧検索
		List<CarryShippingListForReport> list = carryShippingListForReportDao
				.getSearchList(condition); // 検索結果があるか？
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 運送店別出荷指図の登録処理を行う
	 * @param frm フォームデータ
	 * @param loginUserId ログインユーザ
	 * @param errors errors
	 * @throws Exception 例外
	 */
	public void update(final CarryShippingListForm frm,
			final String loginUserId, final ActionMessages errors)
			throws Exception {

		// 運送店別出荷指図作成処理
		ProCarryShippingOrderSendCallDto dto = new ProCarryShippingOrderSendCallDto();

		/** 出荷予定日 */
		dto.setPStrShippingDate(frm.getSrhScheduledShippingDate());
		/** 担当者コード */
		dto.setPStrTantoCd(loginUserId);

		// 検索結果分ループ
		for (CarryShippingList bean : frm.getSearchList()) {
			// チェックボックスにチェックが入っていたら
			if (bean.isCarryShippingCheckBox()) {
				// 更新対象データを作成する
				ShippingTemp newBean = new ShippingTemp();
				// 更新対象データに検索取得時のデータのコピーを行う
				IgnoreCaseBeanUtils.copyProperties(newBean, bean);
				dto.setPStrShippingDate(frm.getSrhScheduledShippingDate());

				// 指図送信済みフラグを設定
				newBean.setShippingOrderSendCompFlag(new BigDecimal(ISSUE)); // 発行済みを設定

				/** 積出ナンバーコード */
				dto.setPStrSendingOffNo(bean.getSendingOffNumber());
				/** 運送会社コード */
				dto.setPStrCarryCd(bean.getCarryCd());

				// 更新者(ログインユーザー)セット
				newBean.setUpdatorCd(loginUserId);
				try {

					/* 指図送信プロシージャコール */
					procedureCallDao.proCarryShippingOrderSend(dto);

					if (CARRYSHIPPING_SEND_ERROR.equals(dto.getPNumRet())) {
						errors.add("", new ActionMessage(
								"errors.carryshipping.send", bean
										.getSendingOffNumber()));
					} else {
						// 出荷指図TEMPテーブル更新
						int updateNum = shippingTempDao.update(newBean);

						if (updateNum == 0) {
							// 更新対象無しエラー
							throw new NoDataException();
						}
					}

					// 更新時に、すでに更新されていた場合
				} catch (NotSingleRowUpdatedRuntimeException e) {
					throw new OptimisticLockRuntimeException();
				}
			}
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

		ErrorLog log = new ErrorLog();

		log.setModuleCd(CARRYSHIPPING_MAKE_MODULE_CD);
		log.setErrorDate(AecDateUtils.getCurrentTimestamp());
		log.setClient(tantoCd);
		log.setErrorMes(strErrorCd);
		log.setSqlStr(strErrorMsg);

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
	 * @param condition 検索条件
	 */
	private void checkParams(final CarryShippingPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 運送店別出荷指図画面用のDaoを設定します。
	 * @param carryShippingListDao 運送店別出荷指図画面用のDao
	 */
	public void setCarryShippingListDao(
			final CarryShippingListDao carryShippingListDao) {
		this.carryShippingListDao = carryShippingListDao;

	}

	/**
	 * 出荷指図ヘッダ(計装IFアクセス用)用のDaoを設定します。
	 * @param shippingTempDao 出荷指図ヘッダテーブル用のDao
	 */
	public void setShippingTempDao(final ShippingTempDao shippingTempDao) {
		this.shippingTempDao = shippingTempDao;

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
	 * carryShippingListForReportDaoを設定します。
	 * @param carryShippingListForReportDao carryShippingListForReportDao
	 */
	public void setCarryShippingListForReportDao(
			final CarryShippingListForReportDao carryShippingListForReportDao) {
		this.carryShippingListForReportDao = carryShippingListForReportDao;
	}
}
