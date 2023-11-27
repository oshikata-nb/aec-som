/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginListForReportDaoクラス
 * @author t0011036
 */
public class MockLoginListForReportDao implements LoginListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LoginListForReport> getListForReport(
			final LoginListConditionForReport condition) {
		List<LoginListForReport> list = new ArrayList<LoginListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LoginListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LoginListForReportを生成する
	 * @param i インデックス
	 * @return LoginListForReport
	 */
	private LoginListForReport createBean(final int i) {
		LoginListForReport bean = new LoginListForReport();
		bean.setOrganizationCd("ORGANIZATION_CD" + i);
		bean.setTantoCd("TANTO_CD" + i);
		bean.setPostId(new BigDecimal(i));
		return bean;
	}
}
