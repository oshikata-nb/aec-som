/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.namesdetail;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * NamesDetailクラス.
 * @author t0011036
 */
public class NamesDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NamesDetailBase() {
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

	/** COLUMNアノテーション name03 */
	public static final String name03_COLUMN = "NAME03";

	/** COLUMNアノテーション quantityRoundup */
	public static final String quantityRoundup_COLUMN = "QUANTITY_ROUNDUP";

	/** COLUMNアノテーション quantityRoundupUnit */
	public static final String quantityRoundupUnit_COLUMN = "QUANTITY_ROUNDUP_UNIT";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション nameDivisionName */
	public static final String nameDivisionName_COLUMN = "NAME_DIVISION_NAME";

	/** COLUMNアノテーション nmqty01 */
	public static final String nmqty01_COLUMN = "NMQTY01";

	//
	// インスタンスフィールド
	//

	private String nameDivision;

	private String nameCd;

	private String name01;

	private String name02;

	private String name03;

	private java.math.BigDecimal quantityRoundup;

	private java.math.BigDecimal quantityRoundupUnit;

	private java.sql.Timestamp updateDate;

	private String nameDivisionName;

	private BigDecimal nmqty01;

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
	 * name03取得.
	 * @return name03
	 */
	public String getName03() {
		return this.name03;
	}

	/**
	 * name03設定.
	 * @param name03 name03
	 */
	public void setName03(final String name03) {
		this.name03 = name03;
	}

	/**
	 * quantityRoundup取得.
	 * @return quantityRoundup
	 */
	public java.math.BigDecimal getQuantityRoundup() {
		return this.quantityRoundup;
	}

	/**
	 * quantityRoundup設定.
	 * @param quantityRoundup quantityRoundup
	 */
	public void setQuantityRoundup(final java.math.BigDecimal quantityRoundup) {
		this.quantityRoundup = quantityRoundup;
	}

	/**
	 * quantityRoundupUnit取得.
	 * @return quantityRoundupUnit
	 */
	public java.math.BigDecimal getQuantityRoundupUnit() {
		return this.quantityRoundupUnit;
	}

	/**
	 * quantityRoundupUnit設定.
	 * @param quantityRoundupUnit quantityRoundupUnit
	 */
	public void setQuantityRoundupUnit(final java.math.BigDecimal quantityRoundupUnit) {
		this.quantityRoundupUnit = quantityRoundupUnit;
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
	 * nameDivisionName取得.
	 * @return nameDivisionName
	 */
	public String getNameDivisionName() {
		return this.nameDivisionName;
	}

	/**
	 * nameDivisionName設定.
	 * @param nameDivisionName nameDivisionName
	 */
	public void setNameDivisionName(final String nameDivisionName) {
		this.nameDivisionName = nameDivisionName;
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

	public BigDecimal getNmqty01() {
		return nmqty01;
	}

	public void setNmqty01(BigDecimal nmqty01) {
		this.nmqty01 = nmqty01;
	}
}

