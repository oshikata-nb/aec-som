/*
 * Created on 2008/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classification;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 分類マスタ一覧　Beanクラス.
 * @author tosco
 */
public class ClassificationListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.分類マスタ一覧
	 */
	public ClassificationListBase() {
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

	/** ﾃﾞｰﾀ種別 */
	private BigDecimal dataType;

	/** ﾃﾞｰﾀ集計区分 */
	private BigDecimal dataTotalDivision;

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

