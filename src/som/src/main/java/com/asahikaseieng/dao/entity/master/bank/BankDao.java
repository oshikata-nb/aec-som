/*
 * Created on Fri Jan 16 10:23:58 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bank;

/**
 * BankDaoインターフェース.
 * @author t0011036
 */
public interface BankDao {

	/** BEANアノテーション. */
	Class BEAN = Bank.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Bank
	 * @return Insert件数
	 */
	int insert(Bank bean);

	/**
	 * Update.
	 * @param bean Bank
	 * @return Update件数
	 */
	int update(Bank bean);

	/**
	 * Delete.
	 * @param bean Bank
	 * @return Delete件数
	 */
	int delete(Bank bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "BANK_MASTER_CD";

	/**
	 * エンティティ取得.
	 * @param bankMasterCd bankMasterCd
	 * @return Bank
	 */
	Bank getEntity(final String bankMasterCd);
}
