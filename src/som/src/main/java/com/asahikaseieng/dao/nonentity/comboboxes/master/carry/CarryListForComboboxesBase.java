/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.carry;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CarryListForComboboxesクラス.
 * @author t0011036
 */
public class CarryListForComboboxesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryListForComboboxesBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション tranceportLeadTime */
	public static final String tranceportLeadTime_COLUMN = "TRANCEPORT_LEAD_TIME";

	/** COLUMNアノテーション companyCd */
	public static final String companyCd_COLUMN = "COMPANY_CD";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション labelCd */
	public static final String labelCd_COLUMN = "LABEL_CD";

	/** COLUMNアノテーション labelName */
	public static final String labelName_COLUMN = "LABEL_NAME";

	/** COLUMNアノテーション labelPath */
	public static final String labelPath_COLUMN = "LABEL_PATH";

	//
	// インスタンスフィールド
	//

	private String carryCd;

	private String carryName1;

	private String carryName2;

	private java.math.BigDecimal tranceportLeadTime;

	private String companyCd;

	private String inputorCd;

	private String updatorCd;

	private java.sql.Timestamp inputDate;

	private java.sql.Timestamp updateDate;

	private String labelCd;

	private String labelName;

	private String labelPath;

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
	 * tranceportLeadTime取得.
	 * @return tranceportLeadTime
	 */
	public java.math.BigDecimal getTranceportLeadTime() {
		return this.tranceportLeadTime;
	}

	/**
	 * tranceportLeadTime設定.
	 * @param tranceportLeadTime tranceportLeadTime
	 */
	public void setTranceportLeadTime(final java.math.BigDecimal tranceportLeadTime) {
		this.tranceportLeadTime = tranceportLeadTime;
	}

	/**
	 * companyCd取得.
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return this.companyCd;
	}

	/**
	 * companyCd設定.
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
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
	 * labelCd取得.
	 * @return labelCd
	 */
	public String getLabelCd() {
		return this.labelCd;
	}

	/**
	 * labelCd設定.
	 * @param labelCd labelCd
	 */
	public void setLabelCd(final String labelCd) {
		this.labelCd = labelCd;
	}

	/**
	 * labelName取得.
	 * @return labelName
	 */
	public String getLabelName() {
		return this.labelName;
	}

	/**
	 * labelName設定.
	 * @param labelName labelName
	 */
	public void setLabelName(final String labelName) {
		this.labelName = labelName;
	}

	/**
	 * labelPath取得.
	 * @return labelPath
	 */
	public String getLabelPath() {
		return this.labelPath;
	}

	/**
	 * labelPath設定.
	 * @param labelPath labelPath
	 */
	public void setLabelPath(final String labelPath) {
		this.labelPath = labelPath;
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

