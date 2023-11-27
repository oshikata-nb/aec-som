/*
 * Created on Thu Jan 15 16:25:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimportfileout;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.orderimportfilelog.OrderImportFileLog;

/**
 * AreaDaoインターフェース.
 * @author t0011036
 */
public interface OrderImportFileOutDao {

	/** BEANアノテーション. */
	Class BEAN = OrderImportFileOut.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Area
	 * @return Insert件数
	 */
	int insert(OrderImportFileOut bean);

	/**
	 * Update.
	 * @param bean Area
	 * @return Update件数
	 */
	int update(OrderImportFileOut bean);

	/**
	 * Delete.
	 * @param bean Area
	 * @return Delete件数
	 */
	int delete(OrderImportFileOut bean);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "SEQ";

	/**
	 * リスト取得.
	 * @param seq String
	 */
	List<OrderImportFileOut> getList(BigDecimal seq);
	
}
