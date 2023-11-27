/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolemenulist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RoleMenuListクラス.
 * @author t0011036
 */
public class RoleMenuListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RoleMenuListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション kbn */
	public static final String kbn_COLUMN = "KBN";

	/** COLUMNアノテーション menuId */
	public static final String menuId_COLUMN = "MENU_ID";

	/** COLUMNアノテーション menuName */
	public static final String menuName_COLUMN = "MENU_NAME";

	/** COLUMNアノテーション parentMenuId */
	public static final String parentMenuId_COLUMN = "PARENT_MENU_ID";

	/** COLUMNアノテーション menuTypeId */
	public static final String menuTypeId_COLUMN = "MENU_TYPE_ID";

	/** COLUMNアノテーション sortNo */
	public static final String sortNo_COLUMN = "SORT_NO";

	/** COLUMNアノテーション tabId */
	public static final String tabId_COLUMN = "TAB_ID";

	/** COLUMNアノテーション tabName */
	public static final String tabName_COLUMN = "TAB_NAME";

	/** COLUMNアノテーション controlId */
	public static final String controlId_COLUMN = "CONTROL_ID";

	/** COLUMNアノテーション controlName */
	public static final String controlName_COLUMN = "CONTROL_NAME";

	/** COLUMNアノテーション fileKbn */
	public static final String fileKbn_COLUMN = "FILE_KBN";

	/** COLUMNアノテーション imgName */
	public static final String imgName_COLUMN = "IMG_NAME";

	/** COLUMNアノテーション openImgName */
	public static final String openImgName_COLUMN = "OPEN_IMG_NAME";

	/** COLUMNアノテーション closeImgName */
	public static final String closeImgName_COLUMN = "CLOSE_IMG_NAME";

	//
	// インスタンスフィールド
	//

	private String kbn;

	private java.math.BigDecimal menuId;

	private String menuName;

	private java.math.BigDecimal parentMenuId;

	private String menuTypeId;

	private java.math.BigDecimal sortNo;

	private java.math.BigDecimal tabId;

	private String tabName;

	private java.math.BigDecimal controlId;

	private String controlName;

	private String fileKbn;

	private String imgName;

	private String openImgName;

	private String closeImgName;

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
	 * tabName取得.
	 * @return tabName
	 */
	public String getTabName() {
		return this.tabName;
	}

	/**
	 * tabName設定.
	 * @param tabName tabName
	 */
	public void setTabName(final String tabName) {
		this.tabName = tabName;
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
	 * fileKbn取得.
	 * @return fileKbn
	 */
	public String getFileKbn() {
		return this.fileKbn;
	}

	/**
	 * fileKbn設定.
	 * @param fileKbn fileKbn
	 */
	public void setFileKbn(final String fileKbn) {
		this.fileKbn = fileKbn;
	}

	/**
	 * imgName取得.
	 * @return imgName
	 */
	public String getImgName() {
		return this.imgName;
	}

	/**
	 * imgName設定.
	 * @param imgName imgName
	 */
	public void setImgName(final String imgName) {
		this.imgName = imgName;
	}

	/**
	 * openImgName取得.
	 * @return openImgName
	 */
	public String getOpenImgName() {
		return this.openImgName;
	}

	/**
	 * openImgName設定.
	 * @param openImgName openImgName
	 */
	public void setOpenImgName(final String openImgName) {
		this.openImgName = openImgName;
	}

	/**
	 * closeImgName取得.
	 * @return closeImgName
	 */
	public String getCloseImgName() {
		return this.closeImgName;
	}

	/**
	 * closeImgName設定.
	 * @param closeImgName closeImgName
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

