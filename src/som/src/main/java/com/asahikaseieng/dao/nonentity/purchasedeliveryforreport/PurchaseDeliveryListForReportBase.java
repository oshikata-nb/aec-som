/*
 * Created on 2009/08/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchasedeliveryforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PurchaseDeliveryListForReportクラス.
 * @author kanri-user
 */
public class PurchaseDeliveryListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション orderCount */
	public static final String orderCount_COLUMN = "ORDER_COUNT";

	/** COLUMNアノテーション issuedCount */
	public static final String issuedCount_COLUMN = "ISSUED_COUNT";

	/** COLUMNアノテーション adjustCount */
	public static final String adjustCount_COLUMN = "ADJUST_COUNT";

	/** COLUMNアノテーション fixedCount */
	public static final String fixedCount_COLUMN = "FIXED_COUNT";

	/** COLUMNアノテーション arrivedAcceptedCount */
	public static final String arrivedAcceptedCount_COLUMN = "ARRIVED_ACCEPTED_COUNT";

	//
	// インスタンスフィールド
	//

	private String orderSheetNo;

	private java.sql.Timestamp orderDate;

	private String venderName;

	private java.math.BigDecimal orderCount;

	private java.math.BigDecimal issuedCount;

	private java.math.BigDecimal adjustCount;

	private java.math.BigDecimal fixedCount;

	private java.math.BigDecimal arrivedAcceptedCount;

	//
	// インスタンスメソッド
	//

	/**
	 * orderSheetNo取得.
	 * @return orderSheetNo
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * orderSheetNo設定.
	 * @param orderSheetNo orderSheetNo
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * orderDate取得.
	 * @return orderDate
	 */
	public java.sql.Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * orderDate設定.
	 * @param orderDate orderDate
	 */
	public void setOrderDate(final java.sql.Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * venderName取得.
	 * @return venderName
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * venderName設定.
	 * @param venderName venderName
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * orderCount取得.
	 * @return orderCount
	 */
	public java.math.BigDecimal getOrderCount() {
		return this.orderCount;
	}

	/**
	 * orderCount設定.
	 * @param orderCount orderCount
	 */
	public void setOrderCount(final java.math.BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * issuedCount取得.
	 * @return issuedCount
	 */
	public java.math.BigDecimal getIssuedCount() {
		return this.issuedCount;
	}

	/**
	 * issuedCount設定.
	 * @param issuedCount issuedCount
	 */
	public void setIssuedCount(final java.math.BigDecimal issuedCount) {
		this.issuedCount = issuedCount;
	}

	/**
	 * adjustCount取得.
	 * @return adjustCount
	 */
	public java.math.BigDecimal getAdjustCount() {
		return this.adjustCount;
	}

	/**
	 * adjustCount設定.
	 * @param adjustCount adjustCount
	 */
	public void setAdjustCount(final java.math.BigDecimal adjustCount) {
		this.adjustCount = adjustCount;
	}

	/**
	 * fixedCount取得.
	 * @return fixedCount
	 */
	public java.math.BigDecimal getFixedCount() {
		return this.fixedCount;
	}

	/**
	 * fixedCount設定.
	 * @param fixedCount fixedCount
	 */
	public void setFixedCount(final java.math.BigDecimal fixedCount) {
		this.fixedCount = fixedCount;
	}

	/**
	 * arrivedAcceptedCount取得.
	 * @return arrivedAcceptedCount
	 */
	public java.math.BigDecimal getArrivedAcceptedCount() {
		return this.arrivedAcceptedCount;
	}

	/**
	 * arrivedAcceptedCount設定.
	 * @param arrivedAcceptedCount arrivedAcceptedCount
	 */
	public void setArrivedAcceptedCount(final java.math.BigDecimal arrivedAcceptedCount) {
		this.arrivedAcceptedCount = arrivedAcceptedCount;
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

