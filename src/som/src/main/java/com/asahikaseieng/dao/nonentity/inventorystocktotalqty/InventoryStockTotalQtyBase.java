/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocktotalqty;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryStockTotalQtyクラス.
 * @author t0011036
 */
public class InventoryStockTotalQtyBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryStockTotalQtyBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal inventoryQty;

	//
	// インスタンスメソッド
	//

	/**
	 * inventoryQty取得.
	 * @return inventoryQty
	 */
	public java.math.BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * inventoryQty設定.
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
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

