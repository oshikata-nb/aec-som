/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.nameslistforreport;

import java.util.List;

/**
 * NamesListForReportDaoクラス
 * @author t0011036
 */
public interface NamesListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * NamesListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<NamesListForReport>
	 */
	List<NamesListForReport> getListForReport(
			final NamesListConditionForReport condition);
}
