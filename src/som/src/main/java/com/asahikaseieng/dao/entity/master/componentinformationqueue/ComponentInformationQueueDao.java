/*
 * Created on Tue Jan 20 09:29:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.componentinformationqueue;

/**
 * ComponentInformationQueueDaoインターフェース.
 * @author t0011036
 */
public interface ComponentInformationQueueDao {

	/** BEANアノテーション. */
	Class BEAN = ComponentInformationQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ComponentInformationQueue
	 * @return Insert件数
	 */
	int insert(ComponentInformationQueue bean);

	/**
	 * Update.
	 * @param bean ComponentInformationQueue
	 * @return Update件数
	 */
	int update(ComponentInformationQueue bean);

	/**
	 * Delete.
	 * @param bean ComponentInformationQueue
	 * @return Delete件数
	 */
	int delete(ComponentInformationQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION,INDICATE_ORDER";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @param indicateOrder indicateOrder
	 * @return ComponentInformationQueue
	 */
	ComponentInformationQueue getEntity(final String itemCd,
			final java.math.BigDecimal version,
			final java.math.BigDecimal indicateOrder);
}
