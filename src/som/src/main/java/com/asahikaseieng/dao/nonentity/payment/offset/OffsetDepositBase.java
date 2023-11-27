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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 売掛取引先一覧用Daoクラス.
 * @author tosco
 */
public class OffsetDepositBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetDepositBase() {
		this.strDepositOffset = "0";
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DEPOSIT_HEADER";

	/** COLUMNアノテーション depositNo */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション customerCd */
	public static final String customerCd_COLUMN = "CUSTOMER_CD";

	/** COLUMNアノテーション customerName */
	public static final String customerName_COLUMN = "CUSTOMER_NAME";

	/** COLUMNアノテーション creditAmount */
	public static final String creditAmount_COLUMN = "CREDIT_AMOUNT";

	/** COLUMNアノテーション creditSalesBreakdown */
	public static final String creditSalesBreakdown_COLUMN = "CREDIT_SALES_BREAKDOWN";

	/** COLUMNアノテーション accruedDebitBreakdown */
	public static final String accruedDebitBreakdown_COLUMN = "ACCRUED_DEBIT_BREAKDOWN";

	/** COLUMNアノテーション exceptBreakdown */
	public static final String exceptBreakdown_COLUMN = "EXCEPT_BREAKDOWN";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション totalCreditAmount */
	public static final String totalCreditAmount_COLUMN = "TOTAL_CREDIT_AMOUNT";

	/** COLUMNアノテーション creditDate */
	public static final String creditDate_COLUMN = "CREDIT_DATE";

	//
	// インスタンスフィールド	//

	/** 売掛番号 */
	private String depositNo;
	/** 請求先コード */
	private String customerCd;
	/** 請求先名称 */
	private String customerName;
	/** 売掛残 */
	private BigDecimal creditAmount;
	/** 相殺金額 */
	private BigDecimal offsetAmount;
	/** 売掛金(内訳) */
	private BigDecimal creditSalesBreakdown;
	/** 未収金(内訳) */
	private BigDecimal accruedDebitBreakdown;
	/** 以外(内訳) */
	private BigDecimal exceptBreakdown;
	/** 入力用：相殺金額 */
	private String strDepositOffset;
	/** 売掛残合計 */
	private BigDecimal totalCreditAmount;
	/** 売掛締め日 */
	private Date creditDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 売掛残を取得します。
	 * @return creditAmount
	 */
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	/**
	 * 売掛残を設定します。
	 * @param creditAmount 売掛残
	 */
	public void setCreditAmount(final BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	/**
	 * 請求先コードを取得します。
	 * @return customerCd
	 */
	public String getCustomerCd() {
		return customerCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param customerCd 請求先コード
	 */
	public void setCustomerCd(final String customerCd) {
		this.customerCd = customerCd;
	}

	/**
	 * 請求先名称を取得します。
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 請求先名称を設定します。
	 * @param customerName 請求先名称
	 */
	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 売掛番号を取得します。
	 * @return depositNo
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
	 * 未収金(内訳)を取得します。
	 * @return accruedDebitBreakdown
	 */
	public BigDecimal getAccruedDebitBreakdown() {
		return accruedDebitBreakdown;
	}

	/**
	 * 未収金(内訳)を設定します。
	 * @param accruedDebitBreakdown 未収金(内訳)
	 */
	public void setAccruedDebitBreakdown(final BigDecimal accruedDebitBreakdown) {
		this.accruedDebitBreakdown = accruedDebitBreakdown;
	}

	/**
	 * 売掛金(内訳)を取得します。
	 * @return creditSalesBreakdown
	 */
	public BigDecimal getCreditSalesBreakdown() {
		return creditSalesBreakdown;
	}

	/**
	 * 売掛金(内訳)を設定します。
	 * @param creditSalesBreakdown 売掛金(内訳)
	 */
	public void setCreditSalesBreakdown(final BigDecimal creditSalesBreakdown) {
		this.creditSalesBreakdown = creditSalesBreakdown;
	}

	/**
	 * 以外(内訳)を取得します。
	 * @return exceptBreakdown
	 */
	public BigDecimal getExceptBreakdown() {
		return exceptBreakdown;
	}

	/**
	 * 以外(内訳)を設定します。
	 * @param exceptBreakdown 以外(内訳)
	 */
	public void setExceptBreakdown(final BigDecimal exceptBreakdown) {
		this.exceptBreakdown = exceptBreakdown;
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
	 * 入力用：相殺金額を取得します。
	 * @return strDepositOffset
	 */
	public String getStrDepositOffset() {
		return strDepositOffset;
	}

	/**
	 * 入力用：相殺金額を設定します。
	 * @param strDepositOffset 入力用：相殺金額
	 */
	public void setStrDepositOffset(final String strDepositOffset) {
		this.strDepositOffset = strDepositOffset;
	}

	/**
	 * 売掛残合計を取得します。
	 * @return totalCreditAmount
	 */
	public BigDecimal getTotalCreditAmount() {
		return totalCreditAmount;
	}

	/**
	 * 売掛残合計を設定します。
	 * @param totalCreditAmount 売掛残合計
	 */
	public void setTotalCreditAmount(final BigDecimal totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

	/**
	 * 売掛締め日を取得します。
	 * @return creditDate
	 */
	public Date getCreditDate() {
		return creditDate;
	}

	/**
	 * 売掛締め日を設定します。
	 * @param creditDate 売掛締め日
	 */
	public void setCreditDate(final Date creditDate) {
		this.creditDate = creditDate;
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

