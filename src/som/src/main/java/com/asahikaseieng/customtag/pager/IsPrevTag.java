/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.customtag.pager;

import javax.servlet.jsp.JspException;

/**
 * 前ページがあるかどうか判断.
 * @author jbd
 */
public final class IsPrevTag extends AbstractPagerTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public IsPrevTag() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {
		super.doStartTag();
		if (getPager().isPrev()) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
}
