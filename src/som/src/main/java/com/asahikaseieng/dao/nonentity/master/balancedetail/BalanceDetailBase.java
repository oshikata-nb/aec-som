/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BalanceDetailクラス.
 * @author t0011036
 */
public class BalanceDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BalanceDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション upperBalanceCd */
	public static final String upperBalanceCd_COLUMN = "UPPER_BALANCE_CD";

	/** COLUMNアノテーション shopLevel */
	public static final String shopLevel_COLUMN = "SHOP_LEVEL";

	/** COLUMNアノテーション shopLevelName */
	public static final String shopLevelName_COLUMN = "SHOP_LEVEL_NAME";

	/** COLUMNアノテーション balanceType */
	public static final String balanceType_COLUMN = "BALANCE_TYPE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String paymentInvoiceCd_COLUMN = "PAYMENT_INVOICE_CD";

	/** COLUMNアノテーション paymentInvoiceName */
	public static final String paymentInvoiceName_COLUMN = "PAYMENT_INVOICE_NAME";

	//
	// インスタンスフィールド
	//

	private String balanceCd;

	private String venderCd;

	private String upperBalanceCd;

	private java.math.BigDecimal shopLevel;

	private String shopLevelName;

	private java.math.BigDecimal balanceType;

	private java.sql.Timestamp updateDate;

	private String venderName1;

	private String paymentInvoiceCd;

	private String paymentInvoiceName;

	//
	// インスタンスメソッド
	//

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
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
	 * upperBalanceCd取得.
	 * @return upperBalanceCd
	 */
	public String getUpperBalanceCd() {
		return this.upperBalanceCd;
	}

	/**
	 * upperBalanceCd設定.
	 * @param upperBalanceCd upperBalanceCd
	 */
	public void setUpperBalanceCd(final String upperBalanceCd) {
		this.upperBalanceCd = upperBalanceCd;
	}

	/**
	 * shopLevel取得.
	 * @return shopLevel
	 */
	public java.math.BigDecimal getShopLevel() {
		return this.shopLevel;
	}

	/**
	 * shopLevel設定.
	 * @param shopLevel shopLevel
	 */
	public void setShopLevel(final java.math.BigDecimal shopLevel) {
		this.shopLevel = shopLevel;
	}

	/**
	 * shopLevelName取得.
	 * @return shopLevelName
	 */
	public String getShopLevelName() {
		return this.shopLevelName;
	}

	/**
	 * shopLevelName設定.
	 * @param shopLevelName shopLevelName
	 */
	public void setShopLevelName(final String shopLevelName) {
		this.shopLevelName = shopLevelName;
	}

	/**
	 * balanceType取得.
	 * @return balanceType
	 */
	public java.math.BigDecimal getBalanceType() {
		return this.balanceType;
	}

	/**
	 * balanceType設定.
	 * @param balanceType balanceType
	 */
	public void setBalanceType(final java.math.BigDecimal balanceType) {
		this.balanceType = balanceType;
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
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * paymentInvoiceCd取得.
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return this.paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCd設定.
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceName取得.
	 * @return paymentInvoiceName
	 */
	public String getPaymentInvoiceName() {
		return this.paymentInvoiceName;
	}

	/**
	 * paymentInvoiceName設定.
	 * @param paymentInvoiceName paymentInvoiceName
	 */
	public void setPaymentInvoiceName(final String paymentInvoiceName) {
		this.paymentInvoiceName = paymentInvoiceName;
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

