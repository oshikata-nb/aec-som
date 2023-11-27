/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図－工程一覧画面 ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionProcedureListLogicImpl implements PkgDirectionProcedureListLogic {

	/** 包装指図－製造指図プロシージャDao */
	private PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao;

	/** 包装指図－製造指図フォーミュラDao */
	private PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao;

	/** 包装指図－製造指図検査Dao */
	private PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao;

	/** 工程マスタDao */
	private OperationDao operationDao;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public PkgDirectionProcedureListLogicImpl() {
	}

	/**
	 * 包装指図－製造指図プロシージャ検索処理を行う.
	 * @param frm 包装指図－工程一覧画面 Form
	 * @return List<PkgDirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgDirectionDirectionProcedureList> getSearchList(final PkgDirectionProcedureListForm frm)
		throws NoDataException {

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

		// 製造指図プロシージャを検索
		List<PkgDirectionDirectionProcedureList> list
			= pkgDirectionDirectionProcedureListDao.getList(directionDivision, frm.getDirectionNo());
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行削除時の存在チェックを行う.<br>
	 * 　製造指図フォーミュラ、製造指図検査テーブルにデータがある場合はエラーとする。
	 * @param searchProcList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final List<PkgDirectionDirectionProcedureList> searchProcList) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			PkgDirectionDirectionProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directionNo = bean.getDirectionNo();
				BigDecimal stepNo = bean.getStepNo();

				// 工程に紐づく処方フォーミュラを検索
				List<PkgDirectionDirectionFormulaList> formulaList
					= pkgDirectionDirectionFormulaListDao.findByDirectionNoStepNo
					(directionDivision, directionNo, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errMsg = new ActionMessage
					("errors.pkgdirection.proc.exist.formula.row", String.valueOf(i + 1));
				}
				if (errMsg != null) {
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					errMsg = null;
				}

				// 工程に紐づく処方検査を検索
				List<PkgDirectionDirectionInspectionList> inspectionList
					= pkgDirectionDirectionInspectionListDao.findByDirectionNoStepNo
					(directionDivision, directionNo, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errMsg = new ActionMessage
					("errors.pkgdirection.proc.exist.inspection.row", String.valueOf(i + 1));
				}
				if (errMsg != null) {
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					errMsg = null;
				}

				// 工程に紐づく製造指図フォーミュラ（仕上げ）を検索
				List<PkgDirectionDirectionFormulaList> finishList
					= pkgDirectionDirectionFormulaListDao.getFinishList
						(directionDivision, directionNo, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上げが存在する場合
					errMsg = new ActionMessage
					("errors.pkgdirection.proc.exist.finish.row", String.valueOf(i + 1));
				}
				if (errMsg != null) {
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					errMsg = null;
				}
			}
		}
		return errMsgs;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param frm 包装指図－工程一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgDirectionProcedureListForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(frm.getDirectionNo(), frm.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < frm.getSearchProcList().size(); i++) {
			PkgDirectionDirectionProcedureList bean = frm.getSearchProcList().get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errMsg = new ActionMessage
				("errors.pkgdirection.no.operation.row", String.valueOf(i + 1));
			}
			if (errMsg != null) {
				errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
				errMsg = null;
			}

		}
		return errMsgs;
	}

	/**
	 * 製造指図プロシージャ登録処理を行う.
	 * @param frm 包装指図－工程一覧画面 Form
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void regist(final PkgDirectionProcedureListForm frm, final String tantoCd) throws Exception {
		int insertNum;
		PkgDirectionDirectionProcedureList stepBean = null;

		try {
			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

			// 製造指図ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, frm.getDirectionNo(),
				frm.getHeaderUpdateDate(), tantoCd);

			// 最終STEP_NO を取得
			stepBean = pkgDirectionDirectionProcedureListDao.getLastStepNo
				(directionDivision, frm.getDirectionNo());
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (PkgDirectionDirectionProcedureList bean : frm.getSearchProcList()) {

				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// 包装指図番号で一括削除
			pkgDirectionDirectionProcedureListDao.deleteByDirectionNo
				(directionDivision, frm.getDirectionNo());

			// 登録処理
			for (PkgDirectionDirectionProcedureList bean : frm.getSearchProcList()) {

				Timestamp updDate = AecDateUtils.getCurrentTimestamp();
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd);	// 登録者コード
					bean.setInputDate(updDate);	// 登録日時
				}
				bean.setUpdatorCd(tantoCd); 		// 更新者コード
				bean.setUpdateDate(updDate);		// 更新日時
				insertNum = pkgDirectionDirectionProcedureListDao.insert(bean);
				if (insertNum != 1) {
					// 一意制約エラー
					throw new DuplicateRuntimeException(0);
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * パラメータチェック
	 * @param PkgDirectionProcedureListForm 包装指図－工程一覧画面 Form
	 */
	private void checkParams(final PkgDirectionProcedureListForm frm) {

		if (frm.getDirectionDivision() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : "
					+ "directionDivision is empty.パラメータチェック.getSearchList");
		}
		if (frm.getDirectionNo() == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : "
					+ "directionNo is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装指図－製造指図プロシージャDao設定
	 * @param pkgDirectionDirectionProcedureListDao 包装指図－製造指図プロシージャDao
	 */
	public void setPkgDirectionDirectionProcedureListDao
		(final PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao) {
		this.pkgDirectionDirectionProcedureListDao = pkgDirectionDirectionProcedureListDao;

	}

	/**
	 * 包装指図－製造指図フォーミュラDao設定
	 * @param pkgDirectionDirectionFormulaListDao 包装指図－製造指図フォーミュラDao
	 */
	public void setPkgDirectionDirectionFormulaListDao
		(final PkgDirectionDirectionFormulaListDao pkgDirectionDirectionFormulaListDao) {
		this.pkgDirectionDirectionFormulaListDao = pkgDirectionDirectionFormulaListDao;
	}

	/**
	 * 包装指図－製造指図検査Dao設定
	 * @param pkgDirectionDirectionInspectionListDao 包装指図－製造指図検査Dao
	 */
	public void setPkgDirectionDirectionInspectionListDao(
			final PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao) {
		this.pkgDirectionDirectionInspectionListDao = pkgDirectionDirectionInspectionListDao;
	}

	/**
	 * 工程マスタDao設定
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 基本処方共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}
}
