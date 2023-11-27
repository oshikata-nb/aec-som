/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetaillist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RoleDetailListクラス.
 * @author t0011036
 */
public class RoleDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RoleDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション kbn */
	public static final String kbn_COLUMN = "KBN";

	/** COLUMNアノテーション roleId */
	public static final String roleId_COLUMN = "ROLE_ID";

	/** COLUMNアノテーション roleName */
	public static final String roleName_COLUMN = "ROLE_NAME";

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション id */
	public static final String id_COLUMN = "ID";

	//
	// インスタンスフィールド
	//

	private String kbn;

	private java.math.BigDecimal roleId;

	private String roleName;

	private java.math.BigDecimal menuId;

	private java.math.BigDecimal tabId;

	private java.math.BigDecimal controlId;

	private String id;

	//
	// インスタンスメソッド
	//

	/**
	 * kbn取得.
	 * @return kbn
	 */
	public String getKbn() {
		return this.kbn;
	}

	/**
	 * kbn設定.
	 * @param kbn kbn
	 */
	public void setKbn(final String kbn) {
		this.kbn = kbn;
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
	 * id取得.
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * id設定.
	 * @param id id
	 */
	public void setId(final String id) {
		this.id = id;
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

