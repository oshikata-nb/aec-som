/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPostDetailDaoクラス
 * @author t0011036
 */
public class MockPostDetailDao implements PostDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockPostDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public PostDetail getEntity(final String postId) {
		if (Constants.TEST_PARAMETER_NODATA.equals(postId)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(postId)) {
			throw new LargeAmountDataRuntimeException();
		}

		PostDetail bean = new PostDetail();

		/* PostDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * PostDetailを生成する
	 * @param bean bean
	 * @return PostDetail
	 */
	private void createBean(final PostDetail bean) {
		bean.setPostId(new BigDecimal("1"));
		bean.setPostName("NAME01");
	}
}
