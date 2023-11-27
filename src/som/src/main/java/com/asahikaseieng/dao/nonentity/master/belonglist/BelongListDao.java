/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglist;

import java.util.List;

/**
 * BelongListDaoクラス
 * @author t0011036
 */
public interface BelongListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belonglist.BelongList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<BelongList>
	 */
	List<BelongList> getList(final BelongListPagerCondition condition);
}
