/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.logindetail;

/**
 * LoginDetailDaoクラス
 * @author t0011036
 */
public interface LoginDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail.class;

	/** ARGSアノテーション getEntity */
	String getDetailEntity_ARGS = "tantoCd";

	/**
	 * LoginDetailメソッド
	 * 
	 * @param tantoCd tantoCd
	 * @return LoginDetail
	 */
	LoginDetail getEntity(final String tantoCd);
}
