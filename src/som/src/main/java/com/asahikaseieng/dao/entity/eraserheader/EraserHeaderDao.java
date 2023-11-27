/*
 * Created on Wed Feb 04 09:37:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.eraserheader;

/**
 * EraserHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface EraserHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = EraserHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean EraserHeader
	 * @return Insert件数
	 */
	int insert(EraserHeader bean);

	/**
	 * Update.
	 * @param bean EraserHeader
	 * @return Update件数
	 */
	int update(EraserHeader bean);

	/**
	 * Delete.
	 * @param bean EraserHeader
	 * @return Delete件数
	 */
	int delete(EraserHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ERASER_NO";

	/**
	 * エンティティ取得.
	 * @param eraserNo eraserNo
	 * @return EraserHeader
	 */
	EraserHeader getEntity(String eraserNo);
}
