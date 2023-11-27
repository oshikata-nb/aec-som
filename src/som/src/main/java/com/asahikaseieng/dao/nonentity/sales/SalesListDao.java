/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import java.util.List;

/**
 * 売上トランザクションメンテ 一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface SalesListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<SalesList> 検索結果リスト
	 */

	List<SalesList> getSearchList(final SalesListPagerCondition condition);

}
