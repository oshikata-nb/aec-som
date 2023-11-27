/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * InventoryLowerLocationListForReportクラス.
 * @author kanri-user
 */
public class InventoryLowerLocationListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryLowerLocationListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemName_COLUMN = "PARENT_ITEM_NAME";

	/** COLUMNアノテーション packQty */
	public static final String packQty_COLUMN = "PACK_QTY";

	/** COLUMNアノテーション packUnit */
	public static final String packUnit_COLUMN = "PACK_UNIT";

	/** COLUMNアノテーション fractionNum */
	public static final String fractionNum_COLUMN = "FRACTION_NUM";

	/** COLUMNアノテーション fractionUnit */
	public static final String fractionUnit_COLUMN = "FRACTION_UNIT";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション backorderQty */
	public static final String backorderQty_COLUMN = "BACKORDER_QTY";

	/** COLUMNアノテーション assignQty */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	/** COLUMNアノテーション salesAssignQty */
	public static final String salesAssignQty_COLUMN = "SALES_ASSIGN_QTY";

	/** COLUMNアノテーション finishQty */
	public static final String finishQty_COLUMN = "FINISH_QTY";

	/** COLUMNアノテーション inspectionQty */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** COLUMNアノテーション invalidQty */
	public static final String invalidQty_COLUMN = "INVALID_QTY";

	/** COLUMNアノテーション faultQty */
	public static final String faultQty_COLUMN = "FAULT_QTY";

	/** COLUMNアノテーション inventoryCost */
	public static final String inventoryCost_COLUMN = "INVENTORY_COST";

	/** COLUMNアノテーション lastInDate */
	public static final String lastInDate_COLUMN = "LAST_IN_DATE";

	/** COLUMNアノテーション lastOutDate */
	public static final String lastOutDate_COLUMN = "LAST_OUT_DATE";

	/** COLUMNアノテーション lastInventoryDate */
	public static final String lastInventoryDate_COLUMN = "LAST_INVENTORY_DATE";

	/** COLUMNアノテーション lastInventoryQty */
	public static final String lastInventoryQty_COLUMN = "LAST_INVENTORY_QTY";

	/** COLUMNアノテーション lastInventoryCost */
	public static final String lastInventoryCost_COLUMN = "LAST_INVENTORY_COST";

	/** COLUMNアノテーション newInventoryQty */
	public static final String newInventoryQty_COLUMN = "NEW_INVENTORY_QTY";

	/** COLUMNアノテーション lmInventoryQty */
	public static final String lmInventoryQty_COLUMN = "LM_INVENTORY_QTY";

	/** COLUMNアノテーション lmInventoryCost */
	public static final String lmInventoryCost_COLUMN = "LM_INVENTORY_COST";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション fraction */
	public static final String fraction_COLUMN = "FRACTION";

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

	private String locationCd;

	private String locationName;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String otherCompanyCd1;

	private String parentItemCd;

	private String parentItemName;

	private java.math.BigDecimal packQty;

	private String packUnit;

	private java.math.BigDecimal fractionNum;

	private String fractionUnit;

	private java.math.BigDecimal inventoryQty;

	private java.math.BigDecimal backorderQty;

	private java.math.BigDecimal assignQty;

	private java.math.BigDecimal salesAssignQty;

	private java.math.BigDecimal finishQty;

	private java.math.BigDecimal inspectionQty;

	private java.math.BigDecimal invalidQty;

	private java.math.BigDecimal faultQty;

	private java.math.BigDecimal inventoryCost;

	private java.sql.Timestamp lastInDate;

	private java.sql.Timestamp lastOutDate;

	private java.sql.Timestamp lastInventoryDate;

	private java.math.BigDecimal lastInventoryQty;

	private java.math.BigDecimal lastInventoryCost;

	private java.math.BigDecimal newInventoryQty;

	private java.math.BigDecimal lmInventoryQty;

	private java.math.BigDecimal lmInventoryCost;

	private java.math.BigDecimal inoutQty;

	private java.math.BigDecimal fraction;

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
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
	 * otherCompanyCd1取得.
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return this.otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1設定.
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(final String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
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
	 * parentItemName取得.
	 * @return parentItemName
	 */
	public String getParentItemName() {
		return parentItemName;
	}

	/**
	 * parentItemName設定.
	 * @param parentItemName parentItemName
	 */
	public void setParentItemName(final String parentItemName) {
		this.parentItemName = parentItemName;
	}

	/**
	 * packQty取得.
	 * @return packQty
	 */
	public java.math.BigDecimal getPackQty() {
		return this.packQty;
	}

	/**
	 * packQty設定.
	 * @param packQty packQty
	 */
	public void setPackQty(final java.math.BigDecimal packQty) {
		this.packQty = packQty;
	}

	/**
	 * packUnit取得.
	 * @return packUnit
	 */
	public String getPackUnit() {
		return this.packUnit;
	}

	/**
	 * packUnit設定.
	 * @param packUnit packUnit
	 */
	public void setPackUnit(final String packUnit) {
		this.packUnit = packUnit;
	}

	/**
	 * fraction取得.
	 * @return fraction
	 */
	public java.math.BigDecimal getFractionNum() {
		return this.fractionNum;
	}

	/**
	 * fraction設定.
	 * @param fraction fraction
	 */
	public void setFractionNum(final java.math.BigDecimal fraction) {
		this.fractionNum = fraction;
	}

	/**
	 * fractionUnit取得.
	 * @return fractionUnit
	 */
	public String getFractionUnit() {
		return this.fractionUnit;
	}

	/**
	 * fractionUnit設定.
	 * @param fractionUnit fractionUnit
	 */
	public void setFractionUnit(final String fractionUnit) {
		this.fractionUnit = fractionUnit;
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
	public void setLastInventoryCost(
			final java.math.BigDecimal lastInventoryCost) {
		this.lastInventoryCost = lastInventoryCost;
	}

	/**
	 * newInventoryQty取得.
	 * @return newInventoryQty
	 */
	public java.math.BigDecimal getNewInventoryQty() {
		return this.newInventoryQty;
	}

	/**
	 * newInventoryQty設定.
	 * @param newInventoryQty newInventoryQty
	 */
	public void setNewInventoryQty(final java.math.BigDecimal newInventoryQty) {
		this.newInventoryQty = newInventoryQty;
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
	 * fraction取得.
	 * @return fraction
	 */
	public java.math.BigDecimal getFraction() {
		return fraction;
	}

	/**
	 * fraction設定.
	 * @param fraction fraction
	 */
	public void setFraction(final java.math.BigDecimal fraction) {
		this.fraction = fraction;
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
