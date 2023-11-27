/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalestermsAndEstimateItemCheckクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateItemCheckBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateItemCheckBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション itemCount */
	public static final String itemCount_COLUMN = "ITEM_COUNT";

	//
	// インスタンスフィールド
	//

	private int itemCount;

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
	 * itemCountを取得します。
	 * @return itemCount
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * itemCountを設定します。
	 * @param itemCount itemCount
	 */
	public void setItemCount(final int itemCount) {
		this.itemCount = itemCount;
	}

}
