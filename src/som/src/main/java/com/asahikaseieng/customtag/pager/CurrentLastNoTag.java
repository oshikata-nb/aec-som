/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.customtag.pager;

import java.io.IOException;

import javax.servlet.jsp.JspException;

/**
 * 現在ページの最後のレコード番号表示.
 * @author jbd
 */
public final class CurrentLastNoTag extends AbstractPagerTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CurrentLastNoTag() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			pageContext.getOut().print(getPager().getCurrentLastNo());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
