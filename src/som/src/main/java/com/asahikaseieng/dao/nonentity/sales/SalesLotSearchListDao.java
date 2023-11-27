/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.util.List;

/**
 * ロット検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface SalesLotSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesLotSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * ロット検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalesLotSearchList> 検索結果一覧
	 */
	List<SalesLotSearchList> getSearchList(final SalesLotSearchListPagerCondition condition);

}
