/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingDeliveryDetailクラス.
 * @author kanri-user
 */
public class RepSlipShippingDeliveryDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingDeliveryDetailBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	
	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション janCd */
	public static final String janCd_COLUMN = "JAN_CD";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション salesQuantity */
	public static final String salesQuantity_COLUMN = "SALES_QUANTITY";

	/** COLUMNアノテーション matssIns */
	public static final String matssIns_COLUMN = "MATSS_INS";

	/** COLUMNアノテーション qtyIns */
	public static final String qtyIns_COLUMN = "QTY_INS";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション allUpWeight */
	public static final String allUpWeight_COLUMN = "ALL_UP_WEIGHT";

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

	/** COLUMNアノテーション slipShippingFareComp */
	public static final String slipShippingFareComp_COLUMN = "SLIP_SHIPPING_FARE_COMP";

	/** COLUMNアノテーション slipShippingFareDate */
	public static final String slipShippingFareDate_COLUMN = "SLIP_SHIPPING_FARE_DATE";

	/** COLUMNアノテーション slipShippingDelivelyComp */
	public static final String slipShippingDelivelyComp_COLUMN = "SLIP_SHIPPING_DELIVELY_COMP";

	/** COLUMNアノテーション slipShippingDelivelyDate */
	public static final String slipShippingDelivelyDate_COLUMN = "SLIP_SHIPPING_DELIVELY_DATE";

	/** COLUMNアノテーション slipShippingNewTag_comp */
	public static final String slipShippingNewTagComp_COLUMN = "SLIP_SHIPPING_NEW_TAG_COMP";

	/** COLUMNアノテーション slipShippingNewTag_date */
	public static final String slipShippingNewTagDate_COLUMN = "SLIP_SHIPPING_NEW_TAG_DATE";

	/** COLUMNアノテーション slipShippingPostalComp */
	public static final String slipShippingPostalComp_COLUMN = "SLIP_SHIPPING_POSTAL_COMP";

	/** COLUMNアノテーション slipShippingPostalDate */
	public static final String slipShippingPostalDate_COLUMN = "SLIP_SHIPPING_POSTAL_DATE";

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

	/** COLUMNアノテーション itemDivision */
	public static final String itemDivision_COLUMN = "ITEM_DIVISION";


	
	//
	// インスタンスフィールド
	//
	private String key;
	
	private String shippingNo;

	private String itemCd;

	private String janCd;

	private String itemName;

	private String styleOfPacking;

	private String matss;

	private String salesQuantity;

	private String matssIns;

	private String qtyIns;

	private String remark;

	private String allUpWeight;

	private String shippingInstructDate;

	private String scheduledShippingDate;

	private String tantoCd;

	private String orderNo;

	private String orderRowNo;

	private String venderDivision;

	private String venderCd;

	private String deliveryCd;

	private String shippingStatus;

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

	private String slipShippingFareComp;

	private String slipShippingFareDate;

	private String slipShippingDelivelyComp;

	private String slipShippingDelivelyDate;

	private String slipShippingNewTagComp;

	private String slipShippingNewTagDate;

	private String slipShippingPostalComp;

	private String slipShippingPostalDate;

	private String carryCd;

	private String suggestedDeliverlimit;

	private String carryFare;

	private String sendingOffNumber;

	private String inputDate;

	private String inputorCd;

	private String updateDate;

	private String updatorCd;

	private String itemDivision;

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
	 * janCdを取得します。
	 * @return janCd
	 */
	public String getJanCd() {
		return janCd;
	}

	/**
	 * janCdを設定します。
	 * @param janCd janCd
	 */
	public void setJanCd(String janCd) {
		this.janCd = janCd;
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
	 * salesQuantityを取得します。
	 * @return salesQuantity
	 */
	public String getSalesQuantity() {
		return salesQuantity;
	}

	/**
	 * salesQuantityを設定します。
	 * @param salesQuantity salesQuantity
	 */
	public void setSalesQuantity(String salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * matssInsを取得します。
	 * @return matssIns
	 */
	public String getMatssIns() {
		return matssIns;
	}

	/**
	 * matssInsを設定します。
	 * @param matssIns matssIns
	 */
	public void setMatssIns(String matssIns) {
		this.matssIns = matssIns;
	}

	/**
	 * qtyInsを取得します。
	 * @return qtyIns
	 */
	public String getQtyIns() {
		return qtyIns;
	}

	/**
	 * qtyInsを設定します。
	 * @param qtyIns qtyIns
	 */
	public void setQtyIns(String qtyIns) {
		this.qtyIns = qtyIns;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(String venderDivision) {
		this.venderDivision = venderDivision;
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
	 * summeryを取得します。
	 * @return summery
	 */
	public String getSummery() {
		return summery;
	}

	/**
	 * summeryを設定します。
	 * @param summery summery
	 */
	public void setSummery(String summery) {
		this.summery = summery;
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
	 * allUpWeightを取得します。
	 * @return allUpWeight
	 */
	public String getAllUpWeight() {
		return allUpWeight;
	}

	/**
	 * allUpWeightを設定します。
	 * @param allUpWeight allUpWeight
	 */
	public void setAllUpWeight(String allUpWeight) {
		this.allUpWeight = allUpWeight;
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
	 * itemDivisionを取得します。
	 * @return itemDivision
	 */
	public String getItemDivision() {
		return itemDivision;
	}

	/**
	 * itemDivisionを設定します。
	 * @param itemDivision itemDivision
	 */
	public void setItemDivision(String itemDivision) {
		this.itemDivision = itemDivision;
	}



}

