/*
 * Created on 2021/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportslipforreport;

import java.util.List;

/**
 * RepOrderSlipHeaderForReportDaoクラス
 * @author kanri-user
 */
public interface RepOrderImportSlipHeaderForReportDao {

	/** BEANアノテーション */
	Class<RepOrderImportSlipHeaderForReport> BEAN = com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipHeaderForReport.class;

	/** ARGSアノテーション getHeaderListForReport */
	String getHeaderListForReport_ARGS = "condition";

	/**
	 * RepOrderSlipHeaderForReportメソッド
	 * @param condition condition
	 * @return RepOrderImportSlipHeaderForReport
	 */
	List<RepOrderImportSlipHeaderForReport> getHeaderListForReport(final RepOrderImportSlipConditionForReport condition);
}
