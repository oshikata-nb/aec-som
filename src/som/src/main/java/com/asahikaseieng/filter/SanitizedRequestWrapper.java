/*
 * Created on 2007/04/30
 *
 * $Id$
 * $copyright$
 *
 */
package com.asahikaseieng.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.asahikaseieng.utils.SanitizeCheck;

/**
 * 問題となる文字のサニタイズをする為のHttpServletRequestWrapper.
 * @author jbd
 */
public final class SanitizedRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * コンストラクタ.
	 * @param request HTTPリクエスト
	 */
	public SanitizedRequestWrapper(final HttpServletRequest request) {
		super(request);
	}

	/**
	 * {@inheritDoc}
	 */
	public Cookie[] getCookies() {

		Cookie[] cookies = super.getCookies();

		for (int i = 0; i < cookies.length; i++) {

			Cookie cookie = cookies[i];

			String value = SanitizeCheck.sanitizeParamValue(cookie.getValue(),
				true);
			value = SanitizeCheck.sanitizeNullParamValue(value);

			cookie.setValue(value);
		}

		return cookies;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getParameter(final String name) {

		String val = SanitizeCheck.sanitizeParamValue(super.getParameter(name),
			true);
		val = SanitizeCheck.sanitizeNullParamValue(val);

		return val;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map getParameterMap() {

		Map<String, String[]> result = new HashMap<String, String[]>();

		Map map = super.getParameterMap();
		Iterator ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			String[] array = (String[]) map.get(key);

			for (int i = 0; i < array.length; i++) {
				array[i] = SanitizeCheck.sanitizeParamValue(array[i], true);
				array[i] = SanitizeCheck.sanitizeNullParamValue(array[i]);
			}

			result.put(key, array);
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getParameterValues(final String name) {

		String[] values = super.getParameterValues(name);

		for (int i = 0; i < values.length; i++) {
			values[i] = SanitizeCheck.sanitizeParamValue(values[i], true);
			values[i] = SanitizeCheck.sanitizeNullParamValue(values[i]);
		}

		return values;
	}
}
