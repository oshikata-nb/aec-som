/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.organizationlist;

import java.util.List;

/**
 * OrganizationListDaoクラス
 * @author t0011036
 */
public interface OrganizationListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<OrganizationList>
	 */
	List<OrganizationList> getList(
			final OrganizationListPagerCondition condition);
}
