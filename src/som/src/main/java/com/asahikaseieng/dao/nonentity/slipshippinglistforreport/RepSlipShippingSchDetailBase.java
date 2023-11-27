/*
 * Created on 2009/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshippinglistforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingSchDetailクラス.
 * 19/12/06　AECS佐藤　出荷予定一覧の改修(出荷後在庫数追加)
 * @author kanri-user
 */
public class RepSlipShippingSchDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingSchDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション sumOrderQty */
	public static final String sumOrderQty_COLUMN = "SUM_ORDER_QTY";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";
	
	//2019/12/06 AECS佐藤 追加
	/** COLUMNアノテーション afterInventoryQty */
	public static final String afterInventoryQty_COLUMN = "AFTER_INVENTORY_QTY";

	//
	// インスタンスフィールド
	//

	private String key;

	private String itemCd;

	private String itemName;

	private String styleOfPacking;

	private String sumOrderQty;

	private String inventoryQty;

	private String scheduledShippingDate;

	private String locationCd;
	
	//2019/12/06 AECS佐藤 追加
	private String afterInventoryQty;

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
	 * sumOrderQty取得.
	 * @return sumOrderQty
	 */
	public String getSumOrderQty() {
		return this.sumOrderQty;
	}

	/**
	 * sumOrderQty設定.
	 * @param sumOrderQty sumOrderQty
	 */
	public void setSumOrderQty(final String sumOrderQty) {
		this.sumOrderQty = sumOrderQty;
	}

	/**
	 * inventoryQty取得.
	 * @return inventoryQty
	 */
	public String getInventoryQty() {
		return this.inventoryQty;
	}

	/**
	 * inventoryQty設定.
	 * @param inventoryQty inventoryQty
	 */
	public void setInventoryQty(final String inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public String getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(final String scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
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
	
	//2019/12/06 AECS佐藤 追加
	/**
	 * 取得.
	 * @return afterInventoryQty
	 */
	public String getAfterInventoryQty() {
		return this.afterInventoryQty;
	}

	/**
	 * afterInventoryQty設定.
	 * @param afterInventoryQty afterInventoryQty
	 */
	public void setAfterInventoryQty(final String afterInventoryQty) {
		this.afterInventoryQty = afterInventoryQty;
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

