/*
 * Created on Wed Feb 04 10:04:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.vender;

/**
 * VenderDaoインターフェース.
 * @author kanri-user
 */
public interface VenderDao {

	/** BEANアノテーション. */
	Class BEAN = Vender.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Vender
	 * @return Insert件数
	 */
	int insert(Vender bean);

	/**
	 * Update.
	 * @param bean Vender
	 * @return Update件数
	 */
	int update(Vender bean);

	/**
	 * Delete.
	 * @param bean Vender
	 * @return Delete件数
	 */
	int delete(Vender bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "VENDER_CD,VENDER_DIVISION";

	/**
	 * エンティティ取得.
	 * @param venderCd venderCd
	 * @param venderDivision venderDivision
	 * @return Vender
	 */
	Vender getEntity(String venderCd, String venderDivision);
}
