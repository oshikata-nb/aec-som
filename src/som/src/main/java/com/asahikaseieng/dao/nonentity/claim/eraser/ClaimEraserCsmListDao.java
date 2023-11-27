/*
 * Created on 2008/10/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import java.util.List;



/**
 * 消込入力詳細画面(カスタマイズ) 
 * 消込トランザクション(カスタマイズ)登録更新用Daoインターフェース.
 * @author tosco
 */
public interface ClaimEraserCsmListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 消込入力一覧画面　検索メソッド
	 * @param condition 検索条件
	 * @return List
	 */
	List<ClaimEraserCsmList> getSearchList(final ClaimEraserCsmListPagerCondition condition);

}
