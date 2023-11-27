/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryupdatelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InquiryUpdateListクラス.
 * @author t0011036
 */
public class InquiryUpdateListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryUpdateListBase() {
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

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション countQty */
	public static final String countQty_COLUMN = "COUNT_QTY";

	/** COLUMNアノテーション fractionQty */
	public static final String fractionQty_COLUMN = "FRACTION_QTY";

	/** COLUMNアノテーション inputQty */
	public static final String inputQty_COLUMN = "INPUT_QTY";

	/** COLUMNアノテーション inputfraction */
	public static final String inputfraction_COLUMN = "INPUTFRACTION";

	/** COLUMNアノテーション countUpdateDate */
	public static final String countUpdateDate_COLUMN = "COUNT_UPDATE_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp countDate;

	private String countDivision;

	private String countLocation;

	private String itemCd;

	private String lotNo;

	private java.math.BigDecimal countQty;

	private java.math.BigDecimal fractionQty;

	private java.math.BigDecimal inputQty;

	private java.math.BigDecimal inputfraction;

	private java.sql.Timestamp countUpdateDate;

	private java.sql.Timestamp updateDate;

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

