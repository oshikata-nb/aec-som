/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.recipeforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeAsprovaForReportクラス.
 * @author kanri-user
 */
public class RecipeAsprovaForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeAsprovaForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション resouceGroupCd */
	public static final String resouceGroupCd_COLUMN = "RESOUCE_GROUP_CD";

	/** COLUMNアノテーション resouceGroupName */
	public static final String resouceGroupName_COLUMN = "RESOUCE_GROUP_NAME";

	/** COLUMNアノテーション operationGroupCd */
	public static final String operationGroupCd_COLUMN = "OPERATION_GROUP_CD";

	/** COLUMNアノテーション operationGroupName */
	public static final String operationGroupName_COLUMN = "OPERATION_GROUP_NAME";

	/** COLUMNアノテーション resouceCd */
	public static final String resouceCd_COLUMN = "RESOUCE_CD";

	/** COLUMNアノテーション resouceName */
	public static final String resouceName_COLUMN = "RESOUCE_NAME";

	/** COLUMNアノテーション youinsu */
	public static final String youinsu_COLUMN = "YOUINSU";

	/** COLUMNアノテーション maejikan */
	public static final String maejikan_COLUMN = "MAEJIKAN";

	/** COLUMNアノテーション atojikan */
	public static final String atojikan_COLUMN = "ATOJIKAN";

	/** COLUMNアノテーション processWorkTime1 */
	public static final String processWorkTime1_COLUMN = "PROCESS_WORK_TIME1";

	/** COLUMNアノテーション processWorkTime2 */
	public static final String processWorkTime2_COLUMN = "PROCESS_WORK_TIME2";

	/** COLUMNアノテーション machineWorkTime1 */
	public static final String machineWorkTime1_COLUMN = "MACHINE_WORK_TIME1";

	/** COLUMNアノテーション machineWorkTime2 */
	public static final String machineWorkTime2_COLUMN = "MACHINE_WORK_TIME2";

	/** COLUMNアノテーション manWorkTime1 */
	public static final String manWorkTime1_COLUMN = "MAN_WORK_TIME1";

	/** COLUMNアノテーション manWorkTime2 */
	public static final String manWorkTime2_COLUMN = "MAN_WORK_TIME2";

	/** COLUMNアノテーション recipePriority */
	public static final String recipePriority_COLUMN = "RECIPE_PRIORITY";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateName */
	public static final String updateName_COLUMN = "UPDATE_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String resouceGroupCd;

	private String resouceGroupName;

	private String operationGroupCd;

	private String operationGroupName;

	private String resouceCd;

	private String resouceName;

	private java.math.BigDecimal youinsu;

	private java.math.BigDecimal maejikan;

	private java.math.BigDecimal atojikan;

	private java.math.BigDecimal processWorkTime1;

	private java.math.BigDecimal processWorkTime2;

	private java.math.BigDecimal machineWorkTime1;

	private java.math.BigDecimal machineWorkTime2;

	private java.math.BigDecimal manWorkTime1;

	private java.math.BigDecimal manWorkTime2;

	private java.math.BigDecimal recipePriority;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

	private String updateName;

	private java.sql.Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * recipeId取得.
	 * @return recipeId
	 */
	public java.math.BigDecimal getRecipeId() {
		return this.recipeId;
	}

	/**
	 * recipeId設定.
	 * @param recipeId recipeId
	 */
	public void setRecipeId(final java.math.BigDecimal recipeId) {
		this.recipeId = recipeId;
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
	 * resouceGroupName取得.
	 * @return resouceGroupName
	 */
	public String getResouceGroupName() {
		return this.resouceGroupName;
	}

	/**
	 * resouceGroupName設定.
	 * @param resouceGroupName resouceGroupName
	 */
	public void setResouceGroupName(final String resouceGroupName) {
		this.resouceGroupName = resouceGroupName;
	}

	/**
	 * operationGroupCd取得.
	 * @return operationGroupCd
	 */
	public String getOperationGroupCd() {
		return this.operationGroupCd;
	}

	/**
	 * operationGroupCd設定.
	 * @param operationGroupCd operationGroupCd
	 */
	public void setOperationGroupCd(final String operationGroupCd) {
		this.operationGroupCd = operationGroupCd;
	}

	/**
	 * operationGroupName取得.
	 * @return operationGroupName
	 */
	public String getOperationGroupName() {
		return this.operationGroupName;
	}

	/**
	 * operationGroupName設定.
	 * @param operationGroupName operationGroupName
	 */
	public void setOperationGroupName(final String operationGroupName) {
		this.operationGroupName = operationGroupName;
	}

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
	 * youinsu取得.
	 * @return youinsu
	 */
	public java.math.BigDecimal getYouinsu() {
		return this.youinsu;
	}

	/**
	 * youinsu設定.
	 * @param youinsu youinsu
	 */
	public void setYouinsu(final java.math.BigDecimal youinsu) {
		this.youinsu = youinsu;
	}

	/**
	 * maejikan取得.
	 * @return maejikan
	 */
	public java.math.BigDecimal getMaejikan() {
		return this.maejikan;
	}

	/**
	 * maejikan設定.
	 * @param maejikan maejikan
	 */
	public void setMaejikan(final java.math.BigDecimal maejikan) {
		this.maejikan = maejikan;
	}

	/**
	 * atojikan取得.
	 * @return atojikan
	 */
	public java.math.BigDecimal getAtojikan() {
		return this.atojikan;
	}

	/**
	 * atojikan設定.
	 * @param atojikan atojikan
	 */
	public void setAtojikan(final java.math.BigDecimal atojikan) {
		this.atojikan = atojikan;
	}

	/**
	 * processWorkTime1取得.
	 * @return processWorkTime1
	 */
	public java.math.BigDecimal getProcessWorkTime1() {
		return this.processWorkTime1;
	}

	/**
	 * processWorkTime1設定.
	 * @param processWorkTime1 processWorkTime1
	 */
	public void setProcessWorkTime1(final java.math.BigDecimal processWorkTime1) {
		this.processWorkTime1 = processWorkTime1;
	}

	/**
	 * processWorkTime2取得.
	 * @return processWorkTime2
	 */
	public java.math.BigDecimal getProcessWorkTime2() {
		return this.processWorkTime2;
	}

	/**
	 * processWorkTime2設定.
	 * @param processWorkTime2 processWorkTime2
	 */
	public void setProcessWorkTime2(final java.math.BigDecimal processWorkTime2) {
		this.processWorkTime2 = processWorkTime2;
	}

	/**
	 * machineWorkTime1取得.
	 * @return machineWorkTime1
	 */
	public java.math.BigDecimal getMachineWorkTime1() {
		return this.machineWorkTime1;
	}

	/**
	 * machineWorkTime1設定.
	 * @param machineWorkTime1 machineWorkTime1
	 */
	public void setMachineWorkTime1(final java.math.BigDecimal machineWorkTime1) {
		this.machineWorkTime1 = machineWorkTime1;
	}

	/**
	 * machineWorkTime2取得.
	 * @return machineWorkTime2
	 */
	public java.math.BigDecimal getMachineWorkTime2() {
		return this.machineWorkTime2;
	}

	/**
	 * machineWorkTime2設定.
	 * @param machineWorkTime2 machineWorkTime2
	 */
	public void setMachineWorkTime2(final java.math.BigDecimal machineWorkTime2) {
		this.machineWorkTime2 = machineWorkTime2;
	}

	/**
	 * manWorkTime1取得.
	 * @return manWorkTime1
	 */
	public java.math.BigDecimal getManWorkTime1() {
		return this.manWorkTime1;
	}

	/**
	 * manWorkTime1設定.
	 * @param manWorkTime1 manWorkTime1
	 */
	public void setManWorkTime1(final java.math.BigDecimal manWorkTime1) {
		this.manWorkTime1 = manWorkTime1;
	}

	/**
	 * manWorkTime2取得.
	 * @return manWorkTime2
	 */
	public java.math.BigDecimal getManWorkTime2() {
		return this.manWorkTime2;
	}

	/**
	 * manWorkTime2設定.
	 * @param manWorkTime2 manWorkTime2
	 */
	public void setManWorkTime2(final java.math.BigDecimal manWorkTime2) {
		this.manWorkTime2 = manWorkTime2;
	}

	/**
	 * recipePriority取得.
	 * @return recipePriority
	 */
	public java.math.BigDecimal getRecipePriority() {
		return this.recipePriority;
	}

	/**
	 * recipePriority設定.
	 * @param recipePriority recipePriority
	 */
	public void setRecipePriority(final java.math.BigDecimal recipePriority) {
		this.recipePriority = recipePriority;
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
	 * updateName取得.
	 * @return updateName
	 */
	public String getUpdateName() {
		return this.updateName;
	}

	/**
	 * updateName設定.
	 * @param updateName updateName
	 */
	public void setUpdateName(final String updateName) {
		this.updateName = updateName;
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

