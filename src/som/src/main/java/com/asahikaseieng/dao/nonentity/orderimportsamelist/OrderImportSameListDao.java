/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportsamelist;

import java.util.List;

/**
 * OrderImportSameListインターフェース.
 * @author 
 */
public interface OrderImportSameListDao {

	/** BEANアノテーション. */
	Class<OrderImportSameList> BEAN = com.asahikaseieng.dao.nonentity.orderimportsamelist.OrderImportSameList.class;

	//
	// インスタンスメソッド
	//
	/** ARGSアノテーション getSameOrderImportList(). */
	String getSameOrderImportList_ARGS = "orderNo, FrstOrderNo";

	/**
	 * 同じ受注に含まれる受注取込データのリストを取得(運賃・受注金額更新対象検索用)
	 * orderNoにNOTNULLを入力すると、同じグループの確定済の受注を検索する(確定処理用)
	 * @param orderNo
	 * @param FrstOrderNo
	 */
	List<OrderImportSameList> getSameOrderImportList(String orderNo, String FrstOrderNo);
}
