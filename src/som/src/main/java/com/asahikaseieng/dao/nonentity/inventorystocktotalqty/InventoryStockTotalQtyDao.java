/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocktotalqty;

/**
 * InventoryStockTotalQtyDaoクラス
 * @author t0011036
 */
public interface InventoryStockTotalQtyDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorystocktotalqty.InventoryStockTotalQty.class;

	/** ARGSアノテーション getTotalQty */
	String getTotalQty_ARGS = "itemCd, otherCompanyCd1";

	/**
	 * InventoryStockTotalQtyメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryStockTotalQty
	 */
	InventoryStockTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1);
}
