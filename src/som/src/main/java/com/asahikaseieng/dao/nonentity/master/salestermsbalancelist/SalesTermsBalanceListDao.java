/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsbalancelist;

import java.util.List;

/**
 * SalesTermsBalanceListDaoクラス
 * @author t0011036
 */
public interface SalesTermsBalanceListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermsbalancelist.SalesTermsBalanceList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "balanceCd";

	/**
	 * Listメソッド
	 * 
	 * @param balanceCd balanceCd
	 * @return List<SalesTermsBalanceList>
	 */
	List<SalesTermsBalanceList> getList(final String balanceCd);
}
