/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.beforehandmeltlbl;

import java.util.List;

/**
 * 予備溶解ラベル発行画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface BeforehandMeltLblListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.beforehandmeltlbl.BeforehandMeltLblList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 予備溶解ラベル発行画面_一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<BeforehandMeltLblList> 検索結果リスト
	 */
	List<BeforehandMeltLblList> getSearchList(final BeforehandMeltLblPagerCondition condition);

}
