/*
 * Created on 2020/12/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repdeliverydatecontactheader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportListクラス.
 * @author
 */
public class RepDeliveryDateContactHeaderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepDeliveryDateContactHeaderBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション key */
	public static final String key_COLUMN = "KEY";
	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "FRST_ORDER_NO";
	/** COLUMNアノテーション pkRow */
	public static final String pkRow_COLUMN = "FRST_ORDER_ROW_NO";
	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";
	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";
	/** COLUMNアノテーション venderFullAddress1 */
	public static final String venderFullAddress1_COLUMN = "VENDER_FULL_ADDRESS_1";
	/** COLUMNアノテーション venderFullAddress2 */
	public static final String venderFullAddress2_COLUMN = "VENDER_FULL_ADDRESS_2";
	/** COLUMNアノテーション venderTelNo */
	public static final String venderTelNo_COLUMN = "VENDER_TEL_NO";
	/** COLUMNアノテーション venderFaxNo */
	public static final String venderFaxNo_COLUMN = "VENDER_FAX_NO";
	/** COLUMNアノテーション ctmVenderCd */
	public static final String ctmVenderCd_COLUMN = "CTM_VENDER_CD";
	/** COLUMNアノテーション ctmVenderName */
	public static final String ctmVenderName_COLUMN = "CTM_VENDER_NAME";
	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";
	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	/** COLUMNアノテーション deliveryFullAddress1 */
	public static final String deliveryFullAddress1_COLUMN = "DELIVERY_FULL_ADDRESS_1";
	/** COLUMNアノテーション deliveryFullAddress2 */
	public static final String deliveryFullAddress2_COLUMN = "DELIVERY_FULL_ADDRESS_2";
	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";
	/** COLUMNアノテーション deliveryFaxNo */
	public static final String deliveryFaxNo_COLUMN = "DELIVERY_FAX_NO";
	/** COLUMNアノテーション ctmDeliveryCd */
	public static final String ctmDeliveryCd_COLUMN = "CTM_DELIVERY_CD";
	/** COLUMNアノテーション ctmDeliveryName */
	public static final String ctmDeliveryName_COLUMN = "CTM_DELIVERY_NAME";
	/** COLUMNアノテーション ctmDeliveryFullAddress */
	public static final String ctmDeliveryFullAddress_COLUMN = "CTM_DELIVERY_FULL_ADDRESS";
	/** COLUMNアノテーション ctmDeliveryTelNo */
	public static final String ctmDeliveryTelNo_COLUMN = "CTM_DELIVERY_TEL_NO";
	/** COLUMNアノテーション ctmDeliveryFaxNo */
	public static final String ctmDeliveryFaxNo_COLUMN = "CTM_DELIVERY_FAX_NO";
	/** COLUMNアノテーション ctmDeliveryZipCd */
	public static final String ctmDeliveryZipCd_COLUMN = "CTM_DELIVERY_ZIP_CD";
	/** COLUMNアノテーション ctmOrderNo */
	public static final String ctmOrderNo_COLUMN = "CTM_ORDER_NO";
	/** COLUMNアノテーション ctmOrderDate */
	public static final String ctmOrderDate_COLUMN = "CTM_ORDER_DATE";
	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	/** COLUMNアノテーション  salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";
	/** COLUMNアノテーション  salesTantoName */
	public static final String salesTantoName_COLUMN = "SALES_TANTO_NAME";
	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";
	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";
	/** COLUMNアノテーション carryName */
	public static final String carryName_COLUMN = "CARRY_NAME";
	/** COLUMNアノテーション carryFare */
	public static final String carryFare_COLUMN = "CARRY_FARE";
	/** COLUMNアノテーション printSummery */
	public static final String printSummery_COLUMN = "PRINT_SUMMERY";
	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション headerRemarkSummery */
	public static final String headerRemark_COLUMN = "HEADER_REMARK";

	/** COLUMNアノテーション orgZipCode */
	public static final String orgZipCode_COLUMN = "ORG_ZIPCODE";

	/** COLUMNアノテーション orgAddress */
	public static final String orgAddress_COLUMN = "ORG_ADDRESS";

	/** COLUMNアノテーション orgTelNo */
	public static final String orgTelNo_COLUMN = "ORG_TEL_NO";

	/** COLUMNアノテーション orgFaxNo */
	public static final String orgFaxNo_COLUMN = "ORG_FAX_NO";

	public static final String secVenderName_COLUMN = "SEC_VENDER_NAME";
	public static final String secVenderAddress_COLUMN = "SEC_VENDER_ADDRESS";
	public static final String secVenderTelNo_COLUMN = "SEC_VENDER_TEL_NO";
	public static final String secVenderFaxNo_COLUMN = "SEC_VENDER_FAX_NO";

	// 20210906 Asclab Saita 納期連絡表改修対応
	public static final String secVenderCd_COLUMN = "SEC_VENDER_CD";
	public static final String thiVenderCd_COLUMN = "THI_VENDER_CD";
	public static final String thiVenderName_COLUMN = "THI_VENDER_NAME";
	public static final String thiVenderAddress_COLUMN = "THI_VENDER_ADDRESS";
	public static final String thiVenderTelNo_COLUMN = "THI_VENDER_TEL_NO";
	public static final String thiVenderFaxNo_COLUMN = "THI_VENDER_FAX_NO";
	public static final String fouVenderCd_COLUMN = "FOU_VENDER_CD";
	public static final String fouVenderName_COLUMN = "FOU_VENDER_NAME";
	public static final String fouVenderAddress_COLUMN = "FOU_VENDER_ADDRESS";
	public static final String fouVenderTelNo_COLUMN = "FOU_VENDER_TEL_NO";
	public static final String fouVenderFaxNo_COLUMN = "FOU_VENDER_FAX_NO";
	public static final String fifVenderCd_COLUMN = "FIF_VENDER_CD";
	public static final String fifVenderName_COLUMN = "FIF_VENDER_NAME";
	public static final String fifVenderAddress_COLUMN = "FIF_VENDER_ADDRESS";
	public static final String fifVenderTelNo_COLUMN = "FIF_VENDER_TEL_NO";
	public static final String fifVenderFaxNo_COLUMN = "FIF_VENDER_FAX_NO";
	public static final String deliverydateContactSummery_COLUMN = "DELIVERYDATE_CONTACT_SUMMERY";

	// インスタンスフィールド
	//
	private String key;

	private String pkNo;

	private BigDecimal pkRow;

	private String venderCd;

	private String venderName;

	private String venderFullAddress1;

	private String venderFullAddress2;

	private String venderTelNo;

	private String venderFaxNo;

	private String ctmVenderCd;

	private String ctmVenderName;

	private String deliveryCd;

	private String deliveryName;

	private String deliveryFullAddress1;

	private String deliveryFullAddress2;

	private String deliveryAddress;

	private String deliveryTelNo;

	private String deliveryFaxNo;

	private String ctmDeliveryCd;

	private String ctmDeliveryName;

	private String ctmDeliveryFullAddress;

	private String ctmDeliveryTelNo;

	private String ctmDeliveryFaxNo;

	private String ctmDeliveryZipCd;

	private String ctmOrderNo;

	private Timestamp ctmOrderDate;

	private String deliveryExpectedDate;

	private String salesTantoCd;

	private String salesTantoName;

	private String orderNo;

	private String carryCd;

	private String carryName;

	private BigDecimal carryFare;

	private String printSummery;

	private String deliverySlipSummery;

	private String scheduledShippingDate;

	private String headerRemark;

	private String orgZipCode;
	private String orgAddress;
	private String orgTelNo;
	private String orgFaxNo;

	private String secVenderName;
	private String secVenderAddress;
	private String secVenderTelNo;
	private String secVenderFaxNo;

	// 20210906 Asclab Saita 納期連絡表改修対応
	private String secVenderCd;
	private String thiVenderCd;
	private String thiVenderName;
	private String thiVenderAddress;
	private String thiVenderTelNo;
	private String thiVenderFaxNo;
	private String fouVenderCd;
	private String fouVenderName;
	private String fouVenderAddress;
	private String fouVenderTelNo;
	private String fouVenderFaxNo;
	private String fifVenderCd;
	private String fifVenderName;
	private String fifVenderAddress;
	private String fifVenderTelNo;
	private String fifVenderFaxNo;
	private String deliverydateContactSummery;

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
	 * venderFullAddress1を取得します。
	 * @return venderFullAddress1
	 */
	public String getVenderFullAddress1() {
		return venderFullAddress1;
	}

	/**
	 * venderFullAddress1を設定します。
	 * @param venderFullAddress1 venderFullAddress1
	 */
	public void setVenderFullAddress1(String venderFullAddress1) {
		this.venderFullAddress1 = venderFullAddress1;
	}

	/**
	 * venderFullAddress2を取得します。
	 * @return venderFullAddress2
	 */
	public String getVenderFullAddress2() {
		return venderFullAddress2;
	}

	/**
	 * venderFullAddress2を設定します。
	 * @param venderFullAddress2 venderFullAddress2
	 */
	public void setVenderFullAddress2(String venderFullAddress2) {
		this.venderFullAddress2 = venderFullAddress2;
	}

	/**
	 * venderTelNoを取得します。
	 * @return venderTelNo
	 */
	public String getVenderTelNo() {
		return venderTelNo;
	}

	/**
	 * venderTelNoを設定します。
	 * @param venderTelNo venderTelNo
	 */
	public void setVenderTelNo(String venderTelNo) {
		this.venderTelNo = venderTelNo;
	}

	/**
	 * venderFaxNoを取得します。
	 * @return venderFaxNo
	 */
	public String getVenderFaxNo() {
		return venderFaxNo;
	}

	/**
	 * venderFaxNoを設定します。
	 * @param venderFaxNo venderFaxNo
	 */
	public void setVenderFaxNo(String venderFaxNo) {
		this.venderFaxNo = venderFaxNo;
	}

	/**
	 * ctmVenderCdを取得します。
	 * @return ctmVenderCd
	 */
	public String getCtmVenderCd() {
		return ctmVenderCd;
	}

	/**
	 * ctmVenderCdを設定します。
	 * @param ctmVenderCd ctmVenderCd
	 */
	public void setCtmVenderCd(String ctmVenderCd) {
		this.ctmVenderCd = ctmVenderCd;
	}

	/**
	 * ctmVenderNameを取得します。
	 * @return ctmVenderName
	 */
	public String getCtmVenderName() {
		return ctmVenderName;
	}

	/**
	 * ctmVenderNameを設定します。
	 * @param ctmVenderName ctmVenderName
	 */
	public void setCtmVenderName(String ctmVenderName) {
		this.ctmVenderName = ctmVenderName;
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
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * deliveryFullAddress1を取得します。
	 * @return deliveryFullAddress1
	 */
	public String getDeliveryFullAddress1() {
		return deliveryFullAddress1;
	}

	/**
	 * deliveryFullAddress1を設定します。
	 * @param deliveryFullAddress1 deliveryFullAddress1
	 */
	public void setDeliveryFullAddress1(String deliveryFullAddress1) {
		this.deliveryFullAddress1 = deliveryFullAddress1;
	}

	/**
	 * deliveryFullAddress2を取得します。
	 * @return deliveryFullAddress2
	 */
	public String getDeliveryFullAddress2() {
		return deliveryFullAddress2;
	}

	/**
	 * deliveryFullAddress2を設定します。
	 * @param deliveryFullAddress2 deliveryFullAddress2
	 */
	public void setDeliveryFullAddress2(String deliveryFullAddress2) {
		this.deliveryFullAddress2 = deliveryFullAddress2;
	}

	/**
	 * deliveryAddressを取得します。
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * deliveryAddressを設定します。
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
	 * deliveryFaxNoを取得します。
	 * @return deliveryFaxNo
	 */
	public String getDeliveryFaxNo() {
		return deliveryFaxNo;
	}

	/**
	 * deliveryFaxNoを設定します。
	 * @param deliveryFaxNo deliveryFaxNo
	 */
	public void setDeliveryFaxNo(String deliveryFaxNo) {
		this.deliveryFaxNo = deliveryFaxNo;
	}

	/**
	 * ctmDeliveryCdを取得します。
	 * @return ctmDeliveryCd
	 */
	public String getCtmDeliveryCd() {
		return ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryCdを設定します。
	 * @param ctmDeliveryCd ctmDeliveryCd
	 */
	public void setCtmDeliveryCd(String ctmDeliveryCd) {
		this.ctmDeliveryCd = ctmDeliveryCd;
	}

	/**
	 * ctmDeliveryNameを取得します。
	 * @return ctmDeliveryName
	 */
	public String getCtmDeliveryName() {
		return ctmDeliveryName;
	}

	/**
	 * ctmDeliveryNameを設定します。
	 * @param ctmDeliveryName ctmDeliveryName
	 */
	public void setCtmDeliveryName(String ctmDeliveryName) {
		this.ctmDeliveryName = ctmDeliveryName;
	}

	/**
	 * ctmDeliveryFullAddressを取得します。
	 * @return ctmDeliveryFullAddress
	 */
	public String getCtmDeliveryFullAddress() {
		return ctmDeliveryFullAddress;
	}

	/**
	 * ctmDeliveryFullAddressを設定します。
	 * @param ctmDeliveryFullAddress ctmDeliveryFullAddress
	 */
	public void setCtmDeliveryFullAddress(String ctmDeliveryFullAddress) {
		this.ctmDeliveryFullAddress = ctmDeliveryFullAddress;
	}

	/**
	 * ctmDeliveryTelNoを取得します。
	 * @return ctmDeliveryTelNo
	 */
	public String getCtmDeliveryTelNo() {
		return ctmDeliveryTelNo;
	}

	/**
	 * ctmDeliveryTelNoを設定します。
	 * @param ctmDeliveryTelNo ctmDeliveryTelNo
	 */
	public void setCtmDeliveryTelNo(String ctmDeliveryTelNo) {
		this.ctmDeliveryTelNo = ctmDeliveryTelNo;
	}

	/**
	 * ctmDeliveryFaxNoを取得します。
	 * @return ctmDeliveryFaxNo
	 */
	public String getCtmDeliveryFaxNo() {
		return ctmDeliveryFaxNo;
	}

	/**
	 * ctmDeliveryFaxNoを設定します。
	 * @param ctmDeliveryFaxNo ctmDeliveryFaxNo
	 */
	public void setCtmDeliveryFaxNo(String ctmDeliveryFaxNo) {
		this.ctmDeliveryFaxNo = ctmDeliveryFaxNo;
	}

	/**
	 * ctmDeliveryZipCdを取得します。
	 * @return ctmDeliveryZipCd
	 */
	public String getCtmDeliveryZipCd() {
		return ctmDeliveryZipCd;
	}

	/**
	 * ctmDeliveryZipCdを設定します。
	 * @param ctmDeliveryZipCd ctmDeliveryZipCd
	 */
	public void setCtmDeliveryZipCd(String ctmDeliveryZipCd) {
		this.ctmDeliveryZipCd = ctmDeliveryZipCd;
	}

	/**
	 * ctmOrderNoを取得します。
	 * @return ctmOrderNo
	 */
	public String getCtmOrderNo() {
		return ctmOrderNo;
	}

	/**
	 * ctmOrderNoを設定します。
	 * @param ctmOrderNo ctmOrderNo
	 */
	public void setCtmOrderNo(String ctmOrderNo) {
		this.ctmOrderNo = ctmOrderNo;
	}

	/**
	 * ctmOrderDateを取得します。
	 * @return ctmOrderDate
	 */
	public Timestamp getCtmOrderDate() {
		return ctmOrderDate;
	}

	/**
	 * ctmOrderDateを設定します。
	 * @param ctmOrderDate ctmOrderDate
	 */
	public void setCtmOrderDate(Timestamp ctmOrderDate) {
		this.ctmOrderDate = ctmOrderDate;
	}


	/**
	 * salesTantoCdを取得します。
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return salesTantoCd;
	}

	/**
	 * salesTantoCdを設定します。
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * salesTantoNameを取得します。
	 * @return salesTantoName
	 */
	public String getSalesTantoName() {
		return salesTantoName;
	}

	/**
	 * salesTantoNameを設定します。
	 * @param salesTantoName salesTantoName
	 */
	public void setSalesTantoName(String salesTantoName) {
		this.salesTantoName = salesTantoName;
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
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(String carryName) {
		this.carryName = carryName;
	}

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public BigDecimal getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(BigDecimal carryFare) {
		this.carryFare = carryFare;
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

	public String getHeaderRemark() {
		return headerRemark;
	}

	public void setHeaderRemark(String headerRemark) {
		this.headerRemark = headerRemark;
	}

	/**
	 * orgZipCodeを取得します。
	 * @return orgZipCode
	 */
	public String getOrgZipCode() {
		return orgZipCode;
	}

	/**
	 * orgZipCodeを設定します。
	 * @param orgZipCode orgZipCode
	 */
	public void setOrgZipCode(String orgZipCode) {
		this.orgZipCode = orgZipCode;
	}

	/**
	 * orgAddressを取得します。
	 * @return orgAddress
	 */
	public String getOrgAddress() {
		return orgAddress;
	}

	/**
	 * orgAddressを設定します。
	 * @param orgAddress orgAddress
	 */
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	/**
	 * orgTelNoを取得します。
	 * @return orgTelNo
	 */
	public String getOrgTelNo() {
		return orgTelNo;
	}

	/**
	 * orgTelNoを設定します。
	 * @param orgTelNo orgTelNo
	 */
	public void setOrgTelNo(String orgTelNo) {
		this.orgTelNo = orgTelNo;
	}

	/**
	 * orgFaxNoを取得します。
	 * @return orgFaxNo
	 */
	public String getOrgFaxNo() {
		return orgFaxNo;
	}

	/**
	 * orgFaxNoを設定します。
	 * @param orgFaxNo orgFaxNo
	 */
	public void setOrgFaxNo(String orgFaxNo) {
		this.orgFaxNo = orgFaxNo;
	}

	/**
	 * secVenderNameを取得します。
	 * @return secVenderName
	 */
	public String getSecVenderName() {
		return secVenderName;
	}

	/**
	 * secVenderNameを設定します。
	 * @param secVenderName secVenderName
	 */
	public void setSecVenderName(String secVenderName) {
		this.secVenderName = secVenderName;
	}

	/**
	 * secVenderAddressを取得します。
	 * @return secVenderAddress
	 */
	public String getSecVenderAddress() {
		return secVenderAddress;
	}

	/**
	 * secVenderAddressを設定します。
	 * @param secVenderAddress secVenderAddress
	 */
	public void setSecVenderAddress(String secVenderAddress) {
		this.secVenderAddress = secVenderAddress;
	}

	/**
	 * secVenderTelNoを取得します。
	 * @return secVenderTelNo
	 */
	public String getSecVenderTelNo() {
		return secVenderTelNo;
	}

	/**
	 * secVenderTelNoを設定します。
	 * @param secVenderTelNo secVenderTelNo
	 */
	public void setSecVenderTelNo(String secVenderTelNo) {
		this.secVenderTelNo = secVenderTelNo;
	}

	/**
	 * secVenderFaxNoを取得します。
	 * @return secVenderFaxNo
	 */
	public String getSecVenderFaxNo() {
		return secVenderFaxNo;
	}

	/**
	 * secVenderFaxNoを設定します。
	 * @param secVenderFaxNo secVenderFaxNo
	 */
	public void setSecVenderFaxNo(String secVenderFaxNo) {
		this.secVenderFaxNo = secVenderFaxNo;
	}

	// 20210906 Asclab Saita 納期連絡表改修対応
	/**
	 * secVenderCdを取得します。
	 * @return secVenderCd
	 */
	public String getSecVenderCd() {
		return secVenderCd;
	}

	/**
	 * secVenderCdを設定します。
	 * @param secVenderCd secVenderCd
	 */
	public void setSecVenderCd(String secVenderCd) {
		this.secVenderCd = secVenderCd;
	}

	/**
	 * thiVenderCdを取得します。
	 * @return thiVenderCd
	 */
	public String getThiVenderCd() {
		return thiVenderCd;
	}

	/**
	 * thiVenderCdを設定します。
	 * @param thiVenderCd thiVenderCd
	 */
	public void setThiVenderCd(String thiVenderCd) {
		this.thiVenderCd = thiVenderCd;
	}

	/**
	 * thiVenderNameを取得します。
	 * @return thiVenderName
	 */
	public String getThiVenderName() {
		return thiVenderName;
	}

	/**
	 * thiVenderNameを設定します。
	 * @param thiVenderName thiVenderName
	 */
	public void setThiVenderName(String thiVenderName) {
		this.thiVenderName = thiVenderName;
	}

	/**
	 * thiVenderAddressを取得します。
	 * @return thiVenderAddress
	 */
	public String getThiVenderAddress() {
		return thiVenderAddress;
	}

	/**
	 * thiVenderAddressを設定します。
	 * @param thiVenderAddress thiVenderAddress
	 */
	public void setThiVenderAddress(String thiVenderAddress) {
		this.thiVenderAddress = thiVenderAddress;
	}

	/**
	 * thiVenderTelNoを取得します。
	 * @return thiVenderTelNo
	 */
	public String getThiVenderTelNo() {
		return thiVenderTelNo;
	}

	/**
	 * thiVenderTelNoを設定します。
	 * @param thiVenderTelNo thiVenderTelNo
	 */
	public void setThiVenderTelNo(String thiVenderTelNo) {
		this.thiVenderTelNo = thiVenderTelNo;
	}

	/**
	 * thiVenderFaxNoを取得します。
	 * @return thiVenderFaxNo
	 */
	public String getThiVenderFaxNo() {
		return thiVenderFaxNo;
	}

	/**
	 * thiVenderFaxNoを設定します。
	 * @param thiVenderFaxNo thiVenderFaxNo
	 */
	public void setThiVenderFaxNo(String thiVenderFaxNo) {
		this.thiVenderFaxNo = thiVenderFaxNo;
	}

	/**
	 * fouVenderCdを取得します。
	 * @return fouVenderCd
	 */
	public String getFouVenderCd() {
		return fouVenderCd;
	}

	/**
	 * fouVenderCdを設定します。
	 * @param fouVenderCd fouVenderCd
	 */
	public void setFouVenderCd(String fouVenderCd) {
		this.fouVenderCd = fouVenderCd;
	}

	/**
	 * fouVenderNameを取得します。
	 * @return fouVenderName
	 */
	public String getFouVenderName() {
		return fouVenderName;
	}

	/**
	 * fouVenderNameを設定します。
	 * @param fouVenderName fouVenderName
	 */
	public void setFouVenderName(String fouVenderName) {
		this.fouVenderName = fouVenderName;
	}

	/**
	 * fouVenderAddressを取得します。
	 * @return fouVenderAddress
	 */
	public String getFouVenderAddress() {
		return fouVenderAddress;
	}

	/**
	 * fouVenderAddressを設定します。
	 * @param fouVenderAddress fouVenderAddress
	 */
	public void setFouVenderAddress(String fouVenderAddress) {
		this.fouVenderAddress = fouVenderAddress;
	}

	/**
	 * fouVenderTelNoを取得します。
	 * @return fouVenderTelNo
	 */
	public String getFouVenderTelNo() {
		return fouVenderTelNo;
	}

	/**
	 * fouVenderTelNoを設定します。
	 * @param fouVenderTelNo fouVenderTelNo
	 */
	public void setFouVenderTelNo(String fouVenderTelNo) {
		this.fouVenderTelNo = fouVenderTelNo;
	}

	/**
	 * fouVenderFaxNoを取得します。
	 * @return fouVenderFaxNo
	 */
	public String getFouVenderFaxNo() {
		return fouVenderFaxNo;
	}

	/**
	 * fouVenderFaxNoを設定します。
	 * @param fouVenderFaxNo fouVenderFaxNo
	 */
	public void setFouVenderFaxNo(String fouVenderFaxNo) {
		this.fouVenderFaxNo = fouVenderFaxNo;
	}

	/**
	 * fifVenderCdを取得します。
	 * @return fifVenderCd
	 */
	public String getFifVenderCd() {
		return fifVenderCd;
	}

	/**
	 * fifVenderCdを設定します。
	 * @param fifVenderCd fifVenderCd
	 */
	public void setFifVenderCd(String fifVenderCd) {
		this.fifVenderCd = fifVenderCd;
	}

	/**
	 * fifVenderNameを取得します。
	 * @return fifVenderName
	 */
	public String getFifVenderName() {
		return fifVenderName;
	}

	/**
	 * fifVenderNameを設定します。
	 * @param fifVenderName fifVenderName
	 */
	public void setFifVenderName(String fifVenderName) {
		this.fifVenderName = fifVenderName;
	}

	/**
	 * fifVenderAddressを取得します。
	 * @return fifVenderAddress
	 */
	public String getFifVenderAddress() {
		return fifVenderAddress;
	}

	/**
	 * fifVenderAddressを設定します。
	 * @param fifVenderAddress fifVenderAddress
	 */
	public void setFifVenderAddress(String fifVenderAddress) {
		this.fifVenderAddress = fifVenderAddress;
	}

	/**
	 * fifVenderTelNoを取得します。
	 * @return fifVenderTelNo
	 */
	public String getFifVenderTelNo() {
		return fifVenderTelNo;
	}

	/**
	 * fifVenderTelNoを設定します。
	 * @param fifVenderTelNo fifVenderTelNo
	 */
	public void setFifVenderTelNo(String fifVenderTelNo) {
		this.fifVenderTelNo = fifVenderTelNo;
	}

	/**
	 * fifVenderFaxNoを取得します。
	 * @return fifVenderFaxNo
	 */
	public String getFifVenderFaxNo() {
		return fifVenderFaxNo;
	}

	/**
	 * fifVenderFaxNoを設定します。
	 * @param fifVenderFaxNo fifVenderFaxNo
	 */
	public void setFifVenderFaxNo(String fifVenderFaxNo) {
		this.fifVenderFaxNo = fifVenderFaxNo;
	}

	/**
	 * deliverydateContactSummeryを取得します。
	 * @return deliverydateContactSummery
	 */
	public String getDeliverydateContactSummery() {
		return deliverydateContactSummery;
	}

	/**
	 * deliverydateContactSummeryを設定します。
	 * @param deliverydateContactSummery deliverydateContactSummery
	 */
	public void setDeliverydateContactSummery(String deliverydateContactSummery) {
		this.deliverydateContactSummery = deliverydateContactSummery;
	}
}

