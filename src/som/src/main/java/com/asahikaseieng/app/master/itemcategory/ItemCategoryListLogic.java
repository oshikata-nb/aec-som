/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.itemcategory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryList;
import com.asahikaseieng.dao.nonentity.master.itemcategorylist.ItemCategoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目分類一覧ロジック interface.
 * @author t0011036
 */
public interface ItemCategoryListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ItemCategoryList>
	 */
	List<ItemCategoryList> getList(
			final ItemCategoryListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ItemCategoryListForReport>
	 */
	List<ItemCategoryListForReport> getListForReport(
			final ItemCategoryListConditionForReport condition);
}
