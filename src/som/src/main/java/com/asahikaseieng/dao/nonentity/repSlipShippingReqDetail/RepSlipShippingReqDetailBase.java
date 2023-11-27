/*
 * Created on 2009/07/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingReqDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepSlipShippingReqDetailクラス.
 * @author kanri-user
 */
public class RepSlipShippingReqDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingReqDetailBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション shippingInstructDate */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション venderDivision */
	public static final String venderDivision_COLUMN = "VENDER_DIVISION";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション summery */
	public static final String summery_COLUMN = "SUMMERY";

	/** COLUMNアノテーション deliveryComp */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション shippingResultDate */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション shippingSlipNo */
	public static final String shippingSlipNo_COLUMN = "SHIPPING_SLIP_NO";

	/** COLUMNアノテーション shippingSlipRowNo */
	public static final String shippingSlipRowNo_COLUMN = "SHIPPING_SLIP_ROW_NO";

	/** COLUMNアノテーション slipPublishComp */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション slipShippingOrderComp */
	public static final String slipShippingOrderComp_COLUMN = "SLIP_SHIPPING_ORDER_COMP";

	/** COLUMNアノテーション slipShippingOrderDate */
	public static final String slipShippingOrderDate_COLUMN = "SLIP_SHIPPING_ORDER_DATE";

	/** COLUMNアノテーション slipShippingScheduleComp */
	public static final String slipShippingScheduleComp_COLUMN = "SLIP_SHIPPING_SCHEDULE_COMP";

	/** COLUMNアノテーション slipShippingScheduleDate */
	public static final String slipShippingScheduleDate_COLUMN = "SLIP_SHIPPING_SCHEDULE_DATE";

	/** COLUMNアノテーション slipShippingTagComp */
	public static final String slipShippingTagComp_COLUMN = "SLIP_SHIPPING_TAG_COMP";

	/** COLUMNアノテーション slipShippingTagDate */
	public static final String slipShippingTagDate_COLUMN = "SLIP_SHIPPING_TAG_DATE";

	/** COLUMNアノテーション slipShippingRequestComp */
	public static final String slipShippingRequestComp_COLUMN = "SLIP_SHIPPING_REQUEST_COMP";

	/** COLUMNアノテーション slipShippingRequestDate */
	public static final String slipShippingRequestDate_COLUMN = "SLIP_SHIPPING_REQUEST_DATE";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション suggestedDeliverlimit */
	public static final String suggestedDeliverlimit_COLUMN = "SUGGESTED_DELIVERLIMIT";

	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";

	/** COLUMNアノテーション sendingOffNumber */
	public static final String sendingOffNumber_COLUMN = "SENDING_OFF_NUMBER";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション otherCompanyCd1 */
	public static final String otherCompanyCd1_COLUMN = "OTHER_COMPANY_CD1";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション zipcodeNo */
	public static final String zipcodeNo_COLUMN = "ZIPCODE_NO";

	/** COLUMNアノテーション address */
	public static final String address_COLUMN = "ADDRESS";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション locationCd */
	public static final String locationCd_COLUMN = "LOCATION_CD";

	/** COLUMNアノテーション deliveryName2AndAddress */
	public static final String deliveryName2AndAddress_COLUMN = "DELIVERY_NAME2_AND_ADDRESS";

	//
	// インスタンスフィールド
	//

	private String key;

	private String shippingNo;

	private String shippingInstructDate;

	private String scheduledShippingDate;

	private String tantoCd;

	private String orderNo;

	private String orderRowNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private String shippingStatus;

	private String itemCd;

	private String summery;

	private String deliveryComp;

	private String shippingResultDate;

	private String shippingSlipNo;

	private String shippingSlipRowNo;

	private String slipPublishComp;

	private String slipPublishDate;

	private String slipShippingOrderComp;

	private String slipShippingOrderDate;

	private String slipShippingScheduleComp;

	private String slipShippingScheduleDate;

	private String slipShippingTagComp;

	private String slipShippingTagDate;

	private String slipShippingRequestComp;

	private String slipShippingRequestDate;

	private String carryCd;

	private String suggestedDeliverlimit;

	private String carryFare;

	private String sendingOffNumber;

	private String inputDate;

	private String inputorCd;

	private String updateDate;

	private String updatorCd;

	private String orderQty;

	private String itemName;

	private String styleOfPacking;

	private String otherCompanyCd1;

	private String deliveryName1;

	private String deliveryName2;

	private String telNo;

	private String zipcodeNo;

	private String address;

	private String deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String customerOrderNo;

	private String deliverySlipSummery;

	private String locationCd;

	private String deliveryName2AndAddress;

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
	public String getShippingInstructDate() {
		return this.shippingInstructDate;
	}

	/**
	 * shippingInstructDate設定.
	 * @param shippingInstructDate shippingInstructDate
	 */
	public void setShippingInstructDate(final String shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
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
	public String getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * orderRowNo設定.
	 * @param orderRowNo orderRowNo
	 */
	public void setOrderRowNo(final String orderRowNo) {
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
	public String getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * shippingStatus設定.
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(final String shippingStatus) {
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
	public String getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * deliveryComp設定.
	 * @param deliveryComp deliveryComp
	 */
	public void setDeliveryComp(final String deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * shippingResultDate取得.
	 * @return shippingResultDate
	 */
	public String getShippingResultDate() {
		return this.shippingResultDate;
	}

	/**
	 * shippingResultDate設定.
	 * @param shippingResultDate shippingResultDate
	 */
	public void setShippingResultDate(final String shippingResultDate) {
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
	public String getShippingSlipRowNo() {
		return this.shippingSlipRowNo;
	}

	/**
	 * shippingSlipRowNo設定.
	 * @param shippingSlipRowNo shippingSlipRowNo
	 */
	public void setShippingSlipRowNo(final String shippingSlipRowNo) {
		this.shippingSlipRowNo = shippingSlipRowNo;
	}

	/**
	 * slipPublishComp取得.
	 * @return slipPublishComp
	 */
	public String getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * slipPublishComp設定.
	 * @param slipPublishComp slipPublishComp
	 */
	public void setSlipPublishComp(final String slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * slipPublishDate取得.
	 * @return slipPublishDate
	 */
	public String getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * slipPublishDate設定.
	 * @param slipPublishDate slipPublishDate
	 */
	public void setSlipPublishDate(final String slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * slipShippingOrderComp取得.
	 * @return slipShippingOrderComp
	 */
	public String getSlipShippingOrderComp() {
		return this.slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderComp設定.
	 * @param slipShippingOrderComp slipShippingOrderComp
	 */
	public void setSlipShippingOrderComp(final String slipShippingOrderComp) {
		this.slipShippingOrderComp = slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderDate取得.
	 * @return slipShippingOrderDate
	 */
	public String getSlipShippingOrderDate() {
		return this.slipShippingOrderDate;
	}

	/**
	 * slipShippingOrderDate設定.
	 * @param slipShippingOrderDate slipShippingOrderDate
	 */
	public void setSlipShippingOrderDate(final String slipShippingOrderDate) {
		this.slipShippingOrderDate = slipShippingOrderDate;
	}

	/**
	 * slipShippingScheduleComp取得.
	 * @return slipShippingScheduleComp
	 */
	public String getSlipShippingScheduleComp() {
		return this.slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleComp設定.
	 * @param slipShippingScheduleComp slipShippingScheduleComp
	 */
	public void setSlipShippingScheduleComp(
			final String slipShippingScheduleComp) {
		this.slipShippingScheduleComp = slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleDate取得.
	 * @return slipShippingScheduleDate
	 */
	public String getSlipShippingScheduleDate() {
		return this.slipShippingScheduleDate;
	}

	/**
	 * slipShippingScheduleDate設定.
	 * @param slipShippingScheduleDate slipShippingScheduleDate
	 */
	public void setSlipShippingScheduleDate(
			final String slipShippingScheduleDate) {
		this.slipShippingScheduleDate = slipShippingScheduleDate;
	}

	/**
	 * slipShippingTagComp取得.
	 * @return slipShippingTagComp
	 */
	public String getSlipShippingTagComp() {
		return this.slipShippingTagComp;
	}

	/**
	 * slipShippingTagComp設定.
	 * @param slipShippingTagComp slipShippingTagComp
	 */
	public void setSlipShippingTagComp(final String slipShippingTagComp) {
		this.slipShippingTagComp = slipShippingTagComp;
	}

	/**
	 * slipShippingTagDate取得.
	 * @return slipShippingTagDate
	 */
	public String getSlipShippingTagDate() {
		return this.slipShippingTagDate;
	}

	/**
	 * slipShippingTagDate設定.
	 * @param slipShippingTagDate slipShippingTagDate
	 */
	public void setSlipShippingTagDate(final String slipShippingTagDate) {
		this.slipShippingTagDate = slipShippingTagDate;
	}

	/**
	 * slipShippingRequestComp取得.
	 * @return slipShippingRequestComp
	 */
	public String getSlipShippingRequestComp() {
		return this.slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestComp設定.
	 * @param slipShippingRequestComp slipShippingRequestComp
	 */
	public void setSlipShippingRequestComp(final String slipShippingRequestComp) {
		this.slipShippingRequestComp = slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestDate取得.
	 * @return slipShippingRequestDate
	 */
	public String getSlipShippingRequestDate() {
		return this.slipShippingRequestDate;
	}

	/**
	 * slipShippingRequestDate設定.
	 * @param slipShippingRequestDate slipShippingRequestDate
	 */
	public void setSlipShippingRequestDate(final String slipShippingRequestDate) {
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
	public String getSuggestedDeliverlimit() {
		return this.suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimit設定.
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(final String suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * carryFare取得.
	 * @return carryFare
	 */
	public String getCarryFare() {
		return this.carryFare;
	}

	/**
	 * carryFare設定.
	 * @param carryFare carryFare
	 */
	public void setCarryFare(final String carryFare) {
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
	 * inputDate取得.
	 * @return inputDate
	 */
	public String getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final String inputDate) {
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
	public String getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final String updateDate) {
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
	 * orderQty取得.
	 * @return orderQty
	 */
	public String getOrderQty() {
		return this.orderQty;
	}

	/**
	 * orderQty設定.
	 * @param orderQty orderQty
	 */
	public void setOrderQty(final String orderQty) {
		this.orderQty = orderQty;
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
	 * deliveryName1取得.
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * deliveryName1設定.
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2取得.
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return this.deliveryName2;
	}

	/**
	 * deliveryName2設定.
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(final String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * telNo取得.
	 * @return telNo
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * telNo設定.
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * zipcodeNo取得.
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return this.zipcodeNo;
	}

	/**
	 * zipcodeNo設定.
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * address取得.
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * address設定.
	 * @param address address
	 */
	public void setAddress(final String address) {
		this.address = address;
	}

	/**
	 * deliveryExpectedDate取得.
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return this.deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDate設定.
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(final String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTime取得.
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTime設定.
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(final String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * customerOrderNo取得.
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return this.customerOrderNo;
	}

	/**
	 * customerOrderNo設定.
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(final String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * deliverySlipSummery取得.
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return this.deliverySlipSummery;
	}

	/**
	 * deliverySlipSummery設定.
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(final String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
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
	 * {@inheritDoc}
	 */
	public String getDeliveryName2AndAddress() {
		return deliveryName2AndAddress;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDeliveryName2AndAddress(final String deliveryName2AndAddress) {
		this.deliveryName2AndAddress = deliveryName2AndAddress;
	}
}
