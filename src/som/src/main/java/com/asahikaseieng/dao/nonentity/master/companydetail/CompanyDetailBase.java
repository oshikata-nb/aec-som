/*
 * Created on 2009/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companydetail;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CompanyDetailクラス.
 * @author t0011036
 */
public class CompanyDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CompanyDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション homeName1 */
	public static final String homeName1_COLUMN = "HOME_NAME1";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション representRole */
	public static final String representRole_COLUMN = "REPRESENT_ROLE";

	/** COLUMNアノテーション representPerson */
	public static final String representPerson_COLUMN = "REPRESENT_PERSON";

	/** COLUMNアノテーション invoiceNo */
	public static final String invoiceNo_COLUMN = "INVOICE_NO";

	/** COLUMNアノテーション settlement */
	public static final String settlement_COLUMN = "SETTLEMENT";

	/** COLUMNアノテーション closingDay */
	public static final String closingDay_COLUMN = "CLOSING_DAY";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション taxRoundup */
	public static final String taxRoundup_COLUMN = "TAX_ROUNDUP";

	/** COLUMNアノテーション taxRoundupUnit */
	public static final String taxRoundupUnit_COLUMN = "TAX_ROUNDUP_UNIT";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション stockDiscountRate */
	public static final String stockDiscountRate_COLUMN = "STOCK_DISCOUNT_RATE";

	/** COLUMNアノテーション purchaseDiscountRate */
	public static final String purchaseDiscountRate_COLUMN = "PURCHASE_DISCOUNT_RATE";

	/** COLUMNアノテーション roundup */
	public static final String roundup_COLUMN = "ROUNDUP";

	/** COLUMNアノテーション roundupUnit */
	public static final String roundupUnit_COLUMN = "ROUNDUP_UNIT";

	/** COLUMNアノテーション salesRoundup */
	public static final String salesRoundup_COLUMN = "SALES_ROUNDUP";

	/** COLUMNアノテーション salesRoundupUnit */
	public static final String salesRoundupUnit_COLUMN = "SALES_ROUNDUP_UNIT";

	/** COLUMNアノテーション purchaseRoundup */
	public static final String purchaseRoundup_COLUMN = "PURCHASE_ROUNDUP";

	/** COLUMNアノテーション purchaseRoundupUnit */
	public static final String purchaseRoundupUnit_COLUMN = "PURCHASE_ROUNDUP_UNIT";

	/** COLUMNアノテーション unitpriceRoundup */
	public static final String unitpriceRoundup_COLUMN = "UNITPRICE_ROUNDUP";

	/** COLUMNアノテーション unitpriceRoundupUnit */
	public static final String unitpriceRoundupUnit_COLUMN = "UNITPRICE_ROUNDUP_UNIT";

	/** COLUMNアノテーション blendQtyRoundup */
	public static final String blendQtyRoundup_COLUMN = "BLEND_QTY_ROUNDUP";

	/** COLUMNアノテーション blendQtyRoundupUnit */
	public static final String blendQtyRoundupUnit_COLUMN = "BLEND_QTY_ROUNDUP_UNIT";

	/** COLUMNアノテーション mixRateRoundup */
	public static final String mixRateRoundup_COLUMN = "MIX_RATE_ROUNDUP";

	/** COLUMNアノテーション mixRateRoundupUnit */
	public static final String mixRateRoundupUnit_COLUMN = "MIX_RATE_ROUNDUP_UNIT";

	/** COLUMNアノテーション adjRoundup */
	public static final String adjRoundup_COLUMN = "ADJ_ROUNDUP";

	/** COLUMNアノテーション adjRoundupUnit */
	public static final String adjRoundupUnit_COLUMN = "ADJ_ROUNDUP_UNIT";

	/** COLUMNアノテーション paymentUpdate */
	public static final String paymentUpdate_COLUMN = "PAYMENT_UPDATE";

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション bankMasterCd1 */
	public static final String bankMasterCd1_COLUMN = "BANK_MASTER_CD1";

	/** COLUMNアノテーション bankMasterName1 */
	public static final String bankMasterName1_COLUMN = "BANK_MASTER_NAME1";

	/** COLUMNアノテーション accountAbbreviation1 */
	public static final String accountAbbreviation1_COLUMN = "ACCOUNT_ABBREVIATION1";

	/** COLUMNアノテーション accountDivision1 */
	public static final String accountDivision1_COLUMN = "ACCOUNT_DIVISION1";

	/** COLUMNアノテーション accountNo1 */
	public static final String accountNo1_COLUMN = "ACCOUNT_NO1";

	/** COLUMNアノテーション accountStockhold1 */
	public static final String accountStockhold1_COLUMN = "ACCOUNT_STOCKHOLD1";

	/** COLUMNアノテーション bankMasterCd2 */
	public static final String bankMasterCd2_COLUMN = "BANK_MASTER_CD2";

	/** COLUMNアノテーション bankMasterName2 */
	public static final String bankMasterName2_COLUMN = "BANK_MASTER_NAME2";

	/** COLUMNアノテーション accountAbbreviation2 */
	public static final String accountAbbreviation2_COLUMN = "ACCOUNT_ABBREVIATION2";

	/** COLUMNアノテーション accountDivision2 */
	public static final String accountDivision2_COLUMN = "ACCOUNT_DIVISION2";

	/** COLUMNアノテーション accountNo2 */
	public static final String accountNo2_COLUMN = "ACCOUNT_NO2";

	/** COLUMNアノテーション accountStockhold2 */
	public static final String accountStockhold2_COLUMN = "ACCOUNT_STOCKHOLD2";

	/** COLUMNアノテーション bankMasterCd3 */
	public static final String bankMasterCd3_COLUMN = "BANK_MASTER_CD3";

	/** COLUMNアノテーション bankMasterName3 */
	public static final String bankMasterName3_COLUMN = "BANK_MASTER_NAME3";

	/** COLUMNアノテーション accountAbbreviation3 */
	public static final String accountAbbreviation3_COLUMN = "ACCOUNT_ABBREVIATION3";

	/** COLUMNアノテーション accountDivision3 */
	public static final String accountDivision3_COLUMN = "ACCOUNT_DIVISION3";

	/** COLUMNアノテーション accountNo3 */
	public static final String accountNo3_COLUMN = "ACCOUNT_NO3";

	/** COLUMNアノテーション accountStockhold3 */
	public static final String accountStockhold3_COLUMN = "ACCOUNT_STOCKHOLD3";

	/** COLUMNアノテーション bankMasterCd4 */
	public static final String bankMasterCd4_COLUMN = "BANK_MASTER_CD4";

	/** COLUMNアノテーション bankMasterName4 */
	public static final String bankMasterName4_COLUMN = "BANK_MASTER_NAME4";

	/** COLUMNアノテーション accountAbbreviation4 */
	public static final String accountAbbreviation4_COLUMN = "ACCOUNT_ABBREVIATION4";

	/** COLUMNアノテーション accountDivision4 */
	public static final String accountDivision4_COLUMN = "ACCOUNT_DIVISION4";

	/** COLUMNアノテーション accountNo4 */
	public static final String accountNo4_COLUMN = "ACCOUNT_NO4";

	/** COLUMNアノテーション accountStockhold4 */
	public static final String accountStockhold4_COLUMN = "ACCOUNT_STOCKHOLD4";

	/** COLUMNアノテーション bankMasterCd */
	public static final String bankMasterCd_COLUMN = "BANK_MASTER_CD";

	/** COLUMNアノテーション bankMasterName */
	public static final String bankMasterName_COLUMN = "BANK_MASTER_NAME";

	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション transferClientCd */
	public static final String transferClientCd_COLUMN = "TRANSFER_CLIENT_CD";

	/** COLUMNアノテーション transferClientName */
	public static final String transferClientName_COLUMN = "TRANSFER_CLIENT_NAME";

	/** COLUMNアノテーション passwordValidTerm */
	public static final String passwordValidTerm_COLUMN = "PASSWORD_VALID_TERM";

	/** COLUMNアノテーション passwordKetaLowerLimit */
	public static final String passwordKetaLowerLimit_COLUMN = "PASSWORD_KETA_LOWER_LIMIT";

	/** COLUMNアノテーション passwordKetaUpperLimit */
	public static final String passwordKetaUpperLimit_COLUMN = "PASSWORD_KETA_UPPER_LIMIT";

	/** COLUMNアノテーション prime */
	public static final String prime_COLUMN = "PRIME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション netTaxRatio */
	public static final String newTaxRatio_COLUMN = "NEW_TAX_RATIO";

	/** COLUMNアノテーション newTaxApllyDate */
	public static final String newTaxApllyDate_COLUMN = "NEW_TAX_APLLY_DATE";

	//
	// インスタンスフィールド
	//

	private String homeName1;

	private String zipcodeNo;

	private String address1;

	private String address2;

	private String address3;

	private String telNo;

	private String faxNo;

	private String representRole;

	private String representPerson;

	private String invoiceNo;

	private java.math.BigDecimal settlement;

	private java.math.BigDecimal closingDay;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal calcDivision;

	private java.math.BigDecimal taxRoundup;

	private java.math.BigDecimal taxRoundupUnit;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal stockDiscountRate;

	private java.math.BigDecimal purchaseDiscountRate;

	private java.math.BigDecimal roundup;

	private java.math.BigDecimal roundupUnit;

	private java.math.BigDecimal salesRoundup;

	private java.math.BigDecimal salesRoundupUnit;

	private java.math.BigDecimal purchaseRoundup;

	private java.math.BigDecimal purchaseRoundupUnit;

	private java.math.BigDecimal unitpriceRoundup;

	private java.math.BigDecimal unitpriceRoundupUnit;

	private java.math.BigDecimal blendQtyRoundup;

	private java.math.BigDecimal blendQtyRoundupUnit;

	private java.math.BigDecimal mixRateRoundup;

	private java.math.BigDecimal mixRateRoundupUnit;

	private java.math.BigDecimal adjRoundup;

	private java.math.BigDecimal adjRoundupUnit;

	private java.math.BigDecimal paymentUpdate;

	private String companyCd;

	private String bankMasterCd1;

	private String bankMasterName1;

	private String accountAbbreviation1;

	private java.math.BigDecimal accountDivision1;

	private String accountNo1;

	private String accountStockhold1;

	private String bankMasterCd2;

	private String bankMasterName2;

	private String accountAbbreviation2;

	private java.math.BigDecimal accountDivision2;

	private String accountNo2;

	private String accountStockhold2;

	private String bankMasterCd3;

	private String bankMasterName3;

	private String accountAbbreviation3;

	private java.math.BigDecimal accountDivision3;

	private String accountNo3;

	private String accountStockhold3;

	private String bankMasterCd4;

	private String bankMasterName4;

	private String accountAbbreviation4;

	private java.math.BigDecimal accountDivision4;

	private String accountNo4;

	private String accountStockhold4;

	private String bankMasterCd;

	private String bankMasterName;

	private java.math.BigDecimal accountDivision;

	private String accountNo;

	private String transferClientCd;

	private String transferClientName;

	private java.math.BigDecimal passwordValidTerm;

	private java.math.BigDecimal passwordKetaLowerLimit;

	private java.math.BigDecimal passwordKetaUpperLimit;

	private java.math.BigDecimal prime;

	private java.sql.Timestamp updateDate;

	private BigDecimal newTaxRatio;

	private java.sql.Timestamp newTaxApllyDate;

	//
	// インスタンスメソッド
	//

	/**
	 * homeName1取得.
	 * @return homeName1
	 */
	public String getHomeName1() {
		return this.homeName1;
	}

	/**
	 * homeName1設定.
	 * @param homeName1 homeName1
	 */
	public void setHomeName1(final String homeName1) {
		this.homeName1 = homeName1;
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
	 * representRole取得.
	 * @return representRole
	 */
	public String getRepresentRole() {
		return this.representRole;
	}

	/**
	 * representRole設定.
	 * @param representRole representRole
	 */
	public void setRepresentRole(final String representRole) {
		this.representRole = representRole;
	}

	/**
	 * representPerson取得.
	 * @return representPerson
	 */
	public String getRepresentPerson() {
		return this.representPerson;
	}

	/**
	 * representPerson設定.
	 * @param representPerson representPerson
	 */
	public void setRepresentPerson(final String representPerson) {
		this.representPerson = representPerson;
	}

	/**
	 * invoiceNo取得.
	 * @return invoiceNo
	 */
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * invoiceNo設定.
	 * @param invoiceNo invoiceNo
	 */
	public void setInvoiceNo(final String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * settlement取得.
	 * @return settlement
	 */
	public java.math.BigDecimal getSettlement() {
		return this.settlement;
	}

	/**
	 * settlement設定.
	 * @param settlement settlement
	 */
	public void setSettlement(final java.math.BigDecimal settlement) {
		this.settlement = settlement;
	}

	/**
	 * closingDay取得.
	 * @return closingDay
	 */
	public java.math.BigDecimal getClosingDay() {
		return this.closingDay;
	}

	/**
	 * closingDay設定.
	 * @param closingDay closingDay
	 */
	public void setClosingDay(final java.math.BigDecimal closingDay) {
		this.closingDay = closingDay;
	}

	/**
	 * taxDivision取得.
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * taxDivision設定.
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * calcDivision取得.
	 * @return calcDivision
	 */
	public java.math.BigDecimal getCalcDivision() {
		return this.calcDivision;
	}

	/**
	 * calcDivision設定.
	 * @param calcDivision calcDivision
	 */
	public void setCalcDivision(final java.math.BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * taxRoundup取得.
	 * @return taxRoundup
	 */
	public java.math.BigDecimal getTaxRoundup() {
		return this.taxRoundup;
	}

	/**
	 * taxRoundup設定.
	 * @param taxRoundup taxRoundup
	 */
	public void setTaxRoundup(final java.math.BigDecimal taxRoundup) {
		this.taxRoundup = taxRoundup;
	}

	/**
	 * taxRoundupUnit取得.
	 * @return taxRoundupUnit
	 */
	public java.math.BigDecimal getTaxRoundupUnit() {
		return this.taxRoundupUnit;
	}

	/**
	 * taxRoundupUnit設定.
	 * @param taxRoundupUnit taxRoundupUnit
	 */
	public void setTaxRoundupUnit(final java.math.BigDecimal taxRoundupUnit) {
		this.taxRoundupUnit = taxRoundupUnit;
	}

	/**
	 * taxRatio取得.
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * taxRatio設定.
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * stockDiscountRate取得.
	 * @return stockDiscountRate
	 */
	public java.math.BigDecimal getStockDiscountRate() {
		return this.stockDiscountRate;
	}

	/**
	 * stockDiscountRate設定.
	 * @param stockDiscountRate stockDiscountRate
	 */
	public void setStockDiscountRate(
			final java.math.BigDecimal stockDiscountRate) {
		this.stockDiscountRate = stockDiscountRate;
	}

	/**
	 * purchaseDiscountRate取得.
	 * @return purchaseDiscountRate
	 */
	public java.math.BigDecimal getPurchaseDiscountRate() {
		return this.purchaseDiscountRate;
	}

	/**
	 * purchaseDiscountRate設定.
	 * @param purchaseDiscountRate purchaseDiscountRate
	 */
	public void setPurchaseDiscountRate(
			final java.math.BigDecimal purchaseDiscountRate) {
		this.purchaseDiscountRate = purchaseDiscountRate;
	}

	/**
	 * roundup取得.
	 * @return roundup
	 */
	public java.math.BigDecimal getRoundup() {
		return this.roundup;
	}

	/**
	 * roundup設定.
	 * @param roundup roundup
	 */
	public void setRoundup(final java.math.BigDecimal roundup) {
		this.roundup = roundup;
	}

	/**
	 * roundupUnit取得.
	 * @return roundupUnit
	 */
	public java.math.BigDecimal getRoundupUnit() {
		return this.roundupUnit;
	}

	/**
	 * roundupUnit設定.
	 * @param roundupUnit roundupUnit
	 */
	public void setRoundupUnit(final java.math.BigDecimal roundupUnit) {
		this.roundupUnit = roundupUnit;
	}

	/**
	 * salesRoundup取得.
	 * @return salesRoundup
	 */
	public java.math.BigDecimal getSalesRoundup() {
		return this.salesRoundup;
	}

	/**
	 * salesRoundup設定.
	 * @param salesRoundup salesRoundup
	 */
	public void setSalesRoundup(final java.math.BigDecimal salesRoundup) {
		this.salesRoundup = salesRoundup;
	}

	/**
	 * salesRoundupUnit取得.
	 * @return salesRoundupUnit
	 */
	public java.math.BigDecimal getSalesRoundupUnit() {
		return this.salesRoundupUnit;
	}

	/**
	 * salesRoundupUnit設定.
	 * @param salesRoundupUnit salesRoundupUnit
	 */
	public void setSalesRoundupUnit(final java.math.BigDecimal salesRoundupUnit) {
		this.salesRoundupUnit = salesRoundupUnit;
	}

	/**
	 * purchaseRoundup取得.
	 * @return purchaseRoundup
	 */
	public java.math.BigDecimal getPurchaseRoundup() {
		return this.purchaseRoundup;
	}

	/**
	 * purchaseRoundup設定.
	 * @param purchaseRoundup purchaseRoundup
	 */
	public void setPurchaseRoundup(final java.math.BigDecimal purchaseRoundup) {
		this.purchaseRoundup = purchaseRoundup;
	}

	/**
	 * purchaseRoundupUnit取得.
	 * @return purchaseRoundupUnit
	 */
	public java.math.BigDecimal getPurchaseRoundupUnit() {
		return this.purchaseRoundupUnit;
	}

	/**
	 * purchaseRoundupUnit設定.
	 * @param purchaseRoundupUnit purchaseRoundupUnit
	 */
	public void setPurchaseRoundupUnit(
			final java.math.BigDecimal purchaseRoundupUnit) {
		this.purchaseRoundupUnit = purchaseRoundupUnit;
	}

	/**
	 * unitpriceRoundup取得.
	 * @return unitpriceRoundup
	 */
	public java.math.BigDecimal getUnitpriceRoundup() {
		return this.unitpriceRoundup;
	}

	/**
	 * unitpriceRoundup設定.
	 * @param unitpriceRoundup unitpriceRoundup
	 */
	public void setUnitpriceRoundup(final java.math.BigDecimal unitpriceRoundup) {
		this.unitpriceRoundup = unitpriceRoundup;
	}

	/**
	 * unitpriceRoundupUnit取得.
	 * @return unitpriceRoundupUnit
	 */
	public java.math.BigDecimal getUnitpriceRoundupUnit() {
		return this.unitpriceRoundupUnit;
	}

	/**
	 * unitpriceRoundupUnit設定.
	 * @param unitpriceRoundupUnit unitpriceRoundupUnit
	 */
	public void setUnitpriceRoundupUnit(
			final java.math.BigDecimal unitpriceRoundupUnit) {
		this.unitpriceRoundupUnit = unitpriceRoundupUnit;
	}

	/**
	 * blendQtyRoundup取得.
	 * @return blendQtyRoundup
	 */
	public java.math.BigDecimal getBlendQtyRoundup() {
		return this.blendQtyRoundup;
	}

	/**
	 * blendQtyRoundup設定.
	 * @param blendQtyRoundup blendQtyRoundup
	 */
	public void setBlendQtyRoundup(final java.math.BigDecimal blendQtyRoundup) {
		this.blendQtyRoundup = blendQtyRoundup;
	}

	/**
	 * blendQtyRoundupUnit取得.
	 * @return blendQtyRoundupUnit
	 */
	public java.math.BigDecimal getBlendQtyRoundupUnit() {
		return this.blendQtyRoundupUnit;
	}

	/**
	 * blendQtyRoundupUnit設定.
	 * @param blendQtyRoundupUnit blendQtyRoundupUnit
	 */
	public void setBlendQtyRoundupUnit(
			final java.math.BigDecimal blendQtyRoundupUnit) {
		this.blendQtyRoundupUnit = blendQtyRoundupUnit;
	}

	/**
	 * mixRateRoundup取得.
	 * @return mixRateRoundup
	 */
	public java.math.BigDecimal getMixRateRoundup() {
		return this.mixRateRoundup;
	}

	/**
	 * mixRateRoundup設定.
	 * @param mixRateRoundup mixRateRoundup
	 */
	public void setMixRateRoundup(final java.math.BigDecimal mixRateRoundup) {
		this.mixRateRoundup = mixRateRoundup;
	}

	/**
	 * mixRateRoundupUnit取得.
	 * @return mixRateRoundupUnit
	 */
	public java.math.BigDecimal getMixRateRoundupUnit() {
		return this.mixRateRoundupUnit;
	}

	/**
	 * mixRateRoundupUnit設定.
	 * @param mixRateRoundupUnit mixRateRoundupUnit
	 */
	public void setMixRateRoundupUnit(
			final java.math.BigDecimal mixRateRoundupUnit) {
		this.mixRateRoundupUnit = mixRateRoundupUnit;
	}

	/**
	 * adjRoundup取得.
	 * @return adjRoundup
	 */
	public java.math.BigDecimal getAdjRoundup() {
		return this.adjRoundup;
	}

	/**
	 * adjRoundup設定.
	 * @param adjRoundup adjRoundup
	 */
	public void setAdjRoundup(final java.math.BigDecimal adjRoundup) {
		this.adjRoundup = adjRoundup;
	}

	/**
	 * adjRoundupUnit取得.
	 * @return adjRoundupUnit
	 */
	public java.math.BigDecimal getAdjRoundupUnit() {
		return this.adjRoundupUnit;
	}

	/**
	 * adjRoundupUnit設定.
	 * @param adjRoundupUnit adjRoundupUnit
	 */
	public void setAdjRoundupUnit(final java.math.BigDecimal adjRoundupUnit) {
		this.adjRoundupUnit = adjRoundupUnit;
	}

	/**
	 * paymentUpdate取得.
	 * @return paymentUpdate
	 */
	public java.math.BigDecimal getPaymentUpdate() {
		return this.paymentUpdate;
	}

	/**
	 * paymentUpdate設定.
	 * @param paymentUpdate paymentUpdate
	 */
	public void setPaymentUpdate(final java.math.BigDecimal paymentUpdate) {
		this.paymentUpdate = paymentUpdate;
	}

	/**
	 * companyCd取得.
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return this.companyCd;
	}

	/**
	 * companyCd設定.
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * bankMasterCd1取得.
	 * @return bankMasterCd1
	 */
	public String getBankMasterCd1() {
		return this.bankMasterCd1;
	}

	/**
	 * bankMasterCd1設定.
	 * @param bankMasterCd1 bankMasterCd1
	 */
	public void setBankMasterCd1(final String bankMasterCd1) {
		this.bankMasterCd1 = bankMasterCd1;
	}

	/**
	 * bankMasterName1取得.
	 * @return bankMasterName1
	 */
	public String getBankMasterName1() {
		return this.bankMasterName1;
	}

	/**
	 * bankMasterName1設定.
	 * @param bankMasterName1 bankMasterName1
	 */
	public void setBankMasterName1(final String bankMasterName1) {
		this.bankMasterName1 = bankMasterName1;
	}

	/**
	 * accountAbbreviation1取得.
	 * @return accountAbbreviation1
	 */
	public String getAccountAbbreviation1() {
		return this.accountAbbreviation1;
	}

	/**
	 * accountAbbreviation1設定.
	 * @param accountAbbreviation1 accountAbbreviation1
	 */
	public void setAccountAbbreviation1(final String accountAbbreviation1) {
		this.accountAbbreviation1 = accountAbbreviation1;
	}

	/**
	 * accountDivision1取得.
	 * @return accountDivision1
	 */
	public java.math.BigDecimal getAccountDivision1() {
		return this.accountDivision1;
	}

	/**
	 * accountDivision1設定.
	 * @param accountDivision1 accountDivision1
	 */
	public void setAccountDivision1(final java.math.BigDecimal accountDivision1) {
		this.accountDivision1 = accountDivision1;
	}

	/**
	 * accountNo1取得.
	 * @return accountNo1
	 */
	public String getAccountNo1() {
		return this.accountNo1;
	}

	/**
	 * accountNo1設定.
	 * @param accountNo1 accountNo1
	 */
	public void setAccountNo1(final String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	/**
	 * accountStockhold1取得.
	 * @return accountStockhold1
	 */
	public String getAccountStockhold1() {
		return this.accountStockhold1;
	}

	/**
	 * accountStockhold1設定.
	 * @param accountStockhold1 accountStockhold1
	 */
	public void setAccountStockhold1(final String accountStockhold1) {
		this.accountStockhold1 = accountStockhold1;
	}

	/**
	 * bankMasterCd2取得.
	 * @return bankMasterCd2
	 */
	public String getBankMasterCd2() {
		return this.bankMasterCd2;
	}

	/**
	 * bankMasterCd2設定.
	 * @param bankMasterCd2 bankMasterCd2
	 */
	public void setBankMasterCd2(final String bankMasterCd2) {
		this.bankMasterCd2 = bankMasterCd2;
	}

	/**
	 * bankMasterName2取得.
	 * @return bankMasterName2
	 */
	public String getBankMasterName2() {
		return this.bankMasterName2;
	}

	/**
	 * bankMasterName2設定.
	 * @param bankMasterName2 bankMasterName2
	 */
	public void setBankMasterName2(final String bankMasterName2) {
		this.bankMasterName2 = bankMasterName2;
	}

	/**
	 * accountAbbreviation2取得.
	 * @return accountAbbreviation2
	 */
	public String getAccountAbbreviation2() {
		return this.accountAbbreviation2;
	}

	/**
	 * accountAbbreviation2設定.
	 * @param accountAbbreviation2 accountAbbreviation2
	 */
	public void setAccountAbbreviation2(final String accountAbbreviation2) {
		this.accountAbbreviation2 = accountAbbreviation2;
	}

	/**
	 * accountDivision2取得.
	 * @return accountDivision2
	 */
	public java.math.BigDecimal getAccountDivision2() {
		return this.accountDivision2;
	}

	/**
	 * accountDivision2設定.
	 * @param accountDivision2 accountDivision2
	 */
	public void setAccountDivision2(final java.math.BigDecimal accountDivision2) {
		this.accountDivision2 = accountDivision2;
	}

	/**
	 * accountNo2取得.
	 * @return accountNo2
	 */
	public String getAccountNo2() {
		return this.accountNo2;
	}

	/**
	 * accountNo2設定.
	 * @param accountNo2 accountNo2
	 */
	public void setAccountNo2(final String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	/**
	 * accountStockhold2取得.
	 * @return accountStockhold2
	 */
	public String getAccountStockhold2() {
		return this.accountStockhold2;
	}

	/**
	 * accountStockhold2設定.
	 * @param accountStockhold2 accountStockhold2
	 */
	public void setAccountStockhold2(final String accountStockhold2) {
		this.accountStockhold2 = accountStockhold2;
	}

	/**
	 * bankMasterCd3取得.
	 * @return bankMasterCd3
	 */
	public String getBankMasterCd3() {
		return this.bankMasterCd3;
	}

	/**
	 * bankMasterCd3設定.
	 * @param bankMasterCd3 bankMasterCd3
	 */
	public void setBankMasterCd3(final String bankMasterCd3) {
		this.bankMasterCd3 = bankMasterCd3;
	}

	/**
	 * bankMasterName3取得.
	 * @return bankMasterName3
	 */
	public String getBankMasterName3() {
		return this.bankMasterName3;
	}

	/**
	 * bankMasterName3設定.
	 * @param bankMasterName3 bankMasterName3
	 */
	public void setBankMasterName3(final String bankMasterName3) {
		this.bankMasterName3 = bankMasterName3;
	}

	/**
	 * accountAbbreviation3取得.
	 * @return accountAbbreviation3
	 */
	public String getAccountAbbreviation3() {
		return this.accountAbbreviation3;
	}

	/**
	 * accountAbbreviation3設定.
	 * @param accountAbbreviation3 accountAbbreviation3
	 */
	public void setAccountAbbreviation3(final String accountAbbreviation3) {
		this.accountAbbreviation3 = accountAbbreviation3;
	}

	/**
	 * accountDivision3取得.
	 * @return accountDivision3
	 */
	public java.math.BigDecimal getAccountDivision3() {
		return this.accountDivision3;
	}

	/**
	 * accountDivision3設定.
	 * @param accountDivision3 accountDivision3
	 */
	public void setAccountDivision3(final java.math.BigDecimal accountDivision3) {
		this.accountDivision3 = accountDivision3;
	}

	/**
	 * accountNo3取得.
	 * @return accountNo3
	 */
	public String getAccountNo3() {
		return this.accountNo3;
	}

	/**
	 * accountNo3設定.
	 * @param accountNo3 accountNo3
	 */
	public void setAccountNo3(final String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	/**
	 * accountStockhold3取得.
	 * @return accountStockhold3
	 */
	public String getAccountStockhold3() {
		return this.accountStockhold3;
	}

	/**
	 * accountStockhold3設定.
	 * @param accountStockhold3 accountStockhold3
	 */
	public void setAccountStockhold3(final String accountStockhold3) {
		this.accountStockhold3 = accountStockhold3;
	}

	/**
	 * bankMasterCd4取得.
	 * @return bankMasterCd4
	 */
	public String getBankMasterCd4() {
		return this.bankMasterCd4;
	}

	/**
	 * bankMasterCd4設定.
	 * @param bankMasterCd4 bankMasterCd4
	 */
	public void setBankMasterCd4(final String bankMasterCd4) {
		this.bankMasterCd4 = bankMasterCd4;
	}

	/**
	 * bankMasterName4取得.
	 * @return bankMasterName4
	 */
	public String getBankMasterName4() {
		return this.bankMasterName4;
	}

	/**
	 * bankMasterName4設定.
	 * @param bankMasterName4 bankMasterName4
	 */
	public void setBankMasterName4(final String bankMasterName4) {
		this.bankMasterName4 = bankMasterName4;
	}

	/**
	 * accountAbbreviation4取得.
	 * @return accountAbbreviation4
	 */
	public String getAccountAbbreviation4() {
		return this.accountAbbreviation4;
	}

	/**
	 * accountAbbreviation4設定.
	 * @param accountAbbreviation4 accountAbbreviation4
	 */
	public void setAccountAbbreviation4(final String accountAbbreviation4) {
		this.accountAbbreviation4 = accountAbbreviation4;
	}

	/**
	 * accountDivision4取得.
	 * @return accountDivision4
	 */
	public java.math.BigDecimal getAccountDivision4() {
		return this.accountDivision4;
	}

	/**
	 * accountDivision4設定.
	 * @param accountDivision4 accountDivision4
	 */
	public void setAccountDivision4(final java.math.BigDecimal accountDivision4) {
		this.accountDivision4 = accountDivision4;
	}

	/**
	 * accountNo4取得.
	 * @return accountNo4
	 */
	public String getAccountNo4() {
		return this.accountNo4;
	}

	/**
	 * accountNo4設定.
	 * @param accountNo4 accountNo4
	 */
	public void setAccountNo4(final String accountNo4) {
		this.accountNo4 = accountNo4;
	}

	/**
	 * accountStockhold4取得.
	 * @return accountStockhold4
	 */
	public String getAccountStockhold4() {
		return this.accountStockhold4;
	}

	/**
	 * accountStockhold4設定.
	 * @param accountStockhold4 accountStockhold4
	 */
	public void setAccountStockhold4(final String accountStockhold4) {
		this.accountStockhold4 = accountStockhold4;
	}

	/**
	 * bankMasterCd取得.
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return this.bankMasterCd;
	}

	/**
	 * bankMasterCd設定.
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * bankMasterName取得.
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return this.bankMasterName;
	}

	/**
	 * bankMasterName設定.
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * accountDivision取得.
	 * @return accountDivision
	 */
	public java.math.BigDecimal getAccountDivision() {
		return this.accountDivision;
	}

	/**
	 * accountDivision設定.
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final java.math.BigDecimal accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNo取得.
	 * @return accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * accountNo設定.
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * transferClientCd取得.
	 * @return transferClientCd
	 */
	public String getTransferClientCd() {
		return this.transferClientCd;
	}

	/**
	 * transferClientCd設定.
	 * @param transferClientCd transferClientCd
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * transferClientName取得.
	 * @return transferClientName
	 */
	public String getTransferClientName() {
		return this.transferClientName;
	}

	/**
	 * transferClientName設定.
	 * @param transferClientName transferClientName
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
	}

	/**
	 * passwordValidTerm取得.
	 * @return passwordValidTerm
	 */
	public java.math.BigDecimal getPasswordValidTerm() {
		return this.passwordValidTerm;
	}

	/**
	 * passwordValidTerm設定.
	 * @param passwordValidTerm passwordValidTerm
	 */
	public void setPasswordValidTerm(
			final java.math.BigDecimal passwordValidTerm) {
		this.passwordValidTerm = passwordValidTerm;
	}

	/**
	 * passwordKetaLowerLimit取得.
	 * @return passwordKetaLowerLimit
	 */
	public java.math.BigDecimal getPasswordKetaLowerLimit() {
		return this.passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaLowerLimit設定.
	 * @param passwordKetaLowerLimit passwordKetaLowerLimit
	 */
	public void setPasswordKetaLowerLimit(
			final java.math.BigDecimal passwordKetaLowerLimit) {
		this.passwordKetaLowerLimit = passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaUpperLimit取得.
	 * @return passwordKetaUpperLimit
	 */
	public java.math.BigDecimal getPasswordKetaUpperLimit() {
		return this.passwordKetaUpperLimit;
	}

	/**
	 * passwordKetaUpperLimit設定.
	 * @param passwordKetaUpperLimit passwordKetaUpperLimit
	 */
	public void setPasswordKetaUpperLimit(
			final java.math.BigDecimal passwordKetaUpperLimit) {
		this.passwordKetaUpperLimit = passwordKetaUpperLimit;
	}

	/**
	 * prime取得.
	 * @return prime
	 */
	public java.math.BigDecimal getPrime() {
		return this.prime;
	}

	/**
	 * prime設定.
	 * @param prime prime
	 */
	public void setPrime(final java.math.BigDecimal prime) {
		this.prime = prime;
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

	/**
	 * newTaxRatioを取得します。
	 * @return newTaxRatio
	 */
	public BigDecimal getNewTaxRatio() {
		return newTaxRatio;
	}

	/**
	 * newTaxRatioを設定します。
	 * @param newTaxRatio newTaxRatio
	 */
	public void setNewTaxRatio(final BigDecimal newTaxRatio) {
		this.newTaxRatio = newTaxRatio;
	}

	/**
	 * bankMasterNameを取得します。
	 * @return bankMasterName
	 */
	public java.sql.Timestamp getNewTaxApllyDate() {
		return newTaxApllyDate;
	}

	/**
	 * newTaxApllyDateを設定します。
	 * @param newTaxApllyDate newTaxApllyDate
	 */
	public void setNewTaxApllyDate(final java.sql.Timestamp newTaxApllyDate) {
		this.newTaxApllyDate = newTaxApllyDate;
	}
}
