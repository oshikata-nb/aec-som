/*
 * Created on 2009/03/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionDirectionDetailSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－製造指図明細ポップアップ画面ロジック interface.
 * @author tosco
 */
public interface PkgRdirectionDirectionDetailSearchLogic {

	/**
	 * 品目マスタキュー検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDirectionDetailSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<PkgRdirectionDirectionDetailSearchList> getSearchList
		(final PkgRdirectionDirectionDetailSearchListPagerCondition condition) throws NoDataException;

}
