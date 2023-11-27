/*
 * Created on 2013/05/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repcarryshippingforreport;

import java.util.List;

/**
 * RepCarryShippingForReportHeaderDaoクラス
 * @author t1344224
 */
public interface RepCarryShippingForReportHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportHeader.class;

	/** ARGSアノテーション getHeaderSerchList */
	String getHeaderSerchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<RepCarryShippingForReportHeader> getHeaderSerchList(
			RepCarryShippingForReportCondition condition);
}
