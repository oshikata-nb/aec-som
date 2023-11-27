/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlogin;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * LoginLoginクラス.
 * @author t0011036
 */
public class LoginLoginBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginLoginBase() {
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

	/** COLUMNアノテーション updatePass */
	public static final String updatePass_COLUMN = "UPDATE_PASS";

	/** COLUMNアノテーション adminFlg */
	public static final String adminFlg_COLUMN = "ADMIN_FLG";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	//
	// インスタンスフィールド
	//

	private String tantoCd;

	private String tantoNm;

	private String password;

	private String activeFlg;

	private java.sql.Timestamp updatePass;

	private String adminFlg;

	private String organizationCd;

	private String organizationName;

	private java.math.BigDecimal postId;

	private String postName;

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
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
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

	/**
	 * adminFlgを取得します。
	 * @return adminFlg
	 */
	public String getAdminFlg() {
		return adminFlg;
	}

	/**
	 * adminFlgを設定します。
	 * @param adminFlg adminFlg
	 */
	public void setAdminFlg(final String adminFlg) {
		this.adminFlg = adminFlg;
	}
}
