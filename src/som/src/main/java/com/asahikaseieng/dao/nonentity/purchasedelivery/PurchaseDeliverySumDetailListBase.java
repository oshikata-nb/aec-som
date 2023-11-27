/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 納期回答まとめ入力画面_明細部表示用データ格納クラス.
 * 
 * @author tosco
 */
public class PurchaseDeliverySumDetailListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetailListBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "PURCHASE_SUBCONTRACT";

	/** COLUMNアノテーション purchaseNo */
	public static final String purchaseNo_COLUMN = "PURCHASE_NO";

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderDevideNo */
	public static final String orderDevideNo_COLUMN = "ORDER_DIVIDE_NO";

	/** COLUMNアノテーション siOrderNo */
	public static final String siOrderNo_COLUMN = "SI_ORDER_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション suggestedDeliverlimitDate */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション replyContentsDivision */
	public static final String replyContentsDivision_COLUMN = "REPLY_CONTENTS_DIVISION";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	/** 購買NO */
	private String purchaseNo;

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 分納番号 */
	private String orderDevideNo;

	/** 仕入先受注番号 */
	private String siOrderNo;

	/** 仕入先コード */
	private String venderCd;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 納品希望日 */
	private Timestamp suggestedDeliverlimitDate;

	/** 購買ステータス */
	private BigDecimal status;

	/** 分納区分 */
	private BigDecimal replyContentsDivision;

	/** 更新日時 */
	private Timestamp updateDate;

	//
	// インスタンスメソッド
	//

	/**
	 * 購買NO取得
	 * @return String 購買NO
	 */
	public String getPurchaseNo() {
		return this.purchaseNo;
	}

	/**
	 * 購買NO設定
	 * @param purchaseNo 購買NO
	 */
	public void setPurchaseNo(final String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	/**
	 * 発注番号取得
	 * @return String 発注番号
	 */
	public String getBuySubcontractOrderNo() {
		return this.buySubcontractOrderNo;
	}

	/**
	 * 発注番号設定
	 * @param buySubcontractOrderNo 発注番号
	 */
	public void setBuySubcontractOrderNo(final String buySubcontractOrderNo) {
		this.buySubcontractOrderNo = buySubcontractOrderNo;
	}

	/**
	 * 発注数量取得
	 * @return BigDecimal 発注数量
	 */
	public BigDecimal getOrderQuantity() {
		return this.orderQuantity;
	}

	/**
	 * 発注数量設定
	 * @param orderQuantity 発注数量
	 */
	public void setOrderQuantity(final BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * 重量取得
	 * @return BigDecimal 重量
	 */
	public BigDecimal getOrderConvertQuantity() {
		return this.orderConvertQuantity;
	}

	/**
	 * 重量設定
	 * @param orderConvertQuantity 重量
	 */
	public void setOrderConvertQuantity(final BigDecimal orderConvertQuantity) {
		this.orderConvertQuantity = orderConvertQuantity;
	}

	/**
	 * 納品希望日取得
	 * @return Timestamp 納品希望日
	 */
	public Timestamp getSuggestedDeliverlimitDate() {
		return this.suggestedDeliverlimitDate;
	}

	/**
	 * 納品希望日設定
	 * @param suggestedDeliverlimitDate 納品希望日
	 */
	public void setSuggestedDeliverlimitDate(
			final Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

	/**
	 * 仕入先受注番取得
	 * @return String 仕入先受注番
	 */
	public String getSiOrderNo() {
		return this.siOrderNo;
	}

	/**
	 * 仕入先受注番設定
	 * @param siOrderNo 仕入先受注番
	 */
	public void setSiOrderNo(final String siOrderNo) {
		this.siOrderNo = siOrderNo;
	}

	/**
	 * 仕入先コード取得
	 * @return String 仕入先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 仕入先コード設定
	 * @param venderCd 仕入先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 購買ステータス取得
	 * @return BigDecimal 購買ステータス
	 */
	public BigDecimal getStatus() {
		return this.status;
	}

	/**
	 * 購買ステータス設定
	 * @param status 購買ステータス
	 */
	public void setStatus(final BigDecimal status) {
		this.status = status;
	}

	/**
	 * 分納区分取得
	 * @return BigDecimal 分納区分
	 */
	public BigDecimal getReplyContentsDivision() {
		return this.replyContentsDivision;
	}

	/**
	 * 分納区分設定
	 * @param replyContentsDivision 分納区分
	 */
	public void setReplyContentsDivision(final BigDecimal replyContentsDivision) {
		this.replyContentsDivision = replyContentsDivision;
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
	 * orderDevideNoを取得します。
	 * @return orderDevideNo
	 */
	public String getOrderDevideNo() {
		return orderDevideNo;
	}

	/**
	 * orderDevideNoを設定します。
	 * @param orderDevideNo orderDevideNo
	 */
	public void setOrderDevideNo(final String orderDevideNo) {
		this.orderDevideNo = orderDevideNo;
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

}
