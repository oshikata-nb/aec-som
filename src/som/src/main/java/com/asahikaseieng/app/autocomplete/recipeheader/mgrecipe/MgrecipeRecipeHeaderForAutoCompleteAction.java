/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.mgrecipe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.mgrecipe.MgrecipeRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 基本処方－原処方ｺｰﾄﾞAutoComplete用アクション
 * @author tosco
 */
public class MgrecipeRecipeHeaderForAutoCompleteAction extends AbstractAction {

	/** 基本処方－原処方ｺｰﾄﾞAutoComplete用ロジック */
	private MgrecipeRecipeHeaderForAutoCompleteLogic mgrecipeRecipeHeaderForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public MgrecipeRecipeHeaderForAutoCompleteAction() {
	}

	/**
	 * 原処方ｺｰﾄﾞ検索
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchRecipeHeader(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchRecipeHeader.");
		}
		MgrecipeRecipeHeaderForAutoCompleteForm frm = (MgrecipeRecipeHeaderForAutoCompleteForm) form;

		// 原処方ｺｰﾄﾞを検索
		try {
			List<MgrecipeRecipeHeaderForAutoComplete> result =
				mgrecipeRecipeHeaderForAutoCompleteLogic.getSearchList(frm.getCode());

			// Formに設定する
			List<MgrecipeRecipeHeaderForAutoCompleteBean> list = getAutoCompleteBean(result);
			frm.setResult(list);

		} catch (NoDataException e) {

			// 検索結果が存在しない場合
			log.debug("原処方ｺｰﾄﾞ検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<MgrecipeRecipeHeaderForAutoCompleteBean> getAutoCompleteBean
		(final List<MgrecipeRecipeHeaderForAutoComplete> result) {
		List<MgrecipeRecipeHeaderForAutoCompleteBean> list =
			new ArrayList<MgrecipeRecipeHeaderForAutoCompleteBean>();
		// 検索結果分繰り返す
		for (MgrecipeRecipeHeaderForAutoComplete bean : result) {
			MgrecipeRecipeHeaderForAutoCompleteBean grecipe =
				new MgrecipeRecipeHeaderForAutoCompleteBean();
			transferEntityData(bean, grecipe);
			list.add(grecipe);
		}
		return list;
	}

	/**
	 * 基本処方－原処方ｺｰﾄﾞAutoComplete用Beanに移送する
	 * @param source 基本処方ヘッダー情報データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private MgrecipeRecipeHeaderForAutoCompleteBean transferEntityData(
			final MgrecipeRecipeHeaderForAutoComplete source,
			final MgrecipeRecipeHeaderForAutoCompleteBean dest) {
		dest.setCode(source.getRecipeCd());
		dest.setRecipeCd(source.getRecipeCd());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 基本処方－原処方ｺｰﾄﾞAutoComplete用ロジックを設定します。
	 * @param mgrecipeRecipeHeaderForAutoCompleteLogic 基本処方－原処方ｺｰﾄﾞAutoComplete用ロジック
	 */
	public void setMgrecipeRecipeHeaderForAutoCompleteLogic(
		final MgrecipeRecipeHeaderForAutoCompleteLogic mgrecipeRecipeHeaderForAutoCompleteLogic) {
		this.mgrecipeRecipeHeaderForAutoCompleteLogic = mgrecipeRecipeHeaderForAutoCompleteLogic;
	}

}
