/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.struts.processor.S2RequestProcessor;

/**
 * processPathメソッドを外から呼び出せないので拡張したクラス.
 * @author jbd
 */
public class CustomS2RequestProcessor extends S2RequestProcessor {

	/**
	 * コンストラクタ.
	 */
	public CustomS2RequestProcessor() {
	}

	String useProcessPath(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		return processPath(request, response);
	}
}
