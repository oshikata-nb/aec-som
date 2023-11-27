/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;

import com.asahikaseieng.Constants;

/**
 * 文字列処理クラス<br>
 * 独自実装の文字列ユーティリティ.
 * @author jbd
 */
public final class AecStringUtils {

	private AecStringUtils() {
	}

	/* 小文字リスト */
	private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

	/* 大文字リスト */
	private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/* 数字リスト */
	private static final String NUMBER_LIST = "0123456789";

	private static final String NUM_NUMERIC = "-0123456789,";

	/* 小文字・大文字リスト */
	private static final String ALPHABET_LIST = LOWER_CASE + UPPER_CASE;

	/* 半角リスト(全角リストと対応) */
	private static final String HALF_ASCII = "!\"#$%&'()*+,-./"
			+ "0123456789:;<=>?@" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "[\\]^_`"
			+ "abcdefghijklmnopqrstuvwxyz{}";

	/* 全角リスト(半角リストと対応) */
	private static final String FULL_ASCII = "！”＃＄％＆’（）＊＋，－．／"
			+ "０１２３４５６７８９：；＜＝＞？＠" + "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" + "［￥］＾＿‘"
			+ "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ｛｝";

	/**
	 * 数字判断<br>
	 * 引数の文字列が全て数字かどうか判定します。<br>
	 * NumberUtils.isNumericは、「数字を表す文字(列)」もOKとなるので、 0～9の固定文字で判断する。
	 * @param str 判定対象文字列
	 * @return 数字ならtrue
	 */
	public static boolean isNumeric(final String str) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (NUMBER_LIST.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 数字判断:正数値:追加:","<br>
	 * 引数の文字列が全て数字かどうか判定します。<br>
	 * NumberUtils.isNumericは、「数字を表す文字(列)」もOKとなるので、 0～9の固定文字で判断する。
	 * @param str 判定対象文字列
	 * @return 数字ならtrue
	 */
	public static boolean isNumNumeric(final String str) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (NUM_NUMERIC.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 半角アルファベット判断<br>
	 * 引数の文字列が全て半角アルファベットかどうか判定します。
	 * @param str 判定対象文字列
	 * @return 半角アルファベットならtrue
	 */
	public static boolean isAlphabet(final String str) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}
		for (int i = 0; i < str.length(); i++) {
			if (ALPHABET_LIST.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	private static final String ALPHANUMERIC_LIST = NUMBER_LIST + LOWER_CASE
			+ UPPER_CASE;

	/**
	 * 半角英数字判断<br>
	 * 引数の文字列が全て半角英数字かどうか判定します。
	 * @param str 判定対象文字列
	 * @return 半角英数字ならtrue
	 */
	public static boolean isAlphanumeric(final String str) {

		if (StringUtils.isEmpty(str)) {
			return true;
		}

		for (int i = 0; i < str.length(); i++) {
			if (ALPHANUMERIC_LIST.indexOf(str.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 機種依存文字を読める文字に変換する。
	 * @param str 置換対象文字列
	 * @return 置換後の文字列
	 */
	public static String replaceInvalidCharacters(final String str) {
		if (str == null) {
			return null;
		}

		if (str.equals("")) {
			return "";
		}

		ResourceBundle rb = Constants.RB_APPLICATION_PROPERTIES;

		String[] invalidCharacters = StringUtils.split(rb
				.getString("invalid.character"), ",");
		String[] validCharacters = StringUtils.split(rb
				.getString("valid.character"), ",");
		String retStr = str;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			for (int j = 0; j < invalidCharacters.length; j++) {
				if (String.valueOf(c).equals(invalidCharacters[j])) {
					retStr = StringUtils.replaceOnce(retStr,
						invalidCharacters[j], validCharacters[j]);
					break;
				}
			}
		}

		return retStr;
	}

	/**
	 * 半角→全角変換<br>
	 * 半角のASCIIを全角に変換します。
	 * @param str 変換対象文字列
	 * @return 全角変換文字列
	 */
	public static String halfToFullAscii(final String str) {
		if (str == null) {
			return null;
		}

		if (str.equals("")) {
			return "";
		}

		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (HALF_ASCII.indexOf(c) != -1) {
				sb.setCharAt(i, FULL_ASCII.charAt(HALF_ASCII.indexOf(c)));
			}
		}

		return sb.toString();
	}

	/**
	 * 指定サイズの文字列に編集する<br>
	 * 対象文字列＞指定サイズ 指定サイズに文字列をカットして返す<br>
	 * 対象文字列＜指定サイズ 後ろに指定サイズまでスペースを詰めて返す<br>
	 * 対象文字列＝指定サイズ そのまま返す
	 * @param str 対象文字列
	 * @param size 指定サイズ
	 * @return 指定サイズに編集した文字列
	 */
	public static String editSpecifiedSize(final String str, final int size) {
		String buf = null;
		if (StringUtils.isEmpty(str)) {
			buf = AecStringUtils.rightPad(str, size);
		} else {
			int editSize = str.getBytes().length - size;
			if (editSize <= 0) {
				buf = AecStringUtils.rightPad(str, size);
			} else {
				buf = StringUtils.substring(str, 0, size);
			}
		}
		return buf;
	}

	/**
	 * 文字列の右側にスペースを詰める
	 * @param str 対象文字列
	 * @param size 指定サイズ
	 * @return 指定サイズまでスペースを詰めした文字列
	 */
	public static String rightPad(final String str, final int size) {
		String buf = str;

		if (StringUtils.isEmpty(str)) {
			char[] ss = new char[size];
			Arrays.fill(ss, ' ');
			buf = new String(ss);
		} else {
			int pads = size - str.getBytes().length;
			if (pads > 0) {
				char[] ss = new char[size - str.getBytes().length];
				Arrays.fill(ss, ' ');
				buf = str + new String(ss);
			}
		}
		return buf;
	}

	/**
	 * ゼロ詰めした文字列を取得する 数値が指定した文字列長より長い場合は、数値を文字列に変換した物を返す。 指定した長さで切ったりはしない
	 * @param value 変換対象数値(long)
	 * @param length 文字列長
	 * @return ゼロ詰めした文字列
	 */
	public static String zeroPadding(final long value, final int length) {
		String res = null;
		if (length > 0) {
			String pattern = StringUtils.repeat("0", length);
			DecimalFormat df = new DecimalFormat(pattern);
			res = df.format(value);
		}
		return res;
	}

	/**
	 * ゼロ詰めした文字列を取得する 数値が指定した文字列長より長い場合は、数値を文字列に変換した物を返す。 指定した長さで切ったりはしない
	 * @param value 変換対象数値(BigDecimal)
	 * @param length 文字列長
	 * @return ゼロ詰めした文字列
	 */
	public static String zeroPadding(final BigDecimal value, final int length) {
		String res = null;
		if (value != null) {
			res = zeroPadding(value.longValue(), length);
		}
		return res;
	}

	/**
	 * BigDecimalを指定した小数点位置、丸めモードで丸めた後、数値文字列で取得する
	 * @param value 対象BigDecimal
	 * @param decimalPoint 小数点位置
	 * @param round 丸めモード
	 * @return 数値文字列（カンマ編集)
	 */
	public static String formatNumber(final BigDecimal value,
			final int decimalPoint, final RoundingMode round) {
		String res = null;
		if (value != null) {
			RoundingMode roundingMode = round;
			if (round == null) {
				roundingMode = RoundingMode.HALF_UP;
			}
			// 丸め
			BigDecimal buf = value.setScale(decimalPoint, roundingMode);
			String pattern = getPattern(decimalPoint);
			// 文字列にフォーマット
			DecimalFormat df = new DecimalFormat(pattern);
			res = df.format(buf);
		}

		return res;
	}

	/**
	 * BigDecimalを指定した小数点位置、丸めモードで丸めた後、数値文字列で取得する
	 * @param value 対象BigDecimal
	 * @param decimalPoint 小数点位置
	 * @param round 丸めモード
	 * @return 数値文字列（カンマ編集無し)
	 */
	public static String format(final BigDecimal value, final int decimalPoint,
			final RoundingMode round) {
		String res = null;
		if (value != null) {
			RoundingMode roundingMode = round;
			if (round == null) {
				roundingMode = RoundingMode.HALF_UP;
			}
			// 丸め
			BigDecimal buf = value.setScale(decimalPoint, roundingMode);
			String pattern = getPattern(decimalPoint, "##0.");
			// 文字列にフォーマット
			DecimalFormat df = new DecimalFormat(pattern);
			res = df.format(buf);
		}

		return res;
	}

	/** 端数区分－0:小数部なし 数値フォーマット文字列 */
	private static final String DECIMAL_POINT_FORMAT_BASE = "#,##0.";

	/**
	 * 小数点フォーマット文字列を作成
	 * @param decimalPoint 小数点位置(0～)
	 * @return 小数点フォーマット文字列
	 */
	private static String getPattern(final int decimalPoint) {
		return getPattern(decimalPoint, "#,##0");
	}

	/**
	 * 小数点フォーマット文字列を作成
	 * @param decimalPoint 小数点位置(0～)
	 * @return 小数点フォーマット文字列
	 */
	private static String getPattern(final int decimalPoint, final String prifix) {
		String res = prifix;
		if (decimalPoint > 0) {
			StringBuilder buf = new StringBuilder(DECIMAL_POINT_FORMAT_BASE);
			for (int i = 0; i < decimalPoint; i++) {
				buf.append("0");
			}
			res = buf.toString();
		}
		return res;
	}

	/**
	 * 半角（半角英数記号、半角ｶﾅ）か判断する
	 * @param c 対象char
	 * @return 半角（半角英数記号、半角ｶﾅ）の場合 true
	 */
	public static boolean isHankaku(final char c) {
		boolean res = false;
		if (('\u0020' <= c && c <= '\u007e') || // 半角英数記号
				('\uff61' <= c && c <= '\uff9f')) { // 半角カタカナ
			res = true;
		}
		return res;
	}

	/**
	 * 半角・全角を判断して、指定文字数内で 文字のを切り取る
	 * @param source 対象文字列
	 * @param len 指定バイト数
	 * @return 指定文字数内で切り取った文字列
	 */
	public static String split(final String source, final int len) {
		String res = source;
		if (StringUtils.isNotEmpty(source)) {
			int num = source.length();
			StringBuilder buf = new StringBuilder();
			int length = 0;
			for (int i = 0; i < num; i++) {
				char c = source.charAt(i);
				if (isHankaku(c)) {
					// 半角
					length++;
				} else {
					// 全角
					length += 2;
				}
				if (length <= len) {
					buf.append(c);
				} else {
					break;
				}
			}
			res = buf.toString();
		}
		return res;
	}

	/**
	 * SOMのＬＯＴ番号規定に合うかどうか判定する。
	 * @param str ロット番号文字列
	 * @return 判定 適合ならTrue
	 */
	public static boolean isSomLot(final String str) {
		Pattern pattern = Pattern.compile("^[*$%.\\- _0-9A-Za-z@]+$");
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}
	
	/**
	 * 文字列でNullではなくかつ空欄でもない
	 * 
	 * @param str
	 * @return true:Nullでも空欄でもない false:nullかもしくは空欄である
	 */
	public static boolean isNotNullAndEmpty(final String str) {

		if (str == null || str.equals("")) {
			// Nullまたは空欄である
			return Boolean.FALSE;
		} else {
			// Nullでも空欄でもない
			return Boolean.TRUE;
		}
	}
	
	/**
	 * 画面入力された項目が必須である為、入力されているかチェックを行いエラーの場合、titleNameを入力してくださいとメッセージを返す
	 * @param checkValue チェックする数値
	 * @param titleName メッセージの名称
	 * @return
	 */
	public static ActionMessage isRequire(final String checkValue,final String titleName){
		ActionMessage message = null;
		
		if(!isNotNullAndEmpty(checkValue)){
			// 空白の場合、メッセージを返す
			message =   new ActionMessage("M00051", titleName);
		}
		return message;
	}
	
	/**
	 * dbから取得した文字列情報がNULL等が入っていた場合、NULL画面に表示される。この場合nullをから文字にするように変換
	 * @param checkValue チェックする数値
	 * @param titleName メッセージの名称
	 * @return
	 */
	public static String changeNull(final String changeValue){
		
		if(changeValue == null || changeValue.equals("null") || changeValue.equals("Null") || changeValue.equals("NULL") ){
			// NULL文字の場合、空白を返す
			return "";
		}
		return changeValue;
	}
	
	/**
	 * dbから取得した文字列情報がNULL等が入っていた場合、NULL画面に表示される。この場合nullをから文字にするように変換
	 * @param checkValue チェックする数値
	 * @param titleName メッセージの名称
	 * @return
	 */
	public static String changeNullExist_(final String changeValue){
		
		if(changeValue == null || changeValue.equals("null") || changeValue.equals("Null") || changeValue.equals("NULL") ){
			// NULL文字の場合、空白を返す
			return "";
		}
		return changeValue + "_";
	}	
}
