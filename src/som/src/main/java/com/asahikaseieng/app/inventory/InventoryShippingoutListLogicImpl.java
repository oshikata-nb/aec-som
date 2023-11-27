/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutList;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutListDao;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlist.InventoryShippingoutListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListConditionForReport;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListForReport;
import com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport.InventoryShippingoutListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫出庫入力一覧ロジック
 * @author FPC
 */
public class InventoryShippingoutListLogicImpl implements
		InventoryShippingoutListLogic {

	private InventoryShippingoutListDao inventoryShippingoutListDao;

	private InventoryShippingoutListForReportDao inventoryShippingoutListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryShippingoutListLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryShippingoutList> getList(
			final InventoryShippingoutListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryShippingoutList> list = inventoryShippingoutListDao
				.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final InventoryShippingoutListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 在庫出庫入力一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InventoryShippingoutListForReport>
	 */
	public List<InventoryShippingoutListForReport> getListForReport(
			final InventoryShippingoutListConditionForReport condition) {
		List<InventoryShippingoutListForReport> list = inventoryShippingoutListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryShippingoutListDaoを設定します。
	 * @param inventoryShippingoutListDao InventoryShippingoutListDao
	 */
	public void setInventoryShippingoutListDao(
			final InventoryShippingoutListDao inventoryShippingoutListDao) {
		this.inventoryShippingoutListDao = inventoryShippingoutListDao;
	}

	/**
	 * inventoryShippingoutListForReportDaoを設定します。
	 * @param inventoryShippingoutListForReportDao
	 *            inventoryShippingoutListForReportDao
	 */
	public void setInventoryShippingoutListForReportDao(
			final InventoryShippingoutListForReportDao inventoryShippingoutListForReportDao) {
		this.inventoryShippingoutListForReportDao = inventoryShippingoutListForReportDao;
	}
}
