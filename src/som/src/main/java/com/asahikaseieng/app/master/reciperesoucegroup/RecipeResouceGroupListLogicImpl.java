/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reciperesoucegroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupList;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupListDao;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplist.RecipeResouceGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListForReport;
import com.asahikaseieng.dao.nonentity.master.reciperesoucegrouplistforreport.RecipeResouceGroupListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 設備グループ一覧ロジック 実装クラス.
 * @author t0011036
 */
public class RecipeResouceGroupListLogicImpl implements
		RecipeResouceGroupListLogic {

	private RecipeResouceGroupListDao recipeResouceGroupListDao;

	private RecipeResouceGroupListForReportDao recipeResouceGroupListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public RecipeResouceGroupListLogicImpl() {
	}

	/**
	 * 設備グループ一覧検索
	 * @param condition 検索条件
	 * @return List<RecipeResouceGroupList>
	 * @throws NoDataException NoDataException
	 */
	public List<RecipeResouceGroupList> getList(
			final RecipeResouceGroupListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<RecipeResouceGroupList> list = recipeResouceGroupListDao
				.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final RecipeResouceGroupListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 設備グループ一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<RecipeResouceGroupListForReport>
	 */
	public List<RecipeResouceGroupListForReport> getListForReport(
			final RecipeResouceGroupListConditionForReport condition) {
		List<RecipeResouceGroupListForReport> list = recipeResouceGroupListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * recipeResouceGroupListDaoを設定します。
	 * @param recipeResouceGroupListDao recipeResouceGroupListDao
	 */
	public void setRecipeResouceGroupListDao(
			final RecipeResouceGroupListDao recipeResouceGroupListDao) {
		this.recipeResouceGroupListDao = recipeResouceGroupListDao;
	}

	/**
	 * recipeResouceGroupListForReportDaoを設定します。
	 * @param recipeResouceGroupListForReportDao
	 *            recipeResouceGroupListForReportDao
	 */
	public void setRecipeResouceGroupListForReportDao(
			final RecipeResouceGroupListForReportDao recipeResouceGroupListForReportDao) {
		this.recipeResouceGroupListForReportDao = recipeResouceGroupListForReportDao;
	}
}
