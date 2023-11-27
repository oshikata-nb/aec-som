/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport;

import java.util.List;

/**
 * UnitpriceListForReportDaoクラス
 * @author t0011036
 */
public interface UnitpriceListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * UnitpriceListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<UnitpriceListForReport>
	 */
	List<UnitpriceListForReport> getListForReport(
			final UnitpriceListConditionForReport condition);
}
