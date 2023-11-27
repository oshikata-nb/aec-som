/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.accountslistforreport;

import java.util.List;

/**
 * AccountsListForReportDaoクラス
 * @author t0011036
 */
public interface AccountsListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * AccountsListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<AccountsListForReport>
	 */
	List<AccountsListForReport> getListForReport(
			final AccountsListConditionForReport condition);
}
