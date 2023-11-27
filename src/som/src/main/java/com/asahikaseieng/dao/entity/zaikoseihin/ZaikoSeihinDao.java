/*
 * Created on Wed Feb 04 16:11:28 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.zaikoseihin;

/**
 * ZaikoSeihinDaoインターフェース.
 * @author kanri-user
 */
public interface ZaikoSeihinDao {

	/** BEANアノテーション. */
	Class BEAN = ZaikoSeihin.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ZaikoSeihin
	 * @return Insert件数
	 */
	int insert(ZaikoSeihin bean);

	/**
	 * Update.
	 * @param bean ZaikoSeihin
	 * @return Update件数
	 */
	int update(ZaikoSeihin bean);

	/**
	 * Delete.
	 * @param bean ZaikoSeihin
	 * @return Delete件数
	 */
	int delete(ZaikoSeihin bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "HOSOSASHIZUNO,LOCATION,NYUKABC,SEIHINKUBUN,ZAIKOKUBUN";

	/**
	 * エンティティ取得.
	 * @param hososashizuno hososashizuno
	 * @param location location
	 * @param nyukabc nyukabc
	 * @param seihinkubun seihinkubun
	 * @param zaikokubun zaikokubun
	 * @return ZaikoSeihin
	 */
	ZaikoSeihin getEntity(String hososashizuno, String location,
			String nyukabc, String seihinkubun, String zaikokubun);
}
