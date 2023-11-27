/*
 * Created on Wed Feb 04 16:12:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastershoricode;

/**
 * MasterShoricodeDaoインターフェース.
 * @author kanri-user
 */
public interface MasterShoricodeDao {

	/** BEANアノテーション. */
	Class BEAN = MasterShoricode.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterShoricode
	 * @return Insert件数
	 */
	int insert(MasterShoricode bean);

	/**
	 * Update.
	 * @param bean MasterShoricode
	 * @return Update件数
	 */
	int update(MasterShoricode bean);

	/**
	 * Delete.
	 * @param bean MasterShoricode
	 * @return Delete件数
	 */
	int delete(MasterShoricode bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SHIORICODE";

	/**
	 * エンティティ取得.
	 * @param shioricode shioricode
	 * @return MasterShoricode
	 */
	MasterShoricode getEntity(String shioricode);
}
