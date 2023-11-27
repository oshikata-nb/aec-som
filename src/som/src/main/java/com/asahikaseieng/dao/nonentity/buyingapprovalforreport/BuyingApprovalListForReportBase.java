/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingapprovalforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * BuyingApprovalListForReportクラス.
 * @author kanri-user
 */
public class BuyingApprovalListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyingApprovalListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション supplierOrdAmount */
	public static final String supplierOrdAmount_COLUMN = "SUPPLIER_ORD_AMOUNT";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション stockingDate */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション status2Name */
	public static final String status2Name_COLUMN = "STATUS2_NAME";

	/** COLUMNアノテーション approvalStatus */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション itemQueueName */
	public static final String itemQueueName_COLUMN = "ITEM_QUEUE_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション paymentInvoiceCd */
	public static final String paymentInvoiceCd_COLUMN = "PAYMENT_INVOICE_CD";

	/** COLUMNアノテーション venderPaymentName */
	public static final String venderPaymentName_COLUMN = "VENDER_PAYMENT_NAME";

	/** COLUMNアノテーション categoryName */
	public static final String categoryName_COLUMN = "CATEGORY_NAME";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	//
	// インスタンスフィールド
	//

	private String slipNo;

	private String purchaseNo;

	private String buySubcontractOrderNo;

	private String orderNo;

	private String venderCd;

	private java.math.BigDecimal orderQuantity;

	private java.math.BigDecimal orderConvertQuantity;

	private java.math.BigDecimal supplierOrdAmount;

	private String orderSheetNo;

	private java.sql.Timestamp stockingDate;

	private java.math.BigDecimal rowNo;

	private java.math.BigDecimal stockingQuantity;

	private java.math.BigDecimal housingUnitprice;

	private java.math.BigDecimal stockingAmount;

	private java.sql.Timestamp acceptDate;

	private java.math.BigDecimal status2;

	private String status2Name;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String itemQueueName;

	private String styleOfPacking;

	private String unitOfOperationManagement;

	private String venderName1;

	private String paymentInvoiceCd;

	private String venderPaymentName;

	private String categoryName;

	private String itemCd;

	//
	// インスタンスメソッド
	//

	/**
	 * slipNo取得.
	 * @return slipNo
	 */
	public String getSlipNo() {
		return this.slipNo;
	}

	/**
	 * slipNo設定.
	 * @param slipNo slipNo
	 */
	public void setSlipNo(final String slipNo) {
		this.slipNo = slipNo;
	}

	/**
	 * purchaseNo取得.
	 * @return purchaseNo
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * purchaseNo設定.
	 * @param purchaseNo purchaseNo
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * buySubcontractOrderNo取得.
	 * @return buySubcontractOrderNo
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * buySubcontractOrderNo設定.
	 * @param buySubcontractOrderNo buySubcontractOrderNo
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

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
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * orderQuantity取得.
	 * @return orderQuantity
	 */
	public java.math.BigDecimal getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * orderQuantity設定.
	 * @param orderQuantity orderQuantity
	 */
	public void setOrderQuantity(final java.math.BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * orderConvertQuantity取得.
	 * @return orderConvertQuantity
	 */
	public java.math.BigDecimal getOrderConvertQuantity() {
		return this.orderConvertQuantity;
	}

	/**
	 * orderConvertQuantity設定.
	 * @param orderConvertQuantity orderConvertQuantity
	 */
	public void setOrderConvertQuantity(final java.math.BigDecimal orderConvertQuantity) {
		this.orderConvertQuantity = orderConvertQuantity;
	}

	/**
	 * supplierOrdAmount取得.
	 * @return supplierOrdAmount
	 */
	public java.math.BigDecimal getSupplierOrdAmount() {
		return this.supplierOrdAmount;
	}

	/**
	 * supplierOrdAmount設定.
	 * @param supplierOrdAmount supplierOrdAmount
	 */
	public void setSupplierOrdAmount(final java.math.BigDecimal supplierOrdAmount) {
		this.supplierOrdAmount = supplierOrdAmount;
	}

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
	 * stockingDate取得.
	 * @return stockingDate
	 */
	public java.sql.Timestamp getStockingDate() {
		return this.stockingDate;
	}

	/**
	 * stockingDate設定.
	 * @param stockingDate stockingDate
	 */
	public void setStockingDate(final java.sql.Timestamp stockingDate) {
		this.stockingDate = stockingDate;
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
	 * stockingQuantity取得.
	 * @return stockingQuantity
	 */
	public java.math.BigDecimal getStockingQuantity() {
		return this.stockingQuantity;
	}

	/**
	 * stockingQuantity設定.
	 * @param stockingQuantity stockingQuantity
	 */
	public void setStockingQuantity(final java.math.BigDecimal stockingQuantity) {
		this.stockingQuantity = stockingQuantity;
	}

	/**
	 * housingUnitprice取得.
	 * @return housingUnitprice
	 */
	public java.math.BigDecimal getHousingUnitprice() {
		return this.housingUnitprice;
	}

	/**
	 * housingUnitprice設定.
	 * @param housingUnitprice housingUnitprice
	 */
	public void setHousingUnitprice(final java.math.BigDecimal housingUnitprice) {
		this.housingUnitprice = housingUnitprice;
	}

	/**
	 * stockingAmount取得.
	 * @return stockingAmount
	 */
	public java.math.BigDecimal getStockingAmount() {
		return this.stockingAmount;
	}

	/**
	 * stockingAmount設定.
	 * @param stockingAmount stockingAmount
	 */
	public void setStockingAmount(final java.math.BigDecimal stockingAmount) {
		this.stockingAmount = stockingAmount;
	}

	/**
	 * acceptDate取得.
	 * @return acceptDate
	 */
	public java.sql.Timestamp getAcceptDate() {
		return this.acceptDate;
	}

	/**
	 * acceptDate設定.
	 * @param acceptDate acceptDate
	 */
	public void setAcceptDate(final java.sql.Timestamp acceptDate) {
		this.acceptDate = acceptDate;
	}

	/**
	 * status2取得.
	 * @return status2
	 */
	public java.math.BigDecimal getStatus2() {
		return this.status2;
	}

	/**
	 * status2設定.
	 * @param status2 status2
	 */
	public void setStatus2(final java.math.BigDecimal status2) {
		this.status2 = status2;
	}

	/**
	 * status2Name取得.
	 * @return status2Name
	 */
	public String getStatus2Name() {
		return this.status2Name;
	}

	/**
	 * status2Name設定.
	 * @param status2Name status2Name
	 */
	public void setStatus2Name(final String status2Name) {
		this.status2Name = status2Name;
	}

	/**
	 * approvalStatus取得.
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * approvalStatus設定.
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvedby取得.
	 * @return approvedby
	 */
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * approvedby設定.
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * approvaldate取得.
	 * @return approvaldate
	 */
	public java.sql.Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * approvaldate設定.
	 * @param approvaldate approvaldate
	 */
	public void setApprovaldate(final java.sql.Timestamp approvaldate) {
		this.approvaldate = approvaldate;
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
	 * itemQueueName取得.
	 * @return itemQueueName
	 */
	public String getItemQueueName() {
		return this.itemQueueName;
	}

	/**
	 * itemQueueName設定.
	 * @param itemQueueName itemQueueName
	 */
	public void setItemQueueName(final String itemQueueName) {
		this.itemQueueName = itemQueueName;
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
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * paymentInvoiceCd取得.
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return this.paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCd設定.
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * venderPaymentName取得.
	 * @return venderPaymentName
	 */
	public String getVenderPaymentName() {
		return this.venderPaymentName;
	}

	/**
	 * venderPaymentName設定.
	 * @param venderPaymentName venderPaymentName
	 */
	public void setVenderPaymentName(final String venderPaymentName) {
		this.venderPaymentName = venderPaymentName;
	}

	/**
	 * categoryName取得.
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * categoryName設定.
	 * @param categoryName categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
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

