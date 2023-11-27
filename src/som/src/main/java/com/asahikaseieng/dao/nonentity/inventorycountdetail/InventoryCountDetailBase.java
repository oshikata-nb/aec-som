/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorycountdetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryCountDetailクラス.
 * @author t0011036
 */
public class InventoryCountDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryCountDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション countDate */
	public static final String countDate_COLUMN = "COUNT_DATE";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp countDate;

	//
	// インスタンスメソッド
	//

	/**
	 * countDate取得.
	 * @return countDate
	 */
	public java.sql.Timestamp getCountDate() {
		return this.countDate;
	}

	/**
	 * countDate設定.
	 * @param countDate countDate
	 */
	public void setCountDate(final java.sql.Timestamp countDate) {
		this.countDate = countDate;
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

