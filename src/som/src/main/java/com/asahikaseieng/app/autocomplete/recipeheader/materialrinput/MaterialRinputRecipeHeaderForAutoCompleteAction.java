/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.materialrinput;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.materialrinput
									.MaterialRinputRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用アクション
 * @author tosco
 */
public class MaterialRinputRecipeHeaderForAutoCompleteAction extends AbstractAction {

	/** 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用ロジック */
	private MaterialRinputRecipeHeaderForAutoCompleteLogic materialRinputRecipeHeaderForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public MaterialRinputRecipeHeaderForAutoCompleteAction() {
	}

	/**
	 * 基本処方ヘッダ情報検索
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

		MaterialRinputRecipeHeaderForAutoCompleteForm frm
				= (MaterialRinputRecipeHeaderForAutoCompleteForm) form;

		// 基本処方ヘッダ情報を検索
		try {
			List<MaterialRinputRecipeHeaderForAutoComplete> result =
				materialRinputRecipeHeaderForAutoCompleteLogic.getSearchList(frm.getCode(),
					frm.getSrhItemCd());

			// Formに設定する
			List<MaterialRinputRecipeHeaderForAutoCompleteBean> list = getAutoCompleteBean(result);
			frm.setResult(list);

		} catch (NoDataException e) {

			// 検索結果が存在しない場合
			log.debug("基本処方ヘッダー情報検索結果なし");
		}
		return mapping.findForward("success");
	}

	//---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<MaterialRinputRecipeHeaderForAutoCompleteBean> getAutoCompleteBean
		(final List<MaterialRinputRecipeHeaderForAutoComplete> result) {
		List<MaterialRinputRecipeHeaderForAutoCompleteBean> list
			= new ArrayList<MaterialRinputRecipeHeaderForAutoCompleteBean>();
		for (MaterialRinputRecipeHeaderForAutoComplete bean : result) {
			MaterialRinputRecipeHeaderForAutoCompleteBean item
				= new MaterialRinputRecipeHeaderForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用Beanに移送する
	 * @param source 基本処方ヘッダー情報データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private MaterialRinputRecipeHeaderForAutoCompleteBean transferEntityData(
			final MaterialRinputRecipeHeaderForAutoComplete source,
			final MaterialRinputRecipeHeaderForAutoCompleteBean dest) {
		dest.setCode(source.getRecipeCdVersion());
		dest.setName(source.getRecipeName());
		dest.setRecipeId(source.getRecipeId());
		dest.setRecipeCd(source.getRecipeCd());
		dest.setRecipeVersion(source.getRecipeVersion());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用ロジックを設定します。
	 * @param materialRinputRecipeHeaderForAutoCompleteLogic
	 *             外注原材料投入実績入力－基本処方ヘッダ情報AutoComplete用ロジック
	 */
	public void setDeliveryForAutoCompleteLogic(
		final MaterialRinputRecipeHeaderForAutoCompleteLogic materialRinputRecipeHeaderForAutoCompleteLogic) {
		this.materialRinputRecipeHeaderForAutoCompleteLogic = materialRinputRecipeHeaderForAutoCompleteLogic;
	}

}
