/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Thu May 07 14:49:29 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.recipeheader;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RecipeHeaderBaseクラス.
 * @author t0011036
 */
public class RecipeHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RecipeHeaderBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "RECIPE_HEADER";


//	/** IDアノテーション recipeId. */
//	public static final String recipeId_ID = "assigned";

	/** COLUMNアノテーション recipeId. */
	public static final String recipeId_COLUMN = "RECIPE_ID";

	/** COLUMNアノテーション recipeCd. */
	public static final String recipeCd_COLUMN = "RECIPE_CD";

	/** COLUMNアノテーション recipeVersion. */
	public static final String recipeVersion_COLUMN = "RECIPE_VERSION";

	/** COLUMNアノテーション recipeType. */
	public static final String recipeType_COLUMN = "RECIPE_TYPE";

	/** COLUMNアノテーション recipeDescription. */
	public static final String recipeDescription_COLUMN = "RECIPE_DESCRIPTION";

	/** COLUMNアノテーション recipeMemo. */
	public static final String recipeMemo_COLUMN = "RECIPE_MEMO";

	/** COLUMNアノテーション recipeStatus. */
	public static final String recipeStatus_COLUMN = "RECIPE_STATUS";

	/** COLUMNアノテーション recipeUse. */
	public static final String recipeUse_COLUMN = "RECIPE_USE";

	/** COLUMNアノテーション recipePriority. */
	public static final String recipePriority_COLUMN = "RECIPE_PRIORITY";

	/** COLUMNアノテーション originalRecipeId. */
	public static final String originalRecipeId_COLUMN = "ORIGINAL_RECIPE_ID";

	/** COLUMNアノテーション productionLine. */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション minQty. */
	public static final String minQty_COLUMN = "MIN_QTY";

	/** COLUMNアノテーション maxQty. */
	public static final String maxQty_COLUMN = "MAX_QTY";

	/** COLUMNアノテーション stdQty. */
	public static final String stdQty_COLUMN = "STD_QTY";

	/** COLUMNアノテーション unitQty. */
	public static final String unitQty_COLUMN = "UNIT_QTY";

	/** COLUMNアノテーション cost. */
	public static final String cost_COLUMN = "COST";

	/** COLUMNアノテーション processLoss. */
	public static final String processLoss_COLUMN = "PROCESS_LOSS";

	/** COLUMNアノテーション startDate. */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate. */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション deleteFlag. */
	public static final String deleteFlag_COLUMN = "DELETE_FLAG";

	/** COLUMNアノテーション printFlag. */
	public static final String printFlag_COLUMN = "PRINT_FLAG";

	/** COLUMNアノテーション printDate. */
	public static final String printDate_COLUMN = "PRINT_DATE";

	/** COLUMNアノテーション printTantoCd. */
	public static final String printTantoCd_COLUMN = "PRINT_TANTO_CD";

	/** COLUMNアノテーション approvalStatus. */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション appTantoCd. */
	public static final String appTantoCd_COLUMN = "APP_TANTO_CD";

	/** COLUMNアノテーション appDate. */
	public static final String appDate_COLUMN = "APP_DATE";

	/** COLUMNアノテーション lastAppTantoCd. */
	public static final String lastAppTantoCd_COLUMN = "LAST_APP_TANTO_CD";

	/** COLUMNアノテーション lastAppDate. */
	public static final String lastAppDate_COLUMN = "LAST_APP_DATE";

	/** COLUMNアノテーション recipeName. */
	public static final String recipeName_COLUMN = "RECIPE_NAME";

	/** COLUMNアノテーション sectionCd. */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal recipeId;

	private String recipeCd;

	private java.math.BigDecimal recipeVersion;

	private java.math.BigDecimal recipeType;

	private String recipeDescription;

	private String recipeMemo;

	private java.math.BigDecimal recipeStatus;

	private java.math.BigDecimal recipeUse;

	private java.math.BigDecimal recipePriority;

	private java.math.BigDecimal originalRecipeId;

	private String productionLine;

	private String itemCd;

	private java.math.BigDecimal minQty;

	private java.math.BigDecimal maxQty;

	private java.math.BigDecimal stdQty;

	private java.math.BigDecimal unitQty;

	private java.math.BigDecimal cost;

	private java.math.BigDecimal processLoss;

	private java.sql.Timestamp startDate;

	private java.sql.Timestamp endDate;

	private java.math.BigDecimal deleteFlag;

	private java.math.BigDecimal printFlag;

	private java.sql.Timestamp printDate;

	private String printTantoCd;

	private java.math.BigDecimal approvalStatus;

	private String appTantoCd;

	private java.sql.Timestamp appDate;

	private String lastAppTantoCd;

	private java.sql.Timestamp lastAppDate;

	private String recipeName;

	private String sectionCd;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	private String updatorCd;

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
	 * recipeType取得.
	 * @return recipeType
	 */
	public java.math.BigDecimal getRecipeType() {
		return this.recipeType;
	}

	/**
	 * recipeType設定.
	 * @param recipeType recipeType
	 */
	public void setRecipeType(final java.math.BigDecimal recipeType) {
		this.recipeType = recipeType;
	}

	/**
	 * recipeDescription取得.
	 * @return recipeDescription
	 */
	public String getRecipeDescription() {
		return this.recipeDescription;
	}

	/**
	 * recipeDescription設定.
	 * @param recipeDescription recipeDescription
	 */
	public void setRecipeDescription(final String recipeDescription) {
		this.recipeDescription = recipeDescription;
	}

	/**
	 * recipeMemo取得.
	 * @return recipeMemo
	 */
	public String getRecipeMemo() {
		return this.recipeMemo;
	}

	/**
	 * recipeMemo設定.
	 * @param recipeMemo recipeMemo
	 */
	public void setRecipeMemo(final String recipeMemo) {
		this.recipeMemo = recipeMemo;
	}

	/**
	 * recipeStatus取得.
	 * @return recipeStatus
	 */
	public java.math.BigDecimal getRecipeStatus() {
		return this.recipeStatus;
	}

	/**
	 * recipeStatus設定.
	 * @param recipeStatus recipeStatus
	 */
	public void setRecipeStatus(final java.math.BigDecimal recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	/**
	 * recipeUse取得.
	 * @return recipeUse
	 */
	public java.math.BigDecimal getRecipeUse() {
		return this.recipeUse;
	}

	/**
	 * recipeUse設定.
	 * @param recipeUse recipeUse
	 */
	public void setRecipeUse(final java.math.BigDecimal recipeUse) {
		this.recipeUse = recipeUse;
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
	 * originalRecipeId取得.
	 * @return originalRecipeId
	 */
	public java.math.BigDecimal getOriginalRecipeId() {
		return this.originalRecipeId;
	}

	/**
	 * originalRecipeId設定.
	 * @param originalRecipeId originalRecipeId
	 */
	public void setOriginalRecipeId(final java.math.BigDecimal originalRecipeId) {
		this.originalRecipeId = originalRecipeId;
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
	 * minQty取得.
	 * @return minQty
	 */
	public java.math.BigDecimal getMinQty() {
		return this.minQty;
	}

	/**
	 * minQty設定.
	 * @param minQty minQty
	 */
	public void setMinQty(final java.math.BigDecimal minQty) {
		this.minQty = minQty;
	}

	/**
	 * maxQty取得.
	 * @return maxQty
	 */
	public java.math.BigDecimal getMaxQty() {
		return this.maxQty;
	}

	/**
	 * maxQty設定.
	 * @param maxQty maxQty
	 */
	public void setMaxQty(final java.math.BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	/**
	 * stdQty取得.
	 * @return stdQty
	 */
	public java.math.BigDecimal getStdQty() {
		return this.stdQty;
	}

	/**
	 * stdQty設定.
	 * @param stdQty stdQty
	 */
	public void setStdQty(final java.math.BigDecimal stdQty) {
		this.stdQty = stdQty;
	}

	/**
	 * unitQty取得.
	 * @return unitQty
	 */
	public java.math.BigDecimal getUnitQty() {
		return this.unitQty;
	}

	/**
	 * unitQty設定.
	 * @param unitQty unitQty
	 */
	public void setUnitQty(final java.math.BigDecimal unitQty) {
		this.unitQty = unitQty;
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
	 * processLoss取得.
	 * @return processLoss
	 */
	public java.math.BigDecimal getProcessLoss() {
		return this.processLoss;
	}

	/**
	 * processLoss設定.
	 * @param processLoss processLoss
	 */
	public void setProcessLoss(final java.math.BigDecimal processLoss) {
		this.processLoss = processLoss;
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
	 * printFlag取得.
	 * @return printFlag
	 */
	public java.math.BigDecimal getPrintFlag() {
		return this.printFlag;
	}

	/**
	 * printFlag設定.
	 * @param printFlag printFlag
	 */
	public void setPrintFlag(final java.math.BigDecimal printFlag) {
		this.printFlag = printFlag;
	}

	/**
	 * printDate取得.
	 * @return printDate
	 */
	public java.sql.Timestamp getPrintDate() {
		return this.printDate;
	}

	/**
	 * printDate設定.
	 * @param printDate printDate
	 */
	public void setPrintDate(final java.sql.Timestamp printDate) {
		this.printDate = printDate;
	}

	/**
	 * printTantoCd取得.
	 * @return printTantoCd
	 */
	public String getPrintTantoCd() {
		return this.printTantoCd;
	}

	/**
	 * printTantoCd設定.
	 * @param printTantoCd printTantoCd
	 */
	public void setPrintTantoCd(final String printTantoCd) {
		this.printTantoCd = printTantoCd;
	}

	/**
	 * approvalStatus取得.
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * approvalStatus設定.
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
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
	 * recipeName取得.
	 * @return recipeName
	 */
	public String getRecipeName() {
		return this.recipeName;
	}

	/**
	 * recipeName設定.
	 * @param recipeName recipeName
	 */
	public void setRecipeName(final String recipeName) {
		this.recipeName = recipeName;
	}

	/**
	 * sectionCd取得.
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return this.sectionCd;
	}

	/**
	 * sectionCd設定.
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
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
