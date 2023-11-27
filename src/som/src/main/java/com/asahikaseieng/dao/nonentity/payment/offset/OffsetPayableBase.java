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
 * 買掛リスト取得用Daoクラス.
 * @author tosco
 */
public class OffsetPayableBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetPayableBase() {
		this.strPayableOffset = "0";
	}

	//
	// 定数
	//
	/** TABLEアノテーション. */
	public static final String TABLE = "PAYABLE_HEADER";

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション supplierCd */
	public static final String supplierCd_COLUMN = "SUPPLIER_CD";

	/** COLUMNアノテーション supplierName */
	public static final String supplierName_COLUMN = "SUPPLIER_NAME";

	/** COLUMNアノテーション payableAmount */
	public static final String payableAmount_COLUMN = "PAYABLE_AMOUNT";

	/** COLUMNアノテーション accountPayableBreakdown */
	public static final String accountPayableBreakdown_COLUMN = "ACCOUNT_PAYABLE_BREAKDOWN";

	/** COLUMNアノテーション arrearageBreakdown */
	public static final String arrearageBreakdown_COLUMN = "ARREARAGE_BREAKDOWN";

	/** COLUMNアノテーション exceptBreakdown */
	public static final String exceptBreakdown_COLUMN = "EXCEPT_BREAKDOWN";

	/** COLUMNアノテーション offsetAmount */
	public static final String offsetAmount_COLUMN = "OFFSET_AMOUNT";

	/** COLUMNアノテーション totalPayableAmount */
	public static final String totalPayableAmount_COLUMN = "TOTAL_PAYABLE_AMOUNT";

	/** COLUMNアノテーション payableDate */
	public static final String payableDate_COLUMN = "PAYABLE_DATE";

	//
	// インスタンスフィールド	//
	/** 買掛番号 */	private String payableNo;
	/** 部署コード */
	private String organizationCd;
	/** 支払先コード */
	private String supplierCd;
	/** 支払先名称 */
	private String supplierName;
	/** 買掛残 */
	private BigDecimal payableAmount;
	/** 相殺金額 */
	private BigDecimal offsetAmount;
	/** 買掛金(内訳) */
	private BigDecimal accountPayableBreakdown;
	/** 未払金(内訳) */
	private BigDecimal arrearageBreakdown;
	/** 以外(内訳) */
	private BigDecimal exceptBreakdown;
	/** 入力用　相殺金額*/
	private String strPayableOffset;
	/** 売掛残合計 */
	private BigDecimal totalPayableAmount;
	/** 売掛締め日 */
	private Date payableDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 買掛番号を取得します。
	 * @return payableNo
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
	 * 部署コードを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部門コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 支払先コードを取得します。
	 * @return supplierCd
	 */
	public String getSupplierCd() {
		return supplierCd;
	}

	/**
	 * 支払先コードを設定します。
	 * @param supplierCd 支払先コード
	 */
	public void setSupplierCd(final String supplierCd) {
		this.supplierCd = supplierCd;
	}

	/**
	 * 支払先名称を取得します。
	 * @return supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 支払先名称を設定します。
	 * @param supplierName 支払先名称
	 */
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 買掛残を取得します。
	 * @return payableAmount
	 */
	public BigDecimal getPayableAmount() {
		return payableAmount;
	}

	/**
	 * 買掛残を設定します。
	 * @param payableAmount 買掛残
	 */
	public void setPayableAmount(final BigDecimal payableAmount) {
		this.payableAmount = payableAmount;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return accountPayableBreakdown
	 */
	public BigDecimal getAccountPayableBreakdown() {
		return accountPayableBreakdown;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param accountPayableBreakdown 買掛金(内訳)
	 */
	public void setAccountPayableBreakdown(final BigDecimal accountPayableBreakdown) {
		this.accountPayableBreakdown = accountPayableBreakdown;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return arrearageBreakdown
	 */
	public BigDecimal getArrearageBreakdown() {
		return arrearageBreakdown;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param arrearageBreakdown 未払金(内訳)
	 */
	public void setArrearageBreakdown(final BigDecimal arrearageBreakdown) {
		this.arrearageBreakdown = arrearageBreakdown;
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
	 * @return strPayableOffset
	 */
	public String getStrPayableOffset() {
		return strPayableOffset;
	}

	/**
	 * 入力用：相殺金額を設定します。
	 * @param strPayableOffset 入力用：相殺金額
	 */
	public void setStrPayableOffset(final String strPayableOffset) {
		this.strPayableOffset = strPayableOffset;
	}

	/**
	 * 売掛残合計を取得します。
	 * @return totalPayableAmount
	 */
	public BigDecimal getTotalPayableAmount() {
		return totalPayableAmount;
	}

	/**
	 * 売掛残合計を設定します。
	 * @param totalPayableAmount 売掛残合計
	 */
	public void setTotalPayableAmount(final BigDecimal totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	/**
	 * 売掛締め日を取得します。
	 * @return payableDate
	 */
	public Date getPayableDate() {
		return payableDate;
	}

	/**
	 * 売掛締め日を設定します。
	 * @param payableDate 売掛締め日
	 */
	public void setPayableDate(final Date payableDate) {
		this.payableDate = payableDate;
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

