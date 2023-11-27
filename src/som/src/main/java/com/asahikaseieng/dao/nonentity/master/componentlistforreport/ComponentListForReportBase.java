/*
 * Created on 2009/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ComponentListForReportクラス.
 * @author t0011036
 */
public class ComponentListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ComponentListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション componentCd */
	public static final String componentCd_COLUMN = "COMPONENT_CD";

	/** COLUMNアノテーション componentName */
	public static final String componentName_COLUMN = "COMPONENT_NAME";

	/** COLUMNアノテーション ghs */
	public static final String ghs_COLUMN = "GHS";

	/** COLUMNアノテーション roanho */
	public static final String roanho_COLUMN = "ROANHO";

	/** COLUMNアノテーション organicSolvent */
	public static final String organicSolvent_COLUMN = "ORGANIC_SOLVENT";

	/** COLUMNアノテーション prtr */
	public static final String prtr_COLUMN = "PRTR";

	/** COLUMNアノテーション allergy */
	public static final String allergy_COLUMN = "ALLERGY";

	/** COLUMNアノテーション bse */
	public static final String bse_COLUMN = "BSE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String componentCd;

	private String componentName;

	private java.math.BigDecimal ghs;

	private java.math.BigDecimal roanho;

	private java.math.BigDecimal organicSolvent;

	private java.math.BigDecimal prtr;

	private java.math.BigDecimal allergy;

	private java.math.BigDecimal bse;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String inputorName;

	private String updatorName;

	//
	// インスタンスメソッド
	//

	/**
	 * componentCd取得.
	 * @return componentCd
	 */
	public String getComponentCd() {
		return this.componentCd;
	}

	/**
	 * componentCd設定.
	 * @param componentCd componentCd
	 */
	public void setComponentCd(final String componentCd) {
		this.componentCd = componentCd;
	}

	/**
	 * componentName取得.
	 * @return componentName
	 */
	public String getComponentName() {
		return this.componentName;
	}

	/**
	 * componentName設定.
	 * @param componentName componentName
	 */
	public void setComponentName(final String componentName) {
		this.componentName = componentName;
	}

	/**
	 * ghs取得.
	 * @return ghs
	 */
	public java.math.BigDecimal getGhs() {
		return this.ghs;
	}

	/**
	 * ghs設定.
	 * @param ghs ghs
	 */
	public void setGhs(final java.math.BigDecimal ghs) {
		this.ghs = ghs;
	}

	/**
	 * roanho取得.
	 * @return roanho
	 */
	public java.math.BigDecimal getRoanho() {
		return this.roanho;
	}

	/**
	 * roanho設定.
	 * @param roanho roanho
	 */
	public void setRoanho(final java.math.BigDecimal roanho) {
		this.roanho = roanho;
	}

	/**
	 * organicSolvent取得.
	 * @return organicSolvent
	 */
	public java.math.BigDecimal getOrganicSolvent() {
		return this.organicSolvent;
	}

	/**
	 * organicSolvent設定.
	 * @param organicSolvent organicSolvent
	 */
	public void setOrganicSolvent(final java.math.BigDecimal organicSolvent) {
		this.organicSolvent = organicSolvent;
	}

	/**
	 * prtr取得.
	 * @return prtr
	 */
	public java.math.BigDecimal getPrtr() {
		return this.prtr;
	}

	/**
	 * prtr設定.
	 * @param prtr prtr
	 */
	public void setPrtr(final java.math.BigDecimal prtr) {
		this.prtr = prtr;
	}

	/**
	 * allergy取得.
	 * @return allergy
	 */
	public java.math.BigDecimal getAllergy() {
		return this.allergy;
	}

	/**
	 * allergy設定.
	 * @param allergy allergy
	 */
	public void setAllergy(final java.math.BigDecimal allergy) {
		this.allergy = allergy;
	}

	/**
	 * bse取得.
	 * @return bse
	 */
	public java.math.BigDecimal getBse() {
		return this.bse;
	}

	/**
	 * bse設定.
	 * @param bse bse
	 */
	public void setBse(final java.math.BigDecimal bse) {
		this.bse = bse;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

