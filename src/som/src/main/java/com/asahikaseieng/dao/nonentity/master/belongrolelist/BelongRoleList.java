/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * BelongRoleListクラス.
 * @author t0011036
 */
public class BelongRoleList extends BelongRoleListBase {

	private static final long serialVersionUID = 1L;

	private String shortOrganizationName;

	private String shortPostName;

	private String shortRoleName;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用organizationName取得.
	 * @return organizationName
	 */
	public String getDispOrganizationName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getOrganizationName(),
			getShortOrganizationName());
	}

	/**
	 * 表示用postName取得.
	 * @return postName
	 */
	public String getDispPostName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getPostName(), getShortPostName());
	}

	/**
	 * 表示用roleName取得.
	 * @return roleName
	 */
	public String getDispRoleName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getRoleName(), getShortRoleName());
	}

	/**
	 * shortOrganizationNameを取得します。
	 * @return shortOrganizationName
	 */
	public String getShortOrganizationName() {
		return shortOrganizationName;
	}

	/**
	 * shortOrganizationNameを設定します。
	 * @param shortOrganizationName shortOrganizationName
	 */
	public void setShortOrganizationName(final String shortOrganizationName) {
		this.shortOrganizationName = shortOrganizationName;
	}

	/**
	 * shortPostNameを取得します。
	 * @return shortPostName
	 */
	public String getShortPostName() {
		return shortPostName;
	}

	/**
	 * shortPostNameを設定します。
	 * @param shortPostName shortPostName
	 */
	public void setShortPostName(final String shortPostName) {
		this.shortPostName = shortPostName;
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
