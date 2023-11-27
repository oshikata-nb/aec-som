/*
 * Created on 2009/12/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsremarklist;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesTermsRemarkListクラス.
 * @author kanri-user
 */
public class SalesTermsRemarkListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsRemarkListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション remark01 */
	public static final String remark01_COLUMN = "REMARK01";

	/** COLUMNアノテーション remark15 */
	public static final String remark15_COLUMN = "REMARK15";

	//
	// インスタンスフィールド
	//

	private String remark01;

	private String remark15;

	//
	// インスタンスメソッド
	//

	/**
	 * remark01取得.
	 * @return remark01
	 */
	public String getRemark01() {
		return this.remark01;
	}

	/**
	 * remark01設定.
	 * @param remark01 remark01
	 */
	public void setRemark01(final String remark01) {
		this.remark01 = remark01;
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

	/**
	 * remark15を取得します。
	 * @return remark15
	 */
	public String getRemark15() {
		return remark15;
	}

	/**
	 * remark15を設定します。
	 * @param remark15 remark15
	 */
	public void setRemark15(final String remark15) {
		this.remark15 = remark15;
	}
}
