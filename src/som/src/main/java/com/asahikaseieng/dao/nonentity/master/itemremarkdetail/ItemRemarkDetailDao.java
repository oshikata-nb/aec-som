/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemremarkdetail;

import java.math.BigDecimal;

/**
 * ItemRemarkDetailDaoクラス
 * @author t0011036
 */
public interface ItemRemarkDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemremarkdetail.ItemRemarkDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd, version";

	/**
	 * ItemRemarkDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return ItemRemarkDetail
	 */
	ItemRemarkDetail getEntity(final String itemCd, final BigDecimal version);
}
