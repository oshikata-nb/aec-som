/*
 * Created on 2009/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * VenderDetailクラス.
 * @author t0011036
 */
public class VenderDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VenderDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderDivisionName */
	public static final String venderDivisionName_COLUMN = "VENDER_DIVISION_NAME";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

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

	/** COLUMNアノテーション orderFaxNo. */
	public static final String orderFaxNo_COLUMN = "ORDER_FAX_NO";

	/** COLUMNアノテーション orderFaxOutput. */
	public static final String orderFaxOutput_COLUMN = "ORDER_FAX_OUTPUT";

	/** COLUMNアノテーション orderMailAddress1. */
	public static final String orderMailAddress1_COLUMN = "ORDER_MAIL_ADDRESS1";

	/** COLUMNアノテーション orderMailAddress2. */
	public static final String orderMailAddress2_COLUMN = "ORDER_MAIL_ADDRESS2";

	/** COLUMNアノテーション orderMailAddress3. */
	public static final String orderMailAddress3_COLUMN = "ORDER_MAIL_ADDRESS3";

	/** COLUMNアノテーション orderMailOutput. */
	public static final String orderMailOutput_COLUMN = "ORDER_MAIL_OUTPUT";

	/** COLUMNアノテーション salesFaxNo. */
	public static final String salesFaxNo_COLUMN = "SALES_FAX_NO";

	/** COLUMNアノテーション salesFaxOutput. */
	public static final String salesFaxOutput_COLUMN = "SALES_FAX_OUTPUT";

	/** COLUMNアノテーション salesMailAddress1. */
	public static final String salesMailAddress1_COLUMN = "SALES_MAIL_ADDRESS1";

	/** COLUMNアノテーション salesMailAddress2. */
	public static final String salesMailAddress2_COLUMN = "SALES_MAIL_ADDRESS2";

	/** COLUMNアノテーション salesMailAddress3. */
	public static final String salesMailAddress3_COLUMN = "SALES_MAIL_ADDRESS3";

	/** COLUMNアノテーション salesMailOutput. */
	public static final String salesMailOutput_COLUMN = "SALES_MAIL_OUTPUT";

	/** COLUMNアノテーション representRole */
	public static final String representRole_COLUMN = "REPRESENT_ROLE";

	/** COLUMNアノテーション representPerson */
	public static final String representPerson_COLUMN = "REPRESENT_PERSON";

	/** COLUMNアノテーション closingDate */
	public static final String closingDate_COLUMN = "CLOSING_DATE";

	/** COLUMNアノテーション boundAmount3 */
	public static final String boundAmount3_COLUMN = "BOUND_AMOUNT3";

	/** COLUMNアノテーション boundAmount4 */
	public static final String boundAmount4_COLUMN = "BOUND_AMOUNT4";

	/** COLUMNアノテーション noteSight3 */
	public static final String noteSight3_COLUMN = "NOTE_SIGHT3";

	/** COLUMNアノテーション noteSight4 */
	public static final String noteSight4_COLUMN = "NOTE_SIGHT4";

	/** COLUMNアノテーション noteSight5 */
	public static final String noteSight5_COLUMN = "NOTE_SIGHT5";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String paymentInvoiceCd_COLUMN = "PAYMENT_INVOICE_CD";

	/** COLUMNアノテーション paymentInvoiceShortName */
	public static final String paymentInvoiceShortName_COLUMN = "PAYMENT_INVOICE_SHORT_NAME";

	/** COLUMNアノテーション paymentInvoiceName */
	public static final String paymentInvoiceName_COLUMN = "PAYMENT_INVOICE_NAME";

	/** COLUMNアノテーション creditLimitPrice */
	public static final String creditLimitPrice_COLUMN = "CREDIT_LIMIT_PRICE";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション tantoNm */
	public static final String tantoNm_COLUMN = "TANTO_NM";

	/** COLUMNアノテーション areaCd */
	public static final String areaCd_COLUMN = "AREA_CD";

	/** COLUMNアノテーション areaName */
	public static final String areaName_COLUMN = "AREA_NAME";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション roundup */
	public static final String roundup_COLUMN = "ROUNDUP";

	/** COLUMNアノテーション roundupUnit */
	public static final String roundupUnit_COLUMN = "ROUNDUP_UNIT";

	/** COLUMNアノテーション salesPurchaseRoundup */
	public static final String salesPurchaseRoundup_COLUMN = "SALES_PURCHASE_ROUNDUP";

	/** COLUMNアノテーション salesPurchaseRoundupUnit */
	public static final String salesPurchaseRoundupUnit_COLUMN = "SALES_PURCHASE_ROUNDUP_UNIT";

	/** COLUMNアノテーション unitpriceRoundup */
	public static final String unitpriceRoundup_COLUMN = "UNITPRICE_ROUNDUP";

	/** COLUMNアノテーション unitpriceRoundupUnit */
	public static final String unitpriceRoundupUnit_COLUMN = "UNITPRICE_ROUNDUP_UNIT";

	/** COLUMNアノテーション bankCd */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** COLUMNアノテーション bankName */
	public static final String bankName_COLUMN = "BANK_NAME";

	/** COLUMNアノテーション branchName */
	public static final String branchName_COLUMN = "BRANCH_NAME";

	/** COLUMNアノテーション accountDivision */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** COLUMNアノテーション accountDivisionName */
	public static final String accountDivisionName_COLUMN = "ACCOUNT_DIVISION_NAME";

	/** COLUMNアノテーション accountNo */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** COLUMNアノテーション accountStockhold */
	public static final String accountStockhold_COLUMN = "ACCOUNT_STOCKHOLD";

	/** COLUMNアノテーション customerTantoName1 */
	public static final String customerTantoName1_COLUMN = "CUSTOMER_TANTO_NAME1";

	/** COLUMNアノテーション customerTantoName2 */
	public static final String customerTantoName2_COLUMN = "CUSTOMER_TANTO_NAME2";

	/** COLUMNアノテーション customerImpression1 */
	public static final String customerImpression1_COLUMN = "CUSTOMER_IMPRESSION1";

	/** COLUMNアノテーション customerImpression2 */
	public static final String customerImpression2_COLUMN = "CUSTOMER_IMPRESSION2";

	/** COLUMNアノテーション billPublish */
	public static final String billPublish_COLUMN = "BILL_PUBLISH";

	/** COLUMNアノテーション slipPublish */
	public static final String slipPublish_COLUMN = "SLIP_PUBLISH";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsName */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	/** COLUMNアノテーション cityCd */
	public static final String cityCd_COLUMN = "CITY_CD";

	/** COLUMNアノテーション subcontractLaw */
	public static final String subcontractLaw_COLUMN = "SUBCONTRACT_LAW";

	/** COLUMNアノテーション calendarCd */
	public static final String calendarCd_COLUMN = "CALENDAR_CD";

	/** COLUMNアノテーション calName */
	public static final String calName_COLUMN = "CAL_NAME";

	/** COLUMNアノテーション accountCd */
	public static final String accountCd_COLUMN = "ACCOUNT_CD";

	/** COLUMNアノテーション otherBankCd */
	public static final String otherBankCd_COLUMN = "OTHER_BANK_CD";

	/** COLUMNアノテーション otherBankName */
	public static final String otherBankName_COLUMN = "OTHER_BANK_NAME";

	/** COLUMNアノテーション otherBranchName */
	public static final String otherBranchName_COLUMN = "OTHER_BRANCH_NAME";

	/** COLUMNアノテーション otherAccountDivision */
	public static final String otherAccountDivision_COLUMN = "OTHER_ACCOUNT_DIVISION";

	/** COLUMNアノテーション otherAccountNo */
	public static final String otherAccountNo_COLUMN = "OTHER_ACCOUNT_NO";

	/** COLUMNアノテーション otherAccountStockhold */
	public static final String otherAccountStockhold_COLUMN = "OTHER_ACCOUNT_STOCKHOLD";

	/** COLUMNアノテーション taxRoundup */
	public static final String taxRoundup_COLUMN = "TAX_ROUNDUP";

	/** COLUMNアノテーション taxRoundupUnit */
	public static final String taxRoundupUnit_COLUMN = "TAX_ROUNDUP_UNIT";

	/** COLUMNアノテーション transferCommissionLoad */
	public static final String transferCommissionLoad_COLUMN = "TRANSFER_COMMISSION_LOAD";

	/** COLUMNアノテーション remarks */
	public static final String remarks_COLUMN = "REMARKS";

	/** COLUMNアノテーション advanceDivision */
	public static final String advanceDivision_COLUMN = "ADVANCE_DIVISION";

	/** COLUMNアノテーション creditMonthDivision1 */
	public static final String creditMonthDivision1_COLUMN = "CREDIT_MONTH_DIVISION1";

	/** COLUMNアノテーション creditMonthDivision2 */
	public static final String creditMonthDivision2_COLUMN = "CREDIT_MONTH_DIVISION2";

	/** COLUMNアノテーション creditMonthDivision3 */
	public static final String creditMonthDivision3_COLUMN = "CREDIT_MONTH_DIVISION3";

	/** COLUMNアノテーション creditScheduledDate1 */
	public static final String creditScheduledDate1_COLUMN = "CREDIT_SCHEDULED_DATE1";

	/** COLUMNアノテーション creditScheduledDate2 */
	public static final String creditScheduledDate2_COLUMN = "CREDIT_SCHEDULED_DATE2";

	/** COLUMNアノテーション creditScheduledDate3 */
	public static final String creditScheduledDate3_COLUMN = "CREDIT_SCHEDULED_DATE3";

	/** COLUMNアノテーション creditDivision3 */
	public static final String creditDivision3_COLUMN = "CREDIT_DIVISION3";

	/** COLUMNアノテーション creditDivision4 */
	public static final String creditDivision4_COLUMN = "CREDIT_DIVISION4";

	/** COLUMNアノテーション creditDivision5 */
	public static final String creditDivision5_COLUMN = "CREDIT_DIVISION5";

	/** COLUMNアノテーション purchaseDiscountDays1 */
	public static final String purchaseDiscountDays1_COLUMN = "PURCHASE_DISCOUNT_DAYS1";

	/** COLUMNアノテーション purchaseDiscountDays2 */
	public static final String purchaseDiscountDays2_COLUMN = "PURCHASE_DISCOUNT_DAYS2";

	/** COLUMNアノテーション purchaseDiscountDays3 */
	public static final String purchaseDiscountDays3_COLUMN = "PURCHASE_DISCOUNT_DAYS3";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション holidayFlg */
	public static final String holidayFlg_COLUMN = "HOLIDAY_FLG";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション faxOutput */
	public static final String faxOutput_COLUMN = "FAX_OUTPUT";

	/** COLUMNアノテーション slipFaxNo */
	public static final String slipFaxNo_COLUMN = "SLIP_FAX_NO";

	/** COLUMNアノテーション mailOrganizationCd */
	public static final String mailOrganizationCd_COLUMN = "MAIL_ORGANIZATION_CD";

	/** COLUMNアノテーション mailOrganizationName */
	public static final String mailOrganizationName_COLUMN = "MAIL_ORGANIZATION_NAME";

	/** COLUMNアノテーション tsInvoicePtn */
	public static final String tsInvoicePtn_COLUMN = "TS_INVOICE_PTN";

	/** COLUMNアノテーション siInvoicePtn */
	public static final String siInvoicePtn_COLUMN = "SI_INVOICE_PTN";

	/** COLUMNアノテーション invoiceNo */
	public static final String invoiceNo_COLUMN = "INVOICE_NO";

	//
	// インスタンスフィールド
	//

	private String venderDivision;

	private String venderDivisionName;

	private String venderCd;

	private String venderName1;

	private String venderName2;

	private String venderShortedName;

	private String zipcodeNo;

	private String address1;

	private String address2;

	private String address3;

	private String telNo;

	private String faxNo;

	private String orderFaxNo;

	private java.math.BigDecimal orderFaxOutput;

	private String orderMailAddress1;

	private String orderMailAddress2;

	private String orderMailAddress3;

	private java.math.BigDecimal orderMailOutput;

	private String salesFaxNo;

	private java.math.BigDecimal salesFaxOutput;

	private String salesMailAddress1;

	private String salesMailAddress2;

	private String salesMailAddress3;

	private java.math.BigDecimal salesMailOutput;

	private String representRole;

	private String representPerson;

	private java.math.BigDecimal closingDate;

	private java.math.BigDecimal boundAmount3;

	private java.math.BigDecimal boundAmount4;

	private java.math.BigDecimal noteSight3;

	private java.math.BigDecimal noteSight4;

	private java.math.BigDecimal noteSight5;

	private String paymentInvoiceCd;

	private String paymentInvoiceShortName;

	private String paymentInvoiceName;

	private java.math.BigDecimal creditLimitPrice;

	private String tantoCd;

	private String tantoNm;

	private String areaCd;

	private String areaName;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal calcDivision;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal roundup;

	private java.math.BigDecimal roundupUnit;

	private java.math.BigDecimal salesPurchaseRoundup;

	private java.math.BigDecimal salesPurchaseRoundupUnit;

	private java.math.BigDecimal unitpriceRoundup;

	private java.math.BigDecimal unitpriceRoundupUnit;

	private String bankCd;

	private String bankName;

	private String branchName;

	private java.math.BigDecimal accountDivision;

	private String accountDivisionName;

	private String accountNo;

	private String accountStockhold;

	private String customerTantoName1;

	private String customerTantoName2;

	private String customerImpression1;

	private String customerImpression2;

	private java.math.BigDecimal billPublish;

	private java.math.BigDecimal slipPublish;

	private String sectionCd;

	private String sectionName;

	private String accountsCd;

	private String accountsName;

	private String cityCd;

	private java.math.BigDecimal subcontractLaw;

	private String calendarCd;

	private String calName;

	private String accountCd;

	private String otherBankCd;

	private String otherBankName;

	private String otherBranchName;

	private java.math.BigDecimal otherAccountDivision;

	private String otherAccountNo;

	private String otherAccountStockhold;

	private java.math.BigDecimal taxRoundup;

	private java.math.BigDecimal taxRoundupUnit;

	private java.math.BigDecimal transferCommissionLoad;

	private String remarks;

	private java.math.BigDecimal advanceDivision;

	private java.math.BigDecimal creditMonthDivision1;

	private java.math.BigDecimal creditMonthDivision2;

	private java.math.BigDecimal creditMonthDivision3;

	private java.math.BigDecimal creditScheduledDate1;

	private java.math.BigDecimal creditScheduledDate2;

	private java.math.BigDecimal creditScheduledDate3;

	private java.math.BigDecimal creditDivision3;

	private java.math.BigDecimal creditDivision4;

	private java.math.BigDecimal creditDivision5;

	private java.math.BigDecimal purchaseDiscountDays1;

	private java.math.BigDecimal purchaseDiscountDays2;

	private java.math.BigDecimal purchaseDiscountDays3;

	private String organizationCd;

	private String organizationName;

	private java.math.BigDecimal holidayFlg;

	private java.sql.Timestamp updateDate;

	private java.math.BigDecimal faxOutput;

	private String slipFaxNo;

	private String mailOrganizationCd;

	private String mailOrganizationName;

	private String tsInvoicePtn;

	private String siInvoicePtn;

	private String invoiceNo;

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
	 * venderDivisionName取得.
	 * @return venderDivisionName
	 */
	public String getVenderDivisionName() {
		return this.venderDivisionName;
	}

	/**
	 * venderDivisionName設定.
	 * @param venderDivisionName venderDivisionName
	 */
	public void setVenderDivisionName(final String venderDivisionName) {
		this.venderDivisionName = venderDivisionName;
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
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * venderShortedName取得.
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return this.venderShortedName;
	}

	/**
	 * venderShortedName設定.
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
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
	 * orderFaxNoを取得します。
	 * @return orderFaxNo
	 */
	public String getOrderFaxNo() {
		return orderFaxNo;
	}

	/**
	 * orderFaxNoを設定します。
	 * @param orderFaxNo orderFaxNo
	 */
	public void setOrderFaxNo(String orderFaxNo) {
		this.orderFaxNo = orderFaxNo;
	}

	/**
	 * orderFaxOutputを取得します。
	 * @return orderFaxOutput
	 */
	public java.math.BigDecimal getOrderFaxOutput() {
		return orderFaxOutput;
	}

	/**
	 * orderFaxOutputを設定します。
	 * @param orderFaxOutput orderFaxOutput
	 */
	public void setOrderFaxOutput(java.math.BigDecimal orderFaxOutput) {
		this.orderFaxOutput = orderFaxOutput;
	}

	/**
	 * orderMailAddress1を取得します。
	 * @return orderMailAddress1
	 */
	public String getOrderMailAddress1() {
		return orderMailAddress1;
	}

	/**
	 * orderMailAddress1を設定します。
	 * @param orderMailAddress1 orderMailAddress1
	 */
	public void setOrderMailAddress1(String orderMailAddress1) {
		this.orderMailAddress1 = orderMailAddress1;
	}

	/**
	 * orderMailAddress2を取得します。
	 * @return orderMailAddress2
	 */
	public String getOrderMailAddress2() {
		return orderMailAddress2;
	}

	/**
	 * orderMailAddress2を設定します。
	 * @param orderMailAddress2 orderMailAddress2
	 */
	public void setOrderMailAddress2(String orderMailAddress2) {
		this.orderMailAddress2 = orderMailAddress2;
	}

	/**
	 * orderMailAddress3を取得します。
	 * @return orderMailAddress3
	 */
	public String getOrderMailAddress3() {
		return orderMailAddress3;
	}

	/**
	 * orderMailAddress3を設定します。
	 * @param orderMailAddress3 orderMailAddress3
	 */
	public void setOrderMailAddress3(String orderMailAddress3) {
		this.orderMailAddress3 = orderMailAddress3;
	}

	/**
	 * orderMailOutputを取得します。
	 * @return orderMailOutput
	 */
	public java.math.BigDecimal getOrderMailOutput() {
		return orderMailOutput;
	}

	/**
	 * orderMailOutputを設定します。
	 * @param orderMailOutput orderMailOutput
	 */
	public void setOrderMailOutput(java.math.BigDecimal orderMailOutput) {
		this.orderMailOutput = orderMailOutput;
	}

	/**
	 * salesFaxNoを取得します。
	 * @return salesFaxNo
	 */
	public String getSalesFaxNo() {
		return salesFaxNo;
	}

	/**
	 * salesFaxNoを設定します。
	 * @param salesFaxNo salesFaxNo
	 */
	public void setSalesFaxNo(String salesFaxNo) {
		this.salesFaxNo = salesFaxNo;
	}

	/**
	 * salesFaxOutputを取得します。
	 * @return salesFaxOutput
	 */
	public java.math.BigDecimal getSalesFaxOutput() {
		return salesFaxOutput;
	}

	/**
	 * salesFaxOutputを設定します。
	 * @param salesFaxOutput salesFaxOutput
	 */
	public void setSalesFaxOutput(java.math.BigDecimal salesFaxOutput) {
		this.salesFaxOutput = salesFaxOutput;
	}

	/**
	 * salesMailAddress1を取得します。
	 * @return salesMailAddress1
	 */
	public String getSalesMailAddress1() {
		return salesMailAddress1;
	}

	/**
	 * salesMailAddress1を設定します。
	 * @param salesMailAddress1 salesMailAddress1
	 */
	public void setSalesMailAddress1(String salesMailAddress1) {
		this.salesMailAddress1 = salesMailAddress1;
	}

	/**
	 * salesMailAddress2を取得します。
	 * @return salesMailAddress2
	 */
	public String getSalesMailAddress2() {
		return salesMailAddress2;
	}

	/**
	 * salesMailAddress2を設定します。
	 * @param salesMailAddress2 salesMailAddress2
	 */
	public void setSalesMailAddress2(String salesMailAddress2) {
		this.salesMailAddress2 = salesMailAddress2;
	}

	/**
	 * salesMailAddress3を取得します。
	 * @return salesMailAddress3
	 */
	public String getSalesMailAddress3() {
		return salesMailAddress3;
	}

	/**
	 * salesMailAddress3を設定します。
	 * @param salesMailAddress3 salesMailAddress3
	 */
	public void setSalesMailAddress3(String salesMailAddress3) {
		this.salesMailAddress3 = salesMailAddress3;
	}

	/**
	 * salesMailOutputを取得します。
	 * @return salesMailOutput
	 */
	public java.math.BigDecimal getSalesMailOutput() {
		return salesMailOutput;
	}

	/**
	 * salesMailOutputを設定します。
	 * @param salesMailOutput salesMailOutput
	 */
	public void setSalesMailOutput(java.math.BigDecimal salesMailOutput) {
		this.salesMailOutput = salesMailOutput;
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
	 * closingDate取得.
	 * @return closingDate
	 */
	public java.math.BigDecimal getClosingDate() {
		return this.closingDate;
	}

	/**
	 * closingDate設定.
	 * @param closingDate closingDate
	 */
	public void setClosingDate(final java.math.BigDecimal closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * boundAmount3取得.
	 * @return boundAmount3
	 */
	public java.math.BigDecimal getBoundAmount3() {
		return this.boundAmount3;
	}

	/**
	 * boundAmount3設定.
	 * @param boundAmount3 boundAmount3
	 */
	public void setBoundAmount3(final java.math.BigDecimal boundAmount3) {
		this.boundAmount3 = boundAmount3;
	}

	/**
	 * boundAmount4取得.
	 * @return boundAmount4
	 */
	public java.math.BigDecimal getBoundAmount4() {
		return this.boundAmount4;
	}

	/**
	 * boundAmount4設定.
	 * @param boundAmount4 boundAmount4
	 */
	public void setBoundAmount4(final java.math.BigDecimal boundAmount4) {
		this.boundAmount4 = boundAmount4;
	}

	/**
	 * noteSight3取得.
	 * @return noteSight3
	 */
	public java.math.BigDecimal getNoteSight3() {
		return this.noteSight3;
	}

	/**
	 * noteSight3設定.
	 * @param noteSight3 noteSight3
	 */
	public void setNoteSight3(final java.math.BigDecimal noteSight3) {
		this.noteSight3 = noteSight3;
	}

	/**
	 * noteSight4取得.
	 * @return noteSight4
	 */
	public java.math.BigDecimal getNoteSight4() {
		return this.noteSight4;
	}

	/**
	 * noteSight4設定.
	 * @param noteSight4 noteSight4
	 */
	public void setNoteSight4(final java.math.BigDecimal noteSight4) {
		this.noteSight4 = noteSight4;
	}

	/**
	 * noteSight5取得.
	 * @return noteSight5
	 */
	public java.math.BigDecimal getNoteSight5() {
		return this.noteSight5;
	}

	/**
	 * noteSight5設定.
	 * @param noteSight5 noteSight5
	 */
	public void setNoteSight5(final java.math.BigDecimal noteSight5) {
		this.noteSight5 = noteSight5;
	}

	/**
	 * paymentInvoiceCd取得.
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return this.paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCd設定.
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceShortName取得.
	 * @return paymentInvoiceShortName
	 */
	public String getPaymentInvoiceShortName() {
		return paymentInvoiceShortName;
	}

	/**
	 * paymentInvoiceShortName設定.
	 * @param paymentInvoiceShortName paymentInvoiceShortName
	 */
	public void setPaymentInvoiceShortName(final String paymentInvoiceShortName) {
		this.paymentInvoiceShortName = paymentInvoiceShortName;
	}

	/**
	 * paymentInvoiceName取得.
	 * @return paymentInvoiceName
	 */
	public String getPaymentInvoiceName() {
		return this.paymentInvoiceName;
	}

	/**
	 * paymentInvoiceName設定.
	 * @param paymentInvoiceName paymentInvoiceName
	 */
	public void setPaymentInvoiceName(final String paymentInvoiceName) {
		this.paymentInvoiceName = paymentInvoiceName;
	}

	/**
	 * creditLimitPrice取得.
	 * @return creditLimitPrice
	 */
	public java.math.BigDecimal getCreditLimitPrice() {
		return this.creditLimitPrice;
	}

	/**
	 * creditLimitPrice設定.
	 * @param creditLimitPrice creditLimitPrice
	 */
	public void setCreditLimitPrice(final java.math.BigDecimal creditLimitPrice) {
		this.creditLimitPrice = creditLimitPrice;
	}

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * tantoNm取得.
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return this.tantoNm;
	}

	/**
	 * tantoNm設定.
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * areaCd取得.
	 * @return areaCd
	 */
	public String getAreaCd() {
		return this.areaCd;
	}

	/**
	 * areaCd設定.
	 * @param areaCd areaCd
	 */
	public void setAreaCd(final String areaCd) {
		this.areaCd = areaCd;
	}

	/**
	 * areaName取得.
	 * @return areaName
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * areaName設定.
	 * @param areaName areaName
	 */
	public void setAreaName(final String areaName) {
		this.areaName = areaName;
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
	 * salesPurchaseRoundup取得.
	 * @return salesPurchaseRoundup
	 */
	public java.math.BigDecimal getSalesPurchaseRoundup() {
		return this.salesPurchaseRoundup;
	}

	/**
	 * salesPurchaseRoundup設定.
	 * @param salesPurchaseRoundup salesPurchaseRoundup
	 */
	public void setSalesPurchaseRoundup(
			final java.math.BigDecimal salesPurchaseRoundup) {
		this.salesPurchaseRoundup = salesPurchaseRoundup;
	}

	/**
	 * salesPurchaseRoundupUnit取得.
	 * @return salesPurchaseRoundupUnit
	 */
	public java.math.BigDecimal getSalesPurchaseRoundupUnit() {
		return this.salesPurchaseRoundupUnit;
	}

	/**
	 * salesPurchaseRoundupUnit設定.
	 * @param salesPurchaseRoundupUnit salesPurchaseRoundupUnit
	 */
	public void setSalesPurchaseRoundupUnit(
			final java.math.BigDecimal salesPurchaseRoundupUnit) {
		this.salesPurchaseRoundupUnit = salesPurchaseRoundupUnit;
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
	 * bankCd取得.
	 * @return bankCd
	 */
	public String getBankCd() {
		return this.bankCd;
	}

	/**
	 * bankCd設定.
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * bankName取得.
	 * @return bankName
	 */
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * bankName設定.
	 * @param bankName bankName
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	/**
	 * branchName取得.
	 * @return branchName
	 */
	public String getBranchName() {
		return this.branchName;
	}

	/**
	 * branchName設定.
	 * @param branchName branchName
	 */
	public void setBranchName(final String branchName) {
		this.branchName = branchName;
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
	 * accountDivisionName取得.
	 * @return accountDivisionName
	 */
	public String getAccountDivisionName() {
		return this.accountDivisionName;
	}

	/**
	 * accountDivisionName設定.
	 * @param accountDivisionName accountDivisionName
	 */
	public void setAccountDivisionName(final String accountDivisionName) {
		this.accountDivisionName = accountDivisionName;
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
	 * accountStockhold取得.
	 * @return accountStockhold
	 */
	public String getAccountStockhold() {
		return this.accountStockhold;
	}

	/**
	 * accountStockhold設定.
	 * @param accountStockhold accountStockhold
	 */
	public void setAccountStockhold(final String accountStockhold) {
		this.accountStockhold = accountStockhold;
	}

	/**
	 * customerTantoName1取得.
	 * @return customerTantoName1
	 */
	public String getCustomerTantoName1() {
		return this.customerTantoName1;
	}

	/**
	 * customerTantoName1設定.
	 * @param customerTantoName1 customerTantoName1
	 */
	public void setCustomerTantoName1(final String customerTantoName1) {
		this.customerTantoName1 = customerTantoName1;
	}

	/**
	 * customerTantoName2取得.
	 * @return customerTantoName2
	 */
	public String getCustomerTantoName2() {
		return this.customerTantoName2;
	}

	/**
	 * customerTantoName2設定.
	 * @param customerTantoName2 customerTantoName2
	 */
	public void setCustomerTantoName2(final String customerTantoName2) {
		this.customerTantoName2 = customerTantoName2;
	}

	/**
	 * customerImpression1取得.
	 * @return customerImpression1
	 */
	public String getCustomerImpression1() {
		return this.customerImpression1;
	}

	/**
	 * customerImpression1設定.
	 * @param customerImpression1 customerImpression1
	 */
	public void setCustomerImpression1(final String customerImpression1) {
		this.customerImpression1 = customerImpression1;
	}

	/**
	 * customerImpression2取得.
	 * @return customerImpression2
	 */
	public String getCustomerImpression2() {
		return this.customerImpression2;
	}

	/**
	 * customerImpression2設定.
	 * @param customerImpression2 customerImpression2
	 */
	public void setCustomerImpression2(final String customerImpression2) {
		this.customerImpression2 = customerImpression2;
	}

	/**
	 * billPublish取得.
	 * @return billPublish
	 */
	public java.math.BigDecimal getBillPublish() {
		return this.billPublish;
	}

	/**
	 * billPublish設定.
	 * @param billPublish billPublish
	 */
	public void setBillPublish(final java.math.BigDecimal billPublish) {
		this.billPublish = billPublish;
	}

	/**
	 * slipPublish取得.
	 * @return slipPublish
	 */
	public java.math.BigDecimal getSlipPublish() {
		return this.slipPublish;
	}

	/**
	 * slipPublish設定.
	 * @param slipPublish slipPublish
	 */
	public void setSlipPublish(final java.math.BigDecimal slipPublish) {
		this.slipPublish = slipPublish;
	}

	/**
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionName取得.
	 * @return sectionName
	 */
	public String getSectionName() {
		return this.sectionName;
	}

	/**
	 * sectionName設定.
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * accountsCd取得.
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return this.accountsCd;
	}

	/**
	 * accountsCd設定.
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsName取得.
	 * @return accountsName
	 */
	public String getAccountsName() {
		return this.accountsName;
	}

	/**
	 * accountsName設定.
	 * @param accountsName accountsName
	 */
	public void setAccountsName(final String accountsName) {
		this.accountsName = accountsName;
	}

	/**
	 * cityCd取得.
	 * @return cityCd
	 */
	public String getCityCd() {
		return this.cityCd;
	}

	/**
	 * cityCd設定.
	 * @param cityCd cityCd
	 */
	public void setCityCd(final String cityCd) {
		this.cityCd = cityCd;
	}

	/**
	 * subcontractLaw取得.
	 * @return subcontractLaw
	 */
	public java.math.BigDecimal getSubcontractLaw() {
		return this.subcontractLaw;
	}

	/**
	 * subcontractLaw設定.
	 * @param subcontractLaw subcontractLaw
	 */
	public void setSubcontractLaw(final java.math.BigDecimal subcontractLaw) {
		this.subcontractLaw = subcontractLaw;
	}

	/**
	 * calendarCd取得.
	 * @return calendarCd
	 */
	public String getCalendarCd() {
		return this.calendarCd;
	}

	/**
	 * calendarCd設定.
	 * @param calendarCd calendarCd
	 */
	public void setCalendarCd(final String calendarCd) {
		this.calendarCd = calendarCd;
	}

	/**
	 * calName取得.
	 * @return calName
	 */
	public String getCalName() {
		return this.calName;
	}

	/**
	 * calName設定.
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
	}

	/**
	 * accountCd取得.
	 * @return accountCd
	 */
	public String getAccountCd() {
		return this.accountCd;
	}

	/**
	 * accountCd設定.
	 * @param accountCd accountCd
	 */
	public void setAccountCd(final String accountCd) {
		this.accountCd = accountCd;
	}

	/**
	 * otherBankCd取得.
	 * @return otherBankCd
	 */
	public String getOtherBankCd() {
		return this.otherBankCd;
	}

	/**
	 * otherBankCd設定.
	 * @param otherBankCd otherBankCd
	 */
	public void setOtherBankCd(final String otherBankCd) {
		this.otherBankCd = otherBankCd;
	}

	/**
	 * otherBankName取得.
	 * @return otherBankName
	 */
	public String getOtherBankName() {
		return this.otherBankName;
	}

	/**
	 * otherBankName設定.
	 * @param otherBankName otherBankName
	 */
	public void setOtherBankName(final String otherBankName) {
		this.otherBankName = otherBankName;
	}

	/**
	 * otherBranchName取得.
	 * @return otherBranchName
	 */
	public String getOtherBranchName() {
		return this.otherBranchName;
	}

	/**
	 * otherBranchName設定.
	 * @param otherBranchName otherBranchName
	 */
	public void setOtherBranchName(final String otherBranchName) {
		this.otherBranchName = otherBranchName;
	}

	/**
	 * otherAccountDivision取得.
	 * @return otherAccountDivision
	 */
	public java.math.BigDecimal getOtherAccountDivision() {
		return this.otherAccountDivision;
	}

	/**
	 * otherAccountDivision設定.
	 * @param otherAccountDivision otherAccountDivision
	 */
	public void setOtherAccountDivision(
			final java.math.BigDecimal otherAccountDivision) {
		this.otherAccountDivision = otherAccountDivision;
	}

	/**
	 * otherAccountNo取得.
	 * @return otherAccountNo
	 */
	public String getOtherAccountNo() {
		return this.otherAccountNo;
	}

	/**
	 * otherAccountNo設定.
	 * @param otherAccountNo otherAccountNo
	 */
	public void setOtherAccountNo(final String otherAccountNo) {
		this.otherAccountNo = otherAccountNo;
	}

	/**
	 * otherAccountStockhold取得.
	 * @return otherAccountStockhold
	 */
	public String getOtherAccountStockhold() {
		return this.otherAccountStockhold;
	}

	/**
	 * otherAccountStockhold設定.
	 * @param otherAccountStockhold otherAccountStockhold
	 */
	public void setOtherAccountStockhold(final String otherAccountStockhold) {
		this.otherAccountStockhold = otherAccountStockhold;
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
	 * transferCommissionLoad取得.
	 * @return transferCommissionLoad
	 */
	public java.math.BigDecimal getTransferCommissionLoad() {
		return this.transferCommissionLoad;
	}

	/**
	 * transferCommissionLoad設定.
	 * @param transferCommissionLoad transferCommissionLoad
	 */
	public void setTransferCommissionLoad(
			final java.math.BigDecimal transferCommissionLoad) {
		this.transferCommissionLoad = transferCommissionLoad;
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
	 * advanceDivision取得.
	 * @return advanceDivision
	 */
	public java.math.BigDecimal getAdvanceDivision() {
		return this.advanceDivision;
	}

	/**
	 * advanceDivision設定.
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final java.math.BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}

	/**
	 * creditMonthDivision1取得.
	 * @return creditMonthDivision1
	 */
	public java.math.BigDecimal getCreditMonthDivision1() {
		return this.creditMonthDivision1;
	}

	/**
	 * creditMonthDivision1設定.
	 * @param creditMonthDivision1 creditMonthDivision1
	 */
	public void setCreditMonthDivision1(
			final java.math.BigDecimal creditMonthDivision1) {
		this.creditMonthDivision1 = creditMonthDivision1;
	}

	/**
	 * creditMonthDivision2取得.
	 * @return creditMonthDivision2
	 */
	public java.math.BigDecimal getCreditMonthDivision2() {
		return this.creditMonthDivision2;
	}

	/**
	 * creditMonthDivision2設定.
	 * @param creditMonthDivision2 creditMonthDivision2
	 */
	public void setCreditMonthDivision2(
			final java.math.BigDecimal creditMonthDivision2) {
		this.creditMonthDivision2 = creditMonthDivision2;
	}

	/**
	 * creditMonthDivision3取得.
	 * @return creditMonthDivision3
	 */
	public java.math.BigDecimal getCreditMonthDivision3() {
		return this.creditMonthDivision3;
	}

	/**
	 * creditMonthDivision3設定.
	 * @param creditMonthDivision3 creditMonthDivision3
	 */
	public void setCreditMonthDivision3(
			final java.math.BigDecimal creditMonthDivision3) {
		this.creditMonthDivision3 = creditMonthDivision3;
	}

	/**
	 * creditScheduledDate1取得.
	 * @return creditScheduledDate1
	 */
	public java.math.BigDecimal getCreditScheduledDate1() {
		return this.creditScheduledDate1;
	}

	/**
	 * creditScheduledDate1設定.
	 * @param creditScheduledDate1 creditScheduledDate1
	 */
	public void setCreditScheduledDate1(
			final java.math.BigDecimal creditScheduledDate1) {
		this.creditScheduledDate1 = creditScheduledDate1;
	}

	/**
	 * creditScheduledDate2取得.
	 * @return creditScheduledDate2
	 */
	public java.math.BigDecimal getCreditScheduledDate2() {
		return this.creditScheduledDate2;
	}

	/**
	 * creditScheduledDate2設定.
	 * @param creditScheduledDate2 creditScheduledDate2
	 */
	public void setCreditScheduledDate2(
			final java.math.BigDecimal creditScheduledDate2) {
		this.creditScheduledDate2 = creditScheduledDate2;
	}

	/**
	 * creditScheduledDate3取得.
	 * @return creditScheduledDate3
	 */
	public java.math.BigDecimal getCreditScheduledDate3() {
		return this.creditScheduledDate3;
	}

	/**
	 * creditScheduledDate3設定.
	 * @param creditScheduledDate3 creditScheduledDate3
	 */
	public void setCreditScheduledDate3(
			final java.math.BigDecimal creditScheduledDate3) {
		this.creditScheduledDate3 = creditScheduledDate3;
	}

	/**
	 * creditDivision3取得.
	 * @return creditDivision3
	 */
	public java.math.BigDecimal getCreditDivision3() {
		return this.creditDivision3;
	}

	/**
	 * creditDivision3設定.
	 * @param creditDivision3 creditDivision3
	 */
	public void setCreditDivision3(final java.math.BigDecimal creditDivision3) {
		this.creditDivision3 = creditDivision3;
	}

	/**
	 * creditDivision4取得.
	 * @return creditDivision4
	 */
	public java.math.BigDecimal getCreditDivision4() {
		return this.creditDivision4;
	}

	/**
	 * creditDivision4設定.
	 * @param creditDivision4 creditDivision4
	 */
	public void setCreditDivision4(final java.math.BigDecimal creditDivision4) {
		this.creditDivision4 = creditDivision4;
	}

	/**
	 * creditDivision5取得.
	 * @return creditDivision5
	 */
	public java.math.BigDecimal getCreditDivision5() {
		return this.creditDivision5;
	}

	/**
	 * creditDivision5設定.
	 * @param creditDivision5 creditDivision5
	 */
	public void setCreditDivision5(final java.math.BigDecimal creditDivision5) {
		this.creditDivision5 = creditDivision5;
	}

	/**
	 * purchaseDiscountDays1取得.
	 * @return purchaseDiscountDays1
	 */
	public java.math.BigDecimal getPurchaseDiscountDays1() {
		return this.purchaseDiscountDays1;
	}

	/**
	 * purchaseDiscountDays1設定.
	 * @param purchaseDiscountDays1 purchaseDiscountDays1
	 */
	public void setPurchaseDiscountDays1(
			final java.math.BigDecimal purchaseDiscountDays1) {
		this.purchaseDiscountDays1 = purchaseDiscountDays1;
	}

	/**
	 * purchaseDiscountDays2取得.
	 * @return purchaseDiscountDays2
	 */
	public java.math.BigDecimal getPurchaseDiscountDays2() {
		return this.purchaseDiscountDays2;
	}

	/**
	 * purchaseDiscountDays2設定.
	 * @param purchaseDiscountDays2 purchaseDiscountDays2
	 */
	public void setPurchaseDiscountDays2(
			final java.math.BigDecimal purchaseDiscountDays2) {
		this.purchaseDiscountDays2 = purchaseDiscountDays2;
	}

	/**
	 * purchaseDiscountDays3取得.
	 * @return purchaseDiscountDays3
	 */
	public java.math.BigDecimal getPurchaseDiscountDays3() {
		return this.purchaseDiscountDays3;
	}

	/**
	 * purchaseDiscountDays3設定.
	 * @param purchaseDiscountDays3 purchaseDiscountDays3
	 */
	public void setPurchaseDiscountDays3(
			final java.math.BigDecimal purchaseDiscountDays3) {
		this.purchaseDiscountDays3 = purchaseDiscountDays3;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * holidayFlg取得.
	 * @return holidayFlg
	 */
	public java.math.BigDecimal getHolidayFlg() {
		return this.holidayFlg;
	}

	/**
	 * holidayFlg設定.
	 * @param holidayFlg holidayFlg
	 */
	public void setHolidayFlg(final java.math.BigDecimal holidayFlg) {
		this.holidayFlg = holidayFlg;
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
	 * faxOutputを取得します。
	 * @return faxOutput
	 */
	public java.math.BigDecimal getFaxOutput() {
		return faxOutput;
	}

	/**
	 * faxOutputを設定します。
	 * @param faxOutput faxOutput
	 */
	public void setFaxOutput(final java.math.BigDecimal faxOutput) {
		this.faxOutput = faxOutput;
	}

	/**
	 * slipFaxNoを取得します。
	 * @return slipFaxNo
	 */
	public String getSlipFaxNo() {
		return slipFaxNo;
	}

	/**
	 * slipFaxNoを設定します。
	 * @param slipFaxNo slipFaxNo
	 */
	public void setSlipFaxNo(final String slipFaxNo) {
		this.slipFaxNo = slipFaxNo;
	}

	/**
	 * mailOrganizationCdを取得します。
	 * @return mailOrganizationCd
	 */
	public String getMailOrganizationCd() {
		return mailOrganizationCd;
	}

	/**
	 * mailOrganizationCdを設定します。
	 * @param mailOrganizationCd mailOrganizationCd
	 */
	public void setMailOrganizationCd(final String mailOrganizationCd) {
		this.mailOrganizationCd = mailOrganizationCd;
	}

	/**
	 * mailOrganizationNameを取得します。
	 * @return mailOrganizationName
	 */
	public String getMailOrganizationName() {
		return mailOrganizationName;
	}

	/**
	 * mailOrganizationNameを設定します。
	 * @param mailOrganizationName mailOrganizationName
	 */
	public void setMailOrganizationName(final String mailOrganizationName) {
		this.mailOrganizationName = mailOrganizationName;
	}

	/**
	 * tsInvoicePtnを取得します。
	 * @return tsInvoicePtn
	 */
	public String getTsInvoicePtn() {
		return tsInvoicePtn;
	}

	/**
	 * tsInvoicePtnを設定します。
	 * @param tsInvoicePtn tsInvoicePtn
	 */
	public void setTsInvoicePtn(String tsInvoicePtn) {
		this.tsInvoicePtn = tsInvoicePtn;
	}

	/**
	 * siInvoicePtnを取得します。
	 * @return siInvoicePtn
	 */
	public String getSiInvoicePtn() {
		return siInvoicePtn;
	}

	/**
	 * siInvoicePtnを設定します。
	 * @param siInvoicePtn siInvoicePtn
	 */
	public void setSiInvoicePtn(String siInvoicePtn) {
		this.siInvoicePtn = siInvoicePtn;
	}

	/**
	 * invoiceNoを取得します。
	 * @return invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * invoiceNoを設定します。
	 * @param invoiceNo invoiceNo
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
}
