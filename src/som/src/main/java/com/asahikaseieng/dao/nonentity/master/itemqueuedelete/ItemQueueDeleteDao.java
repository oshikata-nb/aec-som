/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedelete;

/**
 * ItemQueueDeleteDaoクラス
 * @author t0011036
 */
public interface ItemQueueDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuedelete.ItemQueueDelete.class;

	/** ARGSアノテーション delete */
	String delete_ARGS = "itemCd, version";

	/**
	 * ItemQueueDeleteメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int 削除件数
	 */
	int delete(final String itemCd, final java.math.BigDecimal version);
}
