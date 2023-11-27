/*
 * Created on 2008/08/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力一覧 ロジッククラス
 * @author tosco
 */
public interface EraserListLogic {

	/**
	 * 消込入力一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<EraserList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<ClaimEraserList> getSearchList(final ClaimEraserListPagerCondition condition)
			throws NoDataException;

}
