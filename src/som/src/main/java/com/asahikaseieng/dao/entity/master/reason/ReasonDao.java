/*
 * Created on Fri Jan 23 16:47:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reason;

/**
 * ReasonDaoインターフェース.
 * @author kanri-user
 */
public interface ReasonDao {

	/** BEANアノテーション. */
	Class BEAN = Reason.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Reason
	 * @return Insert件数
	 */
	int insert(Reason bean);

	/**
	 * Update.
	 * @param bean Reason
	 * @return Update件数
	 */
	int update(Reason bean);

	/**
	 * Delete.
	 * @param bean Reason
	 * @return Delete件数
	 */
	int delete(Reason bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "RY_CD";

	/**
	 * エンティティ取得.
	 * @param ryCd ryCd
	 * @return Reason
	 */
	Reason getEntity(String ryCd);
}
