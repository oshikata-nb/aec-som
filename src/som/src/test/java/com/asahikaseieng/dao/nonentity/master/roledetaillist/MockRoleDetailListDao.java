/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRoleDetailListDaoクラス
 * @author t0011036
 */
public class MockRoleDetailListDao implements RoleDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RoleDetailList> getList(final String roleId) {
		List<RoleDetailList> list = new ArrayList<RoleDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(roleId)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(roleId)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RoleDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RoleDetailListを生成する
	 * @param i インデックス
	 * @return RoleDetailList
	 */
	private RoleDetailList createBean(final int i) {
		RoleDetailList bean = new RoleDetailList();
		bean.setRoleId(new BigDecimal(i));
		bean.setRoleName("NAME" + i);
		return bean;
	}
}
