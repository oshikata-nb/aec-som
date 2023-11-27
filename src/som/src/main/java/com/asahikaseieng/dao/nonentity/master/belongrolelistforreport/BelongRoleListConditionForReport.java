package com.asahikaseieng.dao.nonentity.master.belongrolelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 所属・ロール組合せ検索条件
 * @author t0011036
 */
public class BelongRoleListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BelongRoleListConditionForReport() {
	}

	/** 検索条件プロパティ * */

	private String srhOrganizationCd; /* 部署コード */

	private String srhPostId; /* 役職コード */

	private String srhRoleId; /* ロールID */

	/** 検索条件プロパティのGetter,Settetメソッド * */

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * srhPostIdを取得します。
	 * @return srhPostId
	 */
	public String getSrhPostId() {
		return srhPostId;
	}

	/**
	 * srhPostIdを設定します。
	 * @param srhPostId srhPostId
	 */
	public void setSrhPostId(final String srhPostId) {
		this.srhPostId = AecTextUtils.likeFilter(srhPostId);
	}

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
