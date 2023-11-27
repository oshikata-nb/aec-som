/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.tantorolealldelete;

/**
 * TantoRollAllDeleteDaoクラス
 * @author t0011036
 */
public interface TantoRoleAllDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.tantorolealldelete.TantoRoleAllDelete.class;

	/**
	 * TantoRollAllDeleteメソッド
	 * 
	 * @param bean bean
	 * @return int
	 */
	int delete(final TantoRoleAllDelete bean);
}
