/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongroledetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBelongRoleDetailDaoクラス
 * @author t0011036
 */
public class MockBelongRoleDetailDao implements BelongRoleDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBelongRoleDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BelongRoleDetail getEntity(final String organizationCd,
			final String postId) {
		if (Constants.TEST_PARAMETER_NODATA.equals(organizationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(postId)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(organizationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(postId)) {
			throw new LargeAmountDataRuntimeException();
		}

		BelongRoleDetail bean = new BelongRoleDetail();

		/* BelongRoleDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BelongRoleDetailを生成する
	 * @param bean bean
	 * @return BelongRoleDetail
	 */
	private void createBean(final BelongRoleDetail bean) {
		bean.setOrganizationCd("ORGANIZATION_CD001");
		bean.setOrganizationName("部署名称００１");
		bean.setPostId(new BigDecimal("1"));
		bean.setPostName("役職名称００１");
		bean.setRoleId(new BigDecimal("1"));
		bean.setRoleName("ロール名称００１");
	}
}
