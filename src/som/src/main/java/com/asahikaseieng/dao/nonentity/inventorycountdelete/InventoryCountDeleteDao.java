/*
 * Created on 2009/04/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorycountdelete;

/**
 * InventoryCountDeleteDaoクラス
 * @author t0011036
 */
public interface InventoryCountDeleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorycountdelete.InventoryCountDelete.class;

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "countDate, countDivision";

	/**
	 * Listメソッド
	 * 
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @return int
	 */
	int deleteList(final java.sql.Timestamp countDate,
			final String countDivision);
}
