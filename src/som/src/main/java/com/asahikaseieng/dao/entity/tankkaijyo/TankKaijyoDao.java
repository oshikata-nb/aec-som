/*
 * Created on Wed Feb 04 16:11:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tankkaijyo;

/**
 * TankKaijyoDaoインターフェース.
 * @author kanri-user
 */
public interface TankKaijyoDao {

	/** BEANアノテーション. */
	Class BEAN = TankKaijyo.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TankKaijyo
	 * @return Insert件数
	 */
	int insert(TankKaijyo bean);

	/**
	 * Update.
	 * @param bean TankKaijyo
	 * @return Update件数
	 */
	int update(TankKaijyo bean);

	/**
	 * Delete.
	 * @param bean TankKaijyo
	 * @return Delete件数
	 */
	int delete(TankKaijyo bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CHOGOTANKNO";

	/**
	 * エンティティ取得.
	 * @param chogotankno chogotankno
	 * @return TankKaijyo
	 */
	TankKaijyo getEntity(String chogotankno);
}
