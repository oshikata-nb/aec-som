/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimateduplicatelist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * EstimateDuplicateListクラス.
 * @author t0011036
 */
public class EstimateDuplicateListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateDuplicateListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション estimateNo */
	public static final String estimateNo_COLUMN = "ESTIMATE_NO";

	//
	// インスタンスフィールド
	//

	private String estimateNo;

	//
	// インスタンスメソッド
	//

	/**
	 * estimateNo取得.
	 * @return estimateNo
	 */
	public String getEstimateNo() {
		return this.estimateNo;
	}

	/**
	 * estimateNo設定.
	 * @param estimateNo estimateNo
	 */
	public void setEstimateNo(final String estimateNo) {
		this.estimateNo = estimateNo;
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

