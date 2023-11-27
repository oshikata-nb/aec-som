/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.viewauthoritydetail;

/**
 * 閲覧権限マスタ更新用Daoクラス
 * @author t0011036
 */
public interface ViewAuthorityDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.viewauthoritydetail.ViewAuthorityDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean bean
	 * @return Insert件数
	 */
	int insert(ViewAuthorityDetail bean);

	/**
	 * Delete.
	 * @param bean bean
	 * @return Delete件数
	 */
	int delete(ViewAuthorityDetail bean);
}
