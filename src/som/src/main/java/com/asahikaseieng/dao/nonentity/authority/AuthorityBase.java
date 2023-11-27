/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.authority;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Authorityクラス.
 * @author jbd
 */
public class AuthorityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AuthorityBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション tabId */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション controlId */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション gadgetId */
	public static final String gadgetId_COLUMN = "GADGET_ID";

	/** COLUMNアノテーション title */
	public static final String title_COLUMN = "TITLE";

	//
	// インスタンスフィールド	//

	private BigDecimal menuId;

	private BigDecimal tabId;

	private BigDecimal controlId;

	private String gadgetId;

	private String title;

	//
	// インスタンスメソッド
	//

	/**
	 * menuId取得.
	 * @return menuId
	 */
	public BigDecimal getMenuId() {
		return this.menuId;
	}

	/**
	 * menuId設定.
	 * @param menuId menuId
	 */
	public void setMenuId(final BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * タブID取得.
	 * @return BigDecimal タブID
	 */
	public BigDecimal getTabId() {
		return tabId;
	}

	/**
	 * タブID設定.
	 * @param tabId タブID
	 */
	public void setTabId(final BigDecimal tabId) {
		this.tabId = tabId;
	}

	/**
	 * 操作ID取得.
	 * @return BigDecimal 操作ID
	 */
	public BigDecimal getControlId() {
		return controlId;
	}

	/**
	 * 操作ID設定.
	 * @param controlId 操作ID
	 */
	public void setControlId(final BigDecimal controlId) {
		this.controlId = controlId;
	}

	/**
	 * ガジェットID取得.
	 * @return String ガジェットID
	 */
	public String getGadgetId() {
		return gadgetId;
	}

	/**
	 * ガジェットID設定.
	 * @param gadgetId ガジェットID
	 */
	public void setGadgetId(final String gadgetId) {
		this.gadgetId = gadgetId;
	}

	/**
	 * タイトル取得.
	 * @return String タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトル設定.
	 * @param title タイトル
	 */
	public void setTitle(final String title) {
		this.title = title;
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

