/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongpostlist;

import java.util.List;

/**
 * BelongPostListDaoクラス
 * @author t0011036
 */
public interface BelongPostListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belongpostlist.BelongPostList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "postId";

	/**
	 * Listメソッド
	 * 
	 * @param postId postId
	 * @return List<BelongPostList>
	 */
	List<BelongPostList> getList(final String postId);
}
