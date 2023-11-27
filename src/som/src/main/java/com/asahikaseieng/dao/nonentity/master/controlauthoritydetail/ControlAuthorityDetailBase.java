/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritydetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 操作権限マスタ登録用Beanクラス.
 * @author t0011036
 */
public class ControlAuthorityDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ControlAuthorityDetailBase() {
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "CONTROL_AUTHORITY";

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション controlName */
	public static final String controlName_COLUMN = "CONTROL_NAME";

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	//
	// インスタンスフィールド
	//

	/** メニューID */
	private java.math.BigDecimal menuId;

	/** タブID */
	private java.math.BigDecimal tabId;

	/** 操作ID */
	private java.math.BigDecimal controlId;

	private String controlName;

	/** ロールID */
	private java.math.BigDecimal roleId;

	//
	// インスタンスメソッド
	//

	/**
	 * メニューID取得.
	 * @return BigDecimal メニューID
	 */
	public java.math.BigDecimal getMenuId() {
		return this.menuId;
	}

	/**
	 * メニューID設定.
	 * @param menuId メニューID
	 */
	public void setMenuId(final java.math.BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * タブID取得.
	 * @return BigDecimal タブID
	 */
	public java.math.BigDecimal getTabId() {
		return this.tabId;
	}

	/**
	 * タブID設定.
	 * @param tabId タブID
	 */
	public void setTabId(final java.math.BigDecimal tabId) {
		this.tabId = tabId;
	}

	/**
	 * 操作ID取得.
	 * @return BigDecimal 操作ID
	 */
	public java.math.BigDecimal getControlId() {
		return this.controlId;
	}

	/**
	 * 操作ID設定.
	 * @param controlId 操作ID
	 */
	public void setControlId(final java.math.BigDecimal controlId) {
		this.controlId = controlId;
	}

	/**
	 * controlName取得.
	 * @return controlName
	 */
	public String getControlName() {
		return this.controlName;
	}

	/**
	 * controlName設定.
	 * @param controlName controlName
	 */
	public void setControlName(final String controlName) {
		this.controlName = controlName;
	}

	/**
	 * ロールID取得.
	 * @return BigDecimal ロールID
	 */
	public java.math.BigDecimal getRoleId() {
		return this.roleId;
	}

	/**
	 * ロールID設定.
	 * @param roleId ロールID
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
