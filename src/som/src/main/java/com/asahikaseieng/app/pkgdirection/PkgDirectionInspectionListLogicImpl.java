/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 包装指図 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class PkgDirectionInspectionListLogicImpl implements PkgDirectionInspectionListLogic {

	/** 包装指図検査Dao */
	private PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao;

	/** 包装指図プロシージャDao */
	private PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao;

	/** 包装指図共通ロジッククラス */
	private PkgDirectionCommonsLogic pkgDirectionCommonsLogic;


	/**
	 * コンストラクタ.包装指図検査タブ
	 */
	public PkgDirectionInspectionListLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を
	 * 包装指図工程テーブルより取得する
	 * @param form 包装指図-検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final PkgDirectionInspectionListForm form) {
		String[] res = {null, null};
		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			PkgDirectionDirectionProcedureList bean = null;
			bean = pkgDirectionDirectionProcedureListDao.getOperationName(decDirectionNo, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 包装指図-検査情報の検索を行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return List<PkgDirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PkgDirectionDirectionInspectionList> getSearchList(
		final PkgDirectionInspectionListForm form)throws NoDataException {
		List<PkgDirectionDirectionInspectionList> list = null;
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
		list = pkgDirectionDirectionInspectionListDao.getSearchList(
			decDirectionDivision, decDirectionNo, decStepNo);

		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final PkgDirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = addError(errors, "errors.pkgdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = addError(errors, "errors.pkgdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 行削除時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final PkgDirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = addError(errors, "errors.pkgdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = addError(errors, "errors.pkgdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 包装指図検査情報の登録時のチェックを行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final PkgDirectionInspectionListForm form)  {
		ActionMessages errMsgs = new ActionMessages();
		ActionMessage errMsg = null;

		if (form == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 製品入出庫実績のレコード件数を取得
		int cnt = pkgDirectionCommonsLogic.getJissekiSeihinCount(form.getDirectionNo(), form.getItemCd());

		// 存在する場合は、更新不可
		if (cnt > 0) {
			errMsg = new ActionMessage("errors.pkgdirection.results.exist");
			errMsgs.add(ActionMessages.GLOBAL_MESSAGE, errMsg);
			return errMsgs;
		}

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errMsgs = addError(errMsgs, "errors.pkgdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errMsgs = addError(errMsgs, "errors.pkgdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errMsgs;
	}

	/**
	 * 包装指図検査情報の登録処理を行う
	 * @param form 包装指図-検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final PkgDirectionInspectionListForm form, final String tantoCd) throws Exception {

		try {
			BigDecimal directionDivision = new BigDecimal(form.getDirectionDivision());

			// 製造指図ヘッダーを未確定に更新する
			pkgDirectionCommonsLogic.updateUnconfirmedHeader(directionDivision, form.getDirectionNo(),
				form.getHeaderUpdateDate(), tantoCd);

			// 最終LINE_NOを取得する
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			PkgDirectionDirectionInspectionList lineBean
				= pkgDirectionDirectionInspectionListDao.getLastLineNo(
					directionDivision, decDirectionNo, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (PkgDirectionDirectionInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号、STEP_NOで、包装指図検査テーブルより一括削除
			pkgDirectionDirectionInspectionListDao.deleteByDirectionNoStepNo(
				directionDivision, decDirectionNo, decStepNo);

			for (PkgDirectionDirectionInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 製造指図検査テーブルに登録
				int insertNum = pkgDirectionDirectionInspectionListDao.insert(bean);
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
	 * 包装指図検査Daoを設定する
	 * @param pkgDirectionDirectionInspectionListDao 包装指図検査Dao
	 */
	public void setPkgDirectionDirectionInspectionListDao(
			final PkgDirectionDirectionInspectionListDao pkgDirectionDirectionInspectionListDao) {
		this.pkgDirectionDirectionInspectionListDao = pkgDirectionDirectionInspectionListDao;
	}

	/**
	 * 包装指図プロシージャDaoを設定する
	 * @param pkgDirectionDirectionProcedureListDao 包装指図プロシージャDao
	 */
	public void setPkgDirectionDirectionProcedureListDao(
			final PkgDirectionDirectionProcedureListDao pkgDirectionDirectionProcedureListDao) {
		this.pkgDirectionDirectionProcedureListDao = pkgDirectionDirectionProcedureListDao;

	}
	/**
	 * 包装指図共通ロジッククラスを設定します。
	 * @param pkgDirectionCommonsLogic 包装指図共通ロジッククラス
	 */
	public void setPkgDirectionCommonsLogic(final PkgDirectionCommonsLogic pkgDirectionCommonsLogic) {
		this.pkgDirectionCommonsLogic = pkgDirectionCommonsLogic;
	}
}
