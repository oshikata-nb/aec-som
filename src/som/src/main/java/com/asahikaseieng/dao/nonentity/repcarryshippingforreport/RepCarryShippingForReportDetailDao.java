/*
 * Created on 2013/05/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repcarryshippingforreport;

import java.util.List;

/**
 * RepCarryShippingForReportDetailDaoクラス
 * @author t1344224
 */
public interface RepCarryShippingForReportDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportDetail.class;

	/** ARGSアノテーション getDetailSerchList */
	String getDetailSerchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<RepCarryShippingForReportDetail> getDetailSerchList(
			final RepCarryShippingForReportCondition condition);
}
