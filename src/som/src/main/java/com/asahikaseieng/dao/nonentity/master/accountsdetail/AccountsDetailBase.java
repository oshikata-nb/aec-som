/*
 * Created on 2009/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountsdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AccountsDetailクラス.
 * @author t0011036
 */
public class AccountsDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AccountsDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsName */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	/** COLUMNアノテーション taxationDivision */
	public static final String taxationDivision_COLUMN = "TAXATION_DIVISION";

	/** COLUMNアノテーション purchaseAccounts */
	public static final String purchaseAccounts_COLUMN = "PURCHASE_ACCOUNTS";

	/** COLUMNアノテーション articleAccounts */
	public static final String articleAccounts_COLUMN = "ARTICLE_ACCOUNTS";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String accountsCd;

	private String accountsName;

	private String taxationDivision;

	private java.math.BigDecimal purchaseAccounts;

	private java.math.BigDecimal articleAccounts;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * accountsCd取得.
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return this.accountsCd;
	}

	/**
	 * accountsCd設定.
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsName取得.
	 * @return accountsName
	 */
	public String getAccountsName() {
		return this.accountsName;
	}

	/**
	 * accountsName設定.
	 * @param accountsName accountsName
	 */
	public void setAccountsName(final String accountsName) {
		this.accountsName = accountsName;
	}

	/**
	 * taxationDivision取得.
	 * @return taxationDivision
	 */
	public String getTaxationDivision() {
		return this.taxationDivision;
	}

	/**
	 * taxationDivision設定.
	 * @param taxationDivision taxationDivision
	 */
	public void setTaxationDivision(final String taxationDivision) {
		this.taxationDivision = taxationDivision;
	}

	/**
	 * purchaseAccounts取得.
	 * @return purchaseAccounts
	 */
	public java.math.BigDecimal getPurchaseAccounts() {
		return this.purchaseAccounts;
	}

	/**
	 * purchaseAccounts設定.
	 * @param purchaseAccounts purchaseAccounts
	 */
	public void setPurchaseAccounts(final java.math.BigDecimal purchaseAccounts) {
		this.purchaseAccounts = purchaseAccounts;
	}

	/**
	 * articleAccounts取得.
	 * @return articleAccounts
	 */
	public java.math.BigDecimal getArticleAccounts() {
		return this.articleAccounts;
	}

	/**
	 * articleAccounts設定.
	 * @param articleAccounts articleAccounts
	 */
	public void setArticleAccounts(final java.math.BigDecimal articleAccounts) {
		this.articleAccounts = articleAccounts;
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

