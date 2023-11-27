/*
 * Created on 2008/01/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingList;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReport;
import com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty.InventoryDrawingTotalQty;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目別引当明細照会 ロジック interface.
 * @author FPC
 */
public interface InventoryDrawingListLogic {
	/**
	 * Listメソッド
	 * @param condition condition
	 * @param bean bean
	 * @return List<InventoryDrawingList>
	 * @throws NoDataException NoDataException
	 */
	List<InventoryDrawingList> getList(
			final InventoryDrawingListPagerCondition condition,
			final InventoryDrawingTotalQty bean) throws NoDataException;

	/**
	 * 詳細処理を行う．
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanycd1
	 * @return InventoryDrawingTotalQty InventoryDrawingTotalQty
	 */
	InventoryDrawingTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1);

	/**
	 * 帳票用検索処理を行う.
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @throws NoDataException NoDataException
	 * @return List<InventoryDrawingListForReport>
	 */
	List<InventoryDrawingListForReport> getListForReport(final String itemCd,
			final String otherCompanyCd1) throws NoDataException;
}
