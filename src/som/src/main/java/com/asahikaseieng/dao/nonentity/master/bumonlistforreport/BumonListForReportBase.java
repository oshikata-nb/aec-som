/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BumonListForReportクラス.
 * @author t0011036
 */
public class BumonListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BumonListForReportBase() {
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

	/** COLUMNアノテーション sectionKanaName */
	public static final String sectionKanaName_COLUMN = "SECTION_KANA_NAME";

	/** COLUMNアノテーション accountCd */
	public static final String accountCd_COLUMN = "ACCOUNT_CD";

	/** COLUMNアノテーション accountSectionCd */
	public static final String accountSectionCd_COLUMN = "ACCOUNT_SECTION_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String sectionCd;

	private String sectionName;

	private String sectionShortedName;

	private String sectionKanaName;

	private String accountCd;

	private String accountSectionCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String inputorName;

	private String updatorName;

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
	 * sectionKanaName取得.
	 * @return sectionKanaName
	 */
	public String getSectionKanaName() {
		return this.sectionKanaName;
	}

	/**
	 * sectionKanaName設定.
	 * @param sectionKanaName sectionKanaName
	 */
	public void setSectionKanaName(final String sectionKanaName) {
		this.sectionKanaName = sectionKanaName;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

