/*
 * Created on 2009/07/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalesAccountYearsクラス.
 * @author kanri-user
 */
public class SalesAccountYearsBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesAccountYearsBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	//
	// インスタンスフィールド
	//

	private String accountYears;

	//
	// インスタンスメソッド
	//

	/**
	 * accountYears取得.
	 * @return accountYears
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * accountYears設定.
	 * @param accountYears accountYears
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
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

