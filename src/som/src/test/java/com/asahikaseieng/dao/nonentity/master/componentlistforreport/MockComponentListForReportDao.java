/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentListForReportDaoクラス
 * @author t0011036
 */
public class MockComponentListForReportDao implements ComponentListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ComponentListForReport> getListForReport(
			final ComponentListConditionForReport condition) {
		List<ComponentListForReport> list = new ArrayList<ComponentListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhComponentCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhComponentCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ComponentListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ComponentListForReportを生成する
	 * @param i インデックス
	 * @return ComponentListForReport
	 */
	private ComponentListForReport createBean(final int i) {
		ComponentListForReport bean = new ComponentListForReport();
		bean.setComponentCd("COMPONENT" + i);
		bean.setComponentName("NAME" + i);
		return bean;
	}
}
