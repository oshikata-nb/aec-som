/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipeheader.pkgdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.autocomplete.recipeheader.pkgdirection.PkgDirectionRecipeHeaderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 包装指図－基本処方ヘッダ情報AutoComplete用アクション
 * @author tosco
 */
public class PkgDirectionRecipeHeaderForAutoCompleteAction extends AbstractAction {

	/** 包装指図－基本処方ヘッダ情報AutoComplete用ロジック */
	private PkgDirectionRecipeHeaderForAutoCompleteLogic pkgDirectionRecipeHeaderForAutoCompleteLogic;
	/**
	 * コンストラクタ
	 */
	public PkgDirectionRecipeHeaderForAutoCompleteAction() {
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
		PkgDirectionRecipeHeaderForAutoCompleteForm frm = (PkgDirectionRecipeHeaderForAutoCompleteForm) form;

		// 基本処方ヘッダ情報を検索
		try {
			List<PkgDirectionRecipeHeaderForAutoComplete> result =
				pkgDirectionRecipeHeaderForAutoCompleteLogic.getSearchList(frm.getCode(),
					frm.getSrhItemCd(), frm.getSrhProductionLine(), frm.getSrhOtherCompanyCd1());

			// Formに設定する
			List<PkgDirectionRecipeHeaderForAutoCompleteBean> list = getAutoCompleteBean(result);
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
	private List<PkgDirectionRecipeHeaderForAutoCompleteBean> getAutoCompleteBean
		(final List<PkgDirectionRecipeHeaderForAutoComplete> result) {
		List<PkgDirectionRecipeHeaderForAutoCompleteBean> list
			= new ArrayList<PkgDirectionRecipeHeaderForAutoCompleteBean>();
		for (PkgDirectionRecipeHeaderForAutoComplete bean : result) {
			PkgDirectionRecipeHeaderForAutoCompleteBean item
				= new PkgDirectionRecipeHeaderForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}
		return list;
	}

	/**
	 * 包装指図－基本処方ヘッダ情報AutoComplete用Beanに移送する
	 * @param source 基本処方ヘッダー情報データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private PkgDirectionRecipeHeaderForAutoCompleteBean transferEntityData(
			final PkgDirectionRecipeHeaderForAutoComplete source,
			final PkgDirectionRecipeHeaderForAutoCompleteBean dest) {
		dest.setCode(source.getRecipeCdVersion());
		dest.setName(source.getRecipeName());
		dest.setItemCd(source.getItemCd());
		dest.setItemName(source.getItemName());
		dest.setOtherCompanyCd1(source.getOtherCompanyCd1());
		dest.setProductionLine(source.getProductionLine());
		dest.setStyleOfPacking(source.getStyleOfPacking());
		dest.setRecipeId(source.getRecipeId());
		dest.setName01(source.getName01());
		return dest;
	}
	//------------------------------------------------------------------------------
	/**
	 * 包装指図－基本処方ヘッダ情報AutoComplete用ロジックを設定します。
	 * @param pkgDirectionRecipeHeaderForAutoCompleteLogic 包装指図－基本処方ヘッダ情報AutoComplete用ロジック
	 */
	public void setDeliveryForAutoCompleteLogic(
		final PkgDirectionRecipeHeaderForAutoCompleteLogic pkgDirectionRecipeHeaderForAutoCompleteLogic) {
		this.pkgDirectionRecipeHeaderForAutoCompleteLogic = pkgDirectionRecipeHeaderForAutoCompleteLogic;
	}

}
