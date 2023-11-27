/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlogin;

/**
 * LoginLoginDaoクラス
 * @author t0011036
 */
public interface LoginLoginDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "tantoCd";

	/**
	 * LoginLoginメソッド
	 * 
	 * @param tantoCd tantoCd
	 * @return LoginLogin
	 */
	LoginLogin getEntity(final String tantoCd);
}
