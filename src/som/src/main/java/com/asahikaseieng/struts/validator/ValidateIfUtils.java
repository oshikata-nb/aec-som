/*
 * Created on 2006/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import com.asahikaseieng.Constants;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.util.ValidatorUtils;

/**
 * Rulesの～if系のルール用ユーティリティ.
 * @author jbd
 */
public final class ValidateIfUtils {

	/**
	 * コンストラクタ.
	 */
	private ValidateIfUtils() {
	}

	/**
	 * 入力チェックを行うかの判断を返す。<br>
	 * (for validateXxxxxIf method.).
	 * @param field Field
	 * @param validator Validator
	 * @return boolean (true if meets stated requirements, false otherwise.)
	 */
	public static boolean validateIfCheck(final Field field,
			final org.apache.commons.validator.Validator validator) {

		Object form = validator
				.getParameterValue(org.apache.commons.validator.Validator.BEAN_PARAM);

		boolean required = false;

		int i = 0;
		String fieldJoin = "AND";
		if (!GenericValidator.isBlankOrNull(field.getVarValue("fieldJoin"))) {
			fieldJoin = field.getVarValue("fieldJoin");
		}

		if (fieldJoin.equalsIgnoreCase("AND")) {
			required = true;
		}

		while (!GenericValidator.isBlankOrNull(field.getVarValue("field[" + i
				+ "]"))) {
			String dependProp = field.getVarValue("field[" + i + "]");
			String dependTest = field.getVarValue("fieldTest[" + i + "]");
			String dependTestValue = field.getVarValue("fieldValue[" + i + "]");
			String dependIndexed = field.getVarValue("fieldIndexed[" + i + "]");

			/* コンボボックスの未選択値の場合 */
			if (Rules.UNSELECTED.equals(dependTestValue)) {
				dependTestValue = Constants.UNSELECTED_VALUE;
			}

			if (dependIndexed == null) {
				dependIndexed = "false";
			}

			String dependVal = null;
			boolean thisRequired = false;
			if (field.isIndexed() && dependIndexed.equalsIgnoreCase("true")) {
				String key = field.getKey();
				if ((-1 < key.indexOf("[")) && (-1 < key.indexOf("]"))) {
					String ind = key.substring(0, key.indexOf(".") + 1);
					dependProp = ind + dependProp;
				}
			}

			dependVal = ValidatorUtils.getValueAsString(form, dependProp);
			if (dependTest.equals(Rules.FIELD_TEST_NULL)) {
				if ((dependVal != null) && (0 < dependVal.length())) {
					thisRequired = false;
				} else {
					thisRequired = true;
				}
			}

			if (dependTest.equals(Rules.FIELD_TEST_NOTNULL)) {
				if ((dependVal != null) && (0 < dependVal.length())) {
					thisRequired = true;
				} else {
					thisRequired = false;
				}
			}

			if (dependTest.equals(Rules.FIELD_TEST_EQUAL)) {
				thisRequired = dependTestValue.equalsIgnoreCase(dependVal);
			}

			if (dependTest.equals(Rules.FIELD_TEST_NOTEQUAL)) {
				thisRequired = !dependTestValue.equalsIgnoreCase(dependVal);
			}

			if (fieldJoin.equalsIgnoreCase("AND")) {
				required = required && thisRequired;
			} else {
				required = required || thisRequired;
			}

			i++;
		}

		return required;
	}
}
