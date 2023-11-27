/*
 * Created on 2009/04/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;

import java.util.List;

/**
 * 生産計画入力一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ProductionListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.production.ProductionList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 生産計画入力一覧検索
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 */
	List<ProductionList> getSearchList(final ProductionPagerCondition condition);

}
