/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.RecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 設備のAuto Complete用アクション
 * @author t0011036
 */
public class RecipeResouceForAutoCompleteAction extends AbstractAction {

	/** 設備のAuto Complete用ロジック */
	private RecipeResouceForAutoCompleteLogic recipeResouceAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public RecipeResouceForAutoCompleteAction() {
	}

	/**
	 * 検索画面用設備検索 設備名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchRecipeResouce(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchRecipeResouce.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 設備を設備コードまたは設備名称で検索 */
		try {
			List<RecipeResouceForAutoComplete> result = recipeResouceAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<RecipeResouceForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("設備検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<RecipeResouceForAutoCompleteBean> getAutoCompleteBean(
			final List<RecipeResouceForAutoComplete> result) {
		List<RecipeResouceForAutoCompleteBean> list = new ArrayList<RecipeResouceForAutoCompleteBean>();

		for (RecipeResouceForAutoComplete bean : result) {
			RecipeResouceForAutoCompleteBean item = new RecipeResouceForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 設備データをオートコンプリート用Beanに移送する
	 * @param source設備データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private RecipeResouceForAutoCompleteBean transferEntityData(
			final RecipeResouceForAutoComplete source,
			final RecipeResouceForAutoCompleteBean dest) {
		dest.setCode(source.getResouceCd());
		dest.setName(source.getResouceName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * recipeResouceAutoCompleteLogicを設定します。
	 * @param recipeResouceAutoCompleteLogic recipeResouceAutoCompleteLogic
	 */
	public void setRecipeResouceAutoCompleteLogic(
			final RecipeResouceForAutoCompleteLogic recipeResouceAutoCompleteLogic) {
		this.recipeResouceAutoCompleteLogic = recipeResouceAutoCompleteLogic;
	}
}
