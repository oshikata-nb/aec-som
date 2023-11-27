/*
 * Created on Thu Jan 22 18:50:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.line;

/**
 * LineDaoインターフェース.
 * @author t0011036
 */
public interface LineDao {

	/** BEANアノテーション. */
	Class BEAN = Line.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Line
	 * @return Insert件数
	 */
	int insert(Line bean);

	/**
	 * Update.
	 * @param bean Line
	 * @return Update件数
	 */
	int update(Line bean);

	/**
	 * Delete.
	 * @param bean Line
	 * @return Delete件数
	 */
	int delete(Line bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PRODUCTION_LINE";

	/**
	 * エンティティ取得.
	 * @param productionLine productionLine
	 * @return Line
	 */
	Line getEntity(final String productionLine);
}
