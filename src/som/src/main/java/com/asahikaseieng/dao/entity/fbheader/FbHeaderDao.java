/*
 * Created on Tue Apr 28 09:03:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbheader;

/**
 * FbHeaderDaoインターフェース.
 * @author t0011036
 */
public interface FbHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = FbHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean FbHeader
	 * @return Insert件数
	 */
	int insert(FbHeader bean);

	/**
	 * Update.
	 * @param bean FbHeader
	 * @return Update件数
	 */
	int update(FbHeader bean);

	/**
	 * Delete.
	 * @param bean FbHeader
	 * @return Delete件数
	 */
	int delete(FbHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PAYMENT_DATE";

	/**
	 * エンティティ取得.
	 * @param paymentDate paymentDate
	 * @return FbHeader
	 */
	FbHeader getEntity(String paymentDate);
}
