/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.util.List;

/**
 * 外注原材料投入実績一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface MaterialRinputListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.materialrinput.MaterialRinputList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<MaterialRinputList> 検索結果リスト
	 */
	List<MaterialRinputList> getSearchList(final MaterialRinputListPagerCondition condition);

}
