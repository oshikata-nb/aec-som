/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedetail;

/**
 * ComponentInformationQueueDetailDaoクラス
 * @author t0011036
 */
public interface ComponentInformationQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentinformationqueuedetail.
					ComponentInformationQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version, indicateOrder";

	/**
	 * ComponentInformationQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @param indicateOrder indicateOrder
	 * @return ComponentInformationQueueDetail
	 */
	ComponentInformationQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version,
			final java.math.BigDecimal indicateOrder);
}
