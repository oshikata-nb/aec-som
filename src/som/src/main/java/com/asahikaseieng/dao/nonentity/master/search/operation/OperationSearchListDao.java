/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.search.operation;

import java.util.List;

/**
 * 工程マスタ検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface OperationSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.search.operation.OperationSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 工程マスタ検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<OperationSearchList> 検索結果一覧
	 */
	List<OperationSearchList> getSearchList(final OperationSearchListPagerCondition condition);

}
