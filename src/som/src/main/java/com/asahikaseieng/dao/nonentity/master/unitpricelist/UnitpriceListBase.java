/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpricelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * UnitpriceListクラス.
 * @author t0011036
 */
public class UnitpriceListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション validDate */
	public static final String validDate_COLUMN = "VALID_DATE";

	/** COLUMNアノテーション consecutiveNo */
	public static final String consecutiveNo_COLUMN = "CONSECUTIVE_NO";

	/** COLUMNアノテーション version */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション shortItemName */
	public static final String shortItemName_COLUMN = "SHORT_ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション materialMakerName */
	public static final String materialMakerName_COLUMN = "MATERIAL_MAKER_NAME";

	//
	// インスタンスフィールド
	//

	private String venderDivision;

	private String venderCd;

	private String itemCd;

	private java.sql.Timestamp validDate;

	private java.math.BigDecimal consecutiveNo;

	private java.math.BigDecimal version;

	private String venderName1;

	private String itemName;

	private String shortItemName;

	private String styleOfPacking;

	private String materialMakerName;

	//
	// インスタンスメソッド
	//

	/**
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
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
	 * validDate取得.
	 * @return validDate
	 */
	public java.sql.Timestamp getValidDate() {
		return this.validDate;
	}

	/**
	 * validDate設定.
	 * @param validDate validDate
	 */
	public void setValidDate(final java.sql.Timestamp validDate) {
		this.validDate = validDate;
	}

	/**
	 * consecutiveNo取得.
	 * @return consecutiveNo
	 */
	public java.math.BigDecimal getConsecutiveNo() {
		return this.consecutiveNo;
	}

	/**
	 * consecutiveNo設定.
	 * @param consecutiveNo consecutiveNo
	 */
	public void setConsecutiveNo(final java.math.BigDecimal consecutiveNo) {
		this.consecutiveNo = consecutiveNo;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * materialMakerName取得.
	 * @return materialMakerName
	 */
	public String getMaterialMakerName() {
		return this.materialMakerName;
	}

	/**
	 * materialMakerName設定.
	 * @param materialMakerName materialMakerName
	 */
	public void setMaterialMakerName(final String materialMakerName) {
		this.materialMakerName = materialMakerName;
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

