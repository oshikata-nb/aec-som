/*
 * Created on 2009/09/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.productionforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ProductionHeaderListForReportクラス.
 * @author kanri-user
 */
public class ProductionHeaderListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ProductionHeaderListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション shipperDivision */
	public static final String shipperDivision_COLUMN = "SHIPPER_DIVISION";

	/** COLUMNアノテーション typeDivision */
	public static final String typeDivision_COLUMN = "TYPE_DIVISION";

	/** COLUMNアノテーション productionLineName */
	public static final String productionLineName_COLUMN = "PRODUCTION_LINE_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション orderLet */
	public static final String orderLet_COLUMN = "ORDER_LET";

	/** COLUMNアノテーション orderExpectQty */
	public static final String orderExpectQty_COLUMN = "ORDER_EXPECT_QTY";

	/** COLUMNアノテーション orderAcceptQty */
	public static final String orderAcceptQty_COLUMN = "ORDER_ACCEPT_QTY";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	//
	// インスタンスフィールド
	//

	private String shipperDivision;

	private String typeDivision;

	private String productionLineName;

	private String itemCd;

	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	private java.sql.Timestamp orderLet;

	private java.math.BigDecimal orderExpectQty;

	private java.math.BigDecimal orderAcceptQty;

	private String unitOfOperationManagement;

	private String unitName;

	//
	// インスタンスメソッド
	//

	/**
	 * shipperDivision取得.
	 * @return shipperDivision
	 */
	public String getShipperDivision() {
		return this.shipperDivision;
	}

	/**
	 * shipperDivision設定.
	 * @param shipperDivision shipperDivision
	 */
	public void setShipperDivision(final String shipperDivision) {
		this.shipperDivision = shipperDivision;
	}

	/**
	 * typeDivision取得.
	 * @return typeDivision
	 */
	public String getTypeDivision() {
		return this.typeDivision;
	}

	/**
	 * typeDivision設定.
	 * @param typeDivision typeDivision
	 */
	public void setTypeDivision(final String typeDivision) {
		this.typeDivision = typeDivision;
	}

	/**
	 * productionLineName取得.
	 * @return productionLineName
	 */
	public String getProductionLineName() {
		return this.productionLineName;
	}

	/**
	 * productionLineName設定.
	 * @param productionLineName productionLineName
	 */
	public void setProductionLineName(final String productionLineName) {
		this.productionLineName = productionLineName;
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
	 * orderLet取得.
	 * @return orderLet
	 */
	public java.sql.Timestamp getOrderLet() {
		return this.orderLet;
	}

	/**
	 * orderLet設定.
	 * @param orderLet orderLet
	 */
	public void setOrderLet(final java.sql.Timestamp orderLet) {
		this.orderLet = orderLet;
	}

	/**
	 * orderExpectQty取得.
	 * @return orderExpectQty
	 */
	public java.math.BigDecimal getOrderExpectQty() {
		return this.orderExpectQty;
	}

	/**
	 * orderExpectQty設定.
	 * @param orderExpectQty orderExpectQty
	 */
	public void setOrderExpectQty(final java.math.BigDecimal orderExpectQty) {
		this.orderExpectQty = orderExpectQty;
	}

	/**
	 * orderAcceptQty取得.
	 * @return orderAcceptQty
	 */
	public java.math.BigDecimal getOrderAcceptQty() {
		return this.orderAcceptQty;
	}

	/**
	 * orderAcceptQty設定.
	 * @param orderAcceptQty orderAcceptQty
	 */
	public void setOrderAcceptQty(final java.math.BigDecimal orderAcceptQty) {
		this.orderAcceptQty = orderAcceptQty;
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
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * unitName取得.
	 * @return unitName
	 */
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * unitName設定.
	 * @param unitName unitName
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
	}

	/**
	 * 荷姿取得
	 * @return String 荷姿
	 */
	public String getStyleOfPacking() {
		return this.styleOfPacking;
	}

	/**
	 * 荷姿設定
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
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
