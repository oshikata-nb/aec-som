/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginallrolelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MockLoginAllRoleListDaoクラス
 * @author t0011036
 */
public class MockLoginAllRoleListDao implements LoginAllRoleListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginAllRoleListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LoginAllRoleList> getList() {
		List<LoginAllRoleList> list = new ArrayList<LoginAllRoleList>();

		for (int i = 1; i < 10; i++) {
			/* LoginAllRoleListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LoginAllRoleListを生成する
	 * @param i インデックス
	 * @return LoginAllRoleList
	 */
	private LoginAllRoleList createBean(final int i) {
		LoginAllRoleList bean = new LoginAllRoleList();
		bean.setRoleId(new BigDecimal(i));
		bean.setRoleName("NAME" + i);
		return bean;
	}
}
