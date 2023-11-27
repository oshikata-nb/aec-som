/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockFinancialClassListDaoクラス
 * @author t0011036
 */
public class MockFinancialClassListDao implements FinancialClassListDao {

	/**
	 * コンストラクタ.
	 */
	public MockFinancialClassListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FinancialClassList> getList(
			final FinancialClassListPagerCondition condition) {
		List<FinancialClassList> list = new ArrayList<FinancialClassList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhFinancialClassCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhFinancialClassCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* FinancialClassListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * FinancialClassListを生成する
	 * @param i インデックス
	 * @return FinancialClassList
	 */
	private FinancialClassList createBean(final int i) {
		FinancialClassList bean = new FinancialClassList();
		bean.setFinancialClassCd("DELIVERY" + i);
		bean.setFinancialClassName("NAME" + i);
		return bean;
	}
}
