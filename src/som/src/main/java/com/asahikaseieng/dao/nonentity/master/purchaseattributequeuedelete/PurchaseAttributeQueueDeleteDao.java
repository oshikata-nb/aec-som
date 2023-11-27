/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete;

/**
 * PurchaseAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public interface PurchaseAttributeQueueDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedelete.
		PurchaseAttributeQueueDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * PurchaseAttributeQueueDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
