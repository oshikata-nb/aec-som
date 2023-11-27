/*
 * Created on 2008/01/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail;
import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockList;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockListDao;
import com.asahikaseieng.dao.nonentity.inventorystocklist.InventoryStockListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReport;
import com.asahikaseieng.dao.nonentity.inventorystocklistforreport.InventoryStockListForReportDao;
import com.asahikaseieng.dao.nonentity.inventorystocktotalqty.InventoryStockTotalQty;
import com.asahikaseieng.dao.nonentity.inventorystocktotalqty.InventoryStockTotalQtyDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目別予定在庫照会 実装クラス.
 * @author FPC
 */
public class InventoryStockListLogicImpl implements InventoryStockListLogic {

	private InventoryStockListDao inventoryStockListDao;

	private InventoryStockTotalQtyDao inventoryStockTotalQtyDao;

	private InventoryStockListForReportDao inventoryStockListForReportDao;

	private InventoryItemQueueDetailDao inventoryItemQueueDetailDao;

	/**
	 * コンストラクタ.
	 * 
	 */
	public InventoryStockListLogicImpl() {
		super();
	}

	/**
	 * 詳細処理を行う．
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @return InventoryStockTotalQty InventoryStockTotalQty
	 */
	public InventoryStockTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1) {
		InventoryStockTotalQty bean = inventoryStockTotalQtyDao.getTotalQty(
			itemCd, otherCompanyCd1);

		return bean;
	}

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @param bean bean
	 * @return List<InventoryStockList>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryStockList> getList(
			final InventoryStockListPagerCondition condition,
			final InventoryStockTotalQty bean) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryStockList> list = inventoryStockListDao
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
	private void checkParams(final InventoryStockListPagerCondition condition) {
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
	 * @return List<InventoryStockListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryStockListForReport> getListForReport(
			final String itemCd, final String otherCompanyCd1)
			throws NoDataException {
		List<InventoryStockListForReport> list = inventoryStockListForReportDao
				.getListForReport(itemCd, otherCompanyCd1);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return InventoryItemQueueDetail
	 */
	public InventoryItemQueueDetail getItemQueueEntity(final String itemCd) {
		InventoryItemQueueDetail bean = inventoryItemQueueDetailDao
				.getEntity(itemCd);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryStockListDaoを設定します。
	 * @param inventoryStockListDao inventoryStockListDao
	 */
	public void setInventoryStockListDao(
			final InventoryStockListDao inventoryStockListDao) {
		this.inventoryStockListDao = inventoryStockListDao;
	}

	/**
	 * inventoryStockListForReportDaoを設定します。
	 * @param inventoryStockListForReportDao inventoryStockListForReportDao
	 */
	public void setInventoryStockListForReportDao(
			final InventoryStockListForReportDao inventoryStockListForReportDao) {
		this.inventoryStockListForReportDao = inventoryStockListForReportDao;
	}

	/**
	 * inventoryStockTotalQtyDaoを設定します。
	 * @param inventoryStockTotalQtyDao inventoryStockTotalQtyDao
	 */
	public void setInventoryStockTotalQtyDao(
			final InventoryStockTotalQtyDao inventoryStockTotalQtyDao) {
		this.inventoryStockTotalQtyDao = inventoryStockTotalQtyDao;
	}

	/**
	 * inventoryItemQueueDetailDaoを設定します。
	 * @param inventoryItemQueueDetailDao inventoryItemQueueDetailDao
	 */
	public void setInventoryItemQueueDetailDao(
			final InventoryItemQueueDetailDao inventoryItemQueueDetailDao) {
		this.inventoryItemQueueDetailDao = inventoryItemQueueDetailDao;
	}
}
