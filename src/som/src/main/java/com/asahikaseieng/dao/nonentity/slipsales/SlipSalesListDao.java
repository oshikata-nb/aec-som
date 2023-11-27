/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipsales;

import java.util.List;

/**
 * 売上伝票出力画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface SlipSalesListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipsales.SlipSalesList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<SlipSalesList> 検索結果リスト
	 */

	List<SlipSalesList> getSearchList(final SlipSalesListPagerCondition condition);

}
