/*
 * Created on Mon Feb 09 15:26:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspproduction;

/**
 * AspProductionDaoインターフェース.
 * @author kanri-user
 */
public interface AspProductionDao {

	/** BEANアノテーション. */
	Class BEAN = AspProduction.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean AspProduction
	 * @return Insert件数
	 */
	int insert(AspProduction bean);

	/**
	 * Update.
	 * @param bean AspProduction
	 * @return Update件数
	 */
	int update(AspProduction bean);

	/**
	 * Delete.
	 * @param bean AspProduction
	 * @return Delete件数
	 */
	int delete(AspProduction bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,ORDER_CD";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param orderCd orderCd
	 * @return AspProduction
	 */
	AspProduction getEntity(String itemCd, String orderCd);
}
