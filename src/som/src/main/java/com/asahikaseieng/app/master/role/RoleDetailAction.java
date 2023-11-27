/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.entity.master.role.Role;
import com.asahikaseieng.dao.nonentity.master.roledetaillist.RoleDetailList;
import com.asahikaseieng.struts.AbstractAction;

/**
 * ロール詳細 Actionクラス.
 * @author t0011036
 */
public final class RoleDetailAction extends AbstractAction {

	private RoleDetailLogic roleDetailLogic;

	private RoleMenuLogic menuLogic;

	/**
	 * コンストラクタ.
	 */
	public RoleDetailAction() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("init.");
		}

		RoleDetailForm frm = (RoleDetailForm) form;

		if (getLoginInfo(request).getAdminFlg().equals("2")) {
			frm.setDeleteAuthority(true);
			frm.setUpdateAuthority(true);
		} else {
			/* 権限取得 */
			getControlAuthority(request, frm, Constants.MENU_ID_ROLE,
				Constants.TAB_ID_ROLE_DETAIL);

			if (!frm.getViewAuthority()) {
				/* エラーメッセージ */
				saveError(request, "errors.not.view.authority");
				return mapping.findForward("back");
			}
		}

		/* メニュー階層を取得 */
		RoleDetailMenuForm menuForm = new RoleDetailMenuForm();
		menuForm.setMenus(menuLogic
				.getMenus(getLoginInfo(request).getTantoCd()));

		/* 初期検索 */
		List<RoleDetailList> list = roleDetailLogic.getList(frm.getRoleId());

		frm.setRoleId(list.get(0).getRoleId().toString());
		frm.setRoleName(list.get(0).getRoleName());

		/* メニューID＋タブID＋操作IDのマップ設定 */
		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < list.size(); i++) {
			RoleDetailList detail = list.get(i);
			map.put(detail.getId(), detail.getId());
		}

		menuForm.setMap(map);

		/* 画面表示のTREE用にメニュー階層とメニューリストマップをセッションに格納 */
		request.getSession(false)
				.setAttribute(Constants.MENU_SET_KEY, menuForm);

		return mapping.findForward("success");
	}

	/**
	 * 登録処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward update(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("update");
		}

		RoleDetailForm frm = (RoleDetailForm) form;

		Role bean = roleDetailLogic.getEntity(new BigDecimal(frm.getRoleId()));

		if (bean == null) {
			/* 追加処理を実行 */
			roleDetailLogic.insert(frm);
		} else {
			if (frm.getNewFlg().equals("true")) {
				/* メッセージ */
				saveError(request, "errors.duplicate.data");

				return mapping.getInputForward();
			}

			/* 更新処理を実行 */
			roleDetailLogic.update(frm);
		}

		/* メッセージ */
		saveMessage(request, "message.complete.regist");

		return mapping.findForward("back");
	}

	/**
	 * 削除処理処理.
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("delete");
		}

		RoleDetailForm frm = (RoleDetailForm) form;

		/* 削除処理を実行 */
		roleDetailLogic.delete(frm);

		/* メッセージ */
		saveMessage(request, "message.complete.delete");

		return mapping.findForward("back");
	}

	/**
	 * 戻る処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("back.");
		}

		return mapping.findForward("back");
	}

	/**
	 * 新規処理.
	 * @param mapping mapping
	 * @param form form
	 * @param request request
	 * @param response response
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward newPage(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		if (getLog().isDebugEnabled()) {
			getLog().debug("newPage.");
		}

		RoleDetailForm frm = (RoleDetailForm) form;

		if (getLoginInfo(request).getAdminFlg().equals("2")) {
			frm.setDeleteAuthority(true);
			frm.setUpdateAuthority(true);
		} else {
			/* 権限取得 */
			getControlAuthority(request, frm, Constants.MENU_ID_ROLE,
				Constants.TAB_ID_ROLE_DETAIL);

			if (!frm.getViewAuthority()) {
				/* エラーメッセージ */
				saveError(request, "errors.not.view.authority");
				return mapping.findForward("back");
			}
		}

		frm.clear();

		/* ここを通る場合は新規処理 */
		frm.setNewFlg("true");

		/* メニュー階層を取得 */
		RoleDetailMenuForm menuForm = new RoleDetailMenuForm();
		menuForm.setMenus(menuLogic
				.getMenus(getLoginInfo(request).getTantoCd()));

		/* メニューID＋タブID＋操作IDのマップ設定 */
		HashMap<String, String> map = new HashMap<String, String>();
		menuForm.setMap(map);

		/* 画面表示のTREE用にメニュー階層とメニューリストマップ(空)をセッションに格納 */
		request.getSession(false)
				.setAttribute(Constants.MENU_SET_KEY, menuForm);

		return mapping.findForward("success");
	}

	/* -------------------- setter -------------------- */

	/**
	 * roleDetailLogicを設定します。
	 * @param roleDetailLogic roleDetailLogic
	 */
	public void setRoleDetailLogic(final RoleDetailLogic roleDetailLogic) {
		this.roleDetailLogic = roleDetailLogic;
	}

	/**
	 * menuLogicを設定します。
	 * @param menuLogic menuLogic
	 */
	public void setMenuLogic(final RoleMenuLogic menuLogic) {
		this.menuLogic = menuLogic;
	}
}
