/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;

/**
 * 文字列処理クラス<br>
 * 独自実装の文字列ユーティリティ.
 * @author jbd
 */
public final class AecTextUtils {

	/* はみ出し文字「...」 */
	private static final String STR_OVER;

	/* 「％」 */
	private static final String STR_PERCENT;

	static {
		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		STR_OVER = rb.getString("item.over.string");
		STR_PERCENT = rb.getString("item.tani.percent");
	}

	private AecTextUtils() {
	}

	/**
	 * 文字数OVER場合、短く切って「...」をつける.
	 * @param str オリジナル文字列
	 * @param shortStr 短くした文字列
	 * @return String
	 */
	public static String overFilter(final String str, final String shortStr) {

		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(shortStr)) {
			return str;
		} else if (str.equals(shortStr)) {
			/* 短い方と同じなら文字数OVERではない */
			return str;
		}

		/* 最後の一文字を「...」に変える */
		String work = StringUtils.substring(shortStr, 0, shortStr.length() - 1);
		return work + STR_OVER;
	}

	/**
	 * 文字列に「％」をつける(Like検索用).
	 * @param str 文字列
	 * @param both 両方に「％」をつける場合、true
	 * @return String
	 */
	public static String likeFilter(final String str, final boolean both) {

		if (StringUtils.isEmpty(str)) {
			return str;
		}
		if (str.equals("%25")) {
			return STR_PERCENT;
		}
		/* 「％」をつける */
		String work = str;
		if (both) {
			work = STR_PERCENT + work;
		}
		return work + STR_PERCENT;
	}

	/**
	 * 文字列に「％」をつける(Like検索用) (likeFilterをboth=false固定でCallする).
	 * @param str 文字列
	 * @return String
	 */
	public static String likeFilter(final String str) {

		return likeFilter(str, false);
	}
}
