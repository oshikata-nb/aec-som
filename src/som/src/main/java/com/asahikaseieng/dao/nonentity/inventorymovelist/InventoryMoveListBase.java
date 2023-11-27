/*
 * Created on 2009/04/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryMoveListクラス.
 * @author t0011036
 */
public class InventoryMoveListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryMoveListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション packQty */
	public static final String packQty_COLUMN = "PACK_QTY";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション packUnit */
	public static final String packUnit_COLUMN = "PACK_UNIT";

	/** COLUMNアノテーション fraction */
	public static final String fraction_COLUMN = "FRACTION";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション fractionUnit */
	public static final String fractionUnit_COLUMN = "FRACTION_UNIT";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	//
	// インスタンスフィールド
	//

	private String locationCd;

	private String locationName;

	private String lotNo;

	private String aliasLotNo;

	private java.math.BigDecimal packQty;

	private String unitOfOperationManagement;

	private String packUnit;

	private java.math.BigDecimal fraction;

	private String unitOfFractionManagement;

	private String fractionUnit;

	private String itemCd;

	private java.math.BigDecimal inventoryQty;

	//
	// インスタンスメソッド
	//

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * aliasLotNo取得.
	 * @return aliasLotNo
	 */
	public String getAliasLotNo() {
		return this.aliasLotNo;
	}

	/**
	 * aliasLotNo設定.
	 * @param aliasLotNo aliasLotNo
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
	}

	/**
	 * packQty取得.
	 * @return packQty
	 */
	public java.math.BigDecimal getPackQty() {
		return this.packQty;
	}

	/**
	 * packQty設定.
	 * @param packQty packQty
	 */
	public void setPackQty(final java.math.BigDecimal packQty) {
		this.packQty = packQty;
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

	/**
	 * packUnit取得.
	 * @return packUnit
	 */
	public String getPackUnit() {
		return this.packUnit;
	}

	/**
	 * packUnit設定.
	 * @param packUnit packUnit
	 */
	public void setPackUnit(final String packUnit) {
		this.packUnit = packUnit;
	}

	/**
	 * fraction取得.
	 * @return fraction
	 */
	public java.math.BigDecimal getFraction() {
		return this.fraction;
	}

	/**
	 * fraction設定.
	 * @param fraction fraction
	 */
	public void setFraction(final java.math.BigDecimal fraction) {
		this.fraction = fraction;
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

	/**
	 * fractionUnit取得.
	 * @return fractionUnit
	 */
	public String getFractionUnit() {
		return this.fractionUnit;
	}

	/**
	 * fractionUnit設定.
	 * @param fractionUnit fractionUnit
	 */
	public void setFractionUnit(final String fractionUnit) {
		this.fractionUnit = fractionUnit;
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

