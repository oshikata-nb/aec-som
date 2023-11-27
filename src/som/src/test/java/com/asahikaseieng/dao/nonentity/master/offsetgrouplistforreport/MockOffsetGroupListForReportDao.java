/*
 * Created on 2009/08/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetGroupListForReportDaoクラス
 * @author t0011036
 */
public class MockOffsetGroupListForReportDao implements
		OffsetGroupListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetGroupListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OffsetGroupListForReport> getListForReport(
			final OffsetGroupListConditionForReport condition) {
		List<OffsetGroupListForReport> list = new ArrayList<OffsetGroupListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOffsetGroupCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOffsetGroupCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OffsetGroupListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OffsetGroupListForReportを生成する
	 * @param i インデックス
	 * @return OffsetGroupListForReport
	 */
	private OffsetGroupListForReport createBean(final int i) {
		OffsetGroupListForReport bean = new OffsetGroupListForReport();
		bean.setOffsetGroupCd("OFFSET_GROUP_CD" + i);
		bean.setOffsetGroupName("NAME" + i);
		return bean;
	}
}
