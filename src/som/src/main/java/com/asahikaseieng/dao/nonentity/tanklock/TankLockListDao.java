/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.tanklock;

import java.util.List;

/**
 * 調合タンク底弁インターロック解除/再設定一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface TankLockListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.tanklock.TankLockList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<TankLockList> 検索結果リスト
	 */
	List<TankLockList> getSearchList(final TankLockPagerCondition condition);

}
