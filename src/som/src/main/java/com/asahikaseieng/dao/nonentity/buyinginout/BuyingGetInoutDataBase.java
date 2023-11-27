/*
 * Created on 2010/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyinginout;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * BuyingGetInoutDataクラス.
 * @author t1344224
 */
public class BuyingGetInoutDataBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingGetInoutDataBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inoutNo */
	public static final String inoutNo_COLUMN = "INOUT_NO";

	/** COLUMNアノテーション inoutDivision */
	public static final String inoutDivision_COLUMN = "INOUT_DIVISION";

	/** COLUMNアノテーション oderNo */
	public static final String oderNo_COLUMN = "ODER_NO";

	/** COLUMNアノテーション oderLineNo */
	public static final String oderLineNo_COLUMN = "ODER_LINE_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション inoutWeight */
	public static final String inoutWeight_COLUMN = "INOUT_WEIGHT";

	/** COLUMNアノテーション inoutPrice */
	public static final String inoutPrice_COLUMN = "INOUT_PRICE";

	/** COLUMNアノテーション inoutCost */
	public static final String inoutCost_COLUMN = "INOUT_COST";

	/** COLUMNアノテーション inoutDate */
	public static final String inoutDate_COLUMN = "INOUT_DATE";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション inoutSourceNo */
	public static final String inoutSourceNo_COLUMN = "INOUT_SOURCE_NO";

	/** COLUMNアノテーション sectionCd */
	public static final String sectionCd_COLUMN = "SECTION_CD";

	/** COLUMNアノテーション accountsCd */
	public static final String accountsCd_COLUMN = "ACCOUNTS_CD";

	/** COLUMNアノテーション accountsSubCd */
	public static final String accountsSubCd_COLUMN = "ACCOUNTS_SUB_CD";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション parentAccountsCd */
	public static final String parentAccountsCd_COLUMN = "PARENT_ACCOUNTS_CD";

	/** COLUMNアノテーション parentAccountSubCd */
	public static final String parentAccountSubCd_COLUMN = "PARENT_ACCOUNT_SUB_CD";

	/** COLUMNアノテーション parentItemCategory */
	public static final String parentItemCategory_COLUMN = "PARENT_ITEM_CATEGORY";

	/** COLUMNアノテーション ryCd */
	public static final String ryCd_COLUMN = "RY_CD";

	/** COLUMNアノテーション ryName */
	public static final String ryName_COLUMN = "RY_NAME";

	/** COLUMNアノテーション reason */
	public static final String reason_COLUMN = "REASON";

	/** COLUMNアノテーション inoutUpdateDate */
	public static final String inoutUpdateDate_COLUMN = "INOUT_UPDATE_DATE";

	/** COLUMNアノテーション inventoryUpdateDate */
	public static final String inventoryUpdateDate_COLUMN = "INVENTORY_UPDATE_DATE";

	/** COLUMNアノテーション funcDivision */
	public static final String funcDivision_COLUMN = "FUNC_DIVISION";

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

	//
	// インスタンスフィールド
	//

	private String inoutNo;

	private java.math.BigDecimal inoutDivision;

	private String oderNo;

	private java.math.BigDecimal oderLineNo;

	private String itemCd;

	private String locationCd;

	private String lotNo;

	private java.math.BigDecimal inoutQty;

	private java.math.BigDecimal inoutWeight;

	private java.math.BigDecimal inoutPrice;

	private java.math.BigDecimal inoutCost;

	private String inoutDate;

	private String remark;

	private String inoutSourceNo;

	private String sectionCd;

	private String accountsCd;

	private String accountsSubCd;

	private String itemCategory;

	private String parentItemCd;

	private String parentAccountsCd;

	private String parentAccountSubCd;

	private String parentItemCategory;

	private String ryCd;

	private String ryName;

	private String reason;

	private java.sql.Timestamp inoutUpdateDate;

	private java.sql.Timestamp inventoryUpdateDate;

	private java.math.BigDecimal funcDivision;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * inoutNo取得.
	 * @return inoutNo
	 */
	public String getInoutNo() {
		return this.inoutNo;
	}

	/**
	 * inoutNo設定.
	 * @param inoutNo inoutNo
	 */
	public void setInoutNo(final String inoutNo) {
		this.inoutNo = inoutNo;
	}

	/**
	 * inoutDivision取得.
	 * @return inoutDivision
	 */
	public java.math.BigDecimal getInoutDivision() {
		return this.inoutDivision;
	}

	/**
	 * inoutDivision設定.
	 * @param inoutDivision inoutDivision
	 */
	public void setInoutDivision(final java.math.BigDecimal inoutDivision) {
		this.inoutDivision = inoutDivision;
	}

	/**
	 * oderNo取得.
	 * @return oderNo
	 */
	public String getOderNo() {
		return this.oderNo;
	}

	/**
	 * oderNo設定.
	 * @param oderNo oderNo
	 */
	public void setOderNo(final String oderNo) {
		this.oderNo = oderNo;
	}

	/**
	 * oderLineNo取得.
	 * @return oderLineNo
	 */
	public java.math.BigDecimal getOderLineNo() {
		return this.oderLineNo;
	}

	/**
	 * oderLineNo設定.
	 * @param oderLineNo oderLineNo
	 */
	public void setOderLineNo(final java.math.BigDecimal oderLineNo) {
		this.oderLineNo = oderLineNo;
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
	 * inoutWeight取得.
	 * @return inoutWeight
	 */
	public java.math.BigDecimal getInoutWeight() {
		return inoutWeight;
	}

	/**
	 * inoutWeight設定.
	 * @param inoutWeight inoutWeight
	 */
	public void setInoutWeight(final java.math.BigDecimal inoutWeight) {
		this.inoutWeight = inoutWeight;
	}

	/**
	 * inoutPrice取得.
	 * @return inoutPrice
	 */
	public java.math.BigDecimal getInoutPrice() {
		return this.inoutPrice;
	}

	/**
	 * inoutPrice設定.
	 * @param inoutPrice inoutPrice
	 */
	public void setInoutPrice(final java.math.BigDecimal inoutPrice) {
		this.inoutPrice = inoutPrice;
	}

	/**
	 * inoutCost取得.
	 * @return inoutCost
	 */
	public java.math.BigDecimal getInoutCost() {
		return this.inoutCost;
	}

	/**
	 * inoutCost設定.
	 * @param inoutCost inoutCost
	 */
	public void setInoutCost(final java.math.BigDecimal inoutCost) {
		this.inoutCost = inoutCost;
	}

	/**
	 * inoutDate取得.
	 * @return inoutDate
	 */
	public String getInoutDate() {
		return this.inoutDate;
	}

	/**
	 * inoutDate設定.
	 * @param inoutDate inoutDate
	 */
	public void setInoutDate(final String inoutDate) {
		this.inoutDate = inoutDate;
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
	 * inoutSourceNo取得.
	 * @return inoutSourceNo
	 */
	public String getInoutSourceNo() {
		return this.inoutSourceNo;
	}

	/**
	 * inoutSourceNo設定.
	 * @param inoutSourceNo inoutSourceNo
	 */
	public void setInoutSourceNo(final String inoutSourceNo) {
		this.inoutSourceNo = inoutSourceNo;
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
	 * accountsCd取得.
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return this.accountsCd;
	}

	/**
	 * accountsCd設定.
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsSubCd取得.
	 * @return accountsSubCd
	 */
	public String getAccountsSubCd() {
		return this.accountsSubCd;
	}

	/**
	 * accountsSubCd設定.
	 * @param accountsSubCd accountsSubCd
	 */
	public void setAccountsSubCd(final String accountsSubCd) {
		this.accountsSubCd = accountsSubCd;
	}

	/**
	 * itemCategory取得.
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * itemCategory設定.
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(final String itemCategory) {
		this.itemCategory = itemCategory;
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
	 * parentAccountsCd取得.
	 * @return parentAccountsCd
	 */
	public String getParentAccountsCd() {
		return this.parentAccountsCd;
	}

	/**
	 * parentAccountsCd設定.
	 * @param parentAccountsCd parentAccountsCd
	 */
	public void setParentAccountsCd(final String parentAccountsCd) {
		this.parentAccountsCd = parentAccountsCd;
	}

	/**
	 * parentAccountSubCd取得.
	 * @return parentAccountSubCd
	 */
	public String getParentAccountSubCd() {
		return this.parentAccountSubCd;
	}

	/**
	 * parentAccountSubCd設定.
	 * @param parentAccountSubCd parentAccountSubCd
	 */
	public void setParentAccountSubCd(final String parentAccountSubCd) {
		this.parentAccountSubCd = parentAccountSubCd;
	}

	/**
	 * parentItemCategory取得.
	 * @return parentItemCategory
	 */
	public String getParentItemCategory() {
		return this.parentItemCategory;
	}

	/**
	 * parentItemCategory設定.
	 * @param parentItemCategory parentItemCategory
	 */
	public void setParentItemCategory(final String parentItemCategory) {
		this.parentItemCategory = parentItemCategory;
	}

	/**
	 * ryCd取得.
	 * @return ryCd
	 */
	public String getRyCd() {
		return this.ryCd;
	}

	/**
	 * ryCd設定.
	 * @param ryCd ryCd
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * ryName取得.
	 * @return ryName
	 */
	public String getRyName() {
		return this.ryName;
	}

	/**
	 * ryName設定.
	 * @param ryName ryName
	 */
	public void setRyName(final String ryName) {
		this.ryName = ryName;
	}

	/**
	 * reason取得.
	 * @return reason
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * reason設定.
	 * @param reason reason
	 */
	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * inoutUpdateDate取得.
	 * @return inoutUpdateDate
	 */
	public java.sql.Timestamp getInoutUpdateDate() {
		return this.inoutUpdateDate;
	}

	/**
	 * inoutUpdateDate設定.
	 * @param inoutUpdateDate inoutUpdateDate
	 */
	public void setInoutUpdateDate(final java.sql.Timestamp inoutUpdateDate) {
		this.inoutUpdateDate = inoutUpdateDate;
	}

	/**
	 * inventoryUpdateDate取得.
	 * @return inventoryUpdateDate
	 */
	public java.sql.Timestamp getInventoryUpdateDate() {
		return this.inventoryUpdateDate;
	}

	/**
	 * inventoryUpdateDate設定.
	 * @param inventoryUpdateDate inventoryUpdateDate
	 */
	public void setInventoryUpdateDate(
			final java.sql.Timestamp inventoryUpdateDate) {
		this.inventoryUpdateDate = inventoryUpdateDate;
	}

	/**
	 * funcDivision取得.
	 * @return funcDivision
	 */
	public java.math.BigDecimal getFuncDivision() {
		return this.funcDivision;
	}

	/**
	 * funcDivision設定.
	 * @param funcDivision funcDivision
	 */
	public void setFuncDivision(final java.math.BigDecimal funcDivision) {
		this.funcDivision = funcDivision;
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
