/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.post;

import java.util.List;

/**
 * PostForAutoCompleteDaoクラス
 * @author t0011036
 */
public interface PostForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.post.PostForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "postId,rowlimit";

	/**
	 * PostForAutoCompleteメソッド
	 * 
	 * @param postId postId
	 * @param rowlimit 行上限
	 * @return PostForAutoComplete
	 */
	List<PostForAutoComplete> getSearchList(final String postId,
			final String rowlimit);
}
