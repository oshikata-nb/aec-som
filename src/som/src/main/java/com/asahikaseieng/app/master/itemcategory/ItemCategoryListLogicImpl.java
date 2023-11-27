/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryList;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryListDao;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListForReport;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目分類一覧ロジック 実装クラス.
 * @author t0011036
 */
public class ItemCategoryListLogicImpl implements ItemCategoryListLogic {

	private ItemCategoryListDao itemCategoryListDao;

	private ItemCategoryListForReportDao itemCategoryListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ItemCategoryListLogicImpl() {
	}

	/**
	 * 品目分類一覧検索
	 * @param condition 検索条件
	 * @return List<ItemCategoryList>
	 * @throws NoDataException NoDataException
	 */
	public List<ItemCategoryList> getList(
			final ItemCategoryListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ItemCategoryList> list = itemCategoryListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ItemCategoryListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 品目分類一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ItemCategoryListForReport>
	 */
	public List<ItemCategoryListForReport> getListForReport(
			final ItemCategoryListConditionForReport condition) {
		List<ItemCategoryListForReport> list = itemCategoryListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemCategoryListDaoを設定します。
	 * @param itemCategoryListDao itemCategoryListDao
	 */
	public void setItemCategoryListDao(
			final ItemCategoryListDao itemCategoryListDao) {
		this.itemCategoryListDao = itemCategoryListDao;
	}

	/**
	 * itemCategoryListForReportDaoを設定します。
	 * @param itemCategoryListForReportDao itemCategoryListForReportDao
	 */
	public void setItemCategoryListForReportDao(
			final ItemCategoryListForReportDao itemCategoryListForReportDao) {
		this.itemCategoryListForReportDao = itemCategoryListForReportDao;
	}
}
