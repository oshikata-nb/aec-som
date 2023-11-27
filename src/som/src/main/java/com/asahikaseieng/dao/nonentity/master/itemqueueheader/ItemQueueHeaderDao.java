/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueueheader;

import java.math.BigDecimal;

/**
 * ItemQueueHeaderDaoクラス
 * @author t0011036
 */
public interface ItemQueueHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueueheader.ItemQueueHeader.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd,version";

	/**
	 * ItemQueueHeaderメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemQueueHeader
	 */
	ItemQueueHeader getEntity(final String itemCd, final BigDecimal version);
}
