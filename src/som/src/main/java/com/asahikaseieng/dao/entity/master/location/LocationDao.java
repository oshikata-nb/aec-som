/*
 * Created on Thu Jan 22 19:33:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.location;

/**
 * LocationDaoインターフェース.
 * @author t0011036
 */
public interface LocationDao {

	/** BEANアノテーション. */
	Class BEAN = Location.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Location
	 * @return Insert件数
	 */
	int insert(Location bean);

	/**
	 * Update.
	 * @param bean Location
	 * @return Update件数
	 */
	int update(Location bean);

	/**
	 * Delete.
	 * @param bean Location
	 * @return Delete件数
	 */
	int delete(Location bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "LOCATION_CD";

	/**
	 * エンティティ取得.
	 * @param locationCd locationCd
	 * @return Location
	 */
	Location getEntity(final String locationCd);
}
