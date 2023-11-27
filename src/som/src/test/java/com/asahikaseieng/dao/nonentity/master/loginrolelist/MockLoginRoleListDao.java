/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginrolelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginRoleListDaoクラス
 * @author t0011036
 */
public class MockLoginRoleListDao implements LoginRoleListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginRoleListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LoginRoleList> getList(final String tantoCd) {
		List<LoginRoleList> list = new ArrayList<LoginRoleList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LoginRoleListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LoginRoleListを生成する
	 * @param i インデックス
	 * @return LoginRoleList
	 */
	private LoginRoleList createBean(final int i) {
		LoginRoleList bean = new LoginRoleList();
		bean.setRoleId(new BigDecimal(i));
		return bean;
	}
}
