/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 出荷帳票_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class SlipShippingListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SlipShippingListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHIPPING";

	/** COLUMNアノテーション shippingNo. */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingSlipNo. */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";

	/** COLUMNアノテーション orderNo. */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション scheduledShippingDate. */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション locationCd. */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション upperLocationCd. */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";

	/** COLUMNアノテーション shippingStatus. */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション carryCd. */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName1. */
	public static final String carryName1_COLUMN = "CARRY_NAME1";

	/** COLUMNアノテーション carryName2. */
	public static final String carryName2_COLUMN = "CARRY_NAME2";

	/** COLUMNアノテーション labelPublish. */
	public static final String labelPublish_COLUMN = "LABEL_PUBLISH";

	/** COLUMNアノテーション slipPublishComp. */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipShippingOrderComp. */
	public static final String slipShippingOrderComp_COLUMN = "SLIP_SHIPPING_ORDER_COMP";

	/** COLUMNアノテーション slipShippingScheduleComp. */
	public static final String slipShippingScheduleComp_COLUMN = "SLIP_SHIPPING_SCHEDULE_COMP";

	/** COLUMNアノテーション slipShippingTagComp. */
	public static final String slipShippingTagComp_COLUMN = "SLIP_SHIPPING_TAG_COMP";

	/** COLUMNアノテーション slipShippingRequestComp. */
	public static final String slipShippingRequestComp_COLUMN = "SLIP_SHIPPING_REQUEST_COMP";
	
	/** COLUMNアノテーション slipShippingFareComp. */
	public static final String slipShippingFareComp_COLUMN = "SLIP_SHIPPING_FARE_COMP";
	
	/** COLUMNアノテーション slipShippingDeliveryComp. */
	public static final String slipShippingDeliveryComp_COLUMN = "SLIP_SHIPPING_DELIVELY_COMP";
	
	/** COLUMNアノテーション slipShippingNewTagComp. */
	public static final String slipShippingNewTagComp_COLUMN = "SLIP_SHIPPING_NEW_TAG_COMP";

	/** COLUMNアノテーション slipSippingPostalComp. */
	public static final String slipShippingPostalComp_COLUMN = "SLIP_SHIPPING_POSTAL_COMP";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション searchKana. */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション orderQty. */
	public static final String orderQty_COLUMN = "ORDER_QTY";
	//
	// インスタンスフィールド
	//

	private String shippingNo;

	private java.sql.Timestamp scheduledShippingDate;

	private String orderNo;

	private String locationCd;

	private String upperLocationCd;

	private java.math.BigDecimal shippingStatus;

	private String shippingSlipNo;

	private java.math.BigDecimal slipPublishComp;

	private java.math.BigDecimal slipShippingOrderComp;

	private java.math.BigDecimal slipShippingScheduleComp;

	private java.math.BigDecimal slipShippingTagComp;

	private java.math.BigDecimal slipShippingRequestComp;

	private java.math.BigDecimal slipShippingFareComp;
	
	private java.math.BigDecimal slipShippingDeliveryComp;

	private java.math.BigDecimal slipShippingNewTagComp;

	private java.math.BigDecimal slipShippingPostalComp;

	private String carryCd;

	private String carryName1;

	private String carryName2;

	private java.math.BigDecimal labelPublish;

	private java.sql.Timestamp updateDate;

	private String itemName;

	private String searchKana;

	private java.math.BigDecimal orderQty;

	//
	// インスタンスメソッド
	//

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
	 * scheduledShippingDate取得.
	 * @return scheduledShippingDate
	 */
	public java.sql.Timestamp getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * scheduledShippingDate設定.
	 * @param scheduledShippingDate scheduledShippingDate
	 */
	public void setScheduledShippingDate(
			final java.sql.Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
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
	 * shippingStatus取得.
	 * @return shippingStatus
	 */
	public java.math.BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * shippingStatus設定.
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(final java.math.BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * shippingSlipNo取得.
	 * @return shippingSlipNo
	 */
	public String getShippingSlipNo() {
		return this.shippingSlipNo;
	}

	/**
	 * shippingSlipNo設定.
	 * @param shippingSlipNo shippingSlipNo
	 */
	public void setShippingSlipNo(final String shippingSlipNo) {
		this.shippingSlipNo = shippingSlipNo;
	}

	/**
	 * slipPublishComp取得.
	 * @return slipPublishComp
	 */
	public java.math.BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * slipPublishComp設定.
	 * @param slipPublishComp slipPublishComp
	 */
	public void setSlipPublishComp(final java.math.BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * slipShippingOrderComp取得.
	 * @return slipShippingOrderComp
	 */
	public java.math.BigDecimal getSlipShippingOrderComp() {
		return this.slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderComp設定.
	 * @param slipShippingOrderComp slipShippingOrderComp
	 */
	public void setSlipShippingOrderComp(
			final java.math.BigDecimal slipShippingOrderComp) {
		this.slipShippingOrderComp = slipShippingOrderComp;
	}

	/**
	 * slipShippingScheduleComp取得.
	 * @return slipShippingScheduleComp
	 */
	public java.math.BigDecimal getSlipShippingScheduleComp() {
		return this.slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleComp設定.
	 * @param slipShippingScheduleComp slipShippingScheduleComp
	 */
	public void setSlipShippingScheduleComp(
			final java.math.BigDecimal slipShippingScheduleComp) {
		this.slipShippingScheduleComp = slipShippingScheduleComp;
	}

	/**
	 * slipShippingTagComp取得.
	 * @return slipShippingTagComp
	 */
	public java.math.BigDecimal getSlipShippingTagComp() {
		return this.slipShippingTagComp;
	}

	/**
	 * slipShippingTagComp設定.
	 * @param slipShippingTagComp slipShippingTagComp
	 */
	public void setSlipShippingTagComp(
			final java.math.BigDecimal slipShippingTagComp) {
		this.slipShippingTagComp = slipShippingTagComp;
	}

	/**
	 * slipShippingRequestComp取得.
	 * @return slipShippingRequestComp
	 */
	public java.math.BigDecimal getSlipShippingRequestComp() {
		return this.slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestComp設定.
	 * @param slipShippingRequestComp slipShippingRequestComp
	 */
	public void setSlipShippingRequestComp(
			final java.math.BigDecimal slipShippingRequestComp) {
		this.slipShippingRequestComp = slipShippingRequestComp;
	}
	
	/**
	 * slipShippingFareComp取得.
	 * @return slipShippingFareComp
	 */
	public java.math.BigDecimal getSlipShippingFareComp() {
		return slipShippingFareComp;
	}

	/**
	 * slipShippingFareComp設定.
	 * @param slipShippingFareComp slipShippingFareComp
	 */
	public void setSlipShippingFareComp(java.math.BigDecimal slipShippingFareComp) {
		this.slipShippingFareComp = slipShippingFareComp;
	}

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}

	/**
	 * carryName1取得.
	 * @return carryName1
	 */
	public String getCarryName1() {
		return this.carryName1;
	}

	/**
	 * carryName1設定.
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(final String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryName2取得.
	 * @return carryName2
	 */
	public String getCarryName2() {
		return this.carryName2;
	}

	/**
	 * carryName2設定.
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(final String carryName2) {
		this.carryName2 = carryName2;
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
	 * labelPublishを取得します。
	 * @return labelPublish
	 */
	public java.math.BigDecimal getLabelPublish() {
		return labelPublish;
	}

	/**
	 * labelPublishを設定します。
	 * @param labelPublish labelPublish
	 */
	public void setLabelPublish(final java.math.BigDecimal labelPublish) {
		this.labelPublish = labelPublish;
	}

	/**
	 * upperLocationCdを取得します。
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return upperLocationCd;
	}

	/**
	 * upperLocationCdを設定します。
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
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
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public java.math.BigDecimal getOrderQty() {
		return orderQty;
	}

	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final java.math.BigDecimal orderQty) {
		this.orderQty = orderQty;
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
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * slipShippingDeliveryCompを取得します。
	 * @return slipShippingDeliveryComp
	 */
	public java.math.BigDecimal getSlipShippingDeliveryComp() {
		return slipShippingDeliveryComp;
	}

	/**
	 * slipShippingDeliveryCompを設定します。
	 * @param slipShippingDeliveryComp slipShippingDeliveryComp
	 */
	public void setSlipShippingDeliveryComp(
			java.math.BigDecimal slipShippingDeliveryComp) {
		this.slipShippingDeliveryComp = slipShippingDeliveryComp;
	}

	/**
	 * slipShippingNewTagCompを取得します。
	 * @return slipShippingNewTagComp
	 */
	public java.math.BigDecimal getSlipShippingNewTagComp() {
		return slipShippingNewTagComp;
	}

	/**
	 * slipShippingNewTagCompを設定します。
	 * @param slipShippingNewTagComp slipShippingNewTagComp
	 */
	public void setSlipShippingNewTagComp(
			java.math.BigDecimal slipShippingNewTagComp) {
		this.slipShippingNewTagComp = slipShippingNewTagComp;
	}

	/**
	 * slipShippingPostalCompを取得します。
	 * @return slipShippingPostalComp
	 */
	public java.math.BigDecimal getSlipShippingPostalComp() {
		return slipShippingPostalComp;
	}

	/**
	 * slipShippingPostalCompを設定します。
	 * @param slipShippingPostalComp slipShippingPostalComp
	 */
	public void setSlipShippingPostalComp(
			java.math.BigDecimal slipShippingPostalComp) {
		this.slipShippingPostalComp = slipShippingPostalComp;
	}
}
