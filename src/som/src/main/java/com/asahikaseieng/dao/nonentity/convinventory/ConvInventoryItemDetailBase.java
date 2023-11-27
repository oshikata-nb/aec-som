/*
 * Created on 2009/06/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.convinventory;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ConvInventoryItemDetailクラス.
 * @author t0011036
 */
public class ConvInventoryItemDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ConvInventoryItemDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/* Add 2022.05.19 取得内容の見直し T.Sato Start */
	/** COLUMNアノテーション unitOfStockControl */
	public static final String unitOfStockControl_COLUMN = "UNIT_OF_STOCK_CONTROL";
	
	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";
	/* Add 2022.05.19 取得内容の見直し T.Sato End   */
	
	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション kgConversionCoefficient */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal typeDivision;

	private String unitOfOperationManagement;

	/* Add 2022.05.19 取得内容の見直し T.Sato Start */
	private String unitOfStockControl;
	
	private String unitOfFractionManagement;
	/* Add 2022.05.19 取得内容の見直し T.Sato End   */
	
	private java.math.BigDecimal kgOfFractionManagement;

	private java.math.BigDecimal kgConversionCoefficient;

	//
	// インスタンスメソッド
	//

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
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public java.math.BigDecimal getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final java.math.BigDecimal typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * unitOfOperationManagement取得.
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagement設定.
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/* Add 2022.05.19 取得内容の見直し T.Sato Start */
	/**
	 * unitOfStockControl取得.
	 * @return unitOfStockControl
	 */
	public String getUnitOfStockControl() {
		return this.unitOfStockControl;
	}

	/**
	 * unitOfStockControl設定.
	 * @param unitOfStockControl unitOfStockControl
	 */
	public void setUnitOfStockControl(final String unitOfStockControl) {
		this.unitOfStockControl = unitOfStockControl;
	}
	
	/**
	 * unitOfFractionManagement取得.
	 * @return unitOfFractionManagement
	 */
	public String getUnitOfFractionManagement() {
		return this.unitOfFractionManagement;
	}

	/**
	 * unitOfFractionManagement設定.
	 * @param unitOfFractionManagement unitOfFractionManagement
	 */
	public void setUnitOfFractionManagement(final String unitOfFractionManagement) {
		this.unitOfFractionManagement = unitOfFractionManagement;
	}
	/* Add 2022.05.19 取得内容の見直し T.Sato End   */
	
	/**
	 * kgOfFractionManagement取得.
	 * @return kgOfFractionManagement
	 */
	public java.math.BigDecimal getKgOfFractionManagement() {
		return this.kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagement設定.
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(final java.math.BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * kgConversionCoefficient取得.
	 * @return kgConversionCoefficient
	 */
	public java.math.BigDecimal getKgConversionCoefficient() {
		return this.kgConversionCoefficient;
	}

	/**
	 * kgConversionCoefficient設定.
	 * @param kgConversionCoefficient kgConversionCoefficient
	 */
	public void setKgConversionCoefficient(final java.math.BigDecimal kgConversionCoefficient) {
		this.kgConversionCoefficient = kgConversionCoefficient;
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

