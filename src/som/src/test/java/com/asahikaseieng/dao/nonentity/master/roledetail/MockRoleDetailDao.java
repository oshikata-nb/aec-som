/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRoleDetailDaoクラス
 * @author t0011036
 */
public class MockRoleDetailDao implements RoleDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public RoleDetail getEntity(final String roleId) {
		if (Constants.TEST_PARAMETER_NODATA.equals(roleId)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(roleId)) {
			throw new LargeAmountDataRuntimeException();
		}

		RoleDetail bean = new RoleDetail();

		/* RoleDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RoleDetailを生成する
	 * @param bean bean
	 * @return RoleDetail
	 */
	private void createBean(final RoleDetail bean) {
		bean.setRoleId(new BigDecimal("1"));
		bean.setRoleName("NAME01");
	}
}
