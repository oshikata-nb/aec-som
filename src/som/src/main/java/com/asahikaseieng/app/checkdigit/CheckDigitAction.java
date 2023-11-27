/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.checkdigit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractAction;
import com.asahikaseieng.utils.JSONUtil;

/**
 * 数値桁数チェック丸め処理 Actionクラス.
 * @author tosco
 */
public final class CheckDigitAction extends AbstractAction {

	/**
	 * コンストラクタ.
	 */
	public CheckDigitAction() {
		super();
	}

	/**
	 * 入力された数値文字列を数値桁数マスタの設定により丸めて数値文字列を返す。
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 * @throws Exception Exception
	 */
	public ActionForward format(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		if (getLog().isDebugEnabled()) {
			getLog().debug("format.");
		}

		CheckDigitForm checkForm = (CheckDigitForm) form;
		String value = checkForm.getValue();
		String res = null;
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotEmpty(value)) {
			//カンマが入力されているかもしれないので、カンマを除去
			String buf = value.replaceAll(",", "");
			try {
				BigDecimal target = new BigDecimal(buf);
				CheckDigitUtilsLogic formatter = CheckDigitUtil.getCheckDigitUtils(request);
				//数値桁数マスタを元に丸め＆フォーマット
				res = formatter.format(checkForm.getUnitDivision(),
					checkForm.getVenderDivision(), checkForm.getVenderCd(), target);
				map.put("status", "true");
			} catch (NumberFormatException nfe) {
				//数値変換できなかった場合は入力値をそのまま返す
				res = value;
				map.put("status", "false");
			}
		}
		map.put("value", res);
		String jsonString = JSONUtil.convertJSON(map);
		JSONUtil.responseAjax(response, jsonString);

		return null;
	}


/* -------------------- setter -------------------- */


}
