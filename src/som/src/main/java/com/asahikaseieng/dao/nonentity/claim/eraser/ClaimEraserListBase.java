/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 消込入力用Daoクラス.
 * @author tosco
 */
public class ClaimEraserListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CLAIM_HEADER";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション eraserDate */
	public static final String eraserDate_COLUMN = "ERASER_DATE";

	/** COLUMNアノテーション eraserNo */
	public static final String eraserNo_COLUMN = "ERASER_NO";

	/** COLUMNアノテーション eraserBalanceAmount */
	public static final String eraserBalanceAmount_COLUMN = "ERASER_BALANCE_AMOUNT";

	/** COLUMNアノテーション eraserAmount */
	public static final String eraserAmount_COLUMN = "ERASER_AMOUNT";

	/** COLUMNアノテーション creditEraserAmount */
	public static final String creditEraserAmount_COLUMN = "CREDIT_ERASER_AMOUNT";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	//
	// インスタンスフィールド
	//

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 消込日付 */
	private Timestamp eraserDate;

	/** 消込番号 */
	private String eraserNo;

	/** 消込残合計 */
	private BigDecimal eraserBalanceAmount;

	/** 消込金額 */
	private BigDecimal eraserAmount;

	/** 入金消込残合計 */
	private BigDecimal creditEraserAmount;

	/** 承認ステータス */
	private BigDecimal approvalStatus;

	//
	// インスタンスメソッド
	//

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
	 * 請求先名称取得.
	 * @return String 請求先名称
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称設定.
	 * @param venderName 請求先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 消込日付取得.
	 * @return Date 消込日付
	 */
	public Timestamp getEraserDate() {
		return eraserDate;
	}

	/**
	 * 消込日付設定.
	 * @param eraserDate 消込日付
	 */
	public void setEraserDate(final Timestamp eraserDate) {
		this.eraserDate = eraserDate;
	}

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
	 * 消込残合計取得.
	 * @return BigDecimal 消込残合計
	 */
	public BigDecimal getEraserBalanceAmount() {
		return eraserBalanceAmount;
	}

	/**
	 * 消込残合計設定.
	 * @param eraserBalanceAmount 消込残合計
	 */
	public void setEraserBalanceAmount(final BigDecimal eraserBalanceAmount) {
		this.eraserBalanceAmount = eraserBalanceAmount;
	}

	/**
	 * 消込金額取得.
	 * @return BigDecimal 消込残合計
	 */
	public BigDecimal getEraserAmount() {
		return eraserAmount;
	}

	/**
	 * 消込金額設定.
	 * @param eraserAmount 消込金額
	 */
	public void setEraserAmount(final BigDecimal eraserAmount) {
		this.eraserAmount = eraserAmount;
	}

	/**
	 * 入金消込残合計取得.
	 * @return BigDecimal 入金消込残合計
	 */
	public BigDecimal getCreditEraserAmount() {
		return creditEraserAmount;
	}

	/**
	 * 入金消込残合計設定.
	 * @param creditEraserAmount 入金消込残合計
	 */
	public void setCreditEraserAmount(final BigDecimal creditEraserAmount) {
		this.creditEraserAmount = creditEraserAmount;
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

