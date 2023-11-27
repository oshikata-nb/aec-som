/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.accounts;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AccountsForAutoCompleteクラス.
 * @author t0011036
 */
public class AccountsForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AccountsForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsName */
	public static final String accountsName_COLUMN = "ACCOUNTS_NAME";

	//
	// インスタンスフィールド
	//

	private String accountsCd;

	private String accountsName;

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

