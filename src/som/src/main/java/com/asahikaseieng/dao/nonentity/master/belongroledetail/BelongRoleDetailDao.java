/*
 * Created on 2009/03/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belongroledetail;

/**
 * BelongRoleDetailDaoクラス
 * @author t0011036
 */
public interface BelongRoleDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.belongroledetail.BelongRoleDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "organizationCd, postId";

	/**
	 * BelongRoleDetailメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @param postId postId
	 * @return BelongRoleDetail
	 */
	BelongRoleDetail getEntity(final String organizationCd, final String postId);
}
