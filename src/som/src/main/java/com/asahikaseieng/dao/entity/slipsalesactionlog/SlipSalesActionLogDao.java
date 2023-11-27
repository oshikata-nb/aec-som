/*
 * Created on Thu Mar 12 11:47:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.slipsalesactionlog;

import java.math.BigDecimal;

/**
 * SlipSalesActionLogDaoインターフェース.
 * @author kanri-user
 */
public interface SlipSalesActionLogDao {

	/** BEANアノテーション. */
	Class<SlipSalesActionLog> BEAN = SlipSalesActionLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SlipSalesActionLog
	 * @return Insert件数
	 */
	int insert(SlipSalesActionLog bean);

	/**
	 * 連番取得
	 * @return シーケンス
	 */
	BigDecimal getActionSeq();
}
