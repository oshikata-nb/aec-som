/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装実績 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class PkgRdirectionInspectionListLogicImpl implements PkgRdirectionInspectionListLogic {

	/** 包装実績検査Dao */
	private PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao;

	/** 包装実績プロシージャDao */
	private PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao;

	/** 包装実績－共通ロジッククラス */
	private PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic;

	/**
	 * コンストラクタ.包装実績検査タブ
	 */
	public PkgRdirectionInspectionListLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を
	 * 包装実績工程テーブルより取得する
	 * @param form 包装実績 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final PkgRdirectionInspectionListForm form) {
		String[] res = {null, null};
		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			PkgRdirectionDirectionProcedureList bean = null;
			bean = pkgRdirectionDirectionProcedureListDao.getOperationName(decDirectionNo, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 包装実績-検査情報の検索を行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return List<PkgRdirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgRdirectionDirectionInspectionList> getSearchList(
		final PkgRdirectionInspectionListForm form)throws NoDataException {
		List<PkgRdirectionDirectionInspectionList> list = null;
		BigDecimal decDirectionDivision = null;
		String decDirectionNo = null;
		BigDecimal decStepNo = null;

		if (form.getDirectionDivision() != null) {
			decDirectionDivision = new BigDecimal(form.getDirectionDivision());
		}
		if (form.getDirectionNo() != null) {
			decDirectionNo = form.getDirectionNo();
		}
		if (form.getSelectKeyStepNo() != null) {
			decStepNo = new BigDecimal(form.getSelectKeyStepNo());
		}
		list = pkgRdirectionDirectionInspectionListDao.getSearchList(
			decDirectionDivision, decDirectionNo, decStepNo);

		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final PkgRdirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = addError(errors, "errors.pkgrdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = addError(errors, "errors.pkgrdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 行削除時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final PkgRdirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = addError(errors, "errors.pkgrdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = addError(errors, "errors.pkgrdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 包装実績検査情報の登録時のチェックを行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgRdirectionInspectionListForm form)  {
		ActionMessages errMsgs = new ActionMessages();

		if (form == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errMsgs = addError(errMsgs, "errors.pkgrdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errMsgs = addError(errMsgs, "errors.pkgrdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errMsgs;
	}

	/**
	 * 包装実績検査情報の登録処理を行う
	 * @param form 包装実績 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgRdirectionInspectionListForm form, final String tantoCd) throws Exception {

		try {
			BigDecimal directionDivision = new BigDecimal(form.getDirectionDivision());
			String directionStatus = form.getDirectionStatus();

			// 製造指図ヘッダーを包装実績入力済に更新する
			pkgRdirectionCommonsLogic.updateInputResultHeader(directionStatus, directionDivision,
				form.getDirectionNo(), form.getHeaderUpdateDate(), tantoCd);

			// 最終LINE_NOを取得する
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			PkgRdirectionDirectionInspectionList lineBean
				= pkgRdirectionDirectionInspectionListDao.getLastLineNo(
					directionDivision, decDirectionNo, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (PkgRdirectionDirectionInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// 指図区分、指図番号、STEP_NOで、包装実績検査テーブルより一括削除
			pkgRdirectionDirectionInspectionListDao.deleteByDirectionNoStepNo(
				directionDivision, decDirectionNo, decStepNo);

			for (PkgRdirectionDirectionInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 製造指図検査テーブルに登録
				int insertNum = pkgRdirectionDirectionInspectionListDao.insert(bean);
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
	 * メッセージを追加する
	 * 
	 * @param errors ActionMessages
	 * @param key リソースのキー
	 * @param objects オブジェクト
	 * @return ActionMessages メッセージ
	 */
	private static ActionMessages addError(final ActionMessages errors,
											final String key,
											final Object... objects) {
		ActionMessages tmpMsg = errors;
		if (tmpMsg == null) {
			tmpMsg = new ActionMessages();
		}
		tmpMsg.add("", new ActionMessage(key, objects));
		return tmpMsg;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 包装実績検査Daoを設定する
	 * @param pkgRdirectionDirectionInspectionListDao 包装実績検査Dao
	 */
	public void setPkgRdirectionDirectionInspectionListDao(
			final PkgRdirectionDirectionInspectionListDao pkgRdirectionDirectionInspectionListDao) {
		this.pkgRdirectionDirectionInspectionListDao = pkgRdirectionDirectionInspectionListDao;
	}

	/**
	 * 包装実績プロシージャDaoを設定する
	 * @param pkgRdirectionDirectionProcedureListDao 包装実績プロシージャDao
	 */
	public void setPkgRdirectionDirectionProcedureListDao(
			final PkgRdirectionDirectionProcedureListDao pkgRdirectionDirectionProcedureListDao) {
		this.pkgRdirectionDirectionProcedureListDao = pkgRdirectionDirectionProcedureListDao;
	}

	/**
	 * 包装実績－共通ロジッククラスを設定する
	 * @param pkgRdirectionCommonsLogic 包装実績－共通ロジッククラス
	 */
	public void setPkgRdirectionCommonsLogic(
			final PkgRdirectionCommonsLogic pkgRdirectionCommonsLogic) {
		this.pkgRdirectionCommonsLogic = pkgRdirectionCommonsLogic;
	}
}
