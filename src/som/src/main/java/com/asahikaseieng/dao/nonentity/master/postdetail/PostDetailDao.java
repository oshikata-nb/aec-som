/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postdetail;

/**
 * PostDetailDaoクラス
 * @author t0011036
 */
public interface PostDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "postId";

	/**
	 * PostDetailメソッド
	 * 
	 * @param postId postId
	 * @return PostDetail
	 */
	PostDetail getEntity(final String postId);
}
