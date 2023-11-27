/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlist;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * LoginListPagerConditionクラス.
 * @author t0011036
 */
public class LoginListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginListPagerCondition() {
	}

	//
	// 検索入力用.tanto
	//

	/* 検索入力：組織コード */
	private String srhOrganizationCd;

	/* 検索入力：担当者コード */
	private String srhTantoCd;

	/* 検索入力：役職コード */
	private String srhPostId;

	/* ログイン担当者コード */
	private String srhLoginTantoCd;

	/* ログイン管理者区分 */
	private String srhLoginAdminFlg;

	/* 所属区分 */
	private String srhNoBelong;

	//
	// 検索入力.tanto
	//

	/**
	 * srhLoginAdminFlgを取得します。
	 * @return srhLoginAdminFlg
	 */
	public String getSrhLoginAdminFlg() {
		return srhLoginAdminFlg;
	}

	/**
	 * srhLoginAdminFlgを設定します。
	 * @param srhLoginAdminFlg srhLoginAdminFlg
	 */
	public void setSrhLoginAdminFlg(final String srhLoginAdminFlg) {
		this.srhLoginAdminFlg = srhLoginAdminFlg;
	}

	/**
	 * srhLoginTantoCdを取得します。
	 * @return srhLoginTantoCd
	 */
	public String getSrhLoginTantoCd() {
		return srhLoginTantoCd;
	}

	/**
	 * srhLoginTantoCdを設定します。
	 * @param srhLoginTantoCd srhLoginTantoCd
	 */
	public void setSrhLoginTantoCd(final String srhLoginTantoCd) {
		this.srhLoginTantoCd = srhLoginTantoCd;
	}

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

	/**
	 * srhNoBelongを取得します。
	 * @return srhNoBelong
	 */
	public String getSrhNoBelong() {
		return srhNoBelong;
	}

	/**
	 * srhNoBelongを設定します。
	 * @param srhNoBelong srhNoBelong
	 */
	public void setSrhNoBelong(final String srhNoBelong) {
		this.srhNoBelong = srhNoBelong;
	}
}
