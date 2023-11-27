/*
 * Created on 2013/10/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.names;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * NamesSearchクラス.
 * @author ATTS
 */
public class NamesSearchBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NamesSearchBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "V_NAMES";

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
	/** COLUMNアノテーション quantityRoundup */
	public static final String quantityRoundup_COLUMN = "QUANTITY_ROUNDUP";

	/*  */
	/** COLUMNアノテーション quantityRoundupUnit */
	public static final String quantityRoundupUnit_COLUMN = "QUANTITY_ROUNDUP_UNIT";

	/*  */
	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/*  */
	/** COLUMNアノテーション invisibility */
	public static final String invisibility_COLUMN = "INVISIBILITY";

	/*  */
	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/*  */
	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/*  */
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/*  */
	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/*  */
	/** COLUMNアノテーション languageId */
	public static final String languageId_COLUMN = "LANGUAGE_ID";

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
	private java.math.BigDecimal quantityRoundup;

	/**  */
	private java.math.BigDecimal quantityRoundupUnit;

	/**  */
	private java.math.BigDecimal seq;

	/**  */
	private java.math.BigDecimal invisibility;

	/**  */
	private java.sql.Timestamp inputDate;

	/**  */
	private String inputorCd;

	/**  */
	private java.sql.Timestamp updateDate;

	/**  */
	private String updatorCd;

	/**  */
	private String languageId;

	//
	// インスタンスメソッド
	//

	/**
	 * nameDivision取得.
	 * 
	 * @return nameDivision
	 */
	public String getNameDivision() {
		return this.nameDivision;
	}

	/**
	 * nameDivision設定.
	 * 
	 * @param nameDivision nameDivision
	 */
	public void setNameDivision(final String nameDivision) {
		this.nameDivision = nameDivision;
	}

	/**
	 * nameCd取得.
	 * 
	 * @return nameCd
	 */
	public String getNameCd() {
		return this.nameCd;
	}

	/**
	 * nameCd設定.
	 * 
	 * @param nameCd nameCd
	 */
	public void setNameCd(final String nameCd) {
		this.nameCd = nameCd;
	}

	/**
	 * name01取得.
	 * 
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * 
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * name02取得.
	 * 
	 * @return name02
	 */
	public String getName02() {
		return this.name02;
	}

	/**
	 * name02設定.
	 * 
	 * @param name02 name02
	 */
	public void setName02(final String name02) {
		this.name02 = name02;
	}

	/**
	 * name03取得.
	 * 
	 * @return name03
	 */
	public String getName03() {
		return this.name03;
	}

	/**
	 * name03設定.
	 * 
	 * @param name03 name03
	 */
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * quantityRoundup取得.
	 * 
	 * @return quantityRoundup
	 */
	public java.math.BigDecimal getQuantityRoundup() {
		return this.quantityRoundup;
	}

	/**
	 * quantityRoundup設定.
	 * 
	 * @param quantityRoundup quantityRoundup
	 */
	public void setQuantityRoundup(final java.math.BigDecimal quantityRoundup) {
		this.quantityRoundup = quantityRoundup;
	}

	/**
	 * quantityRoundupUnit取得.
	 * 
	 * @return quantityRoundupUnit
	 */
	public java.math.BigDecimal getQuantityRoundupUnit() {
		return this.quantityRoundupUnit;
	}

	/**
	 * quantityRoundupUnit設定.
	 * 
	 * @param quantityRoundupUnit quantityRoundupUnit
	 */
	public void setQuantityRoundupUnit(final java.math.BigDecimal quantityRoundupUnit) {
		this.quantityRoundupUnit = quantityRoundupUnit;
	}

	/**
	 * seq取得.
	 * 
	 * @return seq
	 */
	public java.math.BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * seq設定.
	 * 
	 * @param seq seq
	 */
	public void setSeq(final java.math.BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * invisibility取得.
	 * 
	 * @return invisibility
	 */
	public java.math.BigDecimal getInvisibility() {
		return this.invisibility;
	}

	/**
	 * invisibility設定.
	 * 
	 * @param invisibility invisibility
	 */
	public void setInvisibility(final java.math.BigDecimal invisibility) {
		this.invisibility = invisibility;
	}

	/**
	 * inputDate取得.
	 * 
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * 
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * 
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * 
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updateDate取得.
	 * 
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * 
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCd取得.
	 * 
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * 
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * languageId取得.
	 * 
	 * @return languageId
	 */
	public String getLanguageId() {
		return this.languageId;
	}

	/**
	 * languageId設定.
	 * 
	 * @param languageId languageId
	 */
	public void setLanguageId(final String languageId) {
		this.languageId = languageId;
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

