/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;

/**
 * TestCaseHelperテストケース.
 * @author jbd
 */
public final class TcHelperTest extends TestCase {

	/**
	 * コンストラクタ.
	 * @param testname テスト名
	 */
	public TcHelperTest(final String testname) {
		super(testname);
	}

	/**
	 * getString(num, str).
	 */
	public void testGetString() {
		assertEquals(null, TcHelper.getString(1, null));
		assertEquals(null, TcHelper.getString(1, "aa"));
		assertEquals("a", TcHelper.getString(1, "a"));
		assertEquals(StringUtils.repeat("a", 15), TcHelper.getString(15,
			"a"));
	}

	/**
	 * getString(num).
	 */
	public void testGetString2() {
		assertEquals(null, TcHelper.getString(-1));
		assertEquals("a", TcHelper.getString(1));
		assertEquals(StringUtils.repeat("a", 15), TcHelper.getString(15));
	}

}
