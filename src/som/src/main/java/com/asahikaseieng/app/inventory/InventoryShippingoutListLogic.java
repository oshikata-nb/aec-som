/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutList;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListConditionForReport;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫出庫入力一覧ロジック interface.
 * @author FPC
 */
public interface InventoryShippingoutListLogic {

	/**
	 * 検索処理を行う．
	 * @param condition condition
	 * @return List<InventoryShippingoutList>
	 * @exception NoDataException NoDataException
	 */
	List<InventoryShippingoutList> getList(
			final InventoryShippingoutListPagerCondition condition)
			throws NoDataException;

	/**
	 * 在庫出庫入力一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InventoryShippingoutListForReport>
	 */
	List<InventoryShippingoutListForReport> getListForReport(
			final InventoryShippingoutListConditionForReport condition);
}
