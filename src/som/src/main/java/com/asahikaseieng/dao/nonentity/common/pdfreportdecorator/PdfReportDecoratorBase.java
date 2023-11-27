/*
 * Created on 2022/05/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.pdfreportdecorator;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * PdfReportDecoratorクラス.
 * @author S.Fujimaki
 */
public class PdfReportDecoratorBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PdfReportDecoratorBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション seq */
	public static final String pdfReportSeq_COLUMN = "SEQ";

	
	// インスタンスフィールド
	//
	private BigDecimal seq;

	//
	// インスタンスメソッド
	//

	/**
	 * シーケンスを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * シーケンスを設定します。
	 * @param seq seq
	 */
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
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
