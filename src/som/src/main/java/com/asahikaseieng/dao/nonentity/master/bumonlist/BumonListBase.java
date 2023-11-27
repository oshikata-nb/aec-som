/*
 * Created on 2009/05/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BumonListクラス.
 * @author t0011036
 */
public class BumonListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BumonListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	/** COLUMNアノテーション shortSectionName */
	public static final String shortSectionName_COLUMN = "SHORT_SECTION_NAME";

	/** COLUMNアノテーション sectionShortedName */
	public static final String sectionShortedName_COLUMN = "SECTION_SHORTED_NAME";

	/** COLUMNアノテーション shortSectionShortedName */
	public static final String shortSectionShortedName_COLUMN = "SHORT_SECTION_SHORTED_NAME";

	//
	// インスタンスフィールド
	//

	private String sectionCd;

	private String sectionName;

	private String shortSectionName;

	private String sectionShortedName;

	private String shortSectionShortedName;

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
	 * shortSectionName取得.
	 * @return shortSectionName
	 */
	public String getShortSectionName() {
		return this.shortSectionName;
	}

	/**
	 * shortSectionName設定.
	 * @param shortSectionName shortSectionName
	 */
	public void setShortSectionName(final String shortSectionName) {
		this.shortSectionName = shortSectionName;
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
	 * shortSectionShortedName取得.
	 * @return shortSectionShortedName
	 */
	public String getShortSectionShortedName() {
		return this.shortSectionShortedName;
	}

	/**
	 * shortSectionShortedName設定.
	 * @param shortSectionShortedName shortSectionShortedName
	 */
	public void setShortSectionShortedName(final String shortSectionShortedName) {
		this.shortSectionShortedName = shortSectionShortedName;
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

