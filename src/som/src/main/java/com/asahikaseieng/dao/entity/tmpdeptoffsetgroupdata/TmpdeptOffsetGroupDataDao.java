/*
 * Created on Thu Jan 22 17:16:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpdeptoffsetgroupdata;

/**
 * TmpdeptOffsetGroupDataDaoインターフェース.
 * @author kanri-user
 */
public interface TmpdeptOffsetGroupDataDao {

	/** BEANアノテーション. */
	Class BEAN = TmpdeptOffsetGroupData.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TmpdeptOffsetGroupData
	 * @return Insert件数
	 */
	int insert(TmpdeptOffsetGroupData bean);

	/**
	 * Update.
	 * @param bean TmpdeptOffsetGroupData
	 * @return Update件数
	 */
	int update(TmpdeptOffsetGroupData bean);

	/**
	 * Delete.
	 * @param bean TmpdeptOffsetGroupData
	 * @return Delete件数
	 */
	int delete(TmpdeptOffsetGroupData bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_NO,VENDER_DIVISION,VENDER_CD";

	/**
	 * エンティティ取得.
	 * @param offsetNo offsetNo
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @return TmpdeptOffsetGroupData
	 */
	TmpdeptOffsetGroupData getEntity(String offsetNo, String venderDivision,
			String venderCd);
}
