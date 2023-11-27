/*
 * Created on Wed Feb 04 17:44:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.role;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RoleBaseクラス.
 * @author t0011036
 */
public class RoleBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RoleBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ROLE";


//	/** IDアノテーション roleId. */
//	public static final String roleId_ID = "assigned";

	/** COLUMNアノテーション roleId. */
	public static final String roleId_COLUMN = "ROLE_ID";

	/** COLUMNアノテーション roleName. */
	public static final String roleName_COLUMN = "ROLE_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal roleId;

	private String roleName;

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
