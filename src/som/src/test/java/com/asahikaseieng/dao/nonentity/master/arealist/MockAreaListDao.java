/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.arealist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAreaListDaoクラス
 * @author t0011036
 */
public class MockAreaListDao implements AreaListDao {

	/**
	 * コンストラクタ.
	 */
	public MockAreaListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AreaList> getList(final AreaListPagerCondition condition) {
		List<AreaList> list = new ArrayList<AreaList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhAreaCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhAreaCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AreaListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AreaListを生成する
	 * @param i インデックス
	 * @return AreaList
	 */
	private AreaList createBean(final int i) {
		AreaList bean = new AreaList();
		bean.setAreaCd("AREA" + i);
		bean.setAreaName("地区名" + i);
		return bean;
	}
}
