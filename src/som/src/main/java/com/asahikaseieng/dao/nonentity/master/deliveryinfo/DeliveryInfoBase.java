/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliveryinfo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * DeliveryDetailクラス.
 * @author t0011036
 */
public class DeliveryInfoBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DeliveryInfoBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション faxNo */
	public static final String faxNo_COLUMN = "FAX_NO";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション fareClaimExistence */
	public static final String fareClaimExistence_COLUMN = "FARE_CLAIM_EXISTENCE";

	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";
	
	/** COLUMNアノテーション deliveryTime */
	public static final String deliveryTime_COLUMN = "DELIVERY_TIME";

	/** COLUMNアノテーション tantoCd */
	public static final String tantoCd_COLUMN = "TANTO_CD";
	
	/** COLUMNアノテーション venderGroupCd */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";


	//
	// インスタンスフィールド
	//

	private String deliveryCd;

	private String deliveryName1;

	private String deliveryName2;

	private String searchKana;

	private String address1;

	private String address2;

	private String address3;

	private String telNo;

	private String faxNo;

	private String carryCd;

	private java.math.BigDecimal leadTime;

	private String deliveryTime;

	private java.math.BigDecimal fareClaimExistence;

	private String tantoCd;

	private String venderGroupCd;

	//
	// インスタンスメソッド
	//

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
	 * searchKana取得.
	 * @return searchKana
	 */
	public String getSearchKana() {
		return this.searchKana;
	}

	/**
	 * searchKana設定.
	 * @param searchKana searchKana
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * address1取得.
	 * @return address1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * address1設定.
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2取得.
	 * @return address2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * address2設定.
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3取得.
	 * @return address3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * address3設定.
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
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
	 * faxNo取得.
	 * @return faxNo
	 */
	public String getFaxNo() {
		return this.faxNo;
	}

	/**
	 * faxNo設定.
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
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
	 * leadTime取得.
	 * @return leadTime
	 */
	public java.math.BigDecimal getLeadTime() {
		return this.leadTime;
	}

	/**
	 * leadTime設定.
	 * @param leadTime leadTime
	 */
	public void setLeadTime(final java.math.BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * deliveryTime取得.
	 * @return deliveryTime
	 */
	public String getDeliveryTime() {
		return this.deliveryTime;
	}

	/**
	 * deliveryTime設定.
	 * @param deliveryTime deliveryTime
	 */
	public void setDeliveryTime(final String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * fareClaimExistence取得.
	 * @return fareClaimExistence
	 */
	public java.math.BigDecimal getFareClaimExistence() {
		return this.fareClaimExistence;
	}

	/**
	 * fareClaimExistence設定.
	 * @param fareClaimExistence fareClaimExistence
	 */
	public void setFareClaimExistence(final java.math.BigDecimal fareClaimExistence) {
		this.fareClaimExistence = fareClaimExistence;
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
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
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

