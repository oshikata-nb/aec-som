/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 消込入力詳細(カスタマイズ) 消込トランザクション(カスタマイズ)登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserCsmBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserCsmBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_CSM";

	/** COLUMNアノテーション checkKbn */
	public static final String checkKbn_COLUMN = "CHECK_KBN";

	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション invoiceCd */
	public static final String invoiceCd_COLUMN = "INVOICE_CD";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション processingDate */
	public static final String processingDate_COLUMN = "PROCESSING_DATE";

	/** COLUMNアノテーション eraserObjectAmount */
	public static final String eraserObjectAmount_COLUMN = "ERASER_OBJECT_AMOUNT";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション lastEraserAmount */
	public static final String lastEraserAmount_COLUMN = "LAST_ERASER_AMOUNT";

	/** COLUMNアノテーション eraserBalanceAmount */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/** COLUMNアノテーション eraserCompleteDivision */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション invoiceUpdateDate */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション claimNo */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedBy */
	public static final String approvedBy_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvalDate */
	public static final String approvalDate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション eraserDate */
	public static final String eraserDate_COLUMN = "ERASER_DATE";

	/** COLUMNアノテーション eraserUpdateDate */
	public static final String eraserUpdateDate_COLUMN = "ERASER_UPDATE_DATE";

	/** COLUMNアノテーション eraserorCd */
	public static final String eraserorCd_COLUMN = "ERASEROR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderShortedName */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";

	//
	// インスタンスフィールド
	//

	/** チェック区分 */
	private String checkKbn;

	/** 部署コード */
	private String organizationCd;

	/** 請求先コード */
	private String invoiceCd;

	/** データ種別 */
	private BigDecimal dataType;

	/** 伝票番号 */
	private String slipNo;

	/** 処理日付 */
	private Date processingDate;

	/** 消込対象額 */
	private BigDecimal eraserObjectAmount;

	/** 消込額 */
	private BigDecimal eraserAmount;

	/** 前回消込額 */
	private BigDecimal lastEraserAmount;

	/** 消込残 */
	private BigDecimal eraserBalanceAmount;

	/** 消込完了フラグ */
	private BigDecimal eraserCompleteDivision;

	/** 消込完了日 */
	private Date eraserCompleteDate;

	/** 請求締め日 */
	private Date invoiceUpdateDate;

	/** 請求番号 */
	private String claimNo;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	/** 承認者 */
	private String approvedBy;

	/** 承認日 */
	private Timestamp approvalDate;

	/** 消込日付 */
	private Timestamp eraserDate;

	/** 消込更新日時 */
	private Timestamp eraserUpdateDate;

	/** 消込担当者ＩＤ */
	private String eraserorCd;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	/** 請求先名称 */
	private String venderName1;

	/** 請求先略称 */
	private String venderShortedName;

	//
	// インスタンスメソッド
	//

	/**
	 * チェック区分取得.
	 * @return String チェック区分
	 */
	public String getCheckKbn() {
		return checkKbn;
	}

	/**
	 * チェック区分設定.
	 * @param checkKbn チェック区分
	 */
	public void setCheckKbn(final String checkKbn) {
		this.checkKbn = checkKbn;
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
	 * 請求先コード取得.
	 * @return String 請求先コード
	 */
	public String getInvoiceCd() {
		return invoiceCd;
	}

	/**
	 * 請求先コード設定.
	 * @param invoiceCd 請求先コード
	 */
	public void setInvoiceCd(final String invoiceCd) {
		this.invoiceCd = invoiceCd;
	}

	/**
	 * データ種別取得.
	 * @return BigDecimal データ種別
	 */
	public BigDecimal getDataType() {
		return dataType;
	}

	/**
	 * データ種別設定.
	 * @param dataType データ種別
	 */
	public void setDataType(final BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * 伝票番号取得.
	 * @return String 伝票番号
	 */
	public String getSlipNo() {
		return slipNo;
	}

	/**
	 * 伝票番号設定.
	 * @param slipNo 伝票番号
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * 処理日付取得.
	 * @return Date 処理日付
	 */
	public Date getProcessingDate() {
		return processingDate;
	}

	/**
	 * 処理日付設定.
	 * @param processingDate 処理日付
	 */
	public void setProcessingDate(final Date processingDate) {
		this.processingDate = processingDate;
	}

	/**
	 * 消込対象額取得.
	 * @return BigDecimal 消込対象額
	 */
	public BigDecimal getEraserObjectAmount() {
		return eraserObjectAmount;
	}

	/**
	 * 消込対象額設定.
	 * @param eraserObjectAmount 消込対象額
	 */
	public void setEraserObjectAmount(final BigDecimal eraserObjectAmount) {
		this.eraserObjectAmount = eraserObjectAmount;
	}

	/**
	 * 消込額取得.
	 * @return BigDecimal 消込額
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * 消込額設定.
	 * @param eraserAmount 消込額
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * 前回消込額取得.
	 * @return BigDecimal 前回消込額
	 */
	public BigDecimal getLastEraserAmount() {
		return lastEraserAmount;
	}

	/**
	 * 前回消込額設定.
	 * @param lastEraserAmount 前回消込額
	 */
	public void setLastEraserAmount(final BigDecimal lastEraserAmount) {
		this.lastEraserAmount = lastEraserAmount;
	}

	/**
	 * 消込残取得.
	 * @return BigDecimal 消込残
	 */
	public BigDecimal getEraserBalanceAmount() {
		return eraserBalanceAmount;
	}

	/**
	 * 消込残設定.
	 * @param eraserBalanceAmount 消込残
	 */
	public void setEraserBalanceAmount(final BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * 消込完了フラグ取得.
	 * @return BigDecimal 消込完了フラグ
	 */
	public BigDecimal getEraserCompleteDivision() {
		return eraserCompleteDivision;
	}

	/**
	 * 消込完了フラグ設定.
	 * @param eraserCompleteDivision 消込完了フラグ
	 */
	public void setEraserCompleteDivision(
			final BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * 消込完了日取得.
	 * @return Date 消込完了日
	 */
	public Date getEraserCompleteDate() {
		return eraserCompleteDate;
	}

	/**
	 * 消込完了日設定.
	 * @param eraserCompleteDate 消込完了日
	 */
	public void setEraserCompleteDate(final Date eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * 請求締め日取得.
	 * @return Date 請求締め日
	 */
	public Date getInvoiceUpdateDate() {
		return invoiceUpdateDate;
	}

	/**
	 * 請求締め日設定.
	 * @param invoiceUpdateDate 請求締め日
	 */
	public void setInvoiceUpdateDate(final Date invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * 請求番号取得.
	 * @return String 請求番号
	 */
	public String getClaimNo() {
		return claimNo;
	}

	/**
	 * 請求番号設定.
	 * @param claimNo 請求番号
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * 承認ステータス取得.
	 * @return BigDecimal 承認ステータス
	 */
	public BigDecimal getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * 承認ステータス設定.
	 * @param approvalStatus 承認ステータス
	 */
	public void setApprovalStatus(final BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * 承認者ID取得.
	 * @return String 承認者ID
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * 承認者ID設定.
	 * @param approvedBy 承認者ID
	 */
	public void setApprovedBy(final String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * 消込更新日時を取得します。
	 * @return Timestamp 消込更新日時
	 */
	public Timestamp getEraserUpdateDate() {
		return eraserUpdateDate;
	}

	/**
	 * 消込更新日時を設定します。
	 * @param eraserUpdateDate 消込更新日時
	 */
	public void setEraserUpdateDate(final Timestamp eraserUpdateDate) {
		this.eraserUpdateDate = eraserUpdateDate;
	}

	/**
	 * 消込担当者ＩＤを取得します。
	 * @return String 消込担当者ＩＤ
	 */
	public String getEraserorCd() {
		return eraserorCd;
	}

	/**
	 * 消込担当者ＩＤを設定します。
	 * @param eraserorCd 消込担当者ＩＤ
	 */
	public void setEraserorCd(final String eraserorCd) {
		this.eraserorCd = eraserorCd;
	}

	/**
	 * 登録日時を取得します。
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * 登録日時を設定します。
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ＩＤを取得します。
	 * @return String 登録者ＩＤ
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * 登録者ＩＤを設定します。
	 * @param inputorCd 登録者ＩＤ
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時を取得します。
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日時を設定します。
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ＩＤを取得します。
	 * @return String 更新者ＩＤ
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * 更新者ＩＤを設定します。
	 * @param updatorCd 更新者ＩＤ
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
	 * eraserDateを取得します。
	 * @return eraserDate
	 */
	public Timestamp getEraserDate() {
		return eraserDate;
	}

	/**
	 * eraserDateを設定します。
	 * @param eraserDate eraserDate
	 */
	public void setEraserDate(final Timestamp eraserDate) {
		this.eraserDate = eraserDate;
	}

	/**
	 * approvalDateを取得します。
	 * @return approvalDate
	 */
	public Timestamp getApprovalDate() {
		return approvalDate;
	}

	/**
	 * approvalDateを設定します。
	 * @param approvalDate approvalDate
	 */
	public void setApprovalDate(final Timestamp approvalDate) {
		this.approvalDate = approvalDate;
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
}
