/*
 * Created on Thu Jan 15 17:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.articleattributequeue;

/**
 * ArticleAttributeQueueDaoインターフェース.
 * @author t0011036
 */
public interface ArticleAttributeQueueDao {

	/** BEANアノテーション. */
	Class BEAN = ArticleAttributeQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ArticleAttributeQueue
	 * @return Insert件数
	 */
	int insert(ArticleAttributeQueue bean);

	/**
	 * Update.
	 * @param bean ArticleAttributeQueue
	 * @return Update件数
	 */
	int update(ArticleAttributeQueue bean);

	/**
	 * Delete.
	 * @param bean ArticleAttributeQueue
	 * @return Delete件数
	 */
	int delete(ArticleAttributeQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return ArticleAttributeQueue
	 */
	ArticleAttributeQueue getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
