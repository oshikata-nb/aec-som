/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritydetail;

/**
 * 操作権限マスタ更新用Daoクラス
 * @author t0011036
 */
public interface ControlAuthorityDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.controlauthoritydetail.ControlAuthorityDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean bean
	 * @return Insert件数
	 */
	int insert(ControlAuthorityDetail bean);

	/**
	 * Delete.
	 * @param bean bean
	 * @return Delete件数
	 */
	int delete(ControlAuthorityDetail bean);
}
