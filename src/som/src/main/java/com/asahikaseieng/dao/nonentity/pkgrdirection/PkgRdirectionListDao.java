/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.util.List;

/**
 * 包装実績－検索画面Daoインターフェース.
 *
 * @author tosco
 */
public interface PkgRdirectionListDao {

	/** BEANアノテーション */
	Class<PkgRdirectionList> BEAN = com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionList.class;

	/** ARGSアノテーション getList */
	String getSearchList_ARGS = "condition";
	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<PkgDirectionList> 検索結果リスト
	 */
	List<PkgRdirectionList> getList(PkgRdirectionListPagerCondition condition);
}
