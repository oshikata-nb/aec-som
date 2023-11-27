/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.recipepegresouce.direction;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.recipepegresouce.direction.DirectionRecipePegResouceForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.recipepegresouce.direction.DirectionRecipePegResouceForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * ホールドタンクNOオートコンプリート用ロジック
 * @author tosco
 */
public class DirectionRecipePegResouceForAutoCompleteLogicImpl implements
		DirectionRecipePegResouceForAutoCompleteLogic {

	/** ホールドタンクNOオートコンプリート用DAO */
	private DirectionRecipePegResouceForAutoCompleteDao directionRecipePegResouceForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public DirectionRecipePegResouceForAutoCompleteLogicImpl() {
	}

	/**
	 * 設備一覧を取得
	 * @param compoundTankNo 調合タンクNO
	 * @param holdTankNo ホールドタンクNO
	 * @return List<DirectionRecipePegResouceForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DirectionRecipePegResouceForAutoComplete> getSearchList(
			final String compoundTankNo, final String holdTankNo)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(holdTankNo);
		List<DirectionRecipePegResouceForAutoComplete> list = directionRecipePegResouceForAutoCompleteDao
				.getSearchList(compoundTankNo, val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * ホールドタンクNOオートコンプリート用DAOを設定します。
	 * @param directionRecipePegResouceForAutoCompleteDao ホールドタンクNOオートコンプリート用DAO
	 */
	public void setDirectionRecipePegResouceForAutoCompleteDao(
			final DirectionRecipePegResouceForAutoCompleteDao directionRecipePegResouceForAutoCompleteDao) {
		this.directionRecipePegResouceForAutoCompleteDao = directionRecipePegResouceForAutoCompleteDao;
	}

}
