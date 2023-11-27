/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail;

/**
 * PurchaseAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public interface PurchaseAttributeQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.purchaseattributequeuedetail.
		PurchaseAttributeQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version";

	/**
	 * PurchaseAttributeQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return PurchaseAttributeQueueDetail
	 */
	PurchaseAttributeQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
