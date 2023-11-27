/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.stockbookingforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * StockBookingInspectionListForReportクラス.
 * @author kanri-user
 */
public class StockBookingInspectionListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingInspectionListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

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

	/** COLUMNアノテーション resultValue1 */
	public static final String resultValue1_COLUMN = "RESULT_VALUE1";

	/** COLUMNアノテーション value2 */
	public static final String value2_COLUMN = "VALUE2";

	/** COLUMNアノテーション resultValue2 */
	public static final String resultValue2_COLUMN = "RESULT_VALUE2";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

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

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private java.math.BigDecimal stepNo;

	private java.math.BigDecimal lineNo;

	private java.math.BigDecimal seq;

	private String inspectionCd;

	private String inspectionName;

	private String division;

	private String valueType;

	private String value1;

	private String resultValue1;

	private String value2;

	private String resultValue2;

	private String condition;

	private String notes;

	private String remark;

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
	 * directionDivision取得.
	 * @return directionDivision
	 */
	public java.math.BigDecimal getDirectionDivision() {
		return this.directionDivision;
	}

	/**
	 * directionDivision設定.
	 * @param directionDivision directionDivision
	 */
	public void setDirectionDivision(final java.math.BigDecimal directionDivision) {
		this.directionDivision = directionDivision;
	}

	/**
	 * directionNo取得.
	 * @return directionNo
	 */
	public String getDirectionNo() {
		return this.directionNo;
	}

	/**
	 * directionNo設定.
	 * @param directionNo directionNo
	 */
	public void setDirectionNo(final String directionNo) {
		this.directionNo = directionNo;
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
	 * resultValue1取得.
	 * @return resultValue1
	 */
	public String getResultValue1() {
		return this.resultValue1;
	}

	/**
	 * resultValue1設定.
	 * @param resultValue1 resultValue1
	 */
	public void setResultValue1(final String resultValue1) {
		this.resultValue1 = resultValue1;
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
	 * resultValue2取得.
	 * @return resultValue2
	 */
	public String getResultValue2() {
		return this.resultValue2;
	}

	/**
	 * resultValue2設定.
	 * @param resultValue2 resultValue2
	 */
	public void setResultValue2(final String resultValue2) {
		this.resultValue2 = resultValue2;
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

