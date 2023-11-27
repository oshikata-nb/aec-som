/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.header;

import com.asahikaseieng.Constants;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;

/**
 * HeaderActionテストケースクラス.
 * @author jbd
 */
public class HeaderActionTest extends AbstractS2StrutsTestCase {

	/**
	 * @param testname testname
	 */
	public HeaderActionTest(final String testname) {
		super(testname, "/Header.do");
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
		addRequestParameter("op", "init");
		actionPerform();
		verifyNoActionErrors();
		verifyForwardPath("/jsp/common/header.jsp");
	}
}
