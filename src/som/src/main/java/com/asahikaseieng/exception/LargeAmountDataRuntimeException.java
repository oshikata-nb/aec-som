/*
 * Created on 2007/04/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

/**
 * 大量データ検索時の例外クラス.
 * @author jbd
 */
public class LargeAmountDataRuntimeException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LargeAmountDataRuntimeException() {
		super();
	}
}
