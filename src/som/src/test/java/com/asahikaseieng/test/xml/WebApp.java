/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * web.xmlのweb-appタグクラス.
 * @author jbd
 */
public class WebApp {
	private Map<String, Object> servlets = new HashMap<String, Object>();

	/**
	 * コンストラクタ.
	 */
	public WebApp() {
		super();
	}

	/**
	 * 子要素を追加する.
	 * @param servlet servlet
	 */
	public void addChild(final Servlet servlet) {
		servlets.put(servlet.getServletName(), servlet);
	}

	/**
	 * servletsを取得します。
	 * @return servlets
	 */
	public Map<String, Object> getServlets() {
		return servlets;
	}

}
