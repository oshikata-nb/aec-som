/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.item;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.itemdetail.ItemDetail;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueList;
import com.asahikaseieng.dao.nonentity.master.itemqueuelist.ItemQueueListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport.ItemQueueListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目一覧ロジック interface.
 * @author t0011036
 */
public interface ItemListLogic {
	/**
	 * 有効品目がある場合の検索(未使用)
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getActivateList(
			final ItemQueueListPagerCondition condition) throws NoDataException;

	/**
	 * 有効品目がない場合の検索(未使用)
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getInactivateList(
			final ItemQueueListPagerCondition condition) throws NoDataException;

	/**
	 * 品目一覧検索
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ItemQueueList>
	 */
	List<ItemQueueList> getList(final ItemQueueListPagerCondition condition)
			throws NoDataException;

	/**
	 * 有効品目がある場合の帳票用検索(未使用)
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getActivateListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * 有効品目がない場合の帳票用検索(未使用)
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getInactivateListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * 品目一覧帳票用検索
	 * @param condition condition
	 * @return List<ItemQueueListForReport>
	 */
	List<ItemQueueListForReport> getListForReport(
			final ItemQueueListConditionForReport condition);

	/**
	 * 有効品目検索
	 * @param itemCd 品目コード
	 * @return ItemDetail
	 */
	ItemDetail getItemEntity(final String itemCd);
}
