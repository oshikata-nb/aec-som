/*
 * Created on 2007/12/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveList;
import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveListDao;
import com.asahikaseieng.dao.nonentity.inventorymovelist.InventoryMoveListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListConditionForReport;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReport;
import com.asahikaseieng.dao.nonentity.inventorymovelistforreport.InventoryMoveListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫移動入力一覧ロジック
 * @author FPC
 */
public class InventoryMoveListLogicImpl implements InventoryMoveListLogic {

	private InventoryMoveListDao inventoryMoveListDao;

	private InventoryMoveListForReportDao inventoryMoveListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryMoveListLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryMoveList> getList(
			final InventoryMoveListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryMoveList> list = inventoryMoveListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final InventoryMoveListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 在庫移動入力一覧検索（帳票用）
	 * @param condition condition
	 * @return List<InventoryMoveListForReport>
	 */
	public List<InventoryMoveListForReport> getListForReport(
			final InventoryMoveListConditionForReport condition) {
		List<InventoryMoveListForReport> list = inventoryMoveListForReportDao
				.getListForReport(condition);
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryMoveListDaoを設定します。
	 * @param inventoryMoveListDao InventoryMoveListDao
	 */
	public void setInventoryMoveListDao(
			final InventoryMoveListDao inventoryMoveListDao) {
		this.inventoryMoveListDao = inventoryMoveListDao;
	}

	/**
	 * inventoryMoveListForReportDaoを設定します。
	 * @param inventoryMoveListForReportDao inventoryMoveListForReportDao
	 */
	public void setInventoryMoveListForReportDao(
			final InventoryMoveListForReportDao inventoryMoveListForReportDao) {
		this.inventoryMoveListForReportDao = inventoryMoveListForReportDao;
	}
}
