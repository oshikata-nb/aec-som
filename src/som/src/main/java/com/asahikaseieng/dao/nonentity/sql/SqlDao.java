/*
 * Created on 2008/06/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sql;

import java.util.List;

/**
 * SQLDaoクラス
 * @author a1020630
 */
public interface SqlDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sql.Sql.class;

	/** ARGSアノテーション getGadgetSql */
	String getGadgetSql_SQL = "/*$sql*/;";
	/** getTest_ARGS */
	String getGadgetSql_ARGS = "sql";

	/**
	 * getGadgetSqlメソッド
	 * @param sql sql
	 * @return List
	 */
	List<Sql> getGadgetSql(final String sql);
}
