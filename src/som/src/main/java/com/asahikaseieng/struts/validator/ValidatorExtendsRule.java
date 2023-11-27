/*
 * Created on 2006/08/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResults;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/**
 * 階層構造のValidationを行うRule.
 * @author jbd
 */
public final class ValidatorExtendsRule {

	private static Log log = LogFactory.getLog(ValidatorExtendsRule.class);

	private ValidatorExtendsRule() {
	}

	/**
	 * 階層構造のValidationを行うRule
	 * Executes validation for another set of "inherited" rules.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @param application ServletContext
	 * @return Object (results of the validator.)
	 */
	public static Object validateExtends(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request, final ServletContext application) {

		String fieldPrefix = null;
		if (0 < field.getProperty().length()) {
			fieldPrefix = field.getProperty();
		}
		String formAndProperty = null;
		if (fieldPrefix == null) {
			formAndProperty = validator.getFormName();
		} else {
			formAndProperty = validator.getFormName() + "/" + fieldPrefix;
		}

		// Get the validation key
		String key = field.getVarValue("extends");
		if (key == null || key.length() == 0) {

			if (log.isDebugEnabled()) {
				log.debug("'extends' var is missing for " + formAndProperty);
			}
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"LB897", formAndProperty));
			return Boolean.FALSE;
		}

		// Get the property value
		Object value = null;
		if (fieldPrefix == null) {
			value = bean;
		} else {
			try {
				value = PropertyUtils.getProperty(bean, field.getProperty());
			} catch (Exception e) {
				log.debug("Error retrieving property '" + formAndProperty
						+ "' " + e.getMessage(), e);
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.extends", formAndProperty));
			}
		}

		if (value == null) {
			log.debug("Property '" + formAndProperty + "' is NULL");
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.extends", formAndProperty));
			return Boolean.FALSE;
		}

		// Initialize the validator
		ActionMessages newErrors = new ActionMessages();
		Validator newValidator = Resources.initValidator(key, bean,
			application, request, newErrors, validator.getPage());

		// Is it an Array/Collection?
		Object[] values = null;
		if (value instanceof Collection) {
			values = ((Collection) value).toArray();
		} else if (value.getClass().isArray()) {
			values = (Object[]) value;
		}

		// Execute the validator
		ValidatorResults results = null;
		if (values == null) {
			results = executeValidator(bean, -1, field, newValidator, errors,
				newErrors, fieldPrefix);
		} else {
			results = new ValidatorResults();
			for (int i = 0; i < values.length; i++) {
				fieldPrefix = field.getProperty() + "[" + i + "].";
				newValidator.setParameter(Validator.BEAN_PARAM, values[i]);
				ValidatorResults indexedResults = executeValidator(values[i],
					i + 1, field, newValidator, errors, newErrors, fieldPrefix);
				results.merge(indexedResults);
			}

		}

		return results;

	}

	/**
	 * validatの実行メソッド<br>
	 * Executes a validator.
	 * @param bean Object
	 * @param index int
	 * @param field Field
	 * @param validator Validator
	 * @param errors ActionMessages
	 * @param newErrors ActionMessages
	 * @param fieldPrefix String
	 * @return ValidatorResults
	 */
	private static ValidatorResults executeValidator(final Object bean,
			final int index, final Field field, final Validator validator,
			final ActionMessages errors, final ActionMessages newErrors,
			final String fieldPrefix) {

		// Validate
		ValidatorResults results = null;
		try {
			results = validator.validate();
		} catch (ValidatorException e) {
			log.debug(e.getMessage(), e);
		}

		// Get Additional Argument
		Arg arg = field.getArg(0);
		String argKey = null;
		if (arg != null) {
			argKey = arg.getKey();
		}
		Object argValue = null;
		if (argKey != null) {
			if ("#".equals(argKey)) {
				argValue = "" + index;
			} else {
				try {
					argValue = PropertyUtils.getProperty(bean, argKey);
				} catch (Exception e) {
					log.debug("Error retrieving property '" + argKey + "' "
							+ e.getMessage(), e);
				}
				if (argValue == null || "".equals(argValue)) {
					argValue = "???";
				}
			}
		}

		// Merge Errors
		if (0 < newErrors.size()) {

			if (fieldPrefix == null && argValue == null) {
				errors.add(newErrors);
			} else {
				Iterator properties = newErrors.properties();
				while (properties.hasNext()) {
					String property = (String) properties.next();
					Iterator messages = newErrors.get(property);
					while (messages.hasNext()) {
						ActionMessage msg = (ActionMessage) messages.next();
						ActionMessage newMsg = msg;
						String newProperty = null;
						if (fieldPrefix == null) {
							newProperty = property;
						} else {
							newProperty = fieldPrefix + property;
						}
						if (argValue != null) {

							// Add the additonal "argument" to the messages
							Object[] args = msg.getValues();
							Object[] newArgs = args;
							if (argValue != null) {
								int length = 0;
								if (args != null) {
									for (int i = 0; i < args.length; i++) {
										if (args[i] != null) {
											length = i + 1;
										}
									}
								}

								if (length == 0) {
									newArgs = new Object[] {argValue};
								} else {
									newArgs = new Object[length + 1];
									System.arraycopy(args, 0, newArgs, 0,
										length);
									newArgs[length] = argValue;
								}
							}
							newMsg = new ActionMessage(msg.getKey(), newArgs);
						}
						errors.add(newProperty, newMsg);
					}
				}
			}
		}

		// Clear Errors
		newErrors.clear();

		// Return results
		return results;

	}

	/**
	 * validateLongRangeExの実行判断(IF)ありメソッド.
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @param application ServletContext
	 * @return Object (results of the validator.)
	 */
	public static Object validateExtendsIf(final Object bean,
			final ValidatorAction va, final Field field,
			final ActionMessages errors, final Validator validator,
			final HttpServletRequest request, final ServletContext application) {
		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateExtends(bean, va, field, errors, validator, request,
				application);
		}
		return Boolean.TRUE;
	}
}
