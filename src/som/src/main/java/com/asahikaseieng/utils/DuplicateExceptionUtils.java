/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.sql.SQLException;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.DuplicateRuntimeException;

/**
 * SqlException(一意制約違反)を、DuplicateExceptionに変換する.
 * @author jbd
 */
public final class DuplicateExceptionUtils {

	/* NONUNIQUE_ERRCODE */
	private static final int NONUNIQUE_ERRCODE = 1;

	/**
	 * コンストラクタ.
	 */
	private DuplicateExceptionUtils() {
	}

	/**
	 * エラーコード1のSqlException(一意制約違反)を、DuplicateExceptionに変換する.
	 * @param exception SqlException
	 * @param index 行番号
	 * @throws DuplicateRuntimeException DuplicateException
	 */
	public static void convertException(final SQLRuntimeException exception, final int index)
			throws DuplicateRuntimeException {
		if (exception == null) {
			throw new IllegalArgumentException("paramater is null");
		}
		if (NONUNIQUE_ERRCODE == ((SQLException) exception.getCause())
				.getErrorCode()) {
			throw new DuplicateRuntimeException(index);
		}
		throw exception;
	}

	/**
	 * convertException(SQLRuntimeException, int)のラッパーメソッド<br>
	 * indexには「対象外の初期値」を自動的に設定する.
	 * @param exception SqlException
	 * @throws DuplicateRuntimeException DuplicateException
	 */
	public static void convertException(final SQLRuntimeException exception)
			throws DuplicateRuntimeException {

		convertException(exception, Constants.EXCEPTION_INIT_ROW);
	}
}
