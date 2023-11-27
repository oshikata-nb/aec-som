/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import java.util.List;


/**
 * OffsetListDao．グループ間相殺入力
 * @author tosco
 */
public interface OffsetListDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offset.OffsetList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getSearchList(). */
	String getSearchList_ARGS = "condition";

	/**
	 * エンティティ取得.
	 * @param condition 検索条件
	 * @return List<OffsetList> 支払予定一覧結果
	 */
	List<OffsetList> getSearchList(OffsetPagerCondition condition);


}
