/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockPostListForReportDaoクラス
 * @author t0011036
 */
public class MockPostListForReportDao implements PostListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockPostListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<PostListForReport> getListForReport(
			final PostListConditionForReport condition) {
		List<PostListForReport> list = new ArrayList<PostListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhPostId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhPostId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* PostListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * PostListForReportを生成する
	 * @param i インデックス
	 * @return PostListForReport
	 */
	private PostListForReport createBean(final int i) {
		PostListForReport bean = new PostListForReport();
		bean.setPostId(new BigDecimal(i));
		bean.setPostName("NAME" + i);
		return bean;
	}
}
