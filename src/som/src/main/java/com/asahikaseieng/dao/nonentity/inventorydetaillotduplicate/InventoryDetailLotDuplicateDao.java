/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate;

/**
 * InventoryDetailLotDuplicateDaoクラス
 * @author t0011036
 */
public interface InventoryDetailLotDuplicateDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate.InventoryDetailLotDuplicate.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "lotNo";

	/**
	 * InventoryDetailLotDuplicateメソッド
	 * 
	 * @param lotNo lotNo
	 * @return InventoryDetailLotDuplicate
	 */
	InventoryDetailLotDuplicate getEntity(final String lotNo);
}
