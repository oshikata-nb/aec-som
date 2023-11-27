/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MgrecipeResouceBaseクラス.
 * @author tosco
 */
public class MgrecipeResouceDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeResouceDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "RECIPE_RESOUCE";

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション shortName */
	public static final String shortName_COLUMN = "SHORT_NAME";

	/** COLUMNアノテーション costMachine */
	public static final String costMachine_COLUMN = "COST_MACHINE";

	/** COLUMNアノテーション cost */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション costDivision */
	public static final String costDivision_COLUMN = "COST_DIVISION";

	/** COLUMNアノテーション organizationId */
	public static final String organizationId_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション equipType */
	public static final String equipType_COLUMN = "EQUIP_TYPE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String resouceCd;

	private String resouceName;

	private String shortName;

	private java.math.BigDecimal costMachine;

	private java.math.BigDecimal cost;

	private java.math.BigDecimal costDivision;

	private java.math.BigDecimal organizationId;

	private String resouceGroupCd;

	private String productionLine;

	private java.math.BigDecimal equipType;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * resouceCd取得.
	 * @return resouceCd
	 */
	public String getResouceCd() {
		return this.resouceCd;
	}

	/**
	 * resouceCd設定.
	 * @param resouceCd resouceCd
	 */
	public void setResouceCd(final String resouceCd) {
		this.resouceCd = resouceCd;
	}

	/**
	 * resouceName取得.
	 * @return resouceName
	 */
	public String getResouceName() {
		return this.resouceName;
	}

	/**
	 * resouceName設定.
	 * @param resouceName resouceName
	 */
	public void setResouceName(final String resouceName) {
		this.resouceName = resouceName;
	}

	/**
	 * shortName取得.
	 * @return shortName
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * shortName設定.
	 * @param shortName shortName
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}

	/**
	 * costMachine取得.
	 * @return costMachine
	 */
	public java.math.BigDecimal getCostMachine() {
		return this.costMachine;
	}

	/**
	 * costMachine設定.
	 * @param costMachine costMachine
	 */
	public void setCostMachine(final java.math.BigDecimal costMachine) {
		this.costMachine = costMachine;
	}

	/**
	 * cost取得.
	 * @return cost
	 */
	public java.math.BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * cost設定.
	 * @param cost cost
	 */
	public void setCost(final java.math.BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * costDivision取得.
	 * @return costDivision
	 */
	public java.math.BigDecimal getCostDivision() {
		return this.costDivision;
	}

	/**
	 * costDivision設定.
	 * @param costDivision costDivision
	 */
	public void setCostDivision(final java.math.BigDecimal costDivision) {
		this.costDivision = costDivision;
	}

	/**
	 * organizationId取得.
	 * @return organizationId
	 */
	public java.math.BigDecimal getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * organizationId設定.
	 * @param organizationId organizationId
	 */
	public void setOrganizationId(final java.math.BigDecimal organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * resouceGroupCd取得.
	 * @return resouceGroupCd
	 */
	public String getResouceGroupCd() {
		return this.resouceGroupCd;
	}

	/**
	 * resouceGroupCd設定.
	 * @param resouceGroupCd resouceGroupCd
	 */
	public void setResouceGroupCd(final String resouceGroupCd) {
		this.resouceGroupCd = resouceGroupCd;
	}

	/**
	 * productionLine取得.
	 * @return productionLine
	 */
	public String getProductionLine() {
		return this.productionLine;
	}

	/**
	 * productionLine設定.
	 * @param productionLine productionLine
	 */
	public void setProductionLine(final String productionLine) {
		this.productionLine = productionLine;
	}

	/**
	 * equipType取得.
	 * @return equipType
	 */
	public java.math.BigDecimal getEquipType() {
		return this.equipType;
	}

	/**
	 * equipType設定.
	 * @param equipType equipType
	 */
	public void setEquipType(final java.math.BigDecimal equipType) {
		this.equipType = equipType;
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

