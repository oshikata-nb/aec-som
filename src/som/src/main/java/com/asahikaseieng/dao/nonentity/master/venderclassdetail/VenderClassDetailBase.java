/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderclassdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * VenderClassDetailクラス.
 * @author t0011036
 */
public class VenderClassDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VenderClassDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション noteDivision */
	public static final String noteDivision_COLUMN = "NOTE_DIVISION";

	//
	// インスタンスフィールド
	//

	private String categoryDivision;

	private String categoryName;

	private java.math.BigDecimal noteDivision;

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
	 * noteDivision取得.
	 * @return noteDivision
	 */
	public java.math.BigDecimal getNoteDivision() {
		return this.noteDivision;
	}

	/**
	 * noteDivision設定.
	 * @param noteDivision noteDivision
	 */
	public void setNoteDivision(final java.math.BigDecimal noteDivision) {
		this.noteDivision = noteDivision;
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

