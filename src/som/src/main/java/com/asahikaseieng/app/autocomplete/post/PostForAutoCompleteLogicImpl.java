/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.post;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.post.PostForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.post.PostForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 所属のAuto Complete用ロジック
 * @author t0011036
 */
public class PostForAutoCompleteLogicImpl implements PostForAutoCompleteLogic {
	/* 役職操作DAO */
	private PostForAutoCompleteDao postForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PostForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用役職のオートコンプリート用データの取得
	 * @param postId 役職コードまたは役職名称
	 * @return List<PostForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PostForAutoComplete> getSearchList(final String postId)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(postId);
		List<PostForAutoComplete> list = postForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * postForAutoCompleteDaoを設定します。
	 * @param postForAutoCompleteDao postForAutoCompleteDao
	 */
	public void setPostForAutoCompleteDao(
			final PostForAutoCompleteDao postForAutoCompleteDao) {
		this.postForAutoCompleteDao = postForAutoCompleteDao;
	}
}
