/*
 * Created on 2009/09/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemQueueNewItemCdクラス.
 * @author t0011036
 */
public class ItemQueueNewItemCdBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueNewItemCdBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション nextval */
	public static final String nextval_COLUMN = "NEXTVAL";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal nextval;

	//
	// インスタンスメソッド
	//

	/**
	 * nextval取得.
	 * @return nextval
	 */
	public java.math.BigDecimal getNextval() {
		return this.nextval;
	}

	/**
	 * nextval設定.
	 * @param nextval nextval
	 */
	public void setNextval(final java.math.BigDecimal nextval) {
		this.nextval = nextval;
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

