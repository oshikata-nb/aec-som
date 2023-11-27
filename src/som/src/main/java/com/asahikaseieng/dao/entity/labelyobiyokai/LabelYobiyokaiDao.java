/*
 * Created on Wed Feb 04 16:10:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.labelyobiyokai;

/**
 * LabelYobiyokaiDaoインターフェース.
 * @author kanri-user
 */
public interface LabelYobiyokaiDao {

	/** BEANアノテーション. */
	Class BEAN = LabelYobiyokai.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LabelYobiyokai
	 * @return Insert件数
	 */
	int insert(LabelYobiyokai bean);

	/**
	 * Update.
	 * @param bean LabelYobiyokai
	 * @return Update件数
	 */
	int update(LabelYobiyokai bean);

	/**
	 * Delete.
	 * @param bean LabelYobiyokai
	 * @return Delete件数
	 */
	int delete(LabelYobiyokai bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 * @return LabelYobiyokai
	 */
	LabelYobiyokai getEntity();
}
