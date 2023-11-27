/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumondetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BumonDetailクラス.
 * @author t0011036
 */
public class BumonDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BumonDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション sectionShortedName */
	public static final String sectionShortedName_COLUMN = "SECTION_SHORTED_NAME";

	/** COLUMNアノテーション accountCd */
	public static final String accountCd_COLUMN = "ACCOUNT_CD";

	/** COLUMNアノテーション accountSectionCd */
	public static final String accountSectionCd_COLUMN = "ACCOUNT_SECTION_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String sectionCd;

	private String sectionName;

	private String sectionShortedName;

	private String accountCd;

	private String accountSectionCd;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionName取得.
	 * @return sectionName
	 */
	public String getSectionName() {
		return this.sectionName;
	}

	/**
	 * sectionName設定.
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * sectionShortedName取得.
	 * @return sectionShortedName
	 */
	public String getSectionShortedName() {
		return this.sectionShortedName;
	}

	/**
	 * sectionShortedName設定.
	 * @param sectionShortedName sectionShortedName
	 */
	public void setSectionShortedName(final String sectionShortedName) {
		this.sectionShortedName = sectionShortedName;
	}

	/**
	 * accountCd取得.
	 * @return accountCd
	 */
	public String getAccountCd() {
		return this.accountCd;
	}

	/**
	 * accountCd設定.
	 * @param accountCd accountCd
	 */
	public void setAccountCd(final String accountCd) {
		this.accountCd = accountCd;
	}

	/**
	 * accountSectionCd取得.
	 * @return accountSectionCd
	 */
	public String getAccountSectionCd() {
		return this.accountSectionCd;
	}

	/**
	 * accountSectionCd設定.
	 * @param accountSectionCd accountSectionCd
	 */
	public void setAccountSectionCd(final String accountSectionCd) {
		this.accountSectionCd = accountSectionCd;
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

