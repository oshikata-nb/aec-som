/*
 * Created on Thu Jan 22 15:18:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inventorycount;

/**
 * InventoryCountDaoインターフェース.
 * @author t0011036
 */
public interface InventoryCountDao {

	/** BEANアノテーション. */
	Class BEAN = InventoryCount.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean InventoryCount
	 * @return Insert件数
	 */
	int insert(InventoryCount bean);

	/**
	 * Update.
	 * @param bean InventoryCount
	 * @return Update件数
	 */
	int update(InventoryCount bean);

	/**
	 * Delete.
	 * @param bean InventoryCount
	 * @return Delete件数
	 */
	int delete(InventoryCount bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COUNT_DATE,COUNT_DIVISION,COUNT_LOCATION,ITEM_CD,LOT_NO";

	/**
	 * エンティティ取得.
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @param countLocation countLocation
	 * @param itemCd itemCd
	 * @param lotNo lotNo
	 * @return InventoryCount
	 */
	InventoryCount getEntity(final java.sql.Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, String lotNo);
}
