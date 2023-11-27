/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockNamesListDaoクラス
 * @author t0011036
 */
public class MockNamesListDao implements NamesListDao {

	/**
	 * コンストラクタ.
	 */
	public MockNamesListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<NamesList> getList(final NamesListPagerCondition condition) {
		List<NamesList> list = new ArrayList<NamesList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhNameDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhNameDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhNameCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhNameCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* NamesListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * NamesListを生成する
	 * @param i インデックス
	 * @return NamesList
	 */
	private NamesList createBean(final int i) {
		NamesList bean = new NamesList();
		bean.setNameDivision("NAME_DIVISION" + i);
		bean.setNameCd("NAME_CD" + i);
		bean.setName01("NAME01" + i);
		bean.setName02("NAME02" + i);
		bean.setName03("NAME03" + i);
		return bean;
	}
}
