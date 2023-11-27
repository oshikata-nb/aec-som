/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslist;

import java.util.List;

/**
 * SalestermsListDaoクラス
 * @author t0011036
 */
public interface SalesTermsListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<SalesTermsList>
	 */
	List<SalesTermsList> getList(final SalesTermsListPagerCondition condition);
}
