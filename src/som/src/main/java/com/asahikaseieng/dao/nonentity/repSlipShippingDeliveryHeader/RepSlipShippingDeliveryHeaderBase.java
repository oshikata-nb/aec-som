/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingDeliveryHeaderクラス.
 * @author kanri-user
 */
public class RepSlipShippingDeliveryHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingDeliveryHeaderBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	
	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";
	
	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";
	
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";
	
	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";
	
	/** COLUMNアノテーション deliveryNameAll */
	public static final String deliveryNameAll_COLUMN = "DELIVERY_NAME_ALL";
	
	/** COLUMNアノテーション deliveryAddressAll */
	public static final String deliveryAddressAll_COLUMN = "DELIVERY_ADDRESS_ALL";
	
	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";
	
	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	
	/** COLUMNアノテーション shippingInstructDate */
	public static final String shippingInstructDate_COLUMN = "SHIPPING_INSTRUCT_DATE";
	
	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";
	
	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";
	
	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	
	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";
	
	/** COLUMNアノテーション upperLocationCd */
	public static final String upperLocationCd_COLUMN = "UPPER_LOCATION_CD";
	
	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";
	
	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";
			
	/** COLUMNアノテーション carryName1 */
	public static final String carryName1_COLUMN = "CARRY_NAME1";
	
	/** COLUMNアノテーション carryName2 */
	public static final String carryName2_COLUMN = "CARRY_NAME2";
	
	/** COLUMNアノテーション reference */
	public static final String reference_COLUMN = "REFERENCE";
	
	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";
	
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	
	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";
	
	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";
	
	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";
	
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
	
	/** COLUMNアノテーション slipShippingFareComp */
	public static final String slipShippingFareComp_COLUMN = "SLIP_SHIPPING_FARE_COMP";
	
	/** COLUMNアノテーション slipShippingFareDate */
	public static final String slipShippingFareDate_COLUMN = "SLIP_SHIPPING_FARE_DATE";
	
	/** COLUMNアノテーション slipShippingDelivelyComp */
	public static final String slipShippingDelivelyComp_COLUMN = "SLIP_SHIPPING_DELIVELY_COMP";
	
	/** COLUMNアノテーション slipShippingDelivelyDate */
	public static final String slipShippingDelivelyDate_COLUMN = "SLIP_SHIPPING_DELIVELY_DATE";
	
	/** COLUMNアノテーション slipShippingNewTagComp */
	public static final String slipShippingNewTagComp_COLUMN = "SLIP_SHIPPING_NEW_TAG_COMP";
	
	/** COLUMNアノテーション slipShippingNewTagDate */
	public static final String slipShippingNewTagDate_COLUMN = "SLIP_SHIPPING_NEW_TAG_DATE";
	
	/** COLUMNアノテーション slipShippingPostalComp */
	public static final String slipShippingPostalComp_COLUMN = "SLIP_SHIPPING_POSTAL_COMP";
	
	/** COLUMNアノテーション slipShippingPostalDate */
	public static final String slipShippingPostalDate_COLUMN = "SLIP_SHIPPING_POSTAL_DATE";
	
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
	
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";
	
	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";
	
	/** COLUMNアノテーション orderSummery */
	public static final String orderSummery_COLUMN = "ORDER_SUMMERY";

	/** COLUMNアノテーション carryBarcode */
	public static final String carryBarcode_COLUMN = "CARRY_BARCODE";

	/** COLUMNアノテーション bcPublishDivision */
	public static final String bcPublishDivision_COLUMN = "BC_PUBLISH_DIVISION";

	/** COLUMNアノテーション bcHeader */
	public static final String bcHeader_COLUMN = "BC_HEADER";
	
	/** COLUMNアノテーション bcFooter */
	public static final String bcFooter_COLUMN = "BC_FOOTER";
	
	/** COLUMNアノテーション bcNumberOfDigit */
	public static final String bcNumberOfDigit_COLUMN = "BC_NUMBER_OF_DIGIT";

	/** COLUMNアノテーション bcNumCheckDigitStart */
	public static final String bcNumCheckDigitStart_COLUMN = "BC_CHECKDIGIT_START";

	/** COLUMNアノテーション bcNumCheckDigitEnd */
	public static final String bcNumCheckDigitEnd_COLUMN = "BC_CHECKDIGIT_END";

	/** COLUMNアノテーション bcNumCheckDigitEnd */
	public static final String cstOrderNo_COLUMN = "CST_ORDER_NO";

	//
	// インスタンスフィールド
	//
	private String key;
	
	private String shippingNo;

	private String categoryDivision;

	private String venderCd;

	private String venderName;

	private String deliveryNameAll;

	private String deliveryAddressAll;

	private String deliveryTelNo;

	private String deliveryCd;

	private String shippingInstructDate;

	private String scheduledShippingDate;

	private String tantoCd;

	private String deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String upperLocationCd;
	
	private String locationName;

	private String carryCd;

	private String carryName1;

	private String carryName2;
	
	private String reference;

	private String customerOrderNo;

	private String orderNo;

	private String orderRowNo;

	private String shippingStatus;

	private String itemCd;

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

	private String slipShippingFareComp;

	private String slipShippingFareDate;

	private String slipShippingDelivelyComp;

	private String slipShippingDelivelyDate;

	private String slipShippingNewTagComp;

	private String slipShippingNewTagDate;

	private String slipShippingPostalComp;

	private String slipShippingPostalDate;

	private String suggestedDeliverlimit;

	private String carryFare;

	private String sendingOffNumber;

	private String inputDate;

	private String inputorCd;

	private String updateDate;

	private String updatorCd;

	private String printSummery;

	private String deliverySlipSummery;

	private String orderSummery;

	private String carryBarcode;

	private BigDecimal bcPublishDivision;

	private String bcHeader;

	private String bcFooter;

	private BigDecimal bcNumberOfDigit;

	private BigDecimal bcNumCheckDigitStart;

	private BigDecimal bcNumCheckDigitEnd;
	
	private String cstOrderNo;

	
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
	 * carryName1を取得します。
	 * @return carryName1
	 */
	public String getCarryName1() {
		return carryName1;
	}

	/**
	 * carryName1を設定します。
	 * @param carryName1 carryName1
	 */
	public void setCarryName1(String carryName1) {
		this.carryName1 = carryName1;
	}

	/**
	 * carryName2を取得します。
	 * @return carryName2
	 */
	public String getCarryName2() {
		return carryName2;
	}

	/**
	 * carryName2を設定します。
	 * @param carryName2 carryName2
	 */
	public void setCarryName2(String carryName2) {
		this.carryName2 = carryName2;
	}

	/**
	 * categoryDivisionを取得します。
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return categoryDivision;
	}

	/**
	 * categoryDivisionを設定します。
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * customerOrderNoを取得します。
	 * @return customerOrderNo
	 */
	public String getCustomerOrderNo() {
		return customerOrderNo;
	}

	/**
	 * customerOrderNoを設定します。
	 * @param customerOrderNo customerOrderNo
	 */
	public void setCustomerOrderNo(String customerOrderNo) {
		this.customerOrderNo = customerOrderNo;
	}

	/**
	 * deliveryAddressAllを取得します。
	 * @return deliveryAddressAll
	 */
	public String getDeliveryAddressAll() {
		return deliveryAddressAll;
	}

	/**
	 * deliveryAddressAllを設定します。
	 * @param deliveryAddressAll deliveryAddressAll
	 */
	public void setDeliveryAddressAll(String deliveryAddressAll) {
		this.deliveryAddressAll = deliveryAddressAll;
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
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedTimeを取得します。
	 * @return deliveryExpectedTime
	 */
	public String getDeliveryExpectedTime() {
		return deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTimeを設定します。
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * deliveryNameAllを取得します。
	 * @return deliveryNameAll
	 */
	public String getDeliveryNameAll() {
		return deliveryNameAll;
	}

	/**
	 * deliveryNameAllを設定します。
	 * @param deliveryNameAll deliveryNameAll
	 */
	public void setDeliveryNameAll(String deliveryNameAll) {
		this.deliveryNameAll = deliveryNameAll;
	}

	/**
	 * deliveryTelNoを取得します。
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return deliveryTelNo;
	}

	/**
	 * deliveryTelNoを設定します。
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
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
	 * shippingInstructDateを取得します。
	 * @return shippingInstructDate
	 */
	public String getShippingInstructDate() {
		return shippingInstructDate;
	}

	/**
	 * shippingInstructDateを設定します。
	 * @param shippingInstructDate shippingInstructDate
	 */
	public void setShippingInstructDate(String shippingInstructDate) {
		this.shippingInstructDate = shippingInstructDate;
	}

	/**
	 * shippingNoを取得します。
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return shippingNo;
	}

	/**
	 * shippingNoを設定します。
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * shippingStatusを取得します。
	 * @return shippingStatus
	 */
	public String getShippingStatus() {
		return shippingStatus;
	}

	/**
	 * shippingStatusを設定します。
	 * @param shippingStatus shippingStatus
	 */
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(String tantoCd) {
		this.tantoCd = tantoCd;
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
	public void setUpperLocationCd(String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
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
	 * shippingResultDateを取得します。
	 * @return shippingResultDate
	 */
	public String getShippingResultDate() {
		return shippingResultDate;
	}

	/**
	 * shippingResultDateを設定します。
	 * @param shippingResultDate shippingResultDate
	 */
	public void setShippingResultDate(String shippingResultDate) {
		this.shippingResultDate = shippingResultDate;
	}

	/**
	 * shippingSlipNoを取得します。
	 * @return shippingSlipNo
	 */
	public String getShippingSlipNo() {
		return shippingSlipNo;
	}

	/**
	 * shippingSlipNoを設定します。
	 * @param shippingSlipNo shippingSlipNo
	 */
	public void setShippingSlipNo(String shippingSlipNo) {
		this.shippingSlipNo = shippingSlipNo;
	}

	/**
	 * shippingSlipRowNoを取得します。
	 * @return shippingSlipRowNo
	 */
	public String getShippingSlipRowNo() {
		return shippingSlipRowNo;
	}

	/**
	 * shippingSlipRowNoを設定します。
	 * @param shippingSlipRowNo shippingSlipRowNo
	 */
	public void setShippingSlipRowNo(String shippingSlipRowNo) {
		this.shippingSlipRowNo = shippingSlipRowNo;
	}

	/**
	 * slipPublishCompを取得します。
	 * @return slipPublishComp
	 */
	public String getSlipPublishComp() {
		return slipPublishComp;
	}

	/**
	 * slipPublishCompを設定します。
	 * @param slipPublishComp slipPublishComp
	 */
	public void setSlipPublishComp(String slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * slipPublishDateを取得します。
	 * @return slipPublishDate
	 */
	public String getSlipPublishDate() {
		return slipPublishDate;
	}

	/**
	 * slipPublishDateを設定します。
	 * @param slipPublishDate slipPublishDate
	 */
	public void setSlipPublishDate(String slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * slipShippingOrderCompを取得します。
	 * @return slipShippingOrderComp
	 */
	public String getSlipShippingOrderComp() {
		return slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderCompを設定します。
	 * @param slipShippingOrderComp slipShippingOrderComp
	 */
	public void setSlipShippingOrderComp(String slipShippingOrderComp) {
		this.slipShippingOrderComp = slipShippingOrderComp;
	}

	/**
	 * slipShippingOrderDateを取得します。
	 * @return slipShippingOrderDate
	 */
	public String getSlipShippingOrderDate() {
		return slipShippingOrderDate;
	}

	/**
	 * slipShippingOrderDateを設定します。
	 * @param slipShippingOrderDate slipShippingOrderDate
	 */
	public void setSlipShippingOrderDate(String slipShippingOrderDate) {
		this.slipShippingOrderDate = slipShippingOrderDate;
	}

	/**
	 * slipShippingScheduleCompを取得します。
	 * @return slipShippingScheduleComp
	 */
	public String getSlipShippingScheduleComp() {
		return slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleCompを設定します。
	 * @param slipShippingScheduleComp slipShippingScheduleComp
	 */
	public void setSlipShippingScheduleComp(String slipShippingScheduleComp) {
		this.slipShippingScheduleComp = slipShippingScheduleComp;
	}

	/**
	 * slipShippingScheduleDateを取得します。
	 * @return slipShippingScheduleDate
	 */
	public String getSlipShippingScheduleDate() {
		return slipShippingScheduleDate;
	}

	/**
	 * slipShippingScheduleDateを設定します。
	 * @param slipShippingScheduleDate slipShippingScheduleDate
	 */
	public void setSlipShippingScheduleDate(String slipShippingScheduleDate) {
		this.slipShippingScheduleDate = slipShippingScheduleDate;
	}

	/**
	 * slipShippingTagCompを取得します。
	 * @return slipShippingTagComp
	 */
	public String getSlipShippingTagComp() {
		return slipShippingTagComp;
	}

	/**
	 * slipShippingTagCompを設定します。
	 * @param slipShippingTagComp slipShippingTagComp
	 */
	public void setSlipShippingTagComp(String slipShippingTagComp) {
		this.slipShippingTagComp = slipShippingTagComp;
	}

	/**
	 * slipShippingTagDateを取得します。
	 * @return slipShippingTagDate
	 */
	public String getSlipShippingTagDate() {
		return slipShippingTagDate;
	}

	/**
	 * slipShippingTagDateを設定します。
	 * @param slipShippingTagDate slipShippingTagDate
	 */
	public void setSlipShippingTagDate(String slipShippingTagDate) {
		this.slipShippingTagDate = slipShippingTagDate;
	}

	/**
	 * slipShippingRequestCompを取得します。
	 * @return slipShippingRequestComp
	 */
	public String getSlipShippingRequestComp() {
		return slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestCompを設定します。
	 * @param slipShippingRequestComp slipShippingRequestComp
	 */
	public void setSlipShippingRequestComp(String slipShippingRequestComp) {
		this.slipShippingRequestComp = slipShippingRequestComp;
	}

	/**
	 * slipShippingRequestDateを取得します。
	 * @return slipShippingRequestDate
	 */
	public String getSlipShippingRequestDate() {
		return slipShippingRequestDate;
	}

	/**
	 * slipShippingRequestDateを設定します。
	 * @param slipShippingRequestDate slipShippingRequestDate
	 */
	public void setSlipShippingRequestDate(String slipShippingRequestDate) {
		this.slipShippingRequestDate = slipShippingRequestDate;
	}

	/**
	 * slipShippingFareCompを取得します。
	 * @return slipShippingFareComp
	 */
	public String getSlipShippingFareComp() {
		return slipShippingFareComp;
	}

	/**
	 * slipShippingFareCompを設定します。
	 * @param slipShippingFareComp slipShippingFareComp
	 */
	public void setSlipShippingFareComp(String slipShippingFareComp) {
		this.slipShippingFareComp = slipShippingFareComp;
	}

	/**
	 * slipShippingFareDateを取得します。
	 * @return slipShippingFareDate
	 */
	public String getSlipShippingFareDate() {
		return slipShippingFareDate;
	}

	/**
	 * slipShippingFareDateを設定します。
	 * @param slipShippingFareDate slipShippingFareDate
	 */
	public void setSlipShippingFareDate(String slipShippingFareDate) {
		this.slipShippingFareDate = slipShippingFareDate;
	}

	/**
	 * slipShippingDelivelyCompを取得します。
	 * @return slipShippingDelivelyComp
	 */
	public String getSlipShippingDelivelyComp() {
		return slipShippingDelivelyComp;
	}

	/**
	 * slipShippingDelivelyCompを設定します。
	 * @param slipShippingDelivelyComp slipShippingDelivelyComp
	 */
	public void setSlipShippingDelivelyComp(String slipShippingDelivelyComp) {
		this.slipShippingDelivelyComp = slipShippingDelivelyComp;
	}

	/**
	 * slipShippingDelivelyDateを取得します。
	 * @return slipShippingDelivelyDate
	 */
	public String getSlipShippingDelivelyDate() {
		return slipShippingDelivelyDate;
	}

	/**
	 * slipShippingDelivelyDateを設定します。
	 * @param slipShippingDelivelyDate slipShippingDelivelyDate
	 */
	public void setSlipShippingDelivelyDate(String slipShippingDelivelyDate) {
		this.slipShippingDelivelyDate = slipShippingDelivelyDate;
	}

	/**
	 * slipShippingNewTagCompを取得します。
	 * @return slipShippingNewTagComp
	 */
	public String getSlipShippingNewTagComp() {
		return slipShippingNewTagComp;
	}

	/**
	 * slipShippingNewTagCompを設定します。
	 * @param slipShippingNewTagComp slipShippingNewTagComp
	 */
	public void setSlipShippingNewTagComp(String slipShippingNewTagComp) {
		this.slipShippingNewTagComp = slipShippingNewTagComp;
	}

	/**
	 * slipShippingNewTagDateを取得します。
	 * @return slipShippingNewTagDate
	 */
	public String getSlipShippingNewTagDate() {
		return slipShippingNewTagDate;
	}

	/**
	 * slipShippingNewTagDateを設定します。
	 * @param slipShippingNewTagDate slipShippingNewTagDate
	 */
	public void setSlipShippingNewTagDate(String slipShippingNewTagDate) {
		this.slipShippingNewTagDate = slipShippingNewTagDate;
	}

	/**
	 * slipShippingPostalCompを取得します。
	 * @return slipShippingPostalComp
	 */
	public String getSlipShippingPostalComp() {
		return slipShippingPostalComp;
	}

	/**
	 * slipShippingPostalCompを設定します。
	 * @param slipShippingPostalComp slipShippingPostalComp
	 */
	public void setSlipShippingPostalComp(String slipShippingPostalComp) {
		this.slipShippingPostalComp = slipShippingPostalComp;
	}

	/**
	 * slipShippingPostalDateを取得します。
	 * @return slipShippingPostalDate
	 */
	public String getSlipShippingPostalDate() {
		return slipShippingPostalDate;
	}

	/**
	 * slipShippingPostalDateを設定します。
	 * @param slipShippingPostalDate slipShippingPostalDate
	 */
	public void setSlipShippingPostalDate(String slipShippingPostalDate) {
		this.slipShippingPostalDate = slipShippingPostalDate;
	}

	/**
	 * suggestedDeliverlimitを取得します。
	 * @return suggestedDeliverlimit
	 */
	public String getSuggestedDeliverlimit() {
		return suggestedDeliverlimit;
	}

	/**
	 * suggestedDeliverlimitを設定します。
	 * @param suggestedDeliverlimit suggestedDeliverlimit
	 */
	public void setSuggestedDeliverlimit(String suggestedDeliverlimit) {
		this.suggestedDeliverlimit = suggestedDeliverlimit;
	}

	/**
	 * sendingOffNumberを取得します。
	 * @return sendingOffNumber
	 */
	public String getSendingOffNumber() {
		return sendingOffNumber;
	}

	/**
	 * sendingOffNumberを設定します。
	 * @param sendingOffNumber sendingOffNumber
	 */
	public void setSendingOffNumber(String sendingOffNumber) {
		this.sendingOffNumber = sendingOffNumber;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * deliveryCompを取得します。
	 * @return deliveryComp
	 */
	public String getDeliveryComp() {
		return deliveryComp;
	}

	/**
	 * deliveryCompを設定します。
	 * @param deliveryComp deliveryComp
	 */
	public void setDeliveryComp(String deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public String getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(String carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * locationNameを取得します。
	 * @return locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * locationNameを設定します。s
	 * @param locationName locationName
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * referenceを取得します。
	 * @return reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * referenceを設定します。
	 * @param reference reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * printSummeryを取得します。
	 * @return printSummery
	 */
	public String getPrintSummery() {
		return printSummery;
	}

	/**
	 * printSummeryを設定します。
	 * @param printSummery printSummery
	 */
	public void setPrintSummery(String printSummery) {
		this.printSummery = printSummery;
	}

	/**
	 * deliverySlipSummeryを取得します。
	 * @return deliverySlipSummery
	 */
	public String getDeliverySlipSummery() {
		return deliverySlipSummery;
	}

	/**
	 * deliverySlipSummeryを設定します。
	 * @param deliverySlipSummery deliverySlipSummery
	 */
	public void setDeliverySlipSummery(String deliverySlipSummery) {
		this.deliverySlipSummery = deliverySlipSummery;
	}

	/**
	 * orderSummeryを取得します。
	 * @return orderSummery
	 */
	public String getOrderSummery() {
		return orderSummery;
	}

	/**
	 * orderSummeryを設定します。
	 * @param orderSummery orderSummery
	 */
	public void setOrderSummery(String orderSummery) {
		this.orderSummery = orderSummery;
	}

	/**
	 * carryBarcodeを取得します。
	 * @return carryBarcode
	 */
	public String getCarryBarcode() {
		return carryBarcode;
	}

	/**
	 * carryBarcodeを設定します。
	 * @param carryBarcode carryBarcode
	 */
	public void setCarryBarcode(String carryBarcode) {
		this.carryBarcode = carryBarcode;
	}

	/**
	 * bcPublishDivisionを取得します。
	 * @return bcPublishDivision
	 */
	public BigDecimal getBcPublishDivision() {
		return this.bcPublishDivision;
	}

	/**
	 * bcPublishDivisionを設定します。
	 * @param bcPublishDivision bcPublishDivision
	 */
	public void setBcPublishDivision(BigDecimal bcPublishDivision) {
		this.bcPublishDivision = bcPublishDivision;
	}

	/**
	 * bcHeaderを取得します。
	 * @return carryBarcode
	 */
	public String getBcHeader() {
		return bcHeader;
	}

	/**
	 * bcHeaderを設定します。
	 * @param bcHeader bcHeader
	 */
	public void setBcHeader(String bcHeader) {
		this.bcHeader = bcHeader;
	}

	/**
	 * bcFooterを取得します。
	 * @return bcFooter
	 */
	public String getBcFooter() {
		return bcFooter;
	}

	/**
	 * bcFooterを設定します。
	 * @param bcFooter bcFooter
	 */
	public void setBcFooter(String bcFooter) {
		this.bcFooter = bcFooter;
	}

	/**
	 * bcNumberOfDigitを取得します。
	 * @return bcNumberOfDigit
	 */
	public BigDecimal getBcNumberOfDigit() {
		return bcNumberOfDigit;
	}

	/**
	 * bcNumberOfDigitを設定します。
	 * @param bcNumberOfDigit bcNumberOfDigit
	 */
	public void setBcNumberOfDigit(BigDecimal bcNumberOfDigit) {
		this.bcNumberOfDigit = bcNumberOfDigit;
	}

	/**
	 * bcNumCheckDigitStartを取得します。
	 * @return bcNumCheckDigitStart
	 */
	public BigDecimal getBcNumCheckDigitStart() {
		return bcNumCheckDigitStart;
	}

	/**
	 * bcNumCheckDigitStartを設定します。
	 * @param bcNumCheckDigitStart bcNumCheckDigitStart
	 */
	public void setBcNumCheckDigitStart(BigDecimal bcNumCheckDigitStart) {
		this.bcNumCheckDigitStart = bcNumCheckDigitStart;
	}

	/**
	 * bcNumCheckDigitEndを取得します。
	 * @return bcNumCheckDigitEnd
	 */
	public BigDecimal getBcNumCheckDigitEnd() {
		return bcNumCheckDigitEnd;
	}

	/**
	 * bcNumCheckDigitEndを設定します。
	 * @param bcNumCheckDigitEnd bcNumCheckDigitEnd
	 */
	public void setBcNumCheckDigitEnd(BigDecimal bcNumCheckDigitEnd) {
		this.bcNumCheckDigitEnd = bcNumCheckDigitEnd;
	}

	/**
	 * cstOrderNoを取得します。
	 * @return cstOrderNo
	 */
	public String getCstOrderNo() {
		return cstOrderNo;
	}

	/**
	 * cstOrderNoを設定します。
	 * @param cstOrderNo cstOrderNo
	 */
	public void setCstOrderNo(String cstOrderNo) {
		this.cstOrderNo = cstOrderNo;
	}
	
	
}

