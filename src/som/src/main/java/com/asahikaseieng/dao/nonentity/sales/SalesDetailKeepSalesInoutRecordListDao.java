/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.util.List;

/**
 * 売上詳細(預り品)画面_売上受払履歴Daoインターフェース.
 *
 * @author
 */
public interface SalesDetailKeepSalesInoutRecordListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepSalesInoutRecordList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getList */
	String getList_ARGS = "salesNo,itemCd";
	/**
	 * 売上受払履歴検索メソッド
	 * 
	 * @param salesNo 売上番号
	 * @param itemCd 品目コード
	 * @return List<SalesDetailKeepSalesInoutRecordList> 検索結果一覧
	 */
	List<SalesDetailKeepSalesInoutRecordList> getList(final String salesNo, String itemCd);
}
