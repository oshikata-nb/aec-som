/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport;

import java.util.List;

/**
 * RepApledgerDetailDaoクラス
 * @author kanri-user
 */
public interface RepApledgerDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition, targetKbn";

	/**
	 * RepApledgerDetailメソッド
	 * 
	 * @param condition condition
	 * @param targetKbn targetKbn
	 * @return RepApledgerDetail
	 */
	List<RepApledgerDetail> getListForReport(
			final RepApLedgerConditionForReport condition,
			final String targetKbn);
}
