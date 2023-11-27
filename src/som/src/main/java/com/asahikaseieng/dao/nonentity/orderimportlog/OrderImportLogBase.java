/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlog;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportListクラス.
 * @author 
 */
public class OrderImportLogBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportLogBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション frstOrder */
	public static final String frstOrder_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション seq */
	public static final String sendLogSeq_COLUMN = "SEQ";
	/** COLUMNアノテーション logCls */
	public static final String logCls_COLUMN = "LOG_CLS";
	
	// インスタンスフィールド
	//
	private BigDecimal seq;
	
	//
	// インスタンスメソッド
	//


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
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
}


