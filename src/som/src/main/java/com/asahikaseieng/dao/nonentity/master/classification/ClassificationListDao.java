/*
 * Created on 2008/07/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classification;

import java.util.List;

/**
 * 分類マスタ一覧 Daoクラス
 * @author tosco
 */
public interface ClassificationListDao {

	/** BEANアノテーション. */
	Class BEAN = ClassificationList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 検索Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<ClassificationList> getSearchList(
			final ClassificationPagerCondition condition);
}
