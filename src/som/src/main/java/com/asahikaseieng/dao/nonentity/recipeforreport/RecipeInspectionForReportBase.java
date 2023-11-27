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
 * RecipeInspectionForReportクラス.
 * @author kanri-user
 */
public class RecipeInspectionForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeInspectionForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション stepNo */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション lineNo */
	public static final String lineNo_COLUMN = "LINE_NO";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション inspectionCd */
	public static final String inspectionCd_COLUMN = "INSPECTION_CD";

	/** COLUMNアノテーション inspectionName */
	public static final String inspectionName_COLUMN = "INSPECTION_NAME";

	/** COLUMNアノテーション division */
	public static final String division_COLUMN = "DIVISION";

	/** COLUMNアノテーション valueType */
	public static final String valueType_COLUMN = "VALUE_TYPE";

	/** COLUMNアノテーション value1 */
	public static final String value1_COLUMN = "VALUE1";

	/** COLUMNアノテーション value2 */
	public static final String value2_COLUMN = "VALUE2";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

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

	private java.math.BigDecimal stepNo;

	private java.math.BigDecimal lineNo;

	private java.math.BigDecimal seq;

	private String inspectionCd;

	private String inspectionName;

	private String division;

	private String valueType;

	private String value1;

	private String value2;

	private String condition;

	private String remark;

	private String notes;

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
	 * stepNo取得.
	 * @return stepNo
	 */
	public java.math.BigDecimal getStepNo() {
		return this.stepNo;
	}

	/**
	 * stepNo設定.
	 * @param stepNo stepNo
	 */
	public void setStepNo(final java.math.BigDecimal stepNo) {
		this.stepNo = stepNo;
	}

	/**
	 * lineNo取得.
	 * @return lineNo
	 */
	public java.math.BigDecimal getLineNo() {
		return this.lineNo;
	}

	/**
	 * lineNo設定.
	 * @param lineNo lineNo
	 */
	public void setLineNo(final java.math.BigDecimal lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * seq取得.
	 * @return seq
	 */
	public java.math.BigDecimal getSeq() {
		return this.seq;
	}

	/**
	 * seq設定.
	 * @param seq seq
	 */
	public void setSeq(final java.math.BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * inspectionCd取得.
	 * @return inspectionCd
	 */
	public String getInspectionCd() {
		return this.inspectionCd;
	}

	/**
	 * inspectionCd設定.
	 * @param inspectionCd inspectionCd
	 */
	public void setInspectionCd(final String inspectionCd) {
		this.inspectionCd = inspectionCd;
	}

	/**
	 * inspectionName取得.
	 * @return inspectionName
	 */
	public String getInspectionName() {
		return this.inspectionName;
	}

	/**
	 * inspectionName設定.
	 * @param inspectionName inspectionName
	 */
	public void setInspectionName(final String inspectionName) {
		this.inspectionName = inspectionName;
	}

	/**
	 * division取得.
	 * @return division
	 */
	public String getDivision() {
		return this.division;
	}

	/**
	 * division設定.
	 * @param division division
	 */
	public void setDivision(final String division) {
		this.division = division;
	}

	/**
	 * valueType取得.
	 * @return valueType
	 */
	public String getValueType() {
		return this.valueType;
	}

	/**
	 * valueType設定.
	 * @param valueType valueType
	 */
	public void setValueType(final String valueType) {
		this.valueType = valueType;
	}

	/**
	 * value1取得.
	 * @return value1
	 */
	public String getValue1() {
		return this.value1;
	}

	/**
	 * value1設定.
	 * @param value1 value1
	 */
	public void setValue1(final String value1) {
		this.value1 = value1;
	}

	/**
	 * value2取得.
	 * @return value2
	 */
	public String getValue2() {
		return this.value2;
	}

	/**
	 * value2設定.
	 * @param value2 value2
	 */
	public void setValue2(final String value2) {
		this.value2 = value2;
	}

	/**
	 * condition取得.
	 * @return condition
	 */
	public String getCondition() {
		return this.condition;
	}

	/**
	 * condition設定.
	 * @param condition condition
	 */
	public void setCondition(final String condition) {
		this.condition = condition;
	}

	/**
	 * remark取得.
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * notes取得.
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * notes設定.
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
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

