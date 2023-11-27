/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * InventoryDrawingListクラス.
 * @author t0011036
 */
public class InventoryDrawingListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション inoutDate */
	public static final String inoutDate_COLUMN = "INOUT_DATE";

	/** COLUMNアノテーション inoutDivision */
	public static final String inoutDivision_COLUMN = "INOUT_DIVISION";

	/** COLUMNアノテーション oderNo */
	public static final String oderNo_COLUMN = "ODER_NO";

	/** COLUMNアノテーション inoutQty */
	public static final String inoutQty_COLUMN = "INOUT_QTY";

	/** COLUMNアノテーション oderLineNo */
	public static final String oderLineNo_COLUMN = "ODER_LINE_NO";

	/** COLUMNアノテーション parentItemCd */
	public static final String parentItemCd_COLUMN = "PARENT_ITEM_CD";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション inoutDivisionName */
	public static final String inoutDivisionName_COLUMN = "INOUT_DIVISION_NAME";

	/** COLUMNアノテーション possibleQty */
	public static final String possibleQty_COLUMN = "POSSIBLE_QTY";

	/** COLUMNアノテーション inoutSourceNo */
	public static final String inoutSourceNo_COLUMN = "INOUT_SOURCE_NO";

	/** COLUMNアノテーション directionDivision */
	public static final String directionDivision_COLUMN = "DIRECTION_DIVISION";

	/** COLUMNアノテーション directionStatus */
	public static final String directionStatus_COLUMN = "DIRECTION_STATUS";

	/** COLUMNアノテーション linkNo */
	public static final String linkNo_COLUMN = "LINK_NO";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp inoutDate;

	private java.math.BigDecimal inoutDivision;

	private String oderNo;

	private java.math.BigDecimal inoutQty;

	private java.math.BigDecimal oderLineNo;

	private String parentItemCd;

	private String unitOfOperationManagement;

	private String locationCd;

	private String inoutDivisionName;

	private java.math.BigDecimal possibleQty;

	private String inoutSourceNo;

	private java.math.BigDecimal directionDivision;

	private java.math.BigDecimal directionStatus;

	private String linkNo;

	//
	// インスタンスメソッド
	//

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
	 * linkNo取得.
	 * @return linkNo
	 */
	public String getLinkNo() {
		return this.linkNo;
	}

	/**
	 * linkNo設定.
	 * @param linkNo linkNo
	 */
	public void setLinkNo(final String linkNo) {
		this.linkNo = linkNo;
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

