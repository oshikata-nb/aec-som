/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 製造実績－ロット検索ポップアップ画面 Actionクラス.
 * @author tosco
 */
public final class RdirectionLotInventorySearchAction extends AbstractSearchAction {

	/** 製造実績－ロット検索ポップアップ画面ロジック */
	private RdirectionLotInventorySearchLogic rdirectionLotInventorySearchLogic;

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventorySearchAction() {
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
	 * 在庫検索処理
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

		RdirectionLotInventorySearchForm frm = (RdirectionLotInventorySearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<RdirectionLotInventorySearchList>());

		/* 検索条件を取得 */
		RdirectionLotInventorySearchListPagerCondition condition
			= (RdirectionLotInventorySearchListPagerCondition) frm.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setItemCd(frm.getSrhItemCd());

		/* 明細取得 */
		frm.setSearchList(rdirectionLotInventorySearchLogic.getSearchList(condition));

		// 品目名称を設定
		List<RdirectionLotInventorySearchList> list = frm.getSearchList();
		if (list != null && (!list.isEmpty())) {
			RdirectionLotInventorySearchList bean = list.get(0);
			frm.setSrhItemName(bean.getItemName());
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * ロット検索ポップアップ画面ロジック.
	 * @param rdirectionLotInventorySearchLogic ロット検索ポップアップ画面ロジック
	 */
	public void setRdirectionLotInventorySearchLogic
		(final RdirectionLotInventorySearchLogic rdirectionLotInventorySearchLogic) {
		this.rdirectionLotInventorySearchLogic = rdirectionLotInventorySearchLogic;
	}
}
