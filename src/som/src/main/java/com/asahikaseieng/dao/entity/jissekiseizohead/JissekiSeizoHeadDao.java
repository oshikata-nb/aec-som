/*
 * Created on Wed Feb 04 16:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.jissekiseizohead;

/**
 * JissekiSeizoHeadDaoインターフェース.
 * @author kanri-user
 */
public interface JissekiSeizoHeadDao {

	/** BEANアノテーション. */
	Class BEAN = JissekiSeizoHead.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean JissekiSeizoHead
	 * @return Insert件数
	 */
	int insert(JissekiSeizoHead bean);

	/**
	 * Update.
	 * @param bean JissekiSeizoHead
	 * @return Update件数
	 */
	int update(JissekiSeizoHead bean);

	/**
	 * Delete.
	 * @param bean JissekiSeizoHead
	 * @return Delete件数
	 */
	int delete(JissekiSeizoHead bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SEIZOSASHIZUNO";

	/**
	 * エンティティ取得.
	 * @param seizosashizuno seizosashizuno
	 * @return JissekiSeizoHead
	 */
	JissekiSeizoHead getEntity(String seizosashizuno);
}
