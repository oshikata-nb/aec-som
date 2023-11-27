/*
 * Created on Wed Feb 04 16:12:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tablekanri;

/**
 * TableKanriDaoインターフェース.
 * @author kanri-user
 */
public interface TableKanriDao {

	/** BEANアノテーション. */
	Class BEAN = TableKanri.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TableKanri
	 * @return Insert件数
	 */
	int insert(TableKanri bean);

	/**
	 * Update.
	 * @param bean TableKanri
	 * @return Update件数
	 */
	int update(TableKanri bean);

	/**
	 * Delete.
	 * @param bean TableKanri
	 * @return Delete件数
	 */
	int delete(TableKanri bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CODENO,SETTEICHI";

	/**
	 * エンティティ取得.
	 * @param codeno codeno
	 * @param setteichi setteichi
	 * @return TableKanri
	 */
	TableKanri getEntity(String codeno, String setteichi);
}
