/*
 * Created on Wed Feb 04 16:09:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jokyoseihinbetsushukko;

/**
 * JokyoSeihinbetsuShukkoDaoインターフェース.
 * @author kanri-user
 */
public interface JokyoSeihinbetsuShukkoDao {

	/** BEANアノテーション. */
	Class BEAN = JokyoSeihinbetsuShukko.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JokyoSeihinbetsuShukko
	 * @return Insert件数
	 */
	int insert(JokyoSeihinbetsuShukko bean);

	/**
	 * Update.
	 * @param bean JokyoSeihinbetsuShukko
	 * @return Update件数
	 */
	int update(JokyoSeihinbetsuShukko bean);

	/**
	 * Delete.
	 * @param bean JokyoSeihinbetsuShukko
	 * @return Delete件数
	 */
	int delete(JokyoSeihinbetsuShukko bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEISHUKKOSABC";

	/**
	 * エンティティ取得.
	 * @param seishukkosabc seishukkosabc
	 * @return JokyoSeihinbetsuShukko
	 */
	JokyoSeihinbetsuShukko getEntity(String seishukkosabc);
}
