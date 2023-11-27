/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylist;

import java.util.List;

/**
 * CompanyListDaoクラス
 * @author t0011036
 */
public interface CompanyListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.companylist.CompanyList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<CompanyList>
	 */
	List<CompanyList> getList(final CompanyListPagerCondition condition);
}
