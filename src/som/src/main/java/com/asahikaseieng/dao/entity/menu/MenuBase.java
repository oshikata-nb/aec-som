/*
 * Created on Fri Apr 06 14:14:28 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menu;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * MenuBaseクラス.
 * @author jbd
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

	/** COLUMNアノテーション menuId. */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション menuTypeId. */
	public static final String menuTypeId_COLUMN = "MENU_TYPE_ID";

	/** COLUMNアノテーション parentMenuId. */
	public static final String parentMenuId_COLUMN = "PARENT_MENU_ID";

	/** COLUMNアノテーション menuName. */
	public static final String menuName_COLUMN = "MENU_NAME";

	/** COLUMNアノテーション action. */
	public static final String action_COLUMN = "ACTION";

	/** COLUMNアノテーション sortNo. */
	public static final String sortNo_COLUMN = "SORT_NO";

	/** COLUMNアノテーション fileKbn. */
	public static final String fileKbn_COLUMN = "FILE_KBN";

	/** COLUMNアノテーション imgName. */
	public static final String imgName_COLUMN = "IMG_NAME";

	/** COLUMNアノテーション openImgName. */
	public static final String openImgName_COLUMN = "OPEN_IMG_NAME";

	/** COLUMNアノテーション closeImgName. */
	public static final String closeImgName_COLUMN = "CLOSE_IMG_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal menuId;

	private String menuTypeId;

	private java.math.BigDecimal parentMenuId;

	private String menuName;

	private String action;

	private java.math.BigDecimal sortNo;

	private String fileKbn;

	private String imgName;

	private String openImgName;

	private String closeImgName;

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
	 * menuTypeId取得.
	 * @return menuTypeId
	 */
	public String getMenuTypeId() {
		return this.menuTypeId;
	}

	/**
	 * menuTypeId設定.
	 * @param menuTypeId menuTypeId
	 */
	public void setMenuTypeId(final String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	/**
	 * parentMenuId取得.
	 * @return parentMenuId
	 */
	public java.math.BigDecimal getParentMenuId() {
		return this.parentMenuId;
	}

	/**
	 * parentMenuId設定.
	 * @param parentMenuId parentMenuId
	 */
	public void setParentMenuId(final java.math.BigDecimal parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	/**
	 * menuName取得.
	 * @return menuName
	 */
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * menuName設定.
	 * @param menuName menuName
	 */
	public void setMenuName(final String menuName) {
		this.menuName = menuName;
	}

	/**
	 * action取得.
	 * @return action
	 */
	public String getAction() {
		return this.action;
	}

	/**
	 * action設定.
	 * @param action action
	 */
	public void setAction(final String action) {
		this.action = action;
	}

	/**
	 * sortNo取得.
	 * @return sortNo
	 */
	public java.math.BigDecimal getSortNo() {
		return this.sortNo;
	}

	/**
	 * sortNo設定.
	 * @param sortNo sortNo
	 */
	public void setSortNo(final java.math.BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * ファイル区分取得.
	 * @return String ファイル区分	 */
	public String getFileKbn() {
		return fileKbn;
	}

	/**
	 * ファイル区分設定.
	 * @param fileKbn ファイル区分	 */
	public void setFileKbn(final String fileKbn) {
		this.fileKbn = fileKbn;
	}

	/**
	 * 画像名取得.
	 * @return String 画像名
	 */
	public String getImgName() {
		return imgName;
	}

	/**
	 * 画像名設定.
	 * @param imgName 画像名
	 */
	public void setImgName(final String imgName) {
		this.imgName = imgName;
	}

	/**
	 * OPEN画像名取得.
	 * @return String OPEN画像名
	 */
	public String getOpenImgName() {
		return openImgName;
	}

	/**
	 * OPEN画像名設定.
	 * @param openImgName OPEN画像名
	 */
	public void setOpenImgName(final String openImgName) {
		this.openImgName = openImgName;
	}

	/**
	 * CLOSE画像名取得.
	 * @return String CLOSE画像名
	 */
	public String getCloseImgName() {
		return closeImgName;
	}

	/**
	 * CLOSE画像名設定.
	 * @param closeImgName CLOSE画像名
	 */
	public void setCloseImgName(final String closeImgName) {
		this.closeImgName = closeImgName;
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
