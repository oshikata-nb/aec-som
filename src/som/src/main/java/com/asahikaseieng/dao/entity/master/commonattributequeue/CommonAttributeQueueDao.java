/*
 * Created on Mon Jan 19 17:33:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.commonattributequeue;

/**
 * CommonAttributeQueueDaoインターフェース.
 * @author t0011036
 */
public interface CommonAttributeQueueDao {

	/** BEANアノテーション. */
	Class BEAN = CommonAttributeQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean CommonAttributeQueue
	 * @return Insert件数
	 */
	int insert(CommonAttributeQueue bean);

	/**
	 * Update.
	 * @param bean CommonAttributeQueue
	 * @return Update件数
	 */
	int update(CommonAttributeQueue bean);

	/**
	 * Delete.
	 * @param bean CommonAttributeQueue
	 * @return Delete件数
	 */
	int delete(CommonAttributeQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return CommonAttributeQueue
	 */
	CommonAttributeQueue getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
