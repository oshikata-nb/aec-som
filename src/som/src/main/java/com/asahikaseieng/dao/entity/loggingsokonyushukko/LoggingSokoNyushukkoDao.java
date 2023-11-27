/*
 * Created on Tue May 12 16:22:57 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.loggingsokonyushukko;
import java.util.List;

/**
 * LoggingSokoNyushukkoDaoインターフェース.
 * @author kanri-user
 */
public interface LoggingSokoNyushukkoDao {

	/** BEANアノテーション. */
	Class BEAN = LoggingSokoNyushukko.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LoggingSokoNyushukko
	 * @return Insert件数
	 */
	int insert(LoggingSokoNyushukko bean);

	/**
	 * Update.
	 * @param bean LoggingSokoNyushukko
	 * @return Update件数
	 */
	int update(LoggingSokoNyushukko bean);

	/**
	 * Delete.
	 * @param bean LoggingSokoNyushukko
	 * @return Delete件数
	 */
	int delete(LoggingSokoNyushukko bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "BARCODE,IRIDEFLAG,LOCATION,NICHIJI";

	/**
	 * エンティティ取得.
	 * @param barcode barcode
	 * @param irideflag irideflag
	 * @param location location
	 * @param nichiji nichiji
	 * @return LoggingSokoNyushukko
	 */
	LoggingSokoNyushukko getEntity(String barcode, String irideflag, String location, java.sql.Timestamp nichiji);

	/**
	 * リスト取得.
	 * @return LoggingSokoNyushukkoのリスト
	 */
	List<LoggingSokoNyushukko> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//
}

