/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrganizationDetailDaoクラス
 * @author t0011036
 */
public class MockOrganizationDetailDao implements OrganizationDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrganizationDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public OrganizationDetail getEntity(final String organizationCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(organizationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(organizationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		OrganizationDetail bean = new OrganizationDetail();

		/* OrganizationDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * OrganizationDetailを生成する
	 * @param bean bean
	 * @return OrganizationDetail
	 */
	private void createBean(final OrganizationDetail bean) {
		bean.setOrganizationCd("ORGANIZATION01");
		bean.setOrganizationName("NAME01");
	}
}
