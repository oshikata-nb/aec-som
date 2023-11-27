/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryMoveDetailクラス.
 * @author t0011036
 */
public class InventoryMoveDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryMoveDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション outLocationCd */
	public static final String outLocationCd_COLUMN = "OUT_LOCATION_CD";

	/** COLUMNアノテーション outLocationName */
	public static final String outLocationName_COLUMN = "OUT_LOCATION_NAME";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション kgConversionCoefficient */
	public static final String kgConversionCoefficient_COLUMN = "KG_CONVERSION_COEFFICIENT";

	/** COLUMNアノテーション smallnumLength */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";

	/** COLUMNアノテーション roundDivision */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** COLUMNアノテーション dispPackQty */
	public static final String dispPackQty_COLUMN = "DISP_PACK_QTY";

	/** COLUMNアノテーション packQty */
	public static final String packQty_COLUMN = "PACK_QTY";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション packUnit */
	public static final String packUnit_COLUMN = "PACK_UNIT";

	/** COLUMNアノテーション dispFraction */
	public static final String dispFraction_COLUMN = "DISP_FRACTION";

	/** COLUMNアノテーション fraction */
	public static final String fraction_COLUMN = "FRACTION";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション fractionUnit */
	public static final String fractionUnit_COLUMN = "FRACTION_UNIT";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private String itemName;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private String outLocationCd;

	private String outLocationName;

	private String lotNo;

	private String aliasLotNo;

	private java.math.BigDecimal kgOfFractionManagement;

	private java.math.BigDecimal kgConversionCoefficient;

	private java.math.BigDecimal smallnumLength;

	private java.math.BigDecimal roundDivision;

	private java.math.BigDecimal dispPackQty;

	private String packQty;

	private String unitOfOperationManagement;

	private String packUnit;

	private java.math.BigDecimal dispFraction;

	private String fraction;

	private String unitOfFractionManagement;

	private String fractionUnit;

	private java.math.BigDecimal inventoryQty;

	private java.math.BigDecimal typeDivision;

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
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
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
	 * outLocationCd取得.
	 * @return outLocationCd
	 */
	public String getOutLocationCd() {
		return this.outLocationCd;
	}

	/**
	 * outLocationCd設定.
	 * @param outLocationCd outLocationCd
	 */
	public void setOutLocationCd(final String outLocationCd) {
		this.outLocationCd = outLocationCd;
	}

	/**
	 * outLocationName取得.
	 * @return outLocationName
	 */
	public String getOutLocationName() {
		return this.outLocationName;
	}

	/**
	 * outLocationName設定.
	 * @param outLocationName outLocationName
	 */
	public void setOutLocationName(final String outLocationName) {
		this.outLocationName = outLocationName;
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
	 * smallnumLength取得.
	 * @return smallnumLength
	 */
	public java.math.BigDecimal getSmallnumLength() {
		return this.smallnumLength;
	}

	/**
	 * smallnumLength設定.
	 * @param smallnumLength smallnumLength
	 */
	public void setSmallnumLength(final java.math.BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * roundDivision取得.
	 * @return roundDivision
	 */
	public java.math.BigDecimal getRoundDivision() {
		return this.roundDivision;
	}

	/**
	 * roundDivision設定.
	 * @param roundDivision roundDivision
	 */
	public void setRoundDivision(final java.math.BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * dispPackQty取得.
	 * @return dispPackQty
	 */
	public java.math.BigDecimal getDispPackQty() {
		return this.dispPackQty;
	}

	/**
	 * dispPackQty設定.
	 * @param dispPackQty dispPackQty
	 */
	public void setDispPackQty(final java.math.BigDecimal dispPackQty) {
		this.dispPackQty = dispPackQty;
	}

	/**
	 * packQty取得.
	 * @return packQty
	 */
	public String getPackQty() {
		return this.packQty;
	}

	/**
	 * packQty設定.
	 * @param packQty packQty
	 */
	public void setPackQty(final String packQty) {
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
	 * dispFraction取得.
	 * @return dispFraction
	 */
	public java.math.BigDecimal getDispFraction() {
		return this.dispFraction;
	}

	/**
	 * dispFraction設定.
	 * @param dispFraction dispFraction
	 */
	public void setDispFraction(final java.math.BigDecimal dispFraction) {
		this.dispFraction = dispFraction;
	}

	/**
	 * fraction取得.
	 * @return fraction
	 */
	public String getFraction() {
		return this.fraction;
	}

	/**
	 * fraction設定.
	 * @param fraction fraction
	 */
	public void setFraction(final String fraction) {
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

