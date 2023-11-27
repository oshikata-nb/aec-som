/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlistforreport;

import java.util.List;

/**
 * InoutRecordListForReportDaoクラス
 * @author kanri-user
 */
public interface InoutRecordListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * InoutRecordListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordListForReport>
	 */
	List<InoutRecordListForReport> getListForReport(
			final InoutRecordReportCondition condition);
}
