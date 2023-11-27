/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.post;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.post.PostForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 役職のAuto Complete用ロジック
 * @author t0011036
 */
public interface PostForAutoCompleteLogic {
	/**
	 * 検索画面用役職のオートコンプリート用データの取得
	 * @param postId 役職コードまたは役職名称
	 * @return List<PostForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PostForAutoComplete> getSearchList(String postId)
			throws NoDataException;
}
