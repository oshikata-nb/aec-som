/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRemarkListDaoクラス
 * @author kanri-user
 */
public class MockRemarkListDao implements RemarkListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RemarkList> getList(final RemarkListPagerCondition condition) {
		List<RemarkList> list = new ArrayList<RemarkList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RemarkListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RemarkListを生成する
	 * @param i インデックス
	 * @return RemarkList
	 */
	private RemarkList createBean(final int i) {
		RemarkList bean = new RemarkList();
		bean.setRemarkNo(new BigDecimal(i));
		return bean;
	}
}
