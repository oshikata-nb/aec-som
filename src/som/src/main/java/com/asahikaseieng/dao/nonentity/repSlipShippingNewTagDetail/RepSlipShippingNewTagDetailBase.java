/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingNewTagDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingNewTagDetailクラス.
 * @author kanri-user
 */
public class RepSlipShippingNewTagDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingNewTagDetailBase() {
	}

	//
	// 定数
	//


	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";
	
	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション cdNameStyle */
	public static final String cdNameStyle_COLUMN = "CD_NAME_STYLE";
	
	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション shippinfSlipNo */
	public static final String shippinfSlipNo_COLUMN = "SHIPPING_SLIP_NO";
	
	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";
	
	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	
	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション reportrOutputNum */
	public static final String reportrOutputNum_COLUMN = "REPOTR_OUTPUT_NUM";
	//
	// インスタンスフィールド
	//
	
	private String key;
	
	private String orderNo;
	
	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String cdNameStyle;

	private String orderQty;

	private String shippinfSlipNo;

	private String scheduledShippingDate;

	private String orderRowNo;

	private String deliveryCd;

	private String carryCd;

	private String repotrOutputNum;


	//
	// インスタンスメソッド
	//
	
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
	 * cdNameStyleを取得します。
	 * @return cdNameStyle
	 */
	public String getCdNameStyle() {
		return cdNameStyle;
	}

	/**
	 * cdNameStyleを設定します。
	 * @param cdNameStyle cdNameStyle
	 */
	public void setCdNameStyle(String cdNameStyle) {
		this.cdNameStyle = cdNameStyle;
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
	 * shippinfSlipNoを取得します。
	 * @return shippinfSlipNo
	 */
	public String getShippinfSlipNo() {
		return shippinfSlipNo;
	}

	/**
	 * shippinfSlipNoを設定します。
	 * @param shippinfSlipNo shippinfSlipNo
	 */
	public void setShippinfSlipNo(String shippinfSlipNo) {
		this.shippinfSlipNo = shippinfSlipNo;
	}

	/**
	 * orderRowNoを取得します。
	 * @return orderRowNo
	 */
	public String getOrderRowNo() {
		return orderRowNo;
	}

	/**
	 * orderRowNoを設定します。
	 * @param orderRowNo orderRowNo
	 */
	public void setOrderRowNo(String orderRowNo) {
		this.orderRowNo = orderRowNo;
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
	 * scheduledShippingDateを取得します。
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return scheduledShippingDate;
	}

	/**
	 * scheduledShippingDateを設定します。
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

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
	 * repotrOutputNumを取得します。
	 * @return repotrOutputNum
	 */
	public String getRepotrOutputNum() {
		return repotrOutputNum;
	}

	/**
	 * repotrOutputNumを設定します。
	 * @param repotrOutputNum repotrOutputNum
	 */
	public void setRepotrOutputNum(String repotrOutputNum) {
		this.repotrOutputNum = repotrOutputNum;
	}
	
}

