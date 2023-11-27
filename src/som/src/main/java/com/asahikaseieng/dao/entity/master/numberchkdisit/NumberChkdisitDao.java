/*
 * Created on Wed Feb 04 11:54:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.numberchkdisit;

/**
 * NumberChkdisitDaoインターフェース.
 * @author t0011036
 */
public interface NumberChkdisitDao {

	/** BEANアノテーション. */
	Class BEAN = NumberChkdisit.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean NumberChkdisit
	 * @return Insert件数
	 */
	int insert(NumberChkdisit bean);

	/**
	 * Update.
	 * @param bean NumberChkdisit
	 * @return Update件数
	 */
	int update(NumberChkdisit bean);

	/**
	 * Delete.
	 * @param bean NumberChkdisit
	 * @return Delete件数
	 */
	int delete(NumberChkdisit bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "UNIT_DIVISION,VENDER_CD,VENDER_DIVISION";

	/**
	 * エンティティ取得.
	 * @param unitDivision unitDivision
	 * @param venderCd venderCd
	 * @param venderDivision venderDivision
	 * @return NumberChkdisit
	 */
	NumberChkdisit getEntity(final String unitDivision, final String venderCd,
			final String venderDivision);
}
