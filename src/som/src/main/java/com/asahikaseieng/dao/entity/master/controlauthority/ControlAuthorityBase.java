/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Fri Mar 27 09:29:10 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.controlauthority;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ControlAuthorityBaseクラス.
 * @author t0011036
 */
public class ControlAuthorityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ControlAuthorityBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CONTROL_AUTHORITY";


//	/** IDアノテーション controlId. */
//	public static final String controlId_ID = "assigned";
//	/** IDアノテーション menuId. */
//	public static final String menuId_ID = "assigned";
//	/** IDアノテーション roleId. */
//	public static final String roleId_ID = "assigned";
//	/** IDアノテーション tabId. */
//	public static final String tabId_ID = "assigned";

	/** COLUMNアノテーション menuId. */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId. */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId. */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション roleId. */
	public static final String roleId_COLUMN = "ROLE_ID";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal menuId;

	private java.math.BigDecimal tabId;

	private java.math.BigDecimal controlId;

	private java.math.BigDecimal roleId;

	//
	// インスタンスメソッド
	//

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
