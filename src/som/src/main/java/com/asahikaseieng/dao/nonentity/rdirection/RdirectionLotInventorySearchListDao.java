/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.rdirection;

import java.util.List;

/**
 * 製造実績－ロット検索ポップアップ画面Daoインターフェース
 * @author tosco
 */
public interface RdirectionLotInventorySearchListDao {

	/** BEANアノテーション */
	Class BEAN = RdirectionLotInventorySearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * ロット検索一覧検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RdirectionLotInventorySearchList> 検索結果リスト
	 */
	List<RdirectionLotInventorySearchList> getList
		(final RdirectionLotInventorySearchListPagerCondition condition);

}
