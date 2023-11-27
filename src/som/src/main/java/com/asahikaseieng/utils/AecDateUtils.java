/*
 * Created on 2007/03/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;

/**
 * 日付処理クラス<br>
 * 独自実装の日付処理ユーティリティ.
 * @author jbd
 */
public final class AecDateUtils {

	/**
	 * コンストラクタ.
	 */
	private AecDateUtils() {
	}

	/**
	 * 現在日時を取得する.
	 * @return Date 現在日時
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 現在日時を取得する.
	 * @return Timestamp 現在日時
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(getCurrentDate().getTime());
	}

	/**
	 * Timestampをフォーマットした日付文字列にして戻す.
	 * @param timestamp Timestamp
	 * @param format フォーマット
	 * @return フォーマットした日付文字列
	 */
	public static String dateFormat(final Timestamp timestamp,
			final String format) {
		if (timestamp == null) {
			return null;
		}
		if (format == null) {
			throw new IllegalArgumentException("format is null.");
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date(timestamp.getTime()));
	}
	
	/**
	 * Timestampをフォーマットした日付文字列にして戻す.
	 * @param timestamp Timestamp
	 * @param format フォーマット
	 * @return フォーマットした日付文字列
	 */
	public static String formatStringFromTimestamp(final Timestamp timestamp, final String format) {
		if (timestamp == null) {
			return null;
		}
		if (format == null) {
			throw new IllegalArgumentException("format is null.");
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date(timestamp.getTime()));
	}

	/**
	 * 日付フォーマット文字列をTimestampに変換する<br>
	 * (String:XX99/99/99→Timestamp:9999/99/99).
	 * @param strDate String
	 * @param isYy yyかyyyyかどうかのフラグ
	 * @return Timestamp
	 */
	public static Timestamp getTimestampYmdFormat(final String strDate,
			final boolean isYy) {
		String formatStr = "yyyy/MM/dd";
		if (isYy) {
			formatStr = "yy/MM/dd";
		}
		final int monthEnd = formatStr.length();
		Timestamp timestamp = null;
		if (StringUtils.isNotEmpty(strDate) && (strDate.length() == monthEnd)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(formatStr);
				// 20050229→20050301を避ける為、「厳密に解析」を指定
				format.setLenient(false);
				Date date = format.parse(strDate);
				timestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				return null;
			}
		}
		return timestamp;
	}

	/**
	 * 日付フォーマット文字列をTimestampに変換する<br>
	 * (String:XX99/99/99→Timestamp:9999/99/99).
	 * @param strDate String
	 * @param formatStr フォーマット
	 * @return Timestamp
	 */
	public static Timestamp getTimestampYmdHmFormat(final String strDate,
			final String formatStr) {
		final int monthEnd = formatStr.length();
		Timestamp timestamp = null;
		if (StringUtils.isNotEmpty(strDate) && (strDate.length() == monthEnd)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(formatStr);
				// 20050229→20050301を避ける為、「厳密に解析」を指定
				format.setLenient(false);
				Date date = format.parse(strDate);
				timestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				return null;
			}
		}
		return timestamp;
	}

	/**
	 * 日付フォーマット文字列をTimestampに変換する<br>
	 * (yyyy/MM/ddフォーマット固定でgetTimestampYmdFormatをCall).
	 * @param strDate String
	 * @return Timestamp
	 */
	public static Timestamp getTimestampYmdFormat(final String strDate) {
		return getTimestampYmdFormat(strDate, false);
	}

	/**
	 * 日付チェック
	 * @param strDate 日付文字列
	 * @param datePattern 日付文字列の指定フォーマット 例 yyyy/MM/dd hh:mm:ss
	 * @return boolean チェック合否
	 */
	public static boolean chkDate(final String strDate, final String datePattern) {
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		boolean flg = false;
		if (StringUtils.isNotEmpty(strDate)
				&& StringUtils.isNotEmpty(datePattern)) {
			if (strDate.length() == datePattern.length()) {
				try {
					df.setLenient(false);
					df.applyPattern(datePattern); // 日付のフォーマットを設定
					df.parse(strDate);
					flg = true;
				} catch (ParseException ex) {
					return false;
				}
			}
		}
		return flg;
	}

	/**
	 * 日付フォーマットチェック
	 * @param source 対象文字列
	 * @param pattern 日付フォーマット
	 * @param flg true:文字長チェックバイパス
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws ParseException 日付フォーマットエラー時発生
	 */
	public static Date getDate(final String source, final String pattern,
			final boolean flg) throws ParseException {
		Date res = null;
		String sc = "ariehen";
		if (StringUtils.isNotEmpty(source)) {
			if (flg || (source.length() == pattern.length())) {
				sc = source;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		res = sdf.parse(sc);
		return res;
	}

	/**
	 * 日付フォーマットチェック
	 * @param source 対象文字列
	 * @param pattern 日付フォーマット
	 * @return [true:チェックOK][false:チェックNG]
	 * @throws ParseException 日付フォーマットエラー時発生
	 */
	public static Date getDate(final String source, final String pattern)
			throws ParseException {
		return getDate(source, pattern, false);
	}

	/**
	 * 日付フォーマットチェック（99=末日)
	 * @param strDate 日付
	 * @return 0:正常 1:文字配列数異常 2:文字種異常 3:時刻に変換できない
	 * @throws ParseException 時刻変換エラー
	 */
	public static int validateDate(final String strDate) throws ParseException {
		final String datePatternYmd = "yyyy/MM/dd";
		final String datePatternYm = "yyyy/MM";
		if (StringUtils.isNotEmpty(strDate)) {

			// スラッシュで分割した際に配列数が一致しない場合はエラー
			String[] splitPattern = datePatternYmd.split("/");
			String[] splitValue = strDate.split("/");
			if (splitPattern.length != splitValue.length) {
				return 1;
			}

			/* 正規表現に一致しなかったら、エラー */
			for (int i = 0; i < splitPattern.length; i++) {
				Pattern ptn = Pattern.compile("^[0-9]{"
						+ splitPattern[i].length() + "}$");
				if (!ptn.matcher(splitValue[i]).matches()) {
					return 2;
				}
			}

			// 日付を解析

			java.util.Date result = null;
			if (splitValue[2].equals("99")) {
				// 日にちが月末日(99)指定の場合
				result = getDate(strDate, datePatternYm, true);
			} else {
				result = getDate(strDate, datePatternYmd);
			}
			if (result == null) {
				/* 日付が変換できなかったら、エラーで返す */
				return 3;
			}
		}
		return 0;
	}
	
	/**
	 * 日付フォーマット文字列をTimestampに変換する<br>
	 * (String:XX99/99/99→Timestamp:9999/99/99).
	 * @param strDate String
	 * @param isYy yyかyyyyかどうかのフラグ
	 * @return Timestamp
	 */
	public static Timestamp getYmdFormatStringToTimestamp(final String strDate,final boolean isYy) {
		String formatStr = "yyyy/MM/dd";
		if (isYy) {
			formatStr = "yy/MM/dd";
		}
		final int monthEnd = formatStr.length();
		Timestamp timestamp = null;
		if (StringUtils.isNotEmpty(strDate) && (strDate.length() == monthEnd)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(formatStr);
				// 20050229→20050301を避ける為、「厳密に解析」を指定
				format.setLenient(false);
				Date date = format.parse(strDate);
				timestamp = new Timestamp(date.getTime());
			} catch (ParseException e) {
				return null;
			}
		}
		return timestamp;
	}
	
	/**
	 * 日付フォーマット文字列をTimestampに変換する<br>
	 * (yyyy/MM/ddフォーマット固定でgetTimestampYmdFormatをCall).
	 * @param strDate String
	 * @return Timestamp
	 */
	public static Timestamp convertYmdTimestampFromString(final String strDate) {
		return getYmdFormatStringToTimestamp(strDate, false);
	}
}
