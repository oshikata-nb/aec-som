/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongpostlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBelongPostListDaoクラス
 * @author t0011036
 */
public class MockBelongPostListDao implements BelongPostListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBelongPostListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BelongPostList> getList(final String postId) {
		List<BelongPostList> list = new ArrayList<BelongPostList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(postId)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(postId)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BelongPostListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BelongPostListを生成する
	 * @param i インデックス
	 * @return BelongPostList
	 */
	private BelongPostList createBean(final int i) {
		BelongPostList bean = new BelongPostList();
		bean.setPostId(new BigDecimal(i));
		bean.setPostName("NAME" + i);
		return bean;
	}
}
