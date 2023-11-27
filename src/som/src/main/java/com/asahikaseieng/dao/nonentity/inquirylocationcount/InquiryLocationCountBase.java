/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirylocationcount;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InquiryLocationCountクラス.
 * @author t0011036
 */
public class InquiryLocationCountBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InquiryLocationCountBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション locationCount */
	public static final String locationCount_COLUMN = "LOCATION_COUNT";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal locationCount;

	//
	// インスタンスメソッド
	//

	/**
	 * locationCount取得.
	 * @return locationCount
	 */
	public java.math.BigDecimal getLocationCount() {
		return this.locationCount;
	}

	/**
	 * locationCount設定.
	 * @param locationCount locationCount
	 */
	public void setLocationCount(final java.math.BigDecimal locationCount) {
		this.locationCount = locationCount;
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

