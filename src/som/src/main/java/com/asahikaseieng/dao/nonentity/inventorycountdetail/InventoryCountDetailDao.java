/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorycountdetail;

/**
 * InventoryCountDetailDaoクラス
 * @author t0011036
 */
public interface InventoryCountDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorycountdetail.InventoryCountDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "countDate, countDivision, countUpdateDate";

	/**
	 * InventoryCountDetailメソッド
	 * 
	 * @param countDate countDate
	 * @param countDivision countDivision
	 * @param countUpdateDate countUpdateDate
	 * @return InventoryCountDetail
	 */
	InventoryCountDetail getEntity(final java.sql.Timestamp countDate,
			final String countDivision, final java.sql.Timestamp countUpdateDate);
}
