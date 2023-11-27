/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 部署検索条件
 * @author t0011036
 */
public class OrganizationListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrganizationListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhOrganizationCd; /* 部署コード */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return this.srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd organizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}
}
