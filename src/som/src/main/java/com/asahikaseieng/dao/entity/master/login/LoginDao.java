/*
 * Created on Wed Feb 04 18:16:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.login;

/**
 * LoginDaoインターフェース.
 * @author t0011036
 */
public interface LoginDao {

	/** BEANアノテーション. */
	Class BEAN = Login.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Login
	 * @return Insert件数
	 */
	int insert(Login bean);

	/**
	 * Update.
	 * @param bean Login
	 * @return Update件数
	 */
	int update(Login bean);

	/**
	 * Delete.
	 * @param bean Login
	 * @return Delete件数
	 */
	int delete(Login bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TANTO_CD";

	/**
	 * エンティティ取得.
	 * @param tantoCd tantoCd
	 * @return Login
	 */
	Login getEntity(final String tantoCd);
}
