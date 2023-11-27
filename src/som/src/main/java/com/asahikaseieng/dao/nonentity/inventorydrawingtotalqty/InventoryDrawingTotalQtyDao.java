/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty;

/**
 * InventoryDrawingTotalQtyDaoクラス
 * @author t0011036
 */
public interface InventoryDrawingTotalQtyDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty.InventoryDrawingTotalQty.class;

	/** ARGSアノテーション getTotalQty */
	String getTotalQty_ARGS = "itemCd, otherCompanyCd1";

	/**
	 * InventoryDrawingTotalQtyメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryDrawingTotalQty
	 */
	InventoryDrawingTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1);
}
