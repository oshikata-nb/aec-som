/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPostListDaoクラス
 * @author t0011036
 */
public class MockPostListDao implements PostListDao {

	/**
	 * コンストラクタ.
	 */
	public MockPostListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PostList> getList(final PostListPagerCondition condition) {
		List<PostList> list = new ArrayList<PostList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhPostId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhPostId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PostListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PostListを生成する
	 * @param i インデックス
	 * @return PostList
	 */
	private PostList createBean(final int i) {
		PostList bean = new PostList();
		bean.setPostId(new BigDecimal(i));
		bean.setPostName("NAME" + i);
		return bean;
	}
}
