/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovedetail;

/**
 * InventoryMoveDetailDaoクラス
 * @author t0011036
 */
public interface InventoryMoveDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorymovedetail.InventoryMoveDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "locationCd, itemCd, lotNo";

	/**
	 * InventoryMoveDetailメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryMoveDetail
	 */
	InventoryMoveDetail getEntity(final String locationCd, final String itemCd,
			final String lotNo);
}
