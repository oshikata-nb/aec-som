/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailgetmaxno;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RemarkDetailGetMaxRemarkNoクラス.
 * @author kanri-user
 */
public class RemarkDetailGetMaxRemarkNoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RemarkDetailGetMaxRemarkNoBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション max */
	public static final String max_COLUMN = "MAX";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal max;

	//
	// インスタンスメソッド
	//

	/**
	 * max取得.
	 * @return max
	 */
	public java.math.BigDecimal getMax() {
		return this.max;
	}

	/**
	 * max設定.
	 * @param max max
	 */
	public void setMax(final java.math.BigDecimal max) {
		this.max = max;
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

