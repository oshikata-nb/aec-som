/*
 * Created on 2020/12/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sendmaillog;

/**
 * SendMailLogDaoインターフェース.
 *
 * @author ssv
 */
public interface SendMailLogDao {

	/** BEANアノテーション. */
	Class<SendMailLog> BEAN = SendMailLog.class;

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
	int insert(SendMailLog bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 *
	 * @return ErrorLog
	 */
	SendMailLog getEntity();
}
