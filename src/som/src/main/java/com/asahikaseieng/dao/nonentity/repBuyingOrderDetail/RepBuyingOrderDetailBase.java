/*
 * Created on 2009/06/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repBuyingOrderDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepBuyingOrderDetailクラス.
 * @author kanri-user
 */
public class RepBuyingOrderDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepBuyingOrderDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderDivideNo */
	public static final String orderDivideNo_COLUMN = "ORDER_DIVIDE_NO";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション aspOrderNo */
	public static final String aspOrderNo_COLUMN = "ASP_ORDER_NO";

	/** COLUMNアノテーション materialDivision */
	public static final String materialDivision_COLUMN = "MATERIAL_DIVISION";

	/** COLUMNアノテーション siOrderNo */
	public static final String siOrderNo_COLUMN = "SI_ORDER_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション chargeOrganizationCd */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション organizationCd */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション orderDivision */
	public static final String orderDivision_COLUMN = "ORDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";

	/** COLUMNアノテーション unitpriceDefineunit */
	public static final String unitpriceDefineunit_COLUMN = "UNITPRICE_DEFINEUNIT";

	/** COLUMNアノテーション supplierOrdAmount */
	public static final String supplierOrdAmount_COLUMN = "SUPPLIER_ORD_AMOUNT";

	/** COLUMNアノテーション suggestedDeliverlimitDate */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション orderSheetRemark */
	public static final String orderSheetRemark_COLUMN = "ORDER_SHEET_REMARK";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション notes */
	public static final String notes_COLUMN = "NOTES";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション orderSheeFlag */
	public static final String orderSheeFlag_COLUMN = "ORDER_SHEE_FLAG";

	/** COLUMNアノテーション orderSheeDate */
	public static final String orderSheeDate_COLUMN = "ORDER_SHEE_DATE";

	/** COLUMNアノテーション orderSheelTantoCd */
	public static final String orderSheelTantoCd_COLUMN = "ORDER_SHEEL_TANTO_CD";

	/** COLUMNアノテーション goodHousingSum */
	public static final String goodHousingSum_COLUMN = "GOOD_HOUSING_SUM";

	/** COLUMNアノテーション replyContentsDivision */
	public static final String replyContentsDivision_COLUMN = "REPLY_CONTENTS_DIVISION";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション nextDeliverlimitDate */
	public static final String nextDeliverlimitDate_COLUMN = "NEXT_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション dataType */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション stockingDate */
	public static final String stockingDate_COLUMN = "STOCKING_DATE";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション slipNo */
	public static final String slipNo_COLUMN = "SLIP_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション cancelSlipNo */
	public static final String cancelSlipNo_COLUMN = "CANCEL_SLIP_NO";

	/** COLUMNアノテーション cancelRowNo */
	public static final String cancelRowNo_COLUMN = "CANCEL_ROW_NO";

	/** COLUMNアノテーション supplierLotno */
	public static final String supplierLotno_COLUMN = "SUPPLIER_LOTNO";

	/** COLUMNアノテーション lotNo */
	public static final String lotNo_COLUMN = "LOT_NO";

	/** COLUMNアノテーション stockLocationCd */
	public static final String stockLocationCd_COLUMN = "STOCK_LOCATION_CD";

	/** COLUMNアノテーション housingLocationCd */
	public static final String housingLocationCd_COLUMN = "HOUSING_LOCATION_CD";

	/** COLUMNアノテーション arrivalQuantity */
	public static final String arrivalQuantity_COLUMN = "ARRIVAL_QUANTITY";

	/** COLUMNアノテーション stockQuantity */
	public static final String stockQuantity_COLUMN = "STOCK_QUANTITY";

	/** COLUMNアノテーション acceptQuantity */
	public static final String acceptQuantity_COLUMN = "ACCEPT_QUANTITY";

	/** COLUMNアノテーション acceptConvertQuantity */
	public static final String acceptConvertQuantity_COLUMN = "ACCEPT_CONVERT_QUANTITY";

	/** COLUMNアノテーション increaseQuantity */
	public static final String increaseQuantity_COLUMN = "INCREASE_QUANTITY";

	/** COLUMNアノテーション stockingQuantity */
	public static final String stockingQuantity_COLUMN = "STOCKING_QUANTITY";

	/** COLUMNアノテーション housingUnitprice */
	public static final String housingUnitprice_COLUMN = "HOUSING_UNITPRICE";

	/** COLUMNアノテーション fareAmount */
	public static final String fareAmount_COLUMN = "FARE_AMOUNT";

	/** COLUMNアノテーション stockingAmount */
	public static final String stockingAmount_COLUMN = "STOCKING_AMOUNT";

	/** COLUMNアノテーション acceptDate */
	public static final String acceptDate_COLUMN = "ACCEPT_DATE";

	/** COLUMNアノテーション orderSheetRemark2 */
	public static final String orderSheetRemark2_COLUMN = "ORDER_SHEET_REMARK2";

	/** COLUMNアノテーション remark2 */
	public static final String remark2_COLUMN = "REMARK2";

	/** COLUMNアノテーション notes2 */
	public static final String notes2_COLUMN = "NOTES2";

	/** COLUMNアノテーション status2 */
	public static final String status2_COLUMN = "STATUS2";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション taxCd */
	public static final String taxCd_COLUMN = "TAX_CD";

	/** COLUMNアノテーション payeeCd */
	public static final String payeeCd_COLUMN = "PAYEE_CD";

	/** COLUMNアノテーション accountDebitSectionCd */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountCreditSectionCd */
	public static final String accountCreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitSubTitleCd */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditSubTitleCd */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** COLUMNアノテーション payableTargetDivision */
	public static final String payableTargetDivision_COLUMN = "PAYABLE_TARGET_DIVISION";

	/** COLUMNアノテーション paymentTargetDivision */
	public static final String paymentTargetDivision_COLUMN = "PAYMENT_TARGET_DIVISION";

	/** COLUMNアノテーション payableUpdateDivision */
	public static final String payableUpdateDivision_COLUMN = "PAYABLE_UPDATE_DIVISION";

	/** COLUMNアノテーション payableNo */
	public static final String payableNo_COLUMN = "PAYABLE_NO";

	/** COLUMNアノテーション paymentUpdateDivision */
	public static final String paymentUpdateDivision_COLUMN = "PAYMENT_UPDATE_DIVISION";

	/** COLUMNアノテーション paymentNo */
	public static final String paymentNo_COLUMN = "PAYMENT_NO";

	/** COLUMNアノテーション summaryCd */
	public static final String summaryCd_COLUMN = "SUMMARY_CD";

	/** COLUMNアノテーション summary */
	public static final String summary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション paymentUpdateDate */
	public static final String paymentUpdateDate_COLUMN = "PAYMENT_UPDATE_DATE";

	/** COLUMNアノテーション payableUpdateDate */
	public static final String payableUpdateDate_COLUMN = "PAYABLE_UPDATE_DATE";

	/** COLUMNアノテーション transmissionDate */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション labelFlag */
	public static final String labelFlag_COLUMN = "LABEL_FLAG";

	/** COLUMNアノテーション labelDate */
	public static final String labelDate_COLUMN = "LABEL_DATE";

	/** COLUMNアノテーション labelTantoCd */
	public static final String labelTantoCd_COLUMN = "LABEL_TANTO_CD";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション inspectMethod */
	public static final String inspectMethod_COLUMN = "INSPECT_METHOD";

	/** COLUMNアノテーション mortgageDetailFlg */
	public static final String mortgageDetailFlg_COLUMN = "MORTGAGE_DETAIL_FLG";

	/** COLUMNアノテーション inspreceiptWaitQuantity */
	public static final String inspreceiptWaitQuantity_COLUMN = "INSPRECEIPT_WAIT_QUANTITY";

	/** COLUMNアノテーション badQuantity */
	public static final String badQuantity_COLUMN = "BAD_QUANTITY";

	/** COLUMNアノテーション costEntry */
	public static final String costEntry_COLUMN = "COST_ENTRY";

	/** COLUMNアノテーション advPurNoticeDecideDivision */
	public static final String advPurNoticeDecideDivision_COLUMN = "ADV_PUR_NOTICE_DECIDE_DIVISION";

	/** COLUMNアノテーション orderMortgagedQuantity */
	public static final String orderMortgagedQuantity_COLUMN = "ORDER_MORTGAGED_QUANTITY";

	/** COLUMNアノテーション sumHousingConvertQuant */
	public static final String sumHousingConvertQuant_COLUMN = "SUM_HOUSING_CONVERT_QUANT";

	/** COLUMNアノテーション inspectWaitConvertQuantity */
	public static final String inspectWaitConvertQuantity_COLUMN = "INSPECT_WAIT_CONVERT_QUANTITY";

	/** COLUMNアノテーション extractionsQuantity */
	public static final String extractionsQuantity_COLUMN = "EXTRACTIONS_QUANTITY";

	/** COLUMNアノテーション defectiveQuantity */
	public static final String defectiveQuantity_COLUMN = "DEFECTIVE_QUANTITY";

	/** COLUMNアノテーション checkTantoCd */
	public static final String checkTantoCd_COLUMN = "CHECK_TANTO_CD";

	/** COLUMNアノテーション laboratoryMethod */
	public static final String laboratoryMethod_COLUMN = "LABORATORY_METHOD";

	/** COLUMNアノテーション provisionDivision */
	public static final String provisionDivision_COLUMN = "PROVISION_DIVISION";

	/** COLUMNアノテーション checkDate */
	public static final String checkDate_COLUMN = "CHECK_DATE";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション checkQuantity */
	public static final String checkQuantity_COLUMN = "CHECK_QUANTITY";

	/** COLUMNアノテーション slipIssueDivision */
	public static final String slipIssueDivision_COLUMN = "SLIP_ISSUE_DIVISION";

	/** COLUMNアノテーション slipIssueDate */
	public static final String slipIssueDate_COLUMN = "SLIP_ISSUE_DATE";

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

	/** COLUMNアノテーション sumAcceptQuantity */
	public static final String sumAcceptQuantity_COLUMN = "SUM_ACCEPT_QUANTITY";

	/** COLUMNアノテーション sumStockingQuantity */
	public static final String sumStockingQuantity_COLUMN = "SUM_STOCKING_QUANTITY";

	/** COLUMNアノテーション sumInQuantity */
	public static final String sumInQuantity_COLUMN = "SUM_IN_QUANTITY";

	/** COLUMNアノテーション sumStockingAmount */
	public static final String sumStockingAmount_COLUMN = "SUM_STOCKING_AMOUNT";

	/** COLUMNアノテーション sumTaxAmount */
	public static final String sumTaxAmount_COLUMN = "SUM_TAX_AMOUNT";

	/** COLUMNアノテーション sumAmount */
	public static final String sumAmount_COLUMN = "SUM_AMOUNT";

	/** COLUMNアノテーション purchasePrice */
	public static final String purchasePrice_COLUMN = "PURCHASE_PRICE";

	/** COLUMNアノテーション itemItemName */
	public static final String itemItemName_COLUMN = "ITEM_ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション unit */
	public static final String unit_COLUMN = "UNIT";

	/** COLUMNアノテーション reducedTaxCalcFlg */
    public static final String reducedTaxCalcFlg_COLUMN = "REDUCED_TAX_TARGET_FLG";

	//
	// インスタンスフィールド
	//

	private String key;

	private String purchaseNo;

	private String buySubcontractOrderNo;

	private String orderDivideNo;

	private String orderNo;

	private java.math.BigDecimal orderRowNo;

	private String aspOrderNo;

	private java.math.BigDecimal materialDivision;

	private String siOrderNo;

	private java.sql.Timestamp orderDate;

	private String chargeOrganizationCd;

	private String organizationCd;

	private String tantoCd;

	private java.math.BigDecimal orderDivision;

	private String venderCd;

	private String itemCd;

	private String itemName;

	private java.math.BigDecimal orderQuantity;

	private java.math.BigDecimal orderConvertQuantity;

	private java.math.BigDecimal orderUnitprice;

	private java.math.BigDecimal unitpriceDefineunit;

	private java.math.BigDecimal supplierOrdAmount;

	private java.sql.Timestamp suggestedDeliverlimitDate;

	private String orderSheetRemark;

	private String remark;

	private String notes;

	private String locationCd;

	private java.math.BigDecimal status;

	private String orderSheetNo;

	private java.math.BigDecimal orderSheeFlag;

	private java.sql.Timestamp orderSheeDate;

	private String orderSheelTantoCd;

	private java.math.BigDecimal goodHousingSum;

	private java.math.BigDecimal replyContentsDivision;

	private java.math.BigDecimal deliveryComp;

	private java.sql.Timestamp nextDeliverlimitDate;

	private java.math.BigDecimal dataType;

	private java.math.BigDecimal dataTotalDivision;

	private String categoryDivision;

	private java.sql.Timestamp stockingDate;

	private String accountYears;

	private String slipNo;

	private java.math.BigDecimal rowNo;

	private String cancelSlipNo;

	private java.math.BigDecimal cancelRowNo;

	private String supplierLotno;

	private String lotNo;

	private String stockLocationCd;

	private String housingLocationCd;

	private java.math.BigDecimal arrivalQuantity;

	private java.math.BigDecimal stockQuantity;

	private java.math.BigDecimal acceptQuantity;

	private java.math.BigDecimal acceptConvertQuantity;

	private java.math.BigDecimal increaseQuantity;

	private java.math.BigDecimal stockingQuantity;

	private java.math.BigDecimal housingUnitprice;

	private java.math.BigDecimal fareAmount;

	private java.math.BigDecimal stockingAmount;

	private java.sql.Timestamp acceptDate;

	private String orderSheetRemark2;

	private String remark2;

	private String notes2;

	private java.math.BigDecimal status2;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal taxAmount;

	private String taxCd;

	private String payeeCd;

	private String accountDebitSectionCd;

	private String accountCreditSectionCd;

	private String debitTitleCd;

	private String debitSubTitleCd;

	private String creditTitleCd;

	private String creditSubTitleCd;

	private java.math.BigDecimal payableTargetDivision;

	private java.math.BigDecimal paymentTargetDivision;

	private java.math.BigDecimal payableUpdateDivision;

	private String payableNo;

	private java.math.BigDecimal paymentUpdateDivision;

	private String paymentNo;

	private String summaryCd;

	private String summary;

	private java.sql.Timestamp paymentUpdateDate;

	private java.sql.Timestamp payableUpdateDate;

	private java.sql.Timestamp transmissionDate;

	private java.math.BigDecimal labelFlag;

	private java.sql.Timestamp labelDate;

	private String labelTantoCd;

	private java.math.BigDecimal tmpUnitpriceFlg;

	private java.math.BigDecimal inspectMethod;

	private java.math.BigDecimal mortgageDetailFlg;

	private java.math.BigDecimal inspreceiptWaitQuantity;

	private java.math.BigDecimal badQuantity;

	private java.math.BigDecimal costEntry;

	private java.math.BigDecimal advPurNoticeDecideDivision;

	private java.math.BigDecimal orderMortgagedQuantity;

	private java.math.BigDecimal sumHousingConvertQuant;

	private java.math.BigDecimal inspectWaitConvertQuantity;

	private java.math.BigDecimal extractionsQuantity;

	private java.math.BigDecimal defectiveQuantity;

	private String checkTantoCd;

	private java.math.BigDecimal laboratoryMethod;

	private java.math.BigDecimal provisionDivision;

	private java.sql.Timestamp checkDate;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal checkQuantity;

	private java.math.BigDecimal slipIssueDivision;

	private java.sql.Timestamp slipIssueDate;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private java.math.BigDecimal sumAcceptQuantity;

	private java.math.BigDecimal sumStockingQuantity;

	private java.math.BigDecimal sumInQuantity;

	private java.math.BigDecimal sumStockingAmount;

	private java.math.BigDecimal sumTaxAmount;

	private java.math.BigDecimal sumAmount;

	private java.math.BigDecimal purchasePrice;

	private String itemItemName;

	private String styleOfPacking;

	private String unit;

	private String reducedTaxTargetFlg;

	//
	// インスタンスメソッド
	//

	/**
	 * key取得.
	 * @return key
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * key設定.
	 * @param key key
	 */
	public void setKey(final String key) {
		this.key = key;
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
	 * orderDivideNo取得.
	 * @return orderDivideNo
	 */
	public String getOrderDivideNo() {
		return this.orderDivideNo;
	}

	/**
	 * orderDivideNo設定.
	 * @param orderDivideNo orderDivideNo
	 */
	public void setOrderDivideNo(final String orderDivideNo) {
		this.orderDivideNo = orderDivideNo;
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
	 * orderRowNo取得.
	 * @return orderRowNo
	 */
	public java.math.BigDecimal getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * orderRowNo設定.
	 * @param orderRowNo orderRowNo
	 */
	public void setOrderRowNo(final java.math.BigDecimal orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * aspOrderNo取得.
	 * @return aspOrderNo
	 */
	public String getAspOrderNo() {
		return this.aspOrderNo;
	}

	/**
	 * aspOrderNo設定.
	 * @param aspOrderNo aspOrderNo
	 */
	public void setAspOrderNo(final String aspOrderNo) {
		this.aspOrderNo = aspOrderNo;
	}

	/**
	 * materialDivision取得.
	 * @return materialDivision
	 */
	public java.math.BigDecimal getMaterialDivision() {
		return this.materialDivision;
	}

	/**
	 * materialDivision設定.
	 * @param materialDivision materialDivision
	 */
	public void setMaterialDivision(final java.math.BigDecimal materialDivision) {
		this.materialDivision = materialDivision;
	}

	/**
	 * siOrderNo取得.
	 * @return siOrderNo
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * siOrderNo設定.
	 * @param siOrderNo siOrderNo
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
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
	 * chargeOrganizationCd取得.
	 * @return chargeOrganizationCd
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * chargeOrganizationCd設定.
	 * @param chargeOrganizationCd chargeOrganizationCd
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * tantoCd取得.
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return this.tantoCd;
	}

	/**
	 * tantoCd設定.
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
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
	 * unitpriceDefineunit取得.
	 * @return unitpriceDefineunit
	 */
	public java.math.BigDecimal getUnitpriceDefineunit() {
		return this.unitpriceDefineunit;
	}

	/**
	 * unitpriceDefineunit設定.
	 * @param unitpriceDefineunit unitpriceDefineunit
	 */
	public void setUnitpriceDefineunit(final java.math.BigDecimal unitpriceDefineunit) {
		this.unitpriceDefineunit = unitpriceDefineunit;
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
	 * suggestedDeliverlimitDate取得.
	 * @return suggestedDeliverlimitDate
	 */
	public java.sql.Timestamp getSuggestedDeliverlimitDate() {
		return this.suggestedDeliverlimitDate;
	}

	/**
	 * suggestedDeliverlimitDate設定.
	 * @param suggestedDeliverlimitDate suggestedDeliverlimitDate
	 */
	public void setSuggestedDeliverlimitDate(final java.sql.Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

	/**
	 * orderSheetRemark取得.
	 * @return orderSheetRemark
	 */
	public String getOrderSheetRemark() {
		return this.orderSheetRemark;
	}

	/**
	 * orderSheetRemark設定.
	 * @param orderSheetRemark orderSheetRemark
	 */
	public void setOrderSheetRemark(final String orderSheetRemark) {
		this.orderSheetRemark = orderSheetRemark;
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
	 * notes取得.
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * notes設定.
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
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
	 * status取得.
	 * @return status
	 */
	public java.math.BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * status設定.
	 * @param status status
	 */
	public void setStatus(final java.math.BigDecimal status) {
		this.status = status;
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
	 * orderSheeFlag取得.
	 * @return orderSheeFlag
	 */
	public java.math.BigDecimal getOrderSheeFlag() {
		return this.orderSheeFlag;
	}

	/**
	 * orderSheeFlag設定.
	 * @param orderSheeFlag orderSheeFlag
	 */
	public void setOrderSheeFlag(final java.math.BigDecimal orderSheeFlag) {
		this.orderSheeFlag = orderSheeFlag;
	}

	/**
	 * orderSheeDate取得.
	 * @return orderSheeDate
	 */
	public java.sql.Timestamp getOrderSheeDate() {
		return this.orderSheeDate;
	}

	/**
	 * orderSheeDate設定.
	 * @param orderSheeDate orderSheeDate
	 */
	public void setOrderSheeDate(final java.sql.Timestamp orderSheeDate) {
		this.orderSheeDate = orderSheeDate;
	}

	/**
	 * orderSheelTantoCd取得.
	 * @return orderSheelTantoCd
	 */
	public String getOrderSheelTantoCd() {
		return this.orderSheelTantoCd;
	}

	/**
	 * orderSheelTantoCd設定.
	 * @param orderSheelTantoCd orderSheelTantoCd
	 */
	public void setOrderSheelTantoCd(final String orderSheelTantoCd) {
		this.orderSheelTantoCd = orderSheelTantoCd;
	}

	/**
	 * goodHousingSum取得.
	 * @return goodHousingSum
	 */
	public java.math.BigDecimal getGoodHousingSum() {
		return this.goodHousingSum;
	}

	/**
	 * goodHousingSum設定.
	 * @param goodHousingSum goodHousingSum
	 */
	public void setGoodHousingSum(final java.math.BigDecimal goodHousingSum) {
		this.goodHousingSum = goodHousingSum;
	}

	/**
	 * replyContentsDivision取得.
	 * @return replyContentsDivision
	 */
	public java.math.BigDecimal getReplyContentsDivision() {
		return this.replyContentsDivision;
	}

	/**
	 * replyContentsDivision設定.
	 * @param replyContentsDivision replyContentsDivision
	 */
	public void setReplyContentsDivision(final java.math.BigDecimal replyContentsDivision) {
		this.replyContentsDivision = replyContentsDivision;
	}

	/**
	 * deliveryComp取得.
	 * @return deliveryComp
	 */
	public java.math.BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * deliveryComp設定.
	 * @param deliveryComp deliveryComp
	 */
	public void setDeliveryComp(final java.math.BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * nextDeliverlimitDate取得.
	 * @return nextDeliverlimitDate
	 */
	public java.sql.Timestamp getNextDeliverlimitDate() {
		return this.nextDeliverlimitDate;
	}

	/**
	 * nextDeliverlimitDate設定.
	 * @param nextDeliverlimitDate nextDeliverlimitDate
	 */
	public void setNextDeliverlimitDate(final java.sql.Timestamp nextDeliverlimitDate) {
		this.nextDeliverlimitDate = nextDeliverlimitDate;
	}

	/**
	 * dataType取得.
	 * @return dataType
	 */
	public java.math.BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * dataType設定.
	 * @param dataType dataType
	 */
	public void setDataType(final java.math.BigDecimal dataType) {
		this.dataType = dataType;
	}

	/**
	 * dataTotalDivision取得.
	 * @return dataTotalDivision
	 */
	public java.math.BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * dataTotalDivision設定.
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final java.math.BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * accountYears取得.
	 * @return accountYears
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * accountYears設定.
	 * @param accountYears accountYears
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

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
	 * cancelSlipNo取得.
	 * @return cancelSlipNo
	 */
	public String getCancelSlipNo() {
		return this.cancelSlipNo;
	}

	/**
	 * cancelSlipNo設定.
	 * @param cancelSlipNo cancelSlipNo
	 */
	public void setCancelSlipNo(final String cancelSlipNo) {
		this.cancelSlipNo = cancelSlipNo;
	}

	/**
	 * cancelRowNo取得.
	 * @return cancelRowNo
	 */
	public java.math.BigDecimal getCancelRowNo() {
		return this.cancelRowNo;
	}

	/**
	 * cancelRowNo設定.
	 * @param cancelRowNo cancelRowNo
	 */
	public void setCancelRowNo(final java.math.BigDecimal cancelRowNo) {
		this.cancelRowNo = cancelRowNo;
	}

	/**
	 * supplierLotno取得.
	 * @return supplierLotno
	 */
	public String getSupplierLotno() {
		return this.supplierLotno;
	}

	/**
	 * supplierLotno設定.
	 * @param supplierLotno supplierLotno
	 */
	public void setSupplierLotno(final String supplierLotno) {
		this.supplierLotno = supplierLotno;
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
	 * stockLocationCd取得.
	 * @return stockLocationCd
	 */
	public String getStockLocationCd() {
		return this.stockLocationCd;
	}

	/**
	 * stockLocationCd設定.
	 * @param stockLocationCd stockLocationCd
	 */
	public void setStockLocationCd(final String stockLocationCd) {
		this.stockLocationCd = stockLocationCd;
	}

	/**
	 * housingLocationCd取得.
	 * @return housingLocationCd
	 */
	public String getHousingLocationCd() {
		return this.housingLocationCd;
	}

	/**
	 * housingLocationCd設定.
	 * @param housingLocationCd housingLocationCd
	 */
	public void setHousingLocationCd(final String housingLocationCd) {
		this.housingLocationCd = housingLocationCd;
	}

	/**
	 * arrivalQuantity取得.
	 * @return arrivalQuantity
	 */
	public java.math.BigDecimal getArrivalQuantity() {
		return this.arrivalQuantity;
	}

	/**
	 * arrivalQuantity設定.
	 * @param arrivalQuantity arrivalQuantity
	 */
	public void setArrivalQuantity(final java.math.BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	/**
	 * stockQuantity取得.
	 * @return stockQuantity
	 */
	public java.math.BigDecimal getStockQuantity() {
		return this.stockQuantity;
	}

	/**
	 * stockQuantity設定.
	 * @param stockQuantity stockQuantity
	 */
	public void setStockQuantity(final java.math.BigDecimal stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/**
	 * acceptQuantity取得.
	 * @return acceptQuantity
	 */
	public java.math.BigDecimal getAcceptQuantity() {
		return this.acceptQuantity;
	}

	/**
	 * acceptQuantity設定.
	 * @param acceptQuantity acceptQuantity
	 */
	public void setAcceptQuantity(final java.math.BigDecimal acceptQuantity) {
		this.acceptQuantity = acceptQuantity;
	}

	/**
	 * acceptConvertQuantity取得.
	 * @return acceptConvertQuantity
	 */
	public java.math.BigDecimal getAcceptConvertQuantity() {
		return this.acceptConvertQuantity;
	}

	/**
	 * acceptConvertQuantity設定.
	 * @param acceptConvertQuantity acceptConvertQuantity
	 */
	public void setAcceptConvertQuantity(final java.math.BigDecimal acceptConvertQuantity) {
		this.acceptConvertQuantity = acceptConvertQuantity;
	}

	/**
	 * increaseQuantity取得.
	 * @return increaseQuantity
	 */
	public java.math.BigDecimal getIncreaseQuantity() {
		return this.increaseQuantity;
	}

	/**
	 * increaseQuantity設定.
	 * @param increaseQuantity increaseQuantity
	 */
	public void setIncreaseQuantity(final java.math.BigDecimal increaseQuantity) {
		this.increaseQuantity = increaseQuantity;
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
	 * fareAmount取得.
	 * @return fareAmount
	 */
	public java.math.BigDecimal getFareAmount() {
		return this.fareAmount;
	}

	/**
	 * fareAmount設定.
	 * @param fareAmount fareAmount
	 */
	public void setFareAmount(final java.math.BigDecimal fareAmount) {
		this.fareAmount = fareAmount;
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
	 * orderSheetRemark2取得.
	 * @return orderSheetRemark2
	 */
	public String getOrderSheetRemark2() {
		return this.orderSheetRemark2;
	}

	/**
	 * orderSheetRemark2設定.
	 * @param orderSheetRemark2 orderSheetRemark2
	 */
	public void setOrderSheetRemark2(final String orderSheetRemark2) {
		this.orderSheetRemark2 = orderSheetRemark2;
	}

	/**
	 * remark2取得.
	 * @return remark2
	 */
	public String getRemark2() {
		return this.remark2;
	}

	/**
	 * remark2設定.
	 * @param remark2 remark2
	 */
	public void setRemark2(final String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * notes2取得.
	 * @return notes2
	 */
	public String getNotes2() {
		return this.notes2;
	}

	/**
	 * notes2設定.
	 * @param notes2 notes2
	 */
	public void setNotes2(final String notes2) {
		this.notes2 = notes2;
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
	 * taxDivision取得.
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * taxDivision設定.
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * taxAmount取得.
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * payeeCd取得.
	 * @return payeeCd
	 */
	public String getPayeeCd() {
		return this.payeeCd;
	}

	/**
	 * payeeCd設定.
	 * @param payeeCd payeeCd
	 */
	public void setPayeeCd(final String payeeCd) {
		this.payeeCd = payeeCd;
	}

	/**
	 * accountDebitSectionCd取得.
	 * @return accountDebitSectionCd
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * accountDebitSectionCd設定.
	 * @param accountDebitSectionCd accountDebitSectionCd
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * accountCreditSectionCd取得.
	 * @return accountCreditSectionCd
	 */
	public String getAccountCreditSectionCd() {
		return this.accountCreditSectionCd;
	}

	/**
	 * accountCreditSectionCd設定.
	 * @param accountCreditSectionCd accountCreditSectionCd
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * debitTitleCd取得.
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * debitTitleCd設定.
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * debitSubTitleCd取得.
	 * @return debitSubTitleCd
	 */
	public String getDebitSubTitleCd() {
		return this.debitSubTitleCd;
	}

	/**
	 * debitSubTitleCd設定.
	 * @param debitSubTitleCd debitSubTitleCd
	 */
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
	}

	/**
	 * creditTitleCd取得.
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * creditTitleCd設定.
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * creditSubTitleCd取得.
	 * @return creditSubTitleCd
	 */
	public String getCreditSubTitleCd() {
		return this.creditSubTitleCd;
	}

	/**
	 * creditSubTitleCd設定.
	 * @param creditSubTitleCd creditSubTitleCd
	 */
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * payableTargetDivision取得.
	 * @return payableTargetDivision
	 */
	public java.math.BigDecimal getPayableTargetDivision() {
		return this.payableTargetDivision;
	}

	/**
	 * payableTargetDivision設定.
	 * @param payableTargetDivision payableTargetDivision
	 */
	public void setPayableTargetDivision(final java.math.BigDecimal payableTargetDivision) {
		this.payableTargetDivision = payableTargetDivision;
	}

	/**
	 * paymentTargetDivision取得.
	 * @return paymentTargetDivision
	 */
	public java.math.BigDecimal getPaymentTargetDivision() {
		return this.paymentTargetDivision;
	}

	/**
	 * paymentTargetDivision設定.
	 * @param paymentTargetDivision paymentTargetDivision
	 */
	public void setPaymentTargetDivision(final java.math.BigDecimal paymentTargetDivision) {
		this.paymentTargetDivision = paymentTargetDivision;
	}

	/**
	 * payableUpdateDivision取得.
	 * @return payableUpdateDivision
	 */
	public java.math.BigDecimal getPayableUpdateDivision() {
		return this.payableUpdateDivision;
	}

	/**
	 * payableUpdateDivision設定.
	 * @param payableUpdateDivision payableUpdateDivision
	 */
	public void setPayableUpdateDivision(final java.math.BigDecimal payableUpdateDivision) {
		this.payableUpdateDivision = payableUpdateDivision;
	}

	/**
	 * payableNo取得.
	 * @return payableNo
	 */
	public String getPayableNo() {
		return this.payableNo;
	}

	/**
	 * payableNo設定.
	 * @param payableNo payableNo
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * paymentUpdateDivision取得.
	 * @return paymentUpdateDivision
	 */
	public java.math.BigDecimal getPaymentUpdateDivision() {
		return this.paymentUpdateDivision;
	}

	/**
	 * paymentUpdateDivision設定.
	 * @param paymentUpdateDivision paymentUpdateDivision
	 */
	public void setPaymentUpdateDivision(final java.math.BigDecimal paymentUpdateDivision) {
		this.paymentUpdateDivision = paymentUpdateDivision;
	}

	/**
	 * paymentNo取得.
	 * @return paymentNo
	 */
	public String getPaymentNo() {
		return this.paymentNo;
	}

	/**
	 * paymentNo設定.
	 * @param paymentNo paymentNo
	 */
	public void setPaymentNo(final String paymentNo) {
		this.paymentNo = paymentNo;
	}

	/**
	 * summaryCd取得.
	 * @return summaryCd
	 */
	public String getSummaryCd() {
		return this.summaryCd;
	}

	/**
	 * summaryCd設定.
	 * @param summaryCd summaryCd
	 */
	public void setSummaryCd(final String summaryCd) {
		this.summaryCd = summaryCd;
	}

	/**
	 * summary取得.
	 * @return summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * summary設定.
	 * @param summary summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * paymentUpdateDate取得.
	 * @return paymentUpdateDate
	 */
	public java.sql.Timestamp getPaymentUpdateDate() {
		return this.paymentUpdateDate;
	}

	/**
	 * paymentUpdateDate設定.
	 * @param paymentUpdateDate paymentUpdateDate
	 */
	public void setPaymentUpdateDate(final java.sql.Timestamp paymentUpdateDate) {
		this.paymentUpdateDate = paymentUpdateDate;
	}

	/**
	 * payableUpdateDate取得.
	 * @return payableUpdateDate
	 */
	public java.sql.Timestamp getPayableUpdateDate() {
		return this.payableUpdateDate;
	}

	/**
	 * payableUpdateDate設定.
	 * @param payableUpdateDate payableUpdateDate
	 */
	public void setPayableUpdateDate(final java.sql.Timestamp payableUpdateDate) {
		this.payableUpdateDate = payableUpdateDate;
	}

	/**
	 * transmissionDate取得.
	 * @return transmissionDate
	 */
	public java.sql.Timestamp getTransmissionDate() {
		return this.transmissionDate;
	}

	/**
	 * transmissionDate設定.
	 * @param transmissionDate transmissionDate
	 */
	public void setTransmissionDate(final java.sql.Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	/**
	 * labelFlag取得.
	 * @return labelFlag
	 */
	public java.math.BigDecimal getLabelFlag() {
		return this.labelFlag;
	}

	/**
	 * labelFlag設定.
	 * @param labelFlag labelFlag
	 */
	public void setLabelFlag(final java.math.BigDecimal labelFlag) {
		this.labelFlag = labelFlag;
	}

	/**
	 * labelDate取得.
	 * @return labelDate
	 */
	public java.sql.Timestamp getLabelDate() {
		return this.labelDate;
	}

	/**
	 * labelDate設定.
	 * @param labelDate labelDate
	 */
	public void setLabelDate(final java.sql.Timestamp labelDate) {
		this.labelDate = labelDate;
	}

	/**
	 * labelTantoCd取得.
	 * @return labelTantoCd
	 */
	public String getLabelTantoCd() {
		return this.labelTantoCd;
	}

	/**
	 * labelTantoCd設定.
	 * @param labelTantoCd labelTantoCd
	 */
	public void setLabelTantoCd(final String labelTantoCd) {
		this.labelTantoCd = labelTantoCd;
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
	 * inspectMethod取得.
	 * @return inspectMethod
	 */
	public java.math.BigDecimal getInspectMethod() {
		return this.inspectMethod;
	}

	/**
	 * inspectMethod設定.
	 * @param inspectMethod inspectMethod
	 */
	public void setInspectMethod(final java.math.BigDecimal inspectMethod) {
		this.inspectMethod = inspectMethod;
	}

	/**
	 * mortgageDetailFlg取得.
	 * @return mortgageDetailFlg
	 */
	public java.math.BigDecimal getMortgageDetailFlg() {
		return this.mortgageDetailFlg;
	}

	/**
	 * mortgageDetailFlg設定.
	 * @param mortgageDetailFlg mortgageDetailFlg
	 */
	public void setMortgageDetailFlg(final java.math.BigDecimal mortgageDetailFlg) {
		this.mortgageDetailFlg = mortgageDetailFlg;
	}

	/**
	 * inspreceiptWaitQuantity取得.
	 * @return inspreceiptWaitQuantity
	 */
	public java.math.BigDecimal getInspreceiptWaitQuantity() {
		return this.inspreceiptWaitQuantity;
	}

	/**
	 * inspreceiptWaitQuantity設定.
	 * @param inspreceiptWaitQuantity inspreceiptWaitQuantity
	 */
	public void setInspreceiptWaitQuantity(final java.math.BigDecimal inspreceiptWaitQuantity) {
		this.inspreceiptWaitQuantity = inspreceiptWaitQuantity;
	}

	/**
	 * badQuantity取得.
	 * @return badQuantity
	 */
	public java.math.BigDecimal getBadQuantity() {
		return this.badQuantity;
	}

	/**
	 * badQuantity設定.
	 * @param badQuantity badQuantity
	 */
	public void setBadQuantity(final java.math.BigDecimal badQuantity) {
		this.badQuantity = badQuantity;
	}

	/**
	 * costEntry取得.
	 * @return costEntry
	 */
	public java.math.BigDecimal getCostEntry() {
		return this.costEntry;
	}

	/**
	 * costEntry設定.
	 * @param costEntry costEntry
	 */
	public void setCostEntry(final java.math.BigDecimal costEntry) {
		this.costEntry = costEntry;
	}

	/**
	 * advPurNoticeDecideDivision取得.
	 * @return advPurNoticeDecideDivision
	 */
	public java.math.BigDecimal getAdvPurNoticeDecideDivision() {
		return this.advPurNoticeDecideDivision;
	}

	/**
	 * advPurNoticeDecideDivision設定.
	 * @param advPurNoticeDecideDivision advPurNoticeDecideDivision
	 */
	public void setAdvPurNoticeDecideDivision(final java.math.BigDecimal advPurNoticeDecideDivision) {
		this.advPurNoticeDecideDivision = advPurNoticeDecideDivision;
	}

	/**
	 * orderMortgagedQuantity取得.
	 * @return orderMortgagedQuantity
	 */
	public java.math.BigDecimal getOrderMortgagedQuantity() {
		return this.orderMortgagedQuantity;
	}

	/**
	 * orderMortgagedQuantity設定.
	 * @param orderMortgagedQuantity orderMortgagedQuantity
	 */
	public void setOrderMortgagedQuantity(final java.math.BigDecimal orderMortgagedQuantity) {
		this.orderMortgagedQuantity = orderMortgagedQuantity;
	}

	/**
	 * sumHousingConvertQuant取得.
	 * @return sumHousingConvertQuant
	 */
	public java.math.BigDecimal getSumHousingConvertQuant() {
		return this.sumHousingConvertQuant;
	}

	/**
	 * sumHousingConvertQuant設定.
	 * @param sumHousingConvertQuant sumHousingConvertQuant
	 */
	public void setSumHousingConvertQuant(final java.math.BigDecimal sumHousingConvertQuant) {
		this.sumHousingConvertQuant = sumHousingConvertQuant;
	}

	/**
	 * inspectWaitConvertQuantity取得.
	 * @return inspectWaitConvertQuantity
	 */
	public java.math.BigDecimal getInspectWaitConvertQuantity() {
		return this.inspectWaitConvertQuantity;
	}

	/**
	 * inspectWaitConvertQuantity設定.
	 * @param inspectWaitConvertQuantity inspectWaitConvertQuantity
	 */
	public void setInspectWaitConvertQuantity(final java.math.BigDecimal inspectWaitConvertQuantity) {
		this.inspectWaitConvertQuantity = inspectWaitConvertQuantity;
	}

	/**
	 * extractionsQuantity取得.
	 * @return extractionsQuantity
	 */
	public java.math.BigDecimal getExtractionsQuantity() {
		return this.extractionsQuantity;
	}

	/**
	 * extractionsQuantity設定.
	 * @param extractionsQuantity extractionsQuantity
	 */
	public void setExtractionsQuantity(final java.math.BigDecimal extractionsQuantity) {
		this.extractionsQuantity = extractionsQuantity;
	}

	/**
	 * defectiveQuantity取得.
	 * @return defectiveQuantity
	 */
	public java.math.BigDecimal getDefectiveQuantity() {
		return this.defectiveQuantity;
	}

	/**
	 * defectiveQuantity設定.
	 * @param defectiveQuantity defectiveQuantity
	 */
	public void setDefectiveQuantity(final java.math.BigDecimal defectiveQuantity) {
		this.defectiveQuantity = defectiveQuantity;
	}

	/**
	 * checkTantoCd取得.
	 * @return checkTantoCd
	 */
	public String getCheckTantoCd() {
		return this.checkTantoCd;
	}

	/**
	 * checkTantoCd設定.
	 * @param checkTantoCd checkTantoCd
	 */
	public void setCheckTantoCd(final String checkTantoCd) {
		this.checkTantoCd = checkTantoCd;
	}

	/**
	 * laboratoryMethod取得.
	 * @return laboratoryMethod
	 */
	public java.math.BigDecimal getLaboratoryMethod() {
		return this.laboratoryMethod;
	}

	/**
	 * laboratoryMethod設定.
	 * @param laboratoryMethod laboratoryMethod
	 */
	public void setLaboratoryMethod(final java.math.BigDecimal laboratoryMethod) {
		this.laboratoryMethod = laboratoryMethod;
	}

	/**
	 * provisionDivision取得.
	 * @return provisionDivision
	 */
	public java.math.BigDecimal getProvisionDivision() {
		return this.provisionDivision;
	}

	/**
	 * provisionDivision設定.
	 * @param provisionDivision provisionDivision
	 */
	public void setProvisionDivision(final java.math.BigDecimal provisionDivision) {
		this.provisionDivision = provisionDivision;
	}

	/**
	 * checkDate取得.
	 * @return checkDate
	 */
	public java.sql.Timestamp getCheckDate() {
		return this.checkDate;
	}

	/**
	 * checkDate設定.
	 * @param checkDate checkDate
	 */
	public void setCheckDate(final java.sql.Timestamp checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * taxRatio取得.
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * taxRatio設定.
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * checkQuantity取得.
	 * @return checkQuantity
	 */
	public java.math.BigDecimal getCheckQuantity() {
		return this.checkQuantity;
	}

	/**
	 * checkQuantity設定.
	 * @param checkQuantity checkQuantity
	 */
	public void setCheckQuantity(final java.math.BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	/**
	 * slipIssueDivision取得.
	 * @return slipIssueDivision
	 */
	public java.math.BigDecimal getSlipIssueDivision() {
		return this.slipIssueDivision;
	}

	/**
	 * slipIssueDivision設定.
	 * @param slipIssueDivision slipIssueDivision
	 */
	public void setSlipIssueDivision(final java.math.BigDecimal slipIssueDivision) {
		this.slipIssueDivision = slipIssueDivision;
	}

	/**
	 * slipIssueDate取得.
	 * @return slipIssueDate
	 */
	public java.sql.Timestamp getSlipIssueDate() {
		return this.slipIssueDate;
	}

	/**
	 * slipIssueDate設定.
	 * @param slipIssueDate slipIssueDate
	 */
	public void setSlipIssueDate(final java.sql.Timestamp slipIssueDate) {
		this.slipIssueDate = slipIssueDate;
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
	 * sumAcceptQuantity取得.
	 * @return sumAcceptQuantity
	 */
	public java.math.BigDecimal getSumAcceptQuantity() {
		return this.sumAcceptQuantity;
	}

	/**
	 * sumAcceptQuantity設定.
	 * @param sumAcceptQuantity sumAcceptQuantity
	 */
	public void setSumAcceptQuantity(final java.math.BigDecimal sumAcceptQuantity) {
		this.sumAcceptQuantity = sumAcceptQuantity;
	}

	/**
	 * sumStockingQuantity取得.
	 * @return sumStockingQuantity
	 */
	public java.math.BigDecimal getSumStockingQuantity() {
		return this.sumStockingQuantity;
	}

	/**
	 * sumStockingQuantity設定.
	 * @param sumStockingQuantity sumStockingQuantity
	 */
	public void setSumStockingQuantity(final java.math.BigDecimal sumStockingQuantity) {
		this.sumStockingQuantity = sumStockingQuantity;
	}

	/**
	 * sumInQuantity取得.
	 * @return sumInQuantity
	 */
	public java.math.BigDecimal getSumInQuantity() {
		return this.sumInQuantity;
	}

	/**
	 * sumInQuantity設定.
	 * @param sumInQuantity sumInQuantity
	 */
	public void setSumInQuantity(final java.math.BigDecimal sumInQuantity) {
		this.sumInQuantity = sumInQuantity;
	}

	/**
	 * sumStockingAmount取得.
	 * @return sumStockingAmount
	 */
	public java.math.BigDecimal getSumStockingAmount() {
		return this.sumStockingAmount;
	}

	/**
	 * sumStockingAmount設定.
	 * @param sumStockingAmount sumStockingAmount
	 */
	public void setSumStockingAmount(final java.math.BigDecimal sumStockingAmount) {
		this.sumStockingAmount = sumStockingAmount;
	}

	/**
	 * sumTaxAmount取得.
	 * @return sumTaxAmount
	 */
	public java.math.BigDecimal getSumTaxAmount() {
		return this.sumTaxAmount;
	}

	/**
	 * sumTaxAmount設定.
	 * @param sumTaxAmount sumTaxAmount
	 */
	public void setSumTaxAmount(final java.math.BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}

	/**
	 * sumAmount取得.
	 * @return sumAmount
	 */
	public java.math.BigDecimal getSumAmount() {
		return this.sumAmount;
	}

	/**
	 * sumAmount設定.
	 * @param sumAmount sumAmount
	 */
	public void setSumAmount(final java.math.BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	/**
	 * purchasePrice取得.
	 * @return purchasePrice
	 */
	public java.math.BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	/**
	 * purchasePrice設定.
	 * @param purchasePrice purchasePrice
	 */
	public void setPurchasePrice(final java.math.BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/**
	 * itemItemName取得.
	 * @return itemItemName
	 */
	public String getItemItemName() {
		return this.itemItemName;
	}

	/**
	 * itemItemName設定.
	 * @param itemItemName itemItemName
	 */
	public void setItemItemName(final String itemItemName) {
		this.itemItemName = itemItemName;
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
	 * unit取得.
	 * @return unit
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * unit設定.
	 * @param unit unit
	 */
	public void setUnit(final String unit) {
		this.unit = unit;
	}

	public String getReducedTaxTargetFlg() {
		return reducedTaxTargetFlg;
	}

	public void setReducedTaxTargetFlg(String reducedTaxTargetFlg) {
		this.reducedTaxTargetFlg = reducedTaxTargetFlg;
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
	 * taxCdを取得します。
	 * @return taxCd
	 */
	public String getTaxCd() {
		return taxCd;
	}

	/**
	 * taxCdを設定します。
	 * @param taxCd taxCd
	 */
	public void setTaxCd(String taxCd) {
		this.taxCd = taxCd;
	}
}

