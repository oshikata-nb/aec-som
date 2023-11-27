/*
 * Created on 2018/02/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sendmailerrorlog;

/**
 * SendMailErrorLogDaoインターフェース.
 *
 * @author ssv
 */
public interface SendMailErrorLogDao {

	/** BEANアノテーション. */
	Class<SendMailErrorLog> BEAN = SendMailErrorLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 *
	 * @param bean
	 *            ErrorLog
	 * @return Insert件数
	 */
	int insert(SendMailErrorLog bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 *
	 * @return ErrorLog
	 */
	SendMailErrorLog getEntity();
}
