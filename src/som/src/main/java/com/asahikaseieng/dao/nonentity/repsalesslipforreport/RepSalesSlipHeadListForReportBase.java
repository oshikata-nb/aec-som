/*
 * Created on 2009/09/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repsalesslipforreport;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * RepSalesSlipHeadListForReportクラス.
 * @author kanri-user
 */
public class RepSalesSlipHeadListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSalesSlipHeadListForReportBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション salesSlipNo */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション venderName1 */
	public static final String venderName1_COLUMN = "VENDER_NAME1";

	/** COLUMNアノテーション venderName2 */
	public static final String venderName2_COLUMN = "VENDER_NAME2";

	/** COLUMNアノテーション venderTantoCd */
	public static final String venderTantoCd_COLUMN = "VENDER_TANTO_CD";

	/** COLUMNアノテーション venderTantoName */
	public static final String venderTantoName_COLUMN = "VENDER_TANTO_NAME";

	/** COLUMNアノテーション organizationName */
	public static final String organizationName_COLUMN = "ORGANIZATION_NAME";

	/** COLUMNアノテーション organizationAddress1 */
	public static final String organizationAddress1_COLUMN = "ORGANIZATION_ADDRESS1";

	/** COLUMNアノテーション organizationAddress2 */
	public static final String organizationAddress2_COLUMN = "ORGANIZATION_ADDRESS2";

	/** COLUMNアノテーション organizationAddress3 */
	public static final String organizationAddress3_COLUMN = "ORGANIZATION_ADDRESS3";

	/** COLUMNアノテーション organizationAddressAll */
	public static final String organizationAddressAll_COLUMN = "ORGANIZATION_ADDRESS_ALL";

	/** COLUMNアノテーション organizationTelNo */
	public static final String organizationTelNo_COLUMN = "ORGANIZATION_TEL_NO";

	/** COLUMNアノテーション organizationZipcodeNo */
	public static final String organizationZipcodeNo_COLUMN = "ORGANIZATION_ZIPCODE_NO";

	/** COLUMNアノテーション organizationInvoiceNo */
	public static final String organizationinvoiceNo_COLUMN = "ORGANIZATION_INVOICE_NO";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション deliveryNameAll */
	public static final String deliveryNameAll_COLUMN = "DELIVERY_NAME_ALL";

	/** COLUMNアノテーション deliveryAddress1 */
	public static final String deliveryAddress1_COLUMN = "DELIVERY_ADDRESS1";

	/** COLUMNアノテーション deliveryAddress2 */
	public static final String deliveryAddress2_COLUMN = "DELIVERY_ADDRESS2";

	/** COLUMNアノテーション deliveryAddress3 */
	public static final String deliveryAddress3_COLUMN = "DELIVERY_ADDRESS3";

	/** COLUMNアノテーション deliveryAddressAll */
	public static final String deliveryAddressAll_COLUMN = "DELIVERY_ADDRESS_ALL";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	/** COLUMNアノテーション deliveryTantoCd */
	public static final String deliveryTantoCd_COLUMN = "DELIVERY_TANTO_CD";

	/** COLUMNアノテーション deliveryTantoName */
	public static final String deliveryTantoName_COLUMN = "DELIVERY_TANTO_NAME";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション customerOrderNo */
	public static final String customerOrderNo_COLUMN = "CUSTOMER_ORDER_NO";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション deliverySlipSummery */
	public static final String deliverySlipSummery_COLUMN = "DELIVERY_SLIP_SUMMERY";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

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

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション orderTantoName */
	public static final String orderTantoCd_COLUMN = "ORDER_TANTO_CD";

	/** COLUMNアノテーション orderTantoName */
	public static final String orderTantoName_COLUMN = "ORDER_TANTO_NAME";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション faxOutput */
	public static final String faxOutput_COLUMN = "FAX_OUTPUT";

	/** COLUMNアノテーション tempNewFlg */
	public static final String tempNewFlg_COLUMN = "TEMP_NEW_FLG";

	/** COLUMNアノテーション tsInvoiceFlg */
	public static final String tsInvoiceFlg_COLUMN = "TS_INVOICE_FLG";


	//
	// インスタンスフィールド
	//

	private String salesSlipNo;

	private String salesDate;

	private String venderCd;

	private String venderName1;

	private String venderName2;

	private String venderTantoCd;

	private String venderTantoName;

	private String organizationName;

	private String organizationAddress1;

	private String organizationAddress2;

	private String organizationAddress3;

	private String organizationAddressAll;

	private String organizationTelNo;

	private String organizationZipcodeNo;

	private String organizationInvoiceNo;

	private String deliveryCd;

	private String deliveryName1;

	private String deliveryName2;

	private String deliveryNameAll;

	private String deliveryAddress1;

	private String deliveryAddress2;

	private String deliveryAddress3;

	private String deliveryAddressAll;

	private String deliveryTelNo;

	private String deliveryTantoCd;

	private String deliveryTantoName;

	private String categoryDivision;

	private String orderNo;

	private String customerOrderNo;

	private String balanceCd;

	private String deliveryAddress;

	private String deliverySlipSummery;

	private String accountYears;

	private String upperLocationCd;

	private String locationName;

	private String carryCd;

	private String carryName1;

	private String carryName2;

	private String deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String orderTantoCd;

	private String orderTantoName;

	private String faxNo;

	private String faxOutput;

	private String tempNewFlg;

	private String tsInvoiceFlg;

	//
	// インスタンスメソッド
	//

	/**
	 * salesSlipNo取得.
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return this.salesSlipNo;
	}

	/**
	 * salesSlipNo設定.
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	/**
	 * salesDate取得.
	 * @return salesDate
	 */
	public String getSalesDate() {
		return this.salesDate;
	}

	/**
	 * salesDate設定.
	 * @param salesDate salesDate
	 */
	public void setSalesDate(final String salesDate) {
		this.salesDate = salesDate;
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
	 * venderName1取得.
	 * @return venderName1
	 */
	public String getVenderName1() {
		return this.venderName1;
	}

	/**
	 * venderName1設定.
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2取得.
	 * @return venderName2
	 */
	public String getVenderName2() {
		return this.venderName2;
	}

	/**
	 * venderName2設定.
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
	}

	/**
	 * venderTantoCd取得.
	 * @return venderTantoCd
	 */
	public String getVenderTantoCd() {
		return this.venderTantoCd;
	}

	/**
	 * venderTantoCd設定.
	 * @param venderTantoCd venderTantoCd
	 */
	public void setVenderTantoCd(final String venderTantoCd) {
		this.venderTantoCd = venderTantoCd;
	}

	/**
	 * venderTantoName取得.
	 * @return venderTantoName
	 */
	public String getVenderTantoName() {
		return this.venderTantoName;
	}

	/**
	 * venderTantoName設定.
	 * @param venderTantoName venderTantoName
	 */
	public void setVenderTantoName(final String venderTantoName) {
		this.venderTantoName = venderTantoName;
	}

	/**
	 * organizationName取得.
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * organizationName設定.
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * organizationAddress1取得.
	 * @return organizationAddress1
	 */
	public String getOrganizationAddress1() {
		return this.organizationAddress1;
	}

	/**
	 * organizationAddress1設定.
	 * @param organizationAddress1 organizationAddress1
	 */
	public void setOrganizationAddress1(final String organizationAddress1) {
		this.organizationAddress1 = organizationAddress1;
	}

	/**
	 * organizationAddress2取得.
	 * @return organizationAddress2
	 */
	public String getOrganizationAddress2() {
		return this.organizationAddress2;
	}

	/**
	 * organizationAddress2設定.
	 * @param organizationAddress2 organizationAddress2
	 */
	public void setOrganizationAddress2(final String organizationAddress2) {
		this.organizationAddress2 = organizationAddress2;
	}

	/**
	 * organizationAddress3取得.
	 * @return organizationAddress3
	 */
	public String getOrganizationAddress3() {
		return this.organizationAddress3;
	}

	/**
	 * organizationAddress3設定.
	 * @param organizationAddress3 organizationAddress3
	 */
	public void setOrganizationAddress3(final String organizationAddress3) {
		this.organizationAddress3 = organizationAddress3;
	}

	/**
	 * organizationAddressAll取得.
	 * @return organizationAddressAll
	 */
	public String getOrganizationAddressAll() {
		return this.organizationAddressAll;
	}

	/**
	 * organizationAddressAll設定.
	 * @param organizationAddressAll organizationAddressAll
	 */
	public void setOrganizationAddressAll(final String organizationAddressAll) {
		this.organizationAddressAll = organizationAddressAll;
	}

	/**
	 * organizationTelNo取得.
	 * @return organizationTelNo
	 */
	public String getOrganizationTelNo() {
		return this.organizationTelNo;
	}

	/**
	 * organizationTelNo設定.
	 * @param organizationTelNo organizationTelNo
	 */
	public void setOrganizationTelNo(final String organizationTelNo) {
		this.organizationTelNo = organizationTelNo;
	}

	/**
	 * organizationZipcodeNo取得.
	 * @return organizationZipcodeNo
	 */
	public String getOrganizationZipcodeNo() {
		return this.organizationZipcodeNo;
	}

	/**
	 * organizationZipcodeNo設定.
	 * @param organizationZipcodeNo organizationZipcodeNo
	 */
	public void setOrganizationZipcodeNo(final String organizationZipcodeNo) {
		this.organizationZipcodeNo = organizationZipcodeNo;
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
	 * deliveryNameAll取得.
	 * @return deliveryNameAll
	 */
	public String getDeliveryNameAll() {
		return this.deliveryNameAll;
	}

	/**
	 * deliveryNameAll設定.
	 * @param deliveryNameAll deliveryNameAll
	 */
	public void setDeliveryNameAll(final String deliveryNameAll) {
		this.deliveryNameAll = deliveryNameAll;
	}

	/**
	 * deliveryAddress1取得.
	 * @return deliveryAddress1
	 */
	public String getDeliveryAddress1() {
		return this.deliveryAddress1;
	}

	/**
	 * deliveryAddress1設定.
	 * @param deliveryAddress1 deliveryAddress1
	 */
	public void setDeliveryAddress1(final String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	/**
	 * deliveryAddress2取得.
	 * @return deliveryAddress2
	 */
	public String getDeliveryAddress2() {
		return this.deliveryAddress2;
	}

	/**
	 * deliveryAddress2設定.
	 * @param deliveryAddress2 deliveryAddress2
	 */
	public void setDeliveryAddress2(final String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	/**
	 * deliveryAddress3取得.
	 * @return deliveryAddress3
	 */
	public String getDeliveryAddress3() {
		return this.deliveryAddress3;
	}

	/**
	 * deliveryAddress3設定.
	 * @param deliveryAddress3 deliveryAddress3
	 */
	public void setDeliveryAddress3(final String deliveryAddress3) {
		this.deliveryAddress3 = deliveryAddress3;
	}

	/**
	 * deliveryAddressAll取得.
	 * @return deliveryAddressAll
	 */
	public String getDeliveryAddressAll() {
		return this.deliveryAddressAll;
	}

	/**
	 * deliveryAddressAll設定.
	 * @param deliveryAddressAll deliveryAddressAll
	 */
	public void setDeliveryAddressAll(final String deliveryAddressAll) {
		this.deliveryAddressAll = deliveryAddressAll;
	}

	/**
	 * deliveryTelNo取得.
	 * @return deliveryTelNo
	 */
	public String getDeliveryTelNo() {
		return this.deliveryTelNo;
	}

	/**
	 * deliveryTelNo設定.
	 * @param deliveryTelNo deliveryTelNo
	 */
	public void setDeliveryTelNo(final String deliveryTelNo) {
		this.deliveryTelNo = deliveryTelNo;
	}

	/**
	 * deliveryTantoCd取得.
	 * @return deliveryTantoCd
	 */
	public String getDeliveryTantoCd() {
		return this.deliveryTantoCd;
	}

	/**
	 * deliveryTantoCd設定.
	 * @param deliveryTantoCd deliveryTantoCd
	 */
	public void setDeliveryTantoCd(final String deliveryTantoCd) {
		this.deliveryTantoCd = deliveryTantoCd;
	}

	/**
	 * deliveryTantoName取得.
	 * @return deliveryTantoName
	 */
	public String getDeliveryTantoName() {
		return this.deliveryTantoName;
	}

	/**
	 * deliveryTantoName設定.
	 * @param deliveryTantoName deliveryTantoName
	 */
	public void setDeliveryTantoName(final String deliveryTantoName) {
		this.deliveryTantoName = deliveryTantoName;
	}

	/**
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * deliveryAddress取得.
	 * @return deliveryAddress
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * deliveryAddress設定.
	 * @param deliveryAddress deliveryAddress
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
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
	 * accountYears取得.
	 * @return accountYears
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * accountYears設定.
	 * @param accountYears accountYears
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

	/**
	 * upperLocationCd取得.
	 * @return upperLocationCd
	 */
	public String getUpperLocationCd() {
		return this.upperLocationCd;
	}

	/**
	 * upperLocationCd設定.
	 * @param upperLocationCd upperLocationCd
	 */
	public void setUpperLocationCd(final String upperLocationCd) {
		this.upperLocationCd = upperLocationCd;
	}

	/**
	 * locationName取得.
	 * @return locationName
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * locationName設定.
	 * @param locationName locationName
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
		return this.deliveryExpectedTime;
	}

	/**
	 * deliveryExpectedTime設定.
	 * @param deliveryExpectedTime deliveryExpectedTime
	 */
	public void setDeliveryExpectedTime(final String deliveryExpectedTime) {
		this.deliveryExpectedTime = deliveryExpectedTime;
	}

	/**
	 * orderTantoCd取得.
	 * @return orderTantoCd
	 */
	public String getOrderTantoCd() {
		return orderTantoCd;
	}

	/**
	 * orderTantoCd設定.
	 * @param orderTantoCd orderTantoCd
	 */
	public void setOrderTantoCd(final String orderTantoCd) {
		this.orderTantoCd = orderTantoCd;
	}

	/**
	 * orderTantoName取得.
	 * @return orderTantoName
	 */
	public String getOrderTantoName() {
		return orderTantoName;
	}

	/**
	 * orderTantoName設定.
	 * @param orderTantoName orderTantoName
	 */
	public void setOrderTantoName(final String orderTantoName) {
		this.orderTantoName = orderTantoName;
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
	 * faxNoを取得します。
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoを設定します。
	 * @param faxNo faxNo
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * faxOutputを取得します。
	 * @return faxOutput
	 */
	public String getFaxOutput() {
		return faxOutput;
	}

	/**
	 * faxOutputを設定します。
	 * @param faxOutput faxOutput
	 */
	public void setFaxOutput(String faxOutput) {
		this.faxOutput = faxOutput;
	}

	/**
	 * organizationInvoiceNo設定.
	 * @param organizationInvoiceNo organizationInvoiceNo
	 */
	public String getOrganizationInvoiceNo() {
		return organizationInvoiceNo;
	}

	/**
	 * organizationInvoiceNoを設定します。
	 * @param organizationInvoiceNo organizationInvoiceNo
	 */
	public void setOrganizationInvoiceNo(String organizationInvoiceNo) {
		this.organizationInvoiceNo = organizationInvoiceNo;
	}

	/**
	 * tempNewFlg設定.
	 * @param tempNewFlg tempNewFlg
	 */
	public String getTempNewFlg() {
		return tempNewFlg;
	}

	/**
	 * tempNewFlgを設定します。
	 * @param tempNewFlg tempNewFlg
	 */
	public void setTempNewFlg(String tempNewFlg) {
		this.tempNewFlg = tempNewFlg;
	}

	/**
	 * tsInvoiceFlg設定.
	 * @param tsInvoiceFlg tsInvoiceFlg
	 */
	public String getTsInvoiceFlg() {
		return tsInvoiceFlg;
	}

	/**
	 * tsInvoiceFlg設定.
	 * @param tsInvoiceFlg tsInvoiceFlg
	 */
	public void setTsInvoiceFlg(String tsInvoiceFlg) {
		this.tsInvoiceFlg = tsInvoiceFlg;
	}

}
