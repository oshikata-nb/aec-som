/*
 * Created on 2009/05/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.financialclasslist;

import java.util.List;

/**
 * FinancialClassListDaoクラス
 * @author t0011036
 */
public interface FinancialClassListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<FinancialClassList>
	 */
	List<FinancialClassList> getList(
			final FinancialClassListPagerCondition condition);
}
