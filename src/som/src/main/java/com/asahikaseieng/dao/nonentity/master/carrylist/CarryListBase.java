/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CarryListクラス.
 * @author t0011036
 */
public class CarryListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション shortCarryName1 */
	public static final String shortCarryName1_COLUMN = "SHORT_CARRY_NAME1";

	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション shortCarryName2 */
	public static final String shortCarryName2_COLUMN = "SHORT_CARRY_NAME2";

	/** COLUMNアノテーション abbreviation */
	public static final String abbreviation_COLUMN = "ABBREVIATION";

	/** COLUMNアノテーション shortAbbreviation */
	public static final String shortAbbreviation_COLUMN = "SHORT_ABBREVIATION";

	//
	// インスタンスフィールド
	//

	private String carryCd;

	private String carryName1;

	private String shortCarryName1;

	private String carryName2;

	private String shortCarryName2;

	private String abbreviation;

	private String shortAbbreviation;

	//
	// インスタンスメソッド
	//

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1取得.
	 * @return carryName1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * carryName1設定.
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * shortCarryName1取得.
	 * @return shortCarryName1
	 */
	public String getShortCarryName1() {
		return this.shortCarryName1;
	}

	/**
	 * shortCarryName1設定.
	 * @param shortCarryName1 shortCarryName1
	 */
	public void setShortCarryName1(final String shortCarryName1) {
		this.shortCarryName1 = shortCarryName1;
	}

	/**
	 * carryName2取得.
	 * @return carryName2
	 */
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * carryName2設定.
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * shortCarryName2取得.
	 * @return shortCarryName2
	 */
	public String getShortCarryName2() {
		return this.shortCarryName2;
	}

	/**
	 * shortCarryName2設定.
	 * @param shortCarryName2 shortCarryName2
	 */
	public void setShortCarryName2(final String shortCarryName2) {
		this.shortCarryName2 = shortCarryName2;
	}

	/**
	 * abbreviation取得.
	 * @return abbreviation
	 */
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/**
	 * abbreviation設定.
	 * @param abbreviation abbreviation
	 */
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * shortAbbreviation取得.
	 * @return shortAbbreviation
	 */
	public String getShortAbbreviation() {
		return this.shortAbbreviation;
	}

	/**
	 * shortAbbreviation設定.
	 * @param shortAbbreviation shortAbbreviation
	 */
	public void setShortAbbreviation(final String shortAbbreviation) {
		this.shortAbbreviation = shortAbbreviation;
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

