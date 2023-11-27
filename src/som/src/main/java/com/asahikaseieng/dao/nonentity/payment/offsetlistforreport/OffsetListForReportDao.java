/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import java.util.List;

/**
 * OffsetListForReportDaoクラス
 * @author t0011036
 */
public interface OffsetListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * OffsetListForReportメソッド
	 * @param condition 検索条件
	 * @return List<OffsetListForReport>
	 */
	List<OffsetListForReport> getListForReport(
			final OffsetListConditionForReport condition);
}
