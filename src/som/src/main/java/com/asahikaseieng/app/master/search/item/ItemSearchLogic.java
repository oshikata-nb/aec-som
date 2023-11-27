/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.item;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchList;
import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタ検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface ItemSearchLogic {

	/**
	 * 品目マスタ検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ItemSearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<ItemSearchList> getList(final ItemSearchListPagerCondition condition) throws NoDataException;

}
