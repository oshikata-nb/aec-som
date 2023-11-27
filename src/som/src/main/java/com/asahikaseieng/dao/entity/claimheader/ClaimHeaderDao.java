/*
 * Created on Wed Feb 04 09:31:06 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.claimheader;

/**
 * ClaimHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface ClaimHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = ClaimHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ClaimHeader
	 * @return Insert件数
	 */
	int insert(ClaimHeader bean);

	/**
	 * Update.
	 * @param bean ClaimHeader
	 * @return Update件数
	 */
	int update(ClaimHeader bean);

	/**
	 * Delete.
	 * @param bean ClaimHeader
	 * @return Delete件数
	 */
	int delete(ClaimHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CLAIM_NO";

	/**
	 * エンティティ取得.
	 * @param claimNo claimNo
	 * @return ClaimHeader
	 */
	ClaimHeader getEntity(String claimNo);
}
