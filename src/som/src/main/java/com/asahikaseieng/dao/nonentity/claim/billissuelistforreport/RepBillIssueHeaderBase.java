/*
 * Created on 2015/05/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.billissuelistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepBillIssueHeaderクラス.
 * @author Administrator
 */
public class RepBillIssueHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepBillIssueHeaderBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/*  */
	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/*  */
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/*  */
	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/*  */
	/** COLUMNアノテーション creditScheduledDate */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/*  */
	/** COLUMNアノテーション creditDivision */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/*  */
	/** COLUMNアノテーション noteSight */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/*  */
	/** COLUMNアノテーション holidayFlg */
	public static final String holidayFlg_COLUMN = "HOLIDAY_FLG";

	/*  */
	/** COLUMNアノテーション claimAmountForward */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション creditAmountForward */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション otherCreditAmountForward */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/*  */
	/** COLUMNアノテーション balanceForward */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/*  */
	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション norml0SalesAmount */
	public static final String norml0SalesAmount_COLUMN = "NORMAL_0_SALES_AMOUNT";

	/** COLUMNアノテーション normal8SalesAmount */
	public static final String normal8SalesAmount_COLUMN = "NORMAL_8_SALES_AMOUNT";

	/** COLUMNアノテーション normal10SalesAmount */
	public static final String normal10SalesAmount_COLUMN = "NORMAL_10_SALES_AMOUNT";

	/** COLUMNアノテーション keigen8SalesAmount */
	public static final String Keigen8SalesAmount_COLUMN = "KEIGEN_8_SALES_AMOUNT";

	/** COLUMNアノテーション goukeiSalesAmount */
	public static final String goukeiSalesAmount_COLUMN = "GOUKEI_SALES_AMOUNT";

	/*  */
	/** COLUMNアノテーション salesReturnedAmount */
	public static final String salesReturnedAmount_COLUMN = "SALES_RETURNED_AMOUNT";

	/*  */
	/** COLUMNアノテーション salesDiscountAmount */
	public static final String salesDiscountAmount_COLUMN = "SALES_DISCOUNT_AMOUNT";

	/*  */
	/** COLUMNアノテーション otherSalesAmount */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/*  */
	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/*  */
	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション normal8TaxAmount */
	public static final String normal8TaxAmount_COLUMN = "NORMAL_8_TAX_AMOUNT";

	/** COLUMNアノテーション normal10TaxAmount */
	public static final String normal10TaxAmount_COLUMN = "NORMAL_10_TAX_AMOUNT";

	/** COLUMNアノテーション keigen8TaxAmount */
	public static final String Keigen8TaxAmount_COLUMN = "KEIGEN_8_TAX_AMOUNT";

	/** COLUMNアノテーション goukeiTaxAmount */
	public static final String goukeiTaxAmount_COLUMN = "GOUKEI_TAX_AMOUNT";

	/*  */
	/** COLUMNアノテーション claimAmount */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/*  */
	/** COLUMNアノテーション claimBalanceAmount */
	public static final String claimBalanceAmount_COLUMN = "CLAIM_BALANCE_AMOUNT";

	/*  */
	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/*  */
	/** COLUMNアノテーション eraserBalanceAmount */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/*  */
	/** COLUMNアノテーション billDivision */
	public static final String billDivision_COLUMN = "BILL_DIVISION";

	/*  */
	/** COLUMNアノテーション issueDate */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/*  */
	/** COLUMNアノテーション issuerCd */
	public static final String issuerCd_COLUMN = "ISSUER_CD";

	/*  */
	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/*  */
	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/*  */
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/*  */
	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/*  */
	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/*  */
	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/*  */
	/** COLUMNアノテーション venZipcodeNo */
	public static final String venZipcodeNo_COLUMN = "VEN_ZIPCODE_NO";

	/*  */
	/** COLUMNアノテーション venAddress1 */
	public static final String venAddress1_COLUMN = "VEN_ADDRESS1";

	/*  */
	/** COLUMNアノテーション venAddress2 */
	public static final String venAddress2_COLUMN = "VEN_ADDRESS2";

	/*  */
	/** COLUMNアノテーション venTelNo */
	public static final String venTelNo_COLUMN = "VEN_TEL_NO";

	/*  */
	/** COLUMNアノテーション venFaxNo */
	public static final String venFaxNo_COLUMN = "VEN_FAX_NO";

	/*  */
	/** COLUMNアノテーション homeName */
	public static final String homeName_COLUMN = "HOME_NAME";

	/*  */
	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/*  */
	/** COLUMNアノテーション orgZipcodeNo */
	public static final String orgZipcodeNo_COLUMN = "ORG_ZIPCODE_NO";

	/*  */
	/** COLUMNアノテーション orgAddress */
	public static final String orgAddress_COLUMN = "ORG_ADDRESS";

	/*  */
	/** COLUMNアノテーション orgTelNo */
	public static final String orgTelNo_COLUMN = "ORG_TEL_NO";

	/*  */
	/** COLUMNアノテーション orgFaxNo */
	public static final String orgFaxNo_COLUMN = "ORG_FAX_NO";

	/*  */
	/** COLUMNアノテーション henpinSonota */
	public static final String henpinSonota_COLUMN = "HENPIN_SONOTA";

	/** COLUMNアノテーション norml0HenpinSonota */
	public static final String norml0HenpinSonota_COLUMN = "NORMAL_0_HENPIN_SONOTA";

	/** COLUMNアノテーション normal8HenpinSonota */
	public static final String normal8HenpinSonota_COLUMN = "NORMAL_8_HENPIN_SONOTA";

	/** COLUMNアノテーション normal10HenpinSonota */
	public static final String normal10HenpinSonota_COLUMN = "NORMAL_10_HENPIN_SONOTA";

	/** COLUMNアノテーション keigen8HenpinSonota */
	public static final String Keigen8HenpinSonota_COLUMN = "KEIGEN_8_HENPIN_SONOTA";

	/** COLUMNアノテーション goukeiHenpinSonota */
	public static final String goukeiHenpinSonota_COLUMN = "GOUKEI_HENPIN_SONOTA";

	/*  */
	/** COLUMNアノテーション totalSalesAmount */
	public static final String totalSalesAmount_COLUMN = "TOTAL_SALES_AMOUNT";

	/*  */
	/** COLUMNアノテーション invoiceNo */
	public static final String invoiceNo_COLUMN = "INVOICE_NO";

	/*  */
	/** COLUMNアノテーション invoicePtnFlg */
	public static final String invoicePtnFlg_COLUMN = "INVOICE_PTN_FLG";
	//
	// インスタンスフィールド
	//

	/**  */
	private String claimNo;

	/**  */
	private String organizationCd;

	/**  */
	private String venderCd;

	/**  */
	private java.sql.Timestamp creditDate;

	/**  */
	private java.sql.Timestamp creditScheduledDate;

	/**  */
	private String creditDivision;

	/**  */
	private java.math.BigDecimal noteSight;

	/**  */
	private java.math.BigDecimal holidayFlg;

	/**  */
	private java.math.BigDecimal claimAmountForward;

	/**  */
	private java.math.BigDecimal creditAmountForward;

	/**  */
	private java.math.BigDecimal otherCreditAmountForward;

	/**  */
	private java.math.BigDecimal balanceForward;

	/**  */
	private java.math.BigDecimal salesAmount;

	/**  */
	private java.math.BigDecimal normal0SalesAmount;

	/**  */
	private java.math.BigDecimal normal8SalesAmount;

	/**  */
	private java.math.BigDecimal normal10SalesAmount;

	/**  */
	private java.math.BigDecimal keigen8SalesAmount;

	/**  */
	private java.math.BigDecimal goukeiSalesAmount;

	/**  */
	private java.math.BigDecimal salesReturnedAmount;

	/**  */
	private java.math.BigDecimal salesDiscountAmount;

	/**  */
	private java.math.BigDecimal otherSalesAmount;

	/**  */
	private java.math.BigDecimal offsetAmount;

	/**  */
	private java.math.BigDecimal taxAmount;

	/**  */
	private java.math.BigDecimal normal8TaxAmount;

	/**  */
	private java.math.BigDecimal normal10TaxAmount;

	/**  */
	private java.math.BigDecimal keigen8TaxAmount;

	/**  */
	private java.math.BigDecimal goukeiTaxAmount;

	/**  */
	private java.math.BigDecimal claimAmount;

	/**  */
	private java.math.BigDecimal claimBalanceAmount;

	/**  */
	private java.math.BigDecimal eraserAmount;

	/**  */
	private java.math.BigDecimal eraserBalanceAmount;

	/**  */
	private java.math.BigDecimal billDivision;

	/**  */
	private java.sql.Timestamp issueDate;

	/**  */
	private String issuerCd;

	/**  */
	private java.sql.Timestamp inputDate;

	/**  */
	private String inputorCd;

	/**  */
	private java.sql.Timestamp updateDate;

	/**  */
	private String updatorCd;

	/**  */
	private String venderName1;

	/**  */
	private String venderName2;

	/**  */
	private String venZipcodeNo;

	/**  */
	private String venAddress1;

	/**  */
	private String venAddress2;

	/**  */
	private String venTelNo;

	/**  */
	private String venFaxNo;

	/**  */
	private String homeName;

	/**  */
	private String organizationName;

	/**  */
	private String orgZipcodeNo;

	/**  */
	private String orgAddress;

	/**  */
	private String orgTelNo;

	/**  */
	private String orgFaxNo;

	/**  */
	private java.math.BigDecimal henpinSonota;

	/**  */
	private java.math.BigDecimal normal0HenpinSonota;

	/**  */
	private java.math.BigDecimal normal8HenpinSonota;

	/**  */
	private java.math.BigDecimal normal10HenpinSonota;

	/**  */
	private java.math.BigDecimal keigen8HenpinSonota;

	/**  */
	private java.math.BigDecimal goukeiHenpinSonota;

	/**  */
	private java.math.BigDecimal totalSalesAmount;

	/**  */
	private String invoiceNo;

	/**  */
	private java.math.BigDecimal invoicePtnFlg;

	//
	// インスタンスメソッド
	//

	/**
	 * claimNo取得.
	 *
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 *
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * organizationCd取得.
	 *
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 *
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * venderCd取得.
	 *
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 *
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * creditDate取得.
	 *
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 *
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * creditScheduledDate取得.
	 *
	 * @return creditScheduledDate
	 */
	public java.sql.Timestamp getCreditScheduledDate() {
		return this.creditScheduledDate;
	}

	/**
	 * creditScheduledDate設定.
	 *
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final java.sql.Timestamp creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * creditDivision取得.
	 *
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 *
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * noteSight取得.
	 *
	 * @return noteSight
	 */
	public java.math.BigDecimal getNoteSight() {
		return this.noteSight;
	}

	/**
	 * noteSight設定.
	 *
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final java.math.BigDecimal noteSight) {
		this.noteSight = noteSight;
	}

	/**
	 * holidayFlg取得.
	 *
	 * @return holidayFlg
	 */
	public java.math.BigDecimal getHolidayFlg() {
		return this.holidayFlg;
	}

	/**
	 * holidayFlg設定.
	 *
	 * @param holidayFlg holidayFlg
	 */
	public void setHolidayFlg(final java.math.BigDecimal holidayFlg) {
		this.holidayFlg = holidayFlg;
	}

	/**
	 * claimAmountForward取得.
	 *
	 * @return claimAmountForward
	 */
	public java.math.BigDecimal getClaimAmountForward() {
		return this.claimAmountForward;
	}

	/**
	 * claimAmountForward設定.
	 *
	 * @param claimAmountForward claimAmountForward
	 */
	public void setClaimAmountForward(final java.math.BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * creditAmountForward取得.
	 *
	 * @return creditAmountForward
	 */
	public java.math.BigDecimal getCreditAmountForward() {
		return this.creditAmountForward;
	}

	/**
	 * creditAmountForward設定.
	 *
	 * @param creditAmountForward creditAmountForward
	 */
	public void setCreditAmountForward(final java.math.BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * otherCreditAmountForward取得.
	 *
	 * @return otherCreditAmountForward
	 */
	public java.math.BigDecimal getOtherCreditAmountForward() {
		return this.otherCreditAmountForward;
	}

	/**
	 * otherCreditAmountForward設定.
	 *
	 * @param otherCreditAmountForward otherCreditAmountForward
	 */
	public void setOtherCreditAmountForward(final java.math.BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * balanceForward取得.
	 *
	 * @return balanceForward
	 */
	public java.math.BigDecimal getBalanceForward() {
		return this.balanceForward;
	}

	/**
	 * balanceForward設定.
	 *
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final java.math.BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * salesAmount取得.
	 *
	 * @return salesAmount
	 */
	public java.math.BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * salesAmount設定.
	 *
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final java.math.BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * normal0SalesAmount取得.
	 *
	 * @return normal0SalesAmount
	 */
	public java.math.BigDecimal getNormal0SalesAmount() {
		return this.normal0SalesAmount;
	}

	/**
	 * normal0SalesAmount設定.
	 *
	 * @param normal0SalesAmount normal0SalesAmount
	 */
	public void setNormal0SalesAmount(final java.math.BigDecimal normal0SalesAmount) {
		this.normal0SalesAmount = normal0SalesAmount;
	}

	/**
	 * normal8SalesAmount取得.
	 *
	 * @return normal8SalesAmount normal8SalesAmount
	 */
	public java.math.BigDecimal getNormal8SalesAmount() {
		return this.normal8SalesAmount;
	}

	/**
	 * normal8SalesAmount設定.
	 *
	 * @param salesAmount salesAmount
	 */
	public void setNormal8SalesAmount(final java.math.BigDecimal normal8SalesAmount) {
		this.normal8SalesAmount = normal8SalesAmount;
	}

	/**
	 * normal10SalesAmount取得.
	 *
	 * @return normal10SalesAmount
	 */
	public java.math.BigDecimal getNormal10SalesAmount() {
		return this.normal10SalesAmount;
	}

	/**
	 * normal10SalesAmountt設定.
	 *
	 * @param normal10SalesAmount normal10SalesAmount
	 */
	public void setNormal10SalesAmount(final java.math.BigDecimal normal10SalesAmount) {
		this.normal10SalesAmount = normal10SalesAmount;
	}

	/**
	 * keigen8SalesAmount取得.
	 *
	 * @return keigen8SalesAmount
	 */
	public java.math.BigDecimal getKeigen8SalesAmount() {
		return this.keigen8SalesAmount;
	}

	/**
	 * keigen8SalesAmount設定.
	 *
	 * @param keigen8SalesAmount keigen8SalesAmount
	 */
	public void setKeigen8SalesAmount(final java.math.BigDecimal keigen8SalesAmount) {
		this.keigen8SalesAmount = keigen8SalesAmount;
	}

	/**
	 * goukeiSalesAmount取得.
	 *
	 * @return goukeiSalesAmount
	 */
	public java.math.BigDecimal getGoukeiSalesAmount() {
		return this.goukeiSalesAmount;
	}

	/**
	 * goukeiSalesAmount設定.
	 *
	 * @param goukeiSalesAmount goukeiSalesAmount
	 */
	public void setGoukeiSalesAmount(final java.math.BigDecimal goukeiSalesAmount) {
		this.goukeiSalesAmount = goukeiSalesAmount;
	}

	/**
	 * salesReturnedAmount取得.
	 *
	 * @return salesReturnedAmount
	 */
	public java.math.BigDecimal getSalesReturnedAmount() {
		return this.salesReturnedAmount;
	}

	/**
	 * salesReturnedAmount設定.
	 *
	 * @param salesReturnedAmount salesReturnedAmount
	 */
	public void setSalesReturnedAmount(final java.math.BigDecimal salesReturnedAmount) {
		this.salesReturnedAmount = salesReturnedAmount;
	}

	/**
	 * salesDiscountAmount取得.
	 *
	 * @return salesDiscountAmount
	 */
	public java.math.BigDecimal getSalesDiscountAmount() {
		return this.salesDiscountAmount;
	}

	/**
	 * salesDiscountAmount設定.
	 *
	 * @param salesDiscountAmount salesDiscountAmount
	 */
	public void setSalesDiscountAmount(final java.math.BigDecimal salesDiscountAmount) {
		this.salesDiscountAmount = salesDiscountAmount;
	}

	/**
	 * otherSalesAmount取得.
	 *
	 * @return otherSalesAmount
	 */
	public java.math.BigDecimal getOtherSalesAmount() {
		return this.otherSalesAmount;
	}

	/**
	 * otherSalesAmount設定.
	 *
	 * @param otherSalesAmount otherSalesAmount
	 */
	public void setOtherSalesAmount(final java.math.BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
	}

	/**
	 * offsetAmount取得.
	 *
	 * @return offsetAmount
	 */
	public java.math.BigDecimal getOffsetAmount() {
		return this.offsetAmount;
	}

	/**
	 * offsetAmount設定.
	 *
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final java.math.BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * taxAmount取得.
	 *
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 *
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * normal8TaxAmount取得.
	 *
	 * @return normal8TaxAmount normal8TaxAmount
	 */
	public java.math.BigDecimal getNormal8TaxAmount() {
		return this.normal8TaxAmount;
	}

	/**
	 * normal8TaxAmount設定.
	 *
	 * @param taxAmount taxAmount
	 */
	public void setNormal8TaxAmount(final java.math.BigDecimal normal8TaxAmount) {
		this.normal8TaxAmount = normal8TaxAmount;
	}

	/**
	 * normal10TaxAmount取得.
	 *
	 * @return normal10TaxAmount
	 */
	public java.math.BigDecimal getNormal10TaxAmount() {
		return this.normal10TaxAmount;
	}

	/**
	 * normal10TaxAmountt設定.
	 *
	 * @param normal10TaxAmount normal10TaxAmount
	 */
	public void setNormal10TaxAmount(final java.math.BigDecimal normal10TaxAmount) {
		this.normal10TaxAmount = normal10TaxAmount;
	}

	/**
	 * keigen8TaxAmount取得.
	 *
	 * @return keigen8TaxAmount
	 */
	public java.math.BigDecimal getKeigen8TaxAmount() {
		return this.keigen8TaxAmount;
	}

	/**
	 * keigen8TaxAmount設定.
	 *
	 * @param keigen8TaxAmount keigen8TaxAmount
	 */
	public void setKeigen8TaxAmount(final java.math.BigDecimal keigen8TaxAmount) {
		this.keigen8TaxAmount = keigen8TaxAmount;
	}

	/**
	 * goukeiTaxAmount取得.
	 *
	 * @return goukeiTaxAmount
	 */
	public java.math.BigDecimal getGoukeiTaxAmount() {
		return this.goukeiTaxAmount;
	}

	/**
	 * goukeiTaxAmount設定.
	 *
	 * @param goukeiTaxAmount goukeiTaxAmount
	 */
	public void setGoukeiTaxAmount(final java.math.BigDecimal goukeiTaxAmount) {
		this.goukeiTaxAmount = goukeiTaxAmount;
	}

	/**
	 * claimAmount取得.
	 *
	 * @return claimAmount
	 */
	public java.math.BigDecimal getClaimAmount() {
		return this.claimAmount;
	}

	/**
	 * claimAmount設定.
	 *
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(final java.math.BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * claimBalanceAmount取得.
	 *
	 * @return claimBalanceAmount
	 */
	public java.math.BigDecimal getClaimBalanceAmount() {
		return this.claimBalanceAmount;
	}

	/**
	 * claimBalanceAmount設定.
	 *
	 * @param claimBalanceAmount claimBalanceAmount
	 */
	public void setClaimBalanceAmount(final java.math.BigDecimal claimBalanceAmount) {
		this.claimBalanceAmount = claimBalanceAmount;
	}

	/**
	 * eraserAmount取得.
	 *
	 * @return eraserAmount
	 */
	public java.math.BigDecimal getEraserAmount() {
		return this.eraserAmount;
	}

	/**
	 * eraserAmount設定.
	 *
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final java.math.BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * eraserBalanceAmount取得.
	 *
	 * @return eraserBalanceAmount
	 */
	public java.math.BigDecimal getEraserBalanceAmount() {
		return this.eraserBalanceAmount;
	}

	/**
	 * eraserBalanceAmount設定.
	 *
	 * @param eraserBalanceAmount eraserBalanceAmount
	 */
	public void setEraserBalanceAmount(final java.math.BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * billDivision取得.
	 *
	 * @return billDivision
	 */
	public java.math.BigDecimal getBillDivision() {
		return this.billDivision;
	}

	/**
	 * billDivision設定.
	 *
	 * @param billDivision billDivision
	 */
	public void setBillDivision(final java.math.BigDecimal billDivision) {
		this.billDivision = billDivision;
	}

	/**
	 * issueDate取得.
	 *
	 * @return issueDate
	 */
	public java.sql.Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * issueDate設定.
	 *
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final java.sql.Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * issuerCd取得.
	 *
	 * @return issuerCd
	 */
	public String getIssuerCd() {
		return this.issuerCd;
	}

	/**
	 * issuerCd設定.
	 *
	 * @param issuerCd issuerCd
	 */
	public void setIssuerCd(final String issuerCd) {
		this.issuerCd = issuerCd;
	}

	/**
	 * inputDate取得.
	 *
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 *
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 *
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 *
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 *
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 *
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 *
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 *
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * venderName1取得.
	 *
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 *
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2取得.
	 *
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 *
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * venZipcodeNo取得.
	 *
	 * @return venZipcodeNo
	 */
	public String getVenZipcodeNo() {
		return this.venZipcodeNo;
	}

	/**
	 * venZipcodeNo設定.
	 *
	 * @param venZipcodeNo venZipcodeNo
	 */
	public void setVenZipcodeNo(final String venZipcodeNo) {
		this.venZipcodeNo = venZipcodeNo;
	}

	/**
	 * venAddress1取得.
	 *
	 * @return venAddress1
	 */
	public String getVenAddress1() {
		return this.venAddress1;
	}

	/**
	 * venAddress1設定.
	 *
	 * @param venAddress1 venAddress1
	 */
	public void setVenAddress1(final String venAddress1) {
		this.venAddress1 = venAddress1;
	}

	/**
	 * venAddress2取得.
	 *
	 * @return venAddress2
	 */
	public String getVenAddress2() {
		return this.venAddress2;
	}

	/**
	 * venAddress2設定.
	 *
	 * @param venAddress2 venAddress2
	 */
	public void setVenAddress2(final String venAddress2) {
		this.venAddress2 = venAddress2;
	}

	/**
	 * venTelNo取得.
	 *
	 * @return venTelNo
	 */
	public String getVenTelNo() {
		return this.venTelNo;
	}

	/**
	 * venTelNo設定.
	 *
	 * @param venTelNo venTelNo
	 */
	public void setVenTelNo(final String venTelNo) {
		this.venTelNo = venTelNo;
	}

	/**
	 * venFaxNo取得.
	 *
	 * @return venFaxNo
	 */
	public String getVenFaxNo() {
		return this.venFaxNo;
	}

	/**
	 * venFaxNo設定.
	 *
	 * @param venFaxNo venFaxNo
	 */
	public void setVenFaxNo(final String venFaxNo) {
		this.venFaxNo = venFaxNo;
	}

	/**
	 * homeName取得.
	 *
	 * @return homeName
	 */
	public String getHomeName() {
		return this.homeName;
	}

	/**
	 * homeName設定.
	 *
	 * @param homeName homeName
	 */
	public void setHomeName(final String homeName) {
		this.homeName = homeName;
	}

	/**
	 * organizationName取得.
	 *
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 *
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * orgZipcodeNo取得.
	 *
	 * @return orgZipcodeNo
	 */
	public String getOrgZipcodeNo() {
		return this.orgZipcodeNo;
	}

	/**
	 * orgZipcodeNo設定.
	 *
	 * @param orgZipcodeNo orgZipcodeNo
	 */
	public void setOrgZipcodeNo(final String orgZipcodeNo) {
		this.orgZipcodeNo = orgZipcodeNo;
	}

	/**
	 * orgAddress取得.
	 *
	 * @return orgAddress
	 */
	public String getOrgAddress() {
		return this.orgAddress;
	}

	/**
	 * orgAddress設定.
	 *
	 * @param orgAddress orgAddress
	 */
	public void setOrgAddress(final String orgAddress) {
		this.orgAddress = orgAddress;
	}

	/**
	 * orgTelNo取得.
	 *
	 * @return orgTelNo
	 */
	public String getOrgTelNo() {
		return this.orgTelNo;
	}

	/**
	 * orgTelNo設定.
	 *
	 * @param orgTelNo orgTelNo
	 */
	public void setOrgTelNo(final String orgTelNo) {
		this.orgTelNo = orgTelNo;
	}

	/**
	 * orgFaxNo取得.
	 *
	 * @return orgFaxNo
	 */
	public String getOrgFaxNo() {
		return this.orgFaxNo;
	}

	/**
	 * orgFaxNo設定.
	 *
	 * @param orgFaxNo orgFaxNo
	 */
	public void setOrgFaxNo(final String orgFaxNo) {
		this.orgFaxNo = orgFaxNo;
	}

	/**
	 * henpinSonota取得.
	 *
	 * @return henpinSonota
	 */
	public java.math.BigDecimal getHenpinSonota() {
		return this.henpinSonota;
	}

	/**
	 * henpinSonota設定.
	 *
	 * @param henpinSonota henpinSonota
	 */
	public void setHenpinSonota(final java.math.BigDecimal henpinSonota) {
		this.henpinSonota = henpinSonota;
	}

	/**
	 * normal0HenpinSonota取得.
	 *
	 * @return normal0HenpinSonota
	 */
	public java.math.BigDecimal getNormal0HenpinSonota() {
		return this.normal0HenpinSonota;
	}

	/**
	 * normal0HenpinSonota設定.
	 *
	 * @param normal0HenpinSonota normal0HenpinSonota
	 */
	public void setNormal0HenpinSonota(final java.math.BigDecimal normal0HenpinSonota) {
		this.normal0HenpinSonota = normal0HenpinSonota;
	}

	/**
	 * normal8HenpinSonota取得.
	 *
	 * @return normal8HenpinSonota normal8HenpinSonota
	 */
	public java.math.BigDecimal getNormal8HenpinSonota() {
		return this.normal8HenpinSonota;
	}

	/**
	 * normal8HenpinSonota設定.
	 *
	 * @param henpinSonota henpinSonota
	 */
	public void setNormal8HenpinSonota(final java.math.BigDecimal normal8HenpinSonota) {
		this.normal8HenpinSonota = normal8HenpinSonota;
	}

	/**
	 * normal10HenpinSonota取得.
	 *
	 * @return normal10HenpinSonota
	 */
	public java.math.BigDecimal getNormal10HenpinSonota() {
		return this.normal10HenpinSonota;
	}

	/**
	 * normal10HenpinSonotat設定.
	 *
	 * @param normal10HenpinSonota normal10HenpinSonota
	 */
	public void setNormal10HenpinSonota(final java.math.BigDecimal normal10HenpinSonota) {
		this.normal10HenpinSonota = normal10HenpinSonota;
	}

	/**
	 * keigen8HenpinSonota取得.
	 *
	 * @return keigen8HenpinSonota
	 */
	public java.math.BigDecimal getKeigen8HenpinSonota() {
		return this.keigen8HenpinSonota;
	}

	/**
	 * keigen8HenpinSonota設定.
	 *
	 * @param keigen8HenpinSonota keigen8HenpinSonota
	 */
	public void setKeigen8HenpinSonota(final java.math.BigDecimal keigen8HenpinSonota) {
		this.keigen8HenpinSonota = keigen8HenpinSonota;
	}

	/**
	 * goukeiHenpinSonota取得.
	 *
	 * @return goukeiHenpinSonota
	 */
	public java.math.BigDecimal getGoukeiHenpinSonota() {
		return this.goukeiHenpinSonota;
	}

	/**
	 * goukeiHenpinSonota設定.
	 *
	 * @param goukeiHenpinSonota goukeiHenpinSonota
	 */
	public void setGoukeiHenpinSonota(final java.math.BigDecimal goukeiHenpinSonota) {
		this.goukeiHenpinSonota = goukeiHenpinSonota;
	}

	/**
	 * totalSalesAmount取得.
	 *
	 * @return totalSalesAmount
	 */
	public java.math.BigDecimal getTotalSalesAmount() {
		return this.totalSalesAmount;
	}

	/**
	 * totalSalesAmount設定.
	 *
	 * @param totalSalesAmount totalSalesAmount
	 */
	public void setTotalSalesAmount(final java.math.BigDecimal totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	/**
	 * invoiceNo取得.
	 *
	 * @return invoiceNo
	 */
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	/**
	 * invoiceNo設定.
	 *
	 * @param invoiceNo invoiceNo
	 */
	public void setInvoiceNo(final String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * invoicePtnFlg取得.
	 *
	 * @return invoicePtnFlg
	 */
	public java.math.BigDecimal getInvoicePtnFlg() {
		return this.invoicePtnFlg;
	}

	/**
	 * invoicePtnFlg設定.
	 *
	 * @param invoicePtnFlg invoicePtnFlg
	 */
	public void setInvoicePtnFlg(final java.math.BigDecimal invoicePtnFlg) {
		this.invoicePtnFlg = invoicePtnFlg;
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

