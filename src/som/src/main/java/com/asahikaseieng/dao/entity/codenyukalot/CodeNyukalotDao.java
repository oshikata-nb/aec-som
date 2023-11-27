/*
 * Created on Wed Feb 04 16:08:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.codenyukalot;

/**
 * CodeNyukalotDaoインターフェース.
 * @author kanri-user
 */
public interface CodeNyukalotDao {

	/** BEANアノテーション. */
	Class BEAN = CodeNyukalot.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean CodeNyukalot
	 * @return Insert件数
	 */
	int insert(CodeNyukalot bean);

	/**
	 * Update.
	 * @param bean CodeNyukalot
	 * @return Update件数
	 */
	int update(CodeNyukalot bean);

	/**
	 * Delete.
	 * @param bean CodeNyukalot
	 * @return Delete件数
	 */
	int delete(CodeNyukalot bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NYUKALOT";

	/**
	 * エンティティ取得.
	 * @param nyukalot nyukalot
	 * @return CodeNyukalot
	 */
	CodeNyukalot getEntity(String nyukalot);
}
