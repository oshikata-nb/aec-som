/*
 * Created on 2009/12/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salescancelcheckdeliveryupdate;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalesCancelCheckDeliveryUpdateクラス.
 * @author kanri-user
 */
public class SalesCancelCheckDeliveryUpdateBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesCancelCheckDeliveryUpdateBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション deliveryUpdateDate */
	public static final String deliveryUpdateDate_COLUMN = "DELIVERY_UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp deliveryUpdateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * deliveryUpdateDate取得.
	 * @return deliveryUpdateDate
	 */
	public java.sql.Timestamp getDeliveryUpdateDate() {
		return this.deliveryUpdateDate;
	}

	/**
	 * deliveryUpdateDate設定.
	 * @param deliveryUpdateDate deliveryUpdateDate
	 */
	public void setDeliveryUpdateDate(final java.sql.Timestamp deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
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

