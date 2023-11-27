/*
 * Created on Mon Apr 20 13:53:12 JST 2015
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.checklog;

/**
 * CheckLogDaoインターフェース.
 * @author Administrator
 */
public interface CheckLogDao {

	/** BEANアノテーション. */
	Class BEAN = CheckLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean CheckLog
	 * @return Insert件数
	 */
	int insert(CheckLog bean);
}

