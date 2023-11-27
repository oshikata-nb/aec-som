/*
 * Created on Wed Feb 04 09:34:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.depositheader;

/**
 * DepositHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface DepositHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = DepositHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DepositHeader
	 * @return Insert件数
	 */
	int insert(DepositHeader bean);

	/**
	 * Update.
	 * @param bean DepositHeader
	 * @return Update件数
	 */
	int update(DepositHeader bean);

	/**
	 * Delete.
	 * @param bean DepositHeader
	 * @return Delete件数
	 */
	int delete(DepositHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DEPOSIT_NO";

	/**
	 * エンティティ取得.
	 * @param depositNo depositNo
	 * @return DepositHeader
	 */
	DepositHeader getEntity(String depositNo);
}
