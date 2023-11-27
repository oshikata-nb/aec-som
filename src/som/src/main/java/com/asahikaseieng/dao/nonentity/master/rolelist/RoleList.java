/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * RoleListクラス.
 * @author t0011036
 */
public class RoleList extends RoleListBase {

	private static final long serialVersionUID = 1L;

	private String shortRoleName;

	/**
	 * コンストラクタ.
	 */
	public RoleList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用roleName取得.
	 * @return roleName
	 */
	public String getDispRoleName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getRoleName(), getShortRoleName());
	}

	/**
	 * shortRoleNameを取得します。
	 * @return shortRoleName
	 */
	public String getShortRoleName() {
		return shortRoleName;
	}

	/**
	 * shortRoleNameを設定します。
	 * @param shortRoleName shortRoleName
	 */
	public void setShortRoleName(final String shortRoleName) {
		this.shortRoleName = shortRoleName;
	}
}
