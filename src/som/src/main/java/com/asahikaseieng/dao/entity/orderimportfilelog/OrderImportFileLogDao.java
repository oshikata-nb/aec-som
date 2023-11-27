/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimportfilelog;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderImportHeadDaoインターフェース.
 * @author 
 */
public interface OrderImportFileLogDao {

	/** BEANアノテーション. */
	Class<OrderImportFileLog> BEAN = OrderImportFileLog.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderImportFileLog
	 * @return Insert件数
	 */
	int insert(OrderImportFileLog bean);

	/**
	 * Update.
	 * @param bean OrderImportFileLog
	 * @return Update件数
	 */
	int update(OrderImportFileLog bean);

	/**
	 * Delete.
	 * @param bean OrderImportFileLog
	 * @return Delete件数
	 */
	int delete(OrderImportFileLog bean);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "PK_NO, PK_ROW, FLG";

	/**
	 * リスト取得.
	 * @param pkNo String
	 * @return OrderImportFileLog
	 */
	List<OrderImportFileLog> getList(String pkNo, BigDecimal pkRow, BigDecimal flg);
	
}
