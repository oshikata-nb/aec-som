/*
 * Created on 2009/07/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport;

import java.util.List;

/**
 * RepArledgerDetailDaoクラス
 * @author kanri-user
 */
public interface RepArledgerDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArledgerDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition, targetKbn";

	/**
	 * RepArledgerDetailメソッド
	 * 
	 * @param condition condition
	 * @param targetKbn targetKbn
	 * @return RepArledgerDetail
	 */
	List<RepArledgerDetail> getListForReport(
			final RepArLedgerConditionForReport condition,
			final String targetKbn);
}
