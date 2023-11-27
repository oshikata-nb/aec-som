/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * 基本処方 検査一覧タブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeInspectionListLogicImpl implements MgrecipeInspectionListLogic {

	/** 処方検査Dao */
	private RecipeInspectionListDao recipeInspectionListDao;

	/** 処方プロシージャDao */
	private RecipeProcedureListDao recipeProcedureListDao;

	/**
	 * コンストラクタ.基本処方検査タブ
	 */
	public MgrecipeInspectionListLogicImpl() {
	}

	/**
	 * レシピインデックス、STEP_NOに該当する工程コード、工程名称を<br>
	 * 処方工程テーブルより取得する
	 * @param form 基本処方 検査一覧タブ Form
	 * @return String[] 工程コード、工程名称
	 */
	public String[] getOperationName(final MgrecipeInspectionListForm form) {
		String[] res = {null, null};
		if (form.getRecipeId() != null && form.getSelectKeyStepNo() != null) {
			BigDecimal decRecipeId = new BigDecimal(form.getRecipeId());
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			RecipeProcedureList bean = null;
			bean = recipeProcedureListDao.getOperationName(decRecipeId, decStepNo);
			if (bean != null) {
				res[0] = bean.getOperationCd();		//工程コード
				res[1] = bean.getOperationName();	//工程名称
			}
		}
		return res;
	}

	/**
	 * 処方検査情報の検索を行う
	 * @param form 基本処方 検査一覧タブ Form
	 * @return List<RecipeInspectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RecipeInspectionList> getSearchList(final MgrecipeInspectionListForm form) throws NoDataException {
		List<RecipeInspectionList> list = null;
		BigDecimal decRecipeId = null;
		BigDecimal decStepNo = null;

		if (form.getRecipeId() != null) {
			decRecipeId = new BigDecimal(form.getRecipeId());
		}
		if (form.getSelectKeyStepNo() != null) {
			decStepNo = new BigDecimal(form.getSelectKeyStepNo());
		}
		list = recipeInspectionListDao.getSearchList(decRecipeId, decStepNo);
		if (list.isEmpty()) {
			form.setSearchInspectionList(list);
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 行追加時のチェックを行う
	 * @param form 基本処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForAddList(final MgrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行追加処理の場合はエラー
			if (form.getOperationCd() == null) {
				errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 行削除時のチェックを行う
	 * @param form 基本処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForDelList(final MgrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での行削除処理はエラー
			if (form.getOperationCd() == null) {
				errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 処方検査情報の登録時のチェックを行う
	 * @param form 基本処方 検査一覧タブ Form
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(final MgrecipeInspectionListForm form)  {
		ActionMessages errors = new ActionMessages();

		// 工程データ未登録の場合エラー
		if (form.getProcedureStepNoCombo() == null || form.getProcedureStepNoCombo().isEmpty()) {
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.procedure");
			form.setDirtyFlg(null);
		} else {

			// 検索されていない状態での登録処理はエラー
			if (form.getOperationCd() == null) {
				errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.search");
				form.setDirtyFlg(null);
			}
		}
		return errors;
	}

	/**
	 * 処方検査情報の登録処理を行う
	 * @param form 基本処方 検査一覧タブ Form
	 * @param tantoCd 担当者コード
	 * @throws Exception データが存在しない例外
	 */
	public void regist(final MgrecipeInspectionListForm form, final String tantoCd) throws Exception {

		try {

			// 最終LINE_NOを取得する
			BigDecimal decRecipeId = new BigDecimal(form.getRecipeId());
			BigDecimal decStepNo = new BigDecimal(form.getSelectKeyStepNo());
			RecipeInspectionList lineBean = recipeInspectionListDao.getLastLineNo(decRecipeId, decStepNo);
			BigDecimal lastLineNo = lineBean.getLastLineNo();

			// 新規追加行にLINE_NOを設定する
			for (RecipeInspectionList bean : form.getSearchInspectionList()) {
				if (bean.getLineNo() == null || bean.getLineNo().equals("")) {
					bean.setLineNo(lastLineNo);
					lastLineNo = lastLineNo.add(BigDecimal.ONE);
				}
			}

			// レシピインデックス、STEP_NOで、処方検査テーブルより一括削除
			recipeInspectionListDao.deleteByRecipeIdStepNo(decRecipeId, decStepNo);

			for (RecipeInspectionList bean : form.getSearchInspectionList()) {

				// 新規追加行の場合
				if (bean.getInputDate() == null) {
					bean.setInputorCd(tantoCd); // 登録者コード
					bean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				}
				bean.setUpdatorCd(tantoCd); // 更新者コード

				// 処方検査テーブルに登録
				int insertNum = recipeInspectionListDao.insert(bean);
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
	 * @param recipeInspectionListDao 処方検査Dao
	 */
	public void setRecipeInspectionListDao(
			final RecipeInspectionListDao recipeInspectionListDao) {
		this.recipeInspectionListDao = recipeInspectionListDao;
	}

	/**
	 * 処方プロシージャDaoを設定する
	 * @param recipeProcedureListDao 処方プロシージャDao
	 */
	public void setRecipeProcedureListDao(final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;
	}
}
