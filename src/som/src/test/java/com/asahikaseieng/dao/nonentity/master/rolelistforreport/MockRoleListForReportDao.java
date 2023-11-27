/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRoleListForReportDaoクラス
 * @author t0011036
 */
public class MockRoleListForReportDao implements RoleListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RoleListForReport> getListForReport(
			final RoleListConditionForReport condition) {
		List<RoleListForReport> list = new ArrayList<RoleListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhRoleId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhRoleId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RoleListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RoleListForReportを生成する
	 * @param i インデックス
	 * @return RoleListForReport
	 */
	private RoleListForReport createBean(final int i) {
		RoleListForReport bean = new RoleListForReport();
		bean.setRoleId(new BigDecimal(i));
		bean.setRoleName("NAME" + i);
		return bean;
	}
}
