/*
 * Created on Wed Apr 29 11:48:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbtrailer;

/**
 * FbTrailerDaoインターフェース.
 * @author t0011036
 */
public interface FbTrailerDao {

	/** BEANアノテーション. */
	Class BEAN = FbTrailer.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean FbTrailer
	 * @return Insert件数
	 */
	int insert(FbTrailer bean);

	/**
	 * Update.
	 * @param bean FbTrailer
	 * @return Update件数
	 */
	int update(FbTrailer bean);

	/**
	 * Delete.
	 * @param bean FbTrailer
	 * @return Delete件数
	 */
	int delete(FbTrailer bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PAYMENT_DATE";

	/**
	 * エンティティ取得.
	 * @param paymentDate paymentDate
	 * @return FbTrailer
	 */
	FbTrailer getEntity(String paymentDate);
}
