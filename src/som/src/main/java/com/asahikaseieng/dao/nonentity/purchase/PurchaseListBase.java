/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 発注一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション 購買NO */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション 発注番号 */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション 発注者No */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション 発注日 */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション 仕入先コード */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション 仕入先名称 */
	public static final String supplierName_COLUMN = "SUPPLIER_NAME";

	/** COLUMNアノテーション 品目名称 */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション 発注数量 */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション 単位 */
	public static final String orderUnit_COLUMN = "ORDER_UNIT";

	/** COLUMNアノテーション 荷姿 */
	public static final String loadForm_COLUMN = "LOAD_FORM";

	/** COLUMNアノテーション 重量 */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション 納品希望日 */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション 購買ステータス */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション 納入先 */
	public static final String aheadPaymentName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション 運用管理単位 */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注日 */
	private Timestamp orderDate;

	/** 仕入先コード */
	private String venderCd;

	/** 仕入先 */
	private String supplierName;

	/** 品目名称 */
	private String itemName;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 単位 */
	private String orderUnit;

	/** 荷姿 */
	private String loadForm;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 納品希望日 */
	private Timestamp suggestedDeliverlimitDate;

	/** 納入先名称 */
	private String aheadPaymentName;

	/** 運用管理単位 */
	private String unitDiv;

	/** 購買ステータス */
	private BigDecimal status;

	/** fusokuryo */
	private BigDecimal checkQuantity;

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NOを取得します。
	 * @return purchaseNo
	 */
	public String getPurchaseNo() {
		return purchaseNo;
	}

	/**
	 * 購買NOを設定します。
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注日取得
	 * @return Timestamp 発注日
	 */
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * 発注日設定
	 * @param orderDate 発注日
	 */
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 品目名称取得
	 * @return itemName 品目名称
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 品目名称設定
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 仕入先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 仕入先コードを設定します。
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 仕入先取得
	 * @return Timestamp 仕入先
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * 仕入先設定
	 * @param supplierName 仕入先
	 */
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * 発注数量取得
	 * @return BigDecimal 発注数量
	 */
	public BigDecimal getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定
	 * @param orderQuantity 発注数量
	 */
	public void setOrderQuantity(final BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 単位取得
	 * @return 単位
	 */
	public String getOrderUnit() {
		return orderUnit;
	}

	/**
	 * 単位設定
	 * @param orderUnit 単位数量
	 */
	public void setOrderUnit(final String orderUnit) {
		this.orderUnit = orderUnit;
	}

	/**
	 * 荷姿取得
	 * @return 荷姿
	 */
	public String getLoadForm() {
		return loadForm;
	}

	/**
	 * 荷姿設定
	 * @param loadForm 荷姿
	 */
	public void setLoadForm(final String loadForm) {
		this.loadForm = loadForm;
	}

	/**
	 * 重量取得
	 * @return BigDecimal 重量
	 */
	public BigDecimal getOrderConvertQuantity() {
		return this.orderConvertQuantity;
	}

	/**
	 * 重量設定
	 * @param orderConvertQuantity 重量
	 */
	public void setOrderConvertQuantity(final BigDecimal orderConvertQuantity) {
		this.orderConvertQuantity = orderConvertQuantity;
	}

	/**
	 * 納品希望日取得
	 * @return 納品希望日
	 */
	public Timestamp getSuggestedDeliverlimitDate() {
		return this.suggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定
	 * @param suggestedDeliverlimitDate 納品希望日
	 */
	public void setSuggestedDeliverlimitDate(
			final Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

	/**
	 * 納入先取得
	 * @return 納入先
	 */
	public String getAheadPaymentName() {
		return aheadPaymentName;
	}

	/**
	 * 納入先設定
	 * @param aheadPaymentName 納入先
	 */
	public void setAheadPaymentName(final String aheadPaymentName) {
		this.aheadPaymentName = aheadPaymentName;
	}

	/**
	 * 購買ステータス取得
	 * @return BigDecimal
	 */
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定
	 * @param status 購買ステータス
	 */
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * 発注書NO取得
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 運用管理単位を取得します。
	 * @return unitDivision
	 */
	public String getUnitDiv() {
		return unitDiv;
	}

	/**
	 * 運用管理単位を設定します。
	 * @param unitDiv 運用管理単位
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
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
	 * checkQuantityを取得します。
	 * @return checkQuantity
	 */
	public BigDecimal getCheckQuantity() {
		return checkQuantity;
	}

	/**
	 * checkQuantityを設定します。
	 * @param checkQuantity checkQuantity
	 */
	public void setCheckQuantity(final BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

}
