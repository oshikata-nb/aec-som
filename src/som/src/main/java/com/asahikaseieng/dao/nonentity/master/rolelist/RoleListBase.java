/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RoleListクラス.
 * @author t0011036
 */
public class RoleListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RoleListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	/** COLUMNアノテーション roleName */
	public static final String roleName_COLUMN = "ROLE_NAME";

	/** COLUMNアノテーション shortRoleName */
	public static final String shortRoleName_COLUMN = "SHORT_ROLE_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal roleId;

	private String roleName;

	private String shortRoleName;

	//
	// インスタンスメソッド
	//

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

