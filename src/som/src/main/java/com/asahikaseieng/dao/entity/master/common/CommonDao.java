/*
 * Created on Mon Jan 19 17:30:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.common;

/**
 * CommonDaoインターフェース.
 * @author t0011036
 */
public interface CommonDao {

	/** BEANアノテーション. */
	Class BEAN = Common.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Common
	 * @return Insert件数
	 */
	int insert(Common bean);

	/**
	 * Update.
	 * @param bean Common
	 * @return Update件数
	 */
	int update(Common bean);

	/**
	 * Delete.
	 * @param bean Common
	 * @return Delete件数
	 */
	int delete(Common bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "COMMON_CD";

	/**
	 * エンティティ取得.
	 * @param commonCd commonCd
	 * @return Common
	 */
	Common getEntity(final String commonCd);
}
