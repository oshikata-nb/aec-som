/*
 * Created on Wed Feb 04 16:12:37 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.mastertantosha;

/**
 * MasterTantoshaDaoインターフェース.
 * @author kanri-user
 */
public interface MasterTantoshaDao {

	/** BEANアノテーション. */
	Class BEAN = MasterTantosha.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterTantosha
	 * @return Insert件数
	 */
	int insert(MasterTantosha bean);

	/**
	 * Update.
	 * @param bean MasterTantosha
	 * @return Update件数
	 */
	int update(MasterTantosha bean);

	/**
	 * Delete.
	 * @param bean MasterTantosha
	 * @return Delete件数
	 */
	int delete(MasterTantosha bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TANTOCODE";

	/**
	 * エンティティ取得.
	 * @param tantocode tantocode
	 * @return MasterTantosha
	 */
	MasterTantosha getEntity(String tantocode);
}
