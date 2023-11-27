/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * InventoryListクラス.
 * @author t0011036
 */
public class InventoryList extends InventoryListBase {

	private static final long serialVersionUID = 1L;

	private String strPackQty;

	private String strFraction;

	private String strInventoryQty;

	private String shortLocationName;

	private String shortItemName;

	/**
	 * コンストラクタ.
	 */
	public InventoryList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用locationName取得.
	 * @return locationName
	 */
	public String getDispLocationName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getLocationName(),
			getShortLocationName());
	}

	/**
	 * 表示用itemName取得.
	 * @return itemName
	 */
	public String getDispItemName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getItemName(), getShortItemName());
	}

	/**
	 * strFractionを取得します。
	 * @return strFraction
	 */
	public String getStrFraction() {
		return strFraction;
	}

	/**
	 * strFractionを設定します。
	 * @param strFraction strFraction
	 */
	public void setStrFraction(final String strFraction) {
		this.strFraction = strFraction;
	}

	/**
	 * strInventoryQtyを取得します。
	 * @return strInventoryQty
	 */
	public String getStrInventoryQty() {
		return strInventoryQty;
	}

	/**
	 * strInventoryQtyを設定します。
	 * @param strInventoryQty strInventoryQty
	 */
	public void setStrInventoryQty(final String strInventoryQty) {
		this.strInventoryQty = strInventoryQty;
	}

	/**
	 * shortItemNameを取得します。
	 * @return shortItemName
	 */
	public String getShortItemName() {
		return shortItemName;
	}

	/**
	 * shortItemNameを設定します。
	 * @param shortItemName shortItemName
	 */
	public void setShortItemName(final String shortItemName) {
		this.shortItemName = shortItemName;
	}

	/**
	 * shortLocationNameを取得します。
	 * @return shortLocationName
	 */
	public String getShortLocationName() {
		return shortLocationName;
	}

	/**
	 * shortLocationNameを設定します。
	 * @param shortLocationName shortLocationName
	 */
	public void setShortLocationName(final String shortLocationName) {
		this.shortLocationName = shortLocationName;
	}

	/**
	 * strPackQtyを取得します。
	 * @return strPackQty
	 */
	public String getStrPackQty() {
		return strPackQty;
	}

	/**
	 * strPackQtyを設定します。
	 * @param strPackQty strPackQty
	 */
	public void setStrPackQty(final String strPackQty) {
		this.strPackQty = strPackQty;
	}
}
