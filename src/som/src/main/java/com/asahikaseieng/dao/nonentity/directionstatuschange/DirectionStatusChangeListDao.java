/*
 * Created on 2009/05/28
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.directionstatuschange;

import java.util.List;

/**
 * 製造指図ステータス変更画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface DirectionStatusChangeListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 製造指図ステータス変更　検索メソッド
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeList> 検索結果リスト
	 */
	List<DirectionStatusChangeList> getSearchList(final DirectionStatusChangePagerCondition condition);

}
