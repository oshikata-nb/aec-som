/*
 * Created on Tue Mar 24 09:04:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * ShippingBaseクラス.
 * @author kanri-user
 */
public class ShippingBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHIPPING";


//	/** IDアノテーション shippingNo. */
//	public static final String shippingNo_ID = "assigned";

	/** COLUMNアノテーション shippingNo. */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingInstructDate. */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";

	/** COLUMNアノテーション scheduledShippingDate. */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション tantoCd. */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション orderNo. */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo. */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション venderDivision. */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション shippingStatus. */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション summery. */
	public static final String summery_COLUMN = "SUMMERY";

	/** COLUMNアノテーション deliveryComp. */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション shippingResultDate. */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション shippingSlipNo. */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";

	/** COLUMNアノテーション shippingSlipRowNo. */
	public static final String shippingSlipRowNo_COLUMN = "SHIPPING_SLIP_ROW_NO";

	/** COLUMNアノテーション slipPublishComp. */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate. */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション slipShippingOrderComp. */
	public static final String slipShippingOrderComp_COLUMN = "SLIP_SHIPPING_ORDER_COMP";

	/** COLUMNアノテーション slipShippingOrderDate. */
	public static final String slipShippingOrderDate_COLUMN = "SLIP_SHIPPING_ORDER_DATE";

	/** COLUMNアノテーション slipShippingScheduleComp. */
	public static final String slipShippingScheduleComp_COLUMN = "SLIP_SHIPPING_SCHEDULE_COMP";

	/** COLUMNアノテーション slipShippingScheduleDate. */
	public static final String slipShippingScheduleDate_COLUMN = "SLIP_SHIPPING_SCHEDULE_DATE";

	/** COLUMNアノテーション slipShippingTagComp. */
	public static final String slipShippingTagComp_COLUMN = "SLIP_SHIPPING_TAG_COMP";

	/** COLUMNアノテーション slipShippingTagDate. */
	public static final String slipShippingTagDate_COLUMN = "SLIP_SHIPPING_TAG_DATE";

	/** COLUMNアノテーション slipShippingRequestComp. */
	public static final String slipShippingRequestComp_COLUMN = "SLIP_SHIPPING_REQUEST_COMP";

	/** COLUMNアノテーション slipShippingRequestDate. */
	public static final String slipShippingRequestDate_COLUMN = "SLIP_SHIPPING_REQUEST_DATE";
	
	/** COLUMNアノテーション slipShippingDelivelyComp. */
	public static final String slipShippingDelivelyComp_COLUMN = "SLIP_SHIPPING_DELIVELY_COMP";

	/** COLUMNアノテーション slipShippingDelivelyDate. */
	public static final String slipShippingDelivelyDate_COLUMN = "SLIP_SHIPPING_DELIVELY_DATE";

	/** COLUMNアノテーション slipShippingNewTagComp. */
	public static final String slipShippingNewTagComp_COLUMN = "SLIP_SHIPPING_NEW_TAG_COMP";

	/** COLUMNアノテーション slipShippingNewTagDate. */
	public static final String slipShippingNewTagDate_COLUMN = "SLIP_SHIPPING_NEW_TAG_DATE";

	/** COLUMNアノテーション slipShippingPostalComp. */
	public static final String slipShippingPostalComp_COLUMN = "SLIP_SHIPPING_POSTAL_COMP";

	/** COLUMNアノテーション slipShippingPostalDate. */
	public static final String slipShippingPostalDate_COLUMN = "SLIP_SHIPPING_POSTAL_DATE";
	
	/** COLUMNアノテーション slipShippingFareComp. */
	public static final String slipShippingFareComp_COLUMN = "SLIP_SHIPPING_FARE_COMP";

	/** COLUMNアノテーション slipShippingFareDate. */
	public static final String slipShippingFareDate_COLUMN = "SLIP_SHIPPING_FARE_DATE";

	/** COLUMNアノテーション carryCd. */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション suggestedDeliverlimit. */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション carryFare. */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション sendingOffNumber. */
	public static final String sendingOffNumber_COLUMN = "SENDING_OFF_NUMBER";

	/** COLUMNアノテーション carryBC. */
	public static final String carryBC_COLUMN = "CARRY_BC";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private String shippingNo;

	private java.sql.Timestamp shippingInstructDate;

	private java.sql.Timestamp scheduledShippingDate;

	private String tantoCd;

	private String orderNo;

	private java.math.BigDecimal orderRowNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private java.math.BigDecimal shippingStatus;

	private String itemCd;

	private String summery;

	private java.math.BigDecimal deliveryComp;

	private java.sql.Timestamp shippingResultDate;

	private String shippingSlipNo;

	private java.math.BigDecimal shippingSlipRowNo;

	private java.math.BigDecimal slipPublishComp;

	private java.sql.Timestamp slipPublishDate;

	private java.math.BigDecimal slipShippingOrderComp;

	private java.sql.Timestamp slipShippingOrderDate;

	private java.math.BigDecimal slipShippingScheduleComp;

	private java.sql.Timestamp slipShippingScheduleDate;

	private java.math.BigDecimal slipShippingTagComp;

	private java.sql.Timestamp slipShippingTagDate;

	private java.math.BigDecimal slipShippingRequestComp;

	private java.sql.Timestamp slipShippingRequestDate;
	
	private java.math.BigDecimal slipShippingDelivelyComp;

	private java.sql.Timestamp slipShippingDelivelyDate;

	private java.math.BigDecimal slipShippingNewTagComp;

	private java.sql.Timestamp slipShippingNewTagDate;

	private java.math.BigDecimal slipShippingPostalComp;

	private java.sql.Timestamp slipShippingPostalDate;

	private java.math.BigDecimal slipShippingFareComp;

	private java.sql.Timestamp slipShippingFareDate;
	
	private String carryCd;

	private java.sql.Timestamp suggestedDeliverlimit;

	private java.math.BigDecimal carryFare;

	private String sendingOffNumber;

	private java.sql.Timestamp inputDate;

	private String carryBC;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

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
	 * shippingInstructDate取得.
	 * @return shippingInstructDate
	 */
	public java.sql.Timestamp getShippingInstructDate() {
		return this.shippingInstructDate;
	}

	/**
	 * shippingInstructDate設定.
	 * @param shippingInstructDate shippingInstructDate
	 */
	public void setShippingInstructDate(final java.sql.Timestamp shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
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
	public void setScheduledShippingDate(final java.sql.Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
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
	 * venderDivision取得.
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return this.venderDivision;
	}

	/**
	 * venderDivision設定.
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
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
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
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
	 * summery取得.
	 * @return summery
	 */
	public String getSummery() {
		return this.summery;
	}

	/**
	 * summery設定.
	 * @param summery summery
	 */
	public void setSummery(final String summery) {
		this.summery = summery;
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
	 * shippingResultDate取得.
	 * @return shippingResultDate
	 */
	public java.sql.Timestamp getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * shippingResultDate設定.
	 * @param shippingResultDate shippingResultDate
	 */
	public void setShippingResultDate(final java.sql.Timestamp shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
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
	 * shippingSlipRowNo取得.
	 * @return shippingSlipRowNo
	 */
	public java.math.BigDecimal getShippingSlipRowNo() {
		return this.shippingSlipRowNo;
	}

	/**
	 * shippingSlipRowNo設定.
	 * @param shippingSlipRowNo shippingSlipRowNo
	 */
	public void setShippingSlipRowNo(final java.math.BigDecimal shippingSlipRowNo) {
		this.shippingSlipRowNo = shippingSlipRowNo;
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
	 * slipPublishDate取得.
	 * @return slipPublishDate
	 */
	public java.sql.Timestamp getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * slipPublishDate設定.
	 * @param slipPublishDate slipPublishDate
	 */
	public void setSlipPublishDate(final java.sql.Timestamp slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
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
	public void setSlipShippingOrderComp(final java.math.BigDecimal slipShippingOrderComp) {
		this.slipShippingOrderComp = slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderDate取得.
	 * @return slipShippingOrderDate
	 */
	public java.sql.Timestamp getSlipShippingOrderDate() {
		return this.slipShippingOrderDate;
	}

	/**
	 * slipShippingOrderDate設定.
	 * @param slipShippingOrderDate slipShippingOrderDate
	 */
	public void setSlipShippingOrderDate(final java.sql.Timestamp slipShippingOrderDate) {
		this.slipShippingOrderDate = slipShippingOrderDate;
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
	public void setSlipShippingScheduleComp(final java.math.BigDecimal slipShippingScheduleComp) {
		this.slipShippingScheduleComp = slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleDate取得.
	 * @return slipShippingScheduleDate
	 */
	public java.sql.Timestamp getSlipShippingScheduleDate() {
		return this.slipShippingScheduleDate;
	}

	/**
	 * slipShippingScheduleDate設定.
	 * @param slipShippingScheduleDate slipShippingScheduleDate
	 */
	public void setSlipShippingScheduleDate(final java.sql.Timestamp slipShippingScheduleDate) {
		this.slipShippingScheduleDate = slipShippingScheduleDate;
	}

	/**
	 * slipShippingDelivelyCompを取得します。
	 * @return slipShippingDelivelyComp
	 */
	public java.math.BigDecimal getSlipShippingDelivelyComp() {
		return slipShippingDelivelyComp;
	}

	/**
	 * slipShippingDelivelyCompを設定します。
	 * @param slipShippingDelivelyComp slipShippingDelivelyComp
	 */
	public void setSlipShippingDelivelyComp(
			java.math.BigDecimal slipShippingDelivelyComp) {
		this.slipShippingDelivelyComp = slipShippingDelivelyComp;
	}

	/**
	 * slipShippingDelivelyDateを取得します。
	 * @return slipShippingDelivelyDate
	 */
	public java.sql.Timestamp getSlipShippingDelivelyDate() {
		return slipShippingDelivelyDate;
	}

	/**
	 * slipShippingDelivelyDateを設定します。
	 * @param slipShippingDelivelyDate slipShippingDelivelyDate
	 */
	public void setSlipShippingDelivelyDate(
			java.sql.Timestamp slipShippingDelivelyDate) {
		this.slipShippingDelivelyDate = slipShippingDelivelyDate;
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
	 * slipShippingNewTagDateを取得します。
	 * @return slipShippingNewTagDate
	 */
	public java.sql.Timestamp getSlipShippingNewTagDate() {
		return slipShippingNewTagDate;
	}

	/**
	 * slipShippingNewTagDateを設定します。
	 * @param slipShippingNewTagDate slipShippingNewTagDate
	 */
	public void setSlipShippingNewTagDate(java.sql.Timestamp slipShippingNewTagDate) {
		this.slipShippingNewTagDate = slipShippingNewTagDate;
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

	/**
	 * slipShippingPostalDateを取得します。
	 * @return slipShippingPostalDate
	 */
	public java.sql.Timestamp getSlipShippingPostalDate() {
		return slipShippingPostalDate;
	}

	/**
	 * slipShippingPostalDateを設定します。
	 * @param slipShippingPostalDate slipShippingPostalDate
	 */
	public void setSlipShippingPostalDate(java.sql.Timestamp slipShippingPostalDate) {
		this.slipShippingPostalDate = slipShippingPostalDate;
	}
	
	/**
	 * slipShippingFareCompを取得します。
	 * @return slipShippingFareComp
	 */
	public java.math.BigDecimal getSlipShippingFareComp() {
		return slipShippingFareComp;
	}

	/**
	 * slipShippingFareCompを設定します。
	 * @param slipShippingFareComp slipShippingFareComp
	 */
	public void setSlipShippingFareComp(java.math.BigDecimal slipShippingFareComp) {
		this.slipShippingFareComp = slipShippingFareComp;
	}

	/**
	 * slipShippingFareDateを取得します。
	 * @return slipShippingFareDate
	 */
	public java.sql.Timestamp getSlipShippingFareDate() {
		return slipShippingFareDate;
	}

	/**
	 * slipShippingFareDateを設定します。
	 * @param slipShippingFareDate slipShippingFareDate
	 */
	public void setSlipShippingFareDate(java.sql.Timestamp slipShippingFareDate) {
		this.slipShippingFareDate = slipShippingFareDate;
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
	public void setSlipShippingTagComp(final java.math.BigDecimal slipShippingTagComp) {
		this.slipShippingTagComp = slipShippingTagComp;
	}

	/**
	 * slipShippingTagDate取得.
	 * @return slipShippingTagDate
	 */
	public java.sql.Timestamp getSlipShippingTagDate() {
		return this.slipShippingTagDate;
	}

	/**
	 * slipShippingTagDate設定.
	 * @param slipShippingTagDate slipShippingTagDate
	 */
	public void setSlipShippingTagDate(final java.sql.Timestamp slipShippingTagDate) {
		this.slipShippingTagDate = slipShippingTagDate;
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
	public void setSlipShippingRequestComp(final java.math.BigDecimal slipShippingRequestComp) {
		this.slipShippingRequestComp = slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestDate取得.
	 * @return slipShippingRequestDate
	 */
	public java.sql.Timestamp getSlipShippingRequestDate() {
		return this.slipShippingRequestDate;
	}

	/**
	 * slipShippingRequestDate設定.
	 * @param slipShippingRequestDate slipShippingRequestDate
	 */
	public void setSlipShippingRequestDate(final java.sql.Timestamp slipShippingRequestDate) {
		this.slipShippingRequestDate = slipShippingRequestDate;
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
	 * suggestedDeliverlimit取得.
	 * @return suggestedDeliverlimit
	 */
	public java.sql.Timestamp getSuggestedDeliverlimit() {
		return this.suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimit設定.
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(final java.sql.Timestamp suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * carryFare取得.
	 * @return carryFare
	 */
	public java.math.BigDecimal getCarryFare() {
		return this.carryFare;
	}

	/**
	 * carryFare設定.
	 * @param carryFare carryFare
	 */
	public void setCarryFare(final java.math.BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * sendingOffNumber取得.
	 * @return sendingOffNumber
	 */
	public String getSendingOffNumber() {
		return this.sendingOffNumber;
	}

	/**
	 * sendingOffNumber設定.
	 * @param sendingOffNumber sendingOffNumber
	 */
	public void setSendingOffNumber(final String sendingOffNumber) {
		this.sendingOffNumber = sendingOffNumber;
	}

	/**
	 * carryBC設定.
	 * @param carryBC carryBC
	 */
	public void setCarryBC(final String carryBC) {
		this.carryBC = carryBC;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getCarryBC() {
		return this.carryBC;
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
