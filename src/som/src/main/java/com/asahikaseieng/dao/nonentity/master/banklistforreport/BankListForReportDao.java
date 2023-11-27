/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklistforreport;

import java.util.List;

/**
 * BankListForReportDaoクラス
 * @author t0011036
 */
public interface BankListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * BankListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<BankListForReport>
	 */
	List<BankListForReport> getListForReport(
			final BankListConditionForReport condition);
}
