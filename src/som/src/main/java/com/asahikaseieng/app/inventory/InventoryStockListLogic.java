/*
 * Created on 2008/01/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockList;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReport;
import com.asahikaseieng.dao.nonentity.inventorystocktotalqty.InventoryStockTotalQty;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目別予定在庫照会 ロジック interface.
 * @author FPC
 */
public interface InventoryStockListLogic {
	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @param bean bean
	 * @return List<InventoryStockList>
	 * @throws NoDataException NoDataException
	 */
	List<InventoryStockList> getList(
			final InventoryStockListPagerCondition condition,
			final InventoryStockTotalQty bean) throws NoDataException;

	/**
	 * 詳細処理を行う．
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanycd1
	 * @return InventoryStockTotalQty InventoryStockTotalQty
	 */
	InventoryStockTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1);

	/**
	 * 帳票用検索処理を行う.
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @throws NoDataException NoDataException
	 * @return List<InventoryStockListForReport>
	 */
	List<InventoryStockListForReport> getListForReport(final String itemCd,
			final String otherCompanyCd1) throws NoDataException;

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return InventoryItemQueueDetail
	 */
	InventoryItemQueueDetail getItemQueueEntity(final String itemCd);
}
