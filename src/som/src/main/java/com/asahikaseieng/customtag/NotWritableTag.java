/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.asahikaseieng.Constants;

/**
 * 権限タグ(書き込み権限がない場合はbodyを評価).
 * @author JBD
 */
public class NotWritableTag extends TagSupport {

	private static final long serialVersionUID = -7561352701500131667L;

	/**
	 * コンストラクタ.
	 */
	public NotWritableTag() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int doStartTag() throws JspException {

		// menuを押下時の権限情報を取得
		String auth = (String) pageContext.getSession().getAttribute(
			Constants.MENU_CONTEXT_AUTH);

		if (!Constants.AUTHORITY_WRITE.equals(auth)) {
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}
}
