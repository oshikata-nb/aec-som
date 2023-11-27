/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

import com.asahikaseieng.Constants;

/**
 * 楽観的ロックによる排他が発生した時の例外.
 * @author jbd
 */
public class OptimisticLockRuntimeException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/* 行番号 */
	private int index = Constants.EXCEPTION_INIT_ROW;

	/**
	 * コンストラクタ.
	 */
	public OptimisticLockRuntimeException() {
		super();
	}

	/**
	 * コンストラクタ.
	 * @param index 行番号
	 */
	public OptimisticLockRuntimeException(final int index) {
		super();
		setIndex(index);
	}

	/**
	 * @return index を戻します。
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index 設定する index。
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

}
