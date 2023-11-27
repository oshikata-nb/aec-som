/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport;

import java.util.List;

/**
 * RepApledgerHeaderDaoクラス
 * @author kanri-user
 */
public interface RepApledgerHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.debt.apledgerlistforreport.RepApledgerHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition, targetKbn";

	/**
	 * RepApledgerHeaderメソッド
	 * 
	 * @param condition condition
	 * @param targetKbn targetKbn
	 * @return RepApledgerHeader
	 */
	List<RepApledgerHeader> getListForReport(
			final RepApLedgerConditionForReport condition,
			final String targetKbn);
}
