/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.operation;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.operation.OperationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.operation.OperationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 工程マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public class OperationForAutoCompleteLogicImpl implements
		OperationForAutoCompleteLogic {
	/* 工程操作DAO */
	private OperationForAutoCompleteDao operationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public OperationForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用工程マスタキューのオートコンプリート用データの取得
	 * @param operationCd 工程コードまたは工程名称
	 * @param recipeUse 用途
	 * @return List<OperationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<OperationForAutoComplete> getSearchList(
			final String operationCd, final BigDecimal recipeUse)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(operationCd);
		List<OperationForAutoComplete> list = operationForAutoCompleteDao
				.getSearchList(val, recipeUse,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * 工程マスタキュー操作DAOを設定します。
	 * @param operationForAutoCompleteDao 工程マスタキュー操作DAO
	 */
	public void setOperationForAutoCompleteDao(
			final OperationForAutoCompleteDao operationForAutoCompleteDao) {
		this.operationForAutoCompleteDao = operationForAutoCompleteDao;
	}
}
