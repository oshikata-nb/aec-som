/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemCategoryListForComboboxesクラス.
 * @author t0011036
 */
public class ItemCategoryListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション itemCategoryName */
	public static final String itemCategoryName_COLUMN = "ITEM_CATEGORY_NAME";

	//
	// インスタンスフィールド
	//

	private String itemCategory;

	private String itemCategoryName;

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

