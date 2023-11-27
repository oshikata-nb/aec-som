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
 * 件数を表示.
 * @author jbd
 */
public final class CountTag extends AbstractPagerTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CountTag() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			pageContext.getOut().print(getPager().getCount());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
