/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 納入先検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class SalesDeliverySearchListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesDeliverySearchListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
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

