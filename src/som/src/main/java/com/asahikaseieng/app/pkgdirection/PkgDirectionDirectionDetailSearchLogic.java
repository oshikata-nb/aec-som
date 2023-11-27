/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－製造指図明細ポップアップ画面ロジック interface.
 * @author tosco
 */
public interface PkgDirectionDirectionDetailSearchLogic {

	/**
	 * 品目マスタキュー検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PkgDirectionDirectionDetailSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<PkgDirectionDirectionDetailSearchList> getSearchList
		(final PkgDirectionDirectionDetailSearchListPagerCondition condition) throws NoDataException;

}
