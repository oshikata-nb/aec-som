/*
 * Created on 2009/06/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repPackageDirectionDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepPackageDirectionDetailクラス.
 * @author kanri-user
 */
public class RepPackageDirectionDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepPackageDirectionDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション sumResultQty */
	public static final String sumResultQty_COLUMN = "SUM_RESULT_QTY";

	/** COLUMNアノテーション sumSampleQty */
	public static final String sumSampleQty_COLUMN = "SUM_SAMPLE_QTY";

	/** COLUMNアノテーション sumLossQty */
	public static final String sumLossQty_COLUMN = "SUM_LOSS_QTY";

	/** COLUMNアノテーション sumDefectQty */
	public static final String sumDefectQty_COLUMN = "SUM_DEFECT_QTY";

	/** COLUMNアノテーション sumAdjustQty */
	public static final String sumAdjustQty_COLUMN = "SUM_ADJUST_QTY";

	/** COLUMNアノテーション sumQty */
	public static final String sumQty_COLUMN = "SUM_QTY";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private String itemCd;

	private java.math.BigDecimal sumResultQty;

	private java.math.BigDecimal sumSampleQty;

	private java.math.BigDecimal sumLossQty;

	private java.math.BigDecimal sumDefectQty;

	private java.math.BigDecimal sumAdjustQty;

	private java.math.BigDecimal sumQty;

	private String itemName;

	private java.math.BigDecimal inventoryQty;

	//
	// インスタンスメソッド
	//

	/**
	 * directionDivision取得.
	 * @return directionDivision
	 */
	public java.math.BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * directionDivision設定.
	 * @param directionDivision directionDivision
	 */
	public void setDirectionDivision(final java.math.BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * directionNo取得.
	 * @return directionNo
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * directionNo設定.
	 * @param directionNo directionNo
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
	}

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * sumResultQty取得.
	 * @return sumResultQty
	 */
	public java.math.BigDecimal getSumResultQty() {
		return this.sumResultQty;
	}

	/**
	 * sumResultQty設定.
	 * @param sumResultQty sumResultQty
	 */
	public void setSumResultQty(final java.math.BigDecimal sumResultQty) {
		this.sumResultQty = sumResultQty;
	}

	/**
	 * sumSampleQty取得.
	 * @return sumSampleQty
	 */
	public java.math.BigDecimal getSumSampleQty() {
		return this.sumSampleQty;
	}

	/**
	 * sumSampleQty設定.
	 * @param sumSampleQty sumSampleQty
	 */
	public void setSumSampleQty(final java.math.BigDecimal sumSampleQty) {
		this.sumSampleQty = sumSampleQty;
	}

	/**
	 * sumLossQty取得.
	 * @return sumLossQty
	 */
	public java.math.BigDecimal getSumLossQty() {
		return this.sumLossQty;
	}

	/**
	 * sumLossQty設定.
	 * @param sumLossQty sumLossQty
	 */
	public void setSumLossQty(final java.math.BigDecimal sumLossQty) {
		this.sumLossQty = sumLossQty;
	}

	/**
	 * sumDefectQty取得.
	 * @return sumDefectQty
	 */
	public java.math.BigDecimal getSumDefectQty() {
		return this.sumDefectQty;
	}

	/**
	 * sumDefectQty設定.
	 * @param sumDefectQty sumDefectQty
	 */
	public void setSumDefectQty(final java.math.BigDecimal sumDefectQty) {
		this.sumDefectQty = sumDefectQty;
	}

	/**
	 * sumAdjustQty取得.
	 * @return sumAdjustQty
	 */
	public java.math.BigDecimal getSumAdjustQty() {
		return this.sumAdjustQty;
	}

	/**
	 * sumAdjustQty設定.
	 * @param sumAdjustQty sumAdjustQty
	 */
	public void setSumAdjustQty(final java.math.BigDecimal sumAdjustQty) {
		this.sumAdjustQty = sumAdjustQty;
	}

	/**
	 * sumQty取得.
	 * @return sumQty
	 */
	public java.math.BigDecimal getSumQty() {
		return this.sumQty;
	}

	/**
	 * sumQty設定.
	 * @param sumQty sumQty
	 */
	public void setSumQty(final java.math.BigDecimal sumQty) {
		this.sumQty = sumQty;
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
	 * inventoryQty取得.
	 * @return inventoryQty
	 */
	public java.math.BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * inventoryQty設定.
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
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

