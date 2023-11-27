/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.exception;

import com.asahikaseieng.Constants;

/**
 * 重複(一意制約)例外クラス.
 * @author jbd
 */
public class DuplicateRuntimeException extends RuntimeException {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	/* 行番号 */
	private int index = Constants.EXCEPTION_INIT_ROW;

	/**
	 * コンストラクタ.
	 * @param index 行番号
	 */
	public DuplicateRuntimeException(final int index) {
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
