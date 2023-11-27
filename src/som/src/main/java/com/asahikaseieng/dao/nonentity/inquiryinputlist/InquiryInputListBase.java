/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InquiryInputListクラス.
 * @author t0011036
 */
public class InquiryInputListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション countDate */
	public static final String countDate_COLUMN = "COUNT_DATE";

	/** COLUMNアノテーション countDivision */
	public static final String countDivision_COLUMN = "COUNT_DIVISION";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション shortName01 */
	public static final String shortName01_COLUMN = "SHORT_NAME01";

	/** COLUMNアノテーション countLocation */
	public static final String countLocation_COLUMN = "COUNT_LOCATION";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション shortItemName */
	public static final String shortItemName_COLUMN = "SHORT_ITEM_NAME";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション aliasLotNo */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション countQty */
	public static final String countQty_COLUMN = "COUNT_QTY";

	/** COLUMNアノテーション fractionQty */
	public static final String fractionQty_COLUMN = "FRACTION_QTY";

	/** COLUMNアノテーション inputQty */
	public static final String inputQty_COLUMN = "INPUT_QTY";

	/** COLUMNアノテーション inputfraction */
	public static final String inputfraction_COLUMN = "INPUTFRACTION";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitOfFractionManagement */
	public static final String unitOfFractionManagement_COLUMN = "UNIT_OF_FRACTION_MANAGEMENT";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp countDate;

	private String countDivision;

	private String name01;

	private String shortName01;

	private String countLocation;

	private String itemCd;

	private String itemName;

	private String shortItemName;

	private String lotNo;

	private String aliasLotNo;

	private java.math.BigDecimal countQty;

	private java.math.BigDecimal fractionQty;

	private java.math.BigDecimal inputQty;

	private java.math.BigDecimal inputfraction;

	private java.sql.Timestamp updateDate;

	private String unitOfOperationManagement;

	private String unitOfFractionManagement;

	//
	// インスタンスメソッド
	//

	/**
	 * countDate取得.
	 * @return countDate
	 */
	public java.sql.Timestamp getCountDate() {
		return this.countDate;
	}

	/**
	 * countDate設定.
	 * @param countDate countDate
	 */
	public void setCountDate(final java.sql.Timestamp countDate) {
		this.countDate = countDate;
	}

	/**
	 * countDivision取得.
	 * @return countDivision
	 */
	public String getCountDivision() {
		return this.countDivision;
	}

	/**
	 * countDivision設定.
	 * @param countDivision countDivision
	 */
	public void setCountDivision(final String countDivision) {
		this.countDivision = countDivision;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * shortName01取得.
	 * @return shortName01
	 */
	public String getShortName01() {
		return this.shortName01;
	}

	/**
	 * shortName01設定.
	 * @param shortName01 shortName01
	 */
	public void setShortName01(final String shortName01) {
		this.shortName01 = shortName01;
	}

	/**
	 * countLocation取得.
	 * @return countLocation
	 */
	public String getCountLocation() {
		return this.countLocation;
	}

	/**
	 * countLocation設定.
	 * @param countLocation countLocation
	 */
	public void setCountLocation(final String countLocation) {
		this.countLocation = countLocation;
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
	 * shortItemName取得.
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return this.shortItemName;
	}

	/**
	 * shortItemName設定.
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
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
	 * countQty取得.
	 * @return countQty
	 */
	public java.math.BigDecimal getCountQty() {
		return this.countQty;
	}

	/**
	 * countQty設定.
	 * @param countQty countQty
	 */
	public void setCountQty(final java.math.BigDecimal countQty) {
		this.countQty = countQty;
	}

	/**
	 * fractionQty取得.
	 * @return fractionQty
	 */
	public java.math.BigDecimal getFractionQty() {
		return this.fractionQty;
	}

	/**
	 * fractionQty設定.
	 * @param fractionQty fractionQty
	 */
	public void setFractionQty(final java.math.BigDecimal fractionQty) {
		this.fractionQty = fractionQty;
	}

	/**
	 * inputQty取得.
	 * @return inputQty
	 */
	public java.math.BigDecimal getInputQty() {
		return this.inputQty;
	}

	/**
	 * inputQty設定.
	 * @param inputQty inputQty
	 */
	public void setInputQty(final java.math.BigDecimal inputQty) {
		this.inputQty = inputQty;
	}

	/**
	 * inputfraction取得.
	 * @return inputfraction
	 */
	public java.math.BigDecimal getInputfraction() {
		return this.inputfraction;
	}

	/**
	 * inputfraction設定.
	 * @param inputfraction inputfraction
	 */
	public void setInputfraction(final java.math.BigDecimal inputfraction) {
		this.inputfraction = inputfraction;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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

