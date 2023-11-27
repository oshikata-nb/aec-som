/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bumon;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 会計部門マスタオートコンプリートBeanクラス.
 * @author tosco
 */
public class BumonForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BumonForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション sectionName */
	public static final String sectionName_COLUMN = "SECTION_NAME";

	//
	// インスタンスフィールド
	//

	/** 会計部門コード */
	private String sectionCd;

	/** 会計部門名称 */
	private String sectionName;

	//
	// インスタンスメソッド
	//

	/**
	 * 会計部門コード取得.
	 * @return String 会計部門コード
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 会計部門コード設定.
	 * @param sectionCd 会計部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 会計部門名称取得.
	 * @return String 会計部門名称
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 会計部門名称設定.
	 * @param sectionName 会計部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
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

