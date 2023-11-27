/*
 * Created on 2009/07/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.ordernogoodqty;

/**
 * OrderNoGoodQtyDaoクラス
 * @author kanri-user
 */
public interface OrderNoGoodQtyDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.ordernogoodqty.OrderNoGoodQty.class;

	/** ARGSアノテーション getNoGoodQty */
	String getNoGoodQty_ARGS = "itemCd";

	/**
	 * OrderNoGoodQtyメソッド
	 * 
	 * @param itemCd itemCd
	 * @return OrderNoGoodQty
	 */
	OrderNoGoodQty getNoGoodQty(final String itemCd);
}
