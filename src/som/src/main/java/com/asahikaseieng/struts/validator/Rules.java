/*
 * Created on 2006/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.utils.AecStringUtils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericTypeValidator;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.FieldChecks;
import org.apache.struts.validator.Resources;

/**
 * validator用のルール.
 * @author jbd
 */
public final class Rules extends FieldChecks {

	private static final long serialVersionUID = 1L;

	/* Log */
	private static Log log = LogFactory.getLog(Rules.class);

	/** requiredifで使う */
	public static final String FIELD_TEST_NOTEQUAL = "NOTEQUAL";

	/** selectedifで使う */
	public static final String UNSELECTED = "UNSELECTED";

	private static final String EQUAL = "EQUAL";

	private static final String NOTEQUAL = "NOTEQUAL";

	private static final String GREATERTHAN = "GREATERTHAN";

	private static final String GREATEREQUAL = "GREATEREQUAL";

	private static final String LESSTHAN = "LESSTHAN";

	private static final String LESSEQUAL = "LESSEQUAL";

	/**
	 * コンストラクタ.
	 */
	private Rules() {
	}

	/* ==================== custom(FieldChecksの既存メソッド) ==================== */

	/**
	 * 数値チェック<br>
	 * FieldChecks.validateIntegerは、<BR>
	 * 大文字も数値とする・Integerで扱える桁数の限度がある為、<BR>
	 * Rulesに引き込んだ。
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateInteger(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		if (GenericValidator.isBlankOrNull(value)) {
			return Boolean.TRUE;
		}

		Object result = RulesHelper.formatInt(value);

		if (result == null) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
				field));
		}

		if (result == null) {
			return Boolean.FALSE;
		}
		return result;
	}

	/**
	 * 数値チェック(Long)<br>
	 * FieldChecks.validateLongは、大文字も数値とする為、Rulesに引き込んだ。
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateLong(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		if (GenericValidator.isBlankOrNull(value)) {
			return Boolean.TRUE;
		}

		Object result = RulesHelper.formatLong(value);

		if (result == null) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
				field));
		}

		if (result == null) {
			return Boolean.FALSE;
		}
		return result;
	}

	/* ==================== custom ==================== */

	/**
	 * 数値チェック<br>
	 * FieldChecks.validateIntegerは、大文字も数値とする・Integerで扱える桁数の限度がある為、Rulesに引き込んだ。
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateDecimal(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		if (GenericValidator.isBlankOrNull(value)) {
			return false;
		}

		String point = field.getVarValue("point");
		if (StringUtils.isEmpty(point)) {
			throw new RuntimeException(
					"specified method should have priod parameter.");
		}

		// カンマを削除(この業務特有)
		value = StringUtils.replace(value, ",", "");

		try {
			BigDecimal dec = new BigDecimal(value);
			if (Integer.parseInt(point) < dec.scale()) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
				return false;
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException(
					"specified method should have Numeric value.");
		}
		return true;
	}

	/**
	 * 文字チェック<br>
	 * 指定したクラスの指定したメソッドにて妥当性の検査を行う。<br>
	 * 但し、呼び出されるメソッドはstaticメソッドである事、<br>
	 * 返り値はbooleanで引数はString一つであることが前提です。<br>
	 * また、isReverseにtrueを指定することで値の判定を行った<br>
	 * 結果を反転（falseならtrue,trueならfalse）で返します。<br>
	 * 未指定の場合はisReverse=falseとして処理します。<br>
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateString(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		String className = field.getVarValue("targetClass");
		String methodName = field.getVarValue("targetMethod");
		boolean isReverse = false;
		if (StringUtils.isNotEmpty(field.getVarValue("isReverse"))) {
			isReverse = Boolean.valueOf(field.getVarValue("isReverse"))
					.booleanValue();
		}

		if (!GenericValidator.isBlankOrNull(value)) {
			try {
				Class cls = Class.forName(className);

				Method method = cls.getMethod(methodName,
					new Class[] {String.class});

				// パラメータのタイプチェック
				Class[] params = method.getParameterTypes();
				if (params.length != 1) {
					throw new RuntimeException(
							"specified method should have only one String parameter");
				}
				if (!params[0].equals(String.class)) {
					throw new RuntimeException(
							"specified method should have only one String parameter");
				}
				// 戻り値のチェック
				Class returnType = method.getReturnType();
				if (!returnType.isPrimitive()) {
					throw new RuntimeException(
							"returntype of specified method should be primitive boolean");
				}
				if (!returnType.getName().equals("boolean")) {
					throw new RuntimeException(
							"returntype of specified method should be boolean");
				}

				Object ret = method.invoke(cls, new Object[] {value});

				if (!((Boolean) ret).booleanValue() && !isReverse
						|| ((Boolean) ret).booleanValue() && isReverse) {

					errors.add(field.getKey(), Resources.getActionMessage(
						request, va, field));

					return false;
				}
			} catch (SecurityException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	/**
	 * 英字チェック.
	 * @param bean Object.
	 * @param va ValidatorAction.
	 * @param field Field.
	 * @param errors ActionMessages.
	 * @param request HttpServletRequest.
	 * @return boolean.
	 */
	public static boolean validateAlphabet(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			if (!AecStringUtils.isAlphabet(value)) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * 英数字チェック.
	 * @param bean Object.
	 * @param va ValidatorAction.
	 * @param field Field.
	 * @param errors ActionMessages.
	 * @param request HttpServletRequest.
	 * @return boolean.
	 */
	public static boolean validateAlphanumeric(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		String value = RulesHelper.getValue(bean, field);

		if (!GenericValidator.isBlankOrNull(value)) {
			if (!AecStringUtils.isAlphanumeric(value)) {
				errors.add(field.getKey(), Resources.getActionMessage(request,
					va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * comboボックス(selectタグ用)<br>
	 * valueがコンボボックスを形成しているリストのvalue値に<br>
	 * 含まれているかをチェックする.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateSelected(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		try {
			String selected = null;
			if (isString(bean)) {
				selected = (String) bean;
			} else {
				selected = ValidatorUtils.getValueAsString(bean, field
						.getProperty());
			}

			if (GenericValidator.isBlankOrNull(selected)) {
				return true;
			}

			List list = (List) PropertyUtils.getProperty(bean, field
					.getVarValue("list"));

			if (list == null) {
				return true;
			}

			// List内のコードのプロパティ名
			String valueName = field.getVarValue("valueName");

			for (Iterator ite = list.iterator(); ite.hasNext();) {

				String value = (String) PropertyUtils.getProperty(ite.next(),
					valueName);
				if (value.equals(selected) && StringUtils.isNotEmpty(value)) {
					return true;
				}
			}

		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		errors.add(field.getKey(), Resources.getActionMessage(request, va,
			field));

		return false;
	}

	/**
	 * Dateチェック<br>
	 * FieldChecks.validateDateを呼ぶ前に日付変換エラーがないかをチェックする.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateDate(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		try {
			String value = RulesHelper.getValue(bean, field);
			String datePattern = field.getVarValue("datePattern");
			if (StringUtils.isEmpty(datePattern)) {
				datePattern = field.getVarValue("datePatternStrict");
			}

			if (GenericValidator.isBlankOrNull(value)) {
				return Boolean.TRUE;
			}
			if (datePattern != null && 0 < datePattern.length()) {
				// スラッシュで分割した際に配列数が一致しない場合はエラー
				String[] splitPattern = datePattern.split("/");
				String[] splitValue = value.split("/");
				if (splitPattern.length != splitValue.length) {
					errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
					return Boolean.FALSE;
				}
				/* 正規表現に一致しなかったら、エラー */
				for (int i = 0; i < splitPattern.length; i++) {
					Pattern ptn = Pattern.compile("^[0-9]{"
							+ splitPattern[i].length() + "}$");
					if (!ptn.matcher(splitValue[i]).matches()) {
						errors.add(field.getKey(), Resources.getActionMessage(
							validator, request, va, field));
						return Boolean.FALSE;
					}
				}
				// 日付を解析
				SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
				formatter.setLenient(false);
				Date result = formatter.parse(value);
				if (result == null) {
					/* 日付が変換できなかったら、エラーで返す */
					errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));
					return Boolean.FALSE;
				}
			}
		} catch (ParseException e) {
			/* 日付が変換できなかったら、エラーで返す */
			errors.add(field.getKey(), Resources.getActionMessage(validator,
				request, va, field));
			return Boolean.FALSE;
		}

		return FieldChecks.validateDate(bean, va, field, errors, validator,
			request);
	}

	/* ==================== custom validate If ==================== */

	/**
	 * validateSelectedの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateSelectedIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateSelected(bean, va, field, errors, request);
		}
		return true;
	}

	/**
	 * validateStringの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateStringIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateString(bean, va, field, errors, request);
		}
		return true;
	}

	/* ==================== validate If ==================== */

	/**
	 * validateRequiredの実行判断(IF)ありメソッド<br>
	 * NOTEQUALを使う為に、既存のFieldChecks.validateRequiredIfは使わない.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateRequiredIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return FieldChecks.validateRequired(bean, va, field, errors,
				validator, request);
		}
		return true;
	}

	/**
	 * validateMaskの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateMaskIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return FieldChecks.validateMask(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/**
	 * validateDateの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateDateIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateDate(bean, va, field, errors, validator, request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateDecimalの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateDecimalIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateDecimal(bean, va, field, errors, request);
		}
		return Boolean.TRUE;
	}

	/* ==================== カンマ入力対応 ==================== */

	/**
	 * カンマ編集あり数値チェック<br>
	 * FieldChecks.validateInteger系をコピって来て、カンマ編集ありに対応したもの.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @param type String
	 * @return Object(true if valid, false otherwise.)
	 */
	public static Object validateEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request, final String type) {

		Object result = null;
		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (GenericValidator.isBlankOrNull(value)) {
			return Boolean.TRUE;
		}

		// formatの自前チェック
		if (!RulesHelper.checkFormt(value)) {
			log.debug("validateEx : value's format is invalid.");
			errors.add(field.getKey(), Resources.getActionMessage(validator,
				request, va, field));
			return Boolean.FALSE;
		}

		// カンマ除去
		String work = StringUtils.replace(value, ",", "");

		try {
			if ("Integer".equals(type)) {
				result = RulesHelper.formatInt(work);
			} else if ("Float".equals(type)) {
				result = GenericTypeValidator.formatFloat(work);
			} else if ("Double".equals(type)) {
				result = GenericTypeValidator.formatDouble(work);
			} else if ("Long".equals(type)) {
				result = RulesHelper.formatLong(work);
			} else if ("BigDecimal".equals(type)) {
				result = RulesHelper.formatBigDecimal(work);
			}
		} catch (NullPointerException e) {
			log.debug("validateEx : trim comma value is null.");
		}

		if (result == null) {
			errors.add(field.getKey(), Resources.getActionMessage(validator,
				request, va, field));
			return Boolean.FALSE;
		}
		return result;
	}

	/**
	 * カンマ編集あり数値範囲チェック<br>
	 * FieldChecks.validateIntRange系をコピって来て、カンマ編集ありに対応したもの.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @param type String
	 * @return boolean(True if in range, false otherwise.)
	 */
	public static boolean validateRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request, final String type) {

		String value = null;
		if (isString(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}

		if (!GenericValidator.isBlankOrNull(value)) {

			// formatの自前チェック
			if (!RulesHelper.checkFormt(value)) {
				log.debug("validateRangeEx : value's format is invalid.");
				errors.add(field.getKey(), Resources.getActionMessage(
					validator, request, va, field));
				return false;
			}

			// カンマ除去
			String work = StringUtils.replace(value, ",", "");

			try {
				boolean success = false;

				if ("Integer".equals(type)) {
					int intValue = Integer.parseInt(work);
					int min = Integer.parseInt(field.getVarValue("min"));
					int max = Integer.parseInt(field.getVarValue("max"));
					success = GenericValidator.isInRange(intValue, min, max);
				} else if ("Float".equals(type)) {
					float floatValue = Float.parseFloat(work);
					float min = Float.parseFloat(field.getVarValue("min"));
					float max = Float.parseFloat(field.getVarValue("max"));
					success = GenericValidator.isInRange(floatValue, min, max);
				} else if ("Double".equals(type)) {
					double doubleValue = Double.parseDouble(work);
					double min = Double.parseDouble(field.getVarValue("min"));
					double max = Double.parseDouble(field.getVarValue("max"));
					success = GenericValidator.isInRange(doubleValue, min, max);
				} else if ("Long".equals(type)) {
					long longValue = Long.parseLong(work);
					long min = Long.parseLong(field.getVarValue("min"));
					long max = Long.parseLong(field.getVarValue("max"));
					success = GenericValidator.isInRange(longValue, min, max);
				} else if ("BigDecimal".equals(type)) {
					/* Double以上の桁の場合を考慮 */
					/* 全角文字はダメ */
					if (RulesHelper.formatBigDecimal(work) != null) {
						BigDecimal decimal = new BigDecimal(work);
						BigDecimal min = new BigDecimal(field
								.getVarValue("min"));
						BigDecimal max = new BigDecimal(field
								.getVarValue("max"));
						if (min.compareTo(decimal) <= 0
								&& decimal.compareTo(max) <= 0) {
							success = true;
						}
					}
				}

				if (!success) {
					errors.add(field.getKey(), Resources.getActionMessage(
						validator, request, va, field));

					return false;
				}
			} catch (NumberFormatException e) {
				errors.add(field.getKey(), Resources.getActionMessage(
					validator, request, va, field));
				return false;
			}
		}
		return true;
	}

	/**
	 * validateIntegerのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateIntegerEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateEx(bean, va, field, errors, validator, request,
			"Integer");
	}

	/**
	 * validateDoubleのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateDoubleEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateEx(bean, va, field, errors, validator, request, "Double");
	}

	/**
	 * validateLongのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateLongEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateEx(bean, va, field, errors, validator, request, "Long");
	}

	/**
	 * BigDecimalのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateBigDecimalEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateEx(bean, va, field, errors, validator, request,
			"BigDecimal");
	}

	/**
	 * validateFloatのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static Object validateFloatEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateEx(bean, va, field, errors, validator, request, "Float");
	}

	/**
	 * validateIntRangeのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateIntRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateRangeEx(bean, va, field, errors, validator, request,
			"Integer");
	}

	/**
	 * validateDoubleRangeのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateDoubleRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateRangeEx(bean, va, field, errors, validator, request,
			"Double");
	}

	/**
	 * validateLongRangeのカンマ編集対応(Ex)ver.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateLongRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateRangeEx(bean, va, field, errors, validator, request,
			"Long");
	}

	/**
	 * BigDecimalのRangeチェック（カンマ編集対応(Ex)ver.）.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateBigDecimalRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateRangeEx(bean, va, field, errors, validator, request,
			"BigDecimal");
	}

	/**
	 * FloatのRangeチェック（カンマ編集対応(Ex)ver.）.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public static boolean validateFloatRangeEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		return validateRangeEx(bean, va, field, errors, validator, request,
			"Float");
	}

	/* ==================== Varidate IF ==================== */

	/**
	 * validateIntegerExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateIntegerIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateIntegerEx(bean, va, field, errors, validator,
				request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateDoubleExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateDoubleIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateDoubleEx(bean, va, field, errors, validator, request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateLongExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateLongIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateLongEx(bean, va, field, errors, validator, request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateBigDecimalExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateBigDecimalIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateBigDecimalEx(bean, va, field, errors, validator,
				request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateFloatExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static Object validateFloatIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateFloatEx(bean, va, field, errors, validator, request);
		}
		return Boolean.TRUE;
	}

	/**
	 * validateIntRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateIntRangeIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateIntRangeEx(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/**
	 * validateDoubleRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateDoubleRangeIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateDoubleRangeEx(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/**
	 * validateLongRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateLongRangeIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateLongRangeEx(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/**
	 * validateBigDecimalRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateBigDecimalRangeIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateBigDecimalRangeEx(bean, va, field, errors,
				validator, request);
		}
		return true;
	}

	/**
	 * validateFloatRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateFloatRangeIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateFloatRangeEx(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/* ==================== Compare ==================== */

	/**
	 * 大小判定.
	 * 
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateCompare(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final HttpServletRequest request) {

		boolean success = true;

		try {
			Object obj = PropertyUtils.getProperty(bean, field.getProperty());
			Object targetObj = PropertyUtils.getProperty(bean, field
					.getVarValue("targetField"));
			String compType = field.getVarValue("compareType");

			// パラメータがnullでない場合
			if (obj != null && targetObj != null && compType != null) {
				success = false;
				if (obj instanceof Date) {
					// java.util.Dateの場合
					success = Rules.compareDate((Date) obj, (Date) targetObj,
						compType);
				} else {
					String orig = (String) obj;
					String target = (String) targetObj;

					// 数値の場合
					if (StringUtils.isNotEmpty(orig)
							&& StringUtils.isNumeric(orig)
							&& StringUtils.isNotEmpty(target)
							&& StringUtils.isNumeric(target)) {
						success = Rules.compareBigDecimal(new BigDecimal(orig),
							new BigDecimal(target), compType);
					} else {
						success = Rules.compareString(orig, target, compType);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		// エラーの場合
		if (!success) {
			errors.add(field.getKey(), Resources.getActionMessage(request, va,
				field));
		}
		return success;
	}

	private static boolean compareDate(final Date orig, final Date target,
			final String compType) {
		boolean success = false;
		if (compType.equals(Rules.EQUAL)) { // Equal
			if (orig.compareTo(target) == 0) {
				success = true;
			}
		} else if (compType.equals(Rules.NOTEQUAL)) { // NotEqual
			if (orig.compareTo(target) != 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATERTHAN)) { // GreaterThan
			if (orig.compareTo(target) > 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATEREQUAL)) { // GreaterEqual
			if (orig.compareTo(target) >= 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSTHAN)) { // LessThan
			if (orig.compareTo(target) < 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSEQUAL)) { // LessEqual
			if (orig.compareTo(target) <= 0) {
				success = true;
			}
		}
		return success;
	}

	private static boolean compareBigDecimal(final BigDecimal orig,
			final BigDecimal target, final String compType) {
		boolean success = false;
		if (compType.equals(Rules.EQUAL)) { // Equal
			if (orig.compareTo(target) == 0) {
				success = true;
			}
		} else if (compType.equals(Rules.NOTEQUAL)) { // NotEqual
			if (orig.compareTo(target) != 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATERTHAN)) { // GreaterThan
			if (orig.compareTo(target) > 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATEREQUAL)) { // GreaterEqual
			if (orig.compareTo(target) >= 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSTHAN)) { // LessThan
			if (orig.compareTo(target) < 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSEQUAL)) { // LessEqual
			if (orig.compareTo(target) <= 0) {
				success = true;
			}
		}
		return success;
	}

	private static boolean compareString(final String orig,
			final String target, final String compType) {
		boolean success = false;
		if (compType.equals(Rules.EQUAL)) { // Equal
			if (orig.compareTo(target) == 0) {
				success = true;
			}
		} else if (compType.equals(Rules.NOTEQUAL)) { // NotEqual
			if (orig.compareTo(target) != 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATERTHAN)) { // GreaterThan
			if (orig.compareTo(target) > 0) {
				success = true;
			}
		} else if (compType.equals(Rules.GREATEREQUAL)) { // GreaterEqual
			if (orig.compareTo(target) >= 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSTHAN)) { // LessThan
			if (orig.compareTo(target) < 0) {
				success = true;
			}
		} else if (compType.equals(Rules.LESSEQUAL)) { // LessEqual
			if (orig.compareTo(target) <= 0) {
				success = true;
			}
		}
		return success;
	}

	/**
	 * 大小判定の実行判断(IF)ありメソッド.
	 * 
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateCompareIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors,
			final org.apache.commons.validator.Validator validator,
			final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return Rules.validateCompare(bean, va, field, errors, request);
		}
		return true;
	}

	/**
	 * 大小判定の実行判断(IF)ありメソッド.
	 * 
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateCompareIfEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors,
			final org.apache.commons.validator.Validator validator,
			final HttpServletRequest request) {
		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return Rules.validateCompareEx(bean, va, field, errors, validator,
				request);
		}
		return true;
	}

	/**
	 * 大小判定.
	 * 
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return Object
	 */
	public static boolean validateCompareEx(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors,
			final org.apache.commons.validator.Validator validator,
			final HttpServletRequest request) {

		boolean success = true;

		try {
			Object obj = PropertyUtils.getProperty(bean, field.getProperty());
			Object targetObj = PropertyUtils.getProperty(bean, field
					.getVarValue("targetField"));
			String compType = field.getVarValue("compareType");

			// パラメータがnullでない場合
			if (obj != null && targetObj != null && compType != null) {
				success = false;
				if (obj instanceof Date) {
					// java.util.Dateの場合
					success = Rules.compareDate((Date) obj, (Date) targetObj,
						compType);
				} else {
					String orig = (String) obj;
					String target = (String) targetObj;

					// 数値の場合
					if (StringUtils.isNotEmpty(orig)
							&& NumberUtils.isNumber(StringUtils.replace(orig,
								",", ""))
							&& StringUtils.isNotEmpty(target)
							&& NumberUtils.isNumber(StringUtils.replace(target,
								",", ""))) {
						success = Rules
								.compareBigDecimal(new BigDecimal(StringUtils
										.replace(orig, ",", "")),
									new BigDecimal(StringUtils.replace(target,
										",", "")), compType);
					} else {
						success = Rules.compareString(orig, target, compType);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		// エラーの場合
		if (!success) {
			errors.add(field.getKey(), Resources.getActionMessage(validator,
				request, va, field));
		}
		return success;
	}
}
