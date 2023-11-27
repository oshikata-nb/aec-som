/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.xml;

/**
 * web.xmlのinit-paramタグ.
 * @author jbd
 */
public class InitParam {
	private String name;

	private String value;

	/**
	 * コンストラクタ.
	 */
	public InitParam() {
		super();
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}
