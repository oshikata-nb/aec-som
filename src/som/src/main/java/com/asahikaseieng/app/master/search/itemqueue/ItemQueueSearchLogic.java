/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.itemqueue;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchList;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタキュー検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface ItemQueueSearchLogic {

	/**
	 * 品目マスタキュー検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ItemQueueSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<ItemQueueSearchList> getList(final ItemQueueSearchListPagerCondition condition) throws NoDataException;

}
