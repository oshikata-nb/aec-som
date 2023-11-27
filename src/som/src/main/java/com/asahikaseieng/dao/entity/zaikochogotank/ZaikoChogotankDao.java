/*
 * Created on Wed Feb 04 16:11:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.zaikochogotank;

/**
 * ZaikoChogotankDaoインターフェース.
 * @author kanri-user
 */
public interface ZaikoChogotankDao {

	/** BEANアノテーション. */
	Class BEAN = ZaikoChogotank.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ZaikoChogotank
	 * @return Insert件数
	 */
	int insert(ZaikoChogotank bean);

	/**
	 * Update.
	 * @param bean ZaikoChogotank
	 * @return Update件数
	 */
	int update(ZaikoChogotank bean);

	/**
	 * Delete.
	 * @param bean ZaikoChogotank
	 * @return Delete件数
	 */
	int delete(ZaikoChogotank bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CHOGOTANKNO";

	/**
	 * エンティティ取得.
	 * @param chogotankno chogotankno
	 * @return ZaikoChogotank
	 */
	ZaikoChogotank getEntity(String chogotankno);
}
