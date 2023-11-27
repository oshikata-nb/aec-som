package com.asahikaseieng.dao.nonentity.master.belonglist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 所属検索条件
 * @author t0011036
 */
public class BelongListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public BelongListPagerCondition() {
	}

	/** 検索条件プロパティ * */

	private String srhOrganizationCd; /* 部署コード */

	private String srhTantoCd; /* 担当者コード */

	private String srhPostId; /* 役職コード */

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
	 * srhTantoCdを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * srhTantoCdを設定します。
	 * @param srhTantoCd srhTantoCd
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}
}
