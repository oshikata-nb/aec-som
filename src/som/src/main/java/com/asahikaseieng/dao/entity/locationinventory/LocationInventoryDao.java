/*
 * Created on Thu Jan 22 20:01:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.locationinventory;

/**
 * LocationInventoryDaoインターフェース.
 * @author t0011036
 */
public interface LocationInventoryDao {

	/** BEANアノテーション. */
	Class BEAN = LocationInventory.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LocationInventory
	 * @return Insert件数
	 */
	int insert(LocationInventory bean);

	/**
	 * Update.
	 * @param bean LocationInventory
	 * @return Update件数
	 */
	int update(LocationInventory bean);

	/**
	 * Delete.
	 * @param bean LocationInventory
	 * @return Delete件数
	 */
	int delete(LocationInventory bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,LOCATION_CD";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param locationCd locationCd
	 * @return LocationInventory
	 */
	LocationInventory getEntity(final String itemCd, final String locationCd);
}
