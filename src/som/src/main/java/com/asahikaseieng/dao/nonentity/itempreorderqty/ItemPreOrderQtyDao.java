/*
 * Created on Thu Jan 22 18:23:14 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.itempreorderqty;


/**
 * ItemInventoryDaoインターフェース.
 * @author t0011036
 */
public interface ItemPreOrderQtyDao {

	/** BEANアノテーション. */
	Class BEAN = ItemPreOrderQty.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getPointingOrderQｔｙ */
	String getPreOrderQty_ARGS = "itemCd";

	/**
	 * InventoryStockTotalQtyメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryStockTotalQty
	 */
	ItemPreOrderQtyBase getPreOrderQty(final String itemCd);

}
