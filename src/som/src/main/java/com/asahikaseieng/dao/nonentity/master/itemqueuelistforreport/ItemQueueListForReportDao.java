/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

import java.util.List;

/**
 * ItemQueueListForReportDaoクラス
 * @author t0011036
 */
public interface ItemQueueListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReport.class;

	/** ARGSアノテーション getActivateListForReport */
	String getActivateListForReport_ARGS = "condition";

	/**
	 * getActivateListForReportメソッド(未使用)
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getActivateListForReport(
			final ItemQueueListConditionForReport condition);

	/** ARGSアノテーション getInactivateListForReport */
	String getInactivateListForReport_ARGS = "condition";

	/**
	 * getInactivateListForReportメソッド(未使用)
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getInactivateListForReport(
			final ItemQueueListConditionForReport condition);

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * getListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getCommonAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getArticleAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getProductAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getPurchaseAttributeQueueListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getComponentInformationQueueListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getRecipeHeaderListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getLabelListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getItemRemarkListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * ItemQueueListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getChangeHistoryListForReport(
			final ItemQueueListConditionForReport condition);
}
