/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslastseq;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * SalesTermsLastSeqクラス.
 * @author t0011036
 */
public class SalesTermsLastSeqBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsLastSeqBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション lastSeq */
	public static final String lastSeq_COLUMN = "LAST_SEQ";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal lastSeq;

	//
	// インスタンスメソッド
	//

	/**
	 * lastSeq取得.
	 * @return lastSeq
	 */
	public java.math.BigDecimal getLastSeq() {
		return this.lastSeq;
	}

	/**
	 * lastSeq設定.
	 * @param lastSeq lastSeq
	 */
	public void setLastSeq(final java.math.BigDecimal lastSeq) {
		this.lastSeq = lastSeq;
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

