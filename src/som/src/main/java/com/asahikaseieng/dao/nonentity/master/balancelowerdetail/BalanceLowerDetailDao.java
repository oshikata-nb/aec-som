/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelowerdetail;

/**
 * BalanceLowerDetailDaoクラス
 * @author t0011036
 */
public interface BalanceLowerDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.balancelowerdetail.BalanceLowerDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "upperBalanceCd";

	/**
	 * BalanceLowerDetailメソッド
	 * 
	 * @param upperBalanceCd upperBalanceCd
	 * @return BalanceLowerDetail
	 */
	BalanceLowerDetail getEntity(final String upperBalanceCd);
}
