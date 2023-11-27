/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirection;

import java.util.List;

/**
 * 包装実績－製造指図明細ポップアップ画面Daoインターフェース
 * @author tosco
 */
public interface PkgRdirectionDirectionDetailSearchListDao {

	/** BEANアノテーション */
	Class BEAN = PkgRdirectionDirectionDetailSearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * 製造指図明細検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDirectionDetailSearchList> 検索結果リスト
	 */
	List<PkgRdirectionDirectionDetailSearchList> getList
		(final PkgRdirectionDirectionDetailSearchListPagerCondition condition);
}
