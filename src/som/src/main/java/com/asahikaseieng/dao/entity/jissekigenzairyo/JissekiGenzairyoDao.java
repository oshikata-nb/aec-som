/*
 * Created on Wed Feb 04 16:08:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekigenzairyo;

/**
 * JissekiGenzairyoDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiGenzairyoDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiGenzairyo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiGenzairyo
	 * @return Insert件数
	 */
	int insert(JissekiGenzairyo bean);

	/**
	 * Update.
	 * @param bean JissekiGenzairyo
	 * @return Update件数
	 */
	int update(JissekiGenzairyo bean);

	/**
	 * Delete.
	 * @param bean JissekiGenzairyo
	 * @return Delete件数
	 */
	int delete(JissekiGenzairyo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "JIKOKU,LOCATION,NYUKALOT";

	/**
	 * エンティティ取得.
	 * @param jikoku jikoku
	 * @param location location
	 * @param nyukalot nyukalot
	 * @return JissekiGenzairyo
	 */
	JissekiGenzairyo getEntity(java.sql.Timestamp jikoku, String location,
			String nyukalot);
}
