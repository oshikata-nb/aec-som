/*
 * Created on 2008/08/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.asahikaseieng.utils.ConstantFunction;

/**
 * 支払入力用Daoクラス.
 * @author tosco
 */
public class AltPaymentBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AltPaymentBase() {
	}

	/** TABLEアノテーション. */
	public static final String TABLE = "PAYMENT";

	// 列定義----------------------------------------------------------------------------
	/** ﾃﾞｰﾀ種別 4:支払（'4'固定） */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他） */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** 分類コード */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** 支払日付 */
	public static final String paymentDate_COLUMN = "PAYMENT_DATE";

	/** 伝票番号 */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** 行番号 */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** 仕入先コード */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** 部署コード */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** 支払金額 */
	public static final String paymentAmount_COLUMN = "PAYMENT_AMOUNT";

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

	/** 借方部門コード */
	public static final String debitSectionCd_COLUMN = "DEBIT_SECTION_CD";

	/** 借方科目コード */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** 借方補助科目コード */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** 貸方部門コード */
	public static final String creditSectionCd_COLUMN = "CREDIT_SECTION_CD";

	/** 貸方科目コード */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** 貸方補助科目コード */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** 摘要コード */
	// public static final String summaryCd_COLUMN = "SUMMARY_CD";
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

	/** 伝票発行日 */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
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

	/** 消込完了日 */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み) */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** 承認者 */
	public static final String approvedBy_COLUMN = "APPROVEDBY";

	/** 承認日 */
	public static final String approvalDate_COLUMN = "APPROVALDATE";

	/** データ転送日時 */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** 登録日時 */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** 登録者ID */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** 更新日時 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** 更新者ID */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** 支払分類名称 */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** 請求先名 */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** 請求先略称 */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	// インスタンスフィールド-----------------------------------------------------------------------------
	/** ﾃﾞｰﾀ種別 4:支払（'4'固定） */
	private String dataType;

	/** ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他） */
	private BigDecimal dataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 支払日付 */
	private Date paymentDate;

	/** 伝票番号 */
	private String slipNo;

	/** 行番号 */
	private String rowNo;

	/** 仕入先コード */
	private String supplierCd;

	/** 部署コード */
	private String organizationCd;

	/** 支払金額 */
	private String paymentAmount;

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
	// private String summaryCd;
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
	private Date issueDate;

	/** 伝票発行済フラグ(0：未発行、1：発行済、9：不要) */
	private BigDecimal issuedDivision;

	/** 売掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal depositUpdateDivision;

	/** 売掛番号 */
	private String depositNo;

	/** 売掛締め日 */
	private Date deliveryUpdateDate;

	/** 請求更新フラグ(0：未処理、1：処理済) */
	private BigDecimal claimUpdateDivision;

	/** 請求番号 */
	private String claimNo;

	/** 請求締め日 */
	private Date invoiceUpdateDate;

	/** 買掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal payableUpdateDivision;

	/** 買掛番号 */
	private String payableNo;

	/** 買掛締め日 */
	private Date payableUpdateDate;

	/** 支払更新フラグ(0：未処理、1：処理済) */
	private BigDecimal paymentUpdateDivision;

	/** 支払番号 */
	private String paymentNo;

	/** 支払締め日 */
	private Date paymentUpdateDate;

	/** 消込完了フラグ(0：未処理、1：処理済) */
	private BigDecimal eraserCompleteDivision;

	/** 消込完了日 */
	private Date eraserCompleteDate;

	/** 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み) */
	private String approvalStatus;

	/** 承認者 */
	private String approvedBy;

	/** 承認日 */
	private Date approvalDate;

	/** データ転送日時 */
	private Timestamp transmissionDate;

	/** 登録日時 */
	private Date inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Date updateDate;

	/** 更新者ID */
	private String updatorCd;

	/** 支払分類名称 */
	private String categoryName;

	/** 請求先名 */
	private String venderName;

	/** 請求先略称 */
	private String venderShortedName;

	/** 承認ステータスラベル */
	private String approvalStatusLabel;

	/** 仕入割引額 */
	private BigDecimal stockReduction;

	/** 振込手数料 */
	private BigDecimal transferFree;

	/** 支払予定日 */
	private Timestamp creditScheduledDate;

	/** 支払区分 */
	private String creditDivision;

	/** 前月繰越額 */
	private BigDecimal balanceForward;

	/** 今回仕入額 */
	private BigDecimal stockingAmount;

	/** その他仕入金額 */
	private BigDecimal otherStockingAmount;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 返品金額 */
	private BigDecimal stockingReturnedAmount;

	/** 値引金額 */
	private BigDecimal stockingDiscountAmount;

	/** 相殺 */
	private BigDecimal offsetAmount;

	/** 今回買掛金合計 */
	private BigDecimal accountPayableSum;

	/** 支払合計 */
	private BigDecimal commission;

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
	 * 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み)を取得します。
	 * @return 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み)
	 */
	public String getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み)を設定します。
	 * @param approvalStatus 承認者フラグ(1：入力中、2：承認依頼中、3：承認済み)
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
	 * 預金種別を取得します。
	 * @return 預金種別
	 */
	public String getAccountDivision() {
		return accountDivision;
	}

	/**
	 * 預金種別を設定します。
	 * @param accountDivision 預金種別
	 */
	public void setAccountDivision(final String accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * 口座番号を取得します。
	 * @return 口座番号
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * 口座番号を設定します。
	 * @param accountNo 口座番号
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 承認日を取得します。
	 * @return 承認日
	 */
	public Date getApprovalDate() {
		return approvalDate;
	}

	/**
	 * 承認日を設定します。
	 * @param approvalDate 承認日
	 */
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * 表示用承認者フラグを取得します。
	 * @return 表示用承認者フラグ
	 */
	public String getApprovalStatusLabel() {
		return approvalStatusLabel;
	}

	/**
	 * 表示用承認者フラグを設定します。
	 * @param approvalStatusLabel 表示用承認者フラグ
	 */
	public void setApprovalStatusLabel(final String approvalStatusLabel) {
		this.approvalStatusLabel = approvalStatusLabel;
	}

	/**
	 * 承認者を取得します。
	 * @return 承認者
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * 承認者を設定します。
	 * @param approvedBy 承認者
	 */
	public void setApprovedBy(final String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * 銀行コードを取得します。
	 * @return 銀行コード
	 */
	public String getBankCd() {
		return bankCd;
	}

	/**
	 * 銀行コードを設定します。
	 * @param bankCd 銀行コード
	 */
	public void setBankCd(final String bankCd) {
		this.bankCd = bankCd;
	}

	/**
	 * 分類コードを取得します。
	 * @return 分類コード
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * 分類コードを設定します。
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 支払分類名称を取得します。
	 * @return 支払分類名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 支払分類名称を設定します。
	 * @param categoryName 支払分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 請求番号を取得します。
	 * @return 請求番号
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * 請求番号を設定します。
	 * @param claimNo 請求番号
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * 請求対象(0：未処理、1：処理済、9：対象外)を取得します。
	 * @return 請求対象(0：未処理、1：処理済、9：対象外)
	 */
	public BigDecimal getClaimTargetDivision() {
		return claimTargetDivision;
	}

	/**
	 * 請求対象(0：未処理、1：処理済、9：対象外)を設定します。
	 * @param claimTargetDivision 請求対象(0：未処理、1：処理済、9：対象外)
	 */
	public void setClaimTargetDivision(final BigDecimal claimTargetDivision) {
		this.claimTargetDivision = claimTargetDivision;
	}

	/**
	 * 請求更新フラグ(0：未処理、1：処理済)を取得します。
	 * @return 請求更新フラグ(0：未処理、1：処理済)
	 */
	public BigDecimal getClaimUpdateDivision() {
		return claimUpdateDivision;
	}

	/**
	 * 請求更新フラグ(0：未処理、1：処理済)を設定します。
	 * @param claimUpdateDivision 請求更新フラグ(0：未処理、1：処理済)
	 */
	public void setClaimUpdateDivision(final BigDecimal claimUpdateDivision) {
		this.claimUpdateDivision = claimUpdateDivision;
	}

	/**
	 * 貸方部門を取得します。
	 * @return 貸方部門
	 */
	public String getCreditSectionCd() {
		return creditSectionCd;
	}

	/**
	 * 貸方部門を設定します。
	 * @param creditSectionCd 貸方部門
	 */
	public void setCreditSectionCd(final String creditSectionCd) {
		this.creditSectionCd = creditSectionCd;
	}

	/**
	 * 貸方補助科目コードを取得します。
	 * @return 貸方補助科目コード
	 */
	public String getCreditSubTitleCd() {
		return creditSubTitleCd;
	}

	/**
	 * 貸方補助科目コードを設定します。
	 * @param creditSubTitleCd 貸方補助科目コード
	 */
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * 貸方科目コードを取得します。
	 * @return 貸方科目コード
	 */
	public String getCreditTitleCd() {
		return creditTitleCd;
	}

	/**
	 * 貸方科目コードを設定します。
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）を取得します。
	 * @return ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）
	 */
	public BigDecimal getDataTotalDivision() {
		return dataTotalDivision;
	}

	/**
	 * ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）を設定します。
	 * @param dataTotalDivision ﾃﾞｰﾀ集計区分（1：支払、2：相殺、9：その他）
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * ﾃﾞｰﾀ種別 4:支払（’4’固定）を取得します。
	 * @return ﾃﾞｰﾀ種別 4:支払（’4’固定）
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * ﾃﾞｰﾀ種別 4:支払（’4’固定）を設定します。
	 * @param dataType ﾃﾞｰﾀ種別 4:支払（’4’固定）
	 */
	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}

	/**
	 * 借方部門を取得します。
	 * @return 借方部門
	 */
	public String getDebitSectionCd() {
		return debitSectionCd;
	}

	/**
	 * 借方部門を設定します。
	 * @param debitSectionCd 借方部門
	 */
	public void setDebitSectionCd(final String debitSectionCd) {
		this.debitSectionCd = debitSectionCd;
	}

	/**
	 * 借方補助科目コードを取得します。
	 * @return 借方補助科目コード
	 */
	public String getDebitSubTitleCd() {
		return debitSubTitleCd;
	}

	/**
	 * 借方補助科目コードを設定します。
	 * @param debitSubTitleCd 借方補助科目コード
	 */
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
	}

	/**
	 * 借方科目コードを取得します。
	 * @return 借方科目コード
	 */
	public String getDebitTitleCd() {
		return debitTitleCd;
	}

	/**
	 * 借方科目コードを設定します。
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 売掛締め日を取得します。
	 * @return 売掛締め日
	 */
	public Date getDeliveryUpdateDate() {
		return deliveryUpdateDate;
	}

	/**
	 * 売掛締め日を設定します。
	 * @param deliveryUpdateDate 売掛締め日
	 */
	public void setDeliveryUpdateDate(final Date deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
	}

	/**
	 * 売掛番号を取得します。
	 * @return 売掛番号
	 */
	public String getDepositNo() {
		return depositNo;
	}

	/**
	 * 売掛番号を設定します。
	 * @param depositNo 売掛番号
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * 売掛対象(0：未処理、1：処理済、9：対象外)を取得します。
	 * @return 売掛対象(0：未処理、1：処理済、9：対象外)
	 */
	public BigDecimal getDepositTargetDivision() {
		return depositTargetDivision;
	}

	/**
	 * 売掛対象(0：未処理、1：処理済、9：対象外)を設定します。
	 * @param depositTargetDivision 売掛対象(0：未処理、1：処理済、9：対象外)
	 */
	public void setDepositTargetDivision(final BigDecimal depositTargetDivision) {
		this.depositTargetDivision = depositTargetDivision;
	}

	/**
	 * 売掛更新フラグ(0：未処理、1：処理済)を取得します。
	 * @return 売掛更新フラグ(0：未処理、1：処理済)
	 */
	public BigDecimal getDepositUpdateDivision() {
		return depositUpdateDivision;
	}

	/**
	 * 売掛更新フラグ(0：未処理、1：処理済)を設定します。
	 * @param depositUpdateDivision 売掛更新フラグ(0：未処理、1：処理済)
	 */
	public void setDepositUpdateDivision(final BigDecimal depositUpdateDivision) {
		this.depositUpdateDivision = depositUpdateDivision;
	}

	/**
	 * 登録日時を取得します。
	 * @return 登録日時
	 */
	public Date getInputDate() {
		return inputDate;
	}

	/**
	 * 登録日時を設定します。
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Date inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者IDを取得します。
	 * @return 登録者ID
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者IDを設定します。
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 請求締め日を取得します。
	 * @return 請求締め日
	 */
	public Date getInvoiceUpdateDate() {
		return invoiceUpdateDate;
	}

	/**
	 * 請求締め日を設定します。
	 * @param invoiceUpdateDate 請求締め日
	 */
	public void setInvoiceUpdateDate(final Date invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * 伝票発行日を取得します。
	 * @return 伝票発行日
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * 伝票発行日を設定します。
	 * @param issueDate 伝票発行日
	 */
	public void setIssueDate(final Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * 伝票発行済フラグ(0：未発行、1：発行済、9：不要)を取得します。
	 * @return 伝票発行済フラグ(0：未発行、1：発行済、9：不要)
	 */
	public BigDecimal getIssuedDivision() {
		return issuedDivision;
	}

	/**
	 * 伝票発行済フラグ(0：未発行、1：発行済、9：不要)を設定します。
	 * @param issuedDivision 伝票発行済フラグ(0：未発行、1：発行済、9：不要)
	 */
	public void setIssuedDivision(final BigDecimal issuedDivision) {
		this.issuedDivision = issuedDivision;
	}

	/**
	 * 買掛番号を取得します。
	 * @return 買掛番号
	 */
	public String getPayableNo() {
		return payableNo;
	}

	/**
	 * 買掛番号を設定します。
	 * @param payableNo 買掛番号
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * 買掛対象(0：未処理、1：処理済、9：対象外)を取得します。
	 * @return 買掛対象(0：未処理、1：処理済、9：対象外)
	 */
	public BigDecimal getPayableTargetDivision() {
		return payableTargetDivision;
	}

	/**
	 * 買掛対象(0：未処理、1：処理済、9：対象外)を設定します。
	 * @param payableTargetDivision 買掛対象(0：未処理、1：処理済、9：対象外)
	 */
	public void setPayableTargetDivision(final BigDecimal payableTargetDivision) {
		this.payableTargetDivision = payableTargetDivision;
	}

	/**
	 * 買掛締め日を取得します。
	 * @return 買掛締め日
	 */
	public Date getPayableUpdateDate() {
		return payableUpdateDate;
	}

	/**
	 * 買掛締め日を設定します。
	 * @param payableUpdateDate 買掛締め日
	 */
	public void setPayableUpdateDate(final Date payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * 買掛更新フラグ(0：未処理、1：処理済)を取得します。
	 * @return 買掛更新フラグ(0：未処理、1：処理済)
	 */
	public BigDecimal getPayableUpdateDivision() {
		return payableUpdateDivision;
	}

	/**
	 * 買掛更新フラグ(0：未処理、1：処理済)を設定します。
	 * @param payableUpdateDivision 買掛更新フラグ(0：未処理、1：処理済)
	 */
	public void setPayableUpdateDivision(final BigDecimal payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * 支払金額を取得します。
	 * @return 支払金額
	 */
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * 支払金額を設定します。
	 * @param paymentAmount 支払金額
	 */
	public void setPaymentAmount(final String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * 支払日付を取得します。
	 * @return 支払日付
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * 支払日付を設定します。
	 * @param paymentDate 支払日付
	 */
	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * 支払番号を取得します。
	 * @return 支払番号
	 */
	public String getPaymentNo() {
		return paymentNo;
	}

	/**
	 * 支払番号を設定します。
	 * @param paymentNo 支払番号
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * 支払対象(0：未処理、1：処理済、9：対象外)を取得します。
	 * @return 支払対象(0：未処理、1：処理済、9：対象外)
	 */
	public BigDecimal getPaymentTargetDivision() {
		return paymentTargetDivision;
	}

	/**
	 * 支払対象(0：未処理、1：処理済、9：対象外)を設定します。
	 * @param paymentTargetDivision 支払対象(0：未処理、1：処理済、9：対象外)
	 */
	public void setPaymentTargetDivision(final BigDecimal paymentTargetDivision) {
		this.paymentTargetDivision = paymentTargetDivision;
	}

	/**
	 * 支払締め日を取得します。
	 * @return 支払締め日
	 */
	public Date getPaymentUpdateDate() {
		return paymentUpdateDate;
	}

	/**
	 * 消込完了フラグ(0：未処理、1：処理済)を取得します。
	 * @return eraserCompleteDivision
	 */
	public BigDecimal getEraserCompleteDivision() {
		return eraserCompleteDivision;
	}

	/**
	 * 消込完了フラグ(0：未処理、1：処理済)を設定します。
	 * @param eraserCompleteDivision 消込完了フラグ(0：未処理、1：処理済)
	 */
	public void setEraserCompleteDivision(
			final BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * 消込完了日を取得します。
	 * @return eraserCompleteDate
	 */
	public Date getEraserCompleteDate() {
		return eraserCompleteDate;
	}

	/**
	 * 消込完了日を設定します。
	 * @param eraserCompleteDate 消込完了日
	 */
	public void setEraserCompleteDate(final Date eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * 支払締め日を設定します。
	 * @param paymentUpdateDate 支払締め日
	 */
	public void setPaymentUpdateDate(final Date paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * 支払更新フラグ(0：未処理、1：処理済)を取得します。
	 * @return 支払更新フラグ(0：未処理、1：処理済)
	 */
	public BigDecimal getPaymentUpdateDivision() {
		return paymentUpdateDivision;
	}

	/**
	 * 支払更新フラグ(0：未処理、1：処理済)を設定します。
	 * @param paymentUpdateDivision 支払更新フラグ(0：未処理、1：処理済)
	 */
	public void setPaymentUpdateDivision(final BigDecimal paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

	/**
	 * 行番号を取得します。
	 * @return 行番号
	 */
	public String getRowNo() {
		return rowNo;
	}

	/**
	 * 行番号を設定します。
	 * @param rowNo 行番号
	 */
	public void setRowNo(final String rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * 部署コードを取得します。
	 * @return 部署コード
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 伝票番号を取得します。
	 * @return 伝票番号
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 伝票番号を設定します。
	 * @param slipNo 伝票番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 摘要コードを取得します。
	 * @return 摘要コード
	 */
	// public String getSummaryCd() {
	// return summaryCd;
	// }
	/**
	 * 摘要コードを設定します。
	 * @param summaryCd 摘要コード
	 */
	// public void setSummaryCd(final String summaryCd) {
	// this.summaryCd = summaryCd;
	// }
	/**
	 * 摘要名を取得します。
	 * @return 摘要名
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * 摘要名を設定します。
	 * @param summary 摘要名
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * 仕入先コードを取得します。
	 * @return 仕入先コード
	 */
	public String getSupplierCd() {
		return supplierCd;
	}

	/**
	 * 仕入先コードを設定します。
	 * @param supplierCd 仕入先コード
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * 更新日時を取得します。
	 * @return 更新日時
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者IDを取得します。
	 * @return 更新者ID
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者IDを設定します。
	 * @param updatorCd 更新者ID
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * 取引先名称を取得します。
	 * @return 取引先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 取引先名称を設定します。
	 * @param venderName 取引先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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
	 * 振出日を取得します。
	 * @return 振出日
	 */
	public Timestamp getDrawalDate() {
		return drawalDate;
	}

	/**
	 * 振出日を設定します。
	 * @param drawalDate 振出日
	 */
	public void setDrawalDate(final Timestamp drawalDate) {
		this.drawalDate = drawalDate;
	}

	/**
	 * 満期日を取得します。
	 * @return 満期日
	 */
	public Timestamp getNoteDate() {
		return noteDate;
	}

	/**
	 * 満期日を設定します。
	 * @param noteDate 満期日
	 */
	public void setNoteDate(final Timestamp noteDate) {
		this.noteDate = noteDate;
	}

	/**
	 * 手形種別を取得します。
	 * @return 手形種別
	 */
	public BigDecimal getNoteDivision() {
		return noteDivision;
	}

	/**
	 * 手形種別を設定します。
	 * @param noteDivision 手形種別
	 */
	public void setNoteDivision(final BigDecimal noteDivision) {
		this.noteDivision = noteDivision;
	}

	/**
	 * 手形番号を取得します。
	 * @return noteNo
	 */
	public String getNoteNo() {
		return noteNo;
	}

	/**
	 * 手形番号を設定します。
	 * @param noteNo 手形番号
	 */
	public void setNoteNo(final String noteNo) {
		this.noteNo = noteNo;
	}

	/**
	 * stockReductionを取得します。
	 * @return stockReduction
	 */
	public BigDecimal getStockReduction() {
		return stockReduction;
	}

	/**
	 * stockReductionを設定します。
	 * @param stockReduction stockReduction
	 */
	public void setStockReduction(final BigDecimal stockReduction) {
		this.stockReduction = stockReduction;
	}

	/**
	 * accountPayableSumを取得します。
	 * @return accountPayableSum
	 */
	public BigDecimal getAccountPayableSum() {
		return accountPayableSum;
	}

	/**
	 * accountPayableSumを設定します。
	 * @param accountPayableSum accountPayableSum
	 */
	public void setAccountPayableSum(final BigDecimal accountPayableSum) {
		this.accountPayableSum = accountPayableSum;
	}

	/**
	 * balanceForwardを取得します。
	 * @return balanceForward
	 */
	public BigDecimal getBalanceForward() {
		return balanceForward;
	}

	/**
	 * balanceForwardを設定します。
	 * @param balanceForward balanceForward
	 */
	public void setBalanceForward(final BigDecimal balanceForward) {
		this.balanceForward = balanceForward;
	}

	/**
	 * commissionを取得します。
	 * @return commission
	 */
	public BigDecimal getCommission() {
		return commission;
	}

	/**
	 * commissionを設定します。
	 * @param commission commission
	 */
	public void setCommission(final BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * creditDivisionを取得します。
	 * @return creditDivision
	 */
	public String getCreditDivision() {
		return creditDivision;
	}

	/**
	 * creditDivisionを設定します。
	 * @param creditDivision creditDivision
	 */
	public void setCreditDivision(final String creditDivision) {
		this.creditDivision = creditDivision;
	}

	/**
	 * creditScheduledDateを取得します。
	 * @return creditScheduledDate
	 */
	public Timestamp getCreditScheduledDate() {
		return creditScheduledDate;
	}

	/**
	 * creditScheduledDateを設定します。
	 * @param creditScheduledDate creditScheduledDate
	 */
	public void setCreditScheduledDate(final Timestamp creditScheduledDate) {
		this.creditScheduledDate = creditScheduledDate;
	}

	/**
	 * offsetAmountを取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * offsetAmountを設定します。
	 * @param offsetAmount offsetAmount
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * otherStockingAmountを取得します。
	 * @return otherStockingAmount
	 */
	public BigDecimal getOtherStockingAmount() {
		return otherStockingAmount;
	}

	/**
	 * otherStockingAmountを設定します。
	 * @param otherStockingAmount otherStockingAmount
	 */
	public void setOtherStockingAmount(final BigDecimal otherStockingAmount) {
		this.otherStockingAmount = otherStockingAmount;
	}

	/**
	 * stockingAmountを取得します。
	 * @return stockingAmount
	 */
	public BigDecimal getStockingAmount() {
		return stockingAmount;
	}

	/**
	 * stockingAmountを設定します。
	 * @param stockingAmount stockingAmount
	 */
	public void setStockingAmount(final BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * stockingDiscountAmountを取得します。
	 * @return stockingDiscountAmount
	 */
	public BigDecimal getStockingDiscountAmount() {
		return stockingDiscountAmount;
	}

	/**
	 * stockingDiscountAmountを設定します。
	 * @param stockingDiscountAmount stockingDiscountAmount
	 */
	public void setStockingDiscountAmount(
			final BigDecimal stockingDiscountAmount) {
		this.stockingDiscountAmount = stockingDiscountAmount;
	}

	/**
	 * stockingReturnedAmountを取得します。
	 * @return stockingReturnedAmount
	 */
	public BigDecimal getStockingReturnedAmount() {
		return stockingReturnedAmount;
	}

	/**
	 * stockingReturnedAmountを設定します。
	 * @param stockingReturnedAmount stockingReturnedAmount
	 */
	public void setStockingReturnedAmount(
			final BigDecimal stockingReturnedAmount) {
		this.stockingReturnedAmount = stockingReturnedAmount;
	}

	/**
	 * taxAmountを取得します。
	 * @return taxAmount
	 */
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	/**
	 * taxAmountを設定します。
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * transferFreeを取得します。
	 * @return transferFree
	 */
	public BigDecimal getTransferFree() {
		return transferFree;
	}

	/**
	 * transferFreeを設定します。
	 * @param transferFree transferFree
	 */
	public void setTransferFree(final BigDecimal transferFree) {
		this.transferFree = transferFree;
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
}
