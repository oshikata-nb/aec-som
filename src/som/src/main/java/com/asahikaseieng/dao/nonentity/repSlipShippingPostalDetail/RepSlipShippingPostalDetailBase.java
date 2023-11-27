/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingPostalDetail;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * RepSlipShippingPostalDetailクラス.
 * @author kanri-user
 */
public class RepSlipShippingPostalDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RepSlipShippingPostalDetailBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション itemCategory */
	public static final String itemCategory_COLUMN = "ITEM_CATEGORY";

	/** COLUMNアノテーション paymentMethod */
	public static final String paymentMethod_COLUMN = "PAYMENT_METHOD";

	/** COLUMNアノテーション golfSkyAirport */
	public static final String golfSkyAirport_COLUMN = "GOLF_SKY_AIRPORT";

	/** COLUMNアノテーション roundTrip */
	public static final String roundTrip_COLUMN = "ROUND_TRIP";

	/** COLUMNアノテーション shippingOption */
	public static final String shippingOption_COLUMN = "SHIPPING_OPTION";

	/** COLUMNアノテーション deliveryMethod */
	public static final String deliveryMethod_COLUMN = "DELIVERY_METHOD";

	/** COLUMNアノテーション shippingNum */
	public static final String shippingNum_COLUMN = "SHIPPING_NUM";

	/** COLUMNアノテーション deliveryName */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";

	/** COLUMNアノテーション title */
	public static final String title_COLUMN = "TITLE";

	/** COLUMNアノテーション deliveryNameKana */
	public static final String deliveryNameKana_COLUMN = "DELIVERY_NAME_KANA";

	/** COLUMNアノテーション delZipcodeNo */
	public static final String delZipcodeNo_COLUMN = "DEL_ZIPCODE_NO";

	/** COLUMNアノテーション prefectures */
	public static final String prefectures_COLUMN = "PREFECTURES";

	/** COLUMNアノテーション deliveryAddress1 */
	public static final String deliveryAddress1_COLUMN = "DELIVERY_ADDRESS1";

	/** COLUMNアノテーション deliveryAddress2 */
	public static final String deliveryAddress2_COLUMN = "DELIVERY_ADDRESS2";

	/** COLUMNアノテーション deliveryAddress3 */
	public static final String deliveryAddress3_COLUMN = "DELIVERY_ADDRESS3";

	/** COLUMNアノテーション deliveryTelNo */
	public static final String deliveryTelNo_COLUMN = "DELIVERY_TEL_NO";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション delMail */
	public static final String delMail_COLUMN = "DEL_MAIL";

	/** COLUMNアノテーション airport */
	public static final String airport_COLUMN = "AIRPORT";

	/** COLUMNアノテーション airportCd */
	public static final String airportCd_COLUMN = "AIRPORT_CD";

	/** COLUMNアノテーション receiverName */
	public static final String receiverName_COLUMN = "RECEIVER_NAME";

	/** COLUMNアノテーション clientName */
	public static final String clientName_COLUMN = "CLIENT_NAME";

	/** COLUMNアノテーション clientTitle */
	public static final String clientTitle_COLUMN = "CLIENT_TITLE";

	/** COLUMNアノテーション clientNameKana */
	public static final String clientNameKana_COLUMN = "CLIENT_NAME_KANA";

	/** COLUMNアノテーション postalClientCd */
	public static final String postalClientCd_COLUMN = "POSTAL_CLIENT_CD";

	/** COLUMNアノテーション clientZipcodeNo */
	public static final String clientZipcodeNo_COLUMN = "CLIENT_ZIPCODE_NO";

	/** COLUMNアノテーション clientAddress1 */
	public static final String clientAddress1_COLUMN = "CLIENT_ADDRESS1";

	/** COLUMNアノテーション clientAddress2 */
	public static final String clientAddress2_COLUMN = "CLIENT_ADDRESS2";

	/** COLUMNアノテーション clientAddress3 */
	public static final String clientAddress3_COLUMN = "CLIENT_ADDRESS3";

	/** COLUMNアノテーション clientAddress4 */
	public static final String clientAddress4_COLUMN = "CLIENT_ADDRESS4";

	/** COLUMNアノテーション clientTelNo */
	public static final String clientTelNo_COLUMN = "CLIENT_TEL_NO";

	/** COLUMNアノテーション postalClientName */
	public static final String postalClientName_COLUMN = "POSTAL_CLIENT_NAME";

	/** COLUMNアノテーション clientSection */
	public static final String clientSection_COLUMN = "CLIENT_SECTION";

	/** COLUMNアノテーション clientMail */
	public static final String clientMail_COLUMN = "CLIENT_MAIL";

	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション shippingInstruction */
	public static final String shippingInstruction_COLUMN = "SHIPPING_INSTRUCTION";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション scheduledShippingTime */
	public static final String scheduledShippingTime_COLUMN = "SCHEDULED_SHIPPING_TIME";

	/** COLUMNアノテーション security */
	public static final String security_COLUMN = "SECURITY";

	/** COLUMNアノテーション upWeight */
	public static final String upWeight_COLUMN = "UP_WEIGHT";

	/** COLUMNアノテーション damageAmount */
	public static final String damageAmount_COLUMN = "DAMAGE_AMOUNT";

	/** COLUMNアノテーション cooling */
	public static final String cooling_COLUMN = "COOLING";

	/** COLUMNアノテーション warningBreak */
	public static final String warningBreak_COLUMN = "WARNING_BREAK";

	/** COLUMNアノテーション warningRaw */
	public static final String warningRaw_COLUMN = "WARNING_RAW";

	/** COLUMNアノテーション bin */
	public static final String bin_COLUMN = "BIN";

	/** COLUMNアノテーション upsideDown */
	public static final String upsideDown_COLUMN = "UPSIDE_DOWN";

	/** COLUMNアノテーション warningLower */
	public static final String warningLower_COLUMN = "WARNING_LOWER";

	/** COLUMNアノテーション heavy */
	public static final String heavy_COLUMN = "HEAVY";

	/** COLUMNアノテーション shippingPlanDate */
	public static final String shippingPlanDate_COLUMN = "SHIPPING_PLAN_DATE";

	/** COLUMNアノテーション shippingPlanTime */
	public static final String shippingPlanTime_COLUMN = "SHIPPING_PLAN_TIME";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション deliveryExpectedTime */
	public static final String deliveryExpectedTime_COLUMN = "DELIVERY_EXPECTED_TIME";

	/** COLUMNアノテーション clubNum */
	public static final String clubNum_COLUMN = "CLUB_NUM";

	/** COLUMNアノテーション playDate */
	public static final String playDate_COLUMN = "PLAY_DATE";

	/** COLUMNアノテーション useTime */
	public static final String useTime_COLUMN = "USE_TIME";

	/** COLUMNアノテーション boardingDate */
	public static final String boardingDate_COLUMN = "BOARDING_DATE";

	/** COLUMNアノテーション boardingTime */
	public static final String boardingTime_COLUMN = "BOARDING_TIME";

	/** COLUMNアノテーション boardingName */
	public static final String boardingName_COLUMN = "BOARDING_NAME";

	/** COLUMNアノテーション returnPathScheduledDate */
	public static final String returnPathScheduledDate_COLUMN = "RETURN_PATH_SCHEDULED_DATE";

	/** COLUMNアノテーション paymentMethod2 */
	public static final String paymentMethod2_COLUMN = "PAYMENT_METHOD2";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション shippngSize */
	public static final String shippngSize_COLUMN = "SHIPPNG_SIZE";

	/** COLUMNアノテーション shippingMethod */
	public static final String shippingMethod_COLUMN = "SHIPPING_METHOD";

	/** COLUMNアノテーション discount */
	public static final String discount_COLUMN = "DISCOUNT";

	/** COLUMNアノテーション codAmount */
	public static final String codAmount_COLUMN = "COD_AMOUNT";

	/** COLUMNアノテーション tax */
	public static final String tax_COLUMN = "TAX";

	/** COLUMNアノテーション deliveryDateInfo */
	public static final String deliveryDateInfo_COLUMN = "DELIVERY_DATE_INFO";

	/** COLUMNアノテーション delCompInfoDel */
	public static final String delCompInfoDel_COLUMN = "DEL_COMP_INFO_DEL";

	/** COLUMNアノテーション absenceInfo */
	public static final String absenceInfo_COLUMN = "ABSENCE_INFO";

	/** COLUMNアノテーション postOfficeInfo */
	public static final String postOfficeInfo_COLUMN = "POST_OFFICE_INFO";

	/** COLUMNアノテーション delCompInfoCli */
	public static final String delCompInfoCli_COLUMN = "DEL_COMP_INFO_CLI";

	//
	// インスタンスフィールド
	//
	private String shippingNo;

	private String itemCategory;

	private String paymentMethod;

	private String golfSkyAirport;

	private String roundTrip;

	private String shippingOption;

	private String deliveryMethod;

	private String shippingNum;

	private String deliveryName;

	private String title;

	private String deliveryNameKana;

	private String delZipcodeNo;

	private String prefectures;

	private String deliveryAddress1;

	private String deliveryAddress2;

	private String deliveryAddress3;

	private String deliveryTelNo;

	private String deliveryName1;

	private String deliveryName2;

	private String delMail;

	private String airport;

	private String airportCd;

	private String receiverName;

	private String clientName;

	private String clientTitle;

	private String clientNameKana;

	private String postalClientCd;

	private String clientZipcodeNo;

	private String clientAddress1;

	private String clientAddress2;

	private String clientAddress3;

	private String clientAddress4;

	private String clientTelNo;

	private String postalClientName;

	private String clientSection;

	private String clientMail;

	private String itemName;

	private String styleOfPacking;

	private String shippingInstruction;

	private String scheduledShippingDate;

	private String scheduledShippingTime;

	private String security;

	private String upWeight;

	private String damageAmount;

	private String cooling;

	private String warningBreak;

	private String warningRaw;

	private String bin;

	private String upsideDown;

	private String warningLower;

	private String heavy;

	private String shippingPlanDate;

	private String shippingPlanTime;

	private String deliveryExpectedDate;

	private String deliveryExpectedTime;

	private String clubNum;

	private String playDate;

	private String useTime;

	private String boardingDate;

	private String boardingTime;

	private String boardingName;

	private String returnPathScheduledDate;

	private String paymentMethod2;

	private String orderNo;

	private String shippngSize;

	private String shippingMethod;

	private String discount;

	private String codAmount;

	private String tax;

	private String deliveryDateInfo;

	private String delCompInfoDel;

	private String absenceInfo;

	private String postOfficeInfo;

	private String delCompInfoCli;



	//
	// インスタンスメソッド
	//
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
	 * itemCategoryを取得します。
	 * @return itemCategory
	 */
	public String getItemCategory() {
		return itemCategory;
	}

	/**
	 * itemCategoryを設定します。
	 * @param itemCategory itemCategory
	 */
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * paymentMethodを取得します。
	 * @return paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * paymentMethodを設定します。
	 * @param paymentMethod paymentMethod
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * golfSkyAirportを取得します。
	 * @return golfSkyAirport
	 */
	public String getGolfSkyAirport() {
		return golfSkyAirport;
	}

	/**
	 * golfSkyAirportを設定します。
	 * @param golfSkyAirport golfSkyAirport
	 */
	public void setGolfSkyAirport(String golfSkyAirport) {
		this.golfSkyAirport = golfSkyAirport;
	}

	/**
	 * roundTripを取得します。
	 * @return roundTrip
	 */
	public String getRoundTrip() {
		return roundTrip;
	}

	/**
	 * roundTripを設定します。
	 * @param roundTrip roundTrip
	 */
	public void setRoundTrip(String roundTrip) {
		this.roundTrip = roundTrip;
	}

	/**
	 * shippingOptionを取得します。
	 * @return shippingOption
	 */
	public String getShippingOption() {
		return shippingOption;
	}

	/**
	 * shippingOptionを設定します。
	 * @param shippingOption shippingOption
	 */
	public void setShippingOption(String shippingOption) {
		this.shippingOption = shippingOption;
	}

	/**
	 * deliveryMethodを取得します。
	 * @return deliveryMethod
	 */
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	/**
	 * deliveryMethodを設定します。
	 * @param deliveryMethod deliveryMethod
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	/**
	 * shippingNumを取得します。
	 * @return shippingNum
	 */
	public String getShippingNum() {
		return shippingNum;
	}

	/**
	 * shippingNumを設定します。
	 * @param shippingNum shippingNum
	 */
	public void setShippingNum(String shippingNum) {
		this.shippingNum = shippingNum;
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
	 * titleを取得します。
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * titleを設定します。
	 * @param title title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * deliveryNameKanaを取得します。
	 * @return deliveryNameKana
	 */
	public String getDeliveryNameKana() {
		return deliveryNameKana;
	}

	/**
	 * deliveryNameKanaを設定します。
	 * @param deliveryNameKana deliveryNameKana
	 */
	public void setDeliveryNameKana(String deliveryNameKana) {
		this.deliveryNameKana = deliveryNameKana;
	}

	/**
	 * delZipcodeNoを取得します。
	 * @return delZipcodeNo
	 */
	public String getDelZipcodeNo() {
		return delZipcodeNo;
	}

	/**
	 * delZipcodeNoを設定します。
	 * @param delZipcodeNo delZipcodeNo
	 */
	public void setDelZipcodeNo(String delZipcodeNo) {
		this.delZipcodeNo = delZipcodeNo;
	}

	/**
	 * prefecturesを取得します。
	 * @return prefectures
	 */
	public String getPrefectures() {
		return prefectures;
	}

	/**
	 * prefecturesを設定します。
	 * @param prefectures prefectures
	 */
	public void setPrefectures(String prefectures) {
		this.prefectures = prefectures;
	}

	/**
	 * deliveryAddress1を取得します。
	 * @return deliveryAddress1
	 */
	public String getDeliveryAddress1() {
		return deliveryAddress1;
	}

	/**
	 * deliveryAddress1を設定します。
	 * @param deliveryAddress1 deliveryAddress1
	 */
	public void setDeliveryAddress1(String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	/**
	 * deliveryAddress2を取得します。
	 * @return deliveryAddress2
	 */
	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	/**
	 * deliveryAddress2を設定します。
	 * @param deliveryAddress2 deliveryAddress2
	 */
	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	/**
	 * deliveryAddress3を取得します。
	 * @return deliveryAddress3
	 */
	public String getDeliveryAddress3() {
		return deliveryAddress3;
	}

	/**
	 * deliveryAddress3を設定します。
	 * @param deliveryAddress3 deliveryAddress3
	 */
	public void setDeliveryAddress3(String deliveryAddress3) {
		this.deliveryAddress3 = deliveryAddress3;
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
	 * deliveryName1を取得します。
	 * @return deliveryName1
	 */
	public String getDeliveryName1() {
		return deliveryName1;
	}

	/**
	 * deliveryName1を設定します。
	 * @param deliveryName1 deliveryName1
	 */
	public void setDeliveryName1(String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * deliveryName2を取得します。
	 * @return deliveryName2
	 */
	public String getDeliveryName2() {
		return deliveryName2;
	}

	/**
	 * deliveryName2を設定します。
	 * @param deliveryName2 deliveryName2
	 */
	public void setDeliveryName2(String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * delMailを取得します。
	 * @return delMail
	 */
	public String getDelMail() {
		return delMail;
	}

	/**
	 * delMailを設定します。
	 * @param delMail delMail
	 */
	public void setDelMail(String delMail) {
		this.delMail = delMail;
	}

	/**
	 * airportを取得します。
	 * @return airport
	 */
	public String getAirport() {
		return airport;
	}

	/**
	 * airportを設定します。
	 * @param airport airport
	 */
	public void setAirport(String airport) {
		this.airport = airport;
	}

	/**
	 * airportCdを取得します。
	 * @return airportCd
	 */
	public String getAirportCd() {
		return airportCd;
	}

	/**
	 * airportCdを設定します。
	 * @param airportCd airportCd
	 */
	public void setAirportCd(String airportCd) {
		this.airportCd = airportCd;
	}

	/**
	 * receiverNameを取得します。
	 * @return receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * receiverNameを設定します。
	 * @param receiverName receiverName
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * clientNameを取得します。
	 * @return clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * clientNameを設定します。
	 * @param clientName clientName
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * clientTitleを取得します。
	 * @return clientTitle
	 */
	public String getClientTitle() {
		return clientTitle;
	}

	/**
	 * clientTitleを設定します。
	 * @param clientTitle clientTitle
	 */
	public void setClientTitle(String clientTitle) {
		this.clientTitle = clientTitle;
	}

	/**
	 * clientNameKanaを取得します。
	 * @return clientNameKana
	 */
	public String getClientNameKana() {
		return clientNameKana;
	}

	/**
	 * clientNameKanaを設定します。
	 * @param clientNameKana clientNameKana
	 */
	public void setClientNameKana(String clientNameKana) {
		this.clientNameKana = clientNameKana;
	}

	/**
	 * postalClientCdを取得します。
	 * @return postalClientCd
	 */
	public String getPostalClientCd() {
		return postalClientCd;
	}

	/**
	 * postalClientCdを設定します。
	 * @param postalClientCd postalClientCd
	 */
	public void setPostalClientCd(String postalClientCd) {
		this.postalClientCd = postalClientCd;
	}

	/**
	 * clientZipcodeNoを取得します。
	 * @return clientZipcodeNo
	 */
	public String getClientZipcodeNo() {
		return clientZipcodeNo;
	}

	/**
	 * clientZipcodeNoを設定します。
	 * @param clientZipcodeNo clientZipcodeNo
	 */
	public void setClientZipcodeNo(String clientZipcodeNo) {
		this.clientZipcodeNo = clientZipcodeNo;
	}

	/**
	 * clientAddress1を取得します。
	 * @return clientAddress1
	 */
	public String getClientAddress1() {
		return clientAddress1;
	}

	/**
	 * clientAddress1を設定します。
	 * @param clientAddress1 clientAddress1
	 */
	public void setClientAddress1(String clientAddress1) {
		this.clientAddress1 = clientAddress1;
	}

	/**
	 * clientAddress2を取得します。
	 * @return clientAddress2
	 */
	public String getClientAddress2() {
		return clientAddress2;
	}

	/**
	 * clientAddress2を設定します。
	 * @param clientAddress2 clientAddress2
	 */
	public void setClientAddress2(String clientAddress2) {
		this.clientAddress2 = clientAddress2;
	}

	/**
	 * clientAddress3を取得します。
	 * @return clientAddress3
	 */
	public String getClientAddress3() {
		return clientAddress3;
	}

	/**
	 * clientAddress3を設定します。
	 * @param clientAddress3 clientAddress3
	 */
	public void setClientAddress3(String clientAddress3) {
		this.clientAddress3 = clientAddress3;
	}

	/**
	 * clientAddress4を取得します。
	 * @return clientAddress4
	 */
	public String getClientAddress4() {
		return clientAddress4;
	}

	/**
	 * clientAddress4を設定します。
	 * @param clientAddress4 clientAddress4
	 */
	public void setClientAddress4(String clientAddress4) {
		this.clientAddress4 = clientAddress4;
	}

	/**
	 * clientTelNoを取得します。
	 * @return clientTelNo
	 */
	public String getClientTelNo() {
		return clientTelNo;
	}

	/**
	 * clientTelNoを設定します。
	 * @param clientTelNo clientTelNo
	 */
	public void setClientTelNo(String clientTelNo) {
		this.clientTelNo = clientTelNo;
	}

	/**
	 * postalClientNameを取得します。
	 * @return postalClientName
	 */
	public String getPostalClientName() {
		return postalClientName;
	}

	/**
	 * postalClientNameを設定します。
	 * @param postalClientName postalClientName
	 */
	public void setPostalClientName(String postalClientName) {
		this.postalClientName = postalClientName;
	}

	/**
	 * clientSectionを取得します。
	 * @return clientSection
	 */
	public String getClientSection() {
		return clientSection;
	}

	/**
	 * clientSectionを設定します。
	 * @param clientSection clientSection
	 */
	public void setClientSection(String clientSection) {
		this.clientSection = clientSection;
	}

	/**
	 * clientMailを取得します。
	 * @return clientMail
	 */
	public String getClientMail() {
		return clientMail;
	}

	/**
	 * clientMailを設定します。
	 * @param clientMail clientMail
	 */
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
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
	 * shippingInstructionを取得します。
	 * @return shippingInstruction
	 */
	public String getShippingInstruction() {
		return shippingInstruction;
	}

	/**
	 * shippingInstructionを設定します。
	 * @param shippingInstruction shippingInstruction
	 */
	public void setShippingInstruction(String shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
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
	 * scheduledShippingTimeを取得します。
	 * @return scheduledShippingTime
	 */
	public String getScheduledShippingTime() {
		return scheduledShippingTime;
	}

	/**
	 * scheduledShippingTimeを設定します。
	 * @param scheduledShippingTime scheduledShippingTime
	 */
	public void setScheduledShippingTime(String scheduledShippingTime) {
		this.scheduledShippingTime = scheduledShippingTime;
	}

	/**
	 * securityを取得します。
	 * @return security
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * securityを設定します。
	 * @param security security
	 */
	public void setSecurity(String security) {
		this.security = security;
	}

	/**
	 * upWeightを取得します。
	 * @return upWeight
	 */
	public String getUpWeight() {
		return upWeight;
	}

	/**
	 * upWeightを設定します。
	 * @param upWeight upWeight
	 */
	public void setUpWeight(String upWeight) {
		this.upWeight = upWeight;
	}

	/**
	 * damageAmountを取得します。
	 * @return damageAmount
	 */
	public String getDamageAmount() {
		return damageAmount;
	}

	/**
	 * damageAmountを設定します。
	 * @param damageAmount damageAmount
	 */
	public void setDamageAmount(String damageAmount) {
		this.damageAmount = damageAmount;
	}

	/**
	 * coolingを取得します。
	 * @return cooling
	 */
	public String getCooling() {
		return cooling;
	}

	/**
	 * coolingを設定します。
	 * @param cooling cooling
	 */
	public void setCooling(String cooling) {
		this.cooling = cooling;
	}

	/**
	 * warningBreakを取得します。
	 * @return warningBreak
	 */
	public String getWarningBreak() {
		return warningBreak;
	}

	/**
	 * warningBreakを設定します。
	 * @param warningBreak warningBreak
	 */
	public void setWarningBreak(String warningBreak) {
		this.warningBreak = warningBreak;
	}

	/**
	 * warningRawを取得します。
	 * @return warningRaw
	 */
	public String getWarningRaw() {
		return warningRaw;
	}

	/**
	 * warningRawを設定します。
	 * @param warningRaw warningRaw
	 */
	public void setWarningRaw(String warningRaw) {
		this.warningRaw = warningRaw;
	}

	/**
	 * binを取得します。
	 * @return bin
	 */
	public String getBin() {
		return bin;
	}

	/**
	 * binを設定します。
	 * @param bin bin
	 */
	public void setBin(String bin) {
		this.bin = bin;
	}

	/**
	 * upsideDownを取得します。
	 * @return upsideDown
	 */
	public String getUpsideDown() {
		return upsideDown;
	}

	/**
	 * upsideDownを設定します。
	 * @param upsideDown upsideDown
	 */
	public void setUpsideDown(String upsideDown) {
		this.upsideDown = upsideDown;
	}

	/**
	 * warningLowerを取得します。
	 * @return warningLower
	 */
	public String getWarningLower() {
		return warningLower;
	}

	/**
	 * warningLowerを設定します。
	 * @param warningLower warningLower
	 */
	public void setWarningLower(String warningLower) {
		this.warningLower = warningLower;
	}

	/**
	 * heavyを取得します。
	 * @return heavy
	 */
	public String getHeavy() {
		return heavy;
	}

	/**
	 * heavyを設定します。
	 * @param heavy heavy
	 */
	public void setHeavy(String heavy) {
		this.heavy = heavy;
	}

	/**
	 * shippingPlanDateを取得します。
	 * @return shippingPlanDate
	 */
	public String getShippingPlanDate() {
		return shippingPlanDate;
	}

	/**
	 * shippingPlanDateを設定します。
	 * @param shippingPlanDate shippingPlanDate
	 */
	public void setShippingPlanDate(String shippingPlanDate) {
		this.shippingPlanDate = shippingPlanDate;
	}

	/**
	 * shippingPlanTimeを取得します。
	 * @return shippingPlanTime
	 */
	public String getShippingPlanTime() {
		return shippingPlanTime;
	}

	/**
	 * shippingPlanTimeを設定します。
	 * @param shippingPlanTime shippingPlanTime
	 */
	public void setShippingPlanTime(String shippingPlanTime) {
		this.shippingPlanTime = shippingPlanTime;
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
	 * clubNumを取得します。
	 * @return clubNum
	 */
	public String getClubNum() {
		return clubNum;
	}

	/**
	 * clubNumを設定します。
	 * @param clubNum clubNum
	 */
	public void setClubNum(String clubNum) {
		this.clubNum = clubNum;
	}

	/**
	 * playDateを取得します。
	 * @return playDate
	 */
	public String getPlayDate() {
		return playDate;
	}

	/**
	 * playDateを設定します。
	 * @param playDate playDate
	 */
	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}

	/**
	 * useTimeを取得します。
	 * @return useTime
	 */
	public String getUseTime() {
		return useTime;
	}

	/**
	 * useTimeを設定します。
	 * @param useTime useTime
	 */
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	/**
	 * boardingDateを取得します。
	 * @return boardingDate
	 */
	public String getBoardingDate() {
		return boardingDate;
	}

	/**
	 * boardingDateを設定します。
	 * @param boardingDate boardingDate
	 */
	public void setBoardingDate(String boardingDate) {
		this.boardingDate = boardingDate;
	}

	/**
	 * boardingTimeを取得します。
	 * @return boardingTime
	 */
	public String getBoardingTime() {
		return boardingTime;
	}

	/**
	 * boardingTimeを設定します。
	 * @param boardingTime boardingTime
	 */
	public void setBoardingTime(String boardingTime) {
		this.boardingTime = boardingTime;
	}

	/**
	 * boardingNameを取得します。
	 * @return boardingName
	 */
	public String getBoardingName() {
		return boardingName;
	}

	/**
	 * boardingNameを設定します。
	 * @param boardingName boardingName
	 */
	public void setBoardingName(String boardingName) {
		this.boardingName = boardingName;
	}

	/**
	 * returnPathScheduledDateを取得します。
	 * @return returnPathScheduledDate
	 */
	public String getReturnPathScheduledDate() {
		return returnPathScheduledDate;
	}

	/**
	 * returnPathScheduledDateを設定します。
	 * @param returnPathScheduledDate returnPathScheduledDate
	 */
	public void setReturnPathScheduledDate(String returnPathScheduledDate) {
		this.returnPathScheduledDate = returnPathScheduledDate;
	}

	/**
	 * paymentMethod2を取得します。
	 * @return paymentMethod2
	 */
	public String getPaymentMethod2() {
		return paymentMethod2;
	}

	/**
	 * paymentMethod2を設定します。
	 * @param paymentMethod2 paymentMethod2
	 */
	public void setPaymentMethod2(String paymentMethod2) {
		this.paymentMethod2 = paymentMethod2;
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
	 * shippngSizeを取得します。
	 * @return shippngSize
	 */
	public String getShippngSize() {
		return shippngSize;
	}

	/**
	 * shippngSizeを設定します。
	 * @param shippngSize shippngSize
	 */
	public void setShippngSize(String shippngSize) {
		this.shippngSize = shippngSize;
	}

	/**
	 * shippingMethodを取得します。
	 * @return shippingMethod
	 */
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * shippingMethodを設定します。
	 * @param shippingMethod shippingMethod
	 */
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * discountを取得します。
	 * @return discount
	 */
	public String getDiscount() {
		return discount;
	}

	/**
	 * discountを設定します。
	 * @param discount discount
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}

	/**
	 * codAmountを取得します。
	 * @return codAmount
	 */
	public String getCodAmount() {
		return codAmount;
	}

	/**
	 * codAmountを設定します。
	 * @param codAmount codAmount
	 */
	public void setCodAmount(String codAmount) {
		this.codAmount = codAmount;
	}

	/**
	 * taxを取得します。
	 * @return tax
	 */
	public String getTax() {
		return tax;
	}

	/**
	 * taxを設定します。
	 * @param tax tax
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * deliveryDateInfoを取得します。
	 * @return deliveryDateInfo
	 */
	public String getDeliveryDateInfo() {
		return deliveryDateInfo;
	}

	/**
	 * deliveryDateInfoを設定します。
	 * @param deliveryDateInfo deliveryDateInfo
	 */
	public void setDeliveryDateInfo(String deliveryDateInfo) {
		this.deliveryDateInfo = deliveryDateInfo;
	}

	/**
	 * delCompInfoDelを取得します。
	 * @return delCompInfoDel
	 */
	public String getDelCompInfoDel() {
		return delCompInfoDel;
	}

	/**
	 * delCompInfoDelを設定します。
	 * @param delCompInfoDel delCompInfoDel
	 */
	public void setDelCompInfoDel(String delCompInfoDel) {
		this.delCompInfoDel = delCompInfoDel;
	}

	/**
	 * absenceInfoを取得します。
	 * @return absenceInfo
	 */
	public String getAbsenceInfo() {
		return absenceInfo;
	}

	/**
	 * absenceInfoを設定します。
	 * @param absenceInfo absenceInfo
	 */
	public void setAbsenceInfo(String absenceInfo) {
		this.absenceInfo = absenceInfo;
	}

	/**
	 * postOfficeInfoを取得します。
	 * @return postOfficeInfo
	 */
	public String getPostOfficeInfo() {
		return postOfficeInfo;
	}

	/**
	 * postOfficeInfoを設定します。
	 * @param postOfficeInfo postOfficeInfo
	 */
	public void setPostOfficeInfo(String postOfficeInfo) {
		this.postOfficeInfo = postOfficeInfo;
	}

	/**
	 * delCompInfoCliを取得します。
	 * @return delCompInfoCli
	 */
	public String getDelCompInfoCli() {
		return delCompInfoCli;
	}

	/**
	 * delCompInfoCliを設定します。
	 * @param delCompInfoCli delCompInfoCli
	 */
	public void setDelCompInfoCli(String delCompInfoCli) {
		this.delCompInfoCli = delCompInfoCli;
	}

	
}

