/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

/**
 * 帳票作成時用例外.
 * @author jbd
 */
public class ReportCreateException extends Exception {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ(原因指定)
	 * @param cause 原因
	 */
	public ReportCreateException(final Throwable cause) {
		super(cause);
	}

}
