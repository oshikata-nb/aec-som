/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipepegresouce.direction;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.recipepegresouce.direction.DirectionRecipePegResouceForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * ホールドタンクNOのAuto Complete用ロジック
 * @author tosco
 */
public interface DirectionRecipePegResouceForAutoCompleteLogic {
	/**
	 * 設備一覧を取得
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return List<DirectionRecipePegResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<DirectionRecipePegResouceForAutoComplete> getSearchList(
		String compoundTankNo, String holdTankNo) throws NoDataException;
}
