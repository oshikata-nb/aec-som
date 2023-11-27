/*
 * Created on Wed Feb 04 16:11:39 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterforklan;

/**
 * MasterForklanDaoインターフェース.
 * @author kanri-user
 */
public interface MasterForklanDao {

	/** BEANアノテーション. */
	Class BEAN = MasterForklan.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterForklan
	 * @return Insert件数
	 */
	int insert(MasterForklan bean);

	/**
	 * Update.
	 * @param bean MasterForklan
	 * @return Update件数
	 */
	int update(MasterForklan bean);

	/**
	 * Delete.
	 * @param bean MasterForklan
	 * @return Delete件数
	 */
	int delete(MasterForklan bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "FORKLANNO";

	/**
	 * エンティティ取得.
	 * @param forklanno forklanno
	 * @return MasterForklan
	 */
	MasterForklan getEntity(java.math.BigDecimal forklanno);
}
