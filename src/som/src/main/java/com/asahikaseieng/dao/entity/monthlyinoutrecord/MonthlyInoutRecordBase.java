/*
 * Created on Thu Jan 22 20:07:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.monthlyinoutrecord;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * MonthlyInoutRecordBaseクラス.
 * @author t0011036
 */
public class MonthlyInoutRecordBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MonthlyInoutRecordBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "MONTHLY_INOUT_RECORD";


//	/** IDアノテーション itemCd. */
//	public static final String itemCd_ID = "assigned";
//	/** IDアノテーション locationCd. */
//	public static final String locationCd_ID = "assigned";
//	/** IDアノテーション lotNo. */
//	public static final String lotNo_ID = "assigned";
//	/** IDアノテーション targetMonth. */
//	public static final String targetMonth_ID = "assigned";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション lotNo. */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション targetMonth. */
	public static final String targetMonth_COLUMN = "TARGET_MONTH";

	/** COLUMNアノテーション inventoryClosingDay. */
	public static final String inventoryClosingDay_COLUMN = "INVENTORY_CLOSING_DAY";

	/** COLUMNアノテーション lmInventoryQty. */
	public static final String lmInventoryQty_COLUMN = "LM_INVENTORY_QTY";

	/** COLUMNアノテーション lmInventoryCost. */
	public static final String lmInventoryCost_COLUMN = "LM_INVENTORY_COST";

	/** COLUMNアノテーション lmInventoryAmount. */
	public static final String lmInventoryAmount_COLUMN = "LM_INVENTORY_AMOUNT";

	/** COLUMNアノテーション inQty. */
	public static final String inQty_COLUMN = "IN_QTY";

	/** COLUMNアノテーション inAmount. */
	public static final String inAmount_COLUMN = "IN_AMOUNT";

	/** COLUMNアノテーション outQty. */
	public static final String outQty_COLUMN = "OUT_QTY";

	/** COLUMNアノテーション outAmount. */
	public static final String outAmount_COLUMN = "OUT_AMOUNT";

	/** COLUMNアノテーション nmInventoryQty. */
	public static final String nmInventoryQty_COLUMN = "NM_INVENTORY_QTY";

	/** COLUMNアノテーション nmInventoryCost. */
	public static final String nmInventoryCost_COLUMN = "NM_INVENTORY_COST";

	/** COLUMNアノテーション nmInventoryAmount. */
	public static final String nmInventoryAmount_COLUMN = "NM_INVENTORY_AMOUNT";

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

	private java.math.BigDecimal targetMonth;

	private java.math.BigDecimal inventoryClosingDay;

	private java.math.BigDecimal lmInventoryQty;

	private java.math.BigDecimal lmInventoryCost;

	private java.math.BigDecimal lmInventoryAmount;

	private java.math.BigDecimal inQty;

	private java.math.BigDecimal inAmount;

	private java.math.BigDecimal outQty;

	private java.math.BigDecimal outAmount;

	private java.math.BigDecimal nmInventoryQty;

	private java.math.BigDecimal nmInventoryCost;

	private java.math.BigDecimal nmInventoryAmount;

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
	 * targetMonth取得.
	 * @return targetMonth
	 */
	public java.math.BigDecimal getTargetMonth() {
		return this.targetMonth;
	}

	/**
	 * targetMonth設定.
	 * @param targetMonth targetMonth
	 */
	public void setTargetMonth(final java.math.BigDecimal targetMonth) {
		this.targetMonth = targetMonth;
	}

	/**
	 * inventoryClosingDay取得.
	 * @return inventoryClosingDay
	 */
	public java.math.BigDecimal getInventoryClosingDay() {
		return this.inventoryClosingDay;
	}

	/**
	 * inventoryClosingDay設定.
	 * @param inventoryClosingDay inventoryClosingDay
	 */
	public void setInventoryClosingDay(final java.math.BigDecimal inventoryClosingDay) {
		this.inventoryClosingDay = inventoryClosingDay;
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
	 * lmInventoryAmount取得.
	 * @return lmInventoryAmount
	 */
	public java.math.BigDecimal getLmInventoryAmount() {
		return this.lmInventoryAmount;
	}

	/**
	 * lmInventoryAmount設定.
	 * @param lmInventoryAmount lmInventoryAmount
	 */
	public void setLmInventoryAmount(final java.math.BigDecimal lmInventoryAmount) {
		this.lmInventoryAmount = lmInventoryAmount;
	}

	/**
	 * inQty取得.
	 * @return inQty
	 */
	public java.math.BigDecimal getInQty() {
		return this.inQty;
	}

	/**
	 * inQty設定.
	 * @param inQty inQty
	 */
	public void setInQty(final java.math.BigDecimal inQty) {
		this.inQty = inQty;
	}

	/**
	 * inAmount取得.
	 * @return inAmount
	 */
	public java.math.BigDecimal getInAmount() {
		return this.inAmount;
	}

	/**
	 * inAmount設定.
	 * @param inAmount inAmount
	 */
	public void setInAmount(final java.math.BigDecimal inAmount) {
		this.inAmount = inAmount;
	}

	/**
	 * outQty取得.
	 * @return outQty
	 */
	public java.math.BigDecimal getOutQty() {
		return this.outQty;
	}

	/**
	 * outQty設定.
	 * @param outQty outQty
	 */
	public void setOutQty(final java.math.BigDecimal outQty) {
		this.outQty = outQty;
	}

	/**
	 * outAmount取得.
	 * @return outAmount
	 */
	public java.math.BigDecimal getOutAmount() {
		return this.outAmount;
	}

	/**
	 * outAmount設定.
	 * @param outAmount outAmount
	 */
	public void setOutAmount(final java.math.BigDecimal outAmount) {
		this.outAmount = outAmount;
	}

	/**
	 * nmInventoryQty取得.
	 * @return nmInventoryQty
	 */
	public java.math.BigDecimal getNmInventoryQty() {
		return this.nmInventoryQty;
	}

	/**
	 * nmInventoryQty設定.
	 * @param nmInventoryQty nmInventoryQty
	 */
	public void setNmInventoryQty(final java.math.BigDecimal nmInventoryQty) {
		this.nmInventoryQty = nmInventoryQty;
	}

	/**
	 * nmInventoryCost取得.
	 * @return nmInventoryCost
	 */
	public java.math.BigDecimal getNmInventoryCost() {
		return this.nmInventoryCost;
	}

	/**
	 * nmInventoryCost設定.
	 * @param nmInventoryCost nmInventoryCost
	 */
	public void setNmInventoryCost(final java.math.BigDecimal nmInventoryCost) {
		this.nmInventoryCost = nmInventoryCost;
	}

	/**
	 * nmInventoryAmount取得.
	 * @return nmInventoryAmount
	 */
	public java.math.BigDecimal getNmInventoryAmount() {
		return this.nmInventoryAmount;
	}

	/**
	 * nmInventoryAmount設定.
	 * @param nmInventoryAmount nmInventoryAmount
	 */
	public void setNmInventoryAmount(final java.math.BigDecimal nmInventoryAmount) {
		this.nmInventoryAmount = nmInventoryAmount;
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
