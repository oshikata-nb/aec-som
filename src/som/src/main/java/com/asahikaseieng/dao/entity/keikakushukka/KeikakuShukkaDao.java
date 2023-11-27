/*
 * Created on Tue May 12 16:12:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.keikakushukka;
import java.util.List;

/**
 * KeikakuShukkaDaoインターフェース.
 * @author kanri-user
 */
public interface KeikakuShukkaDao {

	/** BEANアノテーション. */
	Class BEAN = KeikakuShukka.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean KeikakuShukka
	 * @return Insert件数
	 */
	int insert(KeikakuShukka bean);

	/**
	 * Update.
	 * @param bean KeikakuShukka
	 * @return Update件数
	 */
	int update(KeikakuShukka bean);

	/**
	 * Delete.
	 * @param bean KeikakuShukka
	 * @return Delete件数
	 */
	int delete(KeikakuShukka bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NENGAPPI,SEIHINCODE,TSUMINO";

	/**
	 * エンティティ取得.
	 * @param nengappi nengappi
	 * @param seihincode seihincode
	 * @param tsumino tsumino
	 * @return KeikakuShukka
	 */
	KeikakuShukka getEntity(java.sql.Timestamp nengappi, String seihincode, String tsumino);

	/**
	 * リスト取得.
	 * @return KeikakuShukkaのリスト
	 */
	List<KeikakuShukka> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

