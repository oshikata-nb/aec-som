/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetaillist;

import java.util.List;

/**
 * RoleDetailListDaoクラス
 * @author t0011036
 */
public interface RoleDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.roledetaillist.RoleDetailList.class;

	/** ARGSアノテーション getList */
	String getDetailList_ARGS = "roleId";

	/**
	 * Listメソッド
	 * 
	 * @param roleId roleId
	 * @return List<RoleDetailList>
	 */
	List<RoleDetailList> getList(final String roleId);
}
