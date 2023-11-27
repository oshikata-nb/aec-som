/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.xml;

import java.util.HashMap;
import java.util.Map;

/**
 * web.xmlのservletタグ.
 * @author jbd
 */
public class Servlet {
	private String servletName;

	private Map<String, Object> initParams = new HashMap<String, Object>();

	/**
	 * コンストラクタ.
	 */
	public Servlet() {
		super();
	}

	/**
	 * 子要素を追加する.
	 * @param initParam initParam
	 */
	public void addChild(final InitParam initParam) {
		initParams.put(initParam.getName(), initParam);
	}

	/**
	 * servletNameを取得します。
	 * @return servletName
	 */
	public String getServletName() {
		return servletName;
	}

	/**
	 * servletNameを設定します。
	 * @param servletName servletName
	 */
	public void setServletName(final String servletName) {
		this.servletName = servletName;
	}

	/**
	 * initParamsを取得します。
	 * @return initParams
	 */
	public Map<String, Object> getInitParams() {
		return initParams;
	}
}
