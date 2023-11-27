/*
 * Created on 2009/06/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritylist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ControlAuthorityListクラス.
 * @author t0011036
 */
public class ControlAuthorityListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ControlAuthorityListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId */
	public static final String controlId_COLUMN = "CONTROL_ID";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal roleId;

	private java.math.BigDecimal menuId;

	private java.math.BigDecimal tabId;

	private java.math.BigDecimal controlId;

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
	 * menuId取得.
	 * @return menuId
	 */
	public java.math.BigDecimal getMenuId() {
		return this.menuId;
	}

	/**
	 * menuId設定.
	 * @param menuId menuId
	 */
	public void setMenuId(final java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * tabId取得.
	 * @return tabId
	 */
	public java.math.BigDecimal getTabId() {
		return this.tabId;
	}

	/**
	 * tabId設定.
	 * @param tabId tabId
	 */
	public void setTabId(final java.math.BigDecimal tabId) {
		this.tabId = tabId;
	}

	/**
	 * controlId取得.
	 * @return controlId
	 */
	public java.math.BigDecimal getControlId() {
		return this.controlId;
	}

	/**
	 * controlId設定.
	 * @param controlId controlId
	 */
	public void setControlId(final java.math.BigDecimal controlId) {
		this.controlId = controlId;
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

