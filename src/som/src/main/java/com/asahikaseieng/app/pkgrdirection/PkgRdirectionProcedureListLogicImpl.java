/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionFormulaListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;



/**
 * 包装実績－工程一覧画面 ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionProcedureListLogicImpl implements PkgRdirectionProcedureListLogic {

	/** 包装実績－製造指図プロシージャDao */
	private PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao;

	/** 包装実績－製造指図フォーミュラDao */
	private PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao;

	/** 包装実績－製造指図検査Dao */
	private PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao;

	/** 包装実績共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public PkgRdirectionProcedureListLogicImpl() {
	}

	/**
	 * 包装実績－製造指図プロシージャ検索処理を行う.
	 * @param frm 包装実績－工程一覧画面 Form
	 * @return List<PkgRdirectionDirectionProcedureList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgRdirectionDirectionProcedureList> getSearchList(final PkgRdirectionProcedureListForm frm)
		throws NoDataException {

		checkParams(frm);
		BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());

		// 製造指図プロシージャを検索
		List<PkgRdirectionDirectionProcedureList> list
			= pkgRdirectionDirectionProcedureListDao.getList(directionDivision, frm.getDirectionNo());
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
	public ActionMessages checkForDelList(final List<PkgRdirectionDirectionProcedureList> searchProcList) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (searchProcList == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForDelList");
		}

		// 配合・検査存在チェック
		for (int i = 0; i < searchProcList.size(); i++) {
			PkgRdirectionDirectionProcedureList bean = searchProcList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}
			// 行追加していない行の場合
			if (bean.getStepNo() != null) {
				BigDecimal directionDivision = bean.getDirectionDivision();
				String directionNo = bean.getDirectionNo();
				BigDecimal stepNo = bean.getStepNo();

				// 工程に紐づく製造指図フォーミュラを検索
				List<PkgRdirectionDirectionFormulaList> formulaList
					= pkgRdirectionDirectionFormulaListDao.findByDirectionNoStepNo
					(directionDivision, directionNo, stepNo);
				if (!formulaList.isEmpty()) {
					// 配合が存在する場合
					errMsg = new ActionMessage
					("errors.pkgrdirection.proc.exist.formula.row", String.valueOf(i + 1));
				}

				// 工程に紐づく製造指図検査を検索
				List<PkgRdirectionDirectionInspectionList> inspectionList
					= pkgRdirectionDirectionInspectionListDao.findByDirectionNoStepNo
					(directionDivision, directionNo, stepNo);
				if (!inspectionList.isEmpty()) {
					// 検査が存在する場合
					errMsg = new ActionMessage
					("errors.pkgrdirection.proc.exist.inspection.row", String.valueOf(i + 1));
				}
				if (errMsg != null) {
					errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
					errMsg = null;
				}

				//工程に紐づく製造指図フォーミュラ（仕上げ）を検索
				List<PkgRdirectionDirectionFormulaList> finishList
					= pkgRdirectionDirectionFormulaListDao.getFinishList
						(directionDivision, directionNo, stepNo);
				if (!finishList.isEmpty()) {
					// 仕上げが存在する場合
					errMsg = new ActionMessage
					("errors.pkgrdirection.proc.exist.finish.row", String.valueOf(i + 1));
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
	 * @param frm 包装実績－工程一覧画面 Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgRdirectionProcedureListForm frm) {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (frm == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程マスタ存在チェック
		for (int i = 0; i < frm.getSearchProcList().size(); i++) {
			PkgRdirectionDirectionProcedureList bean = frm.getSearchProcList().get(i);

			// 工程マスタを検索
			Operation opeBean = operationDao.getEntity(bean.getOperationCd());

			if (opeBean == null) {
				// データが存在しない場合
				errMsg = new ActionMessage
				("errors.pkgrdirection.no.operation.row", String.valueOf(i + 1));
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
	 * @param frm 包装実績－工程一覧画面 Form
	 * @param tantoCd 担当者コード
	 */
	public void regist(final PkgRdirectionProcedureListForm frm, final String tantoCd) {
		int insertNum;
		PkgRdirectionDirectionProcedureList stepBean = null;

		try {
			BigDecimal directionDivision = new BigDecimal(frm.getDirectionDivision());
			String directionStatus = frm.getDirectionStatus();

			//製造指図ヘッダーを更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(directionStatus, directionDivision,
				frm.getDirectionNo(), frm.getHeaderUpdateDate(), tantoCd);

			// 最終STEP_NO を取得
			stepBean = pkgRdirectionDirectionProcedureListDao.getLastStepNo
				(directionDivision, frm.getDirectionNo());
			BigDecimal lastStepNo = stepBean.getLastStepNo();

			for (PkgRdirectionDirectionProcedureList bean : frm.getSearchProcList()) {
				// STEP_NO 更新
				if (bean.getStepNo() == null || bean.getStepNo().equals("")) {
					bean.setStepNo(lastStepNo);
					lastStepNo = lastStepNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号で一括削除
			pkgRdirectionDirectionProcedureListDao.deleteByDirectionNo
				(directionDivision, frm.getDirectionNo());

			// 登録処理
			for (PkgRdirectionDirectionProcedureList bean : frm.getSearchProcList()) {

				Timestamp updDate = AecDateUtils.getCurrentTimestamp();
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd);	// 登録者コード
					bean.setInputDate(updDate);	// 登録日時
				}
				bean.setUpdatorCd(tantoCd); 		// 更新者コード
				bean.setUpdateDate(updDate);		// 更新日時
				insertNum = pkgRdirectionDirectionProcedureListDao.insert(bean);

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
	 * @param PkgRdirectionProcedureListForm 包装実績－工程一覧画面 Form
	 */
	private void checkParams(final PkgRdirectionProcedureListForm frm) {

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
	 * 包装実績－製造指図プロシージャDao設定
	 * @param pkgRdirectionDirectionProcedureListDao 包装実績－製造指図プロシージャDao
	 */
	public void setPkgRdirectionDirectionProcedureListDao
		(final PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao) {
		this.pkgRdirectionDirectionProcedureListDao = pkgRdirectionDirectionProcedureListDao;

	}

	/**
	 * 包装実績－製造指図フォーミュラDao設定
	 * @param pkgRdirectionDirectionFormulaListDao 包装実績－製造指図フォーミュラDao
	 */
	public void setPkgRdirectionDirectionFormulaListDao
		(final PkgRdirectionDirectionFormulaListDao pkgRdirectionDirectionFormulaListDao) {
		this.pkgRdirectionDirectionFormulaListDao = pkgRdirectionDirectionFormulaListDao;
	}

	/**
	 * 包装実績－製造指図検査Dao設定
	 * @param pkgRdirectionDirectionInspectionListDao 包装実績－製造指図検査Dao
	 */
	public void setPkgRdirectionDirectionInspectionListDao(
			final PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao) {
		this.pkgRdirectionDirectionInspectionListDao = pkgRdirectionDirectionInspectionListDao;
	}

	/**
	 * 包装実績共通ロジッククラスを設定します。
	 * @param pkgRdirectionCommonsLogic 包装実績共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}

	/**
	 * 工程マスタDao設定
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
