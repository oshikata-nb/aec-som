/*
 * Created on Tue Feb 17 11:37:46 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositheader;

/**
 * TemporaryDepositHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryDepositHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryDepositHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryDepositHeader
	 * @return Insert件数
	 */
	int insert(TemporaryDepositHeader bean);

	/**
	 * Update.
	 * @param bean TemporaryDepositHeader
	 * @return Update件数
	 */
	int update(TemporaryDepositHeader bean);

	/**
	 * Delete.
	 * @param bean TemporaryDepositHeader
	 * @return Delete件数
	 */
	int delete(TemporaryDepositHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DEPOSIT_NO";

	/**
	 * エンティティ取得.
	 * @param depositNo depositNo
	 * @return TemporaryDepositHeader
	 */
	TemporaryDepositHeader getEntity(String depositNo);
}
