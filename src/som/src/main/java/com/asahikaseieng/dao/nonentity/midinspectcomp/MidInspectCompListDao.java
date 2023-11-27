/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.midinspectcomp;

import java.util.List;

/**
 * 中間品検査完了入力画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface MidInspectCompListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 中間品検査完了入力　検索メソッド
	 * @param condition 検索条件
	 * @return List<MidInspectCompList> 検索結果リスト
	 */
	List<MidInspectCompList> getSearchList(final MidInspectCompPagerCondition condition);

}
