/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.grecipe.GrecipeListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 原処方-一覧検索 ロジッククラス interface.
 * @author tosco
 */
public interface GrecipeListLogic {

	/**
	 * 原処方-一覧検索処理
	 *
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<GrecipeListBean> getSearchList(
		GrecipeListPagerCondition condition) throws NoDataException;
}
