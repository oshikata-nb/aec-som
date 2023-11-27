/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 原処方 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class GrecipeInspectionListLogicImpl implements GrecipeInspectionListLogic {

	/** 処方検査Dao */
	private GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao;

	/** 処方プロシージャDao */
	private GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao;

	/**
	 * コンストラクタ.原処方検査タブ
	 */
	public GrecipeInspectionListLogicImpl() {
	}

	/**
	 * レシピインデックス、STEP_NOに該当する工程コード、工程名称を<br>
	 * 処方工程テーブルより取得する
	 * @param form 原処方 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final GrecipeInspectionListForm form) {
		String[] res = {null, null};
		if (form.getRecipeId() != null && form.getSelectKeyStepNo() != null) {
			BigDecimal decRecipeId = new BigDecimal(form.getRecipeId());
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			GrecipeRecipeProcedureList bean = null;
			bean = grecipeRecipeProcedureListDao.getOperationName(decRecipeId, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 処方検査情報の検索を行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return List<GrecipeRecipeInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<GrecipeRecipeInspectionList> getSearchList(
		final GrecipeInspectionListForm form) throws NoDataException {
		List<GrecipeRecipeInspectionList> list = null;
		BigDecimal decRecipeId = null;
		BigDecimal decStepNo = null;

		if (form.getRecipeId() != null) {
			decRecipeId = new BigDecimal(form.getRecipeId());
		}
		if (form.getSelectKeyStepNo() != null) {
			decStepNo = new BigDecimal(form.getSelectKeyStepNo());
		}
		list = grecipeRecipeInspectionListDao.getSearchList(decRecipeId, decStepNo);
		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final GrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = GrecipeUtil.addError(errors, "errors.grecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = GrecipeUtil.addError(errors, "errors.grecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 行削除時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final GrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = GrecipeUtil.addError(errors, "errors.grecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = GrecipeUtil.addError(errors, "errors.grecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 処方検査情報の登録時のチェックを行う
	 * @param form 原処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final GrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = GrecipeUtil.addError(errors, "errors.grecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errors = GrecipeUtil.addError(errors, "errors.grecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 処方検査情報の登録処理を行う
	 * @param form 原処方 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final GrecipeInspectionListForm form, final String tantoCd) throws Exception {

		try {

			// 最終LINE_NOを取得する
			BigDecimal decRecipeId = new BigDecimal(form.getRecipeId());
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			GrecipeRecipeInspectionList lineBean = grecipeRecipeInspectionListDao.getLastLineNo(
				decRecipeId, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (GrecipeRecipeInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// レシピインデックス、STEP_NOで、処方検査テーブルより一括削除
			grecipeRecipeInspectionListDao.deleteByRecipeIdStepNo(decRecipeId, decStepNo);

			for (GrecipeRecipeInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 処方検査テーブルに登録
				int insertNum = grecipeRecipeInspectionListDao.insert(bean);
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
	 * 処方検査Daoを設定する
	 * @param grecipeRecipeInspectionListDao 処方検査Dao
	 */
	public void setGrecipeRecipeInspectionListDao(
			final GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao) {
		this.grecipeRecipeInspectionListDao = grecipeRecipeInspectionListDao;
	}

	/**
	 * 処方プロシージャDaoを設定する
	 * @param grecipeRecipeProcedureListDao 処方プロシージャDao
	 */
	public void setGrecipeRecipeProcedureListDao(
			final GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao) {
		this.grecipeRecipeProcedureListDao = grecipeRecipeProcedureListDao;
	}
}
