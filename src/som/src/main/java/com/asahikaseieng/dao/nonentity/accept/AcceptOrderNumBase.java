/*
 * Created on 2009/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AcceptOrderNumクラス.
 * @author kanri-user
 */
public class AcceptOrderNumBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptOrderNumBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNum */
	public static final String orderNum_COLUMN = "ORDER_NUM";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal orderNum;

	//
	// インスタンスメソッド
	//

	/**
	 * orderNum取得.
	 * @return orderNum
	 */
	public java.math.BigDecimal getOrderNum() {
		return this.orderNum;
	}

	/**
	 * orderNum設定.
	 * @param orderNum orderNum
	 */
	public void setOrderNum(final java.math.BigDecimal orderNum) {
		this.orderNum = orderNum;
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

