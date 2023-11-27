/*
 * Created on Fri Jan 23 14:14:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.offsetgroup;

/**
 * OffsetGroupDaoインターフェース.
 * @author t0011036
 */
public interface OffsetGroupDao {

	/** BEANアノテーション. */
	Class BEAN = OffsetGroup.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OffsetGroup
	 * @return Insert件数
	 */
	int insert(OffsetGroup bean);

	/**
	 * Update.
	 * @param bean OffsetGroup
	 * @return Update件数
	 */
	int update(OffsetGroup bean);

	/**
	 * Delete.
	 * @param bean OffsetGroup
	 * @return Delete件数
	 */
	int delete(OffsetGroup bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OFFSET_GROUP_CD,VENDER_CD,VENDER_DIVISION";

	/**
	 * エンティティ取得.
	 * @param offsetGroupCd offsetGroupCd
	 * @param venderCd venderCd
	 * @param venderDivision venderDivision
	 * @return OffsetGroup
	 */
	OffsetGroup getEntity(final String offsetGroupCd, final String venderCd,
			final String venderDivision);
}
