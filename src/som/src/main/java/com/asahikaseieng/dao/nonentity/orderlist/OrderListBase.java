/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderListクラス.
 * @author kanri-user
 */
public class OrderListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション orderDivisionName */
	public static final String orderDivisionName_COLUMN = "ORDER_DIVISION_NAME";

	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション shortDeliveryName */
	public static final String shortDeliveryName_COLUMN = "SHORT_DELIVERY_NAME";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション shortVenderName */
	public static final String shortVenderName_COLUMN = "SHORT_VENDER_NAME";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション shortItemName */
	public static final String shortItemName_COLUMN = "SHORT_ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	//
	// インスタンスフィールド
	//

	private String orderNo;

	private java.math.BigDecimal rowNo;

	private java.math.BigDecimal orderDivision;

	private String orderDivisionName;

	private String deliveryName;

	private String shortDeliveryName;

	private String venderName;

	private String shortVenderName;

	private String itemName;

	private String shortItemName;

	private String styleOfPacking;

	private java.sql.Timestamp orderDate;

	private String statusName;

	//
	// インスタンスメソッド
	//

	/**
	 * orderNo取得.
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * orderNo設定.
	 * @param orderNo orderNo
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * rowNo取得.
	 * @return rowNo
	 */
	public java.math.BigDecimal getRowNo() {
		return this.rowNo;
	}

	/**
	 * rowNo設定.
	 * @param rowNo rowNo
	 */
	public void setRowNo(final java.math.BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * orderDivision取得.
	 * @return orderDivision
	 */
	public java.math.BigDecimal getOrderDivision() {
		return this.orderDivision;
	}

	/**
	 * orderDivision設定.
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(final java.math.BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}

	/**
	 * orderDivisionName取得.
	 * @return orderDivisionName
	 */
	public String getOrderDivisionName() {
		return this.orderDivisionName;
	}

	/**
	 * orderDivisionName設定.
	 * @param orderDivisionName orderDivisionName
	 */
	public void setOrderDivisionName(final String orderDivisionName) {
		this.orderDivisionName = orderDivisionName;
	}

	/**
	 * deliveryName取得.
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return this.deliveryName;
	}

	/**
	 * deliveryName設定.
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(final String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * shortDeliveryName取得.
	 * @return shortDeliveryName
	 */
	public String getShortDeliveryName() {
		return this.shortDeliveryName;
	}

	/**
	 * shortDeliveryName設定.
	 * @param shortDeliveryName shortDeliveryName
	 */
	public void setShortDeliveryName(final String shortDeliveryName) {
		this.shortDeliveryName = shortDeliveryName;
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
	 * shortVenderName取得.
	 * @return shortVenderName
	 */
	public String getShortVenderName() {
		return this.shortVenderName;
	}

	/**
	 * shortVenderName設定.
	 * @param shortVenderName shortVenderName
	 */
	public void setShortVenderName(final String shortVenderName) {
		this.shortVenderName = shortVenderName;
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
	 * shortItemName取得.
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return this.shortItemName;
	}

	/**
	 * shortItemName設定.
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
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
	 * statusName取得.
	 * @return statusName
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * statusName設定.
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
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

