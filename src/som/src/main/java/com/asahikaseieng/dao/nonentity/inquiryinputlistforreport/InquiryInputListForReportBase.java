/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InquiryInputListForReportクラス.
 * @author kanri-user
 */
public class InquiryInputListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryInputListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション countDate */
	public static final String countDate_COLUMN = "COUNT_DATE";

	/** COLUMNアノテーション countDivision */
	public static final String countDivision_COLUMN = "COUNT_DIVISION";

	/** COLUMNアノテーション countLocation */
	public static final String countLocation_COLUMN = "COUNT_LOCATION";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

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

	/** COLUMNアノテーション countCost */
	public static final String countCost_COLUMN = "COUNT_COST";

	/** COLUMNアノテーション countUpdateDate */
	public static final String countUpdateDate_COLUMN = "COUNT_UPDATE_DATE";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp countDate;

	private String countDivision;

	private String countLocation;

	private String locationName;

	private String itemCd;

	private String itemName;

	private String lotNo;

	private String aliasLotNo;

	private java.math.BigDecimal countQty;

	private java.math.BigDecimal fractionQty;

	private java.math.BigDecimal inputQty;

	private java.math.BigDecimal inputfraction;

	private java.math.BigDecimal countCost;

	private java.sql.Timestamp countUpdateDate;

	private String name01;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

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
	 * countCost取得.
	 * @return countCost
	 */
	public java.math.BigDecimal getCountCost() {
		return this.countCost;
	}

	/**
	 * countCost設定.
	 * @param countCost countCost
	 */
	public void setCountCost(final java.math.BigDecimal countCost) {
		this.countCost = countCost;
	}

	/**
	 * countUpdateDate取得.
	 * @return countUpdateDate
	 */
	public java.sql.Timestamp getCountUpdateDate() {
		return this.countUpdateDate;
	}

	/**
	 * countUpdateDate設定.
	 * @param countUpdateDate countUpdateDate
	 */
	public void setCountUpdateDate(final java.sql.Timestamp countUpdateDate) {
		this.countUpdateDate = countUpdateDate;
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
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
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
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

