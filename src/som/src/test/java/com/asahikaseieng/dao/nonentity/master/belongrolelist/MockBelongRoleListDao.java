/*
 * Created on 2009/03/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongrolelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBelongRoleListDaoクラス
 * @author t0011036
 */
public class MockBelongRoleListDao implements BelongRoleListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBelongRoleListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BelongRoleList> getList(
			final BelongRoleListPagerCondition condition) {
		List<BelongRoleList> list = new ArrayList<BelongRoleList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhPostId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhRoleId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhPostId())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhRoleId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BelongRoleListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BelongRoleListを生成する
	 * @param i インデックス
	 * @return BelongRoleList
	 */
	private BelongRoleList createBean(final int i) {
		BelongRoleList bean = new BelongRoleList();
		bean.setOrganizationCd("ORGANIZATION_CD" + i);
		bean.setOrganizationName("部署名称" + i);
		bean.setPostId(new BigDecimal("i"));
		bean.setPostName("役職名称" + i);
		bean.setRoleId(new BigDecimal("i"));
		bean.setRoleName("ロール名称" + i);
		return bean;
	}
}
