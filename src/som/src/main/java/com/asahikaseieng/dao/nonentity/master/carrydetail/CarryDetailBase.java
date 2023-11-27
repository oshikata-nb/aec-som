/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrydetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CarryDetailクラス.
 * @author t0011036
 */
public class CarryDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション abbreviation */
	public static final String abbreviation_COLUMN = "ABBREVIATION";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション flanTransShopCd */
	public static final String flanTransShopCd_COLUMN = "FLAN_TRANS_SHOP_CD";

	/** COLUMNアノテーション bcPublishDivision */
	public static final String bcPublishDivision_COLUMN = "BC_PUBLISH_DIVISION";
	
	/** COLUMNアノテーション bcHeader */
	public static final String bcHeader_COLUMN = "BC_HEADER";
	
	/** COLUMNアノテーション bcFooter */
	public static final String bcFooter_COLUMN = "BC_FOOTER";
	
	/** COLUMNアノテーション bcNumStart */
	public static final String bcNumStart_COLUMN = "BC_NUM_START";
	
	/** COLUMNアノテーション bcNumEnd */
	public static final String bcNumEnd_COLUMN = "BC_NUM_END";
	
	/** COLUMNアノテーション bcNumPresent */
	public static final String bcNumPresent_COLUMN = "BC_NUM_PRESENT";

	/** COLUMNアノテーション nextPublishBc */
	public static final String nextPublishBc_COLUMN = "BC_NUM_NEXT_PUBLISH";

	/** COLUMNアノテーション bcNumberOfDigit */
	public static final String bcNumberOfDigit_COLUMN = "BC_NUMBER_OF_DIGIT";

	/** COLUMNアノテーション bcNumCheckDigitStart */
	public static final String bcNumCheckDigitStart_COLUMN = "BC_CHECKDIGIT_START";

	/** COLUMNアノテーション bcNumCheckDigitEnd */
	public static final String bcNumCheckDigitEnd_COLUMN = "BC_CHECKDIGIT_END";

	
	/** COLUMNアノテーション remarks */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション labelPublish */
	public static final String labelPublish_COLUMN = "LABEL_PUBLISH";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション reportOutputNum. */
	public static final String reportOutputNum_COLUMN = "REPOTR_OUTPUT_NUM";

	//
	// インスタンスフィールド
	//

	private String carryCd;

	private String carryName1;

	private String carryName2;

	private String abbreviation;

	private String zipcodeNo;

	private String telNo;

	private String faxNo;

	private String address1;

	private String address2;

	private String address3;

	private String flanTransShopCd;
	
	private java.math.BigDecimal bcPublishDivision;
	
	private String bcHeader;
	
	private String bcFooter;
	
	private java.math.BigDecimal bcNumStart;

	private java.math.BigDecimal bcNumEnd;
	
	private java.math.BigDecimal bcNumPresent;

	private java.math.BigDecimal bcNumCheckDigitStart;

	private java.math.BigDecimal bcNumCheckDigitEnd;

	
	private String nextPublishBc;

	
	private java.math.BigDecimal bcNumberOfDigit;
	
	private String remarks;

	private java.math.BigDecimal labelPublish;

	private java.sql.Timestamp updateDate;

	private java.math.BigDecimal reportOutputNum;

	//
	// インスタンスメソッド
	//

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1取得.
	 * @return carryName1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * carryName1設定.
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryName2取得.
	 * @return carryName2
	 */
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * carryName2設定.
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * abbreviation取得.
	 * @return abbreviation
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/**
	 * abbreviation設定.
	 * @param abbreviation abbreviation
	 */
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * faxNo取得.
	 * @return faxNo
	 */
	public String getFaxNo() {
		return this.faxNo;
	}

	/**
	 * faxNo設定.
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * address1取得.
	 * @return address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * address1設定.
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2取得.
	 * @return address2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * address2設定.
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3取得.
	 * @return address3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * address3設定.
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * flanTransShopCd取得.
	 * @return flanTransShopCd
	 */
	public String getFlanTransShopCd() {
		return this.flanTransShopCd;
	}

	/**
	 * flanTransShopCd設定.
	 * @param flanTransShopCd flanTransShopCd
	 */
	public void setFlanTransShopCd(final String flanTransShopCd) {
		this.flanTransShopCd = flanTransShopCd;
	}

	/**
	 * bcPublishDivisionを取得します。
	 * @return bcPublishDivision
	 */
	public java.math.BigDecimal getBcPublishDivision() {
		return bcPublishDivision;
	}

	/**
	 * bcPublishDivisionを設定します。
	 * @param bcPublishDivision bcPublishDivision
	 */
	public void setBcPublishDivision(java.math.BigDecimal bcPublishDivision) {
		this.bcPublishDivision = bcPublishDivision;
	}

	/**
	 * bcHeaderを取得します。
	 * @return bcHeader
	 */
	public String getBcHeader() {
		return bcHeader;
	}

	/**
	 * bcHeaderを設定します。
	 * @param bcHeader bcHeader
	 */
	public void setBcHeader(String bcHeader) {
		this.bcHeader = bcHeader;
	}

	/**
	 * bcFooterを取得します。
	 * @return bcFooter
	 */
	public String getBcFooter() {
		return bcFooter;
	}

	/**
	 * bcFooterを設定します。
	 * @param bcFooter bcFooter
	 */
	public void setBcFooter(String bcFooter) {
		this.bcFooter = bcFooter;
	}

	/**
	 * bcNumStartを取得します。
	 * @return bcNumStart
	 */
	public java.math.BigDecimal getBcNumStart() {
		return bcNumStart;
	}

	/**
	 * bcNumStartを設定します。
	 * @param bcNumStart bcNumStart
	 */
	public void setBcNumStart(java.math.BigDecimal bcNumStart) {
		this.bcNumStart = bcNumStart;
	}

	/**
	 * bcNumEndを取得します。
	 * @return bcNumEnd
	 */
	public java.math.BigDecimal getBcNumEnd() {
		return bcNumEnd;
	}

	/**
	 * bcNumEndを設定します。
	 * @param bcNumEnd bcNumEnd
	 */
	public void setBcNumEnd(java.math.BigDecimal bcNumEnd) {
		this.bcNumEnd = bcNumEnd;
	}

	/**
	 * bcNumPresentを取得します。
	 * @return bcNumPresent
	 */
	public java.math.BigDecimal getBcNumPresent() {
		return bcNumPresent;
	}

	/**
	 * bcNumPresentを設定します。
	 * @param bcNumPresent bcNumPresent
	 */
	public void setBcNumPresent(java.math.BigDecimal bcNumPresent) {
		this.bcNumPresent = bcNumPresent;
	}


	/**
	 * nextPublishBcを取得します。
	 * @return nextPublishBc
	 */
	public String getNextPublishBc() {
		return nextPublishBc;
	}

	/**
	 * nextPublishBcを設定します。
	 * @param nextPublishBc nextPublishBc
	 */
	public void setNextPublishBc(String nextPublishBc) {
		this.nextPublishBc = nextPublishBc;
	}

	
	/**
	 * bcNumberOfDigitを取得します。
	 * @return bcNumberOfDigit
	 */
	public java.math.BigDecimal getBcNumberOfDigit() {
		return bcNumberOfDigit;
	}

	/**
	 * bcNumberOfDigitを設定します。
	 * @param bcNumberOfDigit bcNumberOfDigit
	 */
	public void setBcNumberOfDigit(java.math.BigDecimal bcNumberOfDigit) {
		this.bcNumberOfDigit = bcNumberOfDigit;
	}

	/**
	 * remarks取得.
	 * @return remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * remarks設定.
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * labelPublish取得.
	 * @return labelPublish
	 */
	public java.math.BigDecimal getLabelPublish() {
		return this.labelPublish;
	}

	/**
	 * labelPublish設定.
	 * @param labelPublish labelPublish
	 */
	public void setLabelPublish(final java.math.BigDecimal labelPublish) {
		this.labelPublish = labelPublish;
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
	 * reportOutputNum取得.
	 * @return reportOutputNum
	 */
	public java.math.BigDecimal getReportOutputNum() {
		return reportOutputNum;
	}

	/**
	 * reportOutputNum設定.
	 * @param reportOutputNum reportOutputNum
	 */
	public void setReportOutputNum(final java.math.BigDecimal reportOutputNum) {
		this.reportOutputNum = reportOutputNum;
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

	/**
	 * bcNumCheckDigitStartを取得します。
	 * @return bcNumCheckDigitStart
	 */
	public java.math.BigDecimal getBcNumCheckDigitStart() {
		return bcNumCheckDigitStart;
	}

	/**
	 * bcNumCheckDigitStartを設定します。
	 * @param bcNumCheckDigitStart bcNumCheckDigitStart
	 */
	public void setBcNumCheckDigitStart(java.math.BigDecimal bcNumCheckDigitStart) {
		this.bcNumCheckDigitStart = bcNumCheckDigitStart;
	}

	/**
	 * bcNumCheckDigitEndを取得します。
	 * @return bcNumCheckDigitEnd
	 */
	public java.math.BigDecimal getBcNumCheckDigitEnd() {
		return bcNumCheckDigitEnd;
	}

	/**
	 * bcNumCheckDigitEndを設定します。
	 * @param bcNumCheckDigitEnd bcNumCheckDigitEnd
	 */
	public void setBcNumCheckDigitEnd(java.math.BigDecimal bcNumCheckDigitEnd) {
		this.bcNumCheckDigitEnd = bcNumCheckDigitEnd;
	}
}

