/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginrolelist;

import java.util.List;

/**
 * LoginRoleListDaoクラス
 * @author t0011036
 */
public interface LoginRoleListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.loginrolelist.LoginRoleList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "tantoCd";

	/**
	 * Listメソッド
	 * 
	 * @param tantoCd tantoCd
	 * @return List<LoginRoleList>
	 */
	List<LoginRoleList> getList(final String tantoCd);
}
