/*
 * Created on Thu Jan 22 17:22:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tmpclaimoffsetgroupdata;

/**
 * TmpclaimOffsetGroupDataDaoインターフェース.
 * @author kanri-user
 */
public interface TmpclaimOffsetGroupDataDao {

	/** BEANアノテーション. */
	Class BEAN = TmpclaimOffsetGroupData.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TmpclaimOffsetGroupData
	 * @return Insert件数
	 */
	int insert(TmpclaimOffsetGroupData bean);

	/**
	 * Update.
	 * @param bean TmpclaimOffsetGroupData
	 * @return Update件数
	 */
	int update(TmpclaimOffsetGroupData bean);

	/**
	 * Delete.
	 * @param bean TmpclaimOffsetGroupData
	 * @return Delete件数
	 */
	int delete(TmpclaimOffsetGroupData bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_NO,VENDER_DIVISION,VENDER_CD";

	/**
	 * エンティティ取得.
	 * @param offsetNo offsetNo
	 * @param venderDivision venderDivision
	 * @param venderCd venderCd
	 * @return TmpclaimOffsetGroupData
	 */
	TmpclaimOffsetGroupData getEntity(String offsetNo, String venderDivision,
			String venderCd);

}
