/*
 * Created on 2007/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.SystemUtils;

/**
 * 独自実装のエラー出力クラス.
 * @author jbd
 */
public final class AecStackTrace {

	/**
	 * コンストラクタ.
	 */
	private AecStackTrace() {
	}

	/**
	 * エラーをログにはく.
	 * @param e Exception
	 * @return エラーメッセージ
	 */
	public static String getStackTrace(final Throwable e) {
		final StringWriter sw = new StringWriter();
		if (e != null) {
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
		}

		return "stack trace: " + SystemUtils.LINE_SEPARATOR + sw.toString();
	}

}
