/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrylist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCarryListDaoクラス
 * @author t0011036
 */
public class MockCarryListDao implements CarryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockCarryListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CarryList> getList(final CarryListPagerCondition condition) {
		List<CarryList> list = new ArrayList<CarryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhCarryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhCarryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CarryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CarryListを生成する
	 * @param i インデックス
	 * @return CarryList
	 */
	private CarryList createBean(final int i) {
		CarryList bean = new CarryList();
		bean.setCarryCd("CARRY" + i);
		bean.setCarryName1("NAME" + i);
		return bean;
	}
}
