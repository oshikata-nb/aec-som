/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionInspectionListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 製造実績 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class RdirectionInspectionListLogicImpl implements RdirectionInspectionListLogic {

	/** 製造実績検査Dao */
	private RdirectionDirectionInspectionListDao rdirectionDirectionInspectionListDao;

	/** 製造実績プロシージャDao */
	private RdirectionDirectionProcedureListDao rdirectionDirectionProcedureListDao;

	/**
	 * コンストラクタ.製造実績検査タブ
	 */
	public RdirectionInspectionListLogicImpl() {
	}

	/**
	 * 指図番号、STEP_NOに該当する工程コード、工程名称を
	 * 製造実績工程テーブルより取得する
	 * @param form 製造実績-検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final RdirectionInspectionListForm form) {
		String[] res = {null, null};
		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			RdirectionDirectionProcedureList bean = null;
			bean = rdirectionDirectionProcedureListDao.getOperationName(decDirectionNo, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 製造実績検査情報の検索を行う
	 * @param form 製造実績-検査一覧タブ Form
	 * @return List<RdirectionDirectionInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RdirectionDirectionInspectionList> getSearchList(
		final RdirectionInspectionListForm form)throws NoDataException {
		List<RdirectionDirectionInspectionList> list = null;
		String decDirectionNo = null;
		BigDecimal decStepNo = null;

		if (form.getDirectionNo() != null && form.getSelectKeyStepNo() != null) {
			decDirectionNo = form.getDirectionNo();
			decStepNo = new BigDecimal(form.getSelectKeyStepNo());
		}
		list = rdirectionDirectionInspectionListDao.getSearchList(decDirectionNo, decStepNo);

		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 製造実績-検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final RdirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = RdirectionUtil.addError(errors, "errors.rdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = RdirectionUtil.addError(errors, "errors.rdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 行削除時のチェックを行う
	 * @param form 製造実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final RdirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = RdirectionUtil.addError(errors, "errors.rdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = RdirectionUtil.addError(errors, "errors.rdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}


	/**
	 * 製造実績検査情報の登録時のチェックを行う
	 * @param form 製造実績 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final RdirectionInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = RdirectionUtil.addError(errors, "errors.rdirection.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errors = RdirectionUtil.addError(errors, "errors.rdirection.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 製造実績検査情報の登録処理を行う
	 * @param form 製造実績-検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final RdirectionInspectionListForm form, final String tantoCd) throws Exception {

		try {

			// 最終LINE_NOを取得する
			String decDirectionNo = form.getDirectionNo();
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			RdirectionDirectionInspectionList lineBean
				= rdirectionDirectionInspectionListDao.getLastLineNo(decDirectionNo, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (RdirectionDirectionInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// 指図番号、STEP_NOで、製造指図検査テーブルより一括削除
			rdirectionDirectionInspectionListDao.deleteByDirectionNoStepNo(decDirectionNo, decStepNo);

			for (RdirectionDirectionInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 製造指図検査テーブルに登録
				int insertNum = rdirectionDirectionInspectionListDao.insert(bean);
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

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績検査Daoを設定する
	 * @param rdirectionDirectionInspectionListDao 製造実績検査Dao
	 */
	public void setRdirectionDirectionInspectionListDao(
			final RdirectionDirectionInspectionListDao rdirectionDirectionInspectionListDao) {
		this.rdirectionDirectionInspectionListDao = rdirectionDirectionInspectionListDao;
	}

	/**
	 * 製造実績プロシージャDaoを設定する
	 * @param rdirectionDirectionProcedureListDao 製造実績プロシージャDao
	 */
	public void setRdirectionDirectionProcedureListDao(
			final RdirectionDirectionProcedureListDao rdirectionDirectionProcedureListDao) {
		this.rdirectionDirectionProcedureListDao = rdirectionDirectionProcedureListDao;

	}
}
