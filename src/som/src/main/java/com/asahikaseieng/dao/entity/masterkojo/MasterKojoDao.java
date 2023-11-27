/*
 * Created on Wed Feb 04 16:12:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterkojo;

/**
 * MasterKojoDaoインターフェース.
 * @author kanri-user
 */
public interface MasterKojoDao {

	/** BEANアノテーション. */
	Class BEAN = MasterKojo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterKojo
	 * @return Insert件数
	 */
	int insert(MasterKojo bean);

	/**
	 * Update.
	 * @param bean MasterKojo
	 * @return Update件数
	 */
	int update(MasterKojo bean);

	/**
	 * Delete.
	 * @param bean MasterKojo
	 * @return Delete件数
	 */
	int delete(MasterKojo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "KOJOCODE";

	/**
	 * エンティティ取得.
	 * @param kojocode kojocode
	 * @return MasterKojo
	 */
	MasterKojo getEntity(String kojocode);
}
