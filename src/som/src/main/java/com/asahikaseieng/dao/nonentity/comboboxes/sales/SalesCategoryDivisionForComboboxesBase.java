/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.comboboxes.sales;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売上－売上区分コンボボックス用データ
 *
 * @author tosco
 */
public class SalesCategoryDivisionForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesCategoryDivisionForComboboxesBase() {
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

	/** 分類名 */
	private String categoryName;

	//
	// インスタンスメソッド
	//

	/**
	 * 分類コード取得
	 * @return String 分類コード
	*/
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	*/
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * データ集計区分を取得します。
	 * @return String データ集計区分
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
	 * 分類名取得
	 * @return String 分類名
	*/
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * 分類名設定
	 * @param categoryName 分類名
	*/
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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
