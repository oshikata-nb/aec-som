/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 包装指図－在庫確認ポップアップ画面 Actionクラス.
 * @author tosco
 */
public final class PkgDirectionLotInventorySearchAction extends AbstractSearchAction {

	/** 包装指図－在庫確認ポップアップ画面ロジック */
	private PkgDirectionLotInventorySearchLogic pkgDirectionLotInventorySearchLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionLotInventorySearchAction() {
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

		PkgDirectionLotInventorySearchForm frm = (PkgDirectionLotInventorySearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<PkgDirectionLotInventorySearchList>());

		/* 検索条件を取得 */
		PkgDirectionLotInventorySearchListPagerCondition condition
			= (PkgDirectionLotInventorySearchListPagerCondition) frm.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setItemCd(frm.getSrhItemCd());

		/* 明細取得 */
		frm.setSearchList(pkgDirectionLotInventorySearchLogic.getSearchList(condition));

		// 品目名称を設定
		List<PkgDirectionLotInventorySearchList> list = frm.getSearchList();
		if (list != null && (!list.isEmpty())) {
			PkgDirectionLotInventorySearchList bean = list.get(0);
			frm.setSrhItemName(bean.getItemName());
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 在庫確認ポップアップ画面ロジック.
	 * @param pkgDirectionLotInventorySearchLogic 在庫確認ポップアップ画面ロジック
	 */
	public void setPkgDirectionLotInventorySearchLogic
		(final PkgDirectionLotInventorySearchLogic pkgDirectionLotInventorySearchLogic) {
		this.pkgDirectionLotInventorySearchLogic = pkgDirectionLotInventorySearchLogic;
	}
}
