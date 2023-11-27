/*
 * Created on 2009/08/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.beforehandmeltlblforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BeforehandMeltLblProcedureListForReportクラス.
 * @author kanri-user
 */
public class BeforehandMeltLblProcedureListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BeforehandMeltLblProcedureListForReportBase() {
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

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション operationCd */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationName */
	public static final String operationName_COLUMN = "OPERATION_NAME";

	/** COLUMNアノテーション condition */
	public static final String condition_COLUMN = "CONDITION";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション leadtime */
	public static final String leadtime_COLUMN = "LEADTIME";

	/** COLUMNアノテーション startDate */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション resultSdate */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション conditionTemp */
	public static final String conditionTemp_COLUMN = "CONDITION_TEMP";

	/** COLUMNアノテーション conditionTime */
	public static final String conditionTime_COLUMN = "CONDITION_TIME";

	/** COLUMNアノテーション stirSpeed1 */
	public static final String stirSpeed1_COLUMN = "STIR_SPEED1";

	/** COLUMNアノテーション stirSpeed2 */
	public static final String stirSpeed2_COLUMN = "STIR_SPEED2";

	/** COLUMNアノテーション waterWeight */
	public static final String waterWeight_COLUMN = "WATER_WEIGHT";

	/** COLUMNアノテーション mainStream */
	public static final String mainStream_COLUMN = "MAIN_STREAM";

	/** COLUMNアノテーション ph */
	public static final String ph_COLUMN = "PH";

	/** COLUMNアノテーション resultConditionTemp */
	public static final String resultConditionTemp_COLUMN = "RESULT_CONDITION_TEMP";

	/** COLUMNアノテーション resultStirSpeed */
	public static final String resultStirSpeed_COLUMN = "RESULT_STIR_SPEED";

	/** COLUMNアノテーション resultPh */
	public static final String resultPh_COLUMN = "RESULT_PH";

	/** COLUMNアノテーション fillingQty */
	public static final String fillingQty_COLUMN = "FILLING_QTY";

	/** COLUMNアノテーション fillingUnit */
	public static final String fillingUnit_COLUMN = "FILLING_UNIT";

	/** COLUMNアノテーション net */
	public static final String net_COLUMN = "NET";

	/** COLUMNアノテーション weightMin */
	public static final String weightMin_COLUMN = "WEIGHT_MIN";

	/** COLUMNアノテーション weightMax */
	public static final String weightMax_COLUMN = "WEIGHT_MAX";

	/** COLUMNアノテーション filter */
	public static final String filter_COLUMN = "FILTER";

	/** COLUMNアノテーション autoCheckerMin */
	public static final String autoCheckerMin_COLUMN = "AUTO_CHECKER_MIN";

	/** COLUMNアノテーション autoCheckerMax */
	public static final String autoCheckerMax_COLUMN = "AUTO_CHECKER_MAX";

	/** COLUMNアノテーション grossCheckerMin */
	public static final String grossCheckerMin_COLUMN = "GROSS_CHECKER_MIN";

	/** COLUMNアノテーション grossCheckerMax */
	public static final String grossCheckerMax_COLUMN = "GROSS_CHECKER_MAX";

	/** COLUMNアノテーション openingTorqueMin */
	public static final String openingTorqueMin_COLUMN = "OPENING_TORQUE_MIN";

	/** COLUMNアノテーション openingTorqueMax */
	public static final String openingTorqueMax_COLUMN = "OPENING_TORQUE_MAX";

	/** COLUMNアノテーション hotAirPresetTemp */
	public static final String hotAirPresetTemp_COLUMN = "HOT_AIR_PRESET_TEMP";

	/** COLUMNアノテーション hotAirPressure */
	public static final String hotAirPressure_COLUMN = "HOT_AIR_PRESSURE";

	/** COLUMNアノテーション mesh */
	public static final String mesh_COLUMN = "MESH";

	/** COLUMNアノテーション autoCheckerAve */
	public static final String autoCheckerAve_COLUMN = "AUTO_CHECKER_AVE";

	/** COLUMNアノテーション grossCheckerAve */
	public static final String grossCheckerAve_COLUMN = "GROSS_CHECKER_AVE";

	/** COLUMNアノテーション closingTorqueMin */
	public static final String closingTorqueMin_COLUMN = "CLOSING_TORQUE_MIN";

	/** COLUMNアノテーション closingTorqueMax */
	public static final String closingTorqueMax_COLUMN = "CLOSING_TORQUE_MAX";

	/** COLUMNアノテーション torquePressure */
	public static final String torquePressure_COLUMN = "TORQUE_PRESSURE";

	/** COLUMNアノテーション airPressure */
	public static final String airPressure_COLUMN = "AIR_PRESSURE";

	/** COLUMNアノテーション vcloseTime */
	public static final String vcloseTime_COLUMN = "VCLOSE_TIME";

	/** COLUMNアノテーション firstHeatSeal */
	public static final String firstHeatSeal_COLUMN = "FIRST_HEAT_SEAL";

	/** COLUMNアノテーション secondHeatSeal */
	public static final String secondHeatSeal_COLUMN = "SECOND_HEAT_SEAL";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private java.math.BigDecimal stepNo;

	private java.math.BigDecimal seq;

	private String operationCd;

	private String operationName;

	private String condition;

	private String remark;

	private String notes;

	private java.math.BigDecimal leadtime;

	private java.sql.Timestamp startDate;

	private java.sql.Timestamp endDate;

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

	private java.math.BigDecimal fillingQty;

	private String fillingUnit;

	private java.math.BigDecimal net;

	private java.math.BigDecimal weightMin;

	private java.math.BigDecimal weightMax;

	private java.math.BigDecimal filter;

	private java.math.BigDecimal autoCheckerMin;

	private java.math.BigDecimal autoCheckerMax;

	private java.math.BigDecimal grossCheckerMin;

	private java.math.BigDecimal grossCheckerMax;

	private java.math.BigDecimal openingTorqueMin;

	private java.math.BigDecimal openingTorqueMax;

	private java.math.BigDecimal hotAirPresetTemp;

	private java.math.BigDecimal hotAirPressure;

	private java.math.BigDecimal mesh;

	private java.math.BigDecimal autoCheckerAve;

	private java.math.BigDecimal grossCheckerAve;

	private java.math.BigDecimal closingTorqueMin;

	private java.math.BigDecimal closingTorqueMax;

	private java.math.BigDecimal torquePressure;

	private java.math.BigDecimal airPressure;

	private java.math.BigDecimal vcloseTime;

	private java.math.BigDecimal firstHeatSeal;

	private java.math.BigDecimal secondHeatSeal;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * operationCd取得.
	 * @return operationCd
	 */
	public String getOperationCd() {
		return this.operationCd;
	}

	/**
	 * operationCd設定.
	 * @param operationCd operationCd
	 */
	public void setOperationCd(final String operationCd) {
		this.operationCd = operationCd;
	}

	/**
	 * operationName取得.
	 * @return operationName
	 */
	public String getOperationName() {
		return this.operationName;
	}

	/**
	 * operationName設定.
	 * @param operationName operationName
	 */
	public void setOperationName(final String operationName) {
		this.operationName = operationName;
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
	 * leadtime取得.
	 * @return leadtime
	 */
	public java.math.BigDecimal getLeadtime() {
		return this.leadtime;
	}

	/**
	 * leadtime設定.
	 * @param leadtime leadtime
	 */
	public void setLeadtime(final java.math.BigDecimal leadtime) {
		this.leadtime = leadtime;
	}

	/**
	 * startDate取得.
	 * @return startDate
	 */
	public java.sql.Timestamp getStartDate() {
		return this.startDate;
	}

	/**
	 * startDate設定.
	 * @param startDate startDate
	 */
	public void setStartDate(final java.sql.Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * endDate取得.
	 * @return endDate
	 */
	public java.sql.Timestamp getEndDate() {
		return this.endDate;
	}

	/**
	 * endDate設定.
	 * @param endDate endDate
	 */
	public void setEndDate(final java.sql.Timestamp endDate) {
		this.endDate = endDate;
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
	 * fillingQty取得.
	 * @return fillingQty
	 */
	public java.math.BigDecimal getFillingQty() {
		return this.fillingQty;
	}

	/**
	 * fillingQty設定.
	 * @param fillingQty fillingQty
	 */
	public void setFillingQty(final java.math.BigDecimal fillingQty) {
		this.fillingQty = fillingQty;
	}

	/**
	 * fillingUnit取得.
	 * @return fillingUnit
	 */
	public String getFillingUnit() {
		return this.fillingUnit;
	}

	/**
	 * fillingUnit設定.
	 * @param fillingUnit fillingUnit
	 */
	public void setFillingUnit(final String fillingUnit) {
		this.fillingUnit = fillingUnit;
	}

	/**
	 * net取得.
	 * @return net
	 */
	public java.math.BigDecimal getNet() {
		return this.net;
	}

	/**
	 * net設定.
	 * @param net net
	 */
	public void setNet(final java.math.BigDecimal net) {
		this.net = net;
	}

	/**
	 * weightMin取得.
	 * @return weightMin
	 */
	public java.math.BigDecimal getWeightMin() {
		return this.weightMin;
	}

	/**
	 * weightMin設定.
	 * @param weightMin weightMin
	 */
	public void setWeightMin(final java.math.BigDecimal weightMin) {
		this.weightMin = weightMin;
	}

	/**
	 * weightMax取得.
	 * @return weightMax
	 */
	public java.math.BigDecimal getWeightMax() {
		return this.weightMax;
	}

	/**
	 * weightMax設定.
	 * @param weightMax weightMax
	 */
	public void setWeightMax(final java.math.BigDecimal weightMax) {
		this.weightMax = weightMax;
	}

	/**
	 * filter取得.
	 * @return filter
	 */
	public java.math.BigDecimal getFilter() {
		return this.filter;
	}

	/**
	 * filter設定.
	 * @param filter filter
	 */
	public void setFilter(final java.math.BigDecimal filter) {
		this.filter = filter;
	}

	/**
	 * autoCheckerMin取得.
	 * @return autoCheckerMin
	 */
	public java.math.BigDecimal getAutoCheckerMin() {
		return this.autoCheckerMin;
	}

	/**
	 * autoCheckerMin設定.
	 * @param autoCheckerMin autoCheckerMin
	 */
	public void setAutoCheckerMin(final java.math.BigDecimal autoCheckerMin) {
		this.autoCheckerMin = autoCheckerMin;
	}

	/**
	 * autoCheckerMax取得.
	 * @return autoCheckerMax
	 */
	public java.math.BigDecimal getAutoCheckerMax() {
		return this.autoCheckerMax;
	}

	/**
	 * autoCheckerMax設定.
	 * @param autoCheckerMax autoCheckerMax
	 */
	public void setAutoCheckerMax(final java.math.BigDecimal autoCheckerMax) {
		this.autoCheckerMax = autoCheckerMax;
	}

	/**
	 * grossCheckerMin取得.
	 * @return grossCheckerMin
	 */
	public java.math.BigDecimal getGrossCheckerMin() {
		return this.grossCheckerMin;
	}

	/**
	 * grossCheckerMin設定.
	 * @param grossCheckerMin grossCheckerMin
	 */
	public void setGrossCheckerMin(final java.math.BigDecimal grossCheckerMin) {
		this.grossCheckerMin = grossCheckerMin;
	}

	/**
	 * grossCheckerMax取得.
	 * @return grossCheckerMax
	 */
	public java.math.BigDecimal getGrossCheckerMax() {
		return this.grossCheckerMax;
	}

	/**
	 * grossCheckerMax設定.
	 * @param grossCheckerMax grossCheckerMax
	 */
	public void setGrossCheckerMax(final java.math.BigDecimal grossCheckerMax) {
		this.grossCheckerMax = grossCheckerMax;
	}

	/**
	 * openingTorqueMin取得.
	 * @return openingTorqueMin
	 */
	public java.math.BigDecimal getOpeningTorqueMin() {
		return this.openingTorqueMin;
	}

	/**
	 * openingTorqueMin設定.
	 * @param openingTorqueMin openingTorqueMin
	 */
	public void setOpeningTorqueMin(final java.math.BigDecimal openingTorqueMin) {
		this.openingTorqueMin = openingTorqueMin;
	}

	/**
	 * openingTorqueMax取得.
	 * @return openingTorqueMax
	 */
	public java.math.BigDecimal getOpeningTorqueMax() {
		return this.openingTorqueMax;
	}

	/**
	 * openingTorqueMax設定.
	 * @param openingTorqueMax openingTorqueMax
	 */
	public void setOpeningTorqueMax(final java.math.BigDecimal openingTorqueMax) {
		this.openingTorqueMax = openingTorqueMax;
	}

	/**
	 * hotAirPresetTemp取得.
	 * @return hotAirPresetTemp
	 */
	public java.math.BigDecimal getHotAirPresetTemp() {
		return this.hotAirPresetTemp;
	}

	/**
	 * hotAirPresetTemp設定.
	 * @param hotAirPresetTemp hotAirPresetTemp
	 */
	public void setHotAirPresetTemp(final java.math.BigDecimal hotAirPresetTemp) {
		this.hotAirPresetTemp = hotAirPresetTemp;
	}

	/**
	 * hotAirPressure取得.
	 * @return hotAirPressure
	 */
	public java.math.BigDecimal getHotAirPressure() {
		return this.hotAirPressure;
	}

	/**
	 * hotAirPressure設定.
	 * @param hotAirPressure hotAirPressure
	 */
	public void setHotAirPressure(final java.math.BigDecimal hotAirPressure) {
		this.hotAirPressure = hotAirPressure;
	}

	/**
	 * mesh取得.
	 * @return mesh
	 */
	public java.math.BigDecimal getMesh() {
		return this.mesh;
	}

	/**
	 * mesh設定.
	 * @param mesh mesh
	 */
	public void setMesh(final java.math.BigDecimal mesh) {
		this.mesh = mesh;
	}

	/**
	 * autoCheckerAve取得.
	 * @return autoCheckerAve
	 */
	public java.math.BigDecimal getAutoCheckerAve() {
		return this.autoCheckerAve;
	}

	/**
	 * autoCheckerAve設定.
	 * @param autoCheckerAve autoCheckerAve
	 */
	public void setAutoCheckerAve(final java.math.BigDecimal autoCheckerAve) {
		this.autoCheckerAve = autoCheckerAve;
	}

	/**
	 * grossCheckerAve取得.
	 * @return grossCheckerAve
	 */
	public java.math.BigDecimal getGrossCheckerAve() {
		return this.grossCheckerAve;
	}

	/**
	 * grossCheckerAve設定.
	 * @param grossCheckerAve grossCheckerAve
	 */
	public void setGrossCheckerAve(final java.math.BigDecimal grossCheckerAve) {
		this.grossCheckerAve = grossCheckerAve;
	}

	/**
	 * closingTorqueMin取得.
	 * @return closingTorqueMin
	 */
	public java.math.BigDecimal getClosingTorqueMin() {
		return this.closingTorqueMin;
	}

	/**
	 * closingTorqueMin設定.
	 * @param closingTorqueMin closingTorqueMin
	 */
	public void setClosingTorqueMin(final java.math.BigDecimal closingTorqueMin) {
		this.closingTorqueMin = closingTorqueMin;
	}

	/**
	 * closingTorqueMax取得.
	 * @return closingTorqueMax
	 */
	public java.math.BigDecimal getClosingTorqueMax() {
		return this.closingTorqueMax;
	}

	/**
	 * closingTorqueMax設定.
	 * @param closingTorqueMax closingTorqueMax
	 */
	public void setClosingTorqueMax(final java.math.BigDecimal closingTorqueMax) {
		this.closingTorqueMax = closingTorqueMax;
	}

	/**
	 * torquePressure取得.
	 * @return torquePressure
	 */
	public java.math.BigDecimal getTorquePressure() {
		return this.torquePressure;
	}

	/**
	 * torquePressure設定.
	 * @param torquePressure torquePressure
	 */
	public void setTorquePressure(final java.math.BigDecimal torquePressure) {
		this.torquePressure = torquePressure;
	}

	/**
	 * airPressure取得.
	 * @return airPressure
	 */
	public java.math.BigDecimal getAirPressure() {
		return this.airPressure;
	}

	/**
	 * airPressure設定.
	 * @param airPressure airPressure
	 */
	public void setAirPressure(final java.math.BigDecimal airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * vcloseTime取得.
	 * @return vcloseTime
	 */
	public java.math.BigDecimal getVcloseTime() {
		return this.vcloseTime;
	}

	/**
	 * vcloseTime設定.
	 * @param vcloseTime vcloseTime
	 */
	public void setVcloseTime(final java.math.BigDecimal vcloseTime) {
		this.vcloseTime = vcloseTime;
	}

	/**
	 * firstHeatSeal取得.
	 * @return firstHeatSeal
	 */
	public java.math.BigDecimal getFirstHeatSeal() {
		return this.firstHeatSeal;
	}

	/**
	 * firstHeatSeal設定.
	 * @param firstHeatSeal firstHeatSeal
	 */
	public void setFirstHeatSeal(final java.math.BigDecimal firstHeatSeal) {
		this.firstHeatSeal = firstHeatSeal;
	}

	/**
	 * secondHeatSeal取得.
	 * @return secondHeatSeal
	 */
	public java.math.BigDecimal getSecondHeatSeal() {
		return this.secondHeatSeal;
	}

	/**
	 * secondHeatSeal設定.
	 * @param secondHeatSeal secondHeatSeal
	 */
	public void setSecondHeatSeal(final java.math.BigDecimal secondHeatSeal) {
		this.secondHeatSeal = secondHeatSeal;
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

