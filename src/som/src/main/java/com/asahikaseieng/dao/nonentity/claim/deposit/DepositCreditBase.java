/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.deposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.asahikaseieng.utils.ConstantFunction;

/**
 * 入金入力（リスト）用Daoクラス.
 * @author tosco
 */
public class DepositCreditBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DepositCreditBase() {
	}

	// 列定義----------------------------------------------------------------------------
	/** ﾃﾞｰﾀ種別 2:入金（’2’固定） */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** ﾃﾞｰﾀ集計区分（1：入金 2：相殺 9:その他） */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** 分類コード */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** 入金日付 */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	/** 入金番号 */
	public static final String creditNo_COLUMN = "CREDIT_NO";

	/** 行番号 */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** 請求先コード */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** 部署コード */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** 会計部門 */
	public static final String accountSectionCd_COLUMN = "ACCOUNT_SECTION_CD";

	/** 入金金額 */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** 消込額 */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** 入金消込残 */
	public static final String creditEraserAmount_COLUMN = "CREDIT_ERASER_AMOUNT";

	/** 銀行コード */
	public static final String bankCd_COLUMN = "BANK_CD";

	/** 預金種別 */
	public static final String accountDivision_COLUMN = "ACCOUNT_DIVISION";

	/** 口座番号 */
	public static final String accountNo_COLUMN = "ACCOUNT_NO";

	/** 手形種別 */
	public static final String noteDivision_COLUMN = "NOTE_DIVISION";

	/** 手形番号 */
	public static final String noteNo_COLUMN = "NOTE_NO";

	/** 振出日 */
	public static final String drawalDate_COLUMN = "DRAWAL_DATE";

	/** 満期日 */
	public static final String noteDate_COLUMN = "NOTE_DATE";

	/** 借方科目コード */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** 借方補助科目コード */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** 貸方科目コード */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** 貸方補助科目コード */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** 摘要コード */
	public static final String summaryCd_COLUMN = "SUMMARY_CD";

	/** 摘要名 */
	public static final String summary_COLUMN = "SUMMARY";

	/** 売掛対象(0：未処理、1：処理済、9：対象外) */
	public static final String depositTargetDivision_COLUMN = "DEPOSIT_TARGET_DIVISION";

	/** 請求対象(0：未処理、1：処理済、9：対象外) */
	public static final String claimTargetDivision_COLUMN = "CLAIM_TARGET_DIVISION";

	/** 買掛対象(0：未処理、1：処理済、9：対象外) */
	public static final String payableTargetDivision_COLUMN = "PAYABLE_TARGET_DIVISION";

	/** 支払対象(0：未処理、1：処理済、9：対象外) */
	public static final String paymentTargetDivision_COLUMN = "PAYMENT_TARGET_DIVISION";

	/** UE_DATE IS 伝票発行日 */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** UED_DIVISI 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
	public static final String issuedDivision_COLUMN = "ISSUED_DIVISION";

	/** 売掛更新フラグ(0：未処理、1：処理済) */
	public static final String depositUpdateDivision_COLUMN = "DEPOSIT_UPDATE_DIVISION";

	/** 売掛番号 */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** 売掛締め日 */
	public static final String deliveryUpdateDate_COLUMN = "DELIVERY_UPDATE_DATE";

	/** 請求更新フラグ(0：未処理、1：処理済) */
	public static final String claimUpdateDivision_COLUMN = "CLAIM_UPDATE_DIVISION";

	/** 請求番号 */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** 請求締め日 */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** 買掛更新フラグ(0：未処理、1：処理済) */
	public static final String payableUpdateDivision_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** 買掛番号 */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** 買掛締め日 */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** 支払更新フラグ(0：未処理、1：処理済) */
	public static final String paymentUpdateDivision_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** 支払番号 */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** 支払締め日 */
	public static final String paymentUpdateDate_COLUMN = "PAYMENT_UPDATE_DATE";

	/** 消込完了フラグ(0：未処理、1：処理済) */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** 入金消込完了日 */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** 摘要 */
	public static final String remark_COLUMN = "REMARK";

	/** データ転送日時 */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** 承認ステータス(1：入力中、2：承認依頼中、3：承認済み) */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** 承認者 */
	public static final String approvedBy_COLUMN = "APPROVEDBY";

	/** 承認日 */
	public static final String approvalDate_COLUMN = "APPROVALDATE";

	/** 登録日時 */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** 登録者ＩＤ */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** 更新日時 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** 更新者ＩＤ */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** 入金分類名称 */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** 請求先名 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	/** 勘定科目コード */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** 勘定科目名称 */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	/** 請求額 */
	public static final String claimAmount_COLUMN = "CLAIM_AMOUNT";

	/** 売上額 */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	// インスタンスフィールド-----------------------------------------------------------------------------

	/** ﾃﾞｰﾀ種別 2:入金（’2’固定） */
	private String dataType;

	/** ﾃﾞｰﾀ集計区分（1：入金 2：相殺 9:その他） */
	private BigDecimal dataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 入金日付 */
	private Timestamp creditDate;

	/** 入金番号 */
	private String creditNo;

	/** 行番号 */
	private String rowNo;

	/** 請求先コード */
	private String venderCd;

	/** 部署コード */
	private String organizationCd;

	/** 会計部門 */
	private String accountSectionCd;

	/** 入金金額 */
	private String creditAmount;

	/** 消込額 */
	private String eraserAmount;

	/** 入金消込残 */
	private String creditEraserAmount;

	/** 銀行コード */
	private String bankCd;

	/** 預金種別 */
	private String accountDivision;

	/** 口座番号 */
	private String accountNo;

	/** 手形種別 */
	private BigDecimal noteDivision;

	/** 手形番号 */
	private String noteNo;

	/** 振出日 */
	private Timestamp drawalDate;

	/** 満期日 */
	private Timestamp noteDate;

	/** 借方部門コード */
	private String debitSectionCd;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 借方補助科目コード */
	private String debitSubTitleCd;

	/** 貸方部門コード */
	private String creditSectionCd;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 貸方補助科目コード */
	private String creditSubTitleCd;

	/** 摘要コード */
	private String summaryCd;

	/** 摘要名 */
	private String summary;

	/** 売掛対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal depositTargetDivision;

	/** 請求対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal claimTargetDivision;

	/** 買掛対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal payableTargetDivision;

	/** 支払対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal paymentTargetDivision;

	/** 伝票発行日 */
	private Timestamp issueDate;

	/** 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
	private BigDecimal issuedDivision;

	/** 売掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal depositUpdateDivision;

	/** 売掛番号 */
	private String depositNo;

	/** 売掛締め日 */
	private Timestamp deliveryUpdateDate;

	/** 請求更新フラグ(0：未処理、1：処理済) */
	private BigDecimal claimUpdateDivision;

	/** 請求番号 */
	private String claimNo;

	/** 請求締め日 */
	private Timestamp invoiceUpdateDate;

	/** 買掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal payableUpdateDivision;

	/** 買掛番号 */
	private String payableNo;

	/** 買掛締め日 */
	private Timestamp payableUpdateDate;

	/** 支払更新フラグ(0：未処理、1：処理済) */
	private BigDecimal paymentUpdateDivision;

	/** 支払番号 */
	private String paymentNo;

	/** 支払締め日 */
	private Timestamp paymentUpdateDate;

	/** 消込完了フラグ(0：未処理、1：処理済) */
	private BigDecimal eraserCompleteDivision;

	/** 入金消込完了日 */
	private Timestamp eraserCompleteDate;

	/** 摘要 */
	private String remark;

	/** データ転送日時 */
	private Timestamp transmissionDate;

	/** 承認ステータス(1：入力中、2：承認依頼中、3：承認済み) */
	private String approvalStatus;

	/** 承認者 */
	private String approvedBy;

	/** 承認日 */
	private Timestamp approvalDate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	/** 入金分類名称 */
	private String categoryName;

	/** 請求先名 */
	private String venderName1;

	/** 請求先略称 */
	private String venderShortedName;

	/** 承認ステータスラベル */
	private String approvalStatusLabel;

	/** 勘定科目コード */
	private String accountsCd;

	/** 勘定科目名称 */
	private String accountsName;
	
	/** 請求額 */
	private BigDecimal claimAmount;
	
	/** 売上額 */
	private BigDecimal salesAmount;

	// インスタンスメソッド-----------------------------------------------------------------------------

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
	 * accountDivisionを取得します。
	 * @return accountDivision
	 */
	public String getAccountDivision() {
		return accountDivision;
	}

	/**
	 * accountDivisionを設定します。
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNoを取得します。
	 * @return accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * accountNoを設定します。
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * accountSectionCdを取得します。
	 * @return accountSectionCd
	 */
	public String getAccountSectionCd() {
		return accountSectionCd;
	}

	/**
	 * accountSectionCdを設定します。
	 * @param accountSectionCd accountSectionCd
	 */
	public void setAccountSectionCd(final String accountSectionCd) {
		this.accountSectionCd = accountSectionCd;
	}

	/**
	 * approvaldateを取得します。
	 * @return approvalDate
	 */
	public Timestamp getApprovalDate() {
		return approvalDate;
	}

	/**
	 * approvaldateを設定します。
	 * @param approvalDate approvalDate
	 */
	public void setApprovalDate(final Timestamp approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final String approvalStatus) {
		this.approvalStatus = approvalStatus;
		if (ConstantFunction.APPROVAL_STATUS_INPUT.equals(approvalStatus)) {
			setApprovalStatusLabel(ConstantFunction.APPROVAL_STATUS_INPUT_LABEL);
		} else if (ConstantFunction.APPROVAL_STATUS_REQUEST
				.equals(approvalStatus)) {
			setApprovalStatusLabel(ConstantFunction.APPROVAL_STATUS_REQUEST_LABEL);
		} else if (ConstantFunction.APPROVAL_STATUS_APPROVAL
				.equals(approvalStatus)) {
			setApprovalStatusLabel(ConstantFunction.APPROVAL_STATUS_APPROVAL_LABEL);
		}
	}

	/**
	 * approvedbyを取得します。
	 * @return approvedby
	 */
	public String getApprovedby() {
		return approvedBy;
	}

	/**
	 * approvedbyを設定します。
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedBy = approvedby;
	}

	/**
	 * bankCdを取得します。
	 * @return bankCd
	 */
	public String getBankCd() {
		return bankCd;
	}

	/**
	 * bankCdを設定します。
	 * @param bankCd bankCd
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * categoryDivisionを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * categoryDivisionを設定します。
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * claimNoを取得します。
	 * @return claimNo
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * claimNoを設定します。
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * claimTargetDivisionを取得します。
	 * @return claimTargetDivision
	 */
	public BigDecimal getClaimTargetDivision() {
		return claimTargetDivision;
	}

	/**
	 * claimTargetDivisionを設定します。
	 * @param claimTargetDivision claimTargetDivision
	 */
	public void setClaimTargetDivision(final BigDecimal claimTargetDivision) {
		this.claimTargetDivision = claimTargetDivision;
	}

	/**
	 * claimUpdateDivisionを取得します。
	 * @return claimUpdateDivision
	 */
	public BigDecimal getClaimUpdateDivision() {
		return claimUpdateDivision;
	}

	/**
	 * claimUpdateDivisionを設定します。
	 * @param claimUpdateDivision claimUpdateDivision
	 */
	public void setClaimUpdateDivision(final BigDecimal claimUpdateDivision) {
		this.claimUpdateDivision = claimUpdateDivision;
	}

	/**
	 * creditAmountを取得します。
	 * @return creditAmount
	 */
	public String getCreditAmount() {
		return creditAmount;
	}

	/**
	 * creditAmountを設定します。
	 * @param creditAmount creditAmount
	 */
	public void setCreditAmount(final String creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * creditDateを取得します。
	 * @return creditDate
	 */
	public Timestamp getCreditDate() {
		return creditDate;
	}

	/**
	 * creditDateを設定します。
	 * @param creditDate creditDate
	 */
	public void setCreditDate(final Timestamp creditDate) {
		this.creditDate = creditDate;
	}

	/**
	 * creditEraserAmountを取得します。
	 * @return creditEraserAmount
	 */
	public String getCreditEraserAmount() {
		return creditEraserAmount;
	}

	/**
	 * creditEraserAmountを設定します。
	 * @param creditEraserAmount creditEraserAmount
	 */
	public void setCreditEraserAmount(final String creditEraserAmount) {
		this.creditEraserAmount = creditEraserAmount;
	}

	/**
	 * creditNoを取得します。
	 * @return creditNo
	 */
	public String getCreditNo() {
		return creditNo;
	}

	/**
	 * creditNoを設定します。
	 * @param creditNo creditNo
	 */
	public void setCreditNo(final String creditNo) {
		this.creditNo = creditNo;
	}

	/**
	 * creditSubTitleCdを取得します。
	 * @return creditSubTitleCd
	 */
	public String getCreditSubTitleCd() {
		return creditSubTitleCd;
	}

	/**
	 * creditSubTitleCdを設定します。
	 * @param creditSubTitleCd creditSubTitleCd
	 */
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * creditTitleCdを取得します。
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return creditTitleCd;
	}

	/**
	 * creditTitleCdを設定します。
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * dataTotalDivisionを取得します。
	 * @return dataTotalDivision
	 */
	public BigDecimal getDataTotalDivision() {
		return dataTotalDivision;
	}

	/**
	 * dataTotalDivisionを設定します。
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}

	/**
	 * debitSubTitleCdを取得します。
	 * @return debitSubTitleCd
	 */
	public String getDebitSubTitleCd() {
		return debitSubTitleCd;
	}

	/**
	 * debitSubTitleCdを設定します。
	 * @param debitSubTitleCd debitSubTitleCd
	 */
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
	}

	/**
	 * debitTitleCdを取得します。
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return debitTitleCd;
	}

	/**
	 * debitTitleCdを設定します。
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * deliveryUpdateDateを取得します。
	 * @return deliveryUpdateDate
	 */
	public Timestamp getDeliveryUpdateDate() {
		return deliveryUpdateDate;
	}

	/**
	 * deliveryUpdateDateを設定します。
	 * @param deliveryUpdateDate deliveryUpdateDate
	 */
	public void setDeliveryUpdateDate(final Timestamp deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
	}

	/**
	 * depositNoを取得します。
	 * @return depositNo
	 */
	public String getDepositNo() {
		return depositNo;
	}

	/**
	 * depositNoを設定します。
	 * @param depositNo depositNo
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * depositTargetDivisionを取得します。
	 * @return depositTargetDivision
	 */
	public BigDecimal getDepositTargetDivision() {
		return depositTargetDivision;
	}

	/**
	 * depositTargetDivisionを設定します。
	 * @param depositTargetDivision depositTargetDivision
	 */
	public void setDepositTargetDivision(final BigDecimal depositTargetDivision) {
		this.depositTargetDivision = depositTargetDivision;
	}

	/**
	 * depositUpdateDivisionを取得します。
	 * @return depositUpdateDivision
	 */
	public BigDecimal getDepositUpdateDivision() {
		return depositUpdateDivision;
	}

	/**
	 * depositUpdateDivisionを設定します。
	 * @param depositUpdateDivision depositUpdateDivision
	 */
	public void setDepositUpdateDivision(final BigDecimal depositUpdateDivision) {
		this.depositUpdateDivision = depositUpdateDivision;
	}

	/**
	 * drawalDateを取得します。
	 * @return drawalDate
	 */
	public Timestamp getDrawalDate() {
		return drawalDate;
	}

	/**
	 * drawalDateを設定します。
	 * @param drawalDate drawalDate
	 */
	public void setDrawalDate(final Timestamp drawalDate) {
		this.drawalDate = drawalDate;
	}

	/**
	 * eraserAmountを取得します。
	 * @return eraserAmount
	 */
	public String getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * eraserAmountを設定します。
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final String eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * eraserCompleteDateを取得します。
	 * @return eraserCompleteDate
	 */
	public Timestamp getEraserCompleteDate() {
		return eraserCompleteDate;
	}

	/**
	 * eraserCompleteDateを設定します。
	 * @param eraserCompleteDate eraserCompleteDate
	 */
	public void setEraserCompleteDate(final Timestamp eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * eraserCompleteDivisionを取得します。
	 * @return eraserCompleteDivision
	 */
	public BigDecimal getEraserCompleteDivision() {
		return eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDivisionを設定します。
	 * @param eraserCompleteDivision eraserCompleteDivision
	 */
	public void setEraserCompleteDivision(
			final BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * invoiceUpdateDateを取得します。
	 * @return invoiceUpdateDate
	 */
	public Timestamp getInvoiceUpdateDate() {
		return invoiceUpdateDate;
	}

	/**
	 * invoiceUpdateDateを設定します。
	 * @param invoiceUpdateDate invoiceUpdateDate
	 */
	public void setInvoiceUpdateDate(final Timestamp invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * issueDateを取得します。
	 * @return issueDate
	 */
	public Timestamp getIssueDate() {
		return issueDate;
	}

	/**
	 * issueDateを設定します。
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * issuedDivisionを取得します。
	 * @return issuedDivision
	 */
	public BigDecimal getIssuedDivision() {
		return issuedDivision;
	}

	/**
	 * issuedDivisionを設定します。
	 * @param issuedDivision issuedDivision
	 */
	public void setIssuedDivision(final BigDecimal issuedDivision) {
		this.issuedDivision = issuedDivision;
	}

	/**
	 * noteDateを取得します。
	 * @return noteDate
	 */
	public Timestamp getNoteDate() {
		return noteDate;
	}

	/**
	 * noteDateを設定します。
	 * @param noteDate noteDate
	 */
	public void setNoteDate(final Timestamp noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * noteNoを取得します。
	 * @return noteNo
	 */
	public String getNoteNo() {
		return noteNo;
	}

	/**
	 * noteNoを設定します。
	 * @param noteNo noteNo
	 */
	public void setNoteNo(final String noteNo) {
		this.noteNo = noteNo;
	}

	/**
	 * payableNoを取得します。
	 * @return payableNo
	 */
	public String getPayableNo() {
		return payableNo;
	}

	/**
	 * payableNoを設定します。
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * payableTargetDivisionを取得します。
	 * @return payableTargetDivision
	 */
	public BigDecimal getPayableTargetDivision() {
		return payableTargetDivision;
	}

	/**
	 * payableTargetDivisionを設定します。
	 * @param payableTargetDivision payableTargetDivision
	 */
	public void setPayableTargetDivision(final BigDecimal payableTargetDivision) {
		this.payableTargetDivision = payableTargetDivision;
	}

	/**
	 * payableUpdateDateを取得します。
	 * @return payableUpdateDate
	 */
	public Timestamp getPayableUpdateDate() {
		return payableUpdateDate;
	}

	/**
	 * payableUpdateDateを設定します。
	 * @param payableUpdateDate payableUpdateDate
	 */
	public void setPayableUpdateDate(final Timestamp payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * payableUpdateDivisionを取得します。
	 * @return payableUpdateDivision
	 */
	public BigDecimal getPayableUpdateDivision() {
		return payableUpdateDivision;
	}

	/**
	 * payableUpdateDivisionを設定します。
	 * @param payableUpdateDivision payableUpdateDivision
	 */
	public void setPayableUpdateDivision(final BigDecimal payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * paymentNoを取得します。
	 * @return paymentNo
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * paymentNoを設定します。
	 * @param paymentNo paymentNo
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * paymentTargetDivisionを取得します。
	 * @return paymentTargetDivision
	 */
	public BigDecimal getPaymentTargetDivision() {
		return paymentTargetDivision;
	}

	/**
	 * paymentTargetDivisionを設定します。
	 * @param paymentTargetDivision paymentTargetDivision
	 */
	public void setPaymentTargetDivision(final BigDecimal paymentTargetDivision) {
		this.paymentTargetDivision = paymentTargetDivision;
	}

	/**
	 * paymentUpdateDateを取得します。
	 * @return paymentUpdateDate
	 */
	public Timestamp getPaymentUpdateDate() {
		return paymentUpdateDate;
	}

	/**
	 * paymentUpdateDateを設定します。
	 * @param paymentUpdateDate paymentUpdateDate
	 */
	public void setPaymentUpdateDate(final Timestamp paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * paymentUpdateDivisionを取得します。
	 * @return paymentUpdateDivision
	 */
	public BigDecimal getPaymentUpdateDivision() {
		return paymentUpdateDivision;
	}

	/**
	 * paymentUpdateDivisionを設定します。
	 * @param paymentUpdateDivision paymentUpdateDivision
	 */
	public void setPaymentUpdateDivision(final BigDecimal paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public String getRowNo() {
		return rowNo;
	}

	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(final String rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * summaryを取得します。
	 * @return summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * summaryを設定します。
	 * @param summary summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * summaryCdを取得します。
	 * @return summaryCd
	 */
	public String getSummaryCd() {
		return summaryCd;
	}

	/**
	 * summaryCdを設定します。
	 * @param summaryCd summaryCd
	 */
	public void setSummaryCd(final String summaryCd) {
		this.summaryCd = summaryCd;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * approvalStatusLabelを取得します。
	 * @return approvalStatusLabel
	 */
	public String getApprovalStatusLabel() {
		return approvalStatusLabel;
	}

	/**
	 * approvalStatusLabelを設定します。
	 * @param approvalStatusLabel approvalStatusLabel
	 */
	public void setApprovalStatusLabel(final String approvalStatusLabel) {
		this.approvalStatusLabel = approvalStatusLabel;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * approvedByを取得します。
	 * @return approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * approvedByを設定します。
	 * @param approvedBy approvedBy
	 */
	public void setApprovedBy(final String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * accountsCdを取得します。
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return accountsCd;
	}

	/**
	 * accountsCdを設定します。
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsNameを取得します。
	 * @return accountsName
	 */
	public String getAccountsName() {
		return accountsName;
	}

	/**
	 * accountsNameを設定します。
	 * @param accountsName accountsName
	 */
	public void setAccountsName(final String accountsName) {
		this.accountsName = accountsName;
	}

	/**
	 * noteDivisionを取得します。
	 * @return noteDivision
	 */
	public BigDecimal getNoteDivision() {
		return noteDivision;
	}

	/**
	 * noteDivisionを設定します。
	 * @param noteDivision noteDivision
	 */
	public void setNoteDivision(final BigDecimal noteDivision) {
		this.noteDivision = noteDivision;
	}

	/**
	 * creditSectionCdを取得します。
	 * @return creditSectionCd
	 */
	public String getCreditSectionCd() {
		return creditSectionCd;
	}

	/**
	 * creditSectionCdを設定します。
	 * @param creditSectionCd creditSectionCd
	 */
	public void setCreditSectionCd(final String creditSectionCd) {
		this.creditSectionCd = creditSectionCd;
	}

	/**
	 * debitSectionCdを取得します。
	 * @return debitSectionCd
	 */
	public String getDebitSectionCd() {
		return debitSectionCd;
	}

	/**
	 * debitSectionCdを設定します。
	 * @param debitSectionCd debitSectionCd
	 */
	public void setDebitSectionCd(final String debitSectionCd) {
		this.debitSectionCd = debitSectionCd;
	}

	/**
	 * /** categoryNameを取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * categoryNameを設定します。
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * transmissionDateを取得します。
	 * @return transmissionDate
	 */
	public Timestamp getTransmissionDate() {
		return transmissionDate;
	}

	/**
	 * transmissionDateを設定します。
	 * @param transmissionDate transmissionDate
	 */
	public void setTransmissionDate(final Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	/**
	 * claimAmountを取得します。
	 * @return claimAmount
	 */
	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	/**
	 * claimAmountを設定します。
	 * @param claimAmount claimAmount
	 */
	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * salesAmountを取得します。
	 * @return salesAmount
	 */
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	/**
	 * salesAmountを設定します。
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}
}
