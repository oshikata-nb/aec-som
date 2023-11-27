/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.orderstatuslist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderStatusListクラス.
 * @author kanri-user
 */
public class OrderStatusListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderStatusListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション nameDivision */
	public static final String nameDivision_COLUMN = "NAME_DIVISION";

	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション name02 */
	public static final String name02_COLUMN = "NAME02";

	/** COLUMNアノテーション nmeflg1 */
	public static final String nmeflg1_COLUMN = "NMEFLG1";

	//
	// インスタンスフィールド
	//

	private String nameDivision;

	private String nameCd;

	private String name01;

	private String name02;

	private java.math.BigDecimal nmeflg1;

	//
	// インスタンスメソッド
	//

	/**
	 * nameDivision取得.
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return this.nameDivision;
	}

	/**
	 * nameDivision設定.
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * nameCd取得.
	 * @return nameCd
	 */
	public String getNameCd() {
		return this.nameCd;
	}

	/**
	 * nameCd設定.
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * name02取得.
	 * @return name02
	 */
	public String getName02() {
		return this.name02;
	}

	/**
	 * name02設定.
	 * @param name02 name02
	 */
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * nmeflg1取得.
	 * @return nmeflg1
	 */
	public java.math.BigDecimal getNmeflg1() {
		return this.nmeflg1;
	}

	/**
	 * nmeflg1設定.
	 * @param nmeflg1 nmeflg1
	 */
	public void setNmeflg1(final java.math.BigDecimal nmeflg1) {
		this.nmeflg1 = nmeflg1;
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

