/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedetail;

import java.math.BigDecimal;

/**
 * ItemQueueDetailDaoクラス
 * @author t0011036
 */
public interface ItemQueueDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuedetail.ItemQueueDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd,version";

	/**
	 * ItemQueueDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemQueueDetail
	 */
	ItemQueueDetail getEntity(final String itemCd, final BigDecimal version);
}
