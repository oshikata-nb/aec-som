/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist;

/**
 * ComponentInformationQueueDeleteListDaoクラス
 * @author t0011036
 */
public interface ComponentInformationQueueDeleteListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.componentinformationqueuedeletelist.
		ComponentInformationQueueDeleteList.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "itemCd, version";

	/**
	 * Listメソッド
	 * 
	 * @param itemCd itemCd
	 * @param version version
	 * @return int
	 */
	int deleteList(final String itemCd, final java.math.BigDecimal version);
}
