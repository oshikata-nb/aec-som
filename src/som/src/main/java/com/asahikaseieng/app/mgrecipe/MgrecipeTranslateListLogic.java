/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.util.List;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeTranslateListPagerCondition;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方-翻訳検索 ロジッククラス interface.
 * @author tosco
 */
public interface MgrecipeTranslateListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<MgrecipeList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RecipeHeaderList> getSearchList(final MgrecipeTranslateListPagerCondition condition)
			throws NoDataException;
}
