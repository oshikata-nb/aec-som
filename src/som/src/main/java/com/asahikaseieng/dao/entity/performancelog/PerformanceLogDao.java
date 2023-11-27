/*
 * Created on Mon Jan 31 16:34:12 JST 2011
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.performancelog;

/**
 * PerformanceLogDaoインターフェース.
 * @author t1344224
 */
public interface PerformanceLogDao {

	/** BEANアノテーション. */
	Class BEAN = PerformanceLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PerformanceLog
	 * @return Insert件数
	 */
	int insert(PerformanceLog bean);
}
