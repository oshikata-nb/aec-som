/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesoucegroup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesoucegroup.RecipeResouceGroupForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 設備グループのAuto Complete用アクション
 * @author t0011036
 */
public class RecipeResouceGroupForAutoCompleteAction extends AbstractAction {

	/** 設備グループのAuto Complete用ロジック */
	private RecipeResouceGroupForAutoCompleteLogic recipeResouceGroupAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceGroupForAutoCompleteAction() {
	}

	/**
	 * 検索画面用設備グループ検索 設備グループ名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchRecipeResouceGroup(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchRecipeResouceGroup.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 設備グループを設備グループコードまたは設備グループ名称で検索 */
		try {
			List<RecipeResouceGroupForAutoComplete> result = recipeResouceGroupAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<RecipeResouceGroupForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("設備グループ検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<RecipeResouceGroupForAutoCompleteBean> getAutoCompleteBean(
			final List<RecipeResouceGroupForAutoComplete> result) {
		List<RecipeResouceGroupForAutoCompleteBean> list
			= new ArrayList<RecipeResouceGroupForAutoCompleteBean>();

		for (RecipeResouceGroupForAutoComplete bean : result) {
			RecipeResouceGroupForAutoCompleteBean item = new RecipeResouceGroupForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 設備グループデータをオートコンプリート用Beanに移送する
	 * @param source設備グループデータ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private RecipeResouceGroupForAutoCompleteBean transferEntityData(
			final RecipeResouceGroupForAutoComplete source,
			final RecipeResouceGroupForAutoCompleteBean dest) {
		dest.setCode(source.getResouceGroupCd());
		dest.setName(source.getResouceGroupName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * recipeResouceGroupAutoCompleteLogicを設定します。
	 * @param recipeResouceGroupAutoCompleteLogic
	 *            recipeResouceGroupAutoCompleteLogic
	 */
	public void setRecipeResouceGroupAutoCompleteLogic(
			final RecipeResouceGroupForAutoCompleteLogic recipeResouceGroupAutoCompleteLogic) {
		this.recipeResouceGroupAutoCompleteLogic = recipeResouceGroupAutoCompleteLogic;
	}
}
