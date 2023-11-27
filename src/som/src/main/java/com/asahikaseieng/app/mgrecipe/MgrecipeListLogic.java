/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeListPagerCondition;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方検索 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<MgrecipeList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RecipeHeaderList> getSearchList(final MgrecipeListPagerCondition condition)
			throws NoDataException;

}
