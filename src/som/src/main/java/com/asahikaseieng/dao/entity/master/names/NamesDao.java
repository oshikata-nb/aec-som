/*
 * Created on Fri Jan 23 13:58:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.names;

/**
 * NamesDaoインターフェース.
 * @author t0011036
 */
public interface NamesDao {

	/** BEANアノテーション. */
	Class BEAN = Names.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Names
	 * @return Insert件数
	 */
	int insert(Names bean);

	/**
	 * Update.
	 * @param bean Names
	 * @return Update件数
	 */
	int update(Names bean);

	/**
	 * Delete.
	 * @param bean Names
	 * @return Delete件数
	 */
	int delete(Names bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NAME_CD,NAME_DIVISION";

	/**
	 * エンティティ取得.
	 * @param nameCd nameCd
	 * @param nameDivision nameDivision
	 * @return Names
	 */
	Names getEntity(final String nameCd, final String nameDivision);
}
