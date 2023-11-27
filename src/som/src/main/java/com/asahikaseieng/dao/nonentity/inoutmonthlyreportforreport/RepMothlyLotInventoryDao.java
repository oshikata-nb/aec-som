/*
 * Created on 2009/10/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport;

/**
 * RepMothlyLotInventoryDaoクラス
 * @author kanri-user
 */
public interface RepMothlyLotInventoryDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inoutmonthlyreportforreport.RepMothlyLotInventory.class;

	/** ARGSアノテーション getTargetMonth */
	String getTargetMonth_ARGS = "dateTo";

	/**
	 * RepMothlyLotInventoryメソッド
	 * 
	 * @param dateTo dateTo
	 * @return RepMothlyLotInventory
	 */
	RepMothlyLotInventory getTargetMonth(final String dateTo);
}
