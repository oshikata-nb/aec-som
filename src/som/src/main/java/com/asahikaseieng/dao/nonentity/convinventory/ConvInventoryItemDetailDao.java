/*
 * Created on 2009/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.convinventory;

/**
 * ConvInventoryItemDetailDaoクラス
 * @author t0011036
 */
public interface ConvInventoryItemDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.convinventory.ConvInventoryItemDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd";

	/**
	 * ConvInventoryItemDetailメソッド
	 * 
	 * @param itemCd itemCd
	 * @return ConvInventoryItemDetail
	 */
	ConvInventoryItemDetail getEntity(final String itemCd);
}
