/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DirectionProcedureTempBaseクラス.
 * @author 
 */
public class DirectionProcedureTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedureTempBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DIRECTION_PROCEDURE_TEMP";


	/** COLUMNアノテーション tempNo. */
	public static final String tempNo_COLUMN = "TEMP_NO";

	/** COLUMNアノテーション rowNumber. */
	public static final String rowNumber_COLUMN = "ROW_NUMBER";

	/** COLUMNアノテーション directionDivision. */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo. */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション stepNo. */
	public static final String stepNo_COLUMN = "STEP_NO";

	/** COLUMNアノテーション condition. */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark. */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes. */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション resultSdate. */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate. */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション conditionTemp. */
	public static final String conditionTemp_COLUMN = "CONDITION_TEMP";

	/** COLUMNアノテーション conditionTime. */
	public static final String conditionTime_COLUMN = "CONDITION_TIME";

	/** COLUMNアノテーション stirSpeed1. */
	public static final String stirSpeed1_COLUMN = "STIR_SPEED1";

	/** COLUMNアノテーション stirSpeed2. */
	public static final String stirSpeed2_COLUMN = "STIR_SPEED2";

	/** COLUMNアノテーション waterWeight. */
	public static final String waterWeight_COLUMN = "WATER_WEIGHT";

	/** COLUMNアノテーション mainStream. */
	public static final String mainStream_COLUMN = "MAIN_STREAM";

	/** COLUMNアノテーション ph. */
	public static final String ph_COLUMN = "PH";

	/** COLUMNアノテーション resultConditionTemp. */
	public static final String resultConditionTemp_COLUMN = "RESULT_CONDITION_TEMP";

	/** COLUMNアノテーション resultStirSpeed. */
	public static final String resultStirSpeed_COLUMN = "RESULT_STIR_SPEED";

	/** COLUMNアノテーション resultPh. */
	public static final String resultPh_COLUMN = "RESULT_PH";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String tempNo;

	private java.math.BigDecimal rowNumber;

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private java.math.BigDecimal stepNo;

	private String condition;

	private String remark;

	private String notes;

	private java.sql.Timestamp resultSdate;

	private java.sql.Timestamp resultEdate;

	private java.math.BigDecimal conditionTemp;

	private java.math.BigDecimal conditionTime;

	private java.math.BigDecimal stirSpeed1;

	private java.math.BigDecimal stirSpeed2;

	private java.math.BigDecimal waterWeight;

	private java.math.BigDecimal mainStream;

	private java.math.BigDecimal ph;

	private java.math.BigDecimal resultConditionTemp;

	private java.math.BigDecimal resultStirSpeed;

	private java.math.BigDecimal resultPh;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * tempNoを取得.
	 * @return tempNo
	 */
	public String getTempNo() {
		return tempNo;
	}

	/**
	 * tempNoを設定.
	 * @param tempNo tempNo
	 */
	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}

	/**
	 * rowNumberを取得.
	 * @return rowNumber
	 */
	public java.math.BigDecimal getRowNumber() {
		return rowNumber;
	}

	/**
	 * rowNumberを設定.
	 * @param rowNumber rowNumber
	 */
	public void setRowNumber(java.math.BigDecimal rowNumber) {
		this.rowNumber = rowNumber;
	}

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
	 * resultSdate取得.
	 * @return resultSdate
	 */
	public java.sql.Timestamp getResultSdate() {
		return this.resultSdate;
	}

	/**
	 * resultSdate設定.
	 * @param resultSdate resultSdate
	 */
	public void setResultSdate(final java.sql.Timestamp resultSdate) {
		this.resultSdate = resultSdate;
	}

	/**
	 * resultEdate取得.
	 * @return resultEdate
	 */
	public java.sql.Timestamp getResultEdate() {
		return this.resultEdate;
	}

	/**
	 * resultEdate設定.
	 * @param resultEdate resultEdate
	 */
	public void setResultEdate(final java.sql.Timestamp resultEdate) {
		this.resultEdate = resultEdate;
	}

	/**
	 * conditionTemp取得.
	 * @return conditionTemp
	 */
	public java.math.BigDecimal getConditionTemp() {
		return this.conditionTemp;
	}

	/**
	 * conditionTemp設定.
	 * @param conditionTemp conditionTemp
	 */
	public void setConditionTemp(final java.math.BigDecimal conditionTemp) {
		this.conditionTemp = conditionTemp;
	}

	/**
	 * conditionTime取得.
	 * @return conditionTime
	 */
	public java.math.BigDecimal getConditionTime() {
		return this.conditionTime;
	}

	/**
	 * conditionTime設定.
	 * @param conditionTime conditionTime
	 */
	public void setConditionTime(final java.math.BigDecimal conditionTime) {
		this.conditionTime = conditionTime;
	}

	/**
	 * stirSpeed1取得.
	 * @return stirSpeed1
	 */
	public java.math.BigDecimal getStirSpeed1() {
		return this.stirSpeed1;
	}

	/**
	 * stirSpeed1設定.
	 * @param stirSpeed1 stirSpeed1
	 */
	public void setStirSpeed1(final java.math.BigDecimal stirSpeed1) {
		this.stirSpeed1 = stirSpeed1;
	}

	/**
	 * stirSpeed2取得.
	 * @return stirSpeed2
	 */
	public java.math.BigDecimal getStirSpeed2() {
		return this.stirSpeed2;
	}

	/**
	 * stirSpeed2設定.
	 * @param stirSpeed2 stirSpeed2
	 */
	public void setStirSpeed2(final java.math.BigDecimal stirSpeed2) {
		this.stirSpeed2 = stirSpeed2;
	}

	/**
	 * waterWeight取得.
	 * @return waterWeight
	 */
	public java.math.BigDecimal getWaterWeight() {
		return this.waterWeight;
	}

	/**
	 * waterWeight設定.
	 * @param waterWeight waterWeight
	 */
	public void setWaterWeight(final java.math.BigDecimal waterWeight) {
		this.waterWeight = waterWeight;
	}

	/**
	 * mainStream取得.
	 * @return mainStream
	 */
	public java.math.BigDecimal getMainStream() {
		return this.mainStream;
	}

	/**
	 * mainStream設定.
	 * @param mainStream mainStream
	 */
	public void setMainStream(final java.math.BigDecimal mainStream) {
		this.mainStream = mainStream;
	}

	/**
	 * ph取得.
	 * @return ph
	 */
	public java.math.BigDecimal getPh() {
		return this.ph;
	}

	/**
	 * ph設定.
	 * @param ph ph
	 */
	public void setPh(final java.math.BigDecimal ph) {
		this.ph = ph;
	}

	/**
	 * resultConditionTemp取得.
	 * @return resultConditionTemp
	 */
	public java.math.BigDecimal getResultConditionTemp() {
		return this.resultConditionTemp;
	}

	/**
	 * resultConditionTemp設定.
	 * @param resultConditionTemp resultConditionTemp
	 */
	public void setResultConditionTemp(final java.math.BigDecimal resultConditionTemp) {
		this.resultConditionTemp = resultConditionTemp;
	}

	/**
	 * resultStirSpeed取得.
	 * @return resultStirSpeed
	 */
	public java.math.BigDecimal getResultStirSpeed() {
		return this.resultStirSpeed;
	}

	/**
	 * resultStirSpeed設定.
	 * @param resultStirSpeed resultStirSpeed
	 */
	public void setResultStirSpeed(final java.math.BigDecimal resultStirSpeed) {
		this.resultStirSpeed = resultStirSpeed;
	}

	/**
	 * resultPh取得.
	 * @return resultPh
	 */
	public java.math.BigDecimal getResultPh() {
		return this.resultPh;
	}

	/**
	 * resultPh設定.
	 * @param resultPh resultPh
	 */
	public void setResultPh(final java.math.BigDecimal resultPh) {
		this.resultPh = resultPh;
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
