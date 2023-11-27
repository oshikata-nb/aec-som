/*
 * Created on 2006/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.math.BigDecimal;
import java.util.StringTokenizer;

import com.asahikaseieng.utils.AecStringUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.validator.FieldChecks;

/**
 * Rules用Helperクラス.
 * @author jbd
 */
public final class RulesHelper extends FieldChecks {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	private RulesHelper() {
	}

	/**
	 * 値を取得する.
	 * @param bean Object
	 * @param field Field
	 * @return Value
	 */
	protected static String getValue(final Object bean, final Field field) {
		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		return value;
	}

	/**
	 * カンマ編集あり数値のフォーマットチェック.
	 * @param value Value値
	 * @return boolean
	 */
	protected static boolean checkFormt(final String value) {

		String work = value;

		// 先頭に符号があれば外す
		String[] target = new String[] {"-", "+"};
		for (int i = 0; i < target.length; i++) {
			if (StringUtils.indexOf(work, target[i]) == 0) {
				work = StringUtils.substring(work, 1);
				break;
			}
		}

		// ピリオドのチェック
		int p = StringUtils.indexOf(work, ".");
		if (0 <= p) {

			// ピリオドの後ろにカンマがある
			if (0 < StringUtils.indexOf(work, ",", p + 1)) {
				return false;
			}

			// ピリオドが１つ以上ある
			String[] priod = StringUtils.split(work, ".");
			if (2 < priod.length) {
				return false;
			}

			// StringUtils.splitの数とStringTokenizer(区切り文字も含む)の数が異なる
			if (priod.length != tokenCount(work, ".")) {
				return false;
			}

			// カンマのチェックはピリオドの前までが対象
			work = StringUtils.substring(work, 0, p);
		}

		// カンマのチェック
		String[] comma = StringUtils.split(work, ",");

		// StringUtils.splitの数とStringTokenizer(区切り文字も含む)の数が異なる
		if (comma.length != tokenCount(work, ",")) {
			return false;
		}

		// カンマの後ろは文字が３つじゃない
		final int max = 3;
		for (int i = 0; i < comma.length; i++) {
			if (i == 0) {
				// １つ目は、文字が１～３つ
				if (!(1 <= comma[i].length() && comma[i].length() <= max)) {
					return false;
				}
			} else {
				// １つめ以降は、文字が３つ
				if (comma[i].length() != max) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * トークンの数を返す<br>
	 * 備考：トークンに挟まっている文字がない場合、無視されるのを考慮.
	 * @param value Value値
	 * @param delim 区切り文字
	 * @return int トークンの数
	 */
	private static int tokenCount(final String value, final String delim) {
		int i = 0;
		if (StringUtils.isNotEmpty(value)) {
			StringTokenizer token = new StringTokenizer(value, delim, true);
			while (token.hasMoreTokens()) {
				// 区切り文字のみをカウントする
				if (delim.equals(token.nextToken())) {
					i = i + 1;
				}
			}
		}
		return i + 1;
	}

	/**
	 * 独自実装のformatInt<br>
	 * GenericTypeValidator.formatIntのラッパーメソッド<br>
	 * GenericTypeValidator.formatIntは、全角数字も数値とする為、 Rulesに引き込んだ。
	 * @param value String.
	 * @return Integer
	 */
	protected static Integer formatInt(final String value) {
		if (value == null) {
			return null;
		}

		if (!AecStringUtils.isNumeric(StringUtils.replace(value, "-", ""))) {
			return null;
		}

		try {
			return new Integer(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 独自実装のformatLong<br>
	 * GenericTypeValidator.formatLongは、全角数字も数値とする為、 Rulesに引き込んだ。
	 * @param value String.
	 * @return Long
	 */
	protected static Long formatLong(final String value) {
		if (value == null) {
			return null;
		}

		if (!AecStringUtils.isNumeric(StringUtils.replace(value, "-", ""))) {
			return null;
		}

		try {
			return new Long(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 独自実装のformatBigDecimal<br>
	 * @param value String.
	 * @return BigDecimal
	 */
	protected static BigDecimal formatBigDecimal(final String value) {
		if (value == null) {
			return null;
		}

		if (!AecStringUtils.isNumeric(StringUtils.replace(StringUtils
				.replace(value, "-", ""), ".", ""))) {
			return null;
		}

		try {
			return new BigDecimal(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
