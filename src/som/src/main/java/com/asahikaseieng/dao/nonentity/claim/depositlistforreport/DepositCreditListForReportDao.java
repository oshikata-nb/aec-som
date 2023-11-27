/*
 * Created on 2009/09/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositlistforreport;

import java.util.List;

/**
 * DepositCreditListForReportDaoクラス
 * @author t0011036
 */
public interface DepositCreditListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * DepositCreditListForReportメソッド
	 * @param condition 検索条件
	 * @return List<DepositCreditListForReport>
	 */
	List<DepositCreditListForReport> getListForReport(
			final DepositCreditListConditionForReport condition);
}
