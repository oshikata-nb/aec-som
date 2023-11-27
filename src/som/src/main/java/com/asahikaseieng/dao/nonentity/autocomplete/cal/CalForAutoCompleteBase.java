/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.cal;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CalForAutoCompleteクラス.
 * @author t0011036
 */
public class CalForAutoCompleteBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CalForAutoCompleteBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calCd */
	public static final String calCd_COLUMN = "CAL_CD";

	/** COLUMNアノテーション calName */
	public static final String calName_COLUMN = "CAL_NAME";

	//
	// インスタンスフィールド
	//

	private String calCd;

	private String calName;

	//
	// インスタンスメソッド
	//

	/**
	 * calCd取得.
	 * @return calCd
	 */
	public String getCalCd() {
		return this.calCd;
	}

	/**
	 * calCd設定.
	 * @param calCd calCd
	 */
	public void setCalCd(final String calCd) {
		this.calCd = calCd;
	}

	/**
	 * calName取得.
	 * @return calName
	 */
	public String getCalName() {
		return this.calName;
	}

	/**
	 * calName設定.
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
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

