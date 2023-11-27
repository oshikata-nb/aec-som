/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

/**
 * 包装指図－在庫確認ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class PkgDirectionLotInventorySearchList extends PkgDirectionLotInventorySearchListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PkgDirectionLotInventorySearchList() {
		super();
	}

	//
	// 定数
	//
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	//
	// インスタンスフィールド
	//
	/** 品目名称 */
	private String itemName;

	/** 運用管理単位 */
	private String unitDiv;

	/** ロケーション名称 */
	private String locationName;

	/** 在庫数量 */
	private String strInventoryQty;

	//
	// インスタンスメソッド
	//
	/**
	 * 品目名称取得.
	 * @return String 品目名称
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * 品目名称設定.
	 * @param itemName 品目名称
	 */
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 運用管理単位取得.
	 * @return String 運用管理単位
	 */
	public String getUnitDiv() {
		return this.unitDiv;
	}

	/**
	 * 運用管理単位設定.
	 * @param unitDiv 運用管理単位
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
	}

	/**
	 * ロケーション名称取得.
	 * @return String ロケーション名称
	 */
	public String getLocationName() {
		return this.locationName;
	}

	/**
	 * ロケーション名称設定.
	 * @param locationName ロケーション名称
	 */
	public void setLocationName(final String locationName) {
		this.locationName = locationName;
	}

	/**
	 * 在庫数量取得.
	 * @return String 在庫数量
	 */
	public String getStrInventoryQty() {
		return this.strInventoryQty;
	}

	/**
	 * 在庫数量設定.
	 * @param strInventoryQty 在庫数量
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

}

