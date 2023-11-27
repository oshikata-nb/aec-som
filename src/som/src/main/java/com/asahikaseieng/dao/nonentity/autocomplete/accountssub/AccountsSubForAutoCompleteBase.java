/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accountssub;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AccountsSubForAutoCompleteクラス.
 * @author t0011036
 */
public class AccountsSubForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AccountsSubForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション accountsSubCd */
	public static final String accountsSubCd_COLUMN = "ACCOUNTS_SUB_CD";

	/** COLUMNアノテーション accountsSubName */
	public static final String accountsSubName_COLUMN = "ACCOUNTS_SUB_NAME";

	//
	// インスタンスフィールド
	//

	private String accountsSubCd;

	private String accountsSubName;

	//
	// インスタンスメソッド
	//

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

