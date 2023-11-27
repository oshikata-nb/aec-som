/*
 * Created on Thu Mar 12 11:47:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.errorlog;

/**
 * ErrorLogDaoインターフェース.
 * @author kanri-user
 */
public interface ErrorLogDao {

	/** BEANアノテーション. */
	Class BEAN = ErrorLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ErrorLog
	 * @return Insert件数
	 */
	int insert(ErrorLog bean);
}
