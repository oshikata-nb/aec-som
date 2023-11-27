/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlist;

import java.util.List;

/**
 * PostListDaoクラス
 * @author t0011036
 */
public interface PostListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.postlist.PostList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<PostList>
	 */
	List<PostList> getList(final PostListPagerCondition condition);
}
