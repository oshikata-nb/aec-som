/*
 * Created on Tue Feb 03 10:41:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dataseihinnyuka;

/**
 * DataSeihinNyukaDaoインターフェース.
 * @author t0011036
 */
public interface DataSeihinNyukaDao {

	/** BEANアノテーション. */
	Class BEAN = DataSeihinNyuka.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean DataSeihinNyuka
	 * @return Insert件数
	 */
	int insert(DataSeihinNyuka bean);

	/**
	 * Update.
	 * @param bean DataSeihinNyuka
	 * @return Update件数
	 */
	int update(DataSeihinNyuka bean);

	/**
	 * Delete.
	 * @param bean DataSeihinNyuka
	 * @return Delete件数
	 */
	int delete(DataSeihinNyuka bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NYUKABC";

	/**
	 * エンティティ取得.
	 * @param nyukabc nyukabc
	 * @return DataSeihinNyuka
	 */
	DataSeihinNyuka getEntity(final String nyukabc);
}
