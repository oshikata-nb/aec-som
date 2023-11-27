/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.post.Post;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 役職詳細ロジック interface.
 * @author t0011036
 */
public interface PostDetailLogic {
	/**
	 * 検索処理を行う.
	 * @param postId 役職コード
	 * @return Post
	 */
	Post getEntity(final BigDecimal postId);

	/**
	 * 検索処理を行う.
	 * @param postId 役職コード
	 * @return PostDetail
	 */
	PostDetail getDetailEntity(final String postId);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void update(final Post bean);

	/**
	 * 追加処理を行う.
	 * @param bean 追加対象データ
	 */
	void insert(final Post bean);

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象データ
	 * @throws NoDataException NoDataException
	 */
	void delete(final Post bean) throws NoDataException;
}
