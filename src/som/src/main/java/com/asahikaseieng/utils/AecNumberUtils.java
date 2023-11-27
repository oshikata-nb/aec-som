/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 独自実装の数字処理ユーティリティ.
 * @author jbd
 */
public final class AecNumberUtils {

	/**
	 * コンストラクタ.
	 */
	private AecNumberUtils() {
	}

	/**
	 * 解説：String→BigDecimalに変換する<br>
	 * 備考：Stringがnullの場合はnullを戻します。
	 * @param str String
	 * @return BigDecimal
	 */
	public static BigDecimal convertBigDecimal(final String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String num = StringUtils.replace(str, ",", "");
		BigDecimal res = null;
		try {
			res = new BigDecimal(num);
		} catch (NumberFormatException nfe) {
			// 有効な数値文字列でない場合
			res = null;
		}
		return res;
		// if (!NumberUtils.isNumber(num)) {
		// return null;
		// }
		// return new BigDecimal(num);
	}

	/**
	 * BigDecimalを指定のDecimalFormatに編集した文字列に変換する<br>
	 * 備考：指定フォーマットに沿わない小数点以下の値は切捨てる.
	 * @param value BigDecimal
	 * @param format String
	 * @return String
	 */
	public static String decimalFormat(final BigDecimal value,
			final String format) {
		if (StringUtils.isEmpty(format)) {
			throw new IllegalArgumentException(
					"decimalFormat : format is null;");
		}
		if (value == null) {
			return null;
		}
		DecimalFormat decFormat = new DecimalFormat(format);

		BigDecimal dec = new BigDecimal(value.toString());

		/* 指定フォーマットより小数点以下の値が多ければ、切捨てを行う */
		int digit = decFormat.getMaximumFractionDigits();
		if (digit < dec.scale()) {
			dec = dec.divide(BigDecimal.ONE, digit, BigDecimal.ROUND_DOWN);
		}

		return decFormat.format(dec);
	}

	/**
	 * BigDecimalを指定のDecimalFormatに編集した文字列に変換する<br>
	 * @param value BigDecimal
	 * @return String
	 */
	public static String decimalFormatEx(final BigDecimal value) {
		if (value == null) {
			return null;
		}

		String format = "#,##0";
		String decCnt = "";
		BigDecimal dec = new BigDecimal(value.toString());

		for (int i = 0; i < dec.scale(); i++) {
			decCnt += "0";
		}

		if (!StringUtils.isEmpty(decCnt)) {
			format = format + "." + decCnt;
		}

		DecimalFormat decFormat = new DecimalFormat(format);

		return decFormat.format(dec);
	}

	/**
	 * Nullを０に変換する.
	 * @param val BigDecimal
	 * @return val or 0
	 */
	public static BigDecimal convertNullToZero(final BigDecimal val) {
		if (val != null) {
			return val;
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * Nullを０に変換する.
	 * 
	 * @param val
	 *            BigDecimal
	 * @return val or 0
	 */
	public static String convertStringNullToOneFromBigDecimal(final BigDecimal val) {
		if (val != null) {
			return val.toString();
		}
		return BigDecimal.ONE.toString();
	}

	/**
	 * 解説：String→BigDecimalに変換する<br>
	 * 備考：Stringがnullの場合はnullを戻します。
	 * @param str String
	 * @return BigDecimal
	 */
	public static BigDecimal convertBigDecimalFromString(final String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		String num = StringUtils.replace(str, ",", "");
		BigDecimal res = null;
		try {
			res = new BigDecimal(num);
		} catch (NumberFormatException nfe) {
			// 有効な数値文字列でない場合
			res = null;
		}
		return res;
	}
	
	/**
	 * Nullを０に変換する.
	 * 
	 * @param val
	 *            BigDecimal
	 * @return val or 0
	 */
	public static String convertStringNullToZeroFromBigDecimal(final BigDecimal val) {
		if (val != null) {
			return val.toString();
		}
		return BigDecimal.ZERO.toString();
	}
	
	/**
	 * Nullを０に変換する.
	 * @param val BigDecimal
	 * @return val or 0
	 */
	public static BigDecimal convertBigDecimalNullToZeroFromBigDecimal(final BigDecimal val) {
		if (val != null) {
			return val;
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * 解説：String→BigDecimalに変換する<br>
	 * 備考：Stringがnullの場合はゼロを戻します。
	 * 
	 * @param str
	 *            String
	 * @return BigDecimal
	 */
	public static BigDecimal convertBigDecimalNullToZeroFromString(final String str) {
		if (StringUtils.isEmpty(str)) {
			return BigDecimal.ZERO;
		}
		// 文字コードが%2C[,]が来る場合がある為ここで文字コードでも置換を行う
		String num = StringUtils.replace(str, "%2C", "");
		num = StringUtils.replace(num, ",", "");
		BigDecimal res = null;
		try {
			res = new BigDecimal(num);
		} catch (NumberFormatException nfe) {
			// 有効な数値文字列でない場合
			res = BigDecimal.ZERO;
		}
		return res;
	}
	
	/**
	 * bigDecimal型がゼロかチェックする nullの場合はゼロとする
	 */
	public static boolean isBigDecimalZero(BigDecimal value) {
		if (value == null || value.compareTo(BigDecimal.ZERO) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 値が一致する場合、TRUEを返す
	 * その以外はFALSE
	 * @param cmp1
	 * @param cmp2
	 * @return
	 */
	public static boolean compareToEqual(final BigDecimal cmp1, final BigDecimal cmp2){
		if (cmp1 == null || cmp2 == null) {
			return false;		//不一致
		}
		else if (cmp1.compareTo(cmp2) != 0){
			return false;		//不一致
		}
		else {
			return true;		//一致
		}
	}
	
	/**
	 * 解説：BigDecimal→Stringに変換する<br>
	 * 備考：BigDecimalがnullの場合はnullを戻します。
	 * @param str String
	 * @return BigDecimal
	 */
	public static String convertString(final BigDecimal val) {
		if (val != null) {
			return val.toString();
		}
		return null;
	}
}
