/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 納期回答まとめ入力画面_ヘッダ部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseDeliverySumDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetailBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション.*/
	public static final String TABLE = "PURCHASE_SUBCONTRACT";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション venderName */
	public static final String venderName_COLUMN = "VENDER_NAME";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	//
	// インスタンスフィールド
	//

	/** 発注書NO */
	private String orderSheetNo;

	/** 発注日 */
	private Timestamp orderDate;

	/** 仕入先名称 */
	private String venderName;

	/** 納入先名称 */
	private String locationName;

	//
	// インスタンスメソッド
	//

	/**
	 * 発注書NO取得
	 * @return String 発注書NO
	 */
	public String getOrderSheetNo() {
		return this.orderSheetNo;
	}

	/**
	 * 発注書NO設定
	 * @param orderSheetNo 発注書NO
	 */
	public void setOrderSheetNo(final String orderSheetNo) {
		this.orderSheetNo = orderSheetNo;
	}

	/**
	 * 発注日取得
	 * @return Timestamp 発注日
	 */
	public Timestamp getOrderDate() {
		return this.orderDate;
	}

	/**
	 * 発注日設定
	 * @param orderDate 発注日
	 */
	public void setOrderDate(final Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * 仕入先名称取得
	 * @return String 仕入先名称
	 */
	public String getVenderName() {
		return this.venderName;
	}

	/**
	 * 仕入先名称設定
	 * @param venderName 仕入先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
	}

	/**
	 * 納入先名称を取得します。
	 * @return String 納入先名称
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * 納入先名称を設定します。
	 * @param locationName 納入先名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
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
