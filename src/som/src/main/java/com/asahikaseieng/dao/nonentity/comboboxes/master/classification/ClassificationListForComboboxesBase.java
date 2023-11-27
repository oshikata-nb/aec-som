/*
 * Created on 2009/09/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.classification;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ClassificationListForComboboxesクラス.
 * @author t0011036
 */
public class ClassificationListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClassificationListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション externalCategoryName */
	public static final String externalCategoryName_COLUMN = "EXTERNAL_CATEGORY_NAME";

	//
	// インスタンスフィールド
	//

	private String categoryDivision;

	private String categoryName;

	private String externalCategoryName;

	//
	// インスタンスメソッド
	//

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * categoryName取得.
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * externalCategoryName取得.
	 * @return externalCategoryName
	 */
	public String getExternalCategoryName() {
		return this.externalCategoryName;
	}

	/**
	 * externalCategoryName設定.
	 * @param externalCategoryName externalCategoryName
	 */
	public void setExternalCategoryName(final String externalCategoryName) {
		this.externalCategoryName = externalCategoryName;
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

