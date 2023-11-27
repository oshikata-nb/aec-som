/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Tue Apr 27 11:15:47 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.productattributequeue;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ProductAttributeQueueBaseクラス.
 * @author t0011036
 */
public class ProductAttributeQueueBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductAttributeQueueBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PRODUCT_ATTRIBUTE_QUEUE";


//	/** IDアノテーション itemCd. */
//	public static final String itemCd_ID = "assigned";
//	/** IDアノテーション version. */
//	public static final String version_ID = "assigned";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション version. */
	public static final String version_COLUMN = "VERSION";

	/** COLUMNアノテーション status. */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション dailyProduction. */
	public static final String dailyProduction_COLUMN = "DAILY_PRODUCTION";

	/** COLUMNアノテーション serverDivision. */
	public static final String serverDivision_COLUMN = "SERVER_DIVISION";

	/** COLUMNアノテーション productionCycle. */
	public static final String productionCycle_COLUMN = "PRODUCTION_CYCLE";

	/** COLUMNアノテーション enphasisDivision. */
	public static final String enphasisDivision_COLUMN = "ENPHASIS_DIVISION";

	/** COLUMNアノテーション cockDivision. */
	public static final String cockDivision_COLUMN = "COCK_DIVISION";

	/** COLUMNアノテーション planFlg. */
	public static final String planFlg_COLUMN = "PLAN_FLG";

	/** COLUMNアノテーション serialnoFlg. */
	public static final String serialnoFlg_COLUMN = "SERIALNO_FLG";

	/** COLUMNアノテーション inspectionCategory. */
	public static final String inspectionCategory_COLUMN = "INSPECTION_CATEGORY";

	/** COLUMNアノテーション productionLeadTime. */
	public static final String productionLeadTime_COLUMN = "PRODUCTION_LEAD_TIME";

	/** COLUMNアノテーション safetyLeadTime. */
	public static final String safetyLeadTime_COLUMN = "SAFETY_LEAD_TIME";

	/** COLUMNアノテーション productionCategory. */
	public static final String productionCategory_COLUMN = "PRODUCTION_CATEGORY";

	/** COLUMNアノテーション orderPattern. */
	public static final String orderPattern_COLUMN = "ORDER_PATTERN";

	/** COLUMNアノテーション productionLine. */
	public static final String productionLine_COLUMN = "PRODUCTION_LINE";

	/** COLUMNアノテーション inspectionDays. */
	public static final String inspectionDays_COLUMN = "INSPECTION_DAYS";

	/** COLUMNアノテーション sectionCd. */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション planDivision. */
	public static final String planDivision_COLUMN = "PLAN_DIVISION";

	/** COLUMNアノテーション orderPoint. */
	public static final String orderPoint_COLUMN = "ORDER_POINT";

	/** COLUMNアノテーション cost. */
	public static final String cost_COLUMN = "COST";

	//
	// インスタンスフィールド
	//

	private String itemCd;

	private java.math.BigDecimal version;

	private java.math.BigDecimal status;

	private java.math.BigDecimal dailyProduction;

	private java.math.BigDecimal serverDivision;

	private java.math.BigDecimal productionCycle;

	private java.math.BigDecimal enphasisDivision;

	private java.math.BigDecimal cockDivision;

	private java.math.BigDecimal planFlg;

	private java.math.BigDecimal serialnoFlg;

	private String inspectionCategory;

	private java.math.BigDecimal productionLeadTime;

	private java.math.BigDecimal safetyLeadTime;

	private String productionCategory;

	private java.math.BigDecimal orderPattern;

	private String productionLine;

	private java.math.BigDecimal inspectionDays;

	private String sectionCd;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal planDivision;

	private java.math.BigDecimal orderPoint;

	private java.math.BigDecimal cost;

	//
	// インスタンスメソッド
	//

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
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
	}

	/**
	 * dailyProduction取得.
	 * @return dailyProduction
	 */
	public java.math.BigDecimal getDailyProduction() {
		return this.dailyProduction;
	}

	/**
	 * dailyProduction設定.
	 * @param dailyProduction dailyProduction
	 */
	public void setDailyProduction(final java.math.BigDecimal dailyProduction) {
		this.dailyProduction = dailyProduction;
	}

	/**
	 * serverDivision取得.
	 * @return serverDivision
	 */
	public java.math.BigDecimal getServerDivision() {
		return this.serverDivision;
	}

	/**
	 * serverDivision設定.
	 * @param serverDivision serverDivision
	 */
	public void setServerDivision(final java.math.BigDecimal serverDivision) {
		this.serverDivision = serverDivision;
	}

	/**
	 * productionCycle取得.
	 * @return productionCycle
	 */
	public java.math.BigDecimal getProductionCycle() {
		return this.productionCycle;
	}

	/**
	 * productionCycle設定.
	 * @param productionCycle productionCycle
	 */
	public void setProductionCycle(final java.math.BigDecimal productionCycle) {
		this.productionCycle = productionCycle;
	}

	/**
	 * enphasisDivision取得.
	 * @return enphasisDivision
	 */
	public java.math.BigDecimal getEnphasisDivision() {
		return this.enphasisDivision;
	}

	/**
	 * enphasisDivision設定.
	 * @param enphasisDivision enphasisDivision
	 */
	public void setEnphasisDivision(final java.math.BigDecimal enphasisDivision) {
		this.enphasisDivision = enphasisDivision;
	}

	/**
	 * cockDivision取得.
	 * @return cockDivision
	 */
	public java.math.BigDecimal getCockDivision() {
		return this.cockDivision;
	}

	/**
	 * cockDivision設定.
	 * @param cockDivision cockDivision
	 */
	public void setCockDivision(final java.math.BigDecimal cockDivision) {
		this.cockDivision = cockDivision;
	}

	/**
	 * planFlg取得.
	 * @return planFlg
	 */
	public java.math.BigDecimal getPlanFlg() {
		return this.planFlg;
	}

	/**
	 * planFlg設定.
	 * @param planFlg planFlg
	 */
	public void setPlanFlg(final java.math.BigDecimal planFlg) {
		this.planFlg = planFlg;
	}

	/**
	 * serialnoFlg取得.
	 * @return serialnoFlg
	 */
	public java.math.BigDecimal getSerialnoFlg() {
		return this.serialnoFlg;
	}

	/**
	 * serialnoFlg設定.
	 * @param serialnoFlg serialnoFlg
	 */
	public void setSerialnoFlg(final java.math.BigDecimal serialnoFlg) {
		this.serialnoFlg = serialnoFlg;
	}

	/**
	 * inspectionCategory取得.
	 * @return inspectionCategory
	 */
	public String getInspectionCategory() {
		return this.inspectionCategory;
	}

	/**
	 * inspectionCategory設定.
	 * @param inspectionCategory inspectionCategory
	 */
	public void setInspectionCategory(final String inspectionCategory) {
		this.inspectionCategory = inspectionCategory;
	}

	/**
	 * productionLeadTime取得.
	 * @return productionLeadTime
	 */
	public java.math.BigDecimal getProductionLeadTime() {
		return this.productionLeadTime;
	}

	/**
	 * productionLeadTime設定.
	 * @param productionLeadTime productionLeadTime
	 */
	public void setProductionLeadTime(final java.math.BigDecimal productionLeadTime) {
		this.productionLeadTime = productionLeadTime;
	}

	/**
	 * safetyLeadTime取得.
	 * @return safetyLeadTime
	 */
	public java.math.BigDecimal getSafetyLeadTime() {
		return this.safetyLeadTime;
	}

	/**
	 * safetyLeadTime設定.
	 * @param safetyLeadTime safetyLeadTime
	 */
	public void setSafetyLeadTime(final java.math.BigDecimal safetyLeadTime) {
		this.safetyLeadTime = safetyLeadTime;
	}

	/**
	 * productionCategory取得.
	 * @return productionCategory
	 */
	public String getProductionCategory() {
		return this.productionCategory;
	}

	/**
	 * productionCategory設定.
	 * @param productionCategory productionCategory
	 */
	public void setProductionCategory(final String productionCategory) {
		this.productionCategory = productionCategory;
	}

	/**
	 * orderPattern取得.
	 * @return orderPattern
	 */
	public java.math.BigDecimal getOrderPattern() {
		return this.orderPattern;
	}

	/**
	 * orderPattern設定.
	 * @param orderPattern orderPattern
	 */
	public void setOrderPattern(final java.math.BigDecimal orderPattern) {
		this.orderPattern = orderPattern;
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
	 * inspectionDays取得.
	 * @return inspectionDays
	 */
	public java.math.BigDecimal getInspectionDays() {
		return this.inspectionDays;
	}

	/**
	 * inspectionDays設定.
	 * @param inspectionDays inspectionDays
	 */
	public void setInspectionDays(final java.math.BigDecimal inspectionDays) {
		this.inspectionDays = inspectionDays;
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
	 * planDivision取得.
	 * @return planDivision
	 */
	public java.math.BigDecimal getPlanDivision() {
		return this.planDivision;
	}

	/**
	 * planDivision設定.
	 * @param planDivision planDivision
	 */
	public void setPlanDivision(final java.math.BigDecimal planDivision) {
		this.planDivision = planDivision;
	}

	/**
	 * orderPoint取得.
	 * @return orderPoint
	 */
	public java.math.BigDecimal getOrderPoint() {
		return this.orderPoint;
	}

	/**
	 * orderPoint設定.
	 * @param orderPoint orderPoint
	 */
	public void setOrderPoint(final java.math.BigDecimal orderPoint) {
		this.orderPoint = orderPoint;
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
