/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginrolelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LoginRoleListクラス.
 * @author t0011036
 */
public class LoginRoleListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LoginRoleListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal roleId;

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

