/*
 * Created on 2007/04/30
 *
 * $Id$
 * $copyright$
 *
 */
package com.asahikaseieng.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.asahikaseieng.utils.SanitizeCheck;
import junit.framework.TestCase;
import servletunit.HttpServletRequestSimulator;
import servletunit.ServletContextSimulator;

/**
 * SanitizedRequestWrapperテストクラス.
 * @author jbd
 * @version $Revision$
 */
public final class SanitizedRequestWrapperTest extends TestCase {

	private static final int ARRAYS_NUM = 3;

	/**
	 * Constructor for SanitizedRequestWrapperTest.
	 * @param testname テスト名
	 */
	public SanitizedRequestWrapperTest(final String testname) {
		super(testname);
	}

	private SanitizedRequestWrapper request;

	private static final HttpServletRequestSimulator SECURE443 = new HttpServletRequestSimulator(
			new ServletContextSimulator()) {

		public String getParameter(final String name) {
			return "ho%00ge'\"\\\';)yama";
		}

		/**
		 * {@inheritDoc}
		 */
		public Map getParameterMap() {
			Map<String, String[]> map = new HashMap<String, String[]>();

			String[] ret = new String[ARRAYS_NUM];
			ret[0] = "ho'ge\"%00ta";
			ret[1] = "ho\\\'ge%00\"ta";
			ret[2] = "%00ho;ge)ta";

			map.put("hoge", ret);

			return map;
		}

		/**
		 * {@inheritDoc}
		 */
		public String[] getParameterValues(final String name) {
			String[] ret = new String[ARRAYS_NUM];
			ret[0] = "ho'%00ge\"ta";
			ret[1] = "ho\\\'ge\"ta%00";
			ret[2] = "%00ho;ge()ta";
			return ret;
		}

		/**
		 * {@inheritDoc}
		 */
		public Cookie[] getCookies() {
			Cookie c1 = new Cookie("foo", "a%00b");
			Cookie c2 = new Cookie("bar", "a\\\"b");

			return new Cookie[] {c1, c2};
		}

	};

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		request = new SanitizedRequestWrapper(SECURE443);
		/* サニタイジングする文字を設定する */
		SanitizeCheck.setSanitaizeCharacteres("\\\",\\',\",;,(,)");
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * getParameterのテスト.
	 */
	public void testGetParameter() {
		assertEquals((Object) "hoge'yama", request.getParameter("hoge"));
	}

	/**
	 * getParameterMapのテスト.
	 */
	public void testGetParameterMap() {
		Map resultMap = request.getParameterMap();
		String[] result = (String[]) resultMap.get("hoge");
		assertEquals((Object) "ho'geta", result[0]);
		assertEquals((Object) "hogeta", result[1]);
		assertEquals((Object) "hogeta", result[2]);
	}

	/**
	 * getParameterValuesのテスト.
	 */
	public void testGetParameterValues() {
		String[] result = request.getParameterValues("hoge");
		assertEquals((Object) "ho'geta", result[0]);
		assertEquals((Object) "hogeta", result[1]);
		assertEquals((Object) "hogeta", result[2]);
	}

	/**
	 * cookieのテスト.
	 */
	public void testCookie() {
		Cookie[] cs = request.getCookies();
		assertEquals("ab", cs[0].getValue());
		assertEquals("ab", cs[1].getValue());
	}
}
