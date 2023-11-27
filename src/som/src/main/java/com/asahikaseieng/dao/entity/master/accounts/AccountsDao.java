/*
 * Created on Wed Jan 14 18:26:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.accounts;

/**
 * AccountsDaoインターフェース.
 * @author t0011036
 */
public interface AccountsDao {

	/** BEANアノテーション. */
	Class BEAN = Accounts.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Accounts
	 * @return Insert件数
	 */
	int insert(Accounts bean);

	/**
	 * Update.
	 * @param bean Accounts
	 * @return Update件数
	 */
	int update(Accounts bean);

	/**
	 * Delete.
	 * @param bean Accounts
	 * @return Delete件数
	 */
	int delete(Accounts bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ACCOUNTS_CD";

	/**
	 * エンティティ取得.
	 * @param accountsCd accountsCd
	 * @return Accounts
	 */
	Accounts getEntity(final String accountsCd);
}
