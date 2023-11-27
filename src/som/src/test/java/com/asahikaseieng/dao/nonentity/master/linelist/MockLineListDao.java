/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.linelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLineListDaoクラス
 * @author t0011036
 */
public class MockLineListDao implements LineListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLineListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LineList> getList(final LineListPagerCondition condition) {
		List<LineList> list = new ArrayList<LineList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhProductionLine())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhProductionLine())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LineListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LineListを生成する
	 * @param i インデックス
	 * @return LineList
	 */
	private LineList createBean(final int i) {
		LineList bean = new LineList();
		bean.setProductionLine("PRODUCTION_LINE" + i);
		bean.setProductionLineName("NAME" + i);
		return bean;
	}
}
