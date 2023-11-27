/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.math.BigDecimal;

import junit.framework.TestCase;

/**
 * 独自実装NumberUtilsテストケース.
 * @author jbd
 */
public final class AecNumberUtilsTest extends TestCase {

	/**
	 * コンストラクタ.
	 * @param testname テスト名
	 */
	public AecNumberUtilsTest(final String testname) {
		super(testname);
	}

	/**
	 * convertBigDecimal.
	 */
	public void testConvertBigDecimal() {
		assertEquals(null, AecNumberUtils.convertBigDecimal(null));
		assertEquals(null, AecNumberUtils.convertBigDecimal(""));
		assertEquals(null, AecNumberUtils.convertBigDecimal("aa1"));
		assertEquals(new BigDecimal("123456.78"), AecNumberUtils
				.convertBigDecimal("123,456.78"));
		assertEquals(new BigDecimal("123456.19"), AecNumberUtils
				.convertBigDecimal("123456.19"));
		assertEquals(new BigDecimal("123.54"), AecNumberUtils
				.convertBigDecimal("123.54"));
	}

	/**
	 * decimalFormatテスト.
	 */
	public void testDecimalFormat() {

		/* エラーの場合 */
		try {
			AecNumberUtils.decimalFormat(new BigDecimal("12345"), null);
			fail("should raise IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			;
		}

		/* 正常処理の場合 */
		String format = "###,##0.00";
		assertEquals(null, AecNumberUtils.decimalFormat(null, format));
		assertEquals("123.00", AecNumberUtils.decimalFormat(new BigDecimal(
				"123"), format));
		assertEquals("12,345.91", AecNumberUtils.decimalFormat(new BigDecimal(
				"12345.915"), format));
		assertEquals("1,234,567.91", AecNumberUtils.decimalFormat(
			new BigDecimal("1234567.91"), format));
		assertEquals("0.00", AecNumberUtils.decimalFormat(new BigDecimal("0"),
			format));

		format = "###,##0";
		assertEquals("123", AecNumberUtils.decimalFormat(new BigDecimal("123"),
			format));
		assertEquals("123,456", AecNumberUtils.decimalFormat(new BigDecimal(
				"123456.9"), format));
		assertEquals("0", AecNumberUtils.decimalFormat(new BigDecimal("0"),
			format));
	}

	/**
	 * convertNullToZeroテスト.
	 */
	public void testConvertNullToZero() {
		assertEquals(new BigDecimal(0), AecNumberUtils.convertNullToZero(null));
		assertEquals(new BigDecimal(0), AecNumberUtils
				.convertNullToZero(new BigDecimal(0)));
		assertEquals(new BigDecimal(1), AecNumberUtils
				.convertNullToZero(new BigDecimal(1)));
	}
}
