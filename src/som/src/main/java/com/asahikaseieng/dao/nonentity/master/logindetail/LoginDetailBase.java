/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.logindetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LoginDetailクラス.
 * @author t0011036
 */
public class LoginDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション password */
	public static final String password_COLUMN = "PASSWORD";

	/** COLUMNアノテーション activeFlg */
	public static final String activeFlg_COLUMN = "ACTIVE_FLG";

	/** COLUMNアノテーション deleteFlg */
	public static final String deleteFlg_COLUMN = "DELETE_FLG";

	/** COLUMNアノテーション adminFlg */
	public static final String adminFlg_COLUMN = "ADMIN_FLG";

	/** COLUMNアノテーション updatePass */
	public static final String updatePass_COLUMN = "UPDATE_PASS";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private String tantoNm;

	private String password;

	private String activeFlg;

	private String deleteFlg;

	private String adminFlg;

	private java.sql.Timestamp updatePass;

	private java.sql.Timestamp updateDate;

	private java.math.BigDecimal roleId;

	//
	// インスタンスメソッド
	//

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
	 * password取得.
	 * @return password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * password設定.
	 * @param password password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * activeFlg取得.
	 * @return activeFlg
	 */
	public String getActiveFlg() {
		return this.activeFlg;
	}

	/**
	 * activeFlg設定.
	 * @param activeFlg activeFlg
	 */
	public void setActiveFlg(final String activeFlg) {
		this.activeFlg = activeFlg;
	}

	/**
	 * deleteFlg取得.
	 * @return deleteFlg
	 */
	public String getDeleteFlg() {
		return this.deleteFlg;
	}

	/**
	 * deleteFlg設定.
	 * @param deleteFlg deleteFlg
	 */
	public void setDeleteFlg(final String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * adminFlg取得.
	 * @return adminFlg
	 */
	public String getAdminFlg() {
		return this.adminFlg;
	}

	/**
	 * adminFlg設定.
	 * @param adminFlg adminFlg
	 */
	public void setAdminFlg(final String adminFlg) {
		this.adminFlg = adminFlg;
	}

	/**
	 * updatePass取得.
	 * @return updatePass
	 */
	public java.sql.Timestamp getUpdatePass() {
		return this.updatePass;
	}

	/**
	 * updatePass設定.
	 * @param updatePass updatePass
	 */
	public void setUpdatePass(final java.sql.Timestamp updatePass) {
		this.updatePass = updatePass;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * roleId取得.
	 * @return roleId
	 */
	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	/**
	 * roleId設定.
	 * @param roleId roleId
	 */
	public void setRoleId(final java.math.BigDecimal roleId) {
		this.roleId = roleId;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

