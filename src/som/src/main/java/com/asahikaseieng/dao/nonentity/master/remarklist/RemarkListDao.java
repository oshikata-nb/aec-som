/*
 * Created on 2007/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklist;

import java.util.List;

/**
 * RemarkDaoクラス
 * @author a1020630
 */
public interface RemarkListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.remarklist.RemarkList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 備考マスタ一覧取得
	 * @param condition condition
	 * @return List<RemarkList>
	 */
	List<RemarkList> getList(final RemarkListPagerCondition condition);
}
