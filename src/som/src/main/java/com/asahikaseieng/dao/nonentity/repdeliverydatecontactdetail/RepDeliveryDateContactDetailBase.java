/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repdeliverydatecontactdetail;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportListクラス.
 * @author 
 */
public class RepDeliveryDateContactDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDeliveryDateContactDetailBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "PK_NO";
	/** COLUMNアノテーション pkRow */
	public static final String pkRow_COLUMN = "PK_ROW";
	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";
	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";
	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";
	/** COLUMNアノテーション unitOfOperationManagement */
	public static final String unitOfOperationManagement_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";
	/** COLUMNアノテーション uoomName */
	public static final String uoomName_COLUMN = "UOOM_NAME";
	/** COLUMNアノテーション orderAmount */
	public static final String orderAmount_COLUMN = "ORDER_AMOUNT";
	/** COLUMNアノテーション orderUnitprice */
	public static final String orderUnitprice_COLUMN = "ORDER_UNITPRICE";
	/** COLUMNアノテーション standardUnitprice */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";
	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";
	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";
	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";
	/** COLUMNアノテーション qty */
	public static final String qty_COLUMN = "QTY";
	/** COLUMNアノテーション ctmItemCd */
	public static final String ctmItemCd_COLUMN = "CTM_ITEM_CD";
	/** COLUMNアノテーション ctmItemName */
	public static final String ctmItemName_COLUMN = "CTM_ITEM_NAME";
	/** COLUMNアノテーション ctmStyleOfPacking */
	public static final String ctmStyleOfPacking_COLUMN = "CTM_STYLE_OF_PACKING";
	/** COLUMNアノテーション ctmOrderQty */
	public static final String ctmOrderQty_COLUMN = "CTM_ORDER_QTY";
	/** COLUMNアノテーション ctmCaseNum */
	public static final String ctmCaseNum_COLUMN = "CTM_CASE_NUM";
	/** COLUMNアノテーション ctmOrderPiece */
	public static final String ctmOrderPiece_COLUMN = "CTM_ORDER_PIECE";
	/** COLUMNアノテーション ctmOrderUnitprice */
	public static final String ctmOrderUnitprice_COLUMN = "CTM_ORDER_UNITPRICE";
	// インスタンスフィールド
	//
	private String key;
	
	private String pkNo;
	
	private BigDecimal pkRow;
	
	private BigDecimal seq;
	
	private String itemCd;
	
	private String itemName;
	
	private String styleOfPacking;
	
	private BigDecimal allUpWeight;
	
	private BigDecimal orderQty;
	
	private String unitOfOperationManagement;
	
	private String uoomName;
	
	private BigDecimal orderAmount;
	
	private BigDecimal orderUnitprice;
	
	private BigDecimal standardUnitprice;
	
	private BigDecimal standardDiscount;
	
	private BigDecimal specialDiscount;
	
	private BigDecimal matss;
	
	private String qty;
	
	private String ctmItemCd;
	
	private String ctmItemName;
	
	private String ctmStyleOfPacking;
	
	private BigDecimal ctmOrderQty;
	
	private BigDecimal ctmCaseNum;
	
	private BigDecimal ctmOrderPiece;
	
	private BigDecimal ctmOrderUnitprice;
	
	//
	// インスタンスメソッド
	//

	/**
	 * keyを取得します。
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * keyを設定します。
	 * @param key key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}
	
	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * pkRowを取得します。
	 * @return pkRow
	 */
	public BigDecimal getPkRow() {
		return pkRow;
	}

	/**
	 * pkRowを設定します。
	 * @param pkRow pkRow
	 */
	public void setPkRow(BigDecimal pkRow) {
		this.pkRow = pkRow;
	}

	
	/**
	 * seqを取得します。
	 * @return seq
	 */
	public BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(BigDecimal seq) {
		this.seq = seq;
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
	 * uoomNameを取得します。
	 * @return uoomName
	 */
	public String getUoomName() {
		return uoomName;
	}

	/**
	 * uoomNameを設定します。
	 * @param uoomName uoomName
	 */
	public void setUoomName(String uoomName) {
		this.uoomName = uoomName;
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
	 * ctmItemCdを取得します。
	 * @return ctmItemCd
	 */
	public String getCtmItemCd() {
		return ctmItemCd;
	}

	/**
	 * ctmItemCdを設定します。
	 * @param ctmItemCd ctmItemCd
	 */
	public void setCtmItemCd(String ctmItemCd) {
		this.ctmItemCd = ctmItemCd;
	}

	/**
	 * ctmItemNameを取得します。
	 * @return ctmItemName
	 */
	public String getCtmItemName() {
		return ctmItemName;
	}

	/**
	 * ctmItemNameを設定します。
	 * @param ctmItemName ctmItemName
	 */
	public void setCtmItemName(String ctmItemName) {
		this.ctmItemName = ctmItemName;
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
}


