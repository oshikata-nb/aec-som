/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocktotalqty;

/**
 * InventoryStockTotalQtyクラス.
 * @author t0011036
 */
public class InventoryStockTotalQty extends InventoryStockTotalQtyBase {

	private static final long serialVersionUID = 1L;

	private String strTotalQty;

	/**
	 * コンストラクタ.
	 */
	public InventoryStockTotalQty() {
		super();
	}

	/**
	 * strTotalQtyを取得します。
	 * @return strTotalQty
	 */
	public String getStrTotalQty() {
		return strTotalQty;
	}

	/**
	 * strTotalQtyを設定します。
	 * @param strTotalQty strTotalQty
	 */
	public void setStrTotalQty(final String strTotalQty) {
		this.strTotalQty = strTotalQty;
	}
}
