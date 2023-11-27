/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.math.BigDecimal;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷指図詳細画面_出荷指図詳細データ格納クラス.
 * 
 * @author tosco
 */
public class ShippingDetailList extends ShippingDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション inventoryQty */
	public static final String inventoryQty_COLUMN = "INVENTORY_QTY";

	/** COLUMNアノテーション backorderQty */
	public static final String backorderQty_COLUMN = "BACKORDER_QTY";

	/** COLUMNアノテーション inspectionQty */
	public static final String inspectionQty_COLUMN = "INSPECTION_QTY";

	/** COLUMNアノテーション assignQty */
	public static final String assignQty_COLUMN = "ASSIGN_QTY";

	/** COLUMNアノテーション upperLocation */
	public static final String upperLocation_COLUMN = "UPPER_LOCATION";

	/** チェックフラグ */
	private boolean checkFlg;

	/** 出荷指図数量(文字列) */
	private String strShippingInstruction;

	/** ロケーション名称 */
	private String locationName;

	/** 在庫量 */
	private BigDecimal inventoryQty;

	/** 発注残 */
	private BigDecimal backorderQty;

	/** 検査待 */
	private BigDecimal inspectionQty;

	/** 引当残 */
	private BigDecimal assignQty;

	/** 有効在庫 */
	private BigDecimal validInventory;

	/** 在庫量(文字列) */
	private String strInventoryQty;

	/** 発注残(文字列) */
	private String strBackorderQty;

	/** 検査待(文字列) */
	private String strInspectionQty;

	/** 引当残(文字列) */
	private String strAssignQty;

	/** 有効在庫(文字列) */
	private String strValidInventory;

	/** 出荷指図量前回値(文字列) */
	private String strShippingInstructionPrev;

	private String upperLocation;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		// 有効在庫算出
		setValidInventory(calcValidInventory(getInventoryQty(),
			getBackorderQty(), getInspectionQty(), getAssignQty()));

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
	 * 発注残を取得します。
	 * @return String 発注残
	 */
	public BigDecimal getBackorderQty() {
		return backorderQty;
	}

	/**
	 * 発注残を設定します。
	 * @param backorderQty 発注残
	 */
	public void setBackorderQty(final BigDecimal backorderQty) {
		this.backorderQty = backorderQty;
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
	 * 引当残を取得します。
	 * @return String 引当残
	 */
	public BigDecimal getAssignQty() {
		return assignQty;
	}

	/**
	 * 引当残を設定します。
	 * @param assignQty 引当残
	 */
	public void setAssignQty(final BigDecimal assignQty) {
		this.assignQty = assignQty;
	}

	/**
	 * 有効在庫を取得します。
	 * @return String 有効在庫
	 */
	public BigDecimal getValidInventory() {
		return validInventory;
	}

	/**
	 * 有効在庫を設定します。
	 * @param validInventory 有効在庫
	 */
	public void setValidInventory(final BigDecimal validInventory) {
		this.validInventory = validInventory;
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
	 * 発注残(文字列)を取得します。
	 * @return String 発注残(文字列)
	 */
	public String getStrBackorderQty() {
		return strBackorderQty;
	}

	/**
	 * 発注残(文字列)を設定します。
	 * @param strBackorderQty 発注残(文字列)
	 */
	public void setStrBackorderQty(final String strBackorderQty) {
		this.strBackorderQty = strBackorderQty;
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
	 * 引当残(文字列)を取得します。
	 * @return String 引当残(文字列)
	 */
	public String getStrAssignQty() {
		return strAssignQty;
	}

	/**
	 * 引当残(文字列)を設定します。
	 * @param strAssignQty 引当残(文字列)
	 */
	public void setStrAssignQty(final String strAssignQty) {
		this.strAssignQty = strAssignQty;
	}

	/**
	 * 有効在庫(文字列)を取得します。
	 * @return String 有効在庫(文字列)
	 */
	public String getStrValidInventory() {
		return strValidInventory;
	}

	/**
	 * 有効在庫(文字列)を設定します。
	 * @param strValidInventory 有効在庫(文字列)
	 */
	public void setStrValidInventory(final String strValidInventory) {
		this.strValidInventory = strValidInventory;
	}

	/**
	 * 出荷指図量前回値(文字列)を取得します。
	 * @return String 出荷指図量前回値(文字列)
	 */
	public String getStrShippingInstructionPrev() {
		return strShippingInstructionPrev;
	}

	/**
	 * 出荷指図量前回値(文字列)を設定します。
	 * @param strShippingInstructionPrev 出荷指図量前回値(文字列)
	 */
	public void setStrShippingInstructionPrev(
			final String strShippingInstructionPrev) {
		this.strShippingInstructionPrev = strShippingInstructionPrev;
	}

	/**
	 * 有効在庫を計算する。
	 * @param inventoryQty 在庫量
	 * @param backorderQty 発注残
	 * @param inspectionQty 検査待
	 * @param assignQty 引当残
	 * @return BigDecimal 有効在庫
	 */
	private BigDecimal calcValidInventory(final BigDecimal inventoryQty,
			final BigDecimal backorderQty, final BigDecimal inspectionQty,
			final BigDecimal assignQty) {
		BigDecimal validInventory = new BigDecimal(0);

		if (inventoryQty == null || backorderQty == null
				|| inspectionQty == null || assignQty == null) {
			return null;
		}

		// 有効在庫 ＝ 在庫量 ＋ 発注残 ＋ 検査待 ＋ 引当残
		validInventory = inventoryQty.add(backorderQty).add(inspectionQty).add(
			assignQty);
		return validInventory;
	}

	/**
	 * 上位ロケーションを取得します。
	 * @return String 上位ロケーション
	 */
	public String getUpperLocation() {
		return upperLocation;
	}

	/**
	 * 上位ロケーションを設定します。
	 * @param upperLocation 上位ロケーション
	 */
	public void setUpperLocation(final String upperLocation) {
		this.upperLocation = upperLocation;
	}
}
