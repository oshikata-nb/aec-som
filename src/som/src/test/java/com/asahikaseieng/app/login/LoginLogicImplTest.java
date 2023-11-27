/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2TestCase;

/**
 * LoginLogicImplのテストケース.
 * @author jbd
 */
public class LoginLogicImplTest extends AbstractS2TestCase {

	private LoginLogicImpl loginLogic;

	/**
	 * コンストラクタ.
	 * @param name string
	 */
	public LoginLogicImplTest(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		include("logictest.dicon");
	}

	/**
	 * login(引数間違い).
	 * @throws NoDataException NoDataException
	 */
	public void testLoginWithInvalArgument() throws NoDataException {

		String[][] invalidArgs = { {"", "1"}, {null, "1"}, {"1", ""},
				{"", null}};

		for (String[] arg : invalidArgs) {
			try {
				loginLogic.login(arg[0], arg[1]);
				fail("Should raise an " + IllegalArgumentException.class);
			} catch (IllegalArgumentException e) {
				;
			}
		}
	}

	/**
	 * loginテスト.
	 * @throws NoDataException NoDataException
	 */
	public void testLogin() throws NoDataException {

		/** データが取得できる場合(基本から) */
		String userId = "u000000001";
		String userKbn = "1";
		LoginInfo bean = null;
		try {
			bean = loginLogic.login(userId, userKbn);
		} catch (NoDataException e) {
			fail("Raise a NoDataException!!!");
		}
		/* 検証 */
		assertNotNull(bean);

		/** データが取得できない場合 */
		bean = null;
		try {
			userId = Constants.TEST_PARAMETER_NODATA;
			bean = loginLogic.login(userId, userKbn);
			fail("Should raise a NoDataException!!!");
		} catch (NoDataException e) {
			/* 検証 */
			assertNull(bean);
		}
	}

}
