/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売上詳細(預り品)画面_売上受払履歴格納クラス.
 *
 * @author
 */
public class SalesDetailKeepSalesInoutRecordList extends SalesDetailKeepSalesInoutRecordListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション inspectionQty */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** チェックフラグ */
	private boolean checkFlg;

	/** ロケーション名称 */
	private String locationName;

	/** 入庫先ロケーションコード */
	private String inLocationCd;

	/** 在庫量 */
	private BigDecimal inventoryQty;

	/** 検査待 */
	private BigDecimal inspectionQty;

	/** 在庫量(文字列) */
	private String strInventoryQty;

	/** 検査待(文字列) */
	private String strInspectionQty;

	/** 数量(文字列) */
	private String strQty;

	/** 前回数量 */
	private BigDecimal prevQty;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailKeepSalesInoutRecordList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
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
	 * 入庫先ロケーションコード取得
	 * @return String 入庫先ロケーションコード
	*/
	public String getInLocationCd() {
		return this.inLocationCd;
	}

	/**
	 * 入庫先ロケーションコード設定
	 * @param inLocationCd 入庫先ロケーションコード
	*/
	public void setInLocationCd(final String inLocationCd) {
		this.inLocationCd = inLocationCd;
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
	 * 検査待を取得します。
	 * @return String 検査待
	 */
	public BigDecimal getInspectionQty() {
		return inspectionQty;
	}

	/**
	 * 検査待を設定します。
	 * @param inspectionQty 検査待
	 */
	public void setInspectionQty(final BigDecimal inspectionQty) {
		this.inspectionQty = inspectionQty;
	}

	/**
	 * 検査待(文字列)を取得します。
	 * @return String 検査待(文字列)
	 */
	public String getStrInspectionQty() {
		return strInspectionQty;
	}

	/**
	 * 検査待(文字列)を設定します。
	 * @param strInspectionQty 検査待(文字列)
	 */
	public void setStrInspectionQty(final String strInspectionQty) {
		this.strInspectionQty = strInspectionQty;
	}

	/**
	 * 数量(文字列)を取得します。
	 * @return String 数量(文字列)
	 */
	public String getStrQty() {
		return strQty;
	}

	/**
	 * 数量(文字列)を設定します。
	 * @param strQty 数量(文字列)
	 */
	public void setStrQty(final String strQty) {
		this.strQty = strQty;
	}

	/**
	 * 前回数量を取得します。
	 * @return BigDecimal 前回数量
	 */
	public BigDecimal getPrevQty() {
		return prevQty;
	}

	/**
	 * 前回数量を設定します。
	 * @param prevQty 前回数量
	 */
	public void setPrevQty(final BigDecimal prevQty) {
		this.prevQty = prevQty;
	}
}
