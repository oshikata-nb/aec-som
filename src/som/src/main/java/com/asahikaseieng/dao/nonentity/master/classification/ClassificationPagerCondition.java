/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classification;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecTextUtils;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * ClassificationPagerConditionクラス.分類マスタ
 * @author tosco
 */
public class ClassificationPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.ClassificationPagerCondition
	 */
	public ClassificationPagerCondition() {
	}

	//
	// 検索条件.
	//

	/** 検索入力：データ種別 */
	private BigDecimal srhDataType;

	/** 検索入力：データ集計区分 */
	private BigDecimal srhDataTotalDivision;

	/** 検索入力：分類コード */
	private String srhCategoryDivision;

	/** 検索入力：分類名称 */
	private String srhCategoryName;

	//
	// 検索条件.setter、getter
	//

	/**
	 * srhCategoryDivisionを取得します。
	 * @return srhCategoryDivision
	 */
	public String getSrhCategoryDivision() {
		return srhCategoryDivision;
	}

	/**
	 * srhCategoryDivisionを設定します。
	 * @param srhCategoryDivision srhCategoryDivision
	 */
	public void setSrhCategoryDivision(final String srhCategoryDivision) {
		this.srhCategoryDivision = AecTextUtils.likeFilter(srhCategoryDivision);
	}

	/**
	 * srhCategoryNameを取得します。
	 * @return srhCategoryName
	 */
	public String getSrhCategoryName() {
		return srhCategoryName;
	}

	/**
	 * srhCategoryNameを設定します。
	 * @param srhCategoryName srhCategoryName
	 */
	public void setSrhCategoryName(final String srhCategoryName) {
		this.srhCategoryName = AecTextUtils.likeFilter(srhCategoryName);
	}

	/**
	 * srhDataTotalDivisionを取得します。
	 * @return srhDataTotalDivision
	 */
	public BigDecimal getSrhDataTotalDivision() {
		return srhDataTotalDivision;
	}

	/**
	 * srhDataTotalDivisionを設定します。
	 * @param srhDataTotalDivision srhDataTotalDivision
	 */
	public void setSrhDataTotalDivision(final BigDecimal srhDataTotalDivision) {
		this.srhDataTotalDivision = srhDataTotalDivision;
	}

	/**
	 * srhDataTypeを取得します。
	 * @return srhDataType
	 */
	public BigDecimal getSrhDataType() {
		return srhDataType;
	}

	/**
	 * srhDataTypeを設定します。
	 * @param srhDataType srhDataType
	 */
	public void setSrhDataType(final BigDecimal srhDataType) {
		this.srhDataType = srhDataType;
	}

}
