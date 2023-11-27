/*
 * Created on Thu Apr 16 13:52:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AspImportAspOperationBaseクラス.
 * @author 
 */
public class AspImportAspOperationBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AspImportAspOperationBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ASP_OPERATION";


//	/** IDアノテーション operationCd. */
//	public static final String operationCd_ID = "assigned";
//	/** IDアノテーション orderCd. */
//	public static final String orderCd_ID = "assigned";

	/** COLUMNアノテーション orderCd. */
	public static final String orderCd_COLUMN = "ORDER_CD";

	/** COLUMNアノテーション operationCd. */
	public static final String operationCd_COLUMN = "OPERATION_CD";

	/** COLUMNアノテーション operationType. */
	public static final String operationType_COLUMN = "OPERATION_TYPE";

	/** COLUMNアノテーション mainItem. */
	public static final String mainItem_COLUMN = "MAIN_ITEM";

	/** COLUMNアノテーション qty. */
	public static final String qty_COLUMN = "QTY";

	/** COLUMNアノテーション uQty. */
	public static final String uQty_COLUMN = "U_QTY";

	/** COLUMNアノテーション uProductionTime. */
	public static final String uProductionTime_COLUMN = "U_PRODUCTION_TIME";

	/** COLUMNアノテーション uEst. */
	public static final String uEst_COLUMN = "U_EST";

	/** COLUMNアノテーション uLet. */
	public static final String uLet_COLUMN = "U_LET";

	/** COLUMNアノテーション uProductionFactor. */
	public static final String uProductionFactor_COLUMN = "U_PRODUCTION_FACTOR";

	/** COLUMNアノテーション operationComment. */
	public static final String operationComment_COLUMN = "OPERATION_COMMENT";

	/** COLUMNアノテーション isassigned. */
	public static final String isassigned_COLUMN = "ISASSIGNED";

	/** COLUMNアノテーション assignmentDirection. */
	public static final String assignmentDirection_COLUMN = "ASSIGNMENT_DIRECTION";

	/** COLUMNアノテーション mainRes. */
	public static final String mainRes_COLUMN = "MAIN_RES";

	/** COLUMNアノテーション startTime. */
	public static final String startTime_COLUMN = "START_TIME";

	/** COLUMNアノテーション endTime. */
	public static final String endTime_COLUMN = "END_TIME";

	/** COLUMNアノテーション productionTime. */
	public static final String productionTime_COLUMN = "PRODUCTION_TIME";

	/** COLUMNアノテーション timeFixLvl. */
	public static final String timeFixLvl_COLUMN = "TIME_FIX_LVL";

	/** COLUMNアノテーション resFixFlg. */
	public static final String resFixFlg_COLUMN = "RES_FIX_FLG";

	/** COLUMNアノテーション uTimeFixLvl. */
	public static final String uTimeFixLvl_COLUMN = "U_TIME_FIX_LVL";

	/** COLUMNアノテーション status. */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション resultStartTime. */
	public static final String resultStartTime_COLUMN = "RESULT_START_TIME";

	/** COLUMNアノテーション resultEndTime. */
	public static final String resultEndTime_COLUMN = "RESULT_END_TIME";

	/** COLUMNアノテーション resultQty. */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション resultProgress. */
	public static final String resultProgress_COLUMN = "RESULT_PROGRESS";

	/** COLUMNアノテーション resultMainRes. */
	public static final String resultMainRes_COLUMN = "RESULT_MAIN_RES";

	/** COLUMNアノテーション resultScrap. */
	public static final String resultScrap_COLUMN = "RESULT_SCRAP";

	/** COLUMNアノテーション resultObtainTime. */
	public static final String resultObtainTime_COLUMN = "RESULT_OBTAIN_TIME";

	/** COLUMNアノテーション resultRecursiveFlg. */
	public static final String resultRecursiveFlg_COLUMN = "RESULT_RECURSIVE_FLG";

	/** COLUMNアノテーション resultTimeSeries. */
	public static final String resultTimeSeries_COLUMN = "RESULT_TIME_SERIES";

	/** COLUMNアノテーション splitNumber. */
	public static final String splitNumber_COLUMN = "SPLIT_NUMBER";

	/** COLUMNアノテーション splitRatio. */
	public static final String splitRatio_COLUMN = "SPLIT_RATIO";

	/** COLUMNアノテーション splitParateNumber. */
	public static final String splitParateNumber_COLUMN = "SPLIT_PARATE_NUMBER";

	/** COLUMNアノテーション splitQtyMin. */
	public static final String splitQtyMin_COLUMN = "SPLIT_QTY_MIN";

	/** COLUMNアノテーション splitQtyMax. */
	public static final String splitQtyMax_COLUMN = "SPLIT_QTY_MAX";

	/** COLUMNアノテーション splitQtyUnit. */
	public static final String splitQtyUnit_COLUMN = "SPLIT_QTY_UNIT";

	/** COLUMNアノテーション operationProcNo. */
	public static final String operationProcNo_COLUMN = "OPERATION_PROC_NO";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String orderCd;

	private String operationCd;

	private String operationType;

	private String mainItem;

	private java.math.BigDecimal qty;

	private java.math.BigDecimal uQty;

	private String uProductionTime;

	private String uEst;

	private String uLet;

	private String uProductionFactor;

	private String operationComment;

	private String isassigned;

	private String assignmentDirection;

	private String mainRes;

	private String startTime;

	private String endTime;

	private java.math.BigDecimal productionTime;

	private String timeFixLvl;

	private String resFixFlg;

	private String uTimeFixLvl;

	private String status;

	private String resultStartTime;

	private String resultEndTime;

	private java.math.BigDecimal resultQty;

	private java.math.BigDecimal resultProgress;

	private String resultMainRes;

	private java.math.BigDecimal resultScrap;

	private String resultObtainTime;

	private String resultRecursiveFlg;

	private String resultTimeSeries;

	private java.math.BigDecimal splitNumber;

	private String splitRatio;

	private java.math.BigDecimal splitParateNumber;

	private java.math.BigDecimal splitQtyMin;

	private java.math.BigDecimal splitQtyMax;

	private java.math.BigDecimal splitQtyUnit;

	private java.math.BigDecimal operationProcNo;

	private String inputDate;

	private String inputorCd;

	private String updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * orderCd取得.
	 * @return orderCd
	 */
	public String getOrderCd() {
		return this.orderCd;
	}

	/**
	 * orderCd設定.
	 * @param orderCd orderCd
	 */
	public void setOrderCd(final String orderCd) {
		this.orderCd = orderCd;
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
	 * operationType取得.
	 * @return operationType
	 */
	public String getOperationType() {
		return this.operationType;
	}

	/**
	 * operationType設定.
	 * @param operationType operationType
	 */
	public void setOperationType(final String operationType) {
		this.operationType = operationType;
	}

	/**
	 * mainItem取得.
	 * @return mainItem
	 */
	public String getMainItem() {
		return this.mainItem;
	}

	/**
	 * mainItem設定.
	 * @param mainItem mainItem
	 */
	public void setMainItem(final String mainItem) {
		this.mainItem = mainItem;
	}

	/**
	 * qty取得.
	 * @return qty
	 */
	public java.math.BigDecimal getQty() {
		return this.qty;
	}

	/**
	 * qty設定.
	 * @param qty qty
	 */
	public void setQty(final java.math.BigDecimal qty) {
		this.qty = qty;
	}

	/**
	 * uQty取得.
	 * @return uQty
	 */
	public java.math.BigDecimal getUQty() {
		return this.uQty;
	}

	/**
	 * uQty設定.
	 * @param uQty uQty
	 */
	public void setUQty(final java.math.BigDecimal uQty) {
		this.uQty = uQty;
	}

	/**
	 * uProductionTime取得.
	 * @return uProductionTime
	 */
	public String getUProductionTime() {
		return this.uProductionTime;
	}

	/**
	 * uProductionTime設定.
	 * @param uProductionTime uProductionTime
	 */
	public void setUProductionTime(final String uProductionTime) {
		this.uProductionTime = uProductionTime;
	}

	/**
	 * uEst取得.
	 * @return uEst
	 */
	public String getUEst() {
		return this.uEst;
	}

	/**
	 * uEst設定.
	 * @param uEst uEst
	 */
	public void setUEst(final String uEst) {
		this.uEst = uEst;
	}

	/**
	 * uLet取得.
	 * @return uLet
	 */
	public String getULet() {
		return this.uLet;
	}

	/**
	 * uLet設定.
	 * @param uLet uLet
	 */
	public void setULet(final String uLet) {
		this.uLet = uLet;
	}

	/**
	 * uProductionFactor取得.
	 * @return uProductionFactor
	 */
	public String getUProductionFactor() {
		return this.uProductionFactor;
	}

	/**
	 * uProductionFactor設定.
	 * @param uProductionFactor uProductionFactor
	 */
	public void setUProductionFactor(final String uProductionFactor) {
		this.uProductionFactor = uProductionFactor;
	}

	/**
	 * operationComment取得.
	 * @return operationComment
	 */
	public String getOperationComment() {
		return this.operationComment;
	}

	/**
	 * operationComment設定.
	 * @param operationComment operationComment
	 */
	public void setOperationComment(final String operationComment) {
		this.operationComment = operationComment;
	}

	/**
	 * isassigned取得.
	 * @return isassigned
	 */
	public String getIsassigned() {
		return this.isassigned;
	}

	/**
	 * isassigned設定.
	 * @param isassigned isassigned
	 */
	public void setIsassigned(final String isassigned) {
		this.isassigned = isassigned;
	}

	/**
	 * assignmentDirection取得.
	 * @return assignmentDirection
	 */
	public String getAssignmentDirection() {
		return this.assignmentDirection;
	}

	/**
	 * assignmentDirection設定.
	 * @param assignmentDirection assignmentDirection
	 */
	public void setAssignmentDirection(final String assignmentDirection) {
		this.assignmentDirection = assignmentDirection;
	}

	/**
	 * mainRes取得.
	 * @return mainRes
	 */
	public String getMainRes() {
		return this.mainRes;
	}

	/**
	 * mainRes設定.
	 * @param mainRes mainRes
	 */
	public void setMainRes(final String mainRes) {
		this.mainRes = mainRes;
	}

	/**
	 * startTime取得.
	 * @return startTime
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * startTime設定.
	 * @param startTime startTime
	 */
	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	/**
	 * endTime取得.
	 * @return endTime
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * endTime設定.
	 * @param endTime endTime
	 */
	public void setEndTime(final String endTime) {
		this.endTime = endTime;
	}

	/**
	 * productionTime取得.
	 * @return productionTime
	 */
	public java.math.BigDecimal getProductionTime() {
		return this.productionTime;
	}

	/**
	 * productionTime設定.
	 * @param productionTime productionTime
	 */
	public void setProductionTime(final java.math.BigDecimal productionTime) {
		this.productionTime = productionTime;
	}

	/**
	 * timeFixLvl取得.
	 * @return timeFixLvl
	 */
	public String getTimeFixLvl() {
		return this.timeFixLvl;
	}

	/**
	 * timeFixLvl設定.
	 * @param timeFixLvl timeFixLvl
	 */
	public void setTimeFixLvl(final String timeFixLvl) {
		this.timeFixLvl = timeFixLvl;
	}

	/**
	 * resFixFlg取得.
	 * @return resFixFlg
	 */
	public String getResFixFlg() {
		return this.resFixFlg;
	}

	/**
	 * resFixFlg設定.
	 * @param resFixFlg resFixFlg
	 */
	public void setResFixFlg(final String resFixFlg) {
		this.resFixFlg = resFixFlg;
	}

	/**
	 * uTimeFixLvl取得.
	 * @return uTimeFixLvl
	 */
	public String getUTimeFixLvl() {
		return this.uTimeFixLvl;
	}

	/**
	 * uTimeFixLvl設定.
	 * @param uTimeFixLvl uTimeFixLvl
	 */
	public void setUTimeFixLvl(final String uTimeFixLvl) {
		this.uTimeFixLvl = uTimeFixLvl;
	}

	/**
	 * status取得.
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * resultStartTime取得.
	 * @return resultStartTime
	 */
	public String getResultStartTime() {
		return this.resultStartTime;
	}

	/**
	 * resultStartTime設定.
	 * @param resultStartTime resultStartTime
	 */
	public void setResultStartTime(final String resultStartTime) {
		this.resultStartTime = resultStartTime;
	}

	/**
	 * resultEndTime取得.
	 * @return resultEndTime
	 */
	public String getResultEndTime() {
		return this.resultEndTime;
	}

	/**
	 * resultEndTime設定.
	 * @param resultEndTime resultEndTime
	 */
	public void setResultEndTime(final String resultEndTime) {
		this.resultEndTime = resultEndTime;
	}

	/**
	 * resultQty取得.
	 * @return resultQty
	 */
	public java.math.BigDecimal getResultQty() {
		return this.resultQty;
	}

	/**
	 * resultQty設定.
	 * @param resultQty resultQty
	 */
	public void setResultQty(final java.math.BigDecimal resultQty) {
		this.resultQty = resultQty;
	}

	/**
	 * resultProgress取得.
	 * @return resultProgress
	 */
	public java.math.BigDecimal getResultProgress() {
		return this.resultProgress;
	}

	/**
	 * resultProgress設定.
	 * @param resultProgress resultProgress
	 */
	public void setResultProgress(final java.math.BigDecimal resultProgress) {
		this.resultProgress = resultProgress;
	}

	/**
	 * resultMainRes取得.
	 * @return resultMainRes
	 */
	public String getResultMainRes() {
		return this.resultMainRes;
	}

	/**
	 * resultMainRes設定.
	 * @param resultMainRes resultMainRes
	 */
	public void setResultMainRes(final String resultMainRes) {
		this.resultMainRes = resultMainRes;
	}

	/**
	 * resultScrap取得.
	 * @return resultScrap
	 */
	public java.math.BigDecimal getResultScrap() {
		return this.resultScrap;
	}

	/**
	 * resultScrap設定.
	 * @param resultScrap resultScrap
	 */
	public void setResultScrap(final java.math.BigDecimal resultScrap) {
		this.resultScrap = resultScrap;
	}

	/**
	 * resultObtainTime取得.
	 * @return resultObtainTime
	 */
	public String getResultObtainTime() {
		return this.resultObtainTime;
	}

	/**
	 * resultObtainTime設定.
	 * @param resultObtainTime resultObtainTime
	 */
	public void setResultObtainTime(final String resultObtainTime) {
		this.resultObtainTime = resultObtainTime;
	}

	/**
	 * resultRecursiveFlg取得.
	 * @return resultRecursiveFlg
	 */
	public String getResultRecursiveFlg() {
		return this.resultRecursiveFlg;
	}

	/**
	 * resultRecursiveFlg設定.
	 * @param resultRecursiveFlg resultRecursiveFlg
	 */
	public void setResultRecursiveFlg(final String resultRecursiveFlg) {
		this.resultRecursiveFlg = resultRecursiveFlg;
	}

	/**
	 * resultTimeSeries取得.
	 * @return resultTimeSeries
	 */
	public String getResultTimeSeries() {
		return this.resultTimeSeries;
	}

	/**
	 * resultTimeSeries設定.
	 * @param resultTimeSeries resultTimeSeries
	 */
	public void setResultTimeSeries(final String resultTimeSeries) {
		this.resultTimeSeries = resultTimeSeries;
	}

	/**
	 * splitNumber取得.
	 * @return splitNumber
	 */
	public java.math.BigDecimal getSplitNumber() {
		return this.splitNumber;
	}

	/**
	 * splitNumber設定.
	 * @param splitNumber splitNumber
	 */
	public void setSplitNumber(final java.math.BigDecimal splitNumber) {
		this.splitNumber = splitNumber;
	}

	/**
	 * splitRatio取得.
	 * @return splitRatio
	 */
	public String getSplitRatio() {
		return this.splitRatio;
	}

	/**
	 * splitRatio設定.
	 * @param splitRatio splitRatio
	 */
	public void setSplitRatio(final String splitRatio) {
		this.splitRatio = splitRatio;
	}

	/**
	 * splitParateNumber取得.
	 * @return splitParateNumber
	 */
	public java.math.BigDecimal getSplitParateNumber() {
		return this.splitParateNumber;
	}

	/**
	 * splitParateNumber設定.
	 * @param splitParateNumber splitParateNumber
	 */
	public void setSplitParateNumber(final java.math.BigDecimal splitParateNumber) {
		this.splitParateNumber = splitParateNumber;
	}

	/**
	 * splitQtyMin取得.
	 * @return splitQtyMin
	 */
	public java.math.BigDecimal getSplitQtyMin() {
		return this.splitQtyMin;
	}

	/**
	 * splitQtyMin設定.
	 * @param splitQtyMin splitQtyMin
	 */
	public void setSplitQtyMin(final java.math.BigDecimal splitQtyMin) {
		this.splitQtyMin = splitQtyMin;
	}

	/**
	 * splitQtyMax取得.
	 * @return splitQtyMax
	 */
	public java.math.BigDecimal getSplitQtyMax() {
		return this.splitQtyMax;
	}

	/**
	 * splitQtyMax設定.
	 * @param splitQtyMax splitQtyMax
	 */
	public void setSplitQtyMax(final java.math.BigDecimal splitQtyMax) {
		this.splitQtyMax = splitQtyMax;
	}

	/**
	 * splitQtyUnit取得.
	 * @return splitQtyUnit
	 */
	public java.math.BigDecimal getSplitQtyUnit() {
		return this.splitQtyUnit;
	}

	/**
	 * splitQtyUnit設定.
	 * @param splitQtyUnit splitQtyUnit
	 */
	public void setSplitQtyUnit(final java.math.BigDecimal splitQtyUnit) {
		this.splitQtyUnit = splitQtyUnit;
	}

	/**
	 * operationProcNo取得.
	 * @return operationProcNo
	 */
	public java.math.BigDecimal getOperationProcNo() {
		return this.operationProcNo;
	}

	/**
	 * operationProcNo設定.
	 * @param operationProcNo operationProcNo
	 */
	public void setOperationProcNo(final java.math.BigDecimal operationProcNo) {
		this.operationProcNo = operationProcNo;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public String getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final String inputDate) {
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
	public String getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final String updateDate) {
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
