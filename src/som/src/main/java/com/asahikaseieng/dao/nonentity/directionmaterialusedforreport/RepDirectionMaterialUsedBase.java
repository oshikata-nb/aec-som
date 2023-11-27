/*
 * Created on 2009/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.directionmaterialusedforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepDirectionMaterialUsedクラス.
 * @author kanri-user
 */
public class RepDirectionMaterialUsedBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDirectionMaterialUsedBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション division */
	public static final String division_COLUMN = "DIVISION";

	/** COLUMNアノテーション financialClassCdDisp */
	public static final String financialClassCdDisp_COLUMN = "FINANCIAL_CLASS_CD_DISP";

	/** COLUMNアノテーション itemCdDisp */
	public static final String itemCdDisp_COLUMN = "ITEM_CD_DISP";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション mItemDivision */
	public static final String mItemDivision_COLUMN = "M_ITEM_DIVISION";

	/** COLUMNアノテーション mItemCd */
	public static final String mItemCd_COLUMN = "M_ITEM_CD";

	/** COLUMNアノテーション mItemName */
	public static final String mItemName_COLUMN = "M_ITEM_NAME";

	/** COLUMNアノテーション mStyleOfPacking */
	public static final String mStyleOfPacking_COLUMN = "M_STYLE_OF_PACKING";

	/** COLUMNアノテーション unitprice */
	public static final String unitprice_COLUMN = "UNITPRICE";

	/** COLUMNアノテーション mResultQty */
	public static final String mResultQty_COLUMN = "M_RESULT_QTY";

	/** COLUMNアノテーション amount */
	public static final String amount_COLUMN = "AMOUNT";

	//
	// インスタンスフィールド
	//

	private String division;

	private String financialClassCdDisp;

	private String itemCdDisp;

	private String itemName;

	private String styleOfPacking;

	private java.math.BigDecimal resultQty;

	private String mItemDivision;

	private String mItemCd;

	private String mItemName;

	private String mStyleOfPacking;

	private java.math.BigDecimal unitprice;

	private java.math.BigDecimal mResultQty;

	private java.math.BigDecimal amount;

	//
	// インスタンスメソッド
	//

	/**
	 * division取得.
	 * @return division
	 */
	public String getDivision() {
		return this.division;
	}

	/**
	 * division設定.
	 * @param division division
	 */
	public void setDivision(final String division) {
		this.division = division;
	}

	/**
	 * financialClassCdDisp取得.
	 * @return financialClassCdDisp
	 */
	public String getFinancialClassCdDisp() {
		return this.financialClassCdDisp;
	}

	/**
	 * financialClassCdDisp設定.
	 * @param financialClassCdDisp financialClassCdDisp
	 */
	public void setFinancialClassCdDisp(final String financialClassCdDisp) {
		this.financialClassCdDisp = financialClassCdDisp;
	}

	/**
	 * itemCdDisp取得.
	 * @return itemCdDisp
	 */
	public String getItemCdDisp() {
		return this.itemCdDisp;
	}

	/**
	 * itemCdDisp設定.
	 * @param itemCdDisp itemCdDisp
	 */
	public void setItemCdDisp(final String itemCdDisp) {
		this.itemCdDisp = itemCdDisp;
	}

	/**
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * resultQty取得.
	 * @return resultQty
	 */
	public java.math.BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * resultQty設定.
	 * @param resultQty resultQty
	 */
	public void setResultQty(final java.math.BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * mItemDivision取得.
	 * @return mItemDivision
	 */
	public String getMItemDivision() {
		return this.mItemDivision;
	}

	/**
	 * mItemDivision設定.
	 * @param mItemDivision mItemDivision
	 */
	public void setMItemDivision(final String mItemDivision) {
		this.mItemDivision = mItemDivision;
	}

	/**
	 * mItemCd取得.
	 * @return mItemCd
	 */
	public String getMItemCd() {
		return this.mItemCd;
	}

	/**
	 * mItemCd設定.
	 * @param mItemCd mItemCd
	 */
	public void setMItemCd(final String mItemCd) {
		this.mItemCd = mItemCd;
	}

	/**
	 * mItemName取得.
	 * @return mItemName
	 */
	public String getMItemName() {
		return this.mItemName;
	}

	/**
	 * mItemName設定.
	 * @param mItemName mItemName
	 */
	public void setMItemName(final String mItemName) {
		this.mItemName = mItemName;
	}

	/**
	 * mStyleOfPacking取得.
	 * @return mStyleOfPacking
	 */
	public String getMStyleOfPacking() {
		return this.mStyleOfPacking;
	}

	/**
	 * mStyleOfPacking設定.
	 * @param mStyleOfPacking mStyleOfPacking
	 */
	public void setMStyleOfPacking(final String mStyleOfPacking) {
		this.mStyleOfPacking = mStyleOfPacking;
	}

	/**
	 * unitprice取得.
	 * @return unitprice
	 */
	public java.math.BigDecimal getUnitprice() {
		return this.unitprice;
	}

	/**
	 * unitprice設定.
	 * @param unitprice unitprice
	 */
	public void setUnitprice(final java.math.BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * mResultQty取得.
	 * @return mResultQty
	 */
	public java.math.BigDecimal getMResultQty() {
		return this.mResultQty;
	}

	/**
	 * mResultQty設定.
	 * @param mResultQty mResultQty
	 */
	public void setMResultQty(final java.math.BigDecimal mResultQty) {
		this.mResultQty = mResultQty;
	}

	/**
	 * amount取得.
	 * @return amount
	 */
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * amount設定.
	 * @param amount amount
	 */
	public void setAmount(final java.math.BigDecimal amount) {
		this.amount = amount;
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

