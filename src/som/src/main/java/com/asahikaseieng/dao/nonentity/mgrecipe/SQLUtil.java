/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import org.seasar.extension.jdbc.SqlLog;
import org.seasar.extension.jdbc.SqlLogRegistry;
import org.seasar.extension.jdbc.SqlLogRegistryLocator;

/**
 * S2DAOが吐くSQL文を出力するユーティリティ
 * @author tosco
 */
public final class SQLUtil {

	/**
	 * コンストラクタ
	 */
	private SQLUtil() {
	}
	/**
	 * S2DAOが吐くSQL文を出力する
	 */
	public static void printSQLLog() {
		SqlLogRegistry registry = SqlLogRegistryLocator.getInstance();
		if (registry != null) {
			// 存在しなければ設定がOFFになっている
		    SqlLog sqlLog = registry.getLast();
		    if (sqlLog != null) {
		    	// 存在しなければ直近でSQLが発行されていない
		        String completeSql = sqlLog.getCompleteSql();
		        System.out.println(completeSql);
		    }
		}
	}
}
