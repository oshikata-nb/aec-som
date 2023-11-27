/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportdetaillist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderImportListクラス.
 * @author 
 */
public class OrderImportDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportDetailListBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション frstOrderNo */
	public static final String frstOrderNo_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション frstOrderRowNo */
	public static final String frstOrderRowNo_COLUMN = "FRST_ORDER_ROW_NO";
	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	/** COLUMNアノテーション ctmOrderNo */
	public static final String ctmOrderNo_COLUMN = "CTM_ORDER_NO";
	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";
	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";
	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";
	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";
	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";
	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";
	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";
	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";
	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";
	/** COLUMNアノテーション weight */
	public static final String weight_COLUMN = "WEIGHT";
	/** COLUMNアノテーション standardUnitprice */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";
	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";
	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";
	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";
	/** COLUMNアノテーション ctmItemCd01 */
	public static final String ctmItemCd01_COLUMN = "CTM_ITEM_CD_01";
	/** COLUMNアノテーション ctmItemCd02 */
	public static final String ctmItemCd02_COLUMN = "CTM_ITEM_CD_02";
	/** COLUMNアノテーション ctmItemCd03 */
	public static final String ctmItemCd03_COLUMN = "CTM_ITEM_CD_03";
	/** COLUMNアノテーション ctmJanCd */
	public static final String ctmJanCd_COLUMN = "CTM_JAN_CD";
	/** COLUMNアノテーション ctmItemName01 */
	public static final String ctmItemName01_COLUMN = "CTM_ITEM_NAME_01";
	/** COLUMNアノテーション ctmItemName02 */
	public static final String ctmItemName02_COLUMN = "CTM_ITEM_NAME_02";
	/** COLUMNアノテーション ctmItemName03 */
	public static final String ctmItemName03_COLUMN = "CTM_ITEM_NAME_03";
	/** COLUMNアノテーション ctmStyleOfPacking */
	public static final String ctmStyleOfPacking_COLUMN = "CTM_STYLE_OF_PACKING";
	/** COLUMNアノテーション ctmOrderQty */
	public static final String ctmOrderQty_COLUMN = "CTM_ORDER_QTY";
	/** COLUMNアノテーション orderStatus */
	public static final String orderStatus_COLUMN = "ORDER_STATUS";
	/** COLUMNアノテーション orderStatusName */
	public static final String orderStatusName_COLUMN = "ORDER_STATUS_NAME";
	/** COLUMNアノテーション ctmOrderAmount */
	public static final String ctmOrderAmount_COLUMN = "CTM_ORDER_AMOUNT";
	/** COLUMNアノテーション ctmOrderUnitprice */
	public static final String ctmOrderUnitprice_COLUMN = "CTM_ORDER_UNITPRICE";
	/** COLUMNアノテーション ctmCaseNum */
	public static final String ctmCaseNum_COLUMN = "CTM_CASE_NUM";
	/** COLUMNアノテーション ctmRemark01 */
	public static final String ctmRemark01_COLUMN = "CTM_REMARK_01";
	/** COLUMNアノテーション ctmRemark02 */
	public static final String ctmRemark02_COLUMN = "CTM_REMARK_02";
	/** COLUMNアノテーション ctmRemark03 */
	public static final String ctmRemark03_COLUMN = "CTM_REMARK_03";
	/** COLUMNアノテーション estimateMatss */
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";
	/** COLUMNアノテーション estimateStandardAmount */
	public static final String estimateStandardAmount_COLUMN = "ESTIMATE_STANDARD_AMOUNT";
	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";
	/** COLUMNアノテーション purchaseStatus */
	public static final String purchaseStatus_COLUMN = "PURCHASE_STATUS";
	/** COLUMNアノテーション aspStatus */
	public static final String aspStatus_COLUMN = "ASP_STATUS";
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	/** COLUMNアノテーション ctmRemark */
	public static final String ctmRemark_COLUMN = "CTM_REMARK";
	/** COLUMNアノテーション errorMessage */
	public static final String errorMessage_COLUMN = "ERROR_MESSAGE";

	/** COLUMNアノテーション inputApprovalFlg */
	public static final String inputApprovalFlg_COLUMN = "INPUT_APPROVAL_FLG";
	/** COLUMNアノテーション delDateSendFlg */
	public static final String delDateSendFlg_COLUMN = "DEL_DATE_SEND_FLG";
	/** COLUMNアノテーション checkedOrderQty */
	public static final String checkedOrderQty_COLUMN = "CHECKED_ORDER_QTY";
	/** COLUMNアノテーション checkedMatss */
	public static final String checkedMatss_COLUMN = "CHECKED_MATSS";

	/** COLUMNアノテーション delFlg */
	public static final String delFlg_COLUMN = "DEL_FLG";

	/** COLUMNアノテーション cancelFlg */
	public static final String cancelFlg_COLUMN = "CANCEL_FLG";

	/** COLUMNアノテーション viewSeq */
	public static final String viewSeq_COLUMN = "VIEW_SEQ";

	/** COLUMNアノテーション headDelFlg */
	public static final String headDelFlg_COLUMN = "HEAD_DEL_FLG";
	//20220512 add S.Fujimaki
	/** COLUMNアノテーション orderDetailDate */
	public static final String orderDetailDate_COLUMN = "ORDER_DETAIL_DATE";
    //20220512 add S.Fujimaki end 

	
	
	// インスタンスフィールド
	//

	private String frstOrderNo;
	
	private BigDecimal frstOrderRowNo;
	
	private String balanceCd;

	private String orderNo;
	
	private String ctmOrderNo;
	
	private BigDecimal rowNo;
	
	private String itemCd;
	
	private String otherCompanyCd1;
	
	private String itemName;
	
	private String styleOfPacking;
	
	private BigDecimal allUpWeight;
	
	private String unitOfOperationManagement;
	
	private String kgOfFractionManagement;
	
	private String orderQty;
	
	private String matss;
	
	private String orderUnitprice;
	
	private String orderAmount;
	
	private String qty;
	
	private String weight;
	
	private String standardUnitprice;
	
	private String standardDiscount;
	
	private String specialDiscount;
	
	private String tmpUnitpriceFlg;
	
	private String ctmItemCd01;
	
	private String ctmItemCd02;
	
	private String ctmItemCd03;
	
	private String ctmJanCd;

	private String ctmItemName01;
	
	private String ctmItemName02;
	
	private String ctmItemName03;
	
	private String ctmStyleOfPacking;
	
	private String ctmOrderQty;
	
	private BigDecimal orderStatus;
	
	private String orderStatusName;
	
	private BigDecimal ctmOrderAmount;
	
	private String ctmOrderUnitprice;
	
	private String ctmCaseNum;
	
	private String ctmRemark01;
	
	private String ctmRemark02;
	
	private String ctmRemark03;
	
	private String estimateMatss;
	
	private String estimateStandardAmount;
	
	private BigDecimal shippingStatus;
	
	private BigDecimal purchaseStatus;
	
	private String aspStatus;
	
	private Timestamp updateDate;
	
	private String ctmRemark;

	private String errorMessage;
	
	private BigDecimal inputApprovalFlg;
	private BigDecimal delDateSendFlg;
	private BigDecimal checkedOrderQty;
	private BigDecimal checkedMatss;
	private BigDecimal delFlg;
	private BigDecimal cancelFlg;

	private BigDecimal headDelFlg;

	private BigDecimal viewSeq;
	//20220512 add S.Fujimaki受注詳細　排他追加
	/** 受注詳細の更新日付を保持 */
	private Timestamp orderDetailDate;
	//20220512 add S.Fujimaki受注詳細　排他追加
	

	
	//
	// インスタンスメソッド
	//


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
	 * allUpWeightを取得します。
	 * @return allUpWeight
	 */
	public BigDecimal getAllUpWeight() {
		return allUpWeight;
	}

	/**
	 * allUpWeightを設定します。
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(BigDecimal allUpWeight) {
		this.allUpWeight = allUpWeight;
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
	public void setUnitOfOperationManagement(String unitOfOperationManagement) {
		this.unitOfOperationManagement = unitOfOperationManagement;
	}

	/**
	 * kgOfFractionManagementを取得します。
	 * @return kgOfFractionManagement
	 */
	public String getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * kgOfFractionManagementを設定します。
	 * @param kgOfFractionManagement kgOfFractionManagement
	 */
	public void setKgOfFractionManagement(String kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
	}

	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public String getOrderQty() {
		return orderQty;
	}

	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(String orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * matssを取得します。
	 * @return matss
	 */
	public String getMatss() {
		return matss;
	}

	/**
	 * matssを設定します。
	 * @param matss matss
	 */
	public void setMatss(String matss) {
		this.matss = matss;
	}
	
	/**
	 * orderUnitpriceを取得します。
	 * @return orderUnitprice
	 */
	public String getOrderUnitprice() {
		return orderUnitprice;
	}

	/**
	 * orderUnitpriceを設定します。
	 * @param orderUnitprice orderUnitprice
	 */
	public void setOrderUnitprice(String orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}
	

	/**
	 * orderAmountを取得します。
	 * @return orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}

	/**
	 * orderAmountを設定します。
	 * @param orderAmount orderAmount
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * qtyを取得します。
	 * @return qty
	 */
	public String getQty() {
		return qty;
	}

	/**
	 * qtyを設定します。
	 * @param qty qty
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}

	/**
	 * weightを取得します。
	 * @return weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * weightを設定します。
	 * @param weight weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * standardUnitpriceを取得します。
	 * @return standardUnitprice
	 */
	public String getStandardUnitprice() {
		return standardUnitprice;
	}

	/**
	 * standardUnitpriceを設定します。
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(String standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * standardDiscountを取得します。
	 * @return standardDiscount
	 */
	public String getStandardDiscount() {
		return standardDiscount;
	}

	/**
	 * standardDiscountを設定します。
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(String standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscountを取得します。
	 * @return specialDiscount
	 */
	public String getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * specialDiscountを設定します。
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(String specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * tmpUnitpriceFlgを取得します。
	 * @return tmpUnitpriceFlg
	 */
	public String getTmpUnitpriceFlg() {
		return tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlgを設定します。
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(String tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
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
	public String getCtmOrderQty() {
		return ctmOrderQty;
	}

	/**
	 * ctmOrderQtyを設定します。
	 * @param ctmOrderQty ctmOrderQty
	 */
	public void setCtmOrderQty(String ctmOrderQty) {
		this.ctmOrderQty = ctmOrderQty;
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
	 * ctmOrderUnitpriceを取得します。
	 * @return ctmOrderUnitprice
	 */
	public String getCtmOrderUnitprice() {
		return ctmOrderUnitprice;
	}

	/**
	 * ctmOrderUnitpriceを設定します。
	 * @param ctmOrderUnitprice ctmOrderUnitprice
	 */
	public void setCtmOrderUnitprice(String ctmOrderUnitprice) {
		this.ctmOrderUnitprice = ctmOrderUnitprice;
	}

	/**
	 * ctmCaseNumを取得します。
	 * @return ctmCaseNum
	 */
	public String getCtmCaseNum() {
		return ctmCaseNum;
	}

	/**
	 * ctmCaseNumを設定します。
	 * @param ctmCaseNum ctmCaseNum
	 */
	public void setCtmCaseNum(String ctmCaseNum) {
		this.ctmCaseNum = ctmCaseNum;
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
	 * estimateMatssを取得します。
	 * @return estimateMatss
	 */
	public String getEstimateMatss() {
		return estimateMatss;
	}

	/**
	 * estimateMatssを設定します。
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(String estimateMatss) {
		this.estimateMatss = estimateMatss;
	}

	/**
	 * estimateStandardAmountを取得します。
	 * @return estimateStandardAmount
	 */
	public String getEstimateStandardAmount() {
		return estimateStandardAmount;
	}

	/**
	 * estimateStandardAmountを設定します。
	 * @param estimateStandardAmount estimateStandardAmount
	 */
	public void setEstimateStandardAmount(String estimateStandardAmount) {
		this.estimateStandardAmount = estimateStandardAmount;
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
	 * ctmRemarkを取得します。
	 * @return ctmRemark
	 */
	public String getCtmRemark() {
		return ctmRemark;
	}
	
	/**
	 * 客先品番連結した結果を返す
	 * @return
	 */
	public String getCtmItemCds(){
		String itemCd = "";
		
		if( this.ctmItemCd01 != null && !this.ctmItemCd01.isEmpty() ){
			itemCd = itemCd + this.ctmItemCd01;
		}

		if( this.ctmItemCd02 != null && !this.ctmItemCd02.isEmpty() ){
			itemCd = itemCd + this.ctmItemCd02;
		}

		if( this.ctmItemCd03 != null && !this.ctmItemCd03.isEmpty() ){
			itemCd = itemCd + this.ctmItemCd03;
		}

		return itemCd;
	}
	
	/**
	 * 客先品番連結した結果を返す
	 * @return
	 */
	public String getCtmItemNames(){
		String itemName = "";
		
		if( this.ctmItemName01 != null && !this.ctmItemName01.isEmpty() ){
			itemName = itemName + this.ctmItemName01;
		}

		if( this.ctmItemName02 != null && !this.ctmItemName02.isEmpty() ){
			itemName = itemName + this.ctmItemName02;
		}

		if( this.ctmItemName03 != null && !this.ctmItemName03.isEmpty() ){
			itemName = itemName + this.ctmItemName03;
		}

		return itemName;
	}
	
	
	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * frstOrderRowNoを取得します。
	 * @return frstOrderRowNo
	 */
	public BigDecimal getFrstOrderRowNo() {
		return frstOrderRowNo;
	}

	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}

	/**
	 * frstOrderRowNoを設定します。
	 * @param frstOrderRowNo frstOrderRowNo
	 */
	public void setFrstOrderRowNo(BigDecimal frstOrderRowNo) {
		this.frstOrderRowNo = frstOrderRowNo;
	}

	/**
	 * ctmRemarkを設定します。
	 * @param ctmRemark ctmRemark
	 */
	public void setCtmRemark(String ctmRemark) {
		this.ctmRemark = ctmRemark;
	}

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
	 * errorMessageを取得します。
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * errorMessageを設定します。
	 * @param errorMessage errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * inputApprovalFlgを取得します。
	 * @return inputApprovalFlg
	 */
	public BigDecimal getInputApprovalFlg() {
		return inputApprovalFlg;
	}

	/**
	 * inputApprovalFlgを設定します。
	 * @param inputApprovalFlg inputApprovalFlg
	 */
	public void setInputApprovalFlg(BigDecimal inputApprovalFlg) {
		this.inputApprovalFlg = inputApprovalFlg;
	}

	/**
	 * delDateSendFlgを取得します。
	 * @return delDateSendFlg
	 */
	public BigDecimal getDelDateSendFlg() {
		return delDateSendFlg;
	}

	/**
	 * delDateSendFlgを設定します。
	 * @param delDateSendFlg delDateSendFlg
	 */
	public void setDelDateSendFlg(BigDecimal delDateSendFlg) {
		this.delDateSendFlg = delDateSendFlg;
	}

	/**
	 * checkedOrderQtyを取得します。
	 * @return checkedOrderQty
	 */
	public BigDecimal getCheckedOrderQty() {
		return checkedOrderQty;
	}

	/**
	 * checkedOrderQtyを設定します。
	 * @param checkedOrderQty checkedOrderQty
	 */
	public void setCheckedOrderQty(BigDecimal checkedOrderQty) {
		this.checkedOrderQty = checkedOrderQty;
	}

	/**
	 * checkedMatssを取得します。
	 * @return checkedMatss
	 */
	public BigDecimal getCheckedMatss() {
		return checkedMatss;
	}

	/**
	 * checkedMatssを設定します。
	 * @param checkedMatss checkedMatss
	 */
	public void setCheckedMatss(BigDecimal checkedMatss) {
		this.checkedMatss = checkedMatss;
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
	 * viewSeqを取得します。
	 * @return viewSeq
	 */
	public BigDecimal getViewSeq() {
		return viewSeq;
	}

	/**
	 * viewSeqを設定します。
	 * @param viewSeq viewSeq
	 */
	public void setViewSeq(BigDecimal viewSeq) {
		this.viewSeq = viewSeq;
	}

	/**
	 * headDelFlgを取得します。
	 * @return headDelFlg
	 */
	public BigDecimal getHeadDelFlg() {
		return headDelFlg;
	}

	/**
	 * headDelFlgを設定します。
	 * @param headDelFlg headDelFlg
	 */
	public void setHeadDelFlg(BigDecimal headDelFlg) {
		this.headDelFlg = headDelFlg;
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
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	//20220512 add S.Fujimaki受注詳細　排他追加
	/**
	* OrderDetailDateを設定します。
	* @param orderDetailDate orderDetailDate
	*/
	public void setOrderDetailDate(Timestamp orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}

	/**
	* OrderDetailDateを取得します。
	* @return orderDetailDate
	*/
	public Timestamp getOrderDetailDate() {
		return orderDetailDate;
	}
	//20220512 add S.Fujimaki受注詳細　排他追加
}

