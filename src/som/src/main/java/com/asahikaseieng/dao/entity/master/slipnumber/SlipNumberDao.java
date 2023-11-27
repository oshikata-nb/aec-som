/*
 * Created on Thu Jan 22 19:42:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.slipnumber;

/**
 * SlipNumberDaoインターフェース.
 * @author kanri-user
 */
public interface SlipNumberDao {

	/** BEANアノテーション. */
	Class BEAN = SlipNumber.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SlipNumber
	 * @return Insert件数
	 */
	int insert(SlipNumber bean);

	/**
	 * Update.
	 * @param bean SlipNumber
	 * @return Update件数
	 */
	int update(SlipNumber bean);

	/**
	 * Delete.
	 * @param bean SlipNumber
	 * @return Delete件数
	 */
	int delete(SlipNumber bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "KEY";

	/**
	 * エンティティ取得.
	 * @param key key
	 * @return SlipNumber
	 */
	SlipNumber getEntity(java.math.BigDecimal key);
}
