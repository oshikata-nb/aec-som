/*
 * Created on Wed Feb 04 16:09:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseihin;

/**
 * JissekiSeihinDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiSeihinDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiSeihin.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiSeihin
	 * @return Insert件数
	 */
	int insert(JissekiSeihin bean);

	/**
	 * Update.
	 * @param bean JissekiSeihin
	 * @return Update件数
	 */
	int update(JissekiSeihin bean);

	/**
	 * Delete.
	 * @param bean JissekiSeihin
	 * @return Delete件数
	 */
	int delete(JissekiSeihin bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "JIKOKU,LOCATION,SEIHINCODE";

	/**
	 * エンティティ取得.
	 * @param jikoku jikoku
	 * @param location location
	 * @param seihincode seihincode
	 * @return JissekiSeihin
	 */
	JissekiSeihin getEntity(java.sql.Timestamp jikoku, String location,
			String seihincode);
}
