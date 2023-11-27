/*
 * Created on Thu Apr 09 11:09:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.cal;

/**
 * CalDaoインターフェース.
 * @author t0011036
 */
public interface CalDao {

	/** BEANアノテーション. */
	Class BEAN = Cal.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Cal
	 * @return Insert件数
	 */
	int insert(Cal bean);

	/**
	 * Update.
	 * @param bean Cal
	 * @return Update件数
	 */
	int update(Cal bean);

	/**
	 * Delete.
	 * @param bean Cal
	 * @return Delete件数
	 */
	int delete(Cal bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "CAL_CD,CAL_DATE";

	/**
	 * エンティティ取得.
	 * @param calCd calCd
	 * @param calDate calDate
	 * @return Cal
	 */
	Cal getEntity(String calCd, java.sql.Timestamp calDate);
}
