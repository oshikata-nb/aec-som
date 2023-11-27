/*
 * Created on Thu Jan 22 15:02:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutsource;

/**
 * InoutSourceDaoインターフェース.
 * @author t0011036
 */
public interface InoutSourceDao {

	/** BEANアノテーション. */
	Class BEAN = InoutSource.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean InoutSource
	 * @return Insert件数
	 */
	int insert(InoutSource bean);

	/**
	 * Update.
	 * @param bean InoutSource
	 * @return Update件数
	 */
	int update(InoutSource bean);

	/**
	 * Delete.
	 * @param bean InoutSource
	 * @return Delete件数
	 */
	int delete(InoutSource bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "INOUT_SOURCE_NO";

	/**
	 * エンティティ取得.
	 * @param inoutSourceNo inoutSourceNo
	 * @return InoutSource
	 */
	InoutSource getEntity(final String inoutSourceNo);
}
