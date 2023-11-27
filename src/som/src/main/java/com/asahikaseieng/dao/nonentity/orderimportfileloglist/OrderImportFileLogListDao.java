/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportfileloglist;

import java.util.List;

/**
 * OrderImportHeadDaoインターフェース.
 * @author 
 */
public interface OrderImportFileLogListDao {

	/** BEANアノテーション. */
	Class<OrderImportFileLogList> BEAN = com.asahikaseieng.dao.nonentity.orderimportfileloglist.OrderImportFileLogList.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getList(). */
	String getViewList_ARGS = "tantoCd";

	/**
	 * プロシージャ実行ログリスト取得.
	 * @param checkFlg BigDecimal
	 * @return OrderImportFileLogList
	 */
	List<OrderImportFileLogList> getViewList(String tantoCd);
}
