/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * FinancialClassListクラス.
 * @author t0011036
 */
public class FinancialClassListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション financialClassCd */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション financialClassName */
	public static final String financialClassName_COLUMN = "FINANCIAL_CLASS_NAME";

	//
	// インスタンスフィールド
	//

	private String financialClassCd;

	private String financialClassName;

	//
	// インスタンスメソッド
	//

	/**
	 * financialClassCd取得.
	 * @return financialClassCd
	 */
	public String getFinancialClassCd() {
		return this.financialClassCd;
	}

	/**
	 * financialClassCd設定.
	 * @param financialClassCd financialClassCd
	 */
	public void setFinancialClassCd(final String financialClassCd) {
		this.financialClassCd = financialClassCd;
	}

	/**
	 * financialClassName取得.
	 * @return financialClassName
	 */
	public String getFinancialClassName() {
		return this.financialClassName;
	}

	/**
	 * financialClassName設定.
	 * @param financialClassName financialClassName
	 */
	public void setFinancialClassName(final String financialClassName) {
		this.financialClassName = financialClassName;
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

