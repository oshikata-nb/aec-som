/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.util.List;

/**
 * 包装指図－検索画面Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgDirectionListDao {

	/** BEANアノテーション */
	Class<PkgDirectionList> BEAN = com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionList.class;

	/** ARGSアノテーション getList */
	String getSearchList_ARGS = "condition";
	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<PkgDirectionList> 検索結果リスト
	 */
	List<PkgDirectionList> getList(PkgDirectionListPagerCondition condition);
}
