/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.callist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCalListDaoクラス
 * @author t0011036
 */
public class MockCalListDao implements CalListDao {

	/**
	 * コンストラクタ.
	 */
	public MockCalListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CalList> getList(final CalListPagerCondition condition) {
		List<CalList> list = new ArrayList<CalList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhCalCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhCalCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CalListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CalListを生成する
	 * @param i インデックス
	 * @return CalList
	 */
	private CalList createBean(final int i) {
		CalList bean = new CalList();
		bean.setCalCd("CAL_CD" + i);
		bean.setCalName("NAME" + i);
		return bean;
	}
}
