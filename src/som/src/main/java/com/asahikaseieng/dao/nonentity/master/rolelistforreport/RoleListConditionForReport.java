/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * ロール検索条件
 * @author t0011036
 */
public class RoleListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RoleListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhRoleId; /* ロールID */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhRoleIdを取得します。
	 * @return srhRoleId
	 */
	public String getSrhRoleId() {
		return srhRoleId;
	}

	/**
	 * srhRoleIdを設定します。
	 * @param srhRoleId srhRoleId
	 */
	public void setSrhRoleId(final String srhRoleId) {
		this.srhRoleId = AecTextUtils.likeFilter(srhRoleId);
	}
}
