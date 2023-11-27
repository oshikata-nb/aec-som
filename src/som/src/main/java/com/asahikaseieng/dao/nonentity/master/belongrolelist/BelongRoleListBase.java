/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BelongRoleListクラス.
 * @author t0011036
 */
public class BelongRoleListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション shortOrganizationName */
	public static final String shortOrganizationName_COLUMN = "SHORT_ORGANIZATION_NAME";

	/** COLUMNアノテーション postId */
	public static final String postId_COLUMN = "POST_ID";

	/** COLUMNアノテーション postName */
	public static final String postName_COLUMN = "POST_NAME";

	/** COLUMNアノテーション shortPostName */
	public static final String shortPostName_COLUMN = "SHORT_POST_NAME";

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	/** COLUMNアノテーション roleName */
	public static final String roleName_COLUMN = "ROLE_NAME";

	/** COLUMNアノテーション shortRoleName */
	public static final String shortRoleName_COLUMN = "SHORT_ROLE_NAME";

	//
	// インスタンスフィールド
	//

	private String organizationCd;

	private String organizationName;

	private String shortOrganizationName;

	private java.math.BigDecimal postId;

	private String postName;

	private String shortPostName;

	private java.math.BigDecimal roleId;

	private String roleName;

	private String shortRoleName;

	//
	// インスタンスメソッド
	//

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
	 * shortOrganizationName取得.
	 * @return shortOrganizationName
	 */
	public String getShortOrganizationName() {
		return this.shortOrganizationName;
	}

	/**
	 * shortOrganizationName設定.
	 * @param shortOrganizationName shortOrganizationName
	 */
	public void setShortOrganizationName(final String shortOrganizationName) {
		this.shortOrganizationName = shortOrganizationName;
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
	 * shortPostName取得.
	 * @return shortPostName
	 */
	public String getShortPostName() {
		return this.shortPostName;
	}

	/**
	 * shortPostName設定.
	 * @param shortPostName shortPostName
	 */
	public void setShortPostName(final String shortPostName) {
		this.shortPostName = shortPostName;
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
	 * roleName取得.
	 * @return roleName
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * roleName設定.
	 * @param roleName roleName
	 */
	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * shortRoleName取得.
	 * @return shortRoleName
	 */
	public String getShortRoleName() {
		return this.shortRoleName;
	}

	/**
	 * shortRoleName設定.
	 * @param shortRoleName shortRoleName
	 */
	public void setShortRoleName(final String shortRoleName) {
		this.shortRoleName = shortRoleName;
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

