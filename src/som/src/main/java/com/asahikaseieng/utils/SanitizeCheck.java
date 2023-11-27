/*
 * Created on 2007/04/30
 *
 * $Id$
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;

/**
 * 特定の文字をサニタイズするクラス.
 * @author jbd
 */
public final class SanitizeCheck {

	private static final String ENCODING = "Windows-31J";

	private static final String NULL_PARAMETER = "%00";

	/* サニタイジング文字列のデフォルトはプロパティより取得する */
	private static String sanitaizeCharacteres = Constants.RB_SYSTEM_PROPERTIES
			.getString("system.sanitize.character");

	private static List sanitaizeList;

	static {
		sanitaizeList = Arrays.asList(StringUtils.split(sanitaizeCharacteres,
			","));
	}

	/**
	 * コンストラクタ.
	 */
	private SanitizeCheck() {
	}

	/**
	 * サニタイジング対象文字列設定.
	 * @param sanitaizeCharacteres 設定するsanitaizeCharacteres。
	 */
	public static synchronized void setSanitaizeCharacteres(
			final String sanitaizeCharacteres) {
		SanitizeCheck.sanitaizeCharacteres = sanitaizeCharacteres;

		sanitaizeList = Arrays.asList(StringUtils.split(sanitaizeCharacteres,
			","));
	}

	/**
	 * 文字列より特定の文字をサニタイズする。
	 * @param val String
	 * @param isSanitize 対象文字を削除するかどうか (falseの場合は全角文字に変換する)
	 * @return result
	 */
	public static String sanitizeParamValue(final String val,
			final boolean isSanitize) {

		if (!StringUtils.isNotEmpty(val)) {
			return val;
		}

		String result = val;
		for (Iterator ite = sanitaizeList.iterator(); ite.hasNext();) {
			String sanitize = (String) ite.next();

			if (isSanitize) {
				result = StringUtils.replace(result, sanitize, "");
			} else {
				result = StringUtils.replace(result, sanitize, AecStringUtils
						.halfToFullAscii(sanitize));
			}

		}
		return result;
	}

	/**
	 * NULL文字が見つかったら削除する。
	 * @param val String
	 * @return String
	 */
	public static String sanitizeNullParamValue(final String val) {

		if (StringUtils.isEmpty(val)) {
			return val;
		}

		// NULL文字を除去する
		// パフォーマンスとのトレードオフがある
		// 問題がある場合は下記のロジックの必要有無を含め議論が必要
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < val.length(); i++) {
				byte[] b = ("" + val.charAt(i)).getBytes(ENCODING);
				if (!(b.length == 1 && b[0] == 0)) {
					sb.append("" + val.charAt(i));
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		// %00表記を除去する
		if (-1 < sb.toString().indexOf(NULL_PARAMETER)) {
			return StringUtils.replace(sb.toString(), NULL_PARAMETER, "");
		}

		return sb.toString();
	}
}
