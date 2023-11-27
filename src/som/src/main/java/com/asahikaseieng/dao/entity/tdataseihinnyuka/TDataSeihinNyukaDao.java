/*
 * Created on Tue Apr 21 14:40:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tdataseihinnyuka;
import java.util.List;

/**
 * TDataSeihinNyukaDaoインターフェース.
 * @author a7710658
 */
public interface TDataSeihinNyukaDao {

	/** BEANアノテーション. */
	Class BEAN = TDataSeihinNyuka.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TDataSeihinNyuka
	 * @return Insert件数
	 */
	int insert(TDataSeihinNyuka bean);

	/**
	 * Update.
	 * @param bean TDataSeihinNyuka
	 * @return Update件数
	 */
	int update(TDataSeihinNyuka bean);

	/**
	 * Delete.
	 * @param bean TDataSeihinNyuka
	 * @return Delete件数
	 */
	int delete(TDataSeihinNyuka bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NYUKABC";

	/**
	 * エンティティ取得.
	 * @param nyukabc nyukabc
	 * @return TDataSeihinNyuka
	 */
	TDataSeihinNyuka getEntity(String nyukabc);

	/**
	 * リスト取得.
	 * @return TDataSeihinNyukaのリスト
	 */
	List<TDataSeihinNyuka> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

