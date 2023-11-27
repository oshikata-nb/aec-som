/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Thu Mar 12 19:38:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lotinventory;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * LotInventoryBaseクラス.
 * @author t0011036
 */
public class LotInventoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LotInventoryBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "LOT_INVENTORY";


//	/** IDアノテーション itemCd. */
//	public static final String itemCd_ID = "assigned";
//	/** IDアノテーション locationCd. */
//	public static final String locationCd_ID = "assigned";
//	/** IDアノテーション lotNo. */
//	public static final String lotNo_ID = "assigned";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション parentItemCd. */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション inventoryQty. */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション backorderQty. */
	public static final String backorderQty_COLUMN = "BACKORDER_QTY";

	/** COLUMNアノテーション assignQty. */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	/** COLUMNアノテーション salesAssignQty. */
	public static final String salesAssignQty_COLUMN = "SALES_ASSIGN_QTY";

	/** COLUMNアノテーション finishQty. */
	public static final String finishQty_COLUMN = "FINISH_QTY";

	/** COLUMNアノテーション inspectionQty. */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** COLUMNアノテーション invalidQty. */
	public static final String invalidQty_COLUMN = "INVALID_QTY";

	/** COLUMNアノテーション faultQty. */
	public static final String faultQty_COLUMN = "FAULT_QTY";

	/** COLUMNアノテーション inventoryCost. */
	public static final String inventoryCost_COLUMN = "INVENTORY_COST";

	/** COLUMNアノテーション issueDate. */
	public static final String issueDate_COLUMN = "ISSUE_DATE";

	/** COLUMNアノテーション startDate. */
	public static final String startDate_COLUMN = "START_DATE";

	/** COLUMNアノテーション endDate. */
	public static final String endDate_COLUMN = "END_DATE";

	/** COLUMNアノテーション lastInDate. */
	public static final String lastInDate_COLUMN = "LAST_IN_DATE";

	/** COLUMNアノテーション lastOutDate. */
	public static final String lastOutDate_COLUMN = "LAST_OUT_DATE";

	/** COLUMNアノテーション lastInventoryDate. */
	public static final String lastInventoryDate_COLUMN = "LAST_INVENTORY_DATE";

	/** COLUMNアノテーション lastInventoryQty. */
	public static final String lastInventoryQty_COLUMN = "LAST_INVENTORY_QTY";

	/** COLUMNアノテーション lastInventoryCost. */
	public static final String lastInventoryCost_COLUMN = "LAST_INVENTORY_COST";

	/** COLUMNアノテーション newInventoryCount. */
	public static final String newInventoryCount_COLUMN = "NEW_INVENTORY_COUNT";

	/** COLUMNアノテーション lmInventoryQty. */
	public static final String lmInventoryQty_COLUMN = "LM_INVENTORY_QTY";

	/** COLUMNアノテーション lmInventoryCost. */
	public static final String lmInventoryCost_COLUMN = "LM_INVENTORY_COST";

	/** COLUMNアノテーション aliasLotNo. */
	public static final String aliasLotNo_COLUMN = "ALIAS_LOT_NO";

	/** COLUMNアノテーション inoutQty. */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション fraction. */
	public static final String fraction_COLUMN = "FRACTION";

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

	private String locationCd;

	private String itemCd;

	private String lotNo;

	private String parentItemCd;

	private java.math.BigDecimal inventoryQty;

	private java.math.BigDecimal backorderQty;

	private java.math.BigDecimal assignQty;

	private java.math.BigDecimal salesAssignQty;

	private java.math.BigDecimal finishQty;

	private java.math.BigDecimal inspectionQty;

	private java.math.BigDecimal invalidQty;

	private java.math.BigDecimal faultQty;

	private java.math.BigDecimal inventoryCost;

	private java.sql.Timestamp issueDate;

	private java.sql.Timestamp startDate;

	private java.sql.Timestamp endDate;

	private java.sql.Timestamp lastInDate;

	private java.sql.Timestamp lastOutDate;

	private java.sql.Timestamp lastInventoryDate;

	private java.math.BigDecimal lastInventoryQty;

	private java.math.BigDecimal lastInventoryCost;

	private java.math.BigDecimal newInventoryCount;

	private java.math.BigDecimal lmInventoryQty;

	private java.math.BigDecimal lmInventoryCost;

	private String aliasLotNo;

	private java.math.BigDecimal inoutQty;

	private java.math.BigDecimal fraction;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * locationCd取得.
	 * @return locationCd
	 */
	public String getLocationCd() {
		return this.locationCd;
	}

	/**
	 * locationCd設定.
	 * @param locationCd locationCd
	 */
	public void setLocationCd(final String locationCd) {
		this.locationCd = locationCd;
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
	 * parentItemCd取得.
	 * @return parentItemCd
	 */
	public String getParentItemCd() {
		return this.parentItemCd;
	}

	/**
	 * parentItemCd設定.
	 * @param parentItemCd parentItemCd
	 */
	public void setParentItemCd(final String parentItemCd) {
		this.parentItemCd = parentItemCd;
	}

	/**
	 * inventoryQty取得.
	 * @return inventoryQty
	 */
	public java.math.BigDecimal getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * inventoryQty設定.
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final java.math.BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * backorderQty取得.
	 * @return backorderQty
	 */
	public java.math.BigDecimal getBackorderQty() {
		return this.backorderQty;
	}

	/**
	 * backorderQty設定.
	 * @param backorderQty backorderQty
	 */
	public void setBackorderQty(final java.math.BigDecimal backorderQty) {
		this.backorderQty = backorderQty;
	}

	/**
	 * assignQty取得.
	 * @return assignQty
	 */
	public java.math.BigDecimal getAssignQty() {
		return this.assignQty;
	}

	/**
	 * assignQty設定.
	 * @param assignQty assignQty
	 */
	public void setAssignQty(final java.math.BigDecimal assignQty) {
		this.assignQty = assignQty;
	}

	/**
	 * salesAssignQty取得.
	 * @return salesAssignQty
	 */
	public java.math.BigDecimal getSalesAssignQty() {
		return this.salesAssignQty;
	}

	/**
	 * salesAssignQty設定.
	 * @param salesAssignQty salesAssignQty
	 */
	public void setSalesAssignQty(final java.math.BigDecimal salesAssignQty) {
		this.salesAssignQty = salesAssignQty;
	}

	/**
	 * finishQty取得.
	 * @return finishQty
	 */
	public java.math.BigDecimal getFinishQty() {
		return this.finishQty;
	}

	/**
	 * finishQty設定.
	 * @param finishQty finishQty
	 */
	public void setFinishQty(final java.math.BigDecimal finishQty) {
		this.finishQty = finishQty;
	}

	/**
	 * inspectionQty取得.
	 * @return inspectionQty
	 */
	public java.math.BigDecimal getInspectionQty() {
		return this.inspectionQty;
	}

	/**
	 * inspectionQty設定.
	 * @param inspectionQty inspectionQty
	 */
	public void setInspectionQty(final java.math.BigDecimal inspectionQty) {
		this.inspectionQty = inspectionQty;
	}

	/**
	 * invalidQty取得.
	 * @return invalidQty
	 */
	public java.math.BigDecimal getInvalidQty() {
		return this.invalidQty;
	}

	/**
	 * invalidQty設定.
	 * @param invalidQty invalidQty
	 */
	public void setInvalidQty(final java.math.BigDecimal invalidQty) {
		this.invalidQty = invalidQty;
	}

	/**
	 * faultQty取得.
	 * @return faultQty
	 */
	public java.math.BigDecimal getFaultQty() {
		return this.faultQty;
	}

	/**
	 * faultQty設定.
	 * @param faultQty faultQty
	 */
	public void setFaultQty(final java.math.BigDecimal faultQty) {
		this.faultQty = faultQty;
	}

	/**
	 * inventoryCost取得.
	 * @return inventoryCost
	 */
	public java.math.BigDecimal getInventoryCost() {
		return this.inventoryCost;
	}

	/**
	 * inventoryCost設定.
	 * @param inventoryCost inventoryCost
	 */
	public void setInventoryCost(final java.math.BigDecimal inventoryCost) {
		this.inventoryCost = inventoryCost;
	}

	/**
	 * issueDate取得.
	 * @return issueDate
	 */
	public java.sql.Timestamp getIssueDate() {
		return this.issueDate;
	}

	/**
	 * issueDate設定.
	 * @param issueDate issueDate
	 */
	public void setIssueDate(final java.sql.Timestamp issueDate) {
		this.issueDate = issueDate;
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
	 * lastInDate取得.
	 * @return lastInDate
	 */
	public java.sql.Timestamp getLastInDate() {
		return this.lastInDate;
	}

	/**
	 * lastInDate設定.
	 * @param lastInDate lastInDate
	 */
	public void setLastInDate(final java.sql.Timestamp lastInDate) {
		this.lastInDate = lastInDate;
	}

	/**
	 * lastOutDate取得.
	 * @return lastOutDate
	 */
	public java.sql.Timestamp getLastOutDate() {
		return this.lastOutDate;
	}

	/**
	 * lastOutDate設定.
	 * @param lastOutDate lastOutDate
	 */
	public void setLastOutDate(final java.sql.Timestamp lastOutDate) {
		this.lastOutDate = lastOutDate;
	}

	/**
	 * lastInventoryDate取得.
	 * @return lastInventoryDate
	 */
	public java.sql.Timestamp getLastInventoryDate() {
		return this.lastInventoryDate;
	}

	/**
	 * lastInventoryDate設定.
	 * @param lastInventoryDate lastInventoryDate
	 */
	public void setLastInventoryDate(final java.sql.Timestamp lastInventoryDate) {
		this.lastInventoryDate = lastInventoryDate;
	}

	/**
	 * lastInventoryQty取得.
	 * @return lastInventoryQty
	 */
	public java.math.BigDecimal getLastInventoryQty() {
		return this.lastInventoryQty;
	}

	/**
	 * lastInventoryQty設定.
	 * @param lastInventoryQty lastInventoryQty
	 */
	public void setLastInventoryQty(final java.math.BigDecimal lastInventoryQty) {
		this.lastInventoryQty = lastInventoryQty;
	}

	/**
	 * lastInventoryCost取得.
	 * @return lastInventoryCost
	 */
	public java.math.BigDecimal getLastInventoryCost() {
		return this.lastInventoryCost;
	}

	/**
	 * lastInventoryCost設定.
	 * @param lastInventoryCost lastInventoryCost
	 */
	public void setLastInventoryCost(final java.math.BigDecimal lastInventoryCost) {
		this.lastInventoryCost = lastInventoryCost;
	}

	/**
	 * newInventoryCount取得.
	 * @return newInventoryCount
	 */
	public java.math.BigDecimal getNewInventoryCount() {
		return this.newInventoryCount;
	}

	/**
	 * newInventoryCount設定.
	 * @param newInventoryCount newInventoryCount
	 */
	public void setNewInventoryCount(final java.math.BigDecimal newInventoryCount) {
		this.newInventoryCount = newInventoryCount;
	}

	/**
	 * lmInventoryQty取得.
	 * @return lmInventoryQty
	 */
	public java.math.BigDecimal getLmInventoryQty() {
		return this.lmInventoryQty;
	}

	/**
	 * lmInventoryQty設定.
	 * @param lmInventoryQty lmInventoryQty
	 */
	public void setLmInventoryQty(final java.math.BigDecimal lmInventoryQty) {
		this.lmInventoryQty = lmInventoryQty;
	}

	/**
	 * lmInventoryCost取得.
	 * @return lmInventoryCost
	 */
	public java.math.BigDecimal getLmInventoryCost() {
		return this.lmInventoryCost;
	}

	/**
	 * lmInventoryCost設定.
	 * @param lmInventoryCost lmInventoryCost
	 */
	public void setLmInventoryCost(final java.math.BigDecimal lmInventoryCost) {
		this.lmInventoryCost = lmInventoryCost;
	}

	/**
	 * aliasLotNo取得.
	 * @return aliasLotNo
	 */
	public String getAliasLotNo() {
		return this.aliasLotNo;
	}

	/**
	 * aliasLotNo設定.
	 * @param aliasLotNo aliasLotNo
	 */
	public void setAliasLotNo(final String aliasLotNo) {
		this.aliasLotNo = aliasLotNo;
	}

	/**
	 * inoutQty取得.
	 * @return inoutQty
	 */
	public java.math.BigDecimal getInoutQty() {
		return this.inoutQty;
	}

	/**
	 * inoutQty設定.
	 * @param inoutQty inoutQty
	 */
	public void setInoutQty(final java.math.BigDecimal inoutQty) {
		this.inoutQty = inoutQty;
	}

	/**
	 * fraction取得.
	 * @return fraction
	 */
	public java.math.BigDecimal getFraction() {
		return this.fraction;
	}

	/**
	 * fraction設定.
	 * @param fraction fraction
	 */
	public void setFraction(final java.math.BigDecimal fraction) {
		this.fraction = fraction;
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
