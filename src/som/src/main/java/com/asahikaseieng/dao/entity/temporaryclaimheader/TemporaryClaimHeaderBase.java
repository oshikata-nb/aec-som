/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Mon Jun 15 11:26:37 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimheader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TemporaryClaimHeaderBaseクラス.
 * @author a7710658
 */
public class TemporaryClaimHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TemporaryClaimHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TEMPORARY_CLAIM_HEADER";


//	/** IDアノテーション claimNo. */
//	public static final String claimNo_ID = "assigned";

	/** COLUMNアノテーション claimNo. */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション creditDate. */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** COLUMNアノテーション creditScheduledDate. */
	public static final String creditScheduledDate_COLUMN = "CREDIT_SCHEDULED_DATE";

	/** COLUMNアノテーション creditDivision. */
	public static final String creditDivision_COLUMN = "CREDIT_DIVISION";

	/** COLUMNアノテーション noteSight. */
	public static final String noteSight_COLUMN = "NOTE_SIGHT";

	/** COLUMNアノテーション holidayFlg. */
	public static final String holidayFlg_COLUMN = "HOLIDAY_FLG";

	/** COLUMNアノテーション claimAmountForward. */
	public static final String claimAmountForward_COLUMN = "CLAIM_AMOUNT_FORWARD";

	/** COLUMNアノテーション creditAmountForward. */
	public static final String creditAmountForward_COLUMN = "CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション otherCreditAmountForward. */
	public static final String otherCreditAmountForward_COLUMN = "OTHER_CREDIT_AMOUNT_FORWARD";

	/** COLUMNアノテーション balanceForward. */
	public static final String balanceForward_COLUMN = "BALANCE_FORWARD";

	/** COLUMNアノテーション salesAmount. */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション salesReturnedAmount. */
	public static final String salesReturnedAmount_COLUMN = "SALES_RETURNED_AMOUNT";

	/** COLUMNアノテーション salesDiscountAmount. */
	public static final String salesDiscountAmount_COLUMN = "SALES_DISCOUNT_AMOUNT";

	/** COLUMNアノテーション otherSalesAmount. */
	public static final String otherSalesAmount_COLUMN = "OTHER_SALES_AMOUNT";

	/** COLUMNアノテーション offsetAmount. */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション taxAmount. */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション claimAmount. */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/** COLUMNアノテーション claimBalanceAmount. */
	public static final String claimBalanceAmount_COLUMN = "CLAIM_BALANCE_AMOUNT";

	/** COLUMNアノテーション eraserAmount. */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション eraserBalanceAmount. */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/** COLUMNアノテーション billDivision. */
	public static final String billDivision_COLUMN = "BILL_DIVISION";

	/** COLUMNアノテーション issueDate. */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** COLUMNアノテーション issuerCd. */
	public static final String issuerCd_COLUMN = "ISSUER_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String claimNo;

	private String organizationCd;

	private String venderCd;

	private java.sql.Timestamp creditDate;

	private java.sql.Timestamp creditScheduledDate;

	private String creditDivision;

	private java.math.BigDecimal noteSight;

	private java.math.BigDecimal holidayFlg;

	private java.math.BigDecimal claimAmountForward;

	private java.math.BigDecimal creditAmountForward;

	private java.math.BigDecimal otherCreditAmountForward;

	private java.math.BigDecimal balanceForward;

	private java.math.BigDecimal salesAmount;

	private java.math.BigDecimal salesReturnedAmount;

	private java.math.BigDecimal salesDiscountAmount;

	private java.math.BigDecimal otherSalesAmount;

	private java.math.BigDecimal offsetAmount;

	private java.math.BigDecimal taxAmount;

	private java.math.BigDecimal claimAmount;

	private java.math.BigDecimal claimBalanceAmount;

	private java.math.BigDecimal eraserAmount;

	private java.math.BigDecimal eraserBalanceAmount;

	private java.math.BigDecimal billDivision;

	private java.sql.Timestamp issueDate;

	private String issuerCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * claimNo取得.
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
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
	 * creditDate取得.
	 * @return creditDate
	 */
	public java.sql.Timestamp getCreditDate() {
		return this.creditDate;
	}

	/**
	 * creditDate設定.
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final java.sql.Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * creditScheduledDate取得.
	 * @return creditScheduledDate
	 */
	public java.sql.Timestamp getCreditScheduledDate() {
		return this.creditScheduledDate;
	}

	/**
	 * creditScheduledDate設定.
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final java.sql.Timestamp creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * creditDivision取得.
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return this.creditDivision;
	}

	/**
	 * creditDivision設定.
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * noteSight取得.
	 * @return noteSight
	 */
	public java.math.BigDecimal getNoteSight() {
		return this.noteSight;
	}

	/**
	 * noteSight設定.
	 * @param noteSight noteSight
	 */
	public void setNoteSight(final java.math.BigDecimal noteSight) {
		this.noteSight = noteSight;
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
	 * claimAmountForward取得.
	 * @return claimAmountForward
	 */
	public java.math.BigDecimal getClaimAmountForward() {
		return this.claimAmountForward;
	}

	/**
	 * claimAmountForward設定.
	 * @param claimAmountForward claimAmountForward
	 */
	public void setClaimAmountForward(final java.math.BigDecimal claimAmountForward) {
		this.claimAmountForward = claimAmountForward;
	}

	/**
	 * creditAmountForward取得.
	 * @return creditAmountForward
	 */
	public java.math.BigDecimal getCreditAmountForward() {
		return this.creditAmountForward;
	}

	/**
	 * creditAmountForward設定.
	 * @param creditAmountForward creditAmountForward
	 */
	public void setCreditAmountForward(final java.math.BigDecimal creditAmountForward) {
		this.creditAmountForward = creditAmountForward;
	}

	/**
	 * otherCreditAmountForward取得.
	 * @return otherCreditAmountForward
	 */
	public java.math.BigDecimal getOtherCreditAmountForward() {
		return this.otherCreditAmountForward;
	}

	/**
	 * otherCreditAmountForward設定.
	 * @param otherCreditAmountForward otherCreditAmountForward
	 */
	public void setOtherCreditAmountForward(final java.math.BigDecimal otherCreditAmountForward) {
		this.otherCreditAmountForward = otherCreditAmountForward;
	}

	/**
	 * balanceForward取得.
	 * @return balanceForward
	 */
	public java.math.BigDecimal getBalanceForward() {
		return this.balanceForward;
	}

	/**
	 * balanceForward設定.
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final java.math.BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * salesAmount取得.
	 * @return salesAmount
	 */
	public java.math.BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * salesAmount設定.
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final java.math.BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * salesReturnedAmount取得.
	 * @return salesReturnedAmount
	 */
	public java.math.BigDecimal getSalesReturnedAmount() {
		return this.salesReturnedAmount;
	}

	/**
	 * salesReturnedAmount設定.
	 * @param salesReturnedAmount salesReturnedAmount
	 */
	public void setSalesReturnedAmount(final java.math.BigDecimal salesReturnedAmount) {
		this.salesReturnedAmount = salesReturnedAmount;
	}

	/**
	 * salesDiscountAmount取得.
	 * @return salesDiscountAmount
	 */
	public java.math.BigDecimal getSalesDiscountAmount() {
		return this.salesDiscountAmount;
	}

	/**
	 * salesDiscountAmount設定.
	 * @param salesDiscountAmount salesDiscountAmount
	 */
	public void setSalesDiscountAmount(final java.math.BigDecimal salesDiscountAmount) {
		this.salesDiscountAmount = salesDiscountAmount;
	}

	/**
	 * otherSalesAmount取得.
	 * @return otherSalesAmount
	 */
	public java.math.BigDecimal getOtherSalesAmount() {
		return this.otherSalesAmount;
	}

	/**
	 * otherSalesAmount設定.
	 * @param otherSalesAmount otherSalesAmount
	 */
	public void setOtherSalesAmount(final java.math.BigDecimal otherSalesAmount) {
		this.otherSalesAmount = otherSalesAmount;
	}

	/**
	 * offsetAmount取得.
	 * @return offsetAmount
	 */
	public java.math.BigDecimal getOffsetAmount() {
		return this.offsetAmount;
	}

	/**
	 * offsetAmount設定.
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final java.math.BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * taxAmount取得.
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * claimAmount取得.
	 * @return claimAmount
	 */
	public java.math.BigDecimal getClaimAmount() {
		return this.claimAmount;
	}

	/**
	 * claimAmount設定.
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(final java.math.BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * claimBalanceAmount取得.
	 * @return claimBalanceAmount
	 */
	public java.math.BigDecimal getClaimBalanceAmount() {
		return this.claimBalanceAmount;
	}

	/**
	 * claimBalanceAmount設定.
	 * @param claimBalanceAmount claimBalanceAmount
	 */
	public void setClaimBalanceAmount(final java.math.BigDecimal claimBalanceAmount) {
		this.claimBalanceAmount = claimBalanceAmount;
	}

	/**
	 * eraserAmount取得.
	 * @return eraserAmount
	 */
	public java.math.BigDecimal getEraserAmount() {
		return this.eraserAmount;
	}

	/**
	 * eraserAmount設定.
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final java.math.BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * eraserBalanceAmount取得.
	 * @return eraserBalanceAmount
	 */
	public java.math.BigDecimal getEraserBalanceAmount() {
		return this.eraserBalanceAmount;
	}

	/**
	 * eraserBalanceAmount設定.
	 * @param eraserBalanceAmount eraserBalanceAmount
	 */
	public void setEraserBalanceAmount(final java.math.BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * billDivision取得.
	 * @return billDivision
	 */
	public java.math.BigDecimal getBillDivision() {
		return this.billDivision;
	}

	/**
	 * billDivision設定.
	 * @param billDivision billDivision
	 */
	public void setBillDivision(final java.math.BigDecimal billDivision) {
		this.billDivision = billDivision;
	}

	/**
	 * issueDate取得.
	 * @return issueDate
	 */
	public java.sql.Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * issueDate設定.
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final java.sql.Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * issuerCd取得.
	 * @return issuerCd
	 */
	public String getIssuerCd() {
		return this.issuerCd;
	}

	/**
	 * issuerCd設定.
	 * @param issuerCd issuerCd
	 */
	public void setIssuerCd(final String issuerCd) {
		this.issuerCd = issuerCd;
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
