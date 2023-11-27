/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * グループ間相殺トランザクション用Daoクラス.
 * @author tosco
 */
public class OffsetTranDataBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetTranDataBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "OFFSET_GROUP_DATA";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTypeDivi */
	public static final String dataTypeDivi_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivi */
	public static final String categoryDivi_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション debitSectionCd */
	public static final String debitSectionCd_COLUMN = "DEBIT_SECTION_CD";

	/** COLUMNアノテーション debitAccounts */
	public static final String debitAccounts_COLUMN = "DEBIT_ACCOUNTS_CD";

	/** COLUMNアノテーション titlePayable */
	public static final String titlePayable_COLUMN = "DEBIT_ACCOUNTS_CD_NAME";

	/** COLUMNアノテーション creditSectionCd */
	public static final String creditSectionCd_COLUMN = "CREDIT_SECTION_CD";

	/** COLUMNアノテーション creditAccounts */
	public static final String creditAccounts_COLUMN = "CREDIT_ACCOUNTS_CD";

	/** COLUMNアノテーション titleCredit */
	public static final String titleCredit_COLUMN = "CREDIT_ACCOUNTS_CD_NAME";

	/** COLUMNアノテーション offsetNo */
	public static final String offsetNo_COLUMN = "OFFSET_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	// /** COLUMNアノテーション accountSectionCd */
	// public static final String accountSectionCd_COLUMN =
	// "ACCOUNT_SECTION_CD";

	/** COLUMNアノテーション offsetGroupCd */
	public static final String offsetGroupCd_COLUMN = "OFFSET_GROUP_CD";

	/** COLUMNアノテーション offsetGroupName */
	public static final String offsetGroupName_COLUMN = "OFFSET_GROUP_NAME";

	/** COLUMNアノテーション offsetDate */
	public static final String offsetDate_COLUMN = "OFFSET_DATE";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション srhSummaryCd */
	public static final String srhSummaryCd_COLUMN = "SUMMARY_CD";

	/** COLUMNアノテーション srhSummary */
	public static final String srhSummary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション transmissionDate */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション payableOffsetAmount */
	public static final String payableOffsetAmount_COLUMN = "PAYABLE_OFFSET_AMOUNT";

	/** COLUMNアノテーション depositOffsetAmount */
	public static final String depositOffsetAmount_COLUMN = "DEPOSIT_OFFSET_AMOUNT";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitSubTitleCd */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditSubTitleCd */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** COLUMNアノテーション depositTargetDivi */
	public static final String depositTargetDivi_COLUMN = "DEPOSIT_TARGET_DIVISION";

	/** COLUMNアノテーション claimTargetDivi */
	public static final String claimTargetDivi_COLUMN = "CLAIM_TARGET_DIVISION";

	/** COLUMNアノテーション payableTargetDivi */
	public static final String payableTargetDivi_COLUMN = "PAYABLE_TARGET_DIVISION";

	/** COLUMNアノテーション paymentTargetDivi */
	public static final String paymentTargetDivi_COLUMN = "PAYMENT_TARGET_DIVISION";

	/** COLUMNアノテーション depositUpdateDivi */
	public static final String depositUpdateDivi_COLUMN = "DEPOSIT_UPDATE_DIVISION";

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション deliveryUpdateDate */
	public static final String deliveryUpdateDate_COLUMN = "DELIVERY_UPDATE_DATE";

	/** COLUMNアノテーション claimUpdateDivi */
	public static final String claimUpdateDivi_COLUMN = "CLAIM_UPDATE_DIVISION";

	/** COLUMNアノテーション claimDate */
	public static final String claimDate_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション invoiceUpdateDate */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション payableUpdateDivi */
	public static final String payableUpdateDivi_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション payableUpdateDate */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** COLUMNアノテーション paymentUpdateDivi */
	public static final String paymentUpdateDivi_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** COLUMNアノテーション paymentNo */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション paymentUpdateDate */
	public static final String paymentUpdateDate_COLUMN = "PAYMENT_UPDATE_DATE";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション creditEraserAmount */
	public static final String creditEraserAmount_COLUMN = "CREDIT_ERASER_AMOUNT";

	/** COLUMNアノテーション eraserCompleteDivi */
	public static final String eraserCompleteDivi_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvalDate */
	public static final String approvalDate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション 登録日時 */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション 登録者ＩＤ */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション 更新日時 */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション 更新者ＩＤ */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** ﾃﾞｰﾀ種別'5'固定 */
	private BigDecimal dataType;

	/** データ集計区分 */
	private BigDecimal dataTypeDivi;

	/** 分類コード */
	private String categoryDivi;

	/** 分類名称 */
	private String categoryName;

	/** 借方勘定科目 */
	private String debitAccounts;

	/** 貸方勘定科目 */
	private String creditAccounts;

	/** 相殺番号 */
	private String offsetNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	// /** 会計部門 */
	// private String accountSectionCd;
	/** 相殺グループコード */
	private String offsetGroupCd;

	/** 相殺グループ名称 */
	private String offsetGroupName;

	/** 相殺日付 */
	private Date offsetDate;

	/** 取引先区分(TS：得意先、SI：仕入先) */
	private String venderDivision;

	/** 請求先/支払先コード */
	private String venderCd;

	/** 請求先/支払先名称 */
	private String venderName1;

	/** 摘要コード */
	private String srhSummaryCd;

	/** 摘要名 */
	private String srhSummary;

	/** データ転送日時 */
	private Timestamp transmissionDate;

	/** 相殺金額 */
	private BigDecimal offsetAmount;

	/** 買掛相殺金額 */
	private BigDecimal payableOffsetAmount;

	/** 売掛相殺金額 */
	private BigDecimal depositOffsetAmount;

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

	/** 売掛対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal depositTargetDivi;

	/** 請求対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal claimTargetDivi;

	/** 買掛対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal payableTargetDivi;

	/** 支払対象(0：未処理、1：処理済、9：対象外) */
	private BigDecimal paymentTargetDivi;

	/** 売掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal depositUpdateDivi;

	/** 売掛番号 */
	private String depositNo;

	/** 売掛締め日 */
	private Date deliveryUpdateDate;

	/** 請求更新フラグ(0：未処理、1：処理済) */
	private BigDecimal claimUpdateDivi;

	/** 請求番号 */
	private String claimDate;

	/** 請求締め日 */
	private Date invoiceUpdateDate;

	/** 買掛更新フラグ(0：未処理、1：処理済) */
	private BigDecimal payableUpdateDivi;

	/** 買掛番号 */
	private String payableNo;

	/** 買掛締め日 */
	private Date payableUpdateDate;

	/** 支払更新フラグ(0：未処理、1：処理済) */
	private BigDecimal paymentUpdateDivi;

	/** 支払番号 */
	private String paymentNo;

	/** 支払締め日 */
	private Date paymentUpdateDate;

	/** 消込額 */
	private BigDecimal eraserAmount;

	/** 入金消込残 */
	private BigDecimal creditEraserAmount;

	/** 消込完了フラグ(0：未処理、1：処理済) */
	private BigDecimal eraserCompleteDivi;

	/** 消込完了日 */
	private Date eraserCompleteDate;

	/** 承認ステータス(1：入力中、2：承認依頼中、3：承認済み) */
	private BigDecimal approvalStatus;

	/** 承認者 */
	private String approvedby;

	/** 承認日 */
	private Date approvalDate;

	/** 登録日時 */
	private java.sql.Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private java.sql.Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	/** 買掛側タイトル */
	private String titlePayable;

	/** 売掛側タイトル */
	private String titleCredit;

	/** 部門コード */
	private String sectionCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 相殺番号を取得します。
	 * @return offsetNo
	 */
	public String getOffsetNo() {
		return offsetNo;
	}

	/**
	 * 相殺番号を設定します。
	 * @param offsetNo 相殺番号
	 */
	public void setOffsetNo(final String offsetNo) {
		this.offsetNo = offsetNo;
	}

	/**
	 * 部署コードを取得します。
	 * @return organizationCd
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
	 * 部署名称を取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 相殺グループコードを取得します。
	 * @return offsetGroupCd
	 */
	public String getOffsetGroupCd() {
		return offsetGroupCd;
	}

	/**
	 * 相殺グループコードを設定します。
	 * @param offsetGroupCd 相殺グループコード
	 */
	public void setOffsetGroupCd(final String offsetGroupCd) {
		this.offsetGroupCd = offsetGroupCd;
	}

	/**
	 * 相殺グループ名称を取得します。
	 * @return offsetGroupName
	 */
	public String getOffsetGroupName() {
		return offsetGroupName;
	}

	/**
	 * 相殺グループ名称を設定します。
	 * @param offsetGroupName 相殺グループ名称
	 */
	public void setOffsetGroupName(final String offsetGroupName) {
		this.offsetGroupName = offsetGroupName;
	}

	/**
	 * 分類名称を取得します。
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分類名称を設定します。
	 * @param categoryName 分類名称
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 貸方勘定科目を取得します。
	 * @return creditAccounts
	 */
	public String getCreditAccounts() {
		return creditAccounts;
	}

	/**
	 * 貸方勘定科目を設定します。
	 * @param creditAccounts 貸方勘定科目
	 */
	public void setCreditAccounts(final String creditAccounts) {
		this.creditAccounts = creditAccounts;
	}

	/**
	 * 借方勘定科目を取得します。
	 * @return debitAccounts
	 */
	public String getDebitAccounts() {
		return debitAccounts;
	}

	/**
	 * 借方勘定科目を設定します。
	 * @param debitAccounts 借方勘定科目
	 */
	public void setDebitAccounts(final String debitAccounts) {
		this.debitAccounts = debitAccounts;
	}

	/**
	 * 相殺締め日を取得します。
	 * @return offsetDate
	 */
	public Date getOffsetDate() {
		return offsetDate;
	}

	/**
	 * 相殺締め日を設定します。
	 * @param offsetDate 相殺締め日
	 */
	public void setOffsetDate(final Date offsetDate) {
		this.offsetDate = offsetDate;
	}

	/**
	 * 登録日時取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ＩＤ取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ＩＤ設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ＩＤ取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ＩＤ設定.
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

	// /**
	// * accountSectionCdを取得します。
	// * @return accountSectionCd
	// */
	// public String getAccountSectionCd() {
	// return accountSectionCd;
	// }

	// /**
	// * accountSectionCdを設定します。
	// * @param accountSectionCd accountSectionCd
	// */
	// public void setAccountSectionCd(final String accountSectionCd) {
	// this.accountSectionCd = accountSectionCd;
	// }

	/**
	 * approvalDateを取得します。
	 * @return approvalDate
	 */
	public Date getApprovalDate() {
		return approvalDate;
	}

	/**
	 * approvalDateを設定します。
	 * @param approvalDate approvalDate
	 */
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	/**
	 * approvalStatusを取得します。
	 * @return approvalStatus
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * approvalStatusを設定します。
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvedbyを取得します。
	 * @return approvedby
	 */
	public String getApprovedby() {
		return approvedby;
	}

	/**
	 * approvedbyを設定します。
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * categoryDiviを取得します。
	 * @return categoryDivi
	 */
	public String getCategoryDivi() {
		return categoryDivi;
	}

	/**
	 * categoryDiviを設定します。
	 * @param categoryDivi categoryDivi
	 */
	public void setCategoryDivi(final String categoryDivi) {
		this.categoryDivi = categoryDivi;
	}

	/**
	 * claimDateを取得します。
	 * @return claimDate
	 */
	public String getClaimDate() {
		return claimDate;
	}

	/**
	 * claimDateを設定します。
	 * @param claimDate claimDate
	 */
	public void setClaimDate(final String claimDate) {
		this.claimDate = claimDate;
	}

	/**
	 * claimTargetDiviを取得します。
	 * @return claimTargetDivi
	 */
	public BigDecimal getClaimTargetDivi() {
		return claimTargetDivi;
	}

	/**
	 * claimTargetDiviを設定します。
	 * @param claimTargetDivi claimTargetDivi
	 */
	public void setClaimTargetDivi(final BigDecimal claimTargetDivi) {
		this.claimTargetDivi = claimTargetDivi;
	}

	/**
	 * claimUpdateDiviを取得します。
	 * @return claimUpdateDivi
	 */
	public BigDecimal getClaimUpdateDivi() {
		return claimUpdateDivi;
	}

	/**
	 * claimUpdateDiviを設定します。
	 * @param claimUpdateDivi claimUpdateDivi
	 */
	public void setClaimUpdateDivi(final BigDecimal claimUpdateDivi) {
		this.claimUpdateDivi = claimUpdateDivi;
	}

	/**
	 * creditEraserAmountを取得します。
	 * @return creditEraserAmount
	 */
	public BigDecimal getCreditEraserAmount() {
		return creditEraserAmount;
	}

	/**
	 * creditEraserAmountを設定します。
	 * @param creditEraserAmount creditEraserAmount
	 */
	public void setCreditEraserAmount(final BigDecimal creditEraserAmount) {
		this.creditEraserAmount = creditEraserAmount;
	}

	/**
	 * creditSectionCdを取得します。
	 * @return debitSectionCd
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
	 * dataTypeを取得します。
	 * @return dataType
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * dataTypeを設定します。
	 * @param dataType dataType
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * dataTypeDiviを取得します。
	 * @return dataTypeDivi
	 */
	public BigDecimal getDataTypeDivi() {
		return dataTypeDivi;
	}

	/**
	 * dataTypeDiviを設定します。
	 * @param dataTypeDivi dataTypeDivi
	 */
	public void setDataTypeDivi(final BigDecimal dataTypeDivi) {
		this.dataTypeDivi = dataTypeDivi;
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
	public Date getDeliveryUpdateDate() {
		return deliveryUpdateDate;
	}

	/**
	 * deliveryUpdateDateを設定します。
	 * @param deliveryUpdateDate deliveryUpdateDate
	 */
	public void setDeliveryUpdateDate(final Date deliveryUpdateDate) {
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
	 * depositOffsetAmountを取得します。
	 * @return depositOffsetAmount
	 */
	public BigDecimal getDepositOffsetAmount() {
		return depositOffsetAmount;
	}

	/**
	 * depositOffsetAmountを設定します。
	 * @param depositOffsetAmount depositOffsetAmount
	 */
	public void setDepositOffsetAmount(final BigDecimal depositOffsetAmount) {
		this.depositOffsetAmount = depositOffsetAmount;
	}

	/**
	 * depositTargetDiviを取得します。
	 * @return depositTargetDivi
	 */
	public BigDecimal getDepositTargetDivi() {
		return depositTargetDivi;
	}

	/**
	 * depositTargetDiviを設定します。
	 * @param depositTargetDivi depositTargetDivi
	 */
	public void setDepositTargetDivi(final BigDecimal depositTargetDivi) {
		this.depositTargetDivi = depositTargetDivi;
	}

	/**
	 * depositUpdateDiviを取得します。
	 * @return depositUpdateDivi
	 */
	public BigDecimal getDepositUpdateDivi() {
		return depositUpdateDivi;
	}

	/**
	 * depositUpdateDiviを設定します。
	 * @param depositUpdateDivi depositUpdateDivi
	 */
	public void setDepositUpdateDivi(final BigDecimal depositUpdateDivi) {
		this.depositUpdateDivi = depositUpdateDivi;
	}

	/**
	 * eraserAmountを取得します。
	 * @return eraserAmount
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * eraserAmountを設定します。
	 * @param eraserAmount eraserAmount
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * eraserCompleteDateを取得します。
	 * @return eraserCompleteDate
	 */
	public Date getEraserCompleteDate() {
		return eraserCompleteDate;
	}

	/**
	 * eraserCompleteDateを設定します。
	 * @param eraserCompleteDate eraserCompleteDate
	 */
	public void setEraserCompleteDate(final Date eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * eraserCompleteDiviを取得します。
	 * @return eraserCompleteDivi
	 */
	public BigDecimal getEraserCompleteDivi() {
		return eraserCompleteDivi;
	}

	/**
	 * eraserCompleteDiviを設定します。
	 * @param eraserCompleteDivi eraserCompleteDivi
	 */
	public void setEraserCompleteDivi(final BigDecimal eraserCompleteDivi) {
		this.eraserCompleteDivi = eraserCompleteDivi;
	}

	/**
	 * invoiceUpdateDateを取得します。
	 * @return invoiceUpdateDate
	 */
	public Date getInvoiceUpdateDate() {
		return invoiceUpdateDate;
	}

	/**
	 * invoiceUpdateDateを設定します。
	 * @param invoiceUpdateDate invoiceUpdateDate
	 */
	public void setInvoiceUpdateDate(final Date invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
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
	 * payableOffsetAmountを取得します。
	 * @return payableOffsetAmount
	 */
	public BigDecimal getPayableOffsetAmount() {
		return payableOffsetAmount;
	}

	/**
	 * payableOffsetAmountを設定します。
	 * @param payableOffsetAmount payableOffsetAmount
	 */
	public void setPayableOffsetAmount(final BigDecimal payableOffsetAmount) {
		this.payableOffsetAmount = payableOffsetAmount;
	}

	/**
	 * payableTargetDiviを取得します。
	 * @return payableTargetDivi
	 */
	public BigDecimal getPayableTargetDivi() {
		return payableTargetDivi;
	}

	/**
	 * payableTargetDiviを設定します。
	 * @param payableTargetDivi payableTargetDivi
	 */
	public void setPayableTargetDivi(final BigDecimal payableTargetDivi) {
		this.payableTargetDivi = payableTargetDivi;
	}

	/**
	 * payableUpdateDateを取得します。
	 * @return payableUpdateDate
	 */
	public Date getPayableUpdateDate() {
		return payableUpdateDate;
	}

	/**
	 * payableUpdateDateを設定します。
	 * @param payableUpdateDate payableUpdateDate
	 */
	public void setPayableUpdateDate(final Date payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * payableUpdateDiviを取得します。
	 * @return payableUpdateDivi
	 */
	public BigDecimal getPayableUpdateDivi() {
		return payableUpdateDivi;
	}

	/**
	 * payableUpdateDiviを設定します。
	 * @param payableUpdateDivi payableUpdateDivi
	 */
	public void setPayableUpdateDivi(final BigDecimal payableUpdateDivi) {
		this.payableUpdateDivi = payableUpdateDivi;
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
	 * paymentTargetDiviを取得します。
	 * @return paymentTargetDivi
	 */
	public BigDecimal getPaymentTargetDivi() {
		return paymentTargetDivi;
	}

	/**
	 * paymentTargetDiviを設定します。
	 * @param paymentTargetDivi paymentTargetDivi
	 */
	public void setPaymentTargetDivi(final BigDecimal paymentTargetDivi) {
		this.paymentTargetDivi = paymentTargetDivi;
	}

	/**
	 * paymentUpdateDateを取得します。
	 * @return paymentUpdateDate
	 */
	public Date getPaymentUpdateDate() {
		return paymentUpdateDate;
	}

	/**
	 * paymentUpdateDateを設定します。
	 * @param paymentUpdateDate paymentUpdateDate
	 */
	public void setPaymentUpdateDate(final Date paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * paymentUpdateDiviを取得します。
	 * @return paymentUpdateDivi
	 */
	public BigDecimal getPaymentUpdateDivi() {
		return paymentUpdateDivi;
	}

	/**
	 * paymentUpdateDiviを設定します。
	 * @param paymentUpdateDivi paymentUpdateDivi
	 */
	public void setPaymentUpdateDivi(final BigDecimal paymentUpdateDivi) {
		this.paymentUpdateDivi = paymentUpdateDivi;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
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
	 * venderNameを取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * 摘要名を取得します。
	 * @return srhSummary
	 */
	public String getSrhSummary() {
		return srhSummary;
	}

	/**
	 * 摘要名を設定します。
	 * @param srhSummary 摘要名
	 */
	public void setSrhSummary(final String srhSummary) {
		this.srhSummary = srhSummary;
	}

	/**
	 * 摘要コードを取得します。
	 * @return srhSummaryCd
	 */
	public String getSrhSummaryCd() {
		return srhSummaryCd;
	}

	/**
	 * 摘要コードを設定します。
	 * @param srhSummaryCd 摘要コード
	 */
	public void setSrhSummaryCd(final String srhSummaryCd) {
		this.srhSummaryCd = srhSummaryCd;
	}

	/**
	 * 相殺金額を取得します。
	 * @return offsetAmount
	 */
	public BigDecimal getOffsetAmount() {
		return offsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param offsetAmount 相殺金額
	 */
	public void setOffsetAmount(final BigDecimal offsetAmount) {
		this.offsetAmount = offsetAmount;
	}

	/**
	 * 売掛側タイトルを取得します。
	 * @return titleCredit
	 */
	public String getTitleCredit() {
		return titleCredit;
	}

	/**
	 * 売掛側タイトルを設定します。
	 * @param titleCredit 売掛側タイトル
	 */
	public void setTitleCredit(final String titleCredit) {
		this.titleCredit = titleCredit;
	}

	/**
	 * 買掛側タイトルを取得します。
	 * @return titlePayable
	 */
	public String getTitlePayable() {
		return titlePayable;
	}

	/**
	 * 買掛側タイトルを設定します。
	 * @param titlePayable 買掛側タイトル
	 */
	public void setTitlePayable(final String titlePayable) {
		this.titlePayable = titlePayable;
	}

	/**
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
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
