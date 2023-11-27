/*
 * Created on 2007/04/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.login;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Loginクラス.
 * @author jbd
 */
public class LoginBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション organizationId */
	public static final String organizationId_COLUMN = "ORGANIZATION_ID";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	/** COLUMNアノテーション password */
	public static final String password_COLUMN = "PASSWORD";

	/** COLUMNアノテーション activeFlg */
	public static final String activeFlg_COLUMN = "ACTIVE_FLG";

	/** COLUMNアノテーション updatePass */
	public static final String updatePass_COLUMN = "UPDATE_PASS";

	/** COLUMNアノテーション adminFlg */
	public static final String adminFlg_COLUMN = "ADMIN_FLG";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private String tantoNm;

	private java.math.BigDecimal organizationId;

	private String organizationName;

	private java.math.BigDecimal postId;

	private String postName;

	private String password;

	private String activeFlg;

	private Timestamp updatePass;

	private String adminFlg;

	private String sectionCd;

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
	 * organizationId取得.
	 * @return organizationId
	 */
	public java.math.BigDecimal getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * organizationId設定.
	 * @param organizationId organizationId
	 */
	public void setOrganizationId(final java.math.BigDecimal organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * postId取得.
	 * @return postId
	 */
	public java.math.BigDecimal getPostId() {
		return this.postId;
	}

	/**
	 * postId設定.
	 * @param postId postId
	 */
	public void setPostId(final java.math.BigDecimal postId) {
		this.postId = postId;
	}

	/**
	 * postName取得.
	 * @return postName
	 */
	public String getPostName() {
		return this.postName;
	}

	/**
	 * postName設定.
	 * @param postName postName
	 */
	public void setPostName(final String postName) {
		this.postName = postName;
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

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
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

