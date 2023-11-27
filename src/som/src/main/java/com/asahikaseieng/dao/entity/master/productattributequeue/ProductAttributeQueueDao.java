/*
 * Created on Fri Feb 20 17:57:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.productattributequeue;

/**
 * ProductAttributeQueueDaoインターフェース.
 * @author kanri-user
 */
public interface ProductAttributeQueueDao {

	/** BEANアノテーション. */
	Class BEAN = ProductAttributeQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ProductAttributeQueue
	 * @return Insert件数
	 */
	int insert(ProductAttributeQueue bean);

	/**
	 * Update.
	 * @param bean ProductAttributeQueue
	 * @return Update件数
	 */
	int update(ProductAttributeQueue bean);

	/**
	 * Delete.
	 * @param bean ProductAttributeQueue
	 * @return Delete件数
	 */
	int delete(ProductAttributeQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return ProductAttributeQueue
	 */
	ProductAttributeQueue getEntity(String itemCd, java.math.BigDecimal version);
}
