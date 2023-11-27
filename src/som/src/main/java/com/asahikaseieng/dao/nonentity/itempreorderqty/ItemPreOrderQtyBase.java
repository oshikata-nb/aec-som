/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Thu Mar 12 19:39:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.itempreorderqty;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ItemInventoryBaseクラス.
 * @author a1053739
 */
public class ItemPreOrderQtyBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ItemPreOrderQtyBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション itemCd. */
	public static final String preOrderQty_COLUMN = "PRE_ORDER_QTY";

	//
	// インスタンスフィールド
	//
	private java.math.BigDecimal preOrderQty;


	//
	// インスタンスメソッド
	//

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

	/**
	 * pointingOrderQtyを取得します。
	 * @return pointingOrderQty
	 */
	public java.math.BigDecimal getPreOrderQty() {
		return preOrderQty;
	}

	/**
	 * pointingOrderQtyを設定します。
	 * @param pointingOrderQty pointingOrderQty
	 */
	public void setPreOrderQty(java.math.BigDecimal preOrderQty) {
		this.preOrderQty = preOrderQty;
	}

}
