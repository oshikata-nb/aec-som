/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirection;

import java.util.List;

/**
 * 包装指図－製造指図明細ポップアップ画面Daoインターフェース
 * @author tosco
 */
public interface PkgDirectionDirectionDetailSearchListDao {

	/** BEANアノテーション */
	Class BEAN = PkgDirectionDirectionDetailSearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 製造指図明細検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<PkgDirectionDirectionDetailSearchList> 検索結果リスト
	 */
	List<PkgDirectionDirectionDetailSearchList> getList
		(final PkgDirectionDirectionDetailSearchListPagerCondition condition);
}
