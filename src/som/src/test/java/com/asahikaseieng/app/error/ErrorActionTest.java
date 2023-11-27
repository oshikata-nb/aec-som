/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.error;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequestWrapper;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.NoLoginRuntimeException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.test.AbstractS2StrutsTestCase;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections.iterators.ArrayIterator;

/**
 * ErrorActionテストケースクラス.
 * @author jbd
 */
public class ErrorActionTest extends AbstractS2StrutsTestCase {

	/**
	 * @param testname testname
	 */
	public ErrorActionTest(final String testname) {
		super(testname, "/Error.do");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUpImpl() throws Exception {
		;
	}

	/**
	 * nologinエラー.
	 */
	public void testNoLogin() {

		request.setAttribute("javax.servlet.error.exception",
			new NoLoginRuntimeException());
		getSession().setAttribute(Constants.LOGIN_KEY, null);

		actionPerform();

		verifyForwardPath("/jsp/common/top_redirect.jsp?to=timeout%2Ehtml");
	}

	/**
	 * 404エラー.
	 */
	public void testUnspecified404() {

		request.setAttribute("javax.servlet.error.exception",
			new RuntimeException());
		request.setAttribute("javax.servlet.error.status_code", new Integer(
				"404"));
		getSession().setAttribute(Constants.LOGIN_KEY, new LoginInfo());

		actionPerform();

		verifyForwardPath("/jsp/common/top_redirect.jsp?to=404%2Ehtml");
	}

	/**
	 * 500エラー.
	 */
	public void testUnspecified500() {

		request.setAttribute("javax.servlet.error.exception",
			new RuntimeException());
		getSession().setAttribute(Constants.LOGIN_KEY, new LoginInfo());

		setRequestWrapper(new HttpServletRequestWrapper(getRequestWrapper()) {
			@Override
			public StringBuffer getRequestURL() {
				return new StringBuffer("/Error.do");
			}

			private final String[] headers = {"foo", "bar"};

			@Override
			public Enumeration getHeaderNames() {
				return IteratorUtils.asEnumeration(new ArrayIterator(headers));
			}
		});

		actionPerform();

		verifyForwardPath("/jsp/common/top_redirect.jsp?to=500%2Ehtml");
		assertNull(getSession().getAttribute(Constants.LOGIN_KEY));
	}
}
