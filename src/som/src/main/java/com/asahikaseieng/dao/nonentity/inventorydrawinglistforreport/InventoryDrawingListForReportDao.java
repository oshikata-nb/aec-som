/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport;

import java.util.List;

/**
 * InventoryDrawingListForReportDaoクラス
 * @author t0011036
 */
public interface InventoryDrawingListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "itemCd, otherCompanyCd1";

	/**
	 * InventoryDrawingListForReportメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryDrawingListForReport
	 */
	List<InventoryDrawingListForReport> getListForReport(final String itemCd,
			final String otherCompanyCd1);
}
