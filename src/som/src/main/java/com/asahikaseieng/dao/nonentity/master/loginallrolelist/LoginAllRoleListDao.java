/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginallrolelist;

import java.util.List;

/**
 * LoginAllRoleListDaoクラス
 * @author t0011036
 */
public interface LoginAllRoleListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.loginallrolelist.LoginAllRoleList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "";

	/**
	 * Listメソッド
	 * 
	 * @return List<LoginAllRoleList>
	 */
	List<LoginAllRoleList> getList();
}
