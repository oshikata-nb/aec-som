/*
 * Created on Mon Jan 19 16:31:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.changehistory;

/**
 * ChangeHistoryDaoインターフェース.
 * @author t0011036
 */
public interface ChangeHistoryDao {

	/** BEANアノテーション. */
	Class BEAN = ChangeHistory.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ChangeHistory
	 * @return Insert件数
	 */
	int insert(ChangeHistory bean);

	/**
	 * Update.
	 * @param bean ChangeHistory
	 * @return Update件数
	 */
	int update(ChangeHistory bean);

	/**
	 * Delete.
	 * @param bean ChangeHistory
	 * @return Delete件数
	 */
	int delete(ChangeHistory bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "INPUTOR_CD,INPUT_DATE,ITEM_CD,MENU_ID";

	/**
	 * エンティティ取得.
	 * @param inputorCd inputorCd
	 * @param inputDate inputDate
	 * @param itemCd itemCd
	 * @param menuId menuId
	 * @return ChangeHistory
	 */
	ChangeHistory getEntity(final String inputorCd,
			final java.sql.Timestamp inputDate, final String itemCd,
			final java.math.BigDecimal menuId);
}
