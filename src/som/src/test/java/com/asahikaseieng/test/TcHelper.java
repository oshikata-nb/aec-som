/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import org.apache.commons.lang.StringUtils;

/**
 * テストケース用のヘルパーメソッドを格納.
 * @author jbd
 */
public final class TcHelper {

	/**
	 * コンストラクタ.
	 */
	private TcHelper() {
		super();
	}

	/**
	 * 指定した文字数分のStringを作る.
	 * @param num 文字数
	 * @param one 作る文字(一文字)
	 * @return 指定した文字数分のString
	 */
	public static String getString(final int num, final String one) {
		if (num <= 0 || StringUtils.isEmpty(one) || 1 < one.length()) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < num; i++) {
			buffer.append(one);
		}
		return buffer.toString();
	}

	/**
	 * 指定した文字数分のStringを作る("a"固定).
	 * @param num 文字数
	 * @return 指定した文字数分のString
	 */
	public static String getString(final int num) {
		return getString(num, "a");
	}
}
