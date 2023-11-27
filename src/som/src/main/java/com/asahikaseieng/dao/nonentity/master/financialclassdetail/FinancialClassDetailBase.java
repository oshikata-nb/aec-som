/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclassdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * FinancialClassDetailクラス.
 * @author t0011036
 */
public class FinancialClassDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public FinancialClassDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション financialClassCd */
	public static final String financialClassCd_COLUMN = "FINANCIAL_CLASS_CD";

	/** COLUMNアノテーション financialClassName */
	public static final String financialClassName_COLUMN = "FINANCIAL_CLASS_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String financialClassCd;

	private String financialClassName;

	private java.sql.Timestamp updateDate;

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

