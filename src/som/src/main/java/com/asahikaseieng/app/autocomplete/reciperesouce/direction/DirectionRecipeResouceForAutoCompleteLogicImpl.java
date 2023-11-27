/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reciperesouce.direction;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.direction.DirectionRecipeResouceForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.reciperesouce.direction.DirectionRecipeResouceForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 予備溶解タンクNOオートコンプリート用ロジック
 * @author tosco
 */
public class DirectionRecipeResouceForAutoCompleteLogicImpl implements
		DirectionRecipeResouceForAutoCompleteLogic {

	/** 予備溶解タンクNOオートコンプリート用DAO */
	private DirectionRecipeResouceForAutoCompleteDao directionRecipeResouceForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public DirectionRecipeResouceForAutoCompleteLogicImpl() {
	}

	/**
	 * 設備一覧を取得
	 * @param productionLine 生産ライン
	 * @param dissolutionTankNo 予備溶解タンクNO
	 * @return List<DirectionRecipeResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DirectionRecipeResouceForAutoComplete> getSearchList(
			final String productionLine, final String dissolutionTankNo)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(dissolutionTankNo);
		List<DirectionRecipeResouceForAutoComplete> list = directionRecipeResouceForAutoCompleteDao
				.getSearchList(productionLine, val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 予備溶解タンクNOオートコンプリート用DAOを設定します。
	 * @param directionRecipeResouceForAutoCompleteDao 予備溶解タンクNOオートコンプリート用DAO
	 */
	public void setDirectionRecipeResouceForAutoCompleteDao(
			final DirectionRecipeResouceForAutoCompleteDao directionRecipeResouceForAutoCompleteDao) {
		this.directionRecipeResouceForAutoCompleteDao = directionRecipeResouceForAutoCompleteDao;
	}

}
