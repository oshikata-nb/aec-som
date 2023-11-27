/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrganizationListDaoクラス
 * @author t0011036
 */
public class MockOrganizationListDao implements OrganizationListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrganizationListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OrganizationList> getList(
			final OrganizationListPagerCondition condition) {
		List<OrganizationList> list = new ArrayList<OrganizationList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OrganizationListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OrganizationListを生成する
	 * @param i インデックス
	 * @return OrganizationList
	 */
	private OrganizationList createBean(final int i) {
		OrganizationList bean = new OrganizationList();
		bean.setOrganizationCd("ORGANIZATION" + i);
		bean.setOrganizationName("NAME" + i);
		return bean;
	}
}
