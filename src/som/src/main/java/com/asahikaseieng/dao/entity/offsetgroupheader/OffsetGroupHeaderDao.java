/*
 * Created on Fri Jan 23 14:16:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.offsetgroupheader;

/**
 * OffsetGroupHeaderDaoインターフェース.
 * @author t0011036
 */
public interface OffsetGroupHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = OffsetGroupHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OffsetGroupHeader
	 * @return Insert件数
	 */
	int insert(OffsetGroupHeader bean);

	/**
	 * Update.
	 * @param bean OffsetGroupHeader
	 * @return Update件数
	 */
	int update(OffsetGroupHeader bean);

	/**
	 * Delete.
	 * @param bean OffsetGroupHeader
	 * @return Delete件数
	 */
	int delete(OffsetGroupHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_NO";

	/**
	 * エンティティ取得.
	 * @param offsetNo offsetNo
	 * @return OffsetGroupHeader
	 */
	OffsetGroupHeader getEntity(final String offsetNo);
}
