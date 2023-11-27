/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.logout;

import com.asahikaseieng.Constants;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;

/**
 * LogoutActionテストケースクラス.
 * @author jbd
 */
public class LogoutActionTest extends AbstractS2StrutsTestCase {

	/**
	 * @param testname testname
	 */
	public LogoutActionTest(final String testname) {
		super(testname, "/Logout.do");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		getSession().setAttribute(Constants.LOGIN_KEY, new LoginInfo());
	}

	/**
	 * init.
	 */
	public void testInit() {
		super.reset();
		// unspecなので。。
		// addRequestParameter("op", "init");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/top_redirect.jsp?to=logout%2Ehtml");

		assertNull(getSession().getAttribute(Constants.LOGIN_KEY));
	}

	/**
	 * no login時のテストケース.
	 */
	public void testNoLogin() {

		// no loginでもOK!!
		getSession().removeAttribute(Constants.LOGIN_KEY);
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/top_redirect.jsp?to=logout%2Ehtml");
	}
}
