/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

import java.util.List;

/**
 * InquiryInputListForReportDaoクラス
 * @author t0011036
 */
public interface InquiryInputListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * InquiryInputListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<InquiryInputListForReport>
	 */
	List<InquiryInputListForReport> getListForReport(
			final InquiryInputListConditionForReport condition);
}
