/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;

/**
 * 消込入力一覧画面 検索用Daoクラス
 * 
 * @author tosco
 */
public interface ClaimEraserListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 消込入力一覧画面 検索メソッド
	 * @param condition 検索条件
	 * @return List
	 */
	List<ClaimEraserList> getSearchList(
			final ClaimEraserListPagerCondition condition);
}
