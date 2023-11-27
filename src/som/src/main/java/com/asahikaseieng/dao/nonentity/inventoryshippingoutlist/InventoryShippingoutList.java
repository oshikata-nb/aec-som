/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlist;

/**
 * InventoryShippingoutListクラス.
 * @author t0011036
 */
public class InventoryShippingoutList extends InventoryShippingoutListBase {

	private static final long serialVersionUID = 1L;

	private String strPackQty;

	private String strFraction;

	private String strInventoryQty;

	/**
	 * コンストラクタ.
	 */
	public InventoryShippingoutList() {
		super();
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
