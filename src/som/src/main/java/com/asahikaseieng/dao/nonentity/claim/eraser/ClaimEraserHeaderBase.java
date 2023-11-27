/*
 * Created on 2008/08/12
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
 * 消込入力詳細 消込ﾍｯﾀﾞｰ登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ERASER_HEADER";

	/** COLUMNアノテーション eraserNo */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション eraserDate */
	public static final String eraserDate_COLUMN = "ERASER_DATE";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedBy */
	public static final String approvedBy_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvalDate */
	public static final String approvalDate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 消込番号 */
	private String eraserNo;

	/** 部門コード */
	private String sectionCd;

	/** 請求先コード */
	private String venderCd;

	/** 消込日付 */
	private Date eraserDate;

	/** 消込額 */
	private BigDecimal eraserAmount;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	/** 承認者 */
	private String approvedBy;

	/** 承認日 */
	private Date approvalDate;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ＩＤ */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ＩＤ */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 消込番号取得.
	 * @return String 消込番号
	 */
	public String getEraserNo() {
		return eraserNo;
	}

	/**
	 * 消込番号設定.
	 * @param eraserNo 消込番号
	 */
	public void setEraserNo(final String eraserNo) {
		this.eraserNo = eraserNo;
	}

	/**
	 * 部門コード取得.
	 * @return String 部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コード設定.
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 請求先コード取得.
	 * @return String 請求先コード
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コード設定.
	 * @param venderCd 請求先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 消込日付取得.
	 * @return Date 消込日付
	 */
	public Date getEraserDate() {
		return eraserDate;
	}

	/**
	 * 消込日付設定.
	 * @param eraserDate 消込日付
	 */
	public void setEraserDate(final Date eraserDate) {
		this.eraserDate = eraserDate;
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
	 * 承認日取得.
	 * @return Date 承認日
	 */
	public Date getApprovalDate() {
		return approvalDate;
	}

	/**
	 * 承認日設定.
	 * @param approvalDate 承認日
	 */
	public void setApprovalDate(final Date approvalDate) {
		this.approvalDate = approvalDate;
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

}

