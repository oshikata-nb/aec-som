/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.company;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CompanyForAutoCompleteクラス.
 * @author t0011036
 */
public class CompanyForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CompanyForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション homeName1 */
	public static final String homeName1_COLUMN = "HOME_NAME1";

	//
	// インスタンスフィールド
	//

	private String companyCd;

	private String homeName1;

	//
	// インスタンスメソッド
	//

	/**
	 * companyCd取得.
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return this.companyCd;
	}

	/**
	 * companyCd設定.
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * homeName1取得.
	 * @return homeName1
	 */
	public String getHomeName1() {
		return this.homeName1;
	}

	/**
	 * homeName1設定.
	 * @param homeName1 homeName1
	 */
	public void setHomeName1(final String homeName1) {
		this.homeName1 = homeName1;
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

