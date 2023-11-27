/*
 * Created on 2009/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgrouplist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetGroupListDaoクラス
 * @author t0011036
 */
public class MockOffsetGroupListDao implements OffsetGroupListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetGroupListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OffsetGroupList> getList(
			final OffsetGroupListPagerCondition condition) {
		List<OffsetGroupList> list = new ArrayList<OffsetGroupList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOffsetGroupCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOffsetGroupCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OffsetGroupListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OffsetGroupListを生成する
	 * @param i インデックス
	 * @return OffsetGroupList
	 */
	private OffsetGroupList createBean(final int i) {
		OffsetGroupList bean = new OffsetGroupList();
		bean.setOffsetGroupCd("OFFSET_GROUP_CD" + i);
		bean.setOffsetGroupName("OFFSET_GROUP_NAME" + i);
		return bean;
	}
}
