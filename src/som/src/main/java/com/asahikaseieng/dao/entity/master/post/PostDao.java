/*
 * Created on Fri Mar 20 14:59:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.post;

/**
 * PostDaoインターフェース.
 * @author t0011036
 */
public interface PostDao {

	/** BEANアノテーション. */
	Class BEAN = Post.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Post
	 * @return Insert件数
	 */
	int insert(Post bean);

	/**
	 * Update.
	 * @param bean Post
	 * @return Update件数
	 */
	int update(Post bean);

	/**
	 * Delete.
	 * @param bean Post
	 * @return Delete件数
	 */
	int delete(Post bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "POST_ID";

	/**
	 * エンティティ取得.
	 * @param postId postId
	 * @return Post
	 */
	Post getEntity(java.math.BigDecimal postId);
}
