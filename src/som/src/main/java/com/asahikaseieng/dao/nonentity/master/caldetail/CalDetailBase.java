/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CalDetailクラス.
 * @author t0011036
 */
public class CalDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CalDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション calCd */
	public static final String calCd_COLUMN = "CAL_CD";

	//
	// インスタンスフィールド
	//

	private String calCd;

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

