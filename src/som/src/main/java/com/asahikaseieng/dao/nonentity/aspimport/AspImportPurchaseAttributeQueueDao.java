/*
 * Created on Tue Feb 17 17:51:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

/**
 * AspImportPurchaseAttributeQueueDaoインターフェース.
 * @author 
 */
public interface AspImportPurchaseAttributeQueueDao {

	/** BEANアノテーション. */
	Class BEAN = AspImportPurchaseAttributeQueue.class;

	//
	// インスタンスメソッド
	//

	/**
	 * エンティティ取得.
	 * @param itemCd itemCd
	 * @return PurchaseAttributeQueue
	 */
	AspImportPurchaseAttributeQueue getEntity(String itemCd);
}
