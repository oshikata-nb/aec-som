/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 外注原材料投入実績一覧画面_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class MaterialRinputListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション buySubcontractOrderNo */
	public static final String buySubcontractOrderNo_COLUMN = "BUY_SUBCONTRACT_ORDER_NO";

	/** COLUMNアノテーション orderDate */
	public static final String orderDate_COLUMN = "ORDER_DATE";

	/** COLUMNアノテーション orderQuantity */
	public static final String orderQuantity_COLUMN = "ORDER_QUANTITY";

	/** COLUMNアノテーション orderConvertQuantity */
	public static final String orderConvertQuantity_COLUMN = "ORDER_CONVERT_QUANTITY";

	/** COLUMNアノテーション suggestedDeliverlimitDate */
	public static final String suggestedDeliverlimitDate_COLUMN = "SUGGESTED_DELIVERLIMIT_DATE";

	/** COLUMNアノテーション orderSheetNo */
	public static final String orderSheetNo_COLUMN = "ORDER_SHEET_NO";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	//
	// インスタンスフィールド
	//

	/** 発注番号 */
	private String buySubcontractOrderNo;

	/** 発注日 */
	private Timestamp orderDate;

	/** 発注数量 */
	private BigDecimal orderQuantity;

	/** 重量 */
	private BigDecimal orderConvertQuantity;

	/** 納品希望日 */
	private Timestamp suggestedDeliverlimitDate;

	/** 発注書NO */
	private String orderSheetNo;

	/** 仕入先コード */
	private String venderCd;

	//
	// インスタンスメソッド
	//

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
	public void setSuggestedDeliverlimitDate(final Timestamp suggestedDeliverlimitDate) {
		this.suggestedDeliverlimitDate = suggestedDeliverlimitDate;
	}

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
