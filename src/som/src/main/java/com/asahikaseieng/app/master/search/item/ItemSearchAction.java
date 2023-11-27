/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.item;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchList;
import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 品目マスタ検索(ポップアップ) Actionクラス.
 * @author tosco
 */
public final class ItemSearchAction extends AbstractSearchAction {

	/** 品目マスタ検索(ポップアップ)ロジック */
	private ItemSearchLogic itemSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public ItemSearchAction() {
		super();
	}

	/**
	 * 初期処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward initImpl(final ActionMapping mapping,
			final AbstractSearchForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("initImpl");
		}

		/* 初期検索有り */
		return null;
	}

	/**
	 * 品目マスタ検索処理
	 * 
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	protected ActionForward searchImpl(final ActionMapping mapping,
			final HttpServletRequest request, final AbstractSearchForm form)
			throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("searchImpl");
		}

		ItemSearchForm frm = (ItemSearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<ItemSearchList>());

		/* 検索条件を取得 */
		ItemSearchListPagerCondition condition = (ItemSearchListPagerCondition) frm.getPager()
				.getPagerCondition();
		/* 検索条件をセット */
		condition.setItemCd(frm.getSrhItemCd());
		condition.setItemName(frm.getSrhItemName());
		condition.setOtherCompanyCd1(frm.getSrhOtherCompanyCd1());

		/* 明細取得 */
		frm.setSearchList(itemSearchLogic.getList(condition));

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 品目マスタ検索(ポップアップ)ロジックを設定します。
	 * @param itemSearchLogic 品目マスタ検索(ポップアップ)ロジック
	 */
	public void setItemSearchLogic(final ItemSearchLogic itemSearchLogic) {
		this.itemSearchLogic = itemSearchLogic;
	}
}
