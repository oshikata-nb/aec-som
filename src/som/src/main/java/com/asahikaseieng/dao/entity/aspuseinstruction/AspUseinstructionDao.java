/*
 * Created on Tue Apr 21 10:54:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspuseinstruction;

/**
 * AspUseinstructionDaoインターフェース.
 * @author kanri-user
 */
public interface AspUseinstructionDao {

	/** BEANアノテーション. */
	Class BEAN = AspUseinstruction.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean AspUseinstruction
	 * @return Insert件数
	 */
	int insert(AspUseinstruction bean);

	/**
	 * Update.
	 * @param bean AspUseinstruction
	 * @return Update件数
	 */
	int update(AspUseinstruction bean);

	/**
	 * Delete.
	 * @param bean AspUseinstruction
	 * @return Delete件数
	 */
	int delete(AspUseinstruction bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 * @return AspUseinstruction
	 */
	AspUseinstruction getEntity();
}
