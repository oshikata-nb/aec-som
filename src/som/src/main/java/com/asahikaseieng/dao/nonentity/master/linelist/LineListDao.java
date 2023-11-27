/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linelist;

import java.util.List;

/**
 * LineListDaoクラス
 * @author t0011036
 */
public interface LineListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.linelist.LineList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<LineList>
	 */
	List<LineList> getList(final LineListPagerCondition condition);
}
