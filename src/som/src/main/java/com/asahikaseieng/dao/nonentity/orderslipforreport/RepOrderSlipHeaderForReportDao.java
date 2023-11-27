/*
 * Created on 2009/10/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderslipforreport;

import java.util.List;

/**
 * RepOrderSlipHeaderForReportDaoクラス
 * @author kanri-user
 */
public interface RepOrderSlipHeaderForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipHeaderForReport.class;

	/** ARGSアノテーション getHeaderListForReport */
	String getHeaderListForReport_ARGS = "condition";

	/**
	 * RepOrderSlipHeaderForReportメソッド
	 * @param condition condition
	 * @return RepOrderSlipHeaderForReport
	 */
	List<RepOrderSlipHeaderForReport> getHeaderListForReport(
			final RepOrderSlipConditionForReport condition);
}
