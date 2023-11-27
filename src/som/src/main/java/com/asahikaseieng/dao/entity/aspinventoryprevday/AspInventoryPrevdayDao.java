/*
 * Created on Mon Jan 19 09:01:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspinventoryprevday;

/**
 * AspInventoryPrevdayDaoインターフェース.
 * @author t0011036
 */
public interface AspInventoryPrevdayDao {

	/** BEANアノテーション. */
	Class BEAN = AspInventoryPrevday.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean AspInventoryPrevday
	 * @return Insert件数
	 */
	int insert(AspInventoryPrevday bean);

	/**
	 * Update.
	 * @param bean AspInventoryPrevday
	 * @return Update件数
	 */
	int update(AspInventoryPrevday bean);

	/**
	 * Delete.
	 * @param bean AspInventoryPrevday
	 * @return Delete件数
	 */
	int delete(AspInventoryPrevday bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,ORDER_CD";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param orderCd orderCd
	 * @return AspInventoryPrevday
	 */
	AspInventoryPrevday getEntity(final String itemCd, final String orderCd);
}
