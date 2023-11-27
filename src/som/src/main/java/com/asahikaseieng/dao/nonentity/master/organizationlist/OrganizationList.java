/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OrganizationListクラス.
 * @author t0011036
 */
public class OrganizationList extends OrganizationListBase {

	private static final long serialVersionUID = 1L;

	private String shortOrganizationName;

	/**
	 * コンストラクタ.
	 */
	public OrganizationList() {
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
}
