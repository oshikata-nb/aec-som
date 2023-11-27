/*
 * Created on Thu Feb 19 16:25:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.numberchkdisit;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * NumberChkdisitBaseクラス.
 * @author t0011036
 */
public class NumberChkdisitBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NumberChkdisitBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "NUMBER_CHKDISIT";


//	/** IDアノテーション unitDivision. */
//	public static final String unitDivision_ID = "assigned";
//	/** IDアノテーション venderCd. */
//	public static final String venderCd_ID = "assigned";
//	/** IDアノテーション venderDivision. */
//	public static final String venderDivision_ID = "assigned";

	/** COLUMNアノテーション unitDivision. */
	public static final String unitDivision_COLUMN = "UNIT_DIVISION";

	/** COLUMNアノテーション venderDivision. */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション maxLength. */
	public static final String maxLength_COLUMN = "MAX_LENGTH";

	/** COLUMNアノテーション integerLength. */
	public static final String integerLength_COLUMN = "INTEGER_LENGTH";

	/** COLUMNアノテーション smallnumLength. */
	public static final String smallnumLength_COLUMN = "SMALLNUM_LENGTH";

	/** COLUMNアノテーション roundDivision. */
	public static final String roundDivision_COLUMN = "ROUND_DIVISION";

	/** COLUMNアノテーション lowerLimit. */
	public static final String lowerLimit_COLUMN = "LOWER_LIMIT";

	/** COLUMNアノテーション upperLimit. */
	public static final String upperLimit_COLUMN = "UPPER_LIMIT";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String unitDivision;

	private String venderDivision;

	private String venderCd;

	private java.math.BigDecimal maxLength;

	private java.math.BigDecimal integerLength;

	private java.math.BigDecimal smallnumLength;

	private java.math.BigDecimal roundDivision;

	private java.math.BigDecimal lowerLimit;

	private java.math.BigDecimal upperLimit;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * unitDivision取得.
	 * @return unitDivision
	 */
	public String getUnitDivision() {
		return this.unitDivision;
	}

	/**
	 * unitDivision設定.
	 * @param unitDivision unitDivision
	 */
	public void setUnitDivision(final String unitDivision) {
		this.unitDivision = unitDivision;
	}

	/**
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * maxLength取得.
	 * @return maxLength
	 */
	public java.math.BigDecimal getMaxLength() {
		return this.maxLength;
	}

	/**
	 * maxLength設定.
	 * @param maxLength maxLength
	 */
	public void setMaxLength(final java.math.BigDecimal maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * integerLength取得.
	 * @return integerLength
	 */
	public java.math.BigDecimal getIntegerLength() {
		return this.integerLength;
	}

	/**
	 * integerLength設定.
	 * @param integerLength integerLength
	 */
	public void setIntegerLength(final java.math.BigDecimal integerLength) {
		this.integerLength = integerLength;
	}

	/**
	 * smallnumLength取得.
	 * @return smallnumLength
	 */
	public java.math.BigDecimal getSmallnumLength() {
		return this.smallnumLength;
	}

	/**
	 * smallnumLength設定.
	 * @param smallnumLength smallnumLength
	 */
	public void setSmallnumLength(final java.math.BigDecimal smallnumLength) {
		this.smallnumLength = smallnumLength;
	}

	/**
	 * roundDivision取得.
	 * @return roundDivision
	 */
	public java.math.BigDecimal getRoundDivision() {
		return this.roundDivision;
	}

	/**
	 * roundDivision設定.
	 * @param roundDivision roundDivision
	 */
	public void setRoundDivision(final java.math.BigDecimal roundDivision) {
		this.roundDivision = roundDivision;
	}

	/**
	 * lowerLimit取得.
	 * @return lowerLimit
	 */
	public java.math.BigDecimal getLowerLimit() {
		return this.lowerLimit;
	}

	/**
	 * lowerLimit設定.
	 * @param lowerLimit lowerLimit
	 */
	public void setLowerLimit(final java.math.BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * upperLimit取得.
	 * @return upperLimit
	 */
	public java.math.BigDecimal getUpperLimit() {
		return this.upperLimit;
	}

	/**
	 * upperLimit設定.
	 * @param upperLimit upperLimit
	 */
	public void setUpperLimit(final java.math.BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
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
