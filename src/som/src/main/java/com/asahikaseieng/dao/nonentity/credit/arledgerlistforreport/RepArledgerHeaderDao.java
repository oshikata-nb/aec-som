/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport;

import java.util.List;

/**
 * RepArledgerHeaderDaoクラス
 * @author kanri-user
 */
public interface RepArledgerHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition, targetKbn";

	/**
	 * RepArledgerHeaderメソッド
	 * 
	 * @param condition condition
	 * @param targetKbn targetKbn
	 * @return RepArledgerHeader
	 */
	List<RepArledgerHeader> getListForReport(
			final RepArLedgerConditionForReport condition,
			final String targetKbn);
}
