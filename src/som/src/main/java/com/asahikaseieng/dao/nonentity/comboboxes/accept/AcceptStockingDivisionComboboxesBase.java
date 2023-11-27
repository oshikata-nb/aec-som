/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.accept;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 受入・仕入－仕入区分コンボボックス用データ.
 *
 * @author tosco
 */
public class AcceptStockingDivisionComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptStockingDivisionComboboxesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "CLASSIFICATION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";


	//
	// インスタンスフィールド
	//

	/** 分類コード */
	private String categoryDivision;

	/** データ集計区分 */
	private String dataTotalDivision;

	/** 分類名称 */
	private String categoryName;

	//
	// インスタンスメソッド
	//

	/**
	 * 分類コードを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称を設定します。
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * データ集計区分を取得します。
	 * @return dataTotalDivision
	 */
	public String getDataTotalDivision() {
		return dataTotalDivision;
	}

	/**
	 * データ集計区分を設定します。
	 * @param dataTotalDivision データ集計区分
	 */
	public void setDataTotalDivision(final String dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
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
