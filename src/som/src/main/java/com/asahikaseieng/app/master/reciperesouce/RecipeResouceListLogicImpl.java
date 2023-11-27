/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesouce;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceListDao;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelist.RecipeResouceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucelistforreport.RecipeResouceListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備一覧ロジック 実装クラス.
 * @author t0011036
 */
public class RecipeResouceListLogicImpl implements RecipeResouceListLogic {

	private RecipeResouceListDao recipeResouceListDao;

	private RecipeResouceListForReportDao recipeResouceListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceListLogicImpl() {
	}

	/**
	 * 設備一覧検索
	 * @param condition 検索条件
	 * @return List<RecipeResouceList>
	 * @throws NoDataException NoDataException
	 */
	public List<RecipeResouceList> getList(
			final RecipeResouceListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<RecipeResouceList> list = recipeResouceListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final RecipeResouceListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 設備一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<RecipeResouceListForReport>
	 */
	public List<RecipeResouceListForReport> getListForReport(
			final RecipeResouceListConditionForReport condition) {
		List<RecipeResouceListForReport> list = recipeResouceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * recipeResouceListDaoを設定します。
	 * @param recipeResouceListDao recipeResouceListDao
	 */
	public void setRecipeResouceListDao(
			final RecipeResouceListDao recipeResouceListDao) {
		this.recipeResouceListDao = recipeResouceListDao;
	}

	/**
	 * recipeResouceListForReportDaoを設定します。
	 * @param recipeResouceListForReportDao recipeResouceListForReportDao
	 */
	public void setRecipeResouceListForReportDao(
			final RecipeResouceListForReportDao recipeResouceListForReportDao) {
		this.recipeResouceListForReportDao = recipeResouceListForReportDao;
	}
}
