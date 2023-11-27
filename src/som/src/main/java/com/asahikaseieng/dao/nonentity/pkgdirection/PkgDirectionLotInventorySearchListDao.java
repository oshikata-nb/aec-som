/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.util.List;

/**
 * 包装指図－在庫確認ポップアップ画面Daoインターフェース
 * @author tosco
 */
public interface PkgDirectionLotInventorySearchListDao {

	/** BEANアノテーション */
	Class BEAN = PkgDirectionLotInventorySearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 在庫確認一覧検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<PkgDirectionLotInventorySearchList> 検索結果リスト
	 */
	List<PkgDirectionLotInventorySearchList> getList
		(final PkgDirectionLotInventorySearchListPagerCondition condition);
}
