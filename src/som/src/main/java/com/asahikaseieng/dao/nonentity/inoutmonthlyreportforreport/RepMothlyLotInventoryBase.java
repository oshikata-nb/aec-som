/*
 * Created on 2009/10/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepMothlyLotInventoryクラス.
 * @author kanri-user
 */
public class RepMothlyLotInventoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepMothlyLotInventoryBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション targetMonth */
	public static final String targetMonth_COLUMN = "TARGET_MONTH";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal targetMonth;

	//
	// インスタンスメソッド
	//

	/**
	 * targetMonth取得.
	 * @return targetMonth
	 */
	public java.math.BigDecimal getTargetMonth() {
		return this.targetMonth;
	}

	/**
	 * targetMonth設定.
	 * @param targetMonth targetMonth
	 */
	public void setTargetMonth(final java.math.BigDecimal targetMonth) {
		this.targetMonth = targetMonth;
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

