/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

/**
 * JSON形式の文字列作成ユーティリティ
 * @author tosco
 */
public final class JSONUtil {

	/**
	 * コンストラクタ
	 */
	private JSONUtil() {
	}
	/**
	 * MapをJSON形式の文字列に変換する
	 * @param map Map
	 * @return JSON形式の文字列
	 */
	public static String convertJSON(final Map<String, String> map) {
		String res = "";
		if (map != null) {
			StringBuilder buf = new StringBuilder("{ ");

			Set<String> keys = map.keySet();
			for (String key : keys) {
				String value = map.get(key);
				buf.append("\"").append(key).append("\"");
				buf.append(" : ");
				buf.append("\"").append(value).append("\"");
				buf.append(",");
			}
			buf.delete(buf.length() - 1, buf.length());
			buf.append(" }");
			res = buf.toString();
		}
		return res;
	}
	/**
	 * 非同期通信の応答処理
	 * @param response HttpServletResponse
	 * @param jsonString JSON形式の文字列
	 * @throws IOException 入出力例外
	 */
	public static void responseAjax(final HttpServletResponse response, final String jsonString)
	throws IOException {
		PrintWriter out = null;
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

			out.print(jsonString);
			out.println();

			out.flush();
			out.close();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
