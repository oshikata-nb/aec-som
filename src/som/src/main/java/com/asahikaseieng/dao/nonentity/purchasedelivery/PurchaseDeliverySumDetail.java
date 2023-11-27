/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 納期回答まとめ入力画面_ヘッダ部表示用データ格納クラス.
 *
 * @author tosco
 */
public class PurchaseDeliverySumDetail extends PurchaseDeliverySumDetailBase implements
		PropertyTransferCallbacker {


	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション orderCount */
	public static final String orderCount_COLUMN = "ORDER_COUNT";

	/** COLUMNアノテーション issuedCount */
	public static final String issuedCount_COLUMN = "ISSUED_COUNT";

	/** COLUMNアノテーション adjustCount */
	public static final String adjustCount_COLUMN = "ADJUST_COUNT";

	/** COLUMNアノテーション fixedCount */
	public static final String fixedCount_COLUMN = "FIXED_COUNT";

	/** COLUMNアノテーション arrivedAcceptedCount */
	public static final String arrivedAcceptedCount_COLUMN = "ARRIVED_ACCEPTED_COUNT";

	/** COLUMNアノテーション count */
	public static final String count_COLUMN = "COUNT";

	/** 全オーダー件数 */
	private BigDecimal orderCount;

	/** 発注書発行済件数 */
	private BigDecimal issuedCount;

	/** 納期調整中件数 */
	private BigDecimal adjustCount;

	/** 納期確定件数 */
	private BigDecimal fixedCount;

	/** 入荷・受入済件数 */
	private BigDecimal arrivedAcceptedCount;

	/** 発注書発行済件数～納期確定件数の合計 */
	private BigDecimal count;

	/** 発注日(スラッシュ編集) */
	private String strOrderDate;

	/** 全オーダー件数(文字列) */
	private String strOrderCount;

	/** 発注書発行済件数(文字列) */
	private String strIssuedCount;

	/** 納期調整中件数(文字列) */
	private String strAdjustCount;

	/** 納期確定件数(文字列) */
	private String strFixedCount;

	/** 入荷・受入済件数(文字列) */
	private String strArrivedAcceptedCount;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliverySumDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrOrderDate(AecDateUtils.dateFormat(getOrderDate(), "yyyy/MM/dd"));	// 発注日(スラッシュ編集)
		setStrOrderCount(convertDecimalToStr(getOrderCount()));		// 全オーダー件数
		setStrIssuedCount(convertDecimalToStr(getIssuedCount()));	// 発注書発行済件数
		setStrAdjustCount(convertDecimalToStr(getAdjustCount()));	// 納期調整中件数
		setStrFixedCount(convertDecimalToStr(getFixedCount()));		// 納期確定件数
		setStrArrivedAcceptedCount(convertDecimalToStr(getArrivedAcceptedCount()));	// 入荷・受入済件数
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 全オーダー件数取得
	 * @return BigDecimal 全オーダー件数
	 */
	public BigDecimal getOrderCount() {
		return orderCount;
	}

	/**
	 * 全オーダー件数設定
	 * @param orderCount 全オーダー件数
	 */
	public void setOrderCount(final BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	/**
	 * 発注書発行済件数取得
	 * @return BigDecimal 発注書発行済件数
	 */
	public BigDecimal getIssuedCount() {
		return issuedCount;
	}

	/**
	 * 発注書発行済件数設定
	 * @param issuedCount 発注書発行済件数
	 */
	public void setIssuedCount(final BigDecimal issuedCount) {
		this.issuedCount = issuedCount;
	}

	/**
	 * 納期調整中件数取得
	 * @return BigDecimal 納期調整中件数
	 */
	public BigDecimal getAdjustCount() {
		return adjustCount;
	}

	/**
	 * 納期調整中件数設定
	 * @param adjustCount 納期調整中件数
	 */
	public void setAdjustCount(final BigDecimal adjustCount) {
		this.adjustCount = adjustCount;
	}

	/**
	 * 納期確定件数取得
	 * @return BigDecimal 納期確定件数
	 */
	public BigDecimal getFixedCount() {
		return fixedCount;
	}

	/**
	 * 納期確定件数設定
	 * @param fixedCount 納期確定件数
	 */
	public void setFixedCount(final BigDecimal fixedCount) {
		this.fixedCount = fixedCount;
	}

	/**
	 * 入荷・受入済件数取得
	 * @return BigDecimal 入荷・受入済件数
	 */
	public BigDecimal getArrivedAcceptedCount() {
		return arrivedAcceptedCount;
	}

	/**
	 * 入荷・受入済件数設定
	 * @param arrivedAcceptedCount 入荷・受入済件数
	 */
	public void setArrivedAcceptedCount(final BigDecimal arrivedAcceptedCount) {
		this.arrivedAcceptedCount = arrivedAcceptedCount;
	}

	/**
	 * 発注書発行済件数～納期確定件数の合計取得
	 * @return BigDecimal 発注書発行済件数～納期確定件数の合計
	 */
	public BigDecimal getCount() {
		return count;
	}

	/**
	 * 発注書発行済件数～納期確定件数の合計設定
	 * @param count 発注書発行済件数～納期確定件数の合計
	 */
	public void setCount(final BigDecimal count) {
		this.count = count;
	}

	/**
	 * 発注日(スラッシュ編集)取得
	 * @return String 発注日(スラッシュ編集)
	 */
	public String getStrOrderDate() {
		return strOrderDate;
	}

	/**
	 * 発注日(スラッシュ編集)設定
	 * @param strOrderDate 発注日(スラッシュ編集)
	 */
	public void setStrOrderDate(final String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	/**
	 * 全オーダー件数(文字列)取得
	 * @return String 全オーダー件数(文字列)
	 */
	public String getStrOrderCount() {
		return strOrderCount;
	}

	/**
	 * 全オーダー件数(文字列)設定
	 * @param strOrderCount 全オーダー件数(文字列)
	 */
	public void setStrOrderCount(final String strOrderCount) {
		this.strOrderCount = strOrderCount;
	}

	/**
	 * 発注書発行済件数(文字列)取得
	 * @return String 発注書発行済件数(文字列)
	 */
	public String getStrIssuedCount() {
		return strIssuedCount;
	}

	/**
	 * 発注書発行済件数(文字列)設定
	 * @param strIssuedCount 発注書発行済件数(文字列)
	 */
	public void setStrIssuedCount(final String strIssuedCount) {
		this.strIssuedCount = strIssuedCount;
	}

	/**
	 * 納期調整中件数(文字列)取得
	 * @return String 納期調整中件数(文字列)
	 */
	public String getStrAdjustCount() {
		return strAdjustCount;
	}

	/**
	 * 納期調整中件数(文字列)設定
	 * @param strAdjustCount 納期調整中件数(文字列)
	 */
	public void setStrAdjustCount(final String strAdjustCount) {
		this.strAdjustCount = strAdjustCount;
	}

	/**
	 * 納期確定件数(文字列)取得
	 * @return BigDecimal 納期確定件数(文字列)
	 */
	public String getStrFixedCount() {
		return strFixedCount;
	}

	/**
	 * 納期確定件数(文字列)設定
	 * @param strFixedCount 納期確定件数(文字列)
	 */
	public void setStrFixedCount(final String strFixedCount) {
		this.strFixedCount = strFixedCount;
	}

	/**
	 * 入荷・受入済件数(文字列)取得
	 * @return String 入荷・受入済件数(文字列)
	 */
	public String getStrArrivedAcceptedCount() {
		return strArrivedAcceptedCount;
	}

	/**
	 * 入荷・受入済件数(文字列)設定
	 * @param strArrivedAcceptedCount 入荷・受入済件数(文字列)
	 */
	public void setStrArrivedAcceptedCount(final String strArrivedAcceptedCount) {
		this.strArrivedAcceptedCount = strArrivedAcceptedCount;
	}

	private String convertDecimalToStr(final BigDecimal decimal) {
		String ret = null;
		if (decimal != null) {
			ret = decimal.toString();
		}
		return ret;
	}

}
