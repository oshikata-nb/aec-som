/*
 * Created on 2009/04/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail;

/**
 * InventoryShippingoutDetailDaoクラス
 * @author t0011036
 */
public interface InventoryShippingoutDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail.InventoryShippingoutDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "locationCd, itemCd, lotNo";

	/**
	 * InventoryShippingoutDetailメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryShippingoutDetail
	 */
	InventoryShippingoutDetail getEntity(final String locationCd,
			final String itemCd, final String lotNo);
}
