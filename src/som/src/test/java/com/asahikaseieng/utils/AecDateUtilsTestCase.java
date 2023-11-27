/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.sql.Timestamp;
import java.util.Calendar;

import junit.framework.TestCase;

/**
 * 独自実装DateUtilsテストケース.
 * @author jbd
 */
public final class AecDateUtilsTestCase extends TestCase {

	/**
	 * コンストラクタ.
	 * @param testname テスト名
	 */
	public AecDateUtilsTestCase(final String testname) {
		super(testname);
	}

	/**
	 * getCurrentDateテスト.
	 */
	public void testGetCurrentDate() {
		assertNotNull(AecDateUtils.getCurrentDate());
	}

	/**
	 * testDateFormat.
	 */
	public void testDateFormat() {
		Calendar cal = Calendar.getInstance();
		final int year = 2005;
		final int month = 0;
		final int day = 1;
		final int hour = 23;
		final int minute = 59;
		cal.set(year, month, day, hour, minute);
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		assertEquals("2005/01/01 23:59", AecDateUtils.dateFormat(timestamp,
			"yyyy/MM/dd HH:mm"));

		try {
			assertEquals(null, AecDateUtils.dateFormat(null, "yyyy/MM/dd"));
		} catch (IllegalArgumentException e) {
			fail("should raise IllegalArgumentException.");
		}

		String result = null;
		try {
			result = AecDateUtils.dateFormat(timestamp, null);
			fail("should raise NullPointerException.");
		} catch (IllegalArgumentException e) {
			assertNull(result);
		}
	}

	/**
	 * getTimestampYmdFormatテスト.
	 */
	public void testGetTimestampYmdFormat() {

		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.MILLISECOND, 0);
		final int year = 2005;
		final int month = 0;
		final int day = 01;
		final int hour = 0;
		final int minute = 0;
		final int second = 0;
		cl.set(year, month, day, hour, minute, second);

		assertEquals(new Timestamp(cl.getTimeInMillis()), AecDateUtils
				.getTimestampYmdFormat("2005/01/01", false));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("2005/0a/10",
			false));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("20050", false));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat(null, false));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("", false));

		assertEquals(new Timestamp(cl.getTimeInMillis()), AecDateUtils
				.getTimestampYmdFormat("05/01/01", true));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("2005/01/10",
			true));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("05/0a/10", true));
		assertEquals(null, AecDateUtils.getTimestampYmdFormat("20050", true));
	}

}
