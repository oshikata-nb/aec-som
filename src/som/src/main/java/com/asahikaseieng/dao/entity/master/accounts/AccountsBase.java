/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Fri May 08 14:29:27 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.accounts;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AccountsBaseクラス.
 * @author t0011036
 */
public class AccountsBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AccountsBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ACCOUNTS";


//	/** IDアノテーション accountsCd. */
//	public static final String accountsCd_ID = "assigned";

	/** COLUMNアノテーション accountsCd. */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsSubCd. */
	public static final String accountsSubCd_COLUMN = "ACCOUNTS_SUB_CD";

	/** COLUMNアノテーション accountsName. */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	/** COLUMNアノテーション accountsSubName. */
	public static final String accountsSubName_COLUMN = "ACCOUNTS_SUB_NAME";

	/** COLUMNアノテーション taxationDivision. */
	public static final String taxationDivision_COLUMN = "TAXATION_DIVISION";

	/** COLUMNアノテーション costAccounts. */
	public static final String costAccounts_COLUMN = "COST_ACCOUNTS";

	/** COLUMNアノテーション purchaseAccounts. */
	public static final String purchaseAccounts_COLUMN = "PURCHASE_ACCOUNTS";

	/** COLUMNアノテーション articleAccounts. */
	public static final String articleAccounts_COLUMN = "ARTICLE_ACCOUNTS";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String accountsCd;

	private String accountsSubCd;

	private String accountsName;

	private String accountsSubName;

	private String taxationDivision;

	private java.math.BigDecimal costAccounts;

	private java.math.BigDecimal purchaseAccounts;

	private java.math.BigDecimal articleAccounts;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * accountsSubCd取得.
	 * @return accountsSubCd
	 */
	public String getAccountsSubCd() {
		return this.accountsSubCd;
	}

	/**
	 * accountsSubCd設定.
	 * @param accountsSubCd accountsSubCd
	 */
	public void setAccountsSubCd(final String accountsSubCd) {
		this.accountsSubCd = accountsSubCd;
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
	 * accountsSubName取得.
	 * @return accountsSubName
	 */
	public String getAccountsSubName() {
		return this.accountsSubName;
	}

	/**
	 * accountsSubName設定.
	 * @param accountsSubName accountsSubName
	 */
	public void setAccountsSubName(final String accountsSubName) {
		this.accountsSubName = accountsSubName;
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
	 * costAccounts取得.
	 * @return costAccounts
	 */
	public java.math.BigDecimal getCostAccounts() {
		return this.costAccounts;
	}

	/**
	 * costAccounts設定.
	 * @param costAccounts costAccounts
	 */
	public void setCostAccounts(final java.math.BigDecimal costAccounts) {
		this.costAccounts = costAccounts;
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
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
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
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
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
}
