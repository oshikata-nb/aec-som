/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.math.BigDecimal;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.errorlog.ErrorLog;
import com.asahikaseieng.dao.entity.errorlog.ErrorLogDao;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxesDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheckDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateList;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateListDao;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListForReportDao;
import com.asahikaseieng.dao.nonentity.procedurecall.ProConSalestermsAndEstimateCallDto;
import com.asahikaseieng.dao.nonentity.procedurecall.ProcedureCallDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 販売条件・見積単価 コピー作成・削除一覧ロジック 実装クラス.
 * @author t0011036
 */
public class SalestermsAndEstimateListLogicImpl implements SalestermsAndEstimateListLogic {

	private SalestermsAndEstimateListDao salestermsAndEstimateListDao;

	private SalestermsAndEstimateListForReportDao salestermsAndEstimateListForReportDao;
	
	private SalestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao;
	
	/** プロシージャコール用dao */
	private ProcedureCallDao procedureCallDao;
	
	/** エラーログ出力用のDao */
	private ErrorLogDao errorLogDao;
	
	private SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao;
	
	/** 販売条件・見積単価コピー・削除確定or確定取消処理 異常終了 */
	protected static final BigDecimal MAKE_SALESTERMSANDESTIMATE_RECORD_ERROR = new BigDecimal(
			-9);
	
	/** エラーログ出力用モジュールコード */
	protected static final String CON_SALESTERMSANDESTIMATE_MODULE_CD = "PRO_CON_SALESTERMSANDESTIMATE";

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateListLogicImpl() {
	}
	
	/**
	 * 処理区分リスト取得
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	public List<SalestermsandestimateNamesListForComboboxes> getProcessDivisionList() {
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsandestimateNamesListForComboboxesDao
				.getProcessDivision("PRDI");
		return list;
	}
	
	/**
	 * ステータスリスト取得
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	public List<SalestermsandestimateNamesListForComboboxes> getStatusList() {
		List<SalestermsandestimateNamesListForComboboxes> list = salestermsandestimateNamesListForComboboxesDao
				.getStatus("STAT");
		return list;
	}


	/**
	 * 販売条件一覧検索
	 * @param condition 検索条件
	 * @return List<SalestermsAndEstimateList>
	 * @throws NoDataException NoDataException
	 */
	public List<SalestermsAndEstimateList> getList(
			final SalestermsAndEstimateListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<SalestermsAndEstimateList> list = salestermsAndEstimateListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final SalestermsAndEstimateListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 販売条件一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<SalestermsAndEstimateListForReport>
	 */
	public List<SalestermsAndEstimateListForReport> getListForReport(
			final SalestermsAndEstimateListConditionForReport condition) {
		List<SalestermsAndEstimateListForReport> list = salestermsAndEstimateListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}
	
	/**
	 * 販売条件マスタの該当品目有無確認用
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	public SalestermsAndEstimateItemCheck getSalestermsItemCount(final String itemCd) {
		SalestermsAndEstimateItemCheck bean = salestermsAndEstimateItemCheckDao
				.getSalestermsItemCount(itemCd);
		return bean;
	}
	
	/**
	 * 見積ファイルの該当品目有無確認用
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	public SalestermsAndEstimateItemCheck getEstimateItemCount(final String itemCd) {
		SalestermsAndEstimateItemCheck bean = salestermsAndEstimateItemCheckDao
				.getEstimateItemCount(itemCd);
		return bean;
	}
	
	/**
	 * 販売条件・見積単価コピー・削除確定or確定取消処理を行う(詳細画面の「確定」or「確定取消」ボタン押下時).
	 * @param frm 販売条件・見積単価コピー・削除詳細画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	public void conSalestermsAndEstimate(final SalestermsAndEstimateListForm frm,final String tantoCd) {

		ProConSalestermsAndEstimateCallDto dto = new ProConSalestermsAndEstimateCallDto();
		for (SalestermsAndEstimateList bean : frm.getSearchList()) {
			if (bean.isSalestermsAndEstimateCheckBox()){
				// チェックボックスがONであるデータに対して処理を行う
				
				dto.setPStrTantoCd(tantoCd);
				dto.setpPkNo(bean.getPkNo());
				// true：確定処理、false：確定取消処理
				dto.setpConfirmFlg(frm.getConfirmFlg());
				
				// プロシージャを実行
				procedureCallDao.proConSalestermsAndEstimate(dto);
				
				// エラーメッセージを設定
				frm.setErrorCd(dto.getPStrErrorCd());
				frm.setErrorMsg(dto.getPStrErrorMsg());
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

		log.setModuleCd(CON_SALESTERMSANDESTIMATE_MODULE_CD);
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

	/* -------------------- setter -------------------- */

	/**
	 * salestermsAndEstimateListDaoを設定します。
	 * @param salestermsAndEstimateListDao salestermsAndEstimateListDao
	 */
	public void setSalestermsAndEstimateListDao(final SalestermsAndEstimateListDao salestermsAndEstimateListDao) {
		this.salestermsAndEstimateListDao = salestermsAndEstimateListDao;
	}

	/**
	 * salestermsAndEstimateListForReportDaoを設定します。
	 * @param salestermsAndEstimateListForReportDao salestermsAndEstimateListForReportDao
	 */
	public void setSalestermsAndEstimateListForReportDao(
			final SalestermsAndEstimateListForReportDao salestermsAndEstimateListForReportDao) {
		this.salestermsAndEstimateListForReportDao = salestermsAndEstimateListForReportDao;
	}

	/**
	 * salestermsandestimateNamesListForComboboxesDaoを設定します。
	 * @param salestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao
	 */
	public void setSalestermsandestimateNamesListForComboboxesDao(
			final SalestermsandestimateNamesListForComboboxesDao salestermsandestimateNamesListForComboboxesDao) {
		this.salestermsandestimateNamesListForComboboxesDao = salestermsandestimateNamesListForComboboxesDao;
	}

	/**
	 * procedureCallDaoを設定します。
	 * @param procedureCallDao procedureCallDao
	 */
	public void setProcedureCallDao(final ProcedureCallDao procedureCallDao) {
		this.procedureCallDao = procedureCallDao;
	}

	/**
	 * errorLogDaoを設定します。
	 * @param errorLogDao errorLogDao
	 */
	public void setErrorLogDao(final ErrorLogDao errorLogDao) {
		this.errorLogDao = errorLogDao;
	}

	/**
	 * salestermsAndEstimateItemCheckDaoを設定します。
	 * @param salestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao
	 */
	public void setSalestermsAndEstimateItemCheckDao(
			final SalestermsAndEstimateItemCheckDao salestermsAndEstimateItemCheckDao) {
		this.salestermsAndEstimateItemCheckDao = salestermsAndEstimateItemCheckDao;
	}
}
