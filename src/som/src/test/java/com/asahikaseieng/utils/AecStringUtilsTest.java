/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.util.ResourceBundle;

import junit.framework.TestCase;

import com.asahikaseieng.Constants;

/**
 * 独自実装StringUtilsテストケース.
 * @author jbd
 */
public final class AecStringUtilsTest extends TestCase {

	/**
	 * コンストラクタ.
	 * 
	 * @param testname テスト名
	 */
	public AecStringUtilsTest(final String testname) {
		super(testname);
	}

	/**
	 * isNumericのテスト.
	 */
	public void testIsNumeric() {
		assertEquals(true, AecStringUtils.isNumeric("12345"));
		assertEquals(true, AecStringUtils
				.isNumeric("1234567890123456789012345678901234567890"));
		assertEquals(false, AecStringUtils.isNumeric("12san"));
		assertEquals(false, AecStringUtils.isNumeric("１２235"));
		assertEquals(false, AecStringUtils.isNumeric("１２３４５"));
		assertEquals(false, AecStringUtils.isNumeric("12さん"));
		assertEquals(true, AecStringUtils.isNumeric(""));
		assertEquals(true, AecStringUtils.isNumeric(null));
	}

	/**
	 * isAlphabetのテスト.
	 */
	public void testIsAlphabet() {
		assertTrue(AecStringUtils.isAlphabet(""));
		assertTrue(AecStringUtils.isAlphabet(null));
		assertTrue(AecStringUtils.isAlphabet("abcABC"));
		assertFalse(AecStringUtils.isAlphabet("abc0"));
		assertFalse(AecStringUtils.isAlphabet("あ"));
		assertFalse(AecStringUtils.isAlphabet("aaaAAA;"));
	}

	/**
	 * isAlphanumericのテスト.
	 */
	public void testIsAlphanumeric() {
		assertTrue(AecStringUtils.isAlphanumeric(""));
		assertTrue(AecStringUtils.isAlphanumeric(null));
		assertTrue(AecStringUtils.isAlphanumeric("0123abcABC"));
		assertFalse(AecStringUtils.isAlphanumeric("0123 "));
		assertFalse(AecStringUtils.isAlphanumeric("あ"));
		assertFalse(AecStringUtils.isAlphanumeric("aaaAAA;"));
	}

	/**
	 * replaceInvalidCharactersのテスト.
	 */
	public void testReplaceInvalidCharacters() {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;
		assertEquals(rb.getString("valid.character"), AecStringUtils
				.replaceInvalidCharacters(rb.getString("invalid.character")));

		assertNull(AecStringUtils.replaceInvalidCharacters(null));
		assertEquals("", AecStringUtils.replaceInvalidCharacters(""));
	}

	/**
	 * halfToFullAsciiのテスト.
	 */
	public void testHalfToFullAscii() {
		assertNull(AecStringUtils.halfToFullAscii(null));
		assertEquals("", AecStringUtils.halfToFullAscii(""));
		assertEquals("１２３４５６７８９０", AecStringUtils.halfToFullAscii("1234567890"));
		assertEquals("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ", AecStringUtils
				.halfToFullAscii("abcdefghijklmnopqrstuvwxyz"));
		assertEquals("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ＿",
				AecStringUtils.halfToFullAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZ_"));
		assertEquals("ａｂｃほげ０１２", AecStringUtils.halfToFullAscii("abcほげ012"));
		assertEquals("ａｂｃ＠＋＊ほげ０１２", AecStringUtils
				.halfToFullAscii("abc@+*ほげ012"));
	}
}
