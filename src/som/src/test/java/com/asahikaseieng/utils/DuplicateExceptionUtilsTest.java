/*
 * Created on 2007/04/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.utils;

import java.sql.SQLException;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.test.AbstractS2TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.exception.SQLRuntimeException;

/**
 * DuplicateExceptionUtilsのテストクラス.
 * @author jbd
 */
public final class DuplicateExceptionUtilsTest extends AbstractS2TestCase {

	private Log log = LogFactory.getLog(DuplicateExceptionUtilsTest.class);

	/**
	 * Constructor for ErrorsUtilsTest.
	 * 
	 * @param testname testname
	 */
	public DuplicateExceptionUtilsTest(final String testname) {
		super(testname);
	}

	/**
	 * convertExceptionのテスト.
	 */
	public void testConvertException() {

		/* パラメータエラー */
		try {
			DuplicateExceptionUtils.convertException(null);
			fail("should raise IllegalArgumentException.");
		} catch (DuplicateRuntimeException e) {
			fail("should raise IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			log
					.debug("DuplicateExceptionUtilsTest : raise IllegalArgumentException.");
		}

		/* 一意制約エラーの場合(エラーコードは１) */
		try {
			SQLException e = new SQLException(null, null,
					Constants.TEST_DUPLICATE_EXCEPTION_CODE);
			SQLRuntimeException error = new SQLRuntimeException(e);
			DuplicateExceptionUtils.convertException(error);
			fail("should raise DuplicateException.");
		} catch (DuplicateRuntimeException e) {
			log
					.debug("DuplicateExceptionUtilsTest : raise DuplicateRuntimeException.");
		} catch (IllegalArgumentException e) {
			fail("should raise DuplicateException.");
		}

		/* 一意制約エラーの場合(エラーコードは１)・インデックスあり */
		try {
			SQLException e = new SQLException(null, null,
					Constants.TEST_DUPLICATE_EXCEPTION_CODE);

			SQLRuntimeException error = new SQLRuntimeException(e);
			DuplicateExceptionUtils.convertException(error, 1);
			fail("should raise DuplicateException.");
		} catch (DuplicateRuntimeException e) {
			assertEquals(1, e.getIndex());
		} catch (IllegalArgumentException e) {
			fail("should raise DuplicateException.");
		}

		/* 一意制約エラー以外の場合 */
		try {
			final int dummy = 99;
			SQLException e = new SQLException(null, null, dummy);
			SQLRuntimeException error = new SQLRuntimeException(e);
			DuplicateExceptionUtils.convertException(error);
			fail("should raise SQLRuntimeException.");
		} catch (DuplicateRuntimeException e) {
			fail("should raise SQLRuntimeException.");
		} catch (SQLRuntimeException e) {
			log
					.debug("DuplicateExceptionUtilsTest : raise SQLRuntimeException.");
		}
	}

}
