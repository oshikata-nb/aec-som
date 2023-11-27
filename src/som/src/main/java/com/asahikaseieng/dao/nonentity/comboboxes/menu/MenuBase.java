/*
 * Created on 2008/04/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.menu;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * メニューIDとメニュー名称を保持するBeanクラス.
 * @author tosco
 */
public class MenuBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MenuBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MENU_HEAD";

	/** COLUMNアノテーション メニューID */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション メニュー名称 */
	public static final String menuName_COLUMN = "MENU_NAME";

	//
	// インスタンスフィールド
	//

	//メニューID
	private BigDecimal menuId;

	//メニュー名称
	private String menuName;

	//
	// インスタンスメソッド
	//

	/**
	 * メニューID取得.
	 * @return BigDecimal メニューID
	 */
	public BigDecimal getMenuId() {
		return menuId;
	}

	/**
	 * メニューID設定.
	 * @param menuId メニューID
	 */
	public void setMenuId(final BigDecimal menuId) {
		this.menuId = menuId;
	}

	/**
	 * メニュー名称取得.
	 * @return String メニュー名称
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * メニュー名称設定.
	 * @param menuName メニュー名称
	 */
	public void setMenuName(final String menuName) {
		this.menuName = menuName;
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

