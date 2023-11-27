/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.math.BigDecimal;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面データ格納クラス.
 * @author tosco
 */
public class MaterialRinputLotInventorySearchList extends MaterialRinputLotInventorySearchListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public MaterialRinputLotInventorySearchList() {
		super();
	}

	//
	// 定数
	//
	/** COLUMNアノテーション itemName */
	public static final String itemName_COLUMN = "ITEM_NAME";

	/** COLUMNアノテーション styleOfPacking */
	public static final String styleOfPacking_COLUMN = "STYLE_OF_PACKING";

	/** COLUMNアノテーション kgOfFractionManagement */
	public static final String kgOfFractionManagement_COLUMN = "KG_OF_FRACTION_MANAGEMENT";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_OF_OPERATION_MANAGEMENT";

	/** COLUMNアノテーション locationName */
	public static final String locationName_COLUMN = "LOCATION_NAME";

	/** COLUMNアノテーション unitName */
	public static final String unitName_COLUMN = "UNIT_NAME";

	//
	// インスタンスフィールド
	//
	/** 品目名称 */
	private String itemName;

	/** 荷姿 */
	private String styleOfPacking;

	/** Kg換算係数（在庫） */
	private BigDecimal kgOfFractionManagement;

	/** 運用管理単位 */
	private String unitDiv;

	/** ロケーション名称 */
	private String locationName;

	/** 単位 */
	private String unitName;

	/** 在庫数量 */
	private String strInventoryQty;

	/** 使用量 */
	private String strResultQty;

	/** 荷姿数 */
	private String strInoutQty;

	/** 端数 */
	private String strFraction;

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
	 * 荷姿を取得します。
	 * @return styleOfPacking
	 */
	public String getStyleOfPacking() {
		return styleOfPacking;
	}

	/**
	 * 荷姿を設定します。
	 * @param styleOfPacking 荷姿
	 */
	public void setStyleOfPacking(final String styleOfPacking) {
		this.styleOfPacking = styleOfPacking;
	}

	/**
	 * Kg換算係数（在庫）を取得します。
	 * @return kgOfFractionManagement
	 */
	public BigDecimal getKgOfFractionManagement() {
		return kgOfFractionManagement;
	}

	/**
	 * Kg換算係数（在庫）を設定します。
	 * @param kgOfFractionManagement Kg換算係数（在庫）
	 */
	public void setKgOfFractionManagement(final BigDecimal kgOfFractionManagement) {
		this.kgOfFractionManagement = kgOfFractionManagement;
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
	 * 単位を取得します。
	 * @return unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * 単位を設定します。
	 * @param unitName 単位
	 */
	public void setUnitName(final String unitName) {
		this.unitName = unitName;
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

	/**
	 * 使用量を取得します。
	 * @return strResultQty
	 */
	public String getStrResultQty() {
		return strResultQty;
	}

	/**
	 * 使用量を設定します。
	 * @param strResultQty 使用量
	 */
	public void setStrResultQty(final String strResultQty) {
		this.strResultQty = strResultQty;
	}

	/**
	 * 荷姿数取得.
	 * @return String 荷姿数
	 */
	public String getStrInoutQty() {
		return strInoutQty;
	}

	/**
	 * 荷姿数設定.
	 * @param strInoutQty 荷姿数
	 */
	public void setStrInoutQty(final String strInoutQty) {
		this.strInoutQty = strInoutQty;
	}

	/**
	 * 端数取得.
	 * @return String 端数
	 */
	public String getStrFraction() {
		return strFraction;
	}

	/**
	 * 端数設定.
	 * @param strFraction 端数
	 */
	public void setStrFraction(final String strFraction) {
		this.strFraction = strFraction;
	}

}

