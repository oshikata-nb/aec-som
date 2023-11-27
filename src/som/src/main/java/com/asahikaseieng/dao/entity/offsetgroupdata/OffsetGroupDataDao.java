/*
 * Created on Fri Jan 23 14:16:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.offsetgroupdata;

/**
 * OffsetGroupDataDaoインターフェース.
 * @author t0011036
 */
public interface OffsetGroupDataDao {

	/** BEANアノテーション. */
	Class BEAN = OffsetGroupData.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OffsetGroupData
	 * @return Insert件数
	 */
	int insert(OffsetGroupData bean);

	/**
	 * Update.
	 * @param bean OffsetGroupData
	 * @return Update件数
	 */
	int update(OffsetGroupData bean);

	/**
	 * Delete.
	 * @param bean OffsetGroupData
	 * @return Delete件数
	 */
	int delete(OffsetGroupData bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_NO,VENDER_CD,VENDER_DIVISION";

	/**
	 * エンティティ取得.
	 * @param offsetNo offsetNo
	 * @param venderCd venderCd
	 * @param venderDivision venderDivision
	 * @return OffsetGroupData
	 */
	OffsetGroupData getEntity(final String offsetNo, final String venderCd,
			final String venderDivision);
}
