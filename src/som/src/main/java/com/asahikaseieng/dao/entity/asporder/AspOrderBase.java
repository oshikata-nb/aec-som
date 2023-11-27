/*
 * Created on Thu Apr 16 13:51:48 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.asporder;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AspOrderBaseクラス.
 * @author kanri-user
 */
public class AspOrderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AspOrderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ASP_ORDER";


//	/** IDアノテーション itemCd. */
//	public static final String itemCd_ID = "assigned";
//	/** IDアノテーション orderCd. */
//	public static final String orderCd_ID = "assigned";

	/** COLUMNアノテーション orderCd. */
	public static final String orderCd_COLUMN = "ORDER_CD";

	/** COLUMNアノテーション orderType. */
	public static final String orderType_COLUMN = "ORDER_TYPE";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション orderEst. */
	public static final String orderEst_COLUMN = "ORDER_EST";

	/** COLUMNアノテーション orderLet. */
	public static final String orderLet_COLUMN = "ORDER_LET";

	/** COLUMNアノテーション orderQty. */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション priority. */
	public static final String priority_COLUMN = "PRIORITY";

	/** COLUMNアノテーション customer. */
	public static final String customer_COLUMN = "CUSTOMER";

	/** COLUMNアノテーション color. */
	public static final String color_COLUMN = "COLOR";

	/** COLUMNアノテーション version. */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション assignmentDirection. */
	public static final String assignmentDirection_COLUMN = "ASSIGNMENT_DIRECTION";

	/** COLUMNアノテーション productionFactor. */
	public static final String productionFactor_COLUMN = "PRODUCTION_FACTOR";

	/** COLUMNアノテーション endBuffer. */
	public static final String endBuffer_COLUMN = "END_BUFFER";

	/** COLUMNアノテーション halfwayProcess. */
	public static final String halfwayProcess_COLUMN = "HALFWAY_PROCESS";

	/** COLUMNアノテーション disabled. */
	public static final String disabled_COLUMN = "DISABLED";

	/** COLUMNアノテーション lotResult. */
	public static final String lotResult_COLUMN = "LOT_RESULT";

	/** COLUMNアノテーション nextLot. */
	public static final String nextLot_COLUMN = "NEXT_LOT";

	/** COLUMNアノテーション orderComment. */
	public static final String orderComment_COLUMN = "ORDER_COMMENT";

	/** COLUMNアノテーション spec1. */
	public static final String spec1_COLUMN = "SPEC1";

	/** COLUMNアノテーション spec2. */
	public static final String spec2_COLUMN = "SPEC2";

	/** COLUMNアノテーション spec3. */
	public static final String spec3_COLUMN = "SPEC3";

	/** COLUMNアノテーション spec4. */
	public static final String spec4_COLUMN = "SPEC4";

	/** COLUMNアノテーション numspec1. */
	public static final String numspec1_COLUMN = "NUMSPEC1";

	/** COLUMNアノテーション numspec2. */
	public static final String numspec2_COLUMN = "NUMSPEC2";

	/** COLUMNアノテーション numspec3. */
	public static final String numspec3_COLUMN = "NUMSPEC3";

	/** COLUMNアノテーション numspec4. */
	public static final String numspec4_COLUMN = "NUMSPEC4";

	/** COLUMNアノテーション isreplenishment. */
	public static final String isreplenishment_COLUMN = "ISREPLENISHMENT";

	/** COLUMNアノテーション nisugata. */
	public static final String nisugata_COLUMN = "NISUGATA";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション holdtank. */
	public static final String holdtank_COLUMN = "HOLDTANK";

	/** COLUMNアノテーション workStatus. */
	public static final String workStatus_COLUMN = "WORK_STATUS";

	/** COLUMNアノテーション startTime. */
	public static final String startTime_COLUMN = "START_TIME";

	/** COLUMNアノテーション endTime. */
	public static final String endTime_COLUMN = "END_TIME";

	/** COLUMNアノテーション manufactureOrderCd. */
	public static final String manufactureOrderCd_COLUMN = "MANUFACTURE_ORDER_CD";

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

	private String orderType;

	private String itemCd;

	private String orderEst;

	private String orderLet;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal priority;

	private String customer;

	private java.math.BigDecimal color;

	private java.math.BigDecimal version;

	private String assignmentDirection;

	private String productionFactor;

	private String endBuffer;

	private String halfwayProcess;

	private String disabled;

	private String lotResult;

	private String nextLot;

	private String orderComment;

	private String spec1;

	private String spec2;

	private String spec3;

	private String spec4;

	private String numspec1;

	private String numspec2;

	private String numspec3;

	private String numspec4;

	private String isreplenishment;

	private String nisugata;

	private java.math.BigDecimal recipeId;

	private String holdtank;

	private String workStatus;

	private String startTime;

	private String endTime;

	private String manufactureOrderCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

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
	 * orderType取得.
	 * @return orderType
	 */
	public String getOrderType() {
		return this.orderType;
	}

	/**
	 * orderType設定.
	 * @param orderType orderType
	 */
	public void setOrderType(final String orderType) {
		this.orderType = orderType;
	}

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * orderEst取得.
	 * @return orderEst
	 */
	public String getOrderEst() {
		return this.orderEst;
	}

	/**
	 * orderEst設定.
	 * @param orderEst orderEst
	 */
	public void setOrderEst(final String orderEst) {
		this.orderEst = orderEst;
	}

	/**
	 * orderLet取得.
	 * @return orderLet
	 */
	public String getOrderLet() {
		return this.orderLet;
	}

	/**
	 * orderLet設定.
	 * @param orderLet orderLet
	 */
	public void setOrderLet(final String orderLet) {
		this.orderLet = orderLet;
	}

	/**
	 * orderQty取得.
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * priority取得.
	 * @return priority
	 */
	public java.math.BigDecimal getPriority() {
		return this.priority;
	}

	/**
	 * priority設定.
	 * @param priority priority
	 */
	public void setPriority(final java.math.BigDecimal priority) {
		this.priority = priority;
	}

	/**
	 * customer取得.
	 * @return customer
	 */
	public String getCustomer() {
		return this.customer;
	}

	/**
	 * customer設定.
	 * @param customer customer
	 */
	public void setCustomer(final String customer) {
		this.customer = customer;
	}

	/**
	 * color取得.
	 * @return color
	 */
	public java.math.BigDecimal getColor() {
		return this.color;
	}

	/**
	 * color設定.
	 * @param color color
	 */
	public void setColor(final java.math.BigDecimal color) {
		this.color = color;
	}

	/**
	 * version取得.
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return this.version;
	}

	/**
	 * version設定.
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
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
	 * productionFactor取得.
	 * @return productionFactor
	 */
	public String getProductionFactor() {
		return this.productionFactor;
	}

	/**
	 * productionFactor設定.
	 * @param productionFactor productionFactor
	 */
	public void setProductionFactor(final String productionFactor) {
		this.productionFactor = productionFactor;
	}

	/**
	 * endBuffer取得.
	 * @return endBuffer
	 */
	public String getEndBuffer() {
		return this.endBuffer;
	}

	/**
	 * endBuffer設定.
	 * @param endBuffer endBuffer
	 */
	public void setEndBuffer(final String endBuffer) {
		this.endBuffer = endBuffer;
	}

	/**
	 * halfwayProcess取得.
	 * @return halfwayProcess
	 */
	public String getHalfwayProcess() {
		return this.halfwayProcess;
	}

	/**
	 * halfwayProcess設定.
	 * @param halfwayProcess halfwayProcess
	 */
	public void setHalfwayProcess(final String halfwayProcess) {
		this.halfwayProcess = halfwayProcess;
	}

	/**
	 * disabled取得.
	 * @return disabled
	 */
	public String getDisabled() {
		return this.disabled;
	}

	/**
	 * disabled設定.
	 * @param disabled disabled
	 */
	public void setDisabled(final String disabled) {
		this.disabled = disabled;
	}

	/**
	 * lotResult取得.
	 * @return lotResult
	 */
	public String getLotResult() {
		return this.lotResult;
	}

	/**
	 * lotResult設定.
	 * @param lotResult lotResult
	 */
	public void setLotResult(final String lotResult) {
		this.lotResult = lotResult;
	}

	/**
	 * nextLot取得.
	 * @return nextLot
	 */
	public String getNextLot() {
		return this.nextLot;
	}

	/**
	 * nextLot設定.
	 * @param nextLot nextLot
	 */
	public void setNextLot(final String nextLot) {
		this.nextLot = nextLot;
	}

	/**
	 * orderComment取得.
	 * @return orderComment
	 */
	public String getOrderComment() {
		return this.orderComment;
	}

	/**
	 * orderComment設定.
	 * @param orderComment orderComment
	 */
	public void setOrderComment(final String orderComment) {
		this.orderComment = orderComment;
	}

	/**
	 * spec1取得.
	 * @return spec1
	 */
	public String getSpec1() {
		return this.spec1;
	}

	/**
	 * spec1設定.
	 * @param spec1 spec1
	 */
	public void setSpec1(final String spec1) {
		this.spec1 = spec1;
	}

	/**
	 * spec2取得.
	 * @return spec2
	 */
	public String getSpec2() {
		return this.spec2;
	}

	/**
	 * spec2設定.
	 * @param spec2 spec2
	 */
	public void setSpec2(final String spec2) {
		this.spec2 = spec2;
	}

	/**
	 * spec3取得.
	 * @return spec3
	 */
	public String getSpec3() {
		return this.spec3;
	}

	/**
	 * spec3設定.
	 * @param spec3 spec3
	 */
	public void setSpec3(final String spec3) {
		this.spec3 = spec3;
	}

	/**
	 * spec4取得.
	 * @return spec4
	 */
	public String getSpec4() {
		return this.spec4;
	}

	/**
	 * spec4設定.
	 * @param spec4 spec4
	 */
	public void setSpec4(final String spec4) {
		this.spec4 = spec4;
	}

	/**
	 * numspec1取得.
	 * @return numspec1
	 */
	public String getNumspec1() {
		return this.numspec1;
	}

	/**
	 * numspec1設定.
	 * @param numspec1 numspec1
	 */
	public void setNumspec1(final String numspec1) {
		this.numspec1 = numspec1;
	}

	/**
	 * numspec2取得.
	 * @return numspec2
	 */
	public String getNumspec2() {
		return this.numspec2;
	}

	/**
	 * numspec2設定.
	 * @param numspec2 numspec2
	 */
	public void setNumspec2(final String numspec2) {
		this.numspec2 = numspec2;
	}

	/**
	 * numspec3取得.
	 * @return numspec3
	 */
	public String getNumspec3() {
		return this.numspec3;
	}

	/**
	 * numspec3設定.
	 * @param numspec3 numspec3
	 */
	public void setNumspec3(final String numspec3) {
		this.numspec3 = numspec3;
	}

	/**
	 * numspec4取得.
	 * @return numspec4
	 */
	public String getNumspec4() {
		return this.numspec4;
	}

	/**
	 * numspec4設定.
	 * @param numspec4 numspec4
	 */
	public void setNumspec4(final String numspec4) {
		this.numspec4 = numspec4;
	}

	/**
	 * isreplenishment取得.
	 * @return isreplenishment
	 */
	public String getIsreplenishment() {
		return this.isreplenishment;
	}

	/**
	 * isreplenishment設定.
	 * @param isreplenishment isreplenishment
	 */
	public void setIsreplenishment(final String isreplenishment) {
		this.isreplenishment = isreplenishment;
	}

	/**
	 * nisugata取得.
	 * @return nisugata
	 */
	public String getNisugata() {
		return this.nisugata;
	}

	/**
	 * nisugata設定.
	 * @param nisugata nisugata
	 */
	public void setNisugata(final String nisugata) {
		this.nisugata = nisugata;
	}

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
	 * holdtank取得.
	 * @return holdtank
	 */
	public String getHoldtank() {
		return this.holdtank;
	}

	/**
	 * holdtank設定.
	 * @param holdtank holdtank
	 */
	public void setHoldtank(final String holdtank) {
		this.holdtank = holdtank;
	}

	/**
	 * workStatus取得.
	 * @return workStatus
	 */
	public String getWorkStatus() {
		return this.workStatus;
	}

	/**
	 * workStatus設定.
	 * @param workStatus workStatus
	 */
	public void setWorkStatus(final String workStatus) {
		this.workStatus = workStatus;
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
	 * manufactureOrderCd取得.
	 * @return manufactureOrderCd
	 */
	public String getManufactureOrderCd() {
		return this.manufactureOrderCd;
	}

	/**
	 * manufactureOrderCd設定.
	 * @param manufactureOrderCd manufactureOrderCd
	 */
	public void setManufactureOrderCd(final String manufactureOrderCd) {
		this.manufactureOrderCd = manufactureOrderCd;
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
