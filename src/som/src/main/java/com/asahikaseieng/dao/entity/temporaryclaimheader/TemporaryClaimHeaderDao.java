/*
 * Created on Tue Feb 17 14:03:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimheader;

/**
 * TemporaryClaimHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryClaimHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryClaimHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryClaimHeader
	 * @return Insert件数
	 */
	int insert(TemporaryClaimHeader bean);

	/**
	 * Update.
	 * @param bean TemporaryClaimHeader
	 * @return Update件数
	 */
	int update(TemporaryClaimHeader bean);

	/**
	 * Delete.
	 * @param bean TemporaryClaimHeader
	 * @return Delete件数
	 */
	int delete(TemporaryClaimHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CLAIM_NO";

	/**
	 * エンティティ取得.
	 * @param claimNo claimNo
	 * @return TemporaryClaimHeader
	 */
	TemporaryClaimHeader getEntity(String claimNo);
}
