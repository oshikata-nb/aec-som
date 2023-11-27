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
 * StockBookingHeaderListForReportクラス.
 * @author kanri-user
 */
public class StockBookingHeaderListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingHeaderListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionNo */
	public static final String directionNo_COLUMN = "DIRECTION_NO";

	/** COLUMNアノテーション directionDate */
	public static final String directionDate_COLUMN = "DIRECTION_DATE";

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

	/** COLUMNアノテーション directionStatus */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

	/** COLUMNアノテーション directionStatusName */
	public static final String directionStatusName_COLUMN = "DIRECTION_STATUS_NAME";

	/** COLUMNアノテーション recipeId */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション productionLine */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション compoundTankNo */
	public static final String compoundTankNo_COLUMN = "COMPOUND_TANK_NO";

	/** COLUMNアノテーション compoundTankName */
	public static final String compoundTankName_COLUMN = "COMPOUND_TANK_NAME";

	/** COLUMNアノテーション holdTankNo */
	public static final String holdTankNo_COLUMN = "HOLD_TANK_NO";

	/** COLUMNアノテーション holdTankName */
	public static final String holdTankName_COLUMN = "HOLD_TANK_NAME";

	/** COLUMNアノテーション dissolutionTankNo */
	public static final String dissolutionTankNo_COLUMN = "DISSOLUTION_TANK_NO";

	/** COLUMNアノテーション dissolutionTankName */
	public static final String dissolutionTankName_COLUMN = "DISSOLUTION_TANK_NAME";

	/** COLUMNアノテーション filltankNo */
	public static final String filltankNo_COLUMN = "FILLTANK_NO";

	/** COLUMNアノテーション filltankName */
	public static final String filltankName_COLUMN = "FILLTANK_NAME";

	/** COLUMNアノテーション packageLine */
	public static final String packageLine_COLUMN = "PACKAGE_LINE";

	/** COLUMNアノテーション packageLineName */
	public static final String packageLineName_COLUMN = "PACKAGE_LINE_NAME";

	/** COLUMNアノテーション currentStepNo */
	public static final String currentStepNo_COLUMN = "CURRENT_STEP_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション name01 */
	public static final String name01_COLUMN = "NAME01";

	/** COLUMNアノテーション planedQty */
	public static final String planedQty_COLUMN = "PLANED_QTY";

	/** COLUMNアノテーション resultQty */
	public static final String resultQty_COLUMN = "RESULT_QTY";

	/** COLUMNアノテーション divideFlag */
	public static final String divideFlag_COLUMN = "DIVIDE_FLAG";

	/** COLUMNアノテーション nextPlanedQty */
	public static final String nextPlanedQty_COLUMN = "NEXT_PLANED_QTY";

	/** COLUMNアノテーション pdwResult */
	public static final String pdwResult_COLUMN = "PDW_RESULT";

	/** COLUMNアノテーション peocessLoss */
	public static final String peocessLoss_COLUMN = "PEOCESS_LOSS";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション planedSdate */
	public static final String planedSdate_COLUMN = "PLANED_SDATE";

	/** COLUMNアノテーション planedEdate */
	public static final String planedEdate_COLUMN = "PLANED_EDATE";

	/** COLUMNアノテーション resultSdate */
	public static final String resultSdate_COLUMN = "RESULT_SDATE";

	/** COLUMNアノテーション resultEdate */
	public static final String resultEdate_COLUMN = "RESULT_EDATE";

	/** COLUMNアノテーション steritSdate */
	public static final String steritSdate_COLUMN = "STERIT_SDATE";

	/** COLUMNアノテーション steritEdate */
	public static final String steritEdate_COLUMN = "STERIT_EDATE";

	/** COLUMNアノテーション mekkinTankTempMin */
	public static final String mekkinTankTempMin_COLUMN = "MEKKIN_TANK_TEMP_MIN";

	/** COLUMNアノテーション mekkinTankTempMax */
	public static final String mekkinTankTempMax_COLUMN = "MEKKIN_TANK_TEMP_MAX";

	/** COLUMNアノテーション haisuiCheck */
	public static final String haisuiCheck_COLUMN = "HAISUI_CHECK";

	/** COLUMNアノテーション stampFlag */
	public static final String stampFlag_COLUMN = "STAMP_FLAG";

	/** COLUMNアノテーション stampDate */
	public static final String stampDate_COLUMN = "STAMP_DATE";

	/** COLUMNアノテーション stampTantoCd */
	public static final String stampTantoCd_COLUMN = "STAMP_TANTO_CD";

	/** COLUMNアノテーション stampTantoName */
	public static final String stampTantoName_COLUMN = "STAMP_TANTO_NAME";

	/** COLUMNアノテーション productLabelFlag */
	public static final String productLabelFlag_COLUMN = "PRODUCT_LABEL_FLAG";

	/** COLUMNアノテーション productLabelDate */
	public static final String productLabelDate_COLUMN = "PRODUCT_LABEL_DATE";

	/** COLUMNアノテーション productLabelTantoCd */
	public static final String productLabelTantoCd_COLUMN = "PRODUCT_LABEL_TANTO_CD";

	/** COLUMNアノテーション productLabelTantoName */
	public static final String productLabelTantoName_COLUMN = "PRODUCT_LABEL_TANTO_NAME";

	/** COLUMNアノテーション productRecordFlag */
	public static final String productRecordFlag_COLUMN = "PRODUCT_RECORD_FLAG";

	/** COLUMNアノテーション productRecordDate */
	public static final String productRecordDate_COLUMN = "PRODUCT_RECORD_DATE";

	/** COLUMNアノテーション productRecordTantoCd */
	public static final String productRecordTantoCd_COLUMN = "PRODUCT_RECORD_TANTO_CD";

	/** COLUMNアノテーション productRecordTantoName */
	public static final String productRecordTantoName_COLUMN = "PRODUCT_RECORD_TANTO_NAME";

	/** COLUMNアノテーション stockdissLabelFlag */
	public static final String stockdissLabelFlag_COLUMN = "STOCKDISS_LABEL_FLAG";

	/** COLUMNアノテーション stockdissLabelDate */
	public static final String stockdissLabelDate_COLUMN = "STOCKDISS_LABEL_DATE";

	/** COLUMNアノテーション stockdissLabelTantoCd */
	public static final String stockdissLabelTantoCd_COLUMN = "STOCKDISS_LABEL_TANTO_CD";

	/** COLUMNアノテーション stockdissLabelTantoName */
	public static final String stockdissLabelTantoName_COLUMN = "STOCKDISS_LABEL_TANTO_NAME";

	/** COLUMNアノテーション certificationFlag */
	public static final String certificationFlag_COLUMN = "CERTIFICATION_FLAG";

	/** COLUMNアノテーション certificationDate */
	public static final String certificationDate_COLUMN = "CERTIFICATION_DATE";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション deleteFlag */
	public static final String deleteFlag_COLUMN = "DELETE_FLAG";

	/** COLUMNアノテーション seizotantocode */
	public static final String seizotantocode_COLUMN = "SEIZOTANTOCODE";

	/** COLUMNアノテーション seizotantoName */
	public static final String seizotantoName_COLUMN = "SEIZOTANTO_NAME";

	/** COLUMNアノテーション senjotantocode */
	public static final String senjotantocode_COLUMN = "SENJOTANTOCODE";

	/** COLUMNアノテーション senjotantoName */
	public static final String senjotantoName_COLUMN = "SENJOTANTO_NAME";

	/** COLUMNアノテーション mekkintantocode */
	public static final String mekkintantocode_COLUMN = "MEKKINTANTOCODE";

	/** COLUMNアノテーション mekkintantoName */
	public static final String mekkintantoName_COLUMN = "MEKKINTANTO_NAME";

	/** COLUMNアノテーション chogotankcondi */
	public static final String chogotankcondi_COLUMN = "CHOGOTANKCONDI";

	/** COLUMNアノテーション yobiyokaicondi */
	public static final String yobiyokaicondi_COLUMN = "YOBIYOKAICONDI";

	/** COLUMNアノテーション holdtankcondi */
	public static final String holdtankcondi_COLUMN = "HOLDTANKCONDI";

	/** COLUMNアノテーション totalJobtime */
	public static final String totalJobtime_COLUMN = "TOTAL_JOBTIME";

	/** COLUMNアノテーション enployJobtime */
	public static final String enployJobtime_COLUMN = "ENPLOY_JOBTIME";

	/** COLUMNアノテーション cooperJobtime */
	public static final String cooperJobtime_COLUMN = "COOPER_JOBTIME";

	/** COLUMNアノテーション appTantoCd */
	public static final String appTantoCd_COLUMN = "APP_TANTO_CD";

	/** COLUMNアノテーション appTantoName */
	public static final String appTantoName_COLUMN = "APP_TANTO_NAME";

	/** COLUMNアノテーション appDate */
	public static final String appDate_COLUMN = "APP_DATE";

	/** COLUMNアノテーション lastAppTantoCd */
	public static final String lastAppTantoCd_COLUMN = "LAST_APP_TANTO_CD";

	/** COLUMNアノテーション lastAppTantoName */
	public static final String lastAppTantoName_COLUMN = "LAST_APP_TANTO_NAME";

	/** COLUMNアノテーション lastAppDate */
	public static final String lastAppDate_COLUMN = "LAST_APP_DATE";

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

	/** COLUMNアノテーション sumSuuryou */
	public static final String sumSuuryou_COLUMN = "SUM_SUURYOU";

	/** COLUMNアノテーション location */
	public static final String location_COLUMN = "LOCATION";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal directionDivision;

	private String directionNo;

	private java.sql.Timestamp directionDate;

	private String aspOrderNo;

	private java.math.BigDecimal directionStatus;

	private String directionStatusName;

	private java.math.BigDecimal recipeId;

	private String recipeCd;

	private java.math.BigDecimal recipeVersion;

	private String productionLine;

	private String productionLineName;

	private String compoundTankNo;

	private String compoundTankName;

	private String holdTankNo;

	private String holdTankName;

	private String dissolutionTankNo;

	private String dissolutionTankName;

	private String filltankNo;

	private String filltankName;

	private String packageLine;

	private String packageLineName;

	private String currentStepNo;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String name01;

	private java.math.BigDecimal planedQty;

	private java.math.BigDecimal resultQty;

	private java.math.BigDecimal divideFlag;

	private java.math.BigDecimal nextPlanedQty;

	private java.math.BigDecimal pdwResult;

	private java.math.BigDecimal peocessLoss;

	private String lotNo;

	private java.sql.Timestamp planedSdate;

	private java.sql.Timestamp planedEdate;

	private java.sql.Timestamp resultSdate;

	private java.sql.Timestamp resultEdate;

	private java.sql.Timestamp steritSdate;

	private java.sql.Timestamp steritEdate;

	private java.math.BigDecimal mekkinTankTempMin;

	private java.math.BigDecimal mekkinTankTempMax;

	private String haisuiCheck;

	private java.math.BigDecimal stampFlag;

	private java.sql.Timestamp stampDate;

	private String stampTantoCd;

	private String stampTantoName;

	private java.math.BigDecimal productLabelFlag;

	private java.sql.Timestamp productLabelDate;

	private String productLabelTantoCd;

	private String productLabelTantoName;

	private java.math.BigDecimal productRecordFlag;

	private java.sql.Timestamp productRecordDate;

	private String productRecordTantoCd;

	private String productRecordTantoName;

	private java.math.BigDecimal stockdissLabelFlag;

	private java.sql.Timestamp stockdissLabelDate;

	private String stockdissLabelTantoCd;

	private String stockdissLabelTantoName;

	private java.math.BigDecimal certificationFlag;

	private java.sql.Timestamp certificationDate;

	private String remark;

	private String notes;

	private java.math.BigDecimal deleteFlag;

	private String seizotantocode;

	private String seizotantoName;

	private String senjotantocode;

	private String senjotantoName;

	private String mekkintantocode;

	private String mekkintantoName;

	private String chogotankcondi;

	private String yobiyokaicondi;

	private String holdtankcondi;

	private java.math.BigDecimal totalJobtime;

	private java.math.BigDecimal enployJobtime;

	private java.math.BigDecimal cooperJobtime;

	private String appTantoCd;

	private String appTantoName;

	private java.sql.Timestamp appDate;

	private String lastAppTantoCd;

	private String lastAppTantoName;

	private java.sql.Timestamp lastAppDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	private java.math.BigDecimal sumSuuryou;

	private String location;

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
	 * directionDate取得.
	 * @return directionDate
	 */
	public java.sql.Timestamp getDirectionDate() {
		return this.directionDate;
	}

	/**
	 * directionDate設定.
	 * @param directionDate directionDate
	 */
	public void setDirectionDate(final java.sql.Timestamp directionDate) {
		this.directionDate = directionDate;
	}

	/**
	 * aspOrderNo取得.
	 * @return aspOrderNo
	 */
	public String getAspOrderNo() {
		return this.aspOrderNo;
	}

	/**
	 * aspOrderNo設定.
	 * @param aspOrderNo aspOrderNo
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * directionStatus取得.
	 * @return directionStatus
	 */
	public java.math.BigDecimal getDirectionStatus() {
		return this.directionStatus;
	}

	/**
	 * directionStatus設定.
	 * @param directionStatus directionStatus
	 */
	public void setDirectionStatus(final java.math.BigDecimal directionStatus) {
		this.directionStatus = directionStatus;
	}

	/**
	 * directionStatusName取得.
	 * @return directionStatusName
	 */
	public String getDirectionStatusName() {
		return this.directionStatusName;
	}

	/**
	 * directionStatusName設定.
	 * @param directionStatusName directionStatusName
	 */
	public void setDirectionStatusName(final String directionStatusName) {
		this.directionStatusName = directionStatusName;
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
	 * recipeCd取得.
	 * @return recipeCd
	 */
	public String getRecipeCd() {
		return this.recipeCd;
	}

	/**
	 * recipeCd設定.
	 * @param recipeCd recipeCd
	 */
	public void setRecipeCd(final String recipeCd) {
		this.recipeCd = recipeCd;
	}

	/**
	 * recipeVersion取得.
	 * @return recipeVersion
	 */
	public java.math.BigDecimal getRecipeVersion() {
		return this.recipeVersion;
	}

	/**
	 * recipeVersion設定.
	 * @param recipeVersion recipeVersion
	 */
	public void setRecipeVersion(final java.math.BigDecimal recipeVersion) {
		this.recipeVersion = recipeVersion;
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
	 * productionLineName取得.
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
	}

	/**
	 * compoundTankNo取得.
	 * @return compoundTankNo
	 */
	public String getCompoundTankNo() {
		return this.compoundTankNo;
	}

	/**
	 * compoundTankNo設定.
	 * @param compoundTankNo compoundTankNo
	 */
	public void setCompoundTankNo(final String compoundTankNo) {
		this.compoundTankNo = compoundTankNo;
	}

	/**
	 * compoundTankName取得.
	 * @return compoundTankName
	 */
	public String getCompoundTankName() {
		return this.compoundTankName;
	}

	/**
	 * compoundTankName設定.
	 * @param compoundTankName compoundTankName
	 */
	public void setCompoundTankName(final String compoundTankName) {
		this.compoundTankName = compoundTankName;
	}

	/**
	 * holdTankNo取得.
	 * @return holdTankNo
	 */
	public String getHoldTankNo() {
		return this.holdTankNo;
	}

	/**
	 * holdTankNo設定.
	 * @param holdTankNo holdTankNo
	 */
	public void setHoldTankNo(final String holdTankNo) {
		this.holdTankNo = holdTankNo;
	}

	/**
	 * holdTankName取得.
	 * @return holdTankName
	 */
	public String getHoldTankName() {
		return this.holdTankName;
	}

	/**
	 * holdTankName設定.
	 * @param holdTankName holdTankName
	 */
	public void setHoldTankName(final String holdTankName) {
		this.holdTankName = holdTankName;
	}

	/**
	 * dissolutionTankNo取得.
	 * @return dissolutionTankNo
	 */
	public String getDissolutionTankNo() {
		return this.dissolutionTankNo;
	}

	/**
	 * dissolutionTankNo設定.
	 * @param dissolutionTankNo dissolutionTankNo
	 */
	public void setDissolutionTankNo(final String dissolutionTankNo) {
		this.dissolutionTankNo = dissolutionTankNo;
	}

	/**
	 * dissolutionTankName取得.
	 * @return dissolutionTankName
	 */
	public String getDissolutionTankName() {
		return this.dissolutionTankName;
	}

	/**
	 * dissolutionTankName設定.
	 * @param dissolutionTankName dissolutionTankName
	 */
	public void setDissolutionTankName(final String dissolutionTankName) {
		this.dissolutionTankName = dissolutionTankName;
	}

	/**
	 * filltankNo取得.
	 * @return filltankNo
	 */
	public String getFilltankNo() {
		return this.filltankNo;
	}

	/**
	 * filltankNo設定.
	 * @param filltankNo filltankNo
	 */
	public void setFilltankNo(final String filltankNo) {
		this.filltankNo = filltankNo;
	}

	/**
	 * filltankName取得.
	 * @return filltankName
	 */
	public String getFilltankName() {
		return this.filltankName;
	}

	/**
	 * filltankName設定.
	 * @param filltankName filltankName
	 */
	public void setFilltankName(final String filltankName) {
		this.filltankName = filltankName;
	}

	/**
	 * packageLine取得.
	 * @return packageLine
	 */
	public String getPackageLine() {
		return this.packageLine;
	}

	/**
	 * packageLine設定.
	 * @param packageLine packageLine
	 */
	public void setPackageLine(final String packageLine) {
		this.packageLine = packageLine;
	}

	/**
	 * packageLineName取得.
	 * @return packageLineName
	 */
	public String getPackageLineName() {
		return this.packageLineName;
	}

	/**
	 * packageLineName設定.
	 * @param packageLineName packageLineName
	 */
	public void setPackageLineName(final String packageLineName) {
		this.packageLineName = packageLineName;
	}

	/**
	 * currentStepNo取得.
	 * @return currentStepNo
	 */
	public String getCurrentStepNo() {
		return this.currentStepNo;
	}

	/**
	 * currentStepNo設定.
	 * @param currentStepNo currentStepNo
	 */
	public void setCurrentStepNo(final String currentStepNo) {
		this.currentStepNo = currentStepNo;
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
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * styleOfPacking取得.
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * styleOfPacking設定.
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * name01取得.
	 * @return name01
	 */
	public String getName01() {
		return this.name01;
	}

	/**
	 * name01設定.
	 * @param name01 name01
	 */
	public void setName01(final String name01) {
		this.name01 = name01;
	}

	/**
	 * planedQty取得.
	 * @return planedQty
	 */
	public java.math.BigDecimal getPlanedQty() {
		return this.planedQty;
	}

	/**
	 * planedQty設定.
	 * @param planedQty planedQty
	 */
	public void setPlanedQty(final java.math.BigDecimal planedQty) {
		this.planedQty = planedQty;
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
	 * divideFlag取得.
	 * @return divideFlag
	 */
	public java.math.BigDecimal getDivideFlag() {
		return this.divideFlag;
	}

	/**
	 * divideFlag設定.
	 * @param divideFlag divideFlag
	 */
	public void setDivideFlag(final java.math.BigDecimal divideFlag) {
		this.divideFlag = divideFlag;
	}

	/**
	 * nextPlanedQty取得.
	 * @return nextPlanedQty
	 */
	public java.math.BigDecimal getNextPlanedQty() {
		return this.nextPlanedQty;
	}

	/**
	 * nextPlanedQty設定.
	 * @param nextPlanedQty nextPlanedQty
	 */
	public void setNextPlanedQty(final java.math.BigDecimal nextPlanedQty) {
		this.nextPlanedQty = nextPlanedQty;
	}

	/**
	 * pdwResult取得.
	 * @return pdwResult
	 */
	public java.math.BigDecimal getPdwResult() {
		return this.pdwResult;
	}

	/**
	 * pdwResult設定.
	 * @param pdwResult pdwResult
	 */
	public void setPdwResult(final java.math.BigDecimal pdwResult) {
		this.pdwResult = pdwResult;
	}

	/**
	 * peocessLoss取得.
	 * @return peocessLoss
	 */
	public java.math.BigDecimal getPeocessLoss() {
		return this.peocessLoss;
	}

	/**
	 * peocessLoss設定.
	 * @param peocessLoss peocessLoss
	 */
	public void setPeocessLoss(final java.math.BigDecimal peocessLoss) {
		this.peocessLoss = peocessLoss;
	}

	/**
	 * lotNo取得.
	 * @return lotNo
	 */
	public String getLotNo() {
		return this.lotNo;
	}

	/**
	 * lotNo設定.
	 * @param lotNo lotNo
	 */
	public void setLotNo(final String lotNo) {
		this.lotNo = lotNo;
	}

	/**
	 * planedSdate取得.
	 * @return planedSdate
	 */
	public java.sql.Timestamp getPlanedSdate() {
		return this.planedSdate;
	}

	/**
	 * planedSdate設定.
	 * @param planedSdate planedSdate
	 */
	public void setPlanedSdate(final java.sql.Timestamp planedSdate) {
		this.planedSdate = planedSdate;
	}

	/**
	 * planedEdate取得.
	 * @return planedEdate
	 */
	public java.sql.Timestamp getPlanedEdate() {
		return this.planedEdate;
	}

	/**
	 * planedEdate設定.
	 * @param planedEdate planedEdate
	 */
	public void setPlanedEdate(final java.sql.Timestamp planedEdate) {
		this.planedEdate = planedEdate;
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
	 * steritSdate取得.
	 * @return steritSdate
	 */
	public java.sql.Timestamp getSteritSdate() {
		return this.steritSdate;
	}

	/**
	 * steritSdate設定.
	 * @param steritSdate steritSdate
	 */
	public void setSteritSdate(final java.sql.Timestamp steritSdate) {
		this.steritSdate = steritSdate;
	}

	/**
	 * steritEdate取得.
	 * @return steritEdate
	 */
	public java.sql.Timestamp getSteritEdate() {
		return this.steritEdate;
	}

	/**
	 * steritEdate設定.
	 * @param steritEdate steritEdate
	 */
	public void setSteritEdate(final java.sql.Timestamp steritEdate) {
		this.steritEdate = steritEdate;
	}

	/**
	 * mekkinTankTempMin取得.
	 * @return mekkinTankTempMin
	 */
	public java.math.BigDecimal getMekkinTankTempMin() {
		return this.mekkinTankTempMin;
	}

	/**
	 * mekkinTankTempMin設定.
	 * @param mekkinTankTempMin mekkinTankTempMin
	 */
	public void setMekkinTankTempMin(final java.math.BigDecimal mekkinTankTempMin) {
		this.mekkinTankTempMin = mekkinTankTempMin;
	}

	/**
	 * mekkinTankTempMax取得.
	 * @return mekkinTankTempMax
	 */
	public java.math.BigDecimal getMekkinTankTempMax() {
		return this.mekkinTankTempMax;
	}

	/**
	 * mekkinTankTempMax設定.
	 * @param mekkinTankTempMax mekkinTankTempMax
	 */
	public void setMekkinTankTempMax(final java.math.BigDecimal mekkinTankTempMax) {
		this.mekkinTankTempMax = mekkinTankTempMax;
	}

	/**
	 * haisuiCheck取得.
	 * @return haisuiCheck
	 */
	public String getHaisuiCheck() {
		return this.haisuiCheck;
	}

	/**
	 * haisuiCheck設定.
	 * @param haisuiCheck haisuiCheck
	 */
	public void setHaisuiCheck(final String haisuiCheck) {
		this.haisuiCheck = haisuiCheck;
	}

	/**
	 * stampFlag取得.
	 * @return stampFlag
	 */
	public java.math.BigDecimal getStampFlag() {
		return this.stampFlag;
	}

	/**
	 * stampFlag設定.
	 * @param stampFlag stampFlag
	 */
	public void setStampFlag(final java.math.BigDecimal stampFlag) {
		this.stampFlag = stampFlag;
	}

	/**
	 * stampDate取得.
	 * @return stampDate
	 */
	public java.sql.Timestamp getStampDate() {
		return this.stampDate;
	}

	/**
	 * stampDate設定.
	 * @param stampDate stampDate
	 */
	public void setStampDate(final java.sql.Timestamp stampDate) {
		this.stampDate = stampDate;
	}

	/**
	 * stampTantoCd取得.
	 * @return stampTantoCd
	 */
	public String getStampTantoCd() {
		return this.stampTantoCd;
	}

	/**
	 * stampTantoCd設定.
	 * @param stampTantoCd stampTantoCd
	 */
	public void setStampTantoCd(final String stampTantoCd) {
		this.stampTantoCd = stampTantoCd;
	}

	/**
	 * stampTantoName取得.
	 * @return stampTantoName
	 */
	public String getStampTantoName() {
		return this.stampTantoName;
	}

	/**
	 * stampTantoName設定.
	 * @param stampTantoName stampTantoName
	 */
	public void setStampTantoName(final String stampTantoName) {
		this.stampTantoName = stampTantoName;
	}

	/**
	 * productLabelFlag取得.
	 * @return productLabelFlag
	 */
	public java.math.BigDecimal getProductLabelFlag() {
		return this.productLabelFlag;
	}

	/**
	 * productLabelFlag設定.
	 * @param productLabelFlag productLabelFlag
	 */
	public void setProductLabelFlag(final java.math.BigDecimal productLabelFlag) {
		this.productLabelFlag = productLabelFlag;
	}

	/**
	 * productLabelDate取得.
	 * @return productLabelDate
	 */
	public java.sql.Timestamp getProductLabelDate() {
		return this.productLabelDate;
	}

	/**
	 * productLabelDate設定.
	 * @param productLabelDate productLabelDate
	 */
	public void setProductLabelDate(final java.sql.Timestamp productLabelDate) {
		this.productLabelDate = productLabelDate;
	}

	/**
	 * productLabelTantoCd取得.
	 * @return productLabelTantoCd
	 */
	public String getProductLabelTantoCd() {
		return this.productLabelTantoCd;
	}

	/**
	 * productLabelTantoCd設定.
	 * @param productLabelTantoCd productLabelTantoCd
	 */
	public void setProductLabelTantoCd(final String productLabelTantoCd) {
		this.productLabelTantoCd = productLabelTantoCd;
	}

	/**
	 * productLabelTantoName取得.
	 * @return productLabelTantoName
	 */
	public String getProductLabelTantoName() {
		return this.productLabelTantoName;
	}

	/**
	 * productLabelTantoName設定.
	 * @param productLabelTantoName productLabelTantoName
	 */
	public void setProductLabelTantoName(final String productLabelTantoName) {
		this.productLabelTantoName = productLabelTantoName;
	}

	/**
	 * productRecordFlag取得.
	 * @return productRecordFlag
	 */
	public java.math.BigDecimal getProductRecordFlag() {
		return this.productRecordFlag;
	}

	/**
	 * productRecordFlag設定.
	 * @param productRecordFlag productRecordFlag
	 */
	public void setProductRecordFlag(final java.math.BigDecimal productRecordFlag) {
		this.productRecordFlag = productRecordFlag;
	}

	/**
	 * productRecordDate取得.
	 * @return productRecordDate
	 */
	public java.sql.Timestamp getProductRecordDate() {
		return this.productRecordDate;
	}

	/**
	 * productRecordDate設定.
	 * @param productRecordDate productRecordDate
	 */
	public void setProductRecordDate(final java.sql.Timestamp productRecordDate) {
		this.productRecordDate = productRecordDate;
	}

	/**
	 * productRecordTantoCd取得.
	 * @return productRecordTantoCd
	 */
	public String getProductRecordTantoCd() {
		return this.productRecordTantoCd;
	}

	/**
	 * productRecordTantoCd設定.
	 * @param productRecordTantoCd productRecordTantoCd
	 */
	public void setProductRecordTantoCd(final String productRecordTantoCd) {
		this.productRecordTantoCd = productRecordTantoCd;
	}

	/**
	 * productRecordTantoName取得.
	 * @return productRecordTantoName
	 */
	public String getProductRecordTantoName() {
		return this.productRecordTantoName;
	}

	/**
	 * productRecordTantoName設定.
	 * @param productRecordTantoName productRecordTantoName
	 */
	public void setProductRecordTantoName(final String productRecordTantoName) {
		this.productRecordTantoName = productRecordTantoName;
	}

	/**
	 * stockdissLabelFlag取得.
	 * @return stockdissLabelFlag
	 */
	public java.math.BigDecimal getStockdissLabelFlag() {
		return this.stockdissLabelFlag;
	}

	/**
	 * stockdissLabelFlag設定.
	 * @param stockdissLabelFlag stockdissLabelFlag
	 */
	public void setStockdissLabelFlag(final java.math.BigDecimal stockdissLabelFlag) {
		this.stockdissLabelFlag = stockdissLabelFlag;
	}

	/**
	 * stockdissLabelDate取得.
	 * @return stockdissLabelDate
	 */
	public java.sql.Timestamp getStockdissLabelDate() {
		return this.stockdissLabelDate;
	}

	/**
	 * stockdissLabelDate設定.
	 * @param stockdissLabelDate stockdissLabelDate
	 */
	public void setStockdissLabelDate(final java.sql.Timestamp stockdissLabelDate) {
		this.stockdissLabelDate = stockdissLabelDate;
	}

	/**
	 * stockdissLabelTantoCd取得.
	 * @return stockdissLabelTantoCd
	 */
	public String getStockdissLabelTantoCd() {
		return this.stockdissLabelTantoCd;
	}

	/**
	 * stockdissLabelTantoCd設定.
	 * @param stockdissLabelTantoCd stockdissLabelTantoCd
	 */
	public void setStockdissLabelTantoCd(final String stockdissLabelTantoCd) {
		this.stockdissLabelTantoCd = stockdissLabelTantoCd;
	}

	/**
	 * stockdissLabelTantoName取得.
	 * @return stockdissLabelTantoName
	 */
	public String getStockdissLabelTantoName() {
		return this.stockdissLabelTantoName;
	}

	/**
	 * stockdissLabelTantoName設定.
	 * @param stockdissLabelTantoName stockdissLabelTantoName
	 */
	public void setStockdissLabelTantoName(final String stockdissLabelTantoName) {
		this.stockdissLabelTantoName = stockdissLabelTantoName;
	}

	/**
	 * certificationFlag取得.
	 * @return certificationFlag
	 */
	public java.math.BigDecimal getCertificationFlag() {
		return this.certificationFlag;
	}

	/**
	 * certificationFlag設定.
	 * @param certificationFlag certificationFlag
	 */
	public void setCertificationFlag(final java.math.BigDecimal certificationFlag) {
		this.certificationFlag = certificationFlag;
	}

	/**
	 * certificationDate取得.
	 * @return certificationDate
	 */
	public java.sql.Timestamp getCertificationDate() {
		return this.certificationDate;
	}

	/**
	 * certificationDate設定.
	 * @param certificationDate certificationDate
	 */
	public void setCertificationDate(final java.sql.Timestamp certificationDate) {
		this.certificationDate = certificationDate;
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
	 * deleteFlag取得.
	 * @return deleteFlag
	 */
	public java.math.BigDecimal getDeleteFlag() {
		return this.deleteFlag;
	}

	/**
	 * deleteFlag設定.
	 * @param deleteFlag deleteFlag
	 */
	public void setDeleteFlag(final java.math.BigDecimal deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * seizotantocode取得.
	 * @return seizotantocode
	 */
	public String getSeizotantocode() {
		return this.seizotantocode;
	}

	/**
	 * seizotantocode設定.
	 * @param seizotantocode seizotantocode
	 */
	public void setSeizotantocode(final String seizotantocode) {
		this.seizotantocode = seizotantocode;
	}

	/**
	 * seizotantoName取得.
	 * @return seizotantoName
	 */
	public String getSeizotantoName() {
		return this.seizotantoName;
	}

	/**
	 * seizotantoName設定.
	 * @param seizotantoName seizotantoName
	 */
	public void setSeizotantoName(final String seizotantoName) {
		this.seizotantoName = seizotantoName;
	}

	/**
	 * senjotantocode取得.
	 * @return senjotantocode
	 */
	public String getSenjotantocode() {
		return this.senjotantocode;
	}

	/**
	 * senjotantocode設定.
	 * @param senjotantocode senjotantocode
	 */
	public void setSenjotantocode(final String senjotantocode) {
		this.senjotantocode = senjotantocode;
	}

	/**
	 * senjotantoName取得.
	 * @return senjotantoName
	 */
	public String getSenjotantoName() {
		return this.senjotantoName;
	}

	/**
	 * senjotantoName設定.
	 * @param senjotantoName senjotantoName
	 */
	public void setSenjotantoName(final String senjotantoName) {
		this.senjotantoName = senjotantoName;
	}

	/**
	 * mekkintantocode取得.
	 * @return mekkintantocode
	 */
	public String getMekkintantocode() {
		return this.mekkintantocode;
	}

	/**
	 * mekkintantocode設定.
	 * @param mekkintantocode mekkintantocode
	 */
	public void setMekkintantocode(final String mekkintantocode) {
		this.mekkintantocode = mekkintantocode;
	}

	/**
	 * mekkintantoName取得.
	 * @return mekkintantoName
	 */
	public String getMekkintantoName() {
		return this.mekkintantoName;
	}

	/**
	 * mekkintantoName設定.
	 * @param mekkintantoName mekkintantoName
	 */
	public void setMekkintantoName(final String mekkintantoName) {
		this.mekkintantoName = mekkintantoName;
	}

	/**
	 * chogotankcondi取得.
	 * @return chogotankcondi
	 */
	public String getChogotankcondi() {
		return this.chogotankcondi;
	}

	/**
	 * chogotankcondi設定.
	 * @param chogotankcondi chogotankcondi
	 */
	public void setChogotankcondi(final String chogotankcondi) {
		this.chogotankcondi = chogotankcondi;
	}

	/**
	 * yobiyokaicondi取得.
	 * @return yobiyokaicondi
	 */
	public String getYobiyokaicondi() {
		return this.yobiyokaicondi;
	}

	/**
	 * yobiyokaicondi設定.
	 * @param yobiyokaicondi yobiyokaicondi
	 */
	public void setYobiyokaicondi(final String yobiyokaicondi) {
		this.yobiyokaicondi = yobiyokaicondi;
	}

	/**
	 * holdtankcondi取得.
	 * @return holdtankcondi
	 */
	public String getHoldtankcondi() {
		return this.holdtankcondi;
	}

	/**
	 * holdtankcondi設定.
	 * @param holdtankcondi holdtankcondi
	 */
	public void setHoldtankcondi(final String holdtankcondi) {
		this.holdtankcondi = holdtankcondi;
	}

	/**
	 * totalJobtime取得.
	 * @return totalJobtime
	 */
	public java.math.BigDecimal getTotalJobtime() {
		return this.totalJobtime;
	}

	/**
	 * totalJobtime設定.
	 * @param totalJobtime totalJobtime
	 */
	public void setTotalJobtime(final java.math.BigDecimal totalJobtime) {
		this.totalJobtime = totalJobtime;
	}

	/**
	 * enployJobtime取得.
	 * @return enployJobtime
	 */
	public java.math.BigDecimal getEnployJobtime() {
		return this.enployJobtime;
	}

	/**
	 * enployJobtime設定.
	 * @param enployJobtime enployJobtime
	 */
	public void setEnployJobtime(final java.math.BigDecimal enployJobtime) {
		this.enployJobtime = enployJobtime;
	}

	/**
	 * cooperJobtime取得.
	 * @return cooperJobtime
	 */
	public java.math.BigDecimal getCooperJobtime() {
		return this.cooperJobtime;
	}

	/**
	 * cooperJobtime設定.
	 * @param cooperJobtime cooperJobtime
	 */
	public void setCooperJobtime(final java.math.BigDecimal cooperJobtime) {
		this.cooperJobtime = cooperJobtime;
	}

	/**
	 * appTantoCd取得.
	 * @return appTantoCd
	 */
	public String getAppTantoCd() {
		return this.appTantoCd;
	}

	/**
	 * appTantoCd設定.
	 * @param appTantoCd appTantoCd
	 */
	public void setAppTantoCd(final String appTantoCd) {
		this.appTantoCd = appTantoCd;
	}

	/**
	 * appTantoName取得.
	 * @return appTantoName
	 */
	public String getAppTantoName() {
		return this.appTantoName;
	}

	/**
	 * appTantoName設定.
	 * @param appTantoName appTantoName
	 */
	public void setAppTantoName(final String appTantoName) {
		this.appTantoName = appTantoName;
	}

	/**
	 * appDate取得.
	 * @return appDate
	 */
	public java.sql.Timestamp getAppDate() {
		return this.appDate;
	}

	/**
	 * appDate設定.
	 * @param appDate appDate
	 */
	public void setAppDate(final java.sql.Timestamp appDate) {
		this.appDate = appDate;
	}

	/**
	 * lastAppTantoCd取得.
	 * @return lastAppTantoCd
	 */
	public String getLastAppTantoCd() {
		return this.lastAppTantoCd;
	}

	/**
	 * lastAppTantoCd設定.
	 * @param lastAppTantoCd lastAppTantoCd
	 */
	public void setLastAppTantoCd(final String lastAppTantoCd) {
		this.lastAppTantoCd = lastAppTantoCd;
	}

	/**
	 * lastAppTantoName取得.
	 * @return lastAppTantoName
	 */
	public String getLastAppTantoName() {
		return this.lastAppTantoName;
	}

	/**
	 * lastAppTantoName設定.
	 * @param lastAppTantoName lastAppTantoName
	 */
	public void setLastAppTantoName(final String lastAppTantoName) {
		this.lastAppTantoName = lastAppTantoName;
	}

	/**
	 * lastAppDate取得.
	 * @return lastAppDate
	 */
	public java.sql.Timestamp getLastAppDate() {
		return this.lastAppDate;
	}

	/**
	 * lastAppDate設定.
	 * @param lastAppDate lastAppDate
	 */
	public void setLastAppDate(final java.sql.Timestamp lastAppDate) {
		this.lastAppDate = lastAppDate;
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
	 * sumSuuryou取得.
	 * @return sumSuuryou
	 */
	public java.math.BigDecimal getSumSuuryou() {
		return this.sumSuuryou;
	}

	/**
	 * sumSuuryou設定.
	 * @param sumSuuryou sumSuuryou
	 */
	public void setSumSuuryou(final java.math.BigDecimal sumSuuryou) {
		this.sumSuuryou = sumSuuryou;
	}

	/**
	 * location取得.
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * location設定.
	 * @param location location
	 */
	public void setLocation(final String location) {
		this.location = location;
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

