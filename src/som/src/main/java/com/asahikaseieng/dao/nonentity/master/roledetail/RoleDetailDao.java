/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.roledetail;

/**
 * RoleDetailDaoクラス
 * @author t0011036
 */
public interface RoleDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.roledetail.RoleDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "roleId";

	/**
	 * RoleDetailメソッド
	 * 
	 * @param roleId roleId
	 * @return RoleDetail
	 */
	RoleDetail getEntity(final String roleId);
}
