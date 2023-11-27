/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.struts.AbstractSearchAction;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 包装指図－製造指図明細ポップアップ画面 Actionクラス.
 * @author tosco
 */
public final class PkgDirectionDirectionDetailSearchAction extends AbstractSearchAction {

	/** 製造指図詳細ポップアップ画面ロジック */
	private PkgDirectionDirectionDetailSearchLogic pkgDirectionDirectionDetailSearchLogic;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionDirectionDetailSearchAction() {
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
	 * 製造指図詳細検索処理
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

		PkgDirectionDirectionDetailSearchForm frm = (PkgDirectionDirectionDetailSearchForm) form;

		/* 検索に失敗しても明細が消されるように、ここでクリア */
		frm.setSearchList(new ArrayList<PkgDirectionDirectionDetailSearchList>());

		/* 検索条件を取得 */
		PkgDirectionDirectionDetailSearchListPagerCondition condition
			= (PkgDirectionDirectionDetailSearchListPagerCondition) frm.getPager().getPagerCondition();

		/* 検索条件をセット */
		condition.setDirectionDivision(new BigDecimal(frm.getDirectionDivision()));
		condition.setPkgDirectionNo(frm.getPkgDirectionNo());

		/* 明細取得 */
		frm.setSearchList(pkgDirectionDirectionDetailSearchLogic.getSearchList(condition));

		// 品目名称を設定
		List<PkgDirectionDirectionDetailSearchList> list = frm.getSearchList();
		if (list != null && list.size() > 0) {
			PkgDirectionDirectionDetailSearchList bean = list.get(0);
			frm.setItemName(bean.getPkgItemName());
		}

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造指図詳細ポップアップ画面ロジック設定.
	 * @param pkgDirectionDirectionDetailSearchLogic 製造指図詳細ポップアップ画面ロジック
	 */
	public void setPkgDirectionDirectionDetailSearchLogic
		(final PkgDirectionDirectionDetailSearchLogic pkgDirectionDirectionDetailSearchLogic) {
		this.pkgDirectionDirectionDetailSearchLogic = pkgDirectionDirectionDetailSearchLogic;
	}
}
