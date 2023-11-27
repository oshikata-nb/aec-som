/*
 * Created on Wed Feb 04 16:11:59 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.masterhbcr;

/**
 * MasterHbcrDaoインターフェース.
 * @author kanri-user
 */
public interface MasterHbcrDao {

	/** BEANアノテーション. */
	Class BEAN = MasterHbcr.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean MasterHbcr
	 * @return Insert件数
	 */
	int insert(MasterHbcr bean);

	/**
	 * Update.
	 * @param bean MasterHbcr
	 * @return Update件数
	 */
	int update(MasterHbcr bean);

	/**
	 * Delete.
	 * @param bean MasterHbcr
	 * @return Delete件数
	 */
	int delete(MasterHbcr bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "HBCRNO";

	/**
	 * エンティティ取得.
	 * @param hbcrno hbcrno
	 * @return MasterHbcr
	 */
	MasterHbcr getEntity(String hbcrno);
}
