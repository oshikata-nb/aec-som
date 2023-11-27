/*
 * Created on 2021/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportslipforreport;

import java.util.List;

/**
 * RepOrderSlipDetailForReportDaoクラス
 * @author kanri-user
 */
public interface RepOrderImportSlipDetailForReportDao {

	/** BEANアノテーション */
	Class<RepOrderImportSlipDetailForReport> BEAN = com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipDetailForReport.class;

	/** ARGSアノテーション getDetailListForReport */
	String getDetailListForReport_ARGS = "condition";

	/**
	 * RepOrderSlipDetailForReportメソッド
	 * @param condition condition
	 * @return RepOrderImportSlipDetailForReport
	 */
	List<RepOrderImportSlipDetailForReport> getDetailListForReport(final RepOrderImportSlipConditionForReport condition);
}
