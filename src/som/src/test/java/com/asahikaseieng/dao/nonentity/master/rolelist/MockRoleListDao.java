/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRoleListDaoクラス
 * @author t0011036
 */
public class MockRoleListDao implements RoleListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RoleList> getList(final RoleListPagerCondition condition) {
		List<RoleList> list = new ArrayList<RoleList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhRoleId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhRoleId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RoleListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RoleListを生成する
	 * @param i インデックス
	 * @return RoleList
	 */
	private RoleList createBean(final int i) {
		RoleList bean = new RoleList();
		bean.setRoleId(new BigDecimal(i));
		bean.setRoleName("NAME" + i);
		return bean;
	}
}
