/*
 * Created on 2021/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportfilelogupdate;


/**
 * OrderImportHeadDaoインターフェース.
 * @author 
 */
public interface OrderImportFileLogUpdateDao {

	/** BEANアノテーション. */
	Class<OrderImportFileLogUpdate> BEAN =  com.asahikaseieng.dao.nonentity.orderimportfilelogupdate.OrderImportFileLogUpdate.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション updateCheckedFlg */
	String updateCheckedFlg_ARGS = "tantoCd";

	/**
	 * 
	 * 確認フラグを1に更新
	 * @param tantoCd
	 * @return int
	 */
	int updateCheckedFlg(final String tantoCd);
}
