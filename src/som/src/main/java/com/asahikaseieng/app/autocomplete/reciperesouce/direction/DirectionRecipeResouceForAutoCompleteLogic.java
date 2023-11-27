/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.direction;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.direction.DirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 予備溶解タンクのAuto Complete用ロジック
 * @author tosco
 */
public interface DirectionRecipeResouceForAutoCompleteLogic {
	/**
	 * 設備一覧を取得
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @return List<DirectionRecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<DirectionRecipeResouceForAutoComplete> getSearchList(
		String productionLine, String dissolutionTankNo) throws NoDataException;
}
