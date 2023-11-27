/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPostListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockPostForAutoCompleteDao implements PostForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockPostForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PostForAutoComplete> getSearchList(final String postId,
			final String rowlimit) {
		List<PostForAutoComplete> list = new ArrayList<PostForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(postId)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(postId)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PostListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PostListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return PostListForAutoComplete
	 */
	private PostForAutoComplete createBean(final int i) {
		PostForAutoComplete bean = new PostForAutoComplete();
		bean.setPostId(new BigDecimal(i));
		bean.setPostName("NAME" + i);
		return bean;
	}
}
