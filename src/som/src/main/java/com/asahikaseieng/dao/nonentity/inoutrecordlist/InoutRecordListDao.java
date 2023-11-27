/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlist;

import java.util.List;

/**
 * InoutRecordListDaoクラス
 * @author t0011036
 */
public interface InoutRecordListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<InoutRecordList>
	 */
	List<InoutRecordList> getList(final InoutRecordListPagerCondition condition);
}
