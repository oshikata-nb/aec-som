/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.rolemenulist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MockRoleMenuListDaoクラス
 * @author t0011036
 */
public class MockRoleMenuListDao implements RoleMenuListDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleMenuListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RoleMenuList> getMenuList() {
		List<RoleMenuList> list = new ArrayList<RoleMenuList>();

		for (int i = 1; i < 10; i++) {
			/* RoleMenuListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RoleMenuListを生成する
	 * @param i インデックス
	 * @return RoleMenuList
	 */
	private RoleMenuList createBean(final int i) {
		RoleMenuList bean = new RoleMenuList();
		bean.setMenuId(new BigDecimal(i));
		bean.setMenuName("NAME" + i);
		bean.setTabId(new BigDecimal(i));
		bean.setTabName("NAME" + i);
		return bean;
	}
}
