/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport;

import java.util.List;

/**
 * ItemCategoryListForReportDaoクラス
 * @author t0011036
 */
public interface ItemCategoryListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport.ItemCategoryListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ItemCategoryListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return ItemCategoryListForReport
	 */
	List<ItemCategoryListForReport> getListForReport(
			final ItemCategoryListConditionForReport condition);
}
