/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.assistance.authority;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.aop.interceptors.MockInterceptor;

import com.asahikaseieng.dao.nonentity.authority.Authority;
import com.asahikaseieng.dao.nonentity.authority.AuthorityRole;
import com.asahikaseieng.dao.nonentity.authority.AuthorityRoleDao;
import com.asahikaseieng.test.AbstractS2TestCase;

/**
 * ShippingListLogicImplテストクラス.
 * @author jbd
 */
public class AuthorityLogicImplTest extends AbstractS2TestCase {

	private AuthorityLogicImpl logic;

	/**
	 * コンストラクタ.
	 * @param name テスト名
	 */
	public AuthorityLogicImplTest(final String name) {

		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		include("logictest.dicon");
	}

	/**
	 * testGetXxxxAuthorityList.
	 */
	public void testGetAuthorityList() {

		try {
			logic.getGadgetAuthorityList("", null, null);
			fail("shuld raise an " + IllegalArgumentException.class);
		} catch (IllegalArgumentException e) {

		}

		/* MockInterceptorを設定する */
		MockInterceptor mi = new MockInterceptor();

		for (int i = 0; i < 2; i++) {

			/* ① ロールなしの場合、空の配列が返る */
			mi.setReturnValue("getTantoRoleList",
				new ArrayList<AuthorityRole>());
			mi.setReturnValue("getSosikiRoleList",
				new ArrayList<AuthorityRole>());

			logic.setAuthorityRoleDao((AuthorityRoleDao) mi
					.createProxy(AuthorityRoleDao.class));

			List<Authority> list = null;
			if (i == 0) {
				// list = logic.getMenuAuthorityList("dummy");
			} else {
				list = logic.getGadgetAuthorityList("dummy", null, null);
			}
			assertEquals(0, list.size());

			/* ① ロールありの場合、配列が返る */
			List<AuthorityRole> roleList = new ArrayList<AuthorityRole>();
			AuthorityRole bean = new AuthorityRole();
			bean.setRoleId(new BigDecimal("1"));
			roleList.add(bean);

			mi.setReturnValue("getTantoRoleList", roleList);
			mi.setReturnValue("getSosikiRoleList", roleList);

			logic.setAuthorityRoleDao((AuthorityRoleDao) mi
					.createProxy(AuthorityRoleDao.class));

			list = null;
			if (i == 0) {
				// list = logic.getMenuAuthorityList("dummy");
			} else {
				list = logic.getGadgetAuthorityList("dummy", null, null);
			}
			assertTrue(list.size() > 0);
		}

	}

}
