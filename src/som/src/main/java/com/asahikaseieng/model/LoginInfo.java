/*
 * Created on Mon Apr 09 09:43:51 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ログイン情報Bean.
 * @author jbd
 */
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginInfo() {
	}

	/* 担当者コード */
	private String tantoCd;

	/* 担当者名称 */
	private String tantoNm;

	/* 役職コード */
	private BigDecimal roleId;

	/* 役職名称 */
	private String roleName;

	/* 部署コード */
	private String organizationCd;

	/* 部署名称 */
	private String organizationName;

	/* ログイン日時 */
	private Timestamp loginDate;

	/* PASSWORD */
	private String password;

	/* 有効フラグ */
	private String activeFlg;

	/* パスワード変更日時 */
	private Timestamp updatePass;

	/* 管理者フラグ */
	private String adminFlg;

	/* 所属コード */
	// private String sectionCd;
	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNm取得.
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return this.tantoNm;
	}

	/**
	 * tantoNm設定.
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * loginDateを取得します。
	 * @return loginDate
	 */
	public Timestamp getLoginDate() {
		return loginDate;
	}

	/**
	 * loginDateを設定します。
	 * @param loginDate loginDate
	 */
	public void setLoginDate(final Timestamp loginDate) {
		this.loginDate = loginDate;
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
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * activeFlg取得.
	 * @return activeFlg
	 */
	public String getActiveFlg() {
		return activeFlg;
	}

	/**
	 * activeFlg設定.
	 * @param activeFlg activeFlg
	 */
	public void setActiveFlg(final String activeFlg) {
		this.activeFlg = activeFlg;
	}

	/**
	 * adminFlg取得.
	 * @return adminFlg
	 */
	public String getAdminFlg() {
		return adminFlg;
	}

	/**
	 * adminFlg設定.
	 * @param adminFlg adminFlg
	 */
	public void setAdminFlg(final String adminFlg) {
		this.adminFlg = adminFlg;
	}

	/**
	 * password取得.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * password設定.
	 * @param password password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * updatePass取得.
	 * @return updatePass
	 */
	public Timestamp getUpdatePass() {
		return updatePass;
	}

	/**
	 * updatePass設定.
	 * @param updatePass updatePass
	 */
	public void setUpdatePass(final Timestamp updatePass) {
		this.updatePass = updatePass;
	}

	// /**
	// * sectionCdを取得します。
	// * @return sectionCd
	// */
	// public String getSectionCd() {
	// return sectionCd;
	// }
	//
	// /**
	// * sectionCdを設定します。
	// * @param sectionCd sectionCd
	// */
	// public void setSectionCd(final String sectionCd) {
	// this.sectionCd = sectionCd;
	// }

	/**
	 * roleIdを取得します。
	 * @return roleId
	 */
	public BigDecimal getRoleId() {
		return roleId;
	}

	/**
	 * roleIdを設定します。
	 * @param roleId roleId
	 */
	public void setRoleId(final BigDecimal roleId) {
		this.roleId = roleId;
	}
}
