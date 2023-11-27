/*
 * Created on 2009/10/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderslipforreport;

import java.util.List;

/**
 * RepOrderSlipDetailForReportDaoクラス
 * @author kanri-user
 */
public interface RepOrderSlipDetailForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.orderslipforreport.RepOrderSlipDetailForReport.class;

	/** ARGSアノテーション getDetailListForReport */
	String getDetailListForReport_ARGS = "condition";

	/**
	 * RepOrderSlipDetailForReportメソッド
	 * @param condition condition
	 * @return RepOrderSlipDetailForReport
	 */
	List<RepOrderSlipDetailForReport> getDetailListForReport(
			final RepOrderSlipConditionForReport condition);
}
