/*
 * Created on 2020/11/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportlistforreport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderListForReportクラス.
 * @author kanri-user
 */
public class OrderImportListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportListForReportBase() {
	}

	// ヘッダ
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";
	public static final String orderNo_COLUMN = "ORDER_NO";
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";
	public static final String orderDivisionName_COLUMN = "ORDER_DIVISION_NAME";
	public static final String orderDate_COLUMN = "ORDER_DATE";
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";
	public static final String inputTantoName_COLUMN = "INPUT_TANTO_NAME";
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";
	public static final String salesTantoName_COLUMN = "SALES_TANTO_NAME";
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";
	public static final String venderGroupName_COLUMN = "VENDER_GROUP_NAME";
	public static final String venderCd_COLUMN = "VENDER_CD";
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";
	public static final String venderName1_COLUMN = "VENDER_NAME1";
	public static final String venderName2_COLUMN = "VENDER_NAME2";
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	public static final String searchKana_COLUMN = "SEARCH_KANA";
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";
	public static final String address_COLUMN = "ADDRESS";
	public static final String address1_COLUMN = "ADDRESS1";
	public static final String address2_COLUMN = "ADDRESS2";
	public static final String address3_COLUMN = "ADDRESS3";
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";
	public static final String balanceCd_COLUMN = "BALANCE_CD";
	public static final String status_COLUMN = "STATUS";
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";
	public static final String leadTime_COLUMN = "LEAD_TIME";
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";
	public static final String carryCd_COLUMN = "CARRY_CD";
	public static final String carryName1_COLUMN = "CARRY_NAME1";
	public static final String carryFare_COLUMN = "CARRY_FARE";
	public static final String carryInvoiceFlag_COLUMN = "CARRY_INVOICE_FLAG";
	public static final String orderPicture_COLUMN = "ORDER_PICTURE";
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";
	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	public static final String deliverydateContactSummery_COLUMN = "DELIVERYDATE_CONTACT_SUMMERY";
	public static final String totalWeight_COLUMN = "TOTAL_WEIGHT";
	public static final String delFlg_COLUMN = "DEL_FLG";
	public static final String cancelFlg_COLUMN = "CANCEL_FLG";
	public static final String invisibleFlg_COLUMN = "INVISIBLE_FLG";
	public static final String inputDate_COLUMN = "INPUT_DATE";
	public static final String inputorCd_COLUMN = "INPUTOR_CD";
	public static final String inputorName_COLUMN = "INPUTOR_NAME";
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	public static final String updatorCd_COLUMN = "UPDATOR_CD";
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	// 詳細
	public static final String frstOrderRowNo_COLUMN = "FRST_ORDER_ROW_NO";
	public static final String orderImpNo_COLUMN = "ORDER_IMP_NO";
	public static final String rowNo_COLUMN = "ROW_NO";
	public static final String shippingNo_COLUMN = "SHIPPING_NO";
	public static final String orderStatusName_COLUMN = "ORDER_STATUS_NAME";
	public static final String importStatusName_COLUMN = "IMPORT_STATUS_NAME";
	public static final String errorStatusName_COLUMN = "ERROR_STATUS_NAME";
	public static final String itemCd_COLUMN = "ITEM_CD";
	public static final String itemName_COLUMN = "ITEM_NAME";
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	public static final String orderQty_COLUMN = "ORDER_QTY";
	public static final String matss_COLUMN = "MATSS";
	public static final String weight_COLUMN = "WEIGHT";
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";
	public static final String estimateStandardAmount_COLUMN = "ESTIMATE_STANDARD_AMOUNT";
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";
	public static final String inputDivision_COLUMN = "INPUT_DIVISION";
	public static final String customerOrderDetailNo_COLUMN = "CUSTOMER_ORDER_DETAIL_NO";
	public static final String inputApprovalDate_COLUMN = "INPUT_APPROVAL_DATE";
	public static final String inputApproverCd_COLUMN = "INPUT_APPROVER_CD";
	public static final String inputApproverName_COLUMN = "INPUT_APPROVER_NAME";
	public static final String delDateSendDate_COLUMN = "DEL_DATE_SEND_DATE";
	public static final String delDateSenderCd_COLUMN = "DEL_DATE_SENDER_CD";
	public static final String delDateSenderName_COLUMN = "DEL_DATE_SENDER_NAME";
	public static final String deleteDate_COLUMN = "DELETE_DATE";
	public static final String cancelQty_COLUMN = "CANCEL_QTY";
	public static final String cancelDate_COLUMN = "CANCEL_DATE";
	public static final String errorFlg_COLUMN = "ERROR_FLG";
	public static final String importDate_COLUMN = "IMPORT_DATE";
	public static final String tempNo_COLUMN = "TEMP_NO";
	public static final String cellRowNumber_COLUMN = "CELL_ROW_NUMBER";
	public static final String cellColNumber_COLUMN = "CELL_COL_NUMBER";
	public static final String ctmOrderNo_COLUMN = "CTM_ORDER_NO";
	public static final String ctmOrderRow_COLUMN = "CTM_ORDER_ROW";
	public static final String ctmOrderDate_COLUMN = "CTM_ORDER_DATE";
	public static final String ctmDeliverlimit_COLUMN = "CTM_DELIVERLIMIT";
	public static final String ctmVenderCd01_COLUMN = "CTM_VENDER_CD_01";
	public static final String ctmVenderCd02_COLUMN = "CTM_VENDER_CD_02";
	public static final String ctmVenderCd03_COLUMN = "CTM_VENDER_CD_03";
	public static final String ctmVenderName01_COLUMN = "CTM_VENDER_NAME_01";
	public static final String ctmVenderName02_COLUMN = "CTM_VENDER_NAME_02";
	public static final String ctmVenderName03_COLUMN = "CTM_VENDER_NAME_03";
	public static final String ctmDeliveryCd01_COLUMN = "CTM_DELIVERY_CD_01";
	public static final String ctmDeliveryCd02_COLUMN = "CTM_DELIVERY_CD_02";
	public static final String ctmDeliveryCd03_COLUMN = "CTM_DELIVERY_CD_03";
	public static final String ctmDeliveryName01_COLUMN = "CTM_DELIVERY_NAME_01";
	public static final String ctmDeliveryName02_COLUMN = "CTM_DELIVERY_NAME_02";
	public static final String ctmDeliveryName03_COLUMN = "CTM_DELIVERY_NAME_03";
	public static final String ctmDeliveryAddress01_COLUMN = "CTM_DELIVERY_ADDRESS_01";
	public static final String ctmDeliveryAddress02_COLUMN = "CTM_DELIVERY_ADDRESS_02";
	public static final String ctmDeliveryAddress03_COLUMN = "CTM_DELIVERY_ADDRESS_03";
	public static final String ctmDeliveryTelNo_COLUMN = "CTM_DELIVERY_TEL_NO";
	public static final String ctmDeliveryFaxNo_COLUMN = "CTM_DELIVERY_FAX_NO";
	public static final String ctmDeliveryZipCd_COLUMN = "CTM_DELIVERY_ZIP_CD";
	public static final String ctmDeliveryAddress_COLUMN = "CTM_DELIVERY_ADDRESS";
	public static final String ctmItemCd01_COLUMN = "CTM_ITEM_CD_01";
	public static final String ctmItemCd02_COLUMN = "CTM_ITEM_CD_02";
	public static final String ctmItemCd03_COLUMN = "CTM_ITEM_CD_03";
	public static final String ctmJanCd_COLUMN = "CTM_JAN_CD";
	public static final String ctmItemName01_COLUMN = "CTM_ITEM_NAME_01";
	public static final String ctmItemName02_COLUMN = "CTM_ITEM_NAME_02";
	public static final String ctmItemName03_COLUMN = "CTM_ITEM_NAME_03";
	public static final String ctmStyleOfPacking_COLUMN = "CTM_STYLE_OF_PACKING";
	public static final String ctmOrderQty_COLUMN = "CTM_ORDER_QTY";
	public static final String ctmCaseNum_COLUMN = "CTM_CASE_NUM";
	public static final String ctmMatss_COLUMN = "CTM_MATSS";
	public static final String ctmOrderPiece_COLUMN = "CTM_ORDER_PIECE";
	public static final String ctmRemark01_COLUMN = "CTM_REMARK_01";
	public static final String ctmRemark02_COLUMN = "CTM_REMARK_02";
	public static final String ctmRemark03_COLUMN = "CTM_REMARK_03";
	public static final String ctmOrderUnitprice_COLUMN = "CTM_ORDER_UNITPRICE";
	public static final String ctmOrderAmount_COLUMN = "CTM_ORDER_AMOUNT";



	// ヘッダ
	private String frstOrderNo;
	private String orderNo;
	private BigDecimal orderDivision;
	private String orderDivisionName;
	private Timestamp orderDate;
	private String organizationCd;
	private String organizationName;
	private String inputTantoCd;
	private String inputTantoName;
	private String salesTantoCd;
	private String salesTantoName;
	private String venderGroupCd;
	private String venderGroupName;
	private String venderCd;
	private String venderShortedName;
	private String venderName1;
	private String venderName2;
	private String deliveryCd;
	private String searchKana;
	private String deliveryName1;
	private String deliveryName2;
	private String deliveryAddress;
	private String address;
	private String address1;
	private String address2;
	private String address3;
	private String deliveryTelNo;
	private String balanceCd;
	private BigDecimal status;
	private String customerOrderNo;
	private BigDecimal orderAmount;
	private Timestamp suggestedDeliverlimit;
	private Timestamp scheduledShippingDate;
	private BigDecimal leadTime;
	private Timestamp deliveryExpectedDate;
	private String deliveryExpectedTime;
	private String carryCd;
	private String carryName1;
	private BigDecimal carryFare;
	private BigDecimal carryInvoiceFlag;
	private String orderPicture;
	private String printSummery;
	private String deliverySlipSummery;
	private String orderSummery;
	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
	private String deliverydateContactSummery;
	private BigDecimal totalWeight;
	private BigDecimal delFlg;
	private BigDecimal cancelFlg;
	private BigDecimal invisibleFlg;
	private Timestamp inputDate;
	private String inputorCd;
	private String inputorName;
	private Timestamp updateDate;
	private String updatorCd;
	private String updatorName;

	//詳細
	private String frstOrderRowNo;
	private String orderImpNo;
	private BigDecimal rowNo;
	private String shippingNo;
	private String orderStatusName;
	private String importStatusName;
	private String errorStatusName;
	private String itemCd;
	private String itemName;
	private String styleOfPacking;
	private String otherCompanyCd1;
	private BigDecimal orderQty;
	private BigDecimal matss;
	private BigDecimal weight;
	private BigDecimal orderUnitprice;
	private BigDecimal standardUnitprice;
	private BigDecimal standardDiscount;
	private BigDecimal specialDiscount;
	private BigDecimal tmpUnitpriceFlg;
	private BigDecimal estimateStandardAmount;
	private BigDecimal estimateMatss;
	private BigDecimal inputDivision;
	private String customerOrderDetailNo;
	private Timestamp inputApprovalDate;
	private String inputApproverCd;
	private String inputApproverName;
	private Timestamp delDateSendDate;
	private String delDateSenderCd;
	private String delDateSenderName;
	private Timestamp deleteDate;
	private BigDecimal cancelQty;
	private Timestamp cancelDate;
	private BigDecimal errorFlg;
	private Timestamp importDate;
	private String tempNo;
	private BigDecimal cellRowNumber;
	private BigDecimal cellColNumber;
	private String ctmOrderNo;
	private BigDecimal ctmOrderRow;
	private Timestamp ctmOrderDate;
	private Timestamp ctmDeliverlimit;
	private String ctmVenderCd01;
	private String ctmVenderCd02;
	private String ctmVenderCd03;
	private String ctmVenderName01;
	private String ctmVenderName02;
	private String ctmVenderName03;
	private String ctmDeliveryCd01;
	private String ctmDeliveryCd02;
	private String ctmDeliveryCd03;
	private String ctmDeliveryName01;
	private String ctmDeliveryName02;
	private String ctmDeliveryName03;
	private String ctmDeliveryAddress01;
	private String ctmDeliveryAddress02;
	private String ctmDeliveryAddress03;
	private String ctmDeliveryTelNo;
	private String ctmDeliveryFaxNo;
	private String ctmDeliveryZipCd;
	private String ctmDeliveryAddress;
	private String ctmItemCd01;
	private String ctmItemCd02;
	private String ctmItemCd03;
	private String ctmJanCd;
	private String ctmItemName01;
	private String ctmItemName02;
	private String ctmItemName03;
	private String ctmStyleOfPacking;
	private BigDecimal ctmOrderQty;
	private BigDecimal ctmCaseNum;
	private BigDecimal ctmMatss;
	private BigDecimal ctmOrderPiece;
	private String ctmRemark01;
	private String ctmRemark02;
	private String ctmRemark03;
	private BigDecimal ctmOrderUnitprice;
	private BigDecimal ctmOrderAmount;


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
	 * orderDivisionNameを取得します。
	 * @return orderDivisionName
	 */
	public String getOrderDivisionName() {
		return orderDivisionName;
	}

	/**
	 * orderDivisionNameを設定します。
	 * @param orderDivisionName orderDivisionName
	 */
	public void setOrderDivisionName(String orderDivisionName) {
		this.orderDivisionName = orderDivisionName;
	}

	/**
	 * orderDateを取得します。
	 * @return orderDate
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * orderDateを設定します。
	 * @param orderDate orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * inputTantoCdを取得します。
	 * @return inputTantoCd
	 */
	public String getInputTantoCd() {
		return inputTantoCd;
	}

	/**
	 * inputTantoCdを設定します。
	 * @param inputTantoCd inputTantoCd
	 */
	public void setInputTantoCd(String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
	}

	/**
	 * inputTantoNameを取得します。
	 * @return inputTantoName
	 */
	public String getInputTantoName() {
		return inputTantoName;
	}

	/**
	 * inputTantoNameを設定します。
	 * @param inputTantoName inputTantoName
	 */
	public void setInputTantoName(String inputTantoName) {
		this.inputTantoName = inputTantoName;
	}

	/**
	 * salesTantoCdを取得します。
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return salesTantoCd;
	}

	/**
	 * salesTantoCdを設定します。
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * salesTantoNameを取得します。
	 * @return salesTantoName
	 */
	public String getSalesTantoName() {
		return salesTantoName;
	}

	/**
	 * salesTantoNameを設定します。
	 * @param salesTantoName salesTantoName
	 */
	public void setSalesTantoName(String salesTantoName) {
		this.salesTantoName = salesTantoName;
	}

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * venderGroupNameを取得します。
	 * @return venderGroupName
	 */
	public String getVenderGroupName() {
		return venderGroupName;
	}

	/**
	 * venderGroupNameを設定します。
	 * @param venderGroupName venderGroupName
	 */
	public void setVenderGroupName(String venderGroupName) {
		this.venderGroupName = venderGroupName;
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
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2を取得します。
	 * @return venderName2
	 */
	public String getVenderName2() {
		return venderName2;
	}

	/**
	 * venderName2を設定します。
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * deliveryCdを取得します。
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return deliveryCd;
	}

	/**
	 * deliveryCdを設定します。
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2を取得します。
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return deliveryName2;
	}

	/**
	 * deliveryName2を設定します。
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * deliveryAddressを取得します。
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * deliveryAddressを設定します。
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * address1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * address1を設定します。
	 * @param address1 address1
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * address2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * address2を設定します。
	 * @param address2 address2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * address3を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * address3を設定します。
	 * @param address3 address3
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	/**
	 * deliveryTelNoを取得します。
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return deliveryTelNo;
	}

	/**
	 * deliveryTelNoを設定します。
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public BigDecimal getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	/**
	 * customerOrderNoを取得します。
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	/**
	 * customerOrderNoを設定します。
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * orderAmountを取得します。
	 * @return orderAmount
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * orderAmountを設定します。
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * suggestedDeliverlimitを取得します。
	 * @return suggestedDeliverlimit
	 */
	public Timestamp getSuggestedDeliverlimit() {
		return suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimitを設定します。
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
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
	 * leadTimeを取得します。
	 * @return leadTime
	 */
	public BigDecimal getLeadTime() {
		return leadTime;
	}

	/**
	 * leadTimeを設定します。
	 * @param leadTime leadTime
	 */
	public void setLeadTime(BigDecimal leadTime) {
		this.leadTime = leadTime;
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
	 * deliveryExpectedTimeを取得します。
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTimeを設定します。
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * carryCdを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * carryCdを設定します。
	 * @param carryCd carryCd
	 */
	public void setCarryCd(String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1を取得します。
	 * @return carryName1
	 */
	public String getCarryName1() {
		return carryName1;
	}

	/**
	 * carryName1を設定します。
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public BigDecimal getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryInvoiceFlagを取得します。
	 * @return carryInvoiceFlag
	 */
	public BigDecimal getCarryInvoiceFlag() {
		return carryInvoiceFlag;
	}

	/**
	 * carryInvoiceFlagを設定します。
	 * @param carryInvoiceFlag carryInvoiceFlag
	 */
	public void setCarryInvoiceFlag(BigDecimal carryInvoiceFlag) {
		this.carryInvoiceFlag = carryInvoiceFlag;
	}

	/**
	 * orderPictureを取得します。
	 * @return orderPicture
	 */
	public String getOrderPicture() {
		return orderPicture;
	}

	/**
	 * orderPictureを設定します。
	 * @param orderPicture orderPicture
	 */
	public void setOrderPicture(String orderPicture) {
		this.orderPicture = orderPicture;
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

	// 20210906 Asclab Saita 納期連絡表専用備考追加対応
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
	 * totalWeightを取得します。
	 * @return totalWeight
	 */
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}

	/**
	 * totalWeightを設定します。
	 * @param totalWeight totalWeight
	 */
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	/**
	 * delFlgを取得します。
	 * @return delFlg
	 */
	public BigDecimal getDelFlg() {
		return delFlg;
	}

	/**
	 * delFlgを設定します。
	 * @param delFlg delFlg
	 */
	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * cancelFlgを取得します。
	 * @return cancelFlg
	 */
	public BigDecimal getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * cancelFlgを設定します。
	 * @param cancelFlg cancelFlg
	 */
	public void setCancelFlg(BigDecimal cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorNameを取得します。
	 * @return inputorName
	 */
	public String getInputorName() {
		return inputorName;
	}

	/**
	 * inputorNameを設定します。
	 * @param inputorName inputorName
	 */
	public void setInputorName(String inputorName) {
		this.inputorName = inputorName;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorNameを取得します。
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return updatorName;
	}

	/**
	 * updatorNameを設定します。
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * frstOrderRowNoを取得します。
	 * @return frstOrderRowNo
	 */
	public String getFrstOrderRowNo() {
		return frstOrderRowNo;
	}

	/**
	 * frstOrderRowNoを設定します。
	 * @param frstOrderRowNo frstOrderRowNo
	 */
	public void setFrstOrderRowNo(String frstOrderRowNo) {
		this.frstOrderRowNo = frstOrderRowNo;
	}

	/**
	 * orderImpNoを取得します。
	 * @return orderImpNo
	 */
	public String getOrderImpNo() {
		return orderImpNo;
	}

	/**
	 * orderImpNoを設定します。
	 * @param orderImpNo orderImpNo
	 */
	public void setOrderImpNo(String orderImpNo) {
		this.orderImpNo = orderImpNo;
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
	 * shippingNoを取得します。
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return shippingNo;
	}

	/**
	 * shippingNoを設定します。
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
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
	 * errorStatusNameを取得します。
	 * @return errorStatusName
	 */
	public String getErrorStatusName() {
		return errorStatusName;
	}

	/**
	 * errorStatusNameを設定します。
	 * @param errorStatusName errorStatusName
	 */
	public void setErrorStatusName(String errorStatusName) {
		this.errorStatusName = errorStatusName;
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
	 * weightを取得します。
	 * @return weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	/**
	 * weightを設定します。
	 * @param weight weight
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/**
	 * orderUnitpriceを取得します。
	 * @return orderUnitprice
	 */
	public BigDecimal getOrderUnitprice() {
		return orderUnitprice;
	}

	/**
	 * orderUnitpriceを設定します。
	 * @param orderUnitprice orderUnitprice
	 */
	public void setOrderUnitprice(BigDecimal orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}

	/**
	 * standardUnitpriceを取得します。
	 * @return standardUnitprice
	 */
	public BigDecimal getStandardUnitprice() {
		return standardUnitprice;
	}

	/**
	 * standardUnitpriceを設定します。
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * standardDiscountを取得します。
	 * @return standardDiscount
	 */
	public BigDecimal getStandardDiscount() {
		return standardDiscount;
	}

	/**
	 * standardDiscountを設定します。
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscountを取得します。
	 * @return specialDiscount
	 */
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * specialDiscountを設定します。
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * tmpUnitpriceFlgを取得します。
	 * @return tmpUnitpriceFlg
	 */
	public BigDecimal getTmpUnitpriceFlg() {
		return tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlgを設定します。
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * estimateStandardAmountを取得します。
	 * @return estimateStandardAmount
	 */
	public BigDecimal getEstimateStandardAmount() {
		return estimateStandardAmount;
	}

	/**
	 * estimateStandardAmountを設定します。
	 * @param estimateStandardAmount estimateStandardAmount
	 */
	public void setEstimateStandardAmount(BigDecimal estimateStandardAmount) {
		this.estimateStandardAmount = estimateStandardAmount;
	}

	/**
	 * estimateMatssを取得します。
	 * @return estimateMatss
	 */
	public BigDecimal getEstimateMatss() {
		return estimateMatss;
	}

	/**
	 * estimateMatssを設定します。
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(BigDecimal estimateMatss) {
		this.estimateMatss = estimateMatss;
	}

	/**
	 * inputDivisionを取得します。
	 * @return inputDivision
	 */
	public BigDecimal getInputDivision() {
		return inputDivision;
	}

	/**
	 * inputDivisionを設定します。
	 * @param inputDivision inputDivision
	 */
	public void setInputDivision(BigDecimal inputDivision) {
		this.inputDivision = inputDivision;
	}

	/**
	 * customerOrderDetailNoを取得します。
	 * @return customerOrderDetailNo
	 */
	public String getCustomerOrderDetailNo() {
		return customerOrderDetailNo;
	}

	/**
	 * customerOrderDetailNoを設定します。
	 * @param customerOrderDetailNo customerOrderDetailNo
	 */
	public void setCustomerOrderDetailNo(String customerOrderDetailNo) {
		this.customerOrderDetailNo = customerOrderDetailNo;
	}

	/**
	 * inputApprovalDateを取得します。
	 * @return inputApprovalDate
	 */
	public Timestamp getInputApprovalDate() {
		return inputApprovalDate;
	}

	/**
	 * inputApprovalDateを設定します。
	 * @param inputApprovalDate inputApprovalDate
	 */
	public void setInputApprovalDate(Timestamp inputApprovalDate) {
		this.inputApprovalDate = inputApprovalDate;
	}

	/**
	 * inputApproverCdを取得します。
	 * @return inputApproverCd
	 */
	public String getInputApproverCd() {
		return inputApproverCd;
	}

	/**
	 * inputApproverCdを設定します。
	 * @param inputApproverCd inputApproverCd
	 */
	public void setInputApproverCd(String inputApproverCd) {
		this.inputApproverCd = inputApproverCd;
	}

	/**
	 * inputApproverNameを取得します。
	 * @return inputApproverName
	 */
	public String getInputApproverName() {
		return inputApproverName;
	}

	/**
	 * inputApproverNameを設定します。
	 * @param inputApproverName inputApproverName
	 */
	public void setInputApproverName(String inputApproverName) {
		this.inputApproverName = inputApproverName;
	}

	/**
	 * delDateSendDateを取得します。
	 * @return delDateSendDate
	 */
	public Timestamp getDelDateSendDate() {
		return delDateSendDate;
	}

	/**
	 * delDateSendDateを設定します。
	 * @param delDateSendDate delDateSendDate
	 */
	public void setDelDateSendDate(Timestamp delDateSendDate) {
		this.delDateSendDate = delDateSendDate;
	}

	/**
	 * delDateSenderCdを取得します。
	 * @return delDateSenderCd
	 */
	public String getDelDateSenderCd() {
		return delDateSenderCd;
	}

	/**
	 * delDateSenderCdを設定します。
	 * @param delDateSenderCd delDateSenderCd
	 */
	public void setDelDateSenderCd(String delDateSenderCd) {
		this.delDateSenderCd = delDateSenderCd;
	}

	/**
	 * delDateSenderNameを取得します。
	 * @return delDateSenderName
	 */
	public String getDelDateSenderName() {
		return delDateSenderName;
	}

	/**
	 * delDateSenderNameを設定します。
	 * @param delDateSenderName delDateSenderName
	 */
	public void setDelDateSenderName(String delDateSenderName) {
		this.delDateSenderName = delDateSenderName;
	}

	/**
	 * deleteDateを取得します。
	 * @return deleteDate
	 */
	public Timestamp getDeleteDate() {
		return deleteDate;
	}

	/**
	 * deleteDateを設定します。
	 * @param deleteDate deleteDate
	 */
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * cancelQtyを取得します。
	 * @return cancelQty
	 */
	public BigDecimal getCancelQty() {
		return cancelQty;
	}

	/**
	 * cancelQtyを設定します。
	 * @param cancelQty cancelQty
	 */
	public void setCancelQty(BigDecimal cancelQty) {
		this.cancelQty = cancelQty;
	}

	/**
	 * cancelDateを取得します。
	 * @return cancelDate
	 */
	public Timestamp getCancelDate() {
		return cancelDate;
	}

	/**
	 * cancelDateを設定します。
	 * @param cancelDate cancelDate
	 */
	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}

	/**
	 * errorFlgを取得します。
	 * @return errorFlg
	 */
	public BigDecimal getErrorFlg() {
		return errorFlg;
	}

	/**
	 * errorFlgを設定します。
	 * @param errorFlg errorFlg
	 */
	public void setErrorFlg(BigDecimal errorFlg) {
		this.errorFlg = errorFlg;
	}

	/**
	 * importDateを取得します。
	 * @return importDate
	 */
	public Timestamp getImportDate() {
		return importDate;
	}

	/**
	 * importDateを設定します。
	 * @param importDate importDate
	 */
	public void setImportDate(Timestamp importDate) {
		this.importDate = importDate;
	}

	/**
	 * tempNoを取得します。
	 * @return tempNo
	 */
	public String getTempNo() {
		return tempNo;
	}

	/**
	 * tempNoを設定します。
	 * @param tempNo tempNo
	 */
	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}

	/**
	 * cellRowNumberを取得します。
	 * @return cellRowNumber
	 */
	public BigDecimal getCellRowNumber() {
		return cellRowNumber;
	}

	/**
	 * cellRowNumberを設定します。
	 * @param cellRowNumber cellRowNumber
	 */
	public void setCellRowNumber(BigDecimal cellRowNumber) {
		this.cellRowNumber = cellRowNumber;
	}

	/**
	 * cellColNumberを取得します。
	 * @return cellColNumber
	 */
	public BigDecimal getCellColNumber() {
		return cellColNumber;
	}

	/**
	 * cellColNumberを設定します。
	 * @param cellColNumber cellColNumber
	 */
	public void setCellColNumber(BigDecimal cellColNumber) {
		this.cellColNumber = cellColNumber;
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
	 * ctmOrderRowを取得します。
	 * @return ctmOrderRow
	 */
	public BigDecimal getCtmOrderRow() {
		return ctmOrderRow;
	}

	/**
	 * ctmOrderRowを設定します。
	 * @param ctmOrderRow ctmOrderRow
	 */
	public void setCtmOrderRow(BigDecimal ctmOrderRow) {
		this.ctmOrderRow = ctmOrderRow;
	}

	/**
	 * ctmOrderDateを取得します。
	 * @return ctmOrderDate
	 */
	public Timestamp getCtmOrderDate() {
		return ctmOrderDate;
	}

	/**
	 * ctmOrderDateを設定します。
	 * @param ctmOrderDate ctmOrderDate
	 */
	public void setCtmOrderDate(Timestamp ctmOrderDate) {
		this.ctmOrderDate = ctmOrderDate;
	}

	/**
	 * ctmDeliverlimitを取得します。
	 * @return ctmDeliverlimit
	 */
	public Timestamp getCtmDeliverlimit() {
		return ctmDeliverlimit;
	}

	/**
	 * ctmDeliverlimitを設定します。
	 * @param ctmDeliverlimit ctmDeliverlimit
	 */
	public void setCtmDeliverlimit(Timestamp ctmDeliverlimit) {
		this.ctmDeliverlimit = ctmDeliverlimit;
	}

	/**
	 * ctmVenderCd01を取得します。
	 * @return ctmVenderCd01
	 */
	public String getCtmVenderCd01() {
		return ctmVenderCd01;
	}

	/**
	 * ctmVenderCd01を設定します。
	 * @param ctmVenderCd01 ctmVenderCd01
	 */
	public void setCtmVenderCd01(String ctmVenderCd01) {
		this.ctmVenderCd01 = ctmVenderCd01;
	}

	/**
	 * ctmVenderCd02を取得します。
	 * @return ctmVenderCd02
	 */
	public String getCtmVenderCd02() {
		return ctmVenderCd02;
	}

	/**
	 * ctmVenderCd02を設定します。
	 * @param ctmVenderCd02 ctmVenderCd02
	 */
	public void setCtmVenderCd02(String ctmVenderCd02) {
		this.ctmVenderCd02 = ctmVenderCd02;
	}

	/**
	 * ctmVenderCd03を取得します。
	 * @return ctmVenderCd03
	 */
	public String getCtmVenderCd03() {
		return ctmVenderCd03;
	}

	/**
	 * ctmVenderCd03を設定します。
	 * @param ctmVenderCd03 ctmVenderCd03
	 */
	public void setCtmVenderCd03(String ctmVenderCd03) {
		this.ctmVenderCd03 = ctmVenderCd03;
	}

	/**
	 * ctmVenderName01を取得します。
	 * @return ctmVenderName01
	 */
	public String getCtmVenderName01() {
		return ctmVenderName01;
	}

	/**
	 * ctmVenderName01を設定します。
	 * @param ctmVenderName01 ctmVenderName01
	 */
	public void setCtmVenderName01(String ctmVenderName01) {
		this.ctmVenderName01 = ctmVenderName01;
	}

	/**
	 * ctmVenderName02を取得します。
	 * @return ctmVenderName02
	 */
	public String getCtmVenderName02() {
		return ctmVenderName02;
	}

	/**
	 * ctmVenderName02を設定します。
	 * @param ctmVenderName02 ctmVenderName02
	 */
	public void setCtmVenderName02(String ctmVenderName02) {
		this.ctmVenderName02 = ctmVenderName02;
	}

	/**
	 * ctmVenderName03を取得します。
	 * @return ctmVenderName03
	 */
	public String getCtmVenderName03() {
		return ctmVenderName03;
	}

	/**
	 * ctmVenderName03を設定します。
	 * @param ctmVenderName03 ctmVenderName03
	 */
	public void setCtmVenderName03(String ctmVenderName03) {
		this.ctmVenderName03 = ctmVenderName03;
	}

	/**
	 * ctmDeliveryCd01を取得します。
	 * @return ctmDeliveryCd01
	 */
	public String getCtmDeliveryCd01() {
		return ctmDeliveryCd01;
	}

	/**
	 * ctmDeliveryCd01を設定します。
	 * @param ctmDeliveryCd01 ctmDeliveryCd01
	 */
	public void setCtmDeliveryCd01(String ctmDeliveryCd01) {
		this.ctmDeliveryCd01 = ctmDeliveryCd01;
	}

	/**
	 * ctmDeliveryCd02を取得します。
	 * @return ctmDeliveryCd02
	 */
	public String getCtmDeliveryCd02() {
		return ctmDeliveryCd02;
	}

	/**
	 * ctmDeliveryCd02を設定します。
	 * @param ctmDeliveryCd02 ctmDeliveryCd02
	 */
	public void setCtmDeliveryCd02(String ctmDeliveryCd02) {
		this.ctmDeliveryCd02 = ctmDeliveryCd02;
	}

	/**
	 * ctmDeliveryCd03を取得します。
	 * @return ctmDeliveryCd03
	 */
	public String getCtmDeliveryCd03() {
		return ctmDeliveryCd03;
	}

	/**
	 * ctmDeliveryCd03を設定します。
	 * @param ctmDeliveryCd03 ctmDeliveryCd03
	 */
	public void setCtmDeliveryCd03(String ctmDeliveryCd03) {
		this.ctmDeliveryCd03 = ctmDeliveryCd03;
	}

	/**
	 * ctmDeliveryName01を取得します。
	 * @return ctmDeliveryName01
	 */
	public String getCtmDeliveryName01() {
		return ctmDeliveryName01;
	}

	/**
	 * ctmDeliveryName01を設定します。
	 * @param ctmDeliveryName01 ctmDeliveryName01
	 */
	public void setCtmDeliveryName01(String ctmDeliveryName01) {
		this.ctmDeliveryName01 = ctmDeliveryName01;
	}

	/**
	 * ctmDeliveryName02を取得します。
	 * @return ctmDeliveryName02
	 */
	public String getCtmDeliveryName02() {
		return ctmDeliveryName02;
	}

	/**
	 * ctmDeliveryName02を設定します。
	 * @param ctmDeliveryName02 ctmDeliveryName02
	 */
	public void setCtmDeliveryName02(String ctmDeliveryName02) {
		this.ctmDeliveryName02 = ctmDeliveryName02;
	}

	/**
	 * ctmDeliveryName03を取得します。
	 * @return ctmDeliveryName03
	 */
	public String getCtmDeliveryName03() {
		return ctmDeliveryName03;
	}

	/**
	 * ctmDeliveryName03を設定します。
	 * @param ctmDeliveryName03 ctmDeliveryName03
	 */
	public void setCtmDeliveryName03(String ctmDeliveryName03) {
		this.ctmDeliveryName03 = ctmDeliveryName03;
	}

	/**
	 * ctmDeliveryAddress01を取得します。
	 * @return ctmDeliveryAddress01
	 */
	public String getCtmDeliveryAddress01() {
		return ctmDeliveryAddress01;
	}

	/**
	 * ctmDeliveryAddress01を設定します。
	 * @param ctmDeliveryAddress01 ctmDeliveryAddress01
	 */
	public void setCtmDeliveryAddress01(String ctmDeliveryAddress01) {
		this.ctmDeliveryAddress01 = ctmDeliveryAddress01;
	}

	/**
	 * ctmDeliveryAddress02を取得します。
	 * @return ctmDeliveryAddress02
	 */
	public String getCtmDeliveryAddress02() {
		return ctmDeliveryAddress02;
	}

	/**
	 * ctmDeliveryAddress02を設定します。
	 * @param ctmDeliveryAddress02 ctmDeliveryAddress02
	 */
	public void setCtmDeliveryAddress02(String ctmDeliveryAddress02) {
		this.ctmDeliveryAddress02 = ctmDeliveryAddress02;
	}

	/**
	 * ctmDeliveryAddress03を取得します。
	 * @return ctmDeliveryAddress03
	 */
	public String getCtmDeliveryAddress03() {
		return ctmDeliveryAddress03;
	}

	/**
	 * ctmDeliveryAddress03を設定します。
	 * @param ctmDeliveryAddress03 ctmDeliveryAddress03
	 */
	public void setCtmDeliveryAddress03(String ctmDeliveryAddress03) {
		this.ctmDeliveryAddress03 = ctmDeliveryAddress03;
	}

	/**
	 * ctmDeliveryTelNoを取得します。
	 * @return ctmDeliveryTelNo
	 */
	public String getCtmDeliveryTelNo() {
		return ctmDeliveryTelNo;
	}

	/**
	 * ctmDeliveryTelNoを設定します。
	 * @param ctmDeliveryTelNo ctmDeliveryTelNo
	 */
	public void setCtmDeliveryTelNo(String ctmDeliveryTelNo) {
		this.ctmDeliveryTelNo = ctmDeliveryTelNo;
	}

	/**
	 * ctmDeliveryFaxNoを取得します。
	 * @return ctmDeliveryFaxNo
	 */
	public String getCtmDeliveryFaxNo() {
		return ctmDeliveryFaxNo;
	}

	/**
	 * ctmDeliveryFaxNoを設定します。
	 * @param ctmDeliveryFaxNo ctmDeliveryFaxNo
	 */
	public void setCtmDeliveryFaxNo(String ctmDeliveryFaxNo) {
		this.ctmDeliveryFaxNo = ctmDeliveryFaxNo;
	}

	/**
	 * ctmDeliveryZipCdを取得します。
	 * @return ctmDeliveryZipCd
	 */
	public String getCtmDeliveryZipCd() {
		return ctmDeliveryZipCd;
	}

	/**
	 * ctmDeliveryZipCdを設定します。
	 * @param ctmDeliveryZipCd ctmDeliveryZipCd
	 */
	public void setCtmDeliveryZipCd(String ctmDeliveryZipCd) {
		this.ctmDeliveryZipCd = ctmDeliveryZipCd;
	}

	/**
	 * ctmDeliveryAddressを取得します。
	 * @return ctmDeliveryAddress
	 */
	public String getCtmDeliveryAddress() {
		return ctmDeliveryAddress;
	}

	/**
	 * ctmDeliveryAddressを設定します。
	 * @param ctmDeliveryAddress ctmDeliveryAddress
	 */
	public void setCtmDeliveryAddress(String ctmDeliveryAddress) {
		this.ctmDeliveryAddress = ctmDeliveryAddress;
	}

	/**
	 * ctmItemCd01を取得します。
	 * @return ctmItemCd01
	 */
	public String getCtmItemCd01() {
		return ctmItemCd01;
	}

	/**
	 * ctmItemCd01を設定します。
	 * @param ctmItemCd01 ctmItemCd01
	 */
	public void setCtmItemCd01(String ctmItemCd01) {
		this.ctmItemCd01 = ctmItemCd01;
	}

	/**
	 * ctmItemCd02を取得します。
	 * @return ctmItemCd02
	 */
	public String getCtmItemCd02() {
		return ctmItemCd02;
	}

	/**
	 * ctmItemCd02を設定します。
	 * @param ctmItemCd02 ctmItemCd02
	 */
	public void setCtmItemCd02(String ctmItemCd02) {
		this.ctmItemCd02 = ctmItemCd02;
	}

	/**
	 * ctmItemCd03を取得します。
	 * @return ctmItemCd03
	 */
	public String getCtmItemCd03() {
		return ctmItemCd03;
	}

	/**
	 * ctmItemCd03を設定します。
	 * @param ctmItemCd03 ctmItemCd03
	 */
	public void setCtmItemCd03(String ctmItemCd03) {
		this.ctmItemCd03 = ctmItemCd03;
	}

	/**
	 * ctmJanCdを取得します。
	 * @return ctmJanCd
	 */
	public String getCtmJanCd() {
		return ctmJanCd;
	}

	/**
	 * ctmJanCdを設定します。
	 * @param ctmJanCd ctmJanCd
	 */
	public void setCtmJanCd(String ctmJanCd) {
		this.ctmJanCd = ctmJanCd;
	}

	/**
	 * ctmItemName01を取得します。
	 * @return ctmItemName01
	 */
	public String getCtmItemName01() {
		return ctmItemName01;
	}

	/**
	 * ctmItemName01を設定します。
	 * @param ctmItemName01 ctmItemName01
	 */
	public void setCtmItemName01(String ctmItemName01) {
		this.ctmItemName01 = ctmItemName01;
	}

	/**
	 * ctmItemName02を取得します。
	 * @return ctmItemName02
	 */
	public String getCtmItemName02() {
		return ctmItemName02;
	}

	/**
	 * ctmItemName02を設定します。
	 * @param ctmItemName02 ctmItemName02
	 */
	public void setCtmItemName02(String ctmItemName02) {
		this.ctmItemName02 = ctmItemName02;
	}

	/**
	 * ctmItemName03を取得します。
	 * @return ctmItemName03
	 */
	public String getCtmItemName03() {
		return ctmItemName03;
	}

	/**
	 * ctmItemName03を設定します。
	 * @param ctmItemName03 ctmItemName03
	 */
	public void setCtmItemName03(String ctmItemName03) {
		this.ctmItemName03 = ctmItemName03;
	}

	/**
	 * ctmStyleOfPackingを取得します。
	 * @return ctmStyleOfPacking
	 */
	public String getCtmStyleOfPacking() {
		return ctmStyleOfPacking;
	}

	/**
	 * ctmStyleOfPackingを設定します。
	 * @param ctmStyleOfPacking ctmStyleOfPacking
	 */
	public void setCtmStyleOfPacking(String ctmStyleOfPacking) {
		this.ctmStyleOfPacking = ctmStyleOfPacking;
	}

	/**
	 * ctmOrderQtyを取得します。
	 * @return ctmOrderQty
	 */
	public BigDecimal getCtmOrderQty() {
		return ctmOrderQty;
	}

	/**
	 * ctmOrderQtyを設定します。
	 * @param ctmOrderQty ctmOrderQty
	 */
	public void setCtmOrderQty(BigDecimal ctmOrderQty) {
		this.ctmOrderQty = ctmOrderQty;
	}

	/**
	 * ctmCaseNumを取得します。
	 * @return ctmCaseNum
	 */
	public BigDecimal getCtmCaseNum() {
		return ctmCaseNum;
	}

	/**
	 * ctmCaseNumを設定します。
	 * @param ctmCaseNum ctmCaseNum
	 */
	public void setCtmCaseNum(BigDecimal ctmCaseNum) {
		this.ctmCaseNum = ctmCaseNum;
	}

	/**
	 * ctmMatssを取得します。
	 * @return ctmMatss
	 */
	public BigDecimal getCtmMatss() {
		return ctmMatss;
	}

	/**
	 * ctmMatssを設定します。
	 * @param ctmMatss ctmMatss
	 */
	public void setCtmMatss(BigDecimal ctmMatss) {
		this.ctmMatss = ctmMatss;
	}

	/**
	 * ctmOrderPieceを取得します。
	 * @return ctmOrderPiece
	 */
	public BigDecimal getCtmOrderPiece() {
		return ctmOrderPiece;
	}

	/**
	 * ctmOrderPieceを設定します。
	 * @param ctmOrderPiece ctmOrderPiece
	 */
	public void setCtmOrderPiece(BigDecimal ctmOrderPiece) {
		this.ctmOrderPiece = ctmOrderPiece;
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
	 * ctmOrderUnitpriceを取得します。
	 * @return ctmOrderUnitprice
	 */
	public BigDecimal getCtmOrderUnitprice() {
		return ctmOrderUnitprice;
	}

	/**
	 * ctmOrderUnitpriceを設定します。
	 * @param ctmOrderUnitprice ctmOrderUnitprice
	 */
	public void setCtmOrderUnitprice(BigDecimal ctmOrderUnitprice) {
		this.ctmOrderUnitprice = ctmOrderUnitprice;
	}

	/**
	 * ctmOrderAmountを取得します。
	 * @return ctmOrderAmount
	 */
	public BigDecimal getCtmOrderAmount() {
		return ctmOrderAmount;
	}

	/**
	 * ctmOrderAmountを設定します。
	 * @param ctmOrderAmount ctmOrderAmount
	 */
	public void setCtmOrderAmount(BigDecimal ctmOrderAmount) {
		this.ctmOrderAmount = ctmOrderAmount;
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
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * searchKanaを取得します。
	 * @return searchKana
	 */
	public String getSearchKana() {
		return searchKana;
	}

	/**
	 * searchKanaを設定します。
	 * @param searchKana searchKana
	 */
	public void setSearchKana(String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * addressを取得します。
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * addressを設定します。
	 * @param address address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * styleOfPackingを取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * styleOfPackingを設定します。
	 * @param styleOfPacking styleOfPacking
	 */
	public void setStyleOfPacking(String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * otherCompanyCd1を取得します。
	 * @return otherCompanyCd1
	 */
	public String getOtherCompanyCd1() {
		return otherCompanyCd1;
	}

	/**
	 * otherCompanyCd1を設定します。
	 * @param otherCompanyCd1 otherCompanyCd1
	 */
	public void setOtherCompanyCd1(String otherCompanyCd1) {
		this.otherCompanyCd1 = otherCompanyCd1;
	}

	/**
	 * invisibleFlgを取得します。
	 * @return invisibleFlg
	 */
	public BigDecimal getInvisibleFlg() {
		return invisibleFlg;
	}

	/**
	 * invisibleFlgを設定します。
	 * @param invisibleFlg invisibleFlg
	 */
	public void setInvisibleFlg(BigDecimal invisibleFlg) {
		this.invisibleFlg = invisibleFlg;
	}
}
