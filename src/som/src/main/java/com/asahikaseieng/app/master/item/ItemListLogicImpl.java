/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetailDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueListDao;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReport;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目一覧ロジック 実装クラス.
 * @author t0011036
 */
public class ItemListLogicImpl implements ItemListLogic {

	private ItemQueueListDao itemQueueListDao;

	private ItemQueueListForReportDao itemQueueListForReportDao;

	private ItemDetailDao itemDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ItemListLogicImpl() {
	}

	/**
	 * 有効品目がある場合の検索(未使用)
	 * @param condition 検索条件
	 * @return List<ItemQueueList>
	 * @throws NoDataException NoDataException
	 */
	public List<ItemQueueList> getActivateList(
			final ItemQueueListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ItemQueueList> list = itemQueueListDao.getActivateList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 有効品目がない場合の検索(未使用)
	 * @param condition 検索条件
	 * @return List<ItemQueueList>
	 * @throws NoDataException NoDataException
	 */
	public List<ItemQueueList> getInactivateList(
			final ItemQueueListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ItemQueueList> list = itemQueueListDao
				.getInactivateList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 品目一覧検索
	 * @param condition 検索条件
	 * @return List<ItemQueueList>
	 * @throws NoDataException NoDataException
	 */
	public List<ItemQueueList> getList(
			final ItemQueueListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ItemQueueList> list = itemQueueListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ItemQueueListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 有効品目がある場合の帳票用検索(未使用)
	 * @param condition 検索条件
	 * @return List<ItemQueueListForReport>
	 */
	public List<ItemQueueListForReport> getActivateListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getActivateListForReport(condition);

		if (list.isEmpty()) {
			list = itemQueueListForReportDao
					.getActivateListForReport(condition);

			if (list.isEmpty()) {
				return null;
			}
		}

		return list;
	}

	/**
	 * 有効品目がない場合の帳票用検索(未使用)
	 * @param condition 検索条件
	 * @return List<ItemQueueListForReport>
	 */
	public List<ItemQueueListForReport> getInactivateListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getInactivateListForReport(condition);

		if (list.isEmpty()) {
			list = itemQueueListForReportDao
					.getInactivateListForReport(condition);

			if (list.isEmpty()) {
				return null;
			}
		}

		return list;
	}

	/**
	 * 品目一覧帳票用検索
	 * @param condition 検索条件
	 * @return List<ItemQueueListForReport>
	 */
	public List<ItemQueueListForReport> getListForReport(
			final ItemQueueListConditionForReport condition) {
		List<ItemQueueListForReport> list = itemQueueListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 有効品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	public ItemDetail getItemEntity(final String itemCd) {
		ItemDetail bean = itemDetailDao.getEntity(itemCd, null);
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * itemQueueListDaoを設定します。
	 * @param itemQueueListDao itemQueueListDao
	 */
	public void setItemQueueListDao(final ItemQueueListDao itemQueueListDao) {
		this.itemQueueListDao = itemQueueListDao;
	}

	/**
	 * itemQueueListForReportDaoを設定します。
	 * @param itemQueueListForReportDao itemQueueListForReportDao
	 */
	public void setItemQueueListForReportDao(
			final ItemQueueListForReportDao itemQueueListForReportDao) {
		this.itemQueueListForReportDao = itemQueueListForReportDao;
	}

	/**
	 * itemDetailDaoを設定します。
	 * @param itemDetailDao itemDetailDao
	 */
	public void setItemDetailDao(final ItemDetailDao itemDetailDao) {
		this.itemDetailDao = itemDetailDao;
	}
}
