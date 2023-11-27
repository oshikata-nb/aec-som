/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.util.ResourceBundle;

import com.asahikaseieng.Constants;
import junit.framework.TestCase;

/**
 * 独自実装TextUtilsテストケース.
 * @author jbd
 */
public final class AecTextUtilsTest extends TestCase {

	/** はみ出し文字「...」 */
	private static final String STR_OVER;

	/** 「％」 */
	private static final String STR_PERCENT;

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		STR_OVER = rb.getString("item.over.string");
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	/**
	 * コンストラクタ.
	 * 
	 * @param testname テスト名
	 */
	public AecTextUtilsTest(final String testname) {
		super(testname);
	}

	/**
	 * overFilterのテスト.
	 */
	public void testOverFilter() {
		assertEquals("", AecTextUtils.overFilter("", ""));
		assertEquals(null, AecTextUtils.overFilter(null, ""));
		assertEquals("12345", AecTextUtils.overFilter("12345", "12345"));
		assertEquals("1234" + STR_OVER, AecTextUtils.overFilter("123456",
			"12345"));
		assertEquals("１２３４５", AecTextUtils.overFilter("１２３４５", "１２３４５"));
		assertEquals("１２３４" + STR_OVER, AecTextUtils.overFilter("１２３４５６",
			"１２３４５"));
	}

	/**
	 * likeFilterのテスト.
	 */
	public void testLikeFilter() {
		assertEquals("", AecTextUtils.likeFilter("", true));
		assertEquals(null, AecTextUtils.likeFilter(null, true));
		assertEquals(STR_PERCENT + "a" + STR_PERCENT, AecTextUtils.likeFilter(
			"a", true));
		assertEquals("a" + STR_PERCENT, AecTextUtils.likeFilter("a", false));
		assertEquals(STR_PERCENT + "ああ" + STR_PERCENT, AecTextUtils.likeFilter(
			"ああ", true));
		assertEquals("ああ" + STR_PERCENT, AecTextUtils.likeFilter("ああ", false));
	}

	/**
	 * likeFilterのテスト.
	 */
	public void testLikeFilter2() {
		assertEquals("", AecTextUtils.likeFilter(""));
		assertEquals(null, AecTextUtils.likeFilter(null));
		assertEquals("a" + STR_PERCENT, AecTextUtils.likeFilter("a"));
		assertEquals("ああ" + STR_PERCENT, AecTextUtils.likeFilter("ああ"));
	}

}
