/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationListForReportDaoクラス
 * @author t0011036
 */
public class MockOperationListForReportDao implements OperationListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationListForReport> getListForReport(
			final OperationListConditionForReport condition) {
		List<OperationListForReport> list = new ArrayList<OperationListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOperationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOperationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OperationListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationListForReportを生成する
	 * @param i インデックス
	 * @return OperationListForReport
	 */
	private OperationListForReport createBean(final int i) {
		OperationListForReport bean = new OperationListForReport();
		bean.setOperationCd("OPERATION_CD" + i);
		bean.setOperationName("NAME" + i);
		return bean;
	}
}
