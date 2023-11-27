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
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeListLogicImpl implements MgrecipeListLogic {
	/** 処方ヘッダDA0 */
	private RecipeHeaderListDao recipeHeaderListDao;


	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeListLogicImpl() {
	}

	/**
	 * 基本処方検索一覧検索処理
	 *
	 * @param condition 検索条件
	 * @return List<MgrecipeList> 検索結果リスト

	 * @throws NoDataException データが存在しない例外

	 */
	public List<RecipeHeaderList> getSearchList(final MgrecipeListPagerCondition condition) throws NoDataException {
		checkParams(condition);
		List<RecipeHeaderList> list = recipeHeaderListDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final MgrecipeListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダーDAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダーDAO
	 */
	public void setRecipeHeaderListDao(final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
	}

}
