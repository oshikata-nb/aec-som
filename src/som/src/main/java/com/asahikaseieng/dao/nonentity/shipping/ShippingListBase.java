/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SHIPPING";

	/** COLUMNアノテーション shippingNo */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション scheduledShippingDate */
	public static final String scheduledShippingDate_COLUMN = "SCHEDULED_SHIPPING_DATE";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション shippingStatus */
	public static final String shippingStatus_COLUMN = "SHIPPING_STATUS";

	/** COLUMNアノテーション shippingResultDate */
	public static final String shippingResultDate_COLUMN = "SHIPPING_RESULT_DATE";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション shippingInstruction */
	public static final String shippingInstruction_COLUMN = "SHIPPING_INSTRUCTION";

	/** COLUMNアノテーション shippingUnitprice */
	public static final String shippingUnitprice_COLUMN = "SHIPPING_UNITPRICE";


	//
	// インスタンスフィールド
	//

	/** 出荷番号 */
	private String shippingNo;

	/** 受注番号 */
	private String orderNo;

	/** 出荷指図数量 */
	private BigDecimal shippingInstruction;

    /** 出荷予定日 */
	private Timestamp scheduledShippingDate;

	/** 出荷ステータス */
	private BigDecimal shippingStatus;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 出荷番号取得
	 * @return String 出荷番号
	*/
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * 出荷番号設定
	 * @param shippingNo 出荷番号
	*/
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * 受注番号取得
	 * @return String 受注番号
	*/
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	*/
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 出荷指図数量取得
	 * @return BigDecimal 出荷指図数量
	*/
	public BigDecimal getShippingInstruction() {
		return this.shippingInstruction;
	}

	/**
	 * 出荷指図数量設定
	 * @param shippingInstruction 出荷指図数量
	*/
	public void setShippingInstruction(final BigDecimal shippingInstruction) {
		this.shippingInstruction = shippingInstruction;
	}

	/**
	 * 出荷予定日取得
	 * @return Timestamp 出荷予定日
	*/
	public Timestamp getScheduledShippingDate() {
		return this.scheduledShippingDate;
	}

	/**
	 * 出荷予定日設定
	 * @param scheduledShippingDate 出荷予定日
	*/
	public void setScheduledShippingDate(final Timestamp scheduledShippingDate) {
		this.scheduledShippingDate = scheduledShippingDate;
	}

	/**
	 * 出荷ステータス取得
	 * @return BigDecimal 出荷ステータス
	*/
	public BigDecimal getShippingStatus() {
		return this.shippingStatus;
	}

	/**
	 * 出荷ステータス設定
	 * @param shippingStatus 出荷ステータス
	*/
	public void setShippingStatus(final BigDecimal shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	*/
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	*/
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
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
