/*
 * Created on 2009/01/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts.validator;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import com.asahikaseieng.app.checkdigit.CheckDigitResult;
import com.asahikaseieng.app.checkdigit.CheckDigitUtil;
import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;

/**
 * 摂津製油用桁数チェック(validator用)
 * @author tosco
 */
public final class ValidateDigit implements Serializable {

	/** Log */
	private static Log log = LogFactory.getLog(ValidateDigit.class);

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	private ValidateDigit() {
	}

	/**
	 * カンマ編集あり数値桁数チェック<br>
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean(True if in range, false otherwise.)
	 */
	public static boolean validateDegit(
							final Object bean, final ValidatorAction va,
							final Field field, final ActionMessages errors,
							final Validator validator, final HttpServletRequest request) {
		//検証対象文字列がnullの場合はチェックしない
		boolean res = true;
		String value = null;
		//検証対象の値を取得
		if (String.class.isInstance(bean)) {
			value = (String) bean;
		} else {
			value = ValidatorUtils.getValueAsString(bean, field.getProperty());
		}
		if (!GenericValidator.isBlankOrNull(value)) {
			//値が入力されていた場合のみ、検証を行う。
			//数値フォーマットチェック
			if (!RulesHelper.checkFormt(value)) {
				//数値フォーマットとして、正しくない場合検証エラー
				log.debug("validateDigit : value's format is invalid.");
				errors.add(field.getKey(), Resources.getActionMessage(
					validator, request, va, field));
				return false;
			}
			//区分
			String unitDivision = field.getVarValue("unitDivision");
			//区分が変数に設定されているかチェック
			String unitDivisionItem = ValidatorUtils.getValueAsString(bean, unitDivision);
			if (StringUtils.isNotEmpty(unitDivisionItem)) {
				//区分が変数に設定されている場合（固定値の場合取得できなくてnullになる）
				unitDivision = unitDivisionItem;
			}
			String venderDivisionValue = field.getVarValue("venderDivision");
			String venderCdValue = field.getVarValue("venderCd");
			//取引先区分
			String venderDivision = null;
			if (StringUtils.isNotEmpty(venderDivisionValue)) {
				if ("SI".equals(venderDivisionValue) || "TS".equals(venderDivisionValue)) {
					//ST/TSの文字が直接入力されているのでそのまま適用
					venderDivision = venderDivisionValue;
				} else {
					//formのフィールドが指定されている
					venderDivision = ValidatorUtils.getValueAsString(bean, venderDivisionValue);
				}
			}
			//取引先コード
			String venderCd = null;
			if (StringUtils.isNotEmpty(venderCdValue)) {
				//formのフィールドが指定されている
				venderCd = ValidatorUtils.getValueAsString(bean, venderCdValue);
			}
			//数値桁数チェック部品呼び出し
			CheckDigitUtilsLogic check = CheckDigitUtil.getCheckDigitUtils(request);
			//チェック
			CheckDigitResult result = check.checkDigit(unitDivision, venderDivision, venderCd, value);
			NumberChkDisitDetail detail = result.getDetail();
			if (result.getCode() != CheckDigitUtilsLogic.SUCCESS) {
				//入力エラー時

				//項目名称を取得
				Arg arg = field.getArg(0);
				String itemName = Resources.getMessage(request, arg.getKey());
				//配列データフラグ
				String isList = field.getVarValue("isList");
				//エラーコードに対応するメッセージを取得
				ActionMessage trueMessage = createMessage(result.getCode(), detail, itemName, isList);
				errors.add(field.getKey(), trueMessage);
			}

		}
		return res;
	}

	/**
	 * エラーコードに対応するエラーメッセージオブジェクトを返します。
	 * @param errorCode エラーコード
	 * @param itemName 項目名称
	 * @param detail 数値桁数設定
	 * @param isListString 配列データかどうか[true:配列][false:単体]
	 * @return ActionMessage
	 */
	private static ActionMessage createMessage(final int errorCode,
			final NumberChkDisitDetail detail, final String itemName, final String isListString) {
		boolean isList = false;
		if (StringUtils.isNotEmpty(isListString)) {
			isList = Boolean.valueOf(isListString);
		}
		ActionMessage message = null;
		switch (errorCode) {
			case CheckDigitUtilsLogic.ERROR_NUMBER_FORMAT:
				//数値文字列エラー
				if (isList) {
					message = new ActionMessage("errors.digit.number.row", itemName);
				} else {
					message = new ActionMessage("errors.number", itemName);
				}
				break;
			case CheckDigitUtilsLogic.ERROR_NUMBER_MAX_LENGTH:
				//最大文字列長エラー
				if (isList) {
					message = new ActionMessage("errors.digit.maxlength.row",
						itemName, detail.getMaxLength().toString());
				} else {
					message = new ActionMessage("errors.maxlength",
						itemName, detail.getMaxLength().toString());
				}
				break;
			case CheckDigitUtilsLogic.ERROR_NUMBER_INTEGER_LENGTH:
				//整数部桁数エラー
				if (isList) {
					message = new ActionMessage("errors.digit.integer.row",
						itemName, detail.getIntegerLength().toString());
				} else {
					message = new ActionMessage("errors.digit.integer",
						itemName, detail.getIntegerLength().toString());
				}
				break;
			case CheckDigitUtilsLogic.ERROR_NUMBER_SMALLNUM_LENGTH:
				//小数部桁数エラー
				if (isList) {
					message = new ActionMessage("errors.digit.decimal.row",
						itemName, detail.getSmallnumLength().toString());
				} else {
					message = new ActionMessage("errors.digit.decimal",
						itemName, detail.getSmallnumLength().toString());
				}
				break;
			case CheckDigitUtilsLogic.ERROR_NUMBER_RANGE:
				//範囲エラー
				if (isList) {
					message = new ActionMessage("errors.digit.rang.row", itemName,
						detail.getLowerLimit().toString(), detail.getUpperLimit().toString());
				} else {
					message = new ActionMessage("errors.rang", itemName,
						detail.getLowerLimit().toString(), detail.getUpperLimit().toString());
				}
				break;
			default:
				message = null;
				break;
		}
		return message;
	}
	/**
	 * カンマ編集あり数値桁数チェック<br>
	 * @param bean Object
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors ActionMessages
	 * @param validator Validator
	 * @param request HttpServletRequest
	 * @return boolean(True if in range, false otherwise.)
	 */
	public static boolean validateDegitIf(
							final Object bean, final ValidatorAction va,
							final Field field, final ActionMessages errors,
							final Validator validator, final HttpServletRequest request) {

		if (ValidateIfUtils.validateIfCheck(field, validator)) {
			return validateDegit(bean, va, field, errors, validator, request);
		}
		return true;
	}

}
