/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetaillist;

import java.util.List;

/**
 * BalanceDetailListDaoクラス
 * @author t0011036
 */
public interface BalanceDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancedetaillist.BalanceDetailList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "balanceCd";

	/**
	 * Listメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @return List<BalanceDetailList>
	 */
	List<BalanceDetailList> getList(final String balanceCd);
}
