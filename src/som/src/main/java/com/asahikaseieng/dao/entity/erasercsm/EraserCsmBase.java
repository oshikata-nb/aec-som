/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Thu Jun 25 07:54:25 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.erasercsm;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EraserCsmBaseクラス.
 * @author t0011036
 */
public class EraserCsmBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EraserCsmBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_CSM";


//	/** IDアノテーション dataType. */
//	public static final String dataType_ID = "assigned";
//	/** IDアノテーション slipNo. */
//	public static final String slipNo_ID = "assigned";

	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション invoiceCd. */
	public static final String invoiceCd_COLUMN = "INVOICE_CD";

	/** COLUMNアノテーション dataType. */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション slipNo. */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション processingDate. */
	public static final String processingDate_COLUMN = "PROCESSING_DATE";

	/** COLUMNアノテーション eraserObjectAmount. */
	public static final String eraserObjectAmount_COLUMN = "ERASER_OBJECT_AMOUNT";

	/** COLUMNアノテーション eraserAmount. */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション eraserBalanceAmount. */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/** COLUMNアノテーション eraserCompleteDivision. */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate. */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション invoiceUpdateDate. */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション claimNo. */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション approvalStatus. */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby. */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate. */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション eraserDate. */
	public static final String eraserDate_COLUMN = "ERASER_DATE";

	/** COLUMNアノテーション eraserUpdateDate. */
	public static final String eraserUpdateDate_COLUMN = "ERASER_UPDATE_DATE";

	/** COLUMNアノテーション eraserorCd. */
	public static final String eraserorCd_COLUMN = "ERASEROR_CD";

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

	private String organizationCd;

	private String invoiceCd;

	private java.math.BigDecimal dataType;

	private String slipNo;

	private java.sql.Timestamp processingDate;

	private java.math.BigDecimal eraserObjectAmount;

	private java.math.BigDecimal eraserAmount;

	private java.math.BigDecimal eraserBalanceAmount;

	private java.math.BigDecimal eraserCompleteDivision;

	private java.sql.Timestamp eraserCompleteDate;

	private java.sql.Timestamp invoiceUpdateDate;

	private String claimNo;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private java.sql.Timestamp eraserDate;

	private java.sql.Timestamp eraserUpdateDate;

	private String eraserorCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

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
	 * invoiceCd取得.
	 * @return invoiceCd
	 */
	public String getInvoiceCd() {
		return this.invoiceCd;
	}

	/**
	 * invoiceCd設定.
	 * @param invoiceCd invoiceCd
	 */
	public void setInvoiceCd(final String invoiceCd) {
		this.invoiceCd = invoiceCd;
	}

	/**
	 * dataType取得.
	 * @return dataType
	 */
	public java.math.BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * dataType設定.
	 * @param dataType dataType
	 */
	public void setDataType(final java.math.BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * processingDate取得.
	 * @return processingDate
	 */
	public java.sql.Timestamp getProcessingDate() {
		return this.processingDate;
	}

	/**
	 * processingDate設定.
	 * @param processingDate processingDate
	 */
	public void setProcessingDate(final java.sql.Timestamp processingDate) {
		this.processingDate = processingDate;
	}

	/**
	 * eraserObjectAmount取得.
	 * @return eraserObjectAmount
	 */
	public java.math.BigDecimal getEraserObjectAmount() {
		return this.eraserObjectAmount;
	}

	/**
	 * eraserObjectAmount設定.
	 * @param eraserObjectAmount eraserObjectAmount
	 */
	public void setEraserObjectAmount(final java.math.BigDecimal eraserObjectAmount) {
		this.eraserObjectAmount = eraserObjectAmount;
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
	 * eraserCompleteDivision取得.
	 * @return eraserCompleteDivision
	 */
	public java.math.BigDecimal getEraserCompleteDivision() {
		return this.eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDivision設定.
	 * @param eraserCompleteDivision eraserCompleteDivision
	 */
	public void setEraserCompleteDivision(final java.math.BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDate取得.
	 * @return eraserCompleteDate
	 */
	public java.sql.Timestamp getEraserCompleteDate() {
		return this.eraserCompleteDate;
	}

	/**
	 * eraserCompleteDate設定.
	 * @param eraserCompleteDate eraserCompleteDate
	 */
	public void setEraserCompleteDate(final java.sql.Timestamp eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * invoiceUpdateDate取得.
	 * @return invoiceUpdateDate
	 */
	public java.sql.Timestamp getInvoiceUpdateDate() {
		return this.invoiceUpdateDate;
	}

	/**
	 * invoiceUpdateDate設定.
	 * @param invoiceUpdateDate invoiceUpdateDate
	 */
	public void setInvoiceUpdateDate(final java.sql.Timestamp invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

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
	 * approvalStatus取得.
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * approvalStatus設定.
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvedby取得.
	 * @return approvedby
	 */
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * approvedby設定.
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * approvaldate取得.
	 * @return approvaldate
	 */
	public java.sql.Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * approvaldate設定.
	 * @param approvaldate approvaldate
	 */
	public void setApprovaldate(final java.sql.Timestamp approvaldate) {
		this.approvaldate = approvaldate;
	}

	/**
	 * eraserDate取得.
	 * @return eraserDate
	 */
	public java.sql.Timestamp getEraserDate() {
		return this.eraserDate;
	}

	/**
	 * eraserDate設定.
	 * @param eraserDate eraserDate
	 */
	public void setEraserDate(final java.sql.Timestamp eraserDate) {
		this.eraserDate = eraserDate;
	}

	/**
	 * eraserUpdateDate取得.
	 * @return eraserUpdateDate
	 */
	public java.sql.Timestamp getEraserUpdateDate() {
		return this.eraserUpdateDate;
	}

	/**
	 * eraserUpdateDate設定.
	 * @param eraserUpdateDate eraserUpdateDate
	 */
	public void setEraserUpdateDate(final java.sql.Timestamp eraserUpdateDate) {
		this.eraserUpdateDate = eraserUpdateDate;
	}

	/**
	 * eraserorCd取得.
	 * @return eraserorCd
	 */
	public String getEraserorCd() {
		return this.eraserorCd;
	}

	/**
	 * eraserorCd設定.
	 * @param eraserorCd eraserorCd
	 */
	public void setEraserorCd(final String eraserorCd) {
		this.eraserorCd = eraserorCd;
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
