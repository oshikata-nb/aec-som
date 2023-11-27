/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.util.List;

/**
 * 包装実績－ロット検索画面Daoインターフェース
 * @author tosco
 */
public interface PkgRdirectionLotInventorySearchListDao {

	/** BEANアノテーション */
	Class BEAN = PkgRdirectionLotInventorySearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * ロット一覧検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<PkgRdirectionLotInventorySearchList> 検索結果リスト
	 */
	List<PkgRdirectionLotInventorySearchList> getList
		(final PkgRdirectionLotInventorySearchListPagerCondition condition);
}
