/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyingapprovalforreport;

import java.util.List;

/**
 * BuyingApprovalListForReportDaoクラス
 * @author kanri-user
 */
public interface BuyingApprovalListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<BuyingApprovalListForReport>
	 */
	List<BuyingApprovalListForReport> getReportList(
			final BuyingApprovalListConditionForReport condition);
}
