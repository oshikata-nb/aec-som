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
 * 1ページに表示する件数表示.
 * @author jbd
 */
public final class LimitTag extends AbstractPagerTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LimitTag() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();
		try {
			pageContext.getOut().print(getPager().getLimit());
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
