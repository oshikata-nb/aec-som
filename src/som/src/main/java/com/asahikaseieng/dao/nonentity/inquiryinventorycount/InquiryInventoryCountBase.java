/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinventorycount;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InquiryInventoryCountクラス.
 * @author t0011036
 */
public class InquiryInventoryCountBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryInventoryCountBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inventoryCount */
	public static final String inventoryCount_COLUMN = "INVENTORY_COUNT";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal inventoryCount;

	//
	// インスタンスメソッド
	//

	/**
	 * inventoryCount取得.
	 * @return inventoryCount
	 */
	public java.math.BigDecimal getInventoryCount() {
		return this.inventoryCount;
	}

	/**
	 * inventoryCount設定.
	 * @param inventoryCount inventoryCount
	 */
	public void setInventoryCount(final java.math.BigDecimal inventoryCount) {
		this.inventoryCount = inventoryCount;
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

