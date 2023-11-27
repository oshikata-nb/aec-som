/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumonlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBumonListDaoクラス
 * @author t0011036
 */
public class MockBumonListDao implements BumonListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBumonListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonList> getList(final BumonListPagerCondition condition) {
		List<BumonList> list = new ArrayList<BumonList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhSectionCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhSectionCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BumonListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BumonListを生成する
	 * @param i インデックス
	 * @return BumonList
	 */
	private BumonList createBean(final int i) {
		BumonList bean = new BumonList();
		bean.setSectionCd("SECTION" + i);
		bean.setSectionName("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonList> getSearchList(final BumonListPagerCondition condition) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonList> getSearchListForAutoComplete(final String sectionCd) {
		// TODO 自動生成したメソッド・スタブ
		return null;
	}
}
