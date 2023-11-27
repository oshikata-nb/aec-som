/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemcategory;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.app.autocomplete.GeneralParameterForm;
import com.asahikaseieng.dao.nonentity.autocomplete.itemcategory.ItemCategoryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.struts.AbstractAction;

/**
 * 品目分類のAuto Complete用アクション
 * @author t0011036
 */
public class ItemCategoryForAutoCompleteAction extends AbstractAction {

	/** 品目分類のAuto Complete用ロジック */
	private ItemCategoryForAutoCompleteLogic itemCategoryForAutoCompleteLogic;

	/**
	 * コンストラクタ
	 */
	public ItemCategoryForAutoCompleteAction() {
	}

	/**
	 * 検索画面用品目分類検索 品目分類名称
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward searchItemCategory(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchItemCategory.");
		}

		GeneralParameterForm paramForm = (GeneralParameterForm) form;

		/* 品目分類を品目分類コードまたは品目分類名称で検索 */
		try {
			List<ItemCategoryForAutoComplete> result = itemCategoryForAutoCompleteLogic
					.getSearchList(paramForm.getCode());

			/* Formに設定する */
			List<ItemCategoryForAutoCompleteBean> list = getAutoCompleteBean(result);
			paramForm.setResult(list);
		} catch (NoDataException e) {
			/* 検索結果が存在しない場合 */
			log.debug("品目分類検索結果なし");
		}

		return mapping.findForward("success");
	}

	// ---------------------------------------------------------------------------------------
	/**
	 * 検索結果からオートコンプリート表示用のBeanリストを作成する
	 * @param result 検索結果
	 * @return オートコンプリート表示用のBeanリスト
	 */
	private List<ItemCategoryForAutoCompleteBean> getAutoCompleteBean(
			final List<ItemCategoryForAutoComplete> result) {
		List<ItemCategoryForAutoCompleteBean> list = new ArrayList<ItemCategoryForAutoCompleteBean>();

		for (ItemCategoryForAutoComplete bean : result) {
			ItemCategoryForAutoCompleteBean item = new ItemCategoryForAutoCompleteBean();
			transferEntityData(bean, item);
			list.add(item);
		}

		return list;
	}

	/**
	 * 品目分類データをオートコンプリート用Beanに移送する
	 * @param source品目分類データ
	 * @param dest オートコンプリート用Bean
	 * @return オートコンプリート用Bean
	 */
	private ItemCategoryForAutoCompleteBean transferEntityData(
			final ItemCategoryForAutoComplete source,
			final ItemCategoryForAutoCompleteBean dest) {
		dest.setCode(source.getItemCategory());
		dest.setName(source.getItemCategoryName());
		return dest;
	}

	// ------------------------------------------------------------------------------

	/**
	 * itemCategoryForAutoCompleteLogicを設定します。
	 * @param itemCategoryForAutoCompleteLogic itemCategoryForAutoCompleteLogic
	 */
	public void setItemCategoryForAutoCompleteLogic(
			final ItemCategoryForAutoCompleteLogic itemCategoryForAutoCompleteLogic) {
		this.itemCategoryForAutoCompleteLogic = itemCategoryForAutoCompleteLogic;
	}
}
