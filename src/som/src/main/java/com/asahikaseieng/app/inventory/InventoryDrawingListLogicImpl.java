/*
 * Created on 2007/11/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingList;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingListDao;
import com.asahikaseieng.dao.nonentity.inventorydrawinglist.InventoryDrawingListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReport;
import com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport.InventoryDrawingListForReportDao;
import com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty.InventoryDrawingTotalQty;
import com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty.InventoryDrawingTotalQtyDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目別引当明細照会 実装クラス.
 * @author tanaka
 */
public class InventoryDrawingListLogicImpl implements InventoryDrawingListLogic {

	private InventoryDrawingListDao inventoryDrawingListDao;

	private InventoryDrawingTotalQtyDao inventoryDrawingTotalQtyDao;

	private InventoryDrawingListForReportDao inventoryDrawingListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryDrawingListLogicImpl() {
		super();
	}

	/**
	 * 詳細処理を行う．
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryDrawingTotalQty InventoryDrawingTotalQty
	 */
	public InventoryDrawingTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1) {
		InventoryDrawingTotalQty bean = inventoryDrawingTotalQtyDao
				.getTotalQty(itemCd, otherCompanyCd1);

		return bean;
	}

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @param bean bean
	 * @return List<InventoryDrawingList>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryDrawingList> getList(
			final InventoryDrawingListPagerCondition condition,
			final InventoryDrawingTotalQty bean) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryDrawingList> list = inventoryDrawingListDao
				.getList(condition);

		if (bean == null && list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final InventoryDrawingListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * ListForReportメソッド
	 * 
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return List<InventoryDrawingListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryDrawingListForReport> getListForReport(
			final String itemCd, final String otherCompanyCd1)
			throws NoDataException {
		List<InventoryDrawingListForReport> list = inventoryDrawingListForReportDao
				.getListForReport(itemCd, otherCompanyCd1);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryDrawingListDaoを設定します。
	 * @param inventoryDrawingListDao inventoryDrawingListDao
	 */
	public void setInventoryDrawingListDao(
			final InventoryDrawingListDao inventoryDrawingListDao) {
		this.inventoryDrawingListDao = inventoryDrawingListDao;
	}

	/**
	 * inventoryDrawingListForReportDaoを設定します。
	 * @param inventoryDrawingListForReportDao inventoryDrawingListForReportDao
	 */
	public void setInventoryDrawingListForReportDao(
			final InventoryDrawingListForReportDao inventoryDrawingListForReportDao) {
		this.inventoryDrawingListForReportDao = inventoryDrawingListForReportDao;
	}

	/**
	 * inventoryDrawingTotalQtyDaoを設定します。
	 * @param inventoryDrawingTotalQtyDao inventoryDrawingTotalQtyDao
	 */
	public void setInventoryDrawingTotalQtyDao(
			final InventoryDrawingTotalQtyDao inventoryDrawingTotalQtyDao) {
		this.inventoryDrawingTotalQtyDao = inventoryDrawingTotalQtyDao;
	}
}
