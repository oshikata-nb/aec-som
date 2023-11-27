/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.menu;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.assistance.authority.AuthorityLogic;
import com.asahikaseieng.struts.AbstractAction;

/**
 * メニュー Actionクラス.
 * @author jbd
 */
public class MenuAction extends AbstractAction {

	/* 権限のアシスタントロジック */
	private AuthorityLogic authorityLogic;

	private MenuLogic menuLogic;

	/**
	 * コンストラクタ.
	 */
	public MenuAction() {
	}

	/**
	 * menuLogicを設定します。
	 * @param menuLogic menuLogic
	 */
	public void setMenuLogic(final MenuLogic menuLogic) {
		this.menuLogic = menuLogic;
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping map, final ActionForm form,
			final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		MenuForm frm = (MenuForm) form;

		/* ユーザー情報をセッションから取り出す */
		String tantoCd = getLoginInfo(request).getTantoCd();
		String organizationCd = getLoginInfo(request).getOrganizationCd();
		BigDecimal postId = getLoginInfo(request).getRoleId();

		/* 担当者・所属に紐づくロールを取得 */
		String[] tantoRoleIds = authorityLogic.getTantoRoleList(tantoCd,
			organizationCd, postId);

		String[] belongRoleIds = authorityLogic.getBelongRoleList(tantoCd,
			organizationCd, postId);

		// メニューSetを取得

		frm.setMenus(menuLogic.getMenus(tantoRoleIds, belongRoleIds));

		// 後で権限確認に利用する為に、メニューSetをセッションに格納

		request.getSession(false).setAttribute(Constants.MENU_KEY,
			frm.getMenus());

		return super.init(map, form, request, response);
	}

	/**
	 * authorityLogicを設定します。
	 * 
	 * @param authorityLogic AuthorityLogic
	 */
	public void setAuthorityLogic(final AuthorityLogic authorityLogic) {
		this.authorityLogic = authorityLogic;
	}

}
