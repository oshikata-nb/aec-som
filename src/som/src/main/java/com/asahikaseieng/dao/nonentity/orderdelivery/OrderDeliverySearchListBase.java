/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdelivery;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 納入先検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class OrderDeliverySearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderDeliverySearchListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "DELIVERY";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション searchKana */
	public static final String searchKana_COLUMN = "SEARCH_KANA";

	/** COLUMNアノテーション deliveryName1 */
	public static final String deliveryName1_COLUMN = "DELIVERY_NAME1";

	/** COLUMNアノテーション deliveryName2 */
	public static final String deliveryName2_COLUMN = "DELIVERY_NAME2";

	/** COLUMNアノテーション address1 */
	public static final String address1_COLUMN = "ADDRESS1";

	/** COLUMNアノテーション address2 */
	public static final String address2_COLUMN = "ADDRESS2";

	/** COLUMNアノテーション address3 */
	public static final String address3_COLUMN = "ADDRESS3";

	/** COLUMNアノテーション telNo */
	public static final String telNo_COLUMN = "TEL_NO";

	/** COLUMNアノテーション fareClaimExistence */
	public static final String fareClaimExistence_COLUMN = "FARE_CLAIM_EXISTENCE";

	/** COLUMNアノテーション eigyoTantoCd */
	public static final String eigyoTantoCd_COLUMN = "EIGYO_TANTO_CD";

	/** COLUMNアノテーション eigyoTantoName */
	public static final String eigyoTantoName_COLUMN = "EIGYO_TANTO_NAME";

	/** COLUMNアノテーション leadTime */
	public static final String leadTime_COLUMN = "LEAD_TIME";

	/** COLUMNアノテーション deliveryTime */
	public static final String deliveryTime_COLUMN = "DELIVERY_TIME";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	//
	// インスタンスフィールド
	//

	/** 納入先コード */
	private String deliveryCd;

	/** 納入先略称 */
	private String searchKana;

	/** 納入先名称1 */
	private String deliveryName1;

	/** 納入先名称2 */
	private String deliveryName2;

	/** 納入先住所1 */
	private String address1;

	/** 納入先住所2 */
	private String address2;

	/** 納入先住所3 */
	private String address3;

	/** 電話番号 */
	private String telNo;

	/** 運賃請求有無フラグ */
	private String fareClaimExistence;

	/** 営業担当者コード */
	private String eigyoTantoCd;

	/** 営業担当者名 */
	private String eigyoTantoName;

	/** リードタイム */
	private BigDecimal leadTime;

	/** 納入時刻 */
	private String deliveryTime;

	/** 運送会社コード */
	private String carryCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 納入先コード取得
	 * @return String 納入先コード
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * 納入先コード設定
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * 納入先略称取得
	 * @return String 納入先略称
	 */
	public String getSearchKana() {
		return this.searchKana;
	}

	/**
	 * 納入先略称設定
	 * @param searchKana 納入先略称
	 */
	public void setSearchKana(final String searchKana) {
		this.searchKana = searchKana;
	}

	/**
	 * 納入先略称取得
	 * @return String 納入先略称
	 */
	public String getDeliveryName1() {
		return this.deliveryName1;
	}

	/**
	 * 納入先略称設定
	 * @param deliveryName1 納入先略称
	 */
	public void setDeliveryName1(final String deliveryName1) {
		this.deliveryName1 = deliveryName1;
	}

	/**
	 * 納入先名称2取得
	 * @return String 納入先名称2
	 */
	public String getDeliveryName2() {
		return this.deliveryName2;
	}

	/**
	 * 納入先名称2設定
	 * @param deliveryName2 納入先名称2
	 */
	public void setDeliveryName2(final String deliveryName2) {
		this.deliveryName2 = deliveryName2;
	}

	/**
	 * 納入先住所1取得
	 * @return String 納入先住所1
	 */
	public String getAddress1() {
		return this.address1;
	}

	/**
	 * 納入先住所1設定
	 * @param address1 納入先住所1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * 納入先住所2取得
	 * @return String 納入先住所2
	 */
	public String getAddress2() {
		return this.address2;
	}

	/**
	 * 納入先住所2設定
	 * @param address2 納入先住所2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * 納入先住所3取得
	 * @return String 納入先住所3
	 */
	public String getAddress3() {
		return this.address3;
	}

	/**
	 * 納入先住所3設定
	 * @param address3 納入先住所3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * 電話番号取得
	 * @return String 電話番号
	 */
	public String getTelNo() {
		return this.telNo;
	}

	/**
	 * 電話番号設定
	 * @param telNo 電話番号
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * 運賃請求有無フラグを取得します。
	 * @return fareClaimExistence
	 */
	public String getFareClaimExistence() {
		return fareClaimExistence;
	}

	/**
	 * 運賃請求有無フラグを設定します。
	 * @param fareClaimExistence 運賃請求有無フラグ
	 */
	public void setFareClaimExistence(final String fareClaimExistence) {
		this.fareClaimExistence = fareClaimExistence;
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
	 * 営業担当者コードを取得します。
	 * @return eigyoTantoCd
	 */
	public String getEigyoTantoCd() {
		return eigyoTantoCd;
	}

	/**
	 * 営業担当者コードを設定します。
	 * @param eigyoTantoCd 営業担当者コード
	 */
	public void setEigyoTantoCd(final String eigyoTantoCd) {
		this.eigyoTantoCd = eigyoTantoCd;
	}

	/**
	 * eigyoTantoNameを取得します。
	 * @return eigyoTantoName
	 */
	public String getEigyoTantoName() {
		return eigyoTantoName;
	}

	/**
	 * eigyoTantoNameを設定します。
	 * @param eigyoTantoName eigyoTantoName
	 */
	public void setEigyoTantoName(final String eigyoTantoName) {
		this.eigyoTantoName = eigyoTantoName;
	}

	/**
	 * 納入時刻を取得します。
	 * @return deliveryTime
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * 納入時刻を設定します。
	 * @param deliveryTime 納入時刻
	 */
	public void setDeliveryTime(final String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * リードタイムを取得します。
	 * @return leadTime
	 */
	public BigDecimal getLeadTime() {
		return leadTime;
	}

	/**
	 * リードタイムを設定します。
	 * @param leadTime リードタイム
	 */
	public void setLeadTime(final BigDecimal leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * 運送会社コードを取得します。
	 * @return carryCd
	 */
	public String getCarryCd() {
		return carryCd;
	}

	/**
	 * 運送会社コードを設定します。
	 * @param carryCd 運送会社コード
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}
}
