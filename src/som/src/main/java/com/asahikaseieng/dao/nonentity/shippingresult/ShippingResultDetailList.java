/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.math.BigDecimal;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷実績詳細画面_出荷実績詳細表示用データ格納クラス.
 *
 * @author tosco
 */
public class ShippingResultDetailList extends ShippingResultDetailListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** チェックフラグ */
	private boolean checkFlg;

	/** ロケーション名称 */
	private String locationName;

	/** 出荷指図数量(文字列) */
	private String strShippingInstruction;

	/** 出荷実績数(文字列) */
	private String strShippingResultQuantity;

	/** 出荷完了日(文字列) */
	private String strShippingResultDate;

	/** 在庫量 */
	private BigDecimal inventoryQty;

	/** 在庫量(文字列) */
	private String strInventoryQty;

	/** 出荷実績数(文字列) */
	private String strShippingResultQuantityPrev;

	/** 完了フラグ */
	private boolean deliveryCompFlag;

	/**
	 * コンストラクタ.
	 */
	public ShippingResultDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		setStrShippingResultDate(AecDateUtils.dateFormat(getShippingResultDate(), "yyyy/MM/dd"));
		setDeliveryCompFlag(BigDecimal.ONE.equals(getDeliveryComp()));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */
	/**
	 * チェックフラグを取得します。
	 * @return boolean チェックフラグ
	 */
	public boolean isCheckFlg() {
		return checkFlg;
	}

	/**
	 * チェックフラグを設定します。
	 * @param checkFlg チェックフラグ
	 */
	public void setCheckFlg(final boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	/**
	 * ロケーション名称取得
	 * @return String ロケーション名称
	*/
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定
	 * @param locationName ロケーション名称
	*/
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 在庫量を取得します。
	 * @return String 在庫量
	 */
	public BigDecimal getInventoryQty() {
		return inventoryQty;
	}

	/**
	 * 在庫量を設定します。
	 * @param inventoryQty 在庫量
	 */
	public void setInventoryQty(final BigDecimal inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	/**
	 * 出荷指図数量文字列)を取得します。
	 * @return String 出荷指図数量(文字列)
	 */
	public String getStrShippingInstruction() {
		return strShippingInstruction;
	}

	/**
	 * 出荷指図数量(文字列)を設定します。
	 * @param strShippingInstruction 出荷指図数量(文字列)
	 */
	public void setStrShippingInstruction(final String strShippingInstruction) {
		this.strShippingInstruction = strShippingInstruction;
	}

	/**
	 * 出荷実績数(文字列)を取得します。
	 * @return String 出荷実績数(文字列)
	 */
	public String getStrShippingResultQuantity() {
		return strShippingResultQuantity;
	}

	/**
	 * 出荷実績数(文字列)を設定します。
	 * @param strShippingResultQuantity 出荷実績数(文字列)
	 */
	public void setStrShippingResultQuantity(final String strShippingResultQuantity) {
		this.strShippingResultQuantity = strShippingResultQuantity;
	}

	/**
	 * 出荷完了日(文字列)を取得します。
	 * @return String 出荷完了日(文字列)
	 */
	public String getStrShippingResultDate() {
		return strShippingResultDate;
	}

	/**
	 * 出荷完了日(文字列)を設定します。
	 * @param strShippingResultDate 出荷完了日(文字列)
	 */
	public void setStrShippingResultDate(final String strShippingResultDate) {
		this.strShippingResultDate = strShippingResultDate;
	}

	/**
	 * 在庫量(文字列)を取得します。
	 * @return String 在庫量(文字列)
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * 在庫量(文字列)を設定します。
	 * @param strInventoryQty 在庫量(文字列)
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * 出荷実績数前回値(文字列)を取得します。
	 * @return String 出荷実績数前回値(文字列)
	 */
	public String getStrShippingResultQuantityPrev() {
		return strShippingResultQuantityPrev;
	}

	/**
	 * 出荷実績数前回値(文字列)を設定します。
	 * @param strShippingResultQuantityPrev 出荷実績数前回値(文字列)
	 */
	public void setStrShippingResultQuantityPrev(final String strShippingResultQuantityPrev) {
		this.strShippingResultQuantityPrev = strShippingResultQuantityPrev;
	}

	/**
	 * 完了フラグを取得します。
	 * @return boolean 完了フラグ
	 */
	public boolean isDeliveryCompFlag() {
		return deliveryCompFlag;
	}

	/**
	 * 完了フラグを設定します。
	 * @param deliveryCompFlag 完了フラグ
	 */
	public void setDeliveryCompFlag(final boolean deliveryCompFlag) {
		this.deliveryCompFlag = deliveryCompFlag;
	}
}
