/*
 * Created on Thu Jan 22 20:02:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lotinventory;

/**
 * LotInventoryDaoインターフェース.
 * @author t0011036
 */
public interface LotInventoryDao {

	/** BEANアノテーション. */
	Class BEAN = LotInventory.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LotInventory
	 * @return Insert件数
	 */
	int insert(LotInventory bean);

	/**
	 * Update.
	 * @param bean LotInventory
	 * @return Update件数
	 */
	int update(LotInventory bean);

	/**
	 * Delete.
	 * @param bean LotInventory
	 * @return Delete件数
	 */
	int delete(LotInventory bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,LOCATION_CD,LOT_NO";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param locationCd locationCd
	 * @param lotNo lotNo
	 * @return LotInventory
	 */
	LotInventory getEntity(final String itemCd, final String locationCd,
			final String lotNo);
}
