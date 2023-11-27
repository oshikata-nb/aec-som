/*
 * Created on Fri Feb 20 18:19:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.recipepegresouce;

/**
 * RecipePegResouceDaoインターフェース.
 * @author kanri-user
 */
public interface RecipePegResouceDao {

	/** BEANアノテーション. */
	Class BEAN = RecipePegResouce.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean RecipePegResouce
	 * @return Insert件数
	 */
	int insert(RecipePegResouce bean);

	/**
	 * Update.
	 * @param bean RecipePegResouce
	 * @return Update件数
	 */
	int update(RecipePegResouce bean);

	/**
	 * Delete.
	 * @param bean RecipePegResouce
	 * @return Delete件数
	 */
	int delete(RecipePegResouce bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RESOUCE_CD,SEQ";

	/**
	 * エンティティ取得.
	 * @param resouceCd resouceCd
	 * @param seq seq
	 * @return RecipePegResouce
	 */
	RecipePegResouce getEntity(String resouceCd, java.math.BigDecimal seq);
}
