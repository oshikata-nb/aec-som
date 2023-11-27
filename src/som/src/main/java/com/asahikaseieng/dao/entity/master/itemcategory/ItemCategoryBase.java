/*
 * Created on Thu Jan 22 17:18:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemcategory;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemCategoryBaseクラス.
 * @author t0011036
 */
public class ItemCategoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ITEM_CATEGORY";


//	/** IDアノテーション itemCategory. */
//	public static final String itemCategory_ID = "assigned";

	/** COLUMNアノテーション itemCategory. */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション itemCategoryName. */
	public static final String itemCategoryName_COLUMN = "ITEM_CATEGORY_NAME";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String itemCategory;

	private String itemCategoryName;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * itemCategory取得.
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * itemCategory設定.
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * itemCategoryName取得.
	 * @return itemCategoryName
	 */
	public String getItemCategoryName() {
		return this.itemCategoryName;
	}

	/**
	 * itemCategoryName設定.
	 * @param itemCategoryName itemCategoryName
	 */
	public void setItemCategoryName(final String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
