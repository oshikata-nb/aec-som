/*
 * Created on Thu Jan 15 16:25:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.area;

/**
 * AreaDaoインターフェース.
 * @author t0011036
 */
public interface AreaDao {

	/** BEANアノテーション. */
	Class BEAN = Area.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Area
	 * @return Insert件数
	 */
	int insert(Area bean);

	/**
	 * Update.
	 * @param bean Area
	 * @return Update件数
	 */
	int update(Area bean);

	/**
	 * Delete.
	 * @param bean Area
	 * @return Delete件数
	 */
	int delete(Area bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "AREA_CD";

	/**
	 * エンティティ取得.
	 * @param areaCe areaCe
	 * @return Area
	 */
	Area getEntity(final String areaCe);
}
