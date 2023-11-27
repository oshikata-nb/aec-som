/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlist;

import java.util.List;

/**
 * ReasonListDaoクラス
 * @author t0011036
 */
public interface ReasonListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<ReasonList>
	 */
	List<ReasonList> getList(final ReasonListPagerCondition condition);
}
