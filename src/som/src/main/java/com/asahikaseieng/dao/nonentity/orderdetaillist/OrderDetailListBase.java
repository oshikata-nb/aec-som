/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetaillist;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderDetailListクラス.
 * @author kanri-user
 */
public class OrderDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";

	/** COLUMNアノテーション standardUnitprice */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";

	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";

	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";

	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション estimateStandardAmount. */
	public static final String estimateStandardAmount_COLUMN = "ESTIMATE_STANDARD_AMOUNT";

	/** COLUMNアノテーション estimateMatss. */
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション purchaseStatus */
	public static final String purchaseStatus_COLUMN = "PURCHASE_STATUS";

	/** COLUMNアノテーション aspStatus */
	public static final String aspStatus_COLUMN = "ASP_STATUS";

	/** COLUMNアノテーション statusCd */
	public static final String statusCd_COLUMN = "STATUS_CD";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	
	/** COLUMNアノテーション orderNoForOI */
	public static final String orderNoForOI_COLUMN = "ORDER_NO";
	//
	// インスタンスフィールド
	//

	private java.math.BigDecimal orderDivision;

	private String rowNo;

	private String itemCd;

	private java.math.BigDecimal version;

	private String otherCompanyCd1;

	private String styleOfPacking;

	private String itemName;

	private String unitOfOperationManagement;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal orderUnitprice;

	private java.math.BigDecimal standardUnitprice;

	private java.math.BigDecimal standardDiscount;

	private java.math.BigDecimal specialDiscount;

	private java.math.BigDecimal orderAmount;

	private java.math.BigDecimal tmpUnitpriceFlg;

	private java.math.BigDecimal matss;

	private java.math.BigDecimal shippingStatus;

	private java.math.BigDecimal purchaseStatus;

	private String aspStatus;

	private String statusCd;

	private String statusName;

	private java.sql.Timestamp updateDate;

	private java.math.BigDecimal estimateStandardAmount;

	private java.math.BigDecimal estimateMatss;
	
	private String orderNoForOI;


	//
	// インスタンスメソッド
	//

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
	 * itemName取得.
	 * @return itemName
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * unitOfOperationManagementを取得します。
	 * @return unitOfOperationManagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}

	/**
	 * unitOfOperationManagementを設定します。
	 * @param unitOfOperationManagement unitOfOperationManagement
	 */
	public void setUnitOfOperationManagement(
			final String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * itemName設定.
	 * @param itemName itemName
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * orderQty取得.
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * orderUnitprice取得.
	 * @return orderUnitprice
	 */
	public java.math.BigDecimal getOrderUnitprice() {
		return this.orderUnitprice;
	}

	/**
	 * orderUnitprice設定.
	 * @param orderUnitprice orderUnitprice
	 */
	public void setOrderUnitprice(final java.math.BigDecimal orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}

	/**
	 * standardUnitprice取得.
	 * @return standardUnitprice
	 */
	public java.math.BigDecimal getStandardUnitprice() {
		return this.standardUnitprice;
	}

	/**
	 * standardUnitprice設定.
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(final java.math.BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * standardDiscount取得.
	 * @return standardDiscount
	 */
	public java.math.BigDecimal getStandardDiscount() {
		return this.standardDiscount;
	}

	/**
	 * standardDiscount設定.
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(final java.math.BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscount取得.
	 * @return specialDiscount
	 */
	public java.math.BigDecimal getSpecialDiscount() {
		return this.specialDiscount;
	}

	/**
	 * specialDiscount設定.
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(final java.math.BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * orderAmount取得.
	 * @return orderAmount
	 */
	public java.math.BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	/**
	 * orderAmount設定.
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(final java.math.BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * tmpUnitpriceFlg取得.
	 * @return tmpUnitpriceFlg
	 */
	public java.math.BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlg設定.
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(final java.math.BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * matss取得.
	 * @return matss
	 */
	public java.math.BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * matss設定.
	 * @param matss matss
	 */
	public void setMatss(final java.math.BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * shippingStatus取得.
	 * @return shippingStatus
	 */
	public java.math.BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * shippingStatus設定.
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(final java.math.BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * statusCd取得.
	 * @return statusCd
	 */
	public String getStatusCd() {
		return this.statusCd;
	}

	/**
	 * statusCd設定.
	 * @param statusCd statusCd
	 */
	public void setStatusCd(final String statusCd) {
		this.statusCd = statusCd;
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

	/**
	 * aspStatusを取得します。
	 * @return aspStatus
	 */
	public String getAspStatus() {
		return aspStatus;
	}

	/**
	 * aspStatusを設定します。
	 * @param aspStatus aspStatus
	 */
	public void setAspStatus(final String aspStatus) {
		this.aspStatus = aspStatus;
	}

	/**
	 * purchaseStatusを取得します。
	 * @return purchaseStatus
	 */
	public java.math.BigDecimal getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * purchaseStatusを設定します。
	 * @param purchaseStatus purchaseStatus
	 */
	public void setPurchaseStatus(final java.math.BigDecimal purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public String getRowNo() {
		return rowNo;
	}

	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(final String rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * versionを取得します。
	 * @return version
	 */
	public java.math.BigDecimal getVersion() {
		return version;
	}

	/**
	 * versionを設定します。
	 * @param version version
	 */
	public void setVersion(final java.math.BigDecimal version) {
		this.version = version;
	}

	/**
	 * estimateStandardAmount取得.
	 * @return estimateStandardAmount
	 */
	public java.math.BigDecimal getEstimateStandardAmount() {
		return this.estimateStandardAmount;
	}

	/**
	 * estimateStandardAmount設定.
	 * @param estimateStandardAmount estimateStandardAmount
	 */
	public void setEstimateStandardAmount(final java.math.BigDecimal estimateStandardAmount) {
		this.estimateStandardAmount = estimateStandardAmount;
	}

	/**
	 * estimateMatss取得.
	 * @return estimateMatss
	 */
	public java.math.BigDecimal getEstimateMatss() {
		return this.estimateMatss;
	}

	/**
	 * estimateMatss設定.
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(final java.math.BigDecimal estimateMatss) {
		this.estimateMatss = estimateMatss;
	}

	/**
	 * orderNoForOIを取得します。
	 * @return orderNoForOI
	 */
	public String getOrderNoForOI() {
		return orderNoForOI;
	}

	/**
	 * orderNoForOIを設定します。
	 * @param orderNoForOI orderNoForOI
	 */
	public void setOrderNoForOI(String orderNoForOI) {
		this.orderNoForOI = orderNoForOI;
	}
	
}

