/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProUpdateSalesUnitpriceCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.dao.nonentity.sales.SalesList;
import com.asahikaseieng.dao.nonentity.sales.SalesListDao;
import com.asahikaseieng.dao.nonentity.sales.SalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReportDao;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReportDao;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListReportCondition;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.LogicExceptionEx;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 売上トランザクション詳細 ロジック実装クラス
 * @author tosco
 */
public class SalesListLogicImpl implements SalesListLogic {

	private SalesListDao salesListDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;

	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao; // 

	private SalesListForReportDao salesListForReportDao;

	private SalesInoutListForReportDao salesInoutListForReportDao;

	/** 正単価更新処理 正常終了 */
	protected static final BigDecimal MAKE_SALES_RECORD_NOMAL_END = new BigDecimal(
			0);

	/** 正単価更新処理 異常終了 */
	protected static final BigDecimal MAKE_SALES_RECORD_ERROR = new BigDecimal(
			-9);

	/** エラーログ出力用モジュールコード */
	protected static final String UPDATE_SALES_UNITPRICE_MODULE_CD = "PRO_UPDATE_SALES_UNITPRICE";

	/**
	 * コンストラクタ.売上トランザクション
	 */
	public SalesListLogicImpl() {
	}

	/**
	 * 出荷指図データの登録処理を行う.
	 * @param frm 売上検索画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	public void updateSalseUnitprice(final SalesListForm frm,
			final String tantoCd) {

		ProUpdateSalesUnitpriceCallDto dto = new ProUpdateSalesUnitpriceCallDto();

		dto.setPStrTantoCd(tantoCd);
		String errMsg = "errors.sales.update.unitprice.error";

		try {

			procedureCallDao.proUpdateSalesUnitprice(dto);

			frm.setErrorCd(dto.getPStrErrorCd());
			frm.setErrorMsg(dto.getPStrErrorMsg());

			if (MAKE_SALES_RECORD_ERROR.equals(dto.getPNumRet())) {
				throw new LogicExceptionEx(errMsg);
			}

		} catch (LogicExceptionEx e) {
			throw new LogicExceptionEx(errMsg);
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

		log.setModuleCd(UPDATE_SALES_UNITPRICE_MODULE_CD);
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
	 * 売上トランザクション一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<SalesList> 検索結果リスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<SalesList> getSearchList(final SalesListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<SalesList> list = salesListDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値のフォーマット
		for (SalesList bean : list) {
			// 数量
			bean.setStrSalesQuantity(checker.format(bean.getUnitDivision(),
				SalesConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
						.getSalesQuantity()));
			// 金額
			bean.setStrSalesAmount(checker.format(
				SalesConst.UNIT_DIVISION_URKINGAKU,
				SalesConst.VENDER_DIVISION_TS, bean.getVenderCd(), bean
						.getSalesAmount()));
		}
		return list;
	}

	/**
	 * 帳票出力検索
	 * @param condition 検索条件
	 * @return List<SalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<SalesListForReport> getListForReport(
			final SalesListReportCondition condition) throws NoDataException {

		List<SalesListForReport> list = salesListForReportDao
				.getListForReport(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 帳票出力検索
	 * @param condition 検索条件
	 * @return List<SalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	public List<SalesInoutListForReport> getInoutListForReport(
			final SalesListReportCondition condition) throws NoDataException {

		List<SalesInoutListForReport> list = salesInoutListForReportDao
				.getInoutListForReport(condition);
		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final SalesListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * salesListDaoを設定します。
	 * 
	 * @param salesListDao salesListDao
	 */
	public void setSalesListDao(final SalesListDao salesListDao) {
		this.salesListDao = salesListDao;

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
	 * 帳票出力用のDaoを設定します。
	 * @param salesListForReportDao 帳票出力用
	 */
	public void setSalesListForReportDao(
			final SalesListForReportDao salesListForReportDao) {
		this.salesListForReportDao = salesListForReportDao;
	}

	/**
	 * 帳票出力用のDaoを設定します。
	 * @param salesInoutListForReportDao 帳票出力用
	 */
	public void setSalesInoutListForReportDao(
			final SalesInoutListForReportDao salesInoutListForReportDao) {
		this.salesInoutListForReportDao = salesInoutListForReportDao;
	}
}
