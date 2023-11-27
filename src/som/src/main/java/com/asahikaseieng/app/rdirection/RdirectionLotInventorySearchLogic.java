/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績－ロット検索ポップアップ画面ロジック interface.
 * @author tosco
 */
public interface RdirectionLotInventorySearchLogic {

	/**
	 * 品目マスタキュー検索処理を行う.
	 * @param condition 検索条件
	 * @return List<RdirectionLotInventorySearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<RdirectionLotInventorySearchList> getSearchList
		(final RdirectionLotInventorySearchListPagerCondition condition) throws NoDataException;

}
