/*
 * Created on 2020/12/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.vmailvalueentity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * VMailValueEntityクラス.
 * @author 
 */
public class VMailValueEntityBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VMailValueEntityBase() {
	}

	//
	// 定数
	//
	
	/** COLUMNアノテーション vals */
	public static final String vals_COLUMN = "VALS";

	// インスタンスフィールド
	//
	
	private String vals;
	
	//
	// インスタンスメソッド
	//
	
	/**
	 * valsを取得します。
	 * @return vals
	 */
	public String getVals() {
		return vals;
	}

	/**
	 * valsを設定します。
	 * @param vals vals
	 */
	public void setVals(String vals) {
		this.vals = vals;
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

