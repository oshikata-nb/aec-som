/*
 * Created on Fri Jan 16 15:04:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bumon;

/**
 * BumonDaoインターフェース.
 * @author t0011036
 */
public interface BumonDao {

	/** BEANアノテーション. */
	Class BEAN = Bumon.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Bumon
	 * @return Insert件数
	 */
	int insert(Bumon bean);

	/**
	 * Update.
	 * @param bean Bumon
	 * @return Update件数
	 */
	int update(Bumon bean);

	/**
	 * Delete.
	 * @param bean Bumon
	 * @return Delete件数
	 */
	int delete(Bumon bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SECTION_CD";

	/**
	 * エンティティ取得.
	 * @param sectionCd sectionCd
	 * @return Bumon
	 */
	Bumon getEntity(final String sectionCd);
}
