/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInoutRecordListForReportDaoクラス
 * @author t0011036
 */
public class MockInoutRecordListForReportDao implements
		InoutRecordListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInoutRecordListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InoutRecordListForReport> getListForReport(
			final InoutRecordReportCondition condition) {
		List<InoutRecordListForReport> list = new ArrayList<InoutRecordListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InoutRecordListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InoutRecordListForReportを生成する
	 * @param i インデックス
	 * @return InoutRecordListForReport
	 */
	private InoutRecordListForReport createBean(final int i) {
		InoutRecordListForReport bean = new InoutRecordListForReport();
		bean.setInoutDivision(new BigDecimal(i));
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		return bean;
	}
}
