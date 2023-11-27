/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.accept;

import java.util.List;

/**
 * 受入・仕入検索画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface AcceptListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.accept.AcceptList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<ArrivalList> 検索結果リスト
	 */
	List<AcceptList> getSearchList(final AcceptListPagerCondition condition);

}
