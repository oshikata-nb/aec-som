/*
 * Created on 2007/04/30
 *
 * $Id$
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SanitizeCheckテストクラス.
 * @author jbd
 * @version $Revision$
 */
public class SanitizeCheckTest extends TestCase {

	private Log log = LogFactory.getLog(SanitizeCheckTest.class);

	/**
	 * Constructor for SanitizeCheckTest.
	 * @param st String
	 */
	public SanitizeCheckTest(final String st) {
		super(st);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		super.setUp();
		/* サニタイジングする文字を設定する */
		SanitizeCheck.setSanitaizeCharacteres("\\\",\\',\",;,(,)");
	}

	/**
	 * sanitizeCheckテストクラス.
	 */
	public final void testsanitizeParamValue() {
		String chkValue = "";

		/** 空文字 * */
		chkValue = "";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("", SanitizeCheck.sanitizeParamValue(chkValue, false));

		/** NULL文字 * */
		chkValue = null;
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertNull(SanitizeCheck.sanitizeParamValue(chkValue, false));

		/** 通常値 * */
		chkValue = "hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge", SanitizeCheck.sanitizeParamValue(chkValue, false));

		/** アポストロフィは今回変換しない * */
		chkValue = "hoge'hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge'hoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** 引用符 * */
		chkValue = "\"hogehoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("”hogehoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** バックスラッシュエスケープ・アポストロフィ * */
		chkValue = "hogehoge\\'";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hogehoge￥’", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** バックスラッシュエスケープ・引用符 * */
		chkValue = "hoge\\\"hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge￥”hoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** 開始、終了括弧 * */
		chkValue = "hoge()hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge（）hoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** セミコロン * */
		chkValue = "hogehoge;";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hogehoge；", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** 数値パラメータXYZ * */
		// chkValue = "XYZhogehoge";
		// log.debug("Check-Target-Value[" + chkValue + "]");
		// assertEquals("hogehoge", SanitizeCheck.sanitizeParamValue(chkValue,
		// false));
		/** 数値パラメータXYZ + 引用符 * */
		// chkValue = "XYZhogehoge\"";
		// log.debug("Check-Target-Value[" + chkValue + "]");
		// assertEquals("hogehoge", SanitizeCheck.sanitizeParamValue(chkValue,
		// false));
		/** セミコロン + 終了括弧 * */
		chkValue = "hoge;)hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge；）hoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** すべて＜',",\',\",),;,XYZ＞ * */
		chkValue = "\\\"\\''\");";
		// chkValue = "\\\"\\''\");XYZ";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("￥”￥’'”）；", SanitizeCheck.sanitizeParamValue(chkValue,
			false));

		/** 通常値（\） * */
		chkValue = "hoge\\hoge";
		log.debug("Check-Target-Value[" + chkValue + "]");
		assertEquals("hoge\\hoge", SanitizeCheck.sanitizeParamValue(chkValue,
			false));
	}

	/**
	 * nullCheckテスト.
	 */
	public final void testNullCheck() {
		String result = "";

		// 空文字チェック

		result = SanitizeCheck.sanitizeNullParamValue("");
		assertEquals("", result);

		// NULLチェック

		result = SanitizeCheck.sanitizeNullParamValue(null);

		assertEquals(null, result);

		// NULL(%00)チェック
		assertEquals("hohohohohgegegegegeg", SanitizeCheck
				.sanitizeNullParamValue("hohohohoh%00gegegegegeg"));

		assertEquals("hohohohohgegegegegeg", SanitizeCheck
				.sanitizeNullParamValue("hohohohoh%00gegege%00gegeg"));

		// 問題ないためスルー

		result = SanitizeCheck.sanitizeNullParamValue("hohohohohgegegegegeg");
		assertEquals("hohohohohgegegegegeg", result);
	}

}
