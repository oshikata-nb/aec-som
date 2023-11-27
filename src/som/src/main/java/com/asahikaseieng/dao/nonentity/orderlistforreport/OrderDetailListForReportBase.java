/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderlistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * OrderDetailListForReportクラス.
 * @author kanri-user
 */
public class OrderDetailListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderDetailListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション rowNo */
	public static final String rowNo_COLUMN = "ROW_NO";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

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

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション estimateStandardAmount */
	public static final String estimateStandardAmount_COLUMN = "ESTIMATE_STANDARD_AMOUNT";

	/** COLUMNアノテーション estimateMatss */
	public static final String estimateMatss_COLUMN = "ESTIMATE_MATSS";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";

	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";

	//
	// インスタンスフィールド
	//

	private String orderNo;

	private java.math.BigDecimal rowNo;

	private String shippingNo;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String otherCompanyCd1;

	private java.math.BigDecimal orderQty;

	private java.math.BigDecimal orderUnitprice;

	private java.math.BigDecimal standardUnitprice;

	private java.math.BigDecimal standardDiscount;

	private java.math.BigDecimal specialDiscount;

	private java.math.BigDecimal tmpUnitpriceFlg;

	private java.math.BigDecimal matss;

	private java.math.BigDecimal estimateStandardAmount;

	private java.math.BigDecimal estimateMatss;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private String statusName;

	private String inputorName;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	private String updatorName;

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
	 * shippingNo取得.
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * shippingNo設定.
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
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
	 * inputorName取得.
	 * @return inputorName
	 */
	public String getInputorName() {
		return this.inputorName;
	}

	/**
	 * inputorName設定.
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
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
	 * updatorName取得.
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return this.updatorName;
	}

	/**
	 * updatorName設定.
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
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

