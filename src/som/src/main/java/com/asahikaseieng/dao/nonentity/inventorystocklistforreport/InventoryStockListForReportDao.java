/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklistforreport;

import java.util.List;

/**
 * InventoryStockListForReportDaoクラス
 * @author t0011036
 */
public interface InventoryStockListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "itemCd, otherCompanyCd1";

	/**
	 * InventoryStockListForReportメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryStockListForReport
	 */
	List<InventoryStockListForReport> getListForReport(final String itemCd,
			final String otherCompanyCd1);
}
