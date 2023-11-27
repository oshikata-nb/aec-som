/*
 * Created on Tue Feb 17 17:51:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.purchaseattributequeue;

/**
 * PurchaseAttributeQueueDaoインターフェース.
 * @author kanri-user
 */
public interface PurchaseAttributeQueueDao {

	/** BEANアノテーション. */
	Class BEAN = PurchaseAttributeQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PurchaseAttributeQueue
	 * @return Insert件数
	 */
	int insert(PurchaseAttributeQueue bean);

	/**
	 * Update.
	 * @param bean PurchaseAttributeQueue
	 * @return Update件数
	 */
	int update(PurchaseAttributeQueue bean);

	/**
	 * Delete.
	 * @param bean PurchaseAttributeQueue
	 * @return Delete件数
	 */
	int delete(PurchaseAttributeQueue bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ITEM_CD,VERSION";

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @param version version
	 * @return PurchaseAttributeQueue
	 */
	PurchaseAttributeQueue getEntity(String itemCd, java.math.BigDecimal version);
}
