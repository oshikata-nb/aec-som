/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyupdate;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InoutMonthlyUpdateクラス.
 * @author t0011036
 */
public class InoutMonthlyUpdateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyUpdateBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション cnt */
	public static final String cnt_COLUMN = "CNT";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal cnt;

	//
	// インスタンスメソッド
	//

	/**
	 * cnt取得.
	 * @return cnt
	 */
	public java.math.BigDecimal getCnt() {
		return this.cnt;
	}

	/**
	 * cnt設定.
	 * @param cnt cnt
	 */
	public void setCnt(final java.math.BigDecimal cnt) {
		this.cnt = cnt;
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

