/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * OrderImportListクラス.
 * @author
 */
public class OrderImportListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション rowNum */
	public static final String rowNum_COLUMN = "I_ROW_NUM";
	/** COLUMNアノテーション rank */
	public static final String rank_COLUMN = "I_RANK";
	/** COLUMNアノテーション frstOrderNo */
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション frstOrderRowNo */
	public static final String frstOrderRowNo_COLUMN = "FRST_ORDER_ROW_NO";
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	/** COLUMNアノテーション frstOrderRowNo */
	public static final String rowNo_COLUMN = "ROW_NO";
	/** COLUMNアノテーション ctmOrderNo */
	public static final String ctmOrderNo_COLUMN = "CUSTOMER_ORDER_DETAIL_NO";
	/** COLUMNアノテーション deliveryShortedName */
	public static final String deliveryShortedName_COLUMN = "DELIVERY_SHORTED_NAME";
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";
	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";
	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	/** COLUMNアノテーション orderStatusName */
	public static final String orderStatusName_COLUMN = "ORDER_STATUS_NAME";
	/** COLUMNアノテーション importStatusName */
	public static final String importStatusName_COLUMN = "IMPORT_STATUS_NAME";
	/** COLUMNアノテーション errorStatus */
	public static final String errorStatus_COLUMN = "ERROR_STATUS";
	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";
	/** COLUMNアノテーション  matss */
	public static final String matss_COLUMN = "MATSS";
	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";
	/** COLUMNアノテーション ctmRemark01 */
	public static final String ctmRemark01_COLUMN = "CTM_REMARK_01";
	/** COLUMNアノテーション ctmRemark02 */
	public static final String ctmRemark02_COLUMN = "CTM_REMARK_02";
	/** COLUMNアノテーション ctmRemark03 */
	public static final String ctmRemark03_COLUMN = "CTM_REMARK_03";
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";
	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";
	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";
	// 20210909 Asclab Saita 納期連絡表専用備考追加対応
	/** COLUMNアノテーション orderSummery */
	public static final String deliverydateContactSummery_COLUMN = "DELIVERYDATE_CONTACT_SUMMERY";
	/** COLUMNアノテーション unitOfOperationmanagement */
	public static final String unitOfOperationmanagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";
	/** COLUMNアノテーション inputApprovaled */
	public static final String inputApprovaled_COLUMN = "INPUT_APPROVALED";
	/** COLUMNアノテーション delDateApprovaled */
	public static final String delDateApprovaled_COLUMN = "DEL_DATE_APPROVALED";
	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";
	/** COLUMNアノテーション orderStatus */
	public static final String orderStatus_COLUMN = "ORDER_STATUS";
	/** COLUMNアノテーション ctmRemark */
	public static final String ctmRemark_COLUMN = "CTM_REMARK";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";
	/** COLUMNアノテーション purchaseStatus */
	public static final String purchaseStatus_COLUMN = "PURCHASE_STATUS";
	/** COLUMNアノテーション aspStatus */
	public static final String aspStatus_COLUMN = "ASP_STATUS";

	/** COLUMNアノテーション orderFaxOutput */
	public static final String orderFaxOutput_COLUMN = "ORDER_FAX_OUTPUT";
	/** COLUMNアノテーション orderMailOutput */
	public static final String orderMailOutput_COLUMN = "ORDER_MAIL_OUTPUT";
	//20220512 add S.Fujimaki
	/** COLUMNアノテーション orderDetailDate */
	public static final String orderDetailDate_COLUMN = "ORDER_DETAIL_DATE";
    //20220512 add S.Fujimaki end 

	// インスタンスフィールド
	//
	private String rowNum;
	private String rank;
	private String frstOrderNo;
	private BigDecimal frstOrderRowNo;
	private String orderNo;
	private BigDecimal rowNo;
	private String ctmOrderNo;
	private String deliveryShortedName;
	private String venderCd;
	private String venderName;
	private String itemCd;
	private String itemName;
	private Timestamp scheduledShippingDate;
	private Timestamp deliveryExpectedDate;
	private String orderStatusName;
	private String importStatusName;
	private String errorStatusName;
	private BigDecimal orderQty;
	private BigDecimal matss;
	private BigDecimal inventoryQty;
	private String ctmRemark01;
	private String ctmRemark02;
	private String ctmRemark03;
	private String printSummery;
	private String deliverySlipSummery;
	private String orderSummery;
	// 20210909 Asclab Saita 納期連絡表専用備考追加対応
	private String deliverydateContactSummery;
	private String unitOfOperationManagement;
	private BigDecimal inputApprovaled;
	private BigDecimal delDateApprovaled;
	private BigDecimal orderDivision;
	private BigDecimal orderStatus;
	private String ctmRemark;
	private BigDecimal shippingStatus;
	private BigDecimal purchaseStatus;
	private String aspStatus;
	private String orderFaxOutput;
	private String orderMailOutput;
	//20220512 add S.Fujimaki
	private Timestamp orderDetailDate;
	//20220512 end S.Fujimaki

	/**
	 * orderStatusを取得します。
	 * @return orderStatus
	 */
	public BigDecimal getOrderStatus() {
		return orderStatus;
	}
	/**
	 * orderStatusを設定します。
	 * @param orderStatus orderStatus
	 */
	public void setOrderStatus(BigDecimal orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * rankを取得します。
	 * @return rank
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * rankを設定します。
	 * @param rank rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}
	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}
	/**
	 * frstOrderRowNoを取得します。
	 * @return frstOrderRowNo
	 */
	public BigDecimal getFrstOrderRowNo() {
		return frstOrderRowNo;
	}
	/**
	 * frstOrderRowNoを設定します。
	 * @param frstOrderRowNo frstOrderRowNo
	 */
	public void setFrstOrderRowNo(BigDecimal frstOrderRowNo) {
		this.frstOrderRowNo = frstOrderRowNo;
	}
	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}
	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(BigDecimal rowNo) {
		this.rowNo = rowNo;
	}
	/**
	 * ctmOrderNoを取得します。
	 * @return ctmOrderNo
	 */
	public String getCtmOrderNo() {
		return ctmOrderNo;
	}
	/**
	 * ctmOrderNoを設定します。
	 * @param ctmOrderNo ctmOrderNo
	 */
	public void setCtmOrderNo(String ctmOrderNo) {
		this.ctmOrderNo = ctmOrderNo;
	}
	/**
	 * deliveryShortedNameを取得します。
	 * @return deliveryShortedName
	 */
	public String getDeliveryShortedName() {
		return deliveryShortedName;
	}
	/**
	 * deliveryShortedNameを設定します。
	 * @param deliveryShortedName deliveryShortedName
	 */
	public void setDeliveryShortedName(String deliveryShortedName) {
		this.deliveryShortedName = deliveryShortedName;
	}
	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}
	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}
	/**
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}
	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * scheduledShippingDateを取得します。
	 * @return scheduledShippingDate
	 */
	public Timestamp getScheduledShippingDate() {
		return scheduledShippingDate;
	}
	/**
	 * scheduledShippingDateを設定します。
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}
	/**
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public Timestamp getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}
	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}
	/**
	 * orderStatusNameを取得します。
	 * @return orderStatusName
	 */
	public String getOrderStatusName() {
		return orderStatusName;
	}
	/**
	 * orderStatusNameを設定します。
	 * @param orderStatusName orderStatusName
	 */
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	/**
	 * importStatusNameを取得します。
	 * @return importStatusName
	 */
	public String getImportStatusName() {
		return importStatusName;
	}
	/**
	 * importStatusNameを設定します。
	 * @param importStatusName importStatusName
	 */
	public void setImportStatusName(String importStatusName) {
		this.importStatusName = importStatusName;
	}
	/**
	 * errorStatusを取得します。
	 * @return errorStatus
	 */
	public String getErrorStatusName() {
		return errorStatusName;
	}
	/**
	 * errorStatusを設定します。
	 * @param errorStatus errorStatus
	 */
	public void setErrorStatusName(String errorStatusName) {
		this.errorStatusName = errorStatusName;
	}
	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public BigDecimal getOrderQty() {
		return orderQty;
	}
	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}
	/**
	 * matssを取得します。
	 * @return matss
	 */
	public BigDecimal getMatss() {
		return matss;
	}
	/**
	 * matssを設定します。
	 * @param matss matss
	 */
	public void setMatss(BigDecimal matss) {
		this.matss = matss;
	}
	/**
	 * inventoryQtyを取得します。
	 * @return inventoryQty
	 */
	public BigDecimal getInventoryQty() {
		return inventoryQty;
	}
	/**
	 * inventoryQtyを設定します。
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}
	/**
	 * ctmRemark01を取得します。
	 * @return ctmRemark01
	 */
	public String getCtmRemark01() {
		return ctmRemark01;
	}
	/**
	 * ctmRemark01を設定します。
	 * @param ctmRemark01 ctmRemark01
	 */
	public void setCtmRemark01(String ctmRemark01) {
		this.ctmRemark01 = ctmRemark01;
	}
	/**
	 * ctmRemark02を取得します。
	 * @return ctmRemark02
	 */
	public String getCtmRemark02() {
		return ctmRemark02;
	}
	/**
	 * ctmRemark02を設定します。
	 * @param ctmRemark02 ctmRemark02
	 */
	public void setCtmRemark02(String ctmRemark02) {
		this.ctmRemark02 = ctmRemark02;
	}
	/**
	 * ctmRemark03を取得します。
	 * @return ctmRemark03
	 */
	public String getCtmRemark03() {
		return ctmRemark03;
	}
	/**
	 * ctmRemark03を設定します。
	 * @param ctmRemark03 ctmRemark03
	 */
	public void setCtmRemark03(String ctmRemark03) {
		this.ctmRemark03 = ctmRemark03;
	}
	/**
	 * printSummeryを取得します。
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}
	/**
	 * printSummeryを設定します。
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
	}
	/**
	 * deliverySlipSummeryを取得します。
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return deliverySlipSummery;
	}
	/**
	 * deliverySlipSummeryを設定します。
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}
	/**
	 * orderSummeryを取得します。
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return orderSummery;
	}
	/**
	 * orderSummeryを設定します。
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(String orderSummery) {
		this.orderSummery = orderSummery;
	}
	// 20210909 Asclab Saita 納期連絡表専用備考追加対応
	/**
	 * deliverydateContactSummeryを取得します。
	 * @return deliverydateContactSummery
	 */
	public String getDeliverydateContactSummery() {
		return deliverydateContactSummery;
	}
	/**
	 * deliverydateContactSummeryを設定します。
	 * @param deliverydateContactSummery deliverydateContactSummery
	 */
	public void setDeliverydateContactSummery(String deliverydateContactSummery) {
		this.deliverydateContactSummery = deliverydateContactSummery;
	}
	/**
	 * unitOfOperationmanagementを取得します。
	 * @return unitOfOperationmanagement
	 */
	public String getUnitOfOperationManagement() {
		return unitOfOperationManagement;
	}
	/**
	 * unitOfOperationmanagementを設定します。
	 * @param unitOfOperationmanagement unitOfOperationmanagement
	 */
	public void setUnitOfOperationManagement(String unitOfOperationmanagement) {
		this.unitOfOperationManagement = unitOfOperationmanagement;
	}
	/**
	 * inputApprovaledを取得します。
	 * @return inputApprovaled
	 */
	public BigDecimal getInputApprovaled() {
		return inputApprovaled;
	}
	/**
	 * inputApprovaledを設定します。
	 * @param inputApprovaled inputApprovaled
	 */
	public void setInputApprovaled(BigDecimal inputApprovaled) {
		this.inputApprovaled = inputApprovaled;
	}
	/**
	 * delDateApprovaledを取得します。
	 * @return delDateApprovaled
	 */
	public BigDecimal getDelDateApprovaled() {
		return delDateApprovaled;
	}
	/**
	 * delDateApprovaledを設定します。
	 * @param delDateApprovaled delDateApprovaled
	 */
	public void setDelDateApprovaled(BigDecimal delDateApprovaled) {
		this.delDateApprovaled = delDateApprovaled;
	}
	/**
	 * orderDivisionを取得します。
	 * @return orderDivision
	 */
	public BigDecimal getOrderDivision() {
		return orderDivision;
	}
	/**
	 * orderDivisionを設定します。
	 * @param orderDivision orderDivision
	 */
	public void setOrderDivision(BigDecimal orderDivision) {
		this.orderDivision = orderDivision;
	}
	/**
	 * ctmRemarkを取得します。
	 * @return ctmRemark
	 */
	public String getCtmRemark() {
		return ctmRemark;
	}
	/**
	 * ctmRemarkを設定します。
	 * @param ctmRemark ctmRemark
	 */
	public void setCtmRemark(String ctmRemark) {
		this.ctmRemark = ctmRemark;
	}
	/**
	 * rowNumを取得します。
	 * @return rowNum
	 */
	public String getRowNum() {
		return rowNum;
	}
	/**
	 * rowNumを設定します。
	 * @param rowNum rowNum
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * shippingStatusを取得します。
	 * @return shippingStatus
	 */
	public BigDecimal getShippingStatus() {
		return shippingStatus;
	}
	/**
	 * shippingStatusを設定します。
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	/**
	 * purchaseStatusを取得します。
	 * @return purchaseStatus
	 */
	public BigDecimal getPurchaseStatus() {
		return purchaseStatus;
	}
	/**
	 * purchaseStatusを設定します。
	 * @param purchaseStatus purchaseStatus
	 */
	public void setPurchaseStatus(BigDecimal purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
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
	public void setAspStatus(String aspStatus) {
		this.aspStatus = aspStatus;
	}
	/**
	 * orderFaxOutputを取得します。
	 * @return orderFaxOutput
	 */
	public String getOrderFaxOutput() {
		return orderFaxOutput;
	}
	/**
	 * orderFaxOutputを設定します。
	 * @param orderFaxOutput orderFaxOutput
	 */
	public void setOrderFaxOutput(String orderFaxOutput) {
		this.orderFaxOutput = orderFaxOutput;
	}
	/**
	 * orderMailOutputを取得します。
	 * @return orderMailOutput
	 */
	public String getOrderMailOutput() {
		return orderMailOutput;
	}
	/**
	 * orderMailOutputを設定します。
	 * @param orderMailOutput orderMailOutput
	 */
	public void setOrderMailOutput(String orderMailOutput) {
		this.orderMailOutput = orderMailOutput;
	}
    //20220512 add S.Fujimaki

	/**
	 * orderDetailDateを取得します。
	 * @return orderDetailDate
	 */
	public Timestamp getOrderDetailDate() {
		return orderDetailDate;
	}
	/**
	 * orderDetailDateを設定します。
	 * @param orderDetailDate orderDetailDate
	 */
	public void setOrderDetailDate(Timestamp orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}
    //20220512 add S.Fujimaki




	//
	// インスタンスメソッド
	//

}

