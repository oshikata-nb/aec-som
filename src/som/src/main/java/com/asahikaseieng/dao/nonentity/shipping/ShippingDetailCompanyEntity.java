/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 詳細画面(花王・その他)_表示用データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingDetailCompanyEntity extends ShippingDetailEntity implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション deliveryAddress */
	public static final String deliveryAddress_COLUMN = "DELIVERY_ADDRESS";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション deliveryExpectedDate */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";

	/** COLUMNアノテーション updateDateOrderHead */
	public static final String updateDateOrderHead_COLUMN = "UPDATE_DATE_ORDER_HEAD";

	/** COLUMNアノテーション orderQty */
	public static final String orderQty_COLUMN = "ORDER_QTY";

	/** COLUMNアノテーション matss */
	public static final String matss_COLUMN = "MATSS";

	/** COLUMNアノテーション updateDateOrderDetail */
	public static final String updateDateOrderDetail_COLUMN = "UPDATE_DATE_ORDER_DETAIL";

	/** 納入先宛先 */
	private String deliveryAddress;

	/** 帳合コード */
	private String balanceCd;

	/** 受注ヘッダ更新日時 */
	private Timestamp deliveryExpectedDate;

	/** 受注ヘッダ更新日時 */
	private Timestamp updateDateOrderHead;

	/** 受注数量 */
	private BigDecimal orderQty;

	/** 増付数 */
	private BigDecimal matss;

	/** 受注詳細更新日時 */
	private Timestamp updateDateOrderDetail;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailCompanyEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		super.init();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */
	/**
	 * 納入先宛先取得
	 * @return String 納入先宛先
	 */
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * 納入先宛先設定
	 * @param deliveryAddress 納入先宛先
	 */
	public void setDeliveryAddress(final String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 * 帳合コード取得
	 * @return String 帳合コード
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * 受注ヘッダ更新日時取得
	 * @return Timestamp 受注ヘッダ更新日時
	 */
	public Timestamp getUpdateDateOrderHead() {
		return this.updateDateOrderHead;
	}

	/**
	 * 受注ヘッダ更新日時設定
	 * @param updateDateOrderHead 受注ヘッダ更新日時
	 */
	public void setUpdateDateOrderHead(final Timestamp updateDateOrderHead) {
		this.updateDateOrderHead = updateDateOrderHead;
	}

	/**
	 * 受注数量取得
	 * @return BigDecimal 受注数量
	 */
	public BigDecimal getOrderQty() {
		return this.orderQty;
	}

	/**
	 * 受注数量設定
	 * @param orderQty 受注数量
	 */
	public void setOrderQty(final BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * 増付数取得
	 * @return BigDecimal 増付数
	 */
	public BigDecimal getMatss() {
		return this.matss;
	}

	/**
	 * 増付数設定
	 * @param matss 増付数
	 */
	public void setMatss(final BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * 受注詳細更新日時取得
	 * @return Timestamp 受注詳細更新日時
	 */
	public Timestamp getUpdateDateOrderDetail() {
		return this.updateDateOrderDetail;
	}

	/**
	 * 受注詳細更新日時設定
	 * @param updateDateOrderDetail 受注詳細更新日時
	 */
	public void setUpdateDateOrderDetail(final Timestamp updateDateOrderDetail) {
		this.updateDateOrderDetail = updateDateOrderDetail;
	}

	/**
	 * deliveryExpectedDate
	 * @return Timestamp deliveryExpectedDate
	 */
	public Timestamp getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDate
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(final Timestamp deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

}
