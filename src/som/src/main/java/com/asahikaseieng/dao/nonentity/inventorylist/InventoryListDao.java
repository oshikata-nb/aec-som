/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylist;

import java.math.BigDecimal;
import java.util.List;

/**
 * InventoryListDaoクラス
 * @author t0011036
 */
public interface InventoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.inventorylist.InventoryList.class;

	/** ARGSアノテーション getLocItemLotList */
	String getLocItemLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getLocItemLotList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getReLocItemLotList */
	String getReLocItemLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getReLocItemLotList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getLocItemList */
	String getLocItemList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getLocItemList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getReLocItemList */
	String getReLocItemList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getReLocItemList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getLocList */
	String getLocList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getLocList(final InventoryListPagerCondition condition);

	/** ARGSアノテーション getReLocList */
	String getReLocList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getReLocList(final InventoryListPagerCondition condition);

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getList(final InventoryListPagerCondition condition);

	/** ARGSアノテーション getItemLotList */
	String getItemLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getItemLotList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getLotList */
	String getLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getLotList(final InventoryListPagerCondition condition);

	/** ARGSアノテーション getItemList */
	String getItemList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getItemList(final InventoryListPagerCondition condition);

	/** ARGSアノテーション getLocLotList */
	String getLocLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getLocLotList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getReLocLotList */
	String getReLocLotList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<InventoryList>
	 */
	List<InventoryList> getReLocLotList(
			final InventoryListPagerCondition condition);

	/** ARGSアノテーション getLocItemLotTotalQty */
	String getLocItemLotTotalQty_ARGS = "locationCd, itemCd, otherCompanyCd1, lotNo";

	/**
	 * InventoryTotalQtyメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @return BigDecimal
	 */
	BigDecimal getLocItemLotTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final String lotNo);

	/** ARGSアノテーション getReLocItemLotTotalQty */
	String getReLocItemLotTotalQty_ARGS = "locationCd, itemCd, otherCompanyCd1, lotNo, availableFlg";

	/**
	 * InventoryTotalQtyメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getReLocItemLotTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final String lotNo, final BigDecimal availableFlg);

	/** ARGSアノテーション getLocItemTotalQty */
	String getLocItemTotalQty_ARGS = "locationCd, itemCd, otherCompanyCd1";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return BigDecimal
	 */
	BigDecimal getLocItemTotalQty(final String locationCd, final String itemCd,
			final String otherCompanyCd1);

	/** ARGSアノテーション getReLocItemTotalQty */
	String getReLocItemTotalQty_ARGS = "locationCd, itemCd, otherCompanyCd1, availableFlg";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getReLocItemTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final BigDecimal availableFlg);

	/** ARGSアノテーション getLocTotalQty */
	String getLocTotalQty_ARGS = "locationCd";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @return BigDecimal
	 */
	BigDecimal getLocTotalQty(final String locationCd);

	/** ARGSアノテーション getReLocTotalQty */
	String getReLocTotalQty_ARGS = "locationCd, availableFlg";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getReLocTotalQty(final String locationCd,
			final BigDecimal availableFlg);

	/** ARGSアノテーション getTotalQty */
	String getTotalQty_ARGS = "availableFlg";

	/**
	 * InventoryListメソッド
	 * 
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getTotalQty(final BigDecimal availableFlg);

	/** ARGSアノテーション getItemLotTotalQty */
	String getItemLotTotalQty_ARGS = "itemCd, otherCompanyCd1, lotNo";

	/**
	 * InventoryListメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @return BigDecimal
	 */
	BigDecimal getItemLotTotalQty(final String itemCd,
			final String otherCompanyCd1, final String lotNo);

	/** ARGSアノテーション getLotTotalQty */
	String getLotTotalQty_ARGS = "lotNo";

	/**
	 * InventoryListメソッド
	 * 
	 * @param lotNo lotNo
	 * @return BigDecimal
	 */
	BigDecimal getLotTotalQty(final String lotNo);

	/** ARGSアノテーション getItemTotalQty */
	String getItemTotalQty_ARGS = "itemCd, otherCompanyCd1";

	/**
	 * InventoryListメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return BigDecimal
	 */
	BigDecimal getItemTotalQty(final String itemCd, final String otherCompanyCd1);

	/** ARGSアノテーション getLocLotTotalQty */
	String getLocLotTotalQty_ARGS = "locationCd, lotNo";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @param lotNo lotNo
	 * @return BigDecimal
	 */
	BigDecimal getLocLotTotalQty(final String locationCd,
			final String lotNo);

	/** ARGSアノテーション getReLocLotTotalQty */
	String getReLocLotTotalQty_ARGS = "locationCd, lotNo, availableFlg";

	/**
	 * InventoryListメソッド
	 * 
	 * @param locationCd locationCd
	 * @param lotNo lotNo
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getReLocLotTotalQty(final String locationCd,
			final String lotNo, final BigDecimal availableFlg);
}
