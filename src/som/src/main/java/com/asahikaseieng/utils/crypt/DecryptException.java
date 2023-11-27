/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils.crypt;

/**
 * 複合化に失敗した場合に発生する例外.
 * @author jbd
 */
public class DecryptException extends Exception {

	private static final long serialVersionUID = -4397752544294865492L;

	/**
	 * コンストラクタ.
	 * @param nestedException ネストする例外
	 */
	public DecryptException(final Exception nestedException) {
		super(nestedException);
	}
}
