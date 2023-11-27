/*
 * Created on Wed Feb 04 16:12:47 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterunsoten;

/**
 * MasterUnsotenDaoインターフェース.
 * @author kanri-user
 */
public interface MasterUnsotenDao {

	/** BEANアノテーション. */
	Class BEAN = MasterUnsoten.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterUnsoten
	 * @return Insert件数
	 */
	int insert(MasterUnsoten bean);

	/**
	 * Update.
	 * @param bean MasterUnsoten
	 * @return Update件数
	 */
	int update(MasterUnsoten bean);

	/**
	 * Delete.
	 * @param bean MasterUnsoten
	 * @return Delete件数
	 */
	int delete(MasterUnsoten bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "UNSOTENCODE";

	/**
	 * エンティティ取得.
	 * @param unsotencode unsotencode
	 * @return MasterUnsoten
	 */
	MasterUnsoten getEntity(String unsotencode);
}
