/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;
import com.asahikaseieng.struts.AbstractForm;

/**
 * ロール詳細 Formクラス.
 * @author t0011036
 */
public final class RoleDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* ロールID */
	private String roleId;

	/* ロール名称 */
	private String roleName;

	/* 選択メニューリスト */
	private String chkMenuList;

	/** チェックメニューリスト */
	private List<String[]> menuList;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/**
	 * コンストラクタ.
	 */
	public RoleDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);

			/* チェックされたメニューID設定 */
			setCheckIdList(request);

			/* メニュー選択チェック */
			checkChkMenuList(request, errors);
		}

		return errors;
	}

	/**
	 * 画面上でチェックされたメニューID(メニュー＋タブ＋操作)をMAPに設定して
	 * セッションに格納する（validateでエラー発生時にチェックを復元するため）
	 * @param request HttpServletRequest
	 */
	private void setCheckIdList(final HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();

		RoleDetailMenuForm form = (RoleDetailMenuForm) (request
				.getSession(false).getAttribute(Constants.MENU_SET_KEY));

		if (form != null) {
			if (StringUtils.isNotEmpty(chkMenuList)) {
				/* チェックしたメニューリスト格納 */
				String[] menuStrings = chkMenuList.split("@");

				for (int i = 0; i < menuStrings.length; i++) {
					String id = menuStrings[i].substring(1); /* 先頭の区分を除去 */
					map.put(id, id);
				}

				form.setMap(map);
			} else {
				form.setMap(map);
			}

			request.getSession(false)
					.setAttribute(Constants.MENU_SET_KEY, form);
		}
	}

	/**
	 * ロールに紐づくメニュー選択チェック
	 * @param request HttpServletRequest
	 * @param errors エラーリスト
	 */
	private void checkChkMenuList(final HttpServletRequest request,
			final ActionErrors errors) {
		/* メニューリストサイズチェック */
		if (StringUtils.isEmpty(chkMenuList)) {
			errors.add("roleDetailList",
				new ActionMessage("errors.role.notdel"));
		}
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setRoleId(null);
		setRoleName(null);
		setChkMenuList(null);
		setMenuList(null);
		setDirtyFlg(null);
		setNewFlg(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * roleNameを取得します。
	 * @return roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * roleNameを設定します。
	 * @param roleName roleName
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * roleIdを取得します。
	 * @return roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * roleIdを設定します。
	 * @param roleId roleId
	 */
	public void setRoleId(final String roleId) {
		this.roleId = roleId;
	}

	/**
	 * chkMenuListを取得します。
	 * @return chkMenuList
	 */
	public String getChkMenuList() {
		return chkMenuList;
	}

	/**
	 * chkMenuListを設定します。
	 * @param chkMenuList chkMenuList
	 */
	public void setChkMenuList(final String chkMenuList) {
		this.chkMenuList = chkMenuList;
	}

	/**
	 * menuListを取得します。
	 * @return menuList
	 */
	public List<String[]> getMenuList() {
		return menuList;
	}

	/**
	 * menuListを設定します。
	 * @param menuList menuList
	 */
	public void setMenuList(final List<String[]> menuList) {
		this.menuList = menuList;
	}
}
