/*
 * Created on 2013/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.common.batchwait;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * BatchWaitNamesArrayクラス.
 * @author atts
 */
public class BatchWaitNamesArrayBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BatchWaitNamesArrayBase() {
	}

	//
	// 定数
	//

	/*  */
	/** COLUMNアノテーション nameDivision */
	public static final String nameDivision_COLUMN = "NAME_DIVISION";

	/*  */
	/** COLUMNアノテーション nameCd */
	public static final String nameCd_COLUMN = "NAME_CD";

	/*  */
	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/*  */
	/** COLUMNアノテーション name02 */
	public static final String name02_COLUMN = "NAME02";

	/*  */
	/** COLUMNアノテーション name03 */
	public static final String name03_COLUMN = "NAME03";

	/*  */
	/** COLUMNアノテーション nmevalue1 */
	public static final String nmevalue1_COLUMN = "NMEVALUE1";

	//
	// インスタンスフィールド
	//

	/**  */
	private String nameDivision;

	/**  */
	private String nameCd;

	/**  */
	private String name01;

	/**  */
	private String name02;

	/**  */
	private String name03;

	/**  */
	private String nmevalue1;

	//
	// インスタンスメソッド
	//

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return name01;
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
		return name02;
	}

	/**
	 * name02設定.
	 * @param name02 name02
	 */
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * name03取得.
	 * @return name03
	 */
	public String getName03() {
		return name03;
	}

	/**
	 * name03設定.
	 * @param name03 name03
	 */
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * nameCd取得.
	 * @return nameCd
	 */
	public String getNameCd() {
		return nameCd;
	}

	/**
	 * nameCd設定.
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * nameDivision取得.
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return nameDivision;
	}

	/**
	 * nameDivision設定.
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * nmevalue1取得.
	 * @return nmevalue1
	 */
	public String getNmevalue1() {
		return nmevalue1;
	}

	/**
	 * nmevalue1設定.
	 * @param nmevalue1 nmevalue1
	 */
	public void setNmevalue1(final String nmevalue1) {
		this.nmevalue1 = nmevalue1;
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
