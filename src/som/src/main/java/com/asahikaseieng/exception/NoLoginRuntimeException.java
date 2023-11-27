/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

/**
 * Loginオブジェクトを持たずにallowNoLogin=false領域に入った時に発生する例外.
 * @author jbd
 */
public class NoLoginRuntimeException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public NoLoginRuntimeException() {
	}
}
