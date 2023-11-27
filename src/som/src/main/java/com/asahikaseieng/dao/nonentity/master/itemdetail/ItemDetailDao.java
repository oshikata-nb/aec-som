/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemdetail;

import java.math.BigDecimal;

/**
 * ItemDetailDaoクラス
 * @author t0011036
 */
public interface ItemDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd,version";

	/**
	 * ItemDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemDetail
	 */
	ItemDetail getEntity(final String itemCd, final BigDecimal version);
}
