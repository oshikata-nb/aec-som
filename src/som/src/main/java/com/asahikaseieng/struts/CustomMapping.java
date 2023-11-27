/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.struts;

import org.apache.struts.action.ActionMapping;

/**
 * カスタマイズしたActionMapping.
 * @author jbd
 */
public class CustomMapping extends ActionMapping {

	private static final long serialVersionUID = -2072977078871943084L;

	private boolean allowNoLogin;

	private boolean gadget;

	/**
	 * コンストラクタ.
	 */
	public CustomMapping() {
		super();
	}

	/**
	 * @return allowNoLogin を戻します。
	 */
	public boolean isAllowNoLogin() {
		return allowNoLogin;
	}

	/**
	 * @param allowNoLogin 設定する allowNoLogin。
	 */
	public void setAllowNoLogin(final boolean allowNoLogin) {
		this.allowNoLogin = allowNoLogin;
	}

	/**
	 * gadgetを取得します。
	 * @return gadget
	 */
	public boolean isGadget() {
		return gadget;
	}

	/**
	 * gadgetを設定します。
	 * @param gadget gadget
	 */
	public void setGadget(final boolean gadget) {
		this.gadget = gadget;
	}
}
