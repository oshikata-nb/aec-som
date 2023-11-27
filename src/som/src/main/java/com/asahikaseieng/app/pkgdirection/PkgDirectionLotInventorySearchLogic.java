/*
 * Created on 2009/03/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－在庫確認ポップアップ画面ロジック interface.
 * @author tosco
 */
public interface PkgDirectionLotInventorySearchLogic {

	/**
	 * 品目マスタキュー検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PkgDirectionLotInventorySearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<PkgDirectionLotInventorySearchList> getSearchList
		(final PkgDirectionLotInventorySearchListPagerCondition condition) throws NoDataException;

}
