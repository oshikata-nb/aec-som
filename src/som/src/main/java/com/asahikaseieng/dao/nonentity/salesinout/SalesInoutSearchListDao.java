/*
 * Created on 2010/09/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.salesinout;

import java.util.List;

/**
 * SalesInoutSearchListDaoクラス
 * @author t1344224
 */
public interface SalesInoutSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.salesinout.SalesInoutSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<SalesInoutSearchList> 検索結果
	 */
	List<SalesInoutSearchList> getSearchList(
			SalesInoutSearchListPagerCondition condition);
}
