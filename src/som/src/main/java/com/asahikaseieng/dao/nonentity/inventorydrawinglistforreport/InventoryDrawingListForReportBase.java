/*
 * Created on 2009/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryDrawingListForReportクラス.
 * @author kanri-user
 */
public class InventoryDrawingListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inoutSourceNo */
	public static final String inoutSourceNo_COLUMN = "INOUT_SOURCE_NO";

	/** COLUMNアノテーション inoutDivision */
	public static final String inoutDivision_COLUMN = "INOUT_DIVISION";

	/** COLUMNアノテーション inoutDivisionName */
	public static final String inoutDivisionName_COLUMN = "INOUT_DIVISION_NAME";

	/** COLUMNアノテーション possibleQty */
	public static final String possibleQty_COLUMN = "POSSIBLE_QTY";

	/** COLUMNアノテーション oderNo */
	public static final String oderNo_COLUMN = "ODER_NO";

	/** COLUMNアノテーション oderLineNo */
	public static final String oderLineNo_COLUMN = "ODER_LINE_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション parentItemName */
	public static final String parentItemName_COLUMN = "PARENT_ITEM_NAME";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション inoutDate */
	public static final String inoutDate_COLUMN = "INOUT_DATE";

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

	/** COLUMNアノテーション itemType */
	public static final String itemType_COLUMN = "ITEM_TYPE";

	/** COLUMNアノテーション referenceNo */
	public static final String referenceNo_COLUMN = "REFERENCE_NO";

	/** COLUMNアノテーション referenceLineNo */
	public static final String referenceLineNo_COLUMN = "REFERENCE_LINE_NO";

	/** COLUMNアノテーション assignFlag */
	public static final String assignFlag_COLUMN = "ASSIGN_FLAG";

	/** COLUMNアノテーション overFlg */
	public static final String overFlg_COLUMN = "OVER_FLG";

	//
	// インスタンスフィールド
	//

	private String inoutSourceNo;

	private java.math.BigDecimal inoutDivision;

	private String inoutDivisionName;

	private java.math.BigDecimal possibleQty;

	private String oderNo;

	private java.math.BigDecimal oderLineNo;

	private String itemCd;

	private String itemName;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private String unitOfOperationManagement;

	private String parentItemCd;

	private String parentItemName;

	private String locationCd;

	private String locationName;

	private String lotNo;

	private java.math.BigDecimal inoutQty;

	private java.sql.Timestamp inoutDate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

	private java.math.BigDecimal itemType;

	private String referenceNo;

	private java.math.BigDecimal referenceLineNo;

	private java.math.BigDecimal assignFlag;

	private java.math.BigDecimal overFlg;

	//
	// インスタンスメソッド
	//

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
	 * inoutDivisionName取得.
	 * @return inoutDivisionName
	 */
	public String getInoutDivisionName() {
		return this.inoutDivisionName;
	}

	/**
	 * inoutDivisionName設定.
	 * @param inoutDivisionName inoutDivisionName
	 */
	public void setInoutDivisionName(final String inoutDivisionName) {
		this.inoutDivisionName = inoutDivisionName;
	}

	/**
	 * possibleQty取得.
	 * @return possibleQty
	 */
	public java.math.BigDecimal getPossibleQty() {
		return this.possibleQty;
	}

	/**
	 * possibleQty設定.
	 * @param possibleQty possibleQty
	 */
	public void setPossibleQty(final java.math.BigDecimal possibleQty) {
		this.possibleQty = possibleQty;
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
	 * unitOfOperationManagement取得.
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return this.unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagement設定.
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
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
		return this.parentItemName;
	}

	/**
	 * parentItemName設定.
	 * @param parentItemName parentItemName
	 */
	public void setParentItemName(final String parentItemName) {
		this.parentItemName = parentItemName;
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
	 * inoutDate取得.
	 * @return inoutDate
	 */
	public java.sql.Timestamp getInoutDate() {
		return this.inoutDate;
	}

	/**
	 * inoutDate設定.
	 * @param inoutDate inoutDate
	 */
	public void setInoutDate(final java.sql.Timestamp inoutDate) {
		this.inoutDate = inoutDate;
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
	 * itemType取得.
	 * @return itemType
	 */
	public java.math.BigDecimal getItemType() {
		return this.itemType;
	}

	/**
	 * itemType設定.
	 * @param itemType itemType
	 */
	public void setItemType(final java.math.BigDecimal itemType) {
		this.itemType = itemType;
	}

	/**
	 * referenceNo取得.
	 * @return referenceNo
	 */
	public String getReferenceNo() {
		return this.referenceNo;
	}

	/**
	 * referenceNo設定.
	 * @param referenceNo referenceNo
	 */
	public void setReferenceNo(final String referenceNo) {
		this.referenceNo = referenceNo;
	}

	/**
	 * referenceLineNo取得.
	 * @return referenceLineNo
	 */
	public java.math.BigDecimal getReferenceLineNo() {
		return this.referenceLineNo;
	}

	/**
	 * referenceLineNo設定.
	 * @param referenceLineNo referenceLineNo
	 */
	public void setReferenceLineNo(final java.math.BigDecimal referenceLineNo) {
		this.referenceLineNo = referenceLineNo;
	}

	/**
	 * assignFlag取得.
	 * @return assignFlag
	 */
	public java.math.BigDecimal getAssignFlag() {
		return this.assignFlag;
	}

	/**
	 * assignFlag設定.
	 * @param assignFlag assignFlag
	 */
	public void setAssignFlag(final java.math.BigDecimal assignFlag) {
		this.assignFlag = assignFlag;
	}

	/**
	 * overFlg取得.
	 * @return overFlg
	 */
	public java.math.BigDecimal getOverFlg() {
		return this.overFlg;
	}

	/**
	 * overFlg設定.
	 * @param overFlg overFlg
	 */
	public void setOverFlg(final java.math.BigDecimal overFlg) {
		this.overFlg = overFlg;
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

