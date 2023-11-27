/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.operation;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.operation.OperationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public interface OperationForAutoCompleteLogic {
	// 工程コードまたは、工程名称で検索--------------------------------
	/**
	 * 検索画面用工程マスタキューのオートコンプリート用データの取得
	 * @param operationCd 工程コードまたは工程名称
	 * @param recipeUse 用途
	 * @return List<OperationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<OperationForAutoComplete> getSearchList(final String operationCd,
			final BigDecimal recipeUse) throws NoDataException;
}
