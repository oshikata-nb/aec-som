/*
 * Created on Fri Apr 06 14:14:43 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.menutype;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * MenuTypeBaseクラス.
 * @author jbd
 */
public class MenuTypeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MenuTypeBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MENU_TYPE";


	/** IDアノテーション menuTypeId. */
	public static final String menuTypeId_ID = "assigned";

	/** COLUMNアノテーション menuTypeId. */
	public static final String menuTypeId_COLUMN = "MENU_TYPE_ID";

	/** COLUMNアノテーション menuTypeName. */
	public static final String menuTypeName_COLUMN = "MENU_TYPE_NAME";

	/** COLUMNアノテーション imgName. */
	public static final String imgName_COLUMN = "IMG_NAME";

	/** COLUMNアノテーション openImgName. */
	public static final String openImgName_COLUMN = "OPEN_IMG_NAME";

	/** COLUMNアノテーション closeImgName. */
	public static final String closeImgName_COLUMN = "CLOSE_IMG_NAME";

	/** COLUMNアノテーション fileKbn. */
	public static final String fileKbn_COLUMN = "FILE_KBN";

	/** COLUMNアノテーション updatedAt. */
	public static final String updatedAt_COLUMN = "UPDATED_AT";

	/** COLUMNアノテーション registedAt. */
	public static final String registedAt_COLUMN = "REGISTED_AT";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal menuTypeId;

	private String menuTypeName;

	private String imgName;

	private String openImgName;

	private String closeImgName;

	private String fileKbn;

	private java.sql.Timestamp updatedAt;

	private java.sql.Timestamp registedAt;

	//
	// インスタンスメソッド
	//

	/**
	 * menuTypeId取得.
	 * @return menuTypeId
	 */
	public java.math.BigDecimal getMenuTypeId() {
		return this.menuTypeId;
	}

	/**
	 * menuTypeId設定.
	 * @param menuTypeId menuTypeId
	 */
	public void setMenuTypeId(final java.math.BigDecimal menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

	/**
	 * menuTypeName取得.
	 * @return menuTypeName
	 */
	public String getMenuTypeName() {
		return this.menuTypeName;
	}

	/**
	 * menuTypeName設定.
	 * @param menuTypeName menuTypeName
	 */
	public void setMenuTypeName(final String menuTypeName) {
		this.menuTypeName = menuTypeName;
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
	 * updatedAt取得.
	 * @return updatedAt
	 */
	public java.sql.Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * updatedAt設定.
	 * @param updatedAt updatedAt
	 */
	public void setUpdatedAt(final java.sql.Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * registedAt取得.
	 * @return registedAt
	 */
	public java.sql.Timestamp getRegistedAt() {
		return this.registedAt;
	}

	/**
	 * registedAt設定.
	 * @param registedAt registedAt
	 */
	public void setRegistedAt(final java.sql.Timestamp registedAt) {
		this.registedAt = registedAt;
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
