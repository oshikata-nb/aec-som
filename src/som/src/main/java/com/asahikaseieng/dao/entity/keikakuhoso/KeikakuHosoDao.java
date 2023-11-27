/*
 * Created on Wed Feb 04 16:09:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuhoso;

/**
 * KeikakuHosoDaoインターフェース.
 * @author kanri-user
 */
public interface KeikakuHosoDao {

	/** BEANアノテーション. */
	Class BEAN = KeikakuHoso.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean KeikakuHoso
	 * @return Insert件数
	 */
	int insert(KeikakuHoso bean);

	/**
	 * Update.
	 * @param bean KeikakuHoso
	 * @return Update件数
	 */
	int update(KeikakuHoso bean);

	/**
	 * Delete.
	 * @param bean KeikakuHoso
	 * @return Delete件数
	 */
	int delete(KeikakuHoso bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "HOSOSASHIZUNO";

	/**
	 * エンティティ取得.
	 * @param hososashizuno hososashizuno
	 * @return KeikakuHoso
	 */
	KeikakuHoso getEntity(String hososashizuno);
}
