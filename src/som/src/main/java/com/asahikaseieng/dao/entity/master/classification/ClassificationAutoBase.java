/*
 * Created on 2008/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.classification;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分類マスタ　Beanクラス.
 * @author tosco
 */
public class ClassificationAutoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClassificationAutoBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CLASSIFICATION";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	//
	// インスタンスフィールド	//

	/** データ種別(数値) */
	private BigDecimal dataType;

	/** データ集計区分(数値) */
	private BigDecimal dataTotalDivision;

	/** データ種別(文字列) */
	private String strDataType;

	/** データ集計区分(文字列) */
	private String strDataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 分類名称 */
	private String categoryName;

	//
	// インスタンスメソッド
	//

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

	/**
	 * データ種別取得.
	 * @return BigDecimal データ種別
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * データ種別設定.
	 * @param dataType データ種別
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * データ集計区分取得.
	 * @return BigDecimal データ集計区分
	 */
	public BigDecimal getDataTotalDivision() {
		return dataTotalDivision;
	}

	/**
	 * データ集計区分設定.
	 * @param dataTotalDivision データ集計区分
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * データ種別取得.
	 * @return String データ種別
	 */
	public String getStrDataType() {
		return strDataType;
	}

	/**
	 * データ種別設定.
	 * @param strDataType データ種別
	 */
	public void setStrDataType(final String strDataType) {
		this.strDataType = strDataType;
	}

	/**
	 * データ集計区分取得.
	 * @return String データ集計区分
	 */
	public String getStrDataTotalDivision() {
		return strDataTotalDivision;
	}

	/**
	 * データ集計区分設定.
	 * @param strDataTotalDivision データ集計区分
	 */
	public void setStrDataTotalDivision(final String strDataTotalDivision) {
		this.strDataTotalDivision = strDataTotalDivision;
	}

	/**
	 * 分類コード区分取得.
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コード設定.
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 分類名称区分取得.
	 * @return String 分類名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称設定.
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

}

