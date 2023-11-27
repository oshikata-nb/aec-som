/*
 * Created on Wed Feb 04 16:10:11 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakuseizo;

/**
 * KeikakuSeizoDaoインターフェース.
 * @author kanri-user
 */
public interface KeikakuSeizoDao {

	/** BEANアノテーション. */
	Class BEAN = KeikakuSeizo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean KeikakuSeizo
	 * @return Insert件数
	 */
	int insert(KeikakuSeizo bean);

	/**
	 * Update.
	 * @param bean KeikakuSeizo
	 * @return Update件数
	 */
	int update(KeikakuSeizo bean);

	/**
	 * Delete.
	 * @param bean KeikakuSeizo
	 * @return Delete件数
	 */
	int delete(KeikakuSeizo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEIZOSASHIZUNO";

	/**
	 * エンティティ取得.
	 * @param seizosashizuno seizosashizuno
	 * @return KeikakuSeizo
	 */
	KeikakuSeizo getEntity(String seizosashizuno);
}
