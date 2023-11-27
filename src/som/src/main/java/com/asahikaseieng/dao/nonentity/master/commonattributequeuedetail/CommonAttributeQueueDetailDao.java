/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail;

/**
 * CommonAttributeQueueDetailDaoクラス
 * @author t0011036
 */
public interface CommonAttributeQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.commonattributequeuedetail.CommonAttributeQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version";

	/**
	 * CommonAttributeQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return CommonAttributeQueueDetail
	 */
	CommonAttributeQueueDetail getEntity(final String itemCd,
			final java.math.BigDecimal version);
}
