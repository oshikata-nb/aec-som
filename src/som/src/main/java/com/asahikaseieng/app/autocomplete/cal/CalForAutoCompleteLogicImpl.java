/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.cal;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.cal.CalForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.cal.CalForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * カレンダーのAuto Complete用ロジック
 * @author t0011036
 */
public class CalForAutoCompleteLogicImpl implements CalForAutoCompleteLogic {
	/* カレンダー操作DAO */
	private CalForAutoCompleteDao calForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public CalForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用カレンダーのオートコンプリート用データの取得
	 * @param calCd カレンダーコードまたはカレンダー名称
	 * @return List<CalForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<CalForAutoComplete> getSearchList(final String calCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(calCd);
		List<CalForAutoComplete> list = calForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * calForAutoCompleteDaoを設定します。
	 * @param calForAutoCompleteDao calForAutoCompleteDao
	 */
	public void setCalForAutoCompleteDao(
			final CalForAutoCompleteDao calForAutoCompleteDao) {
		this.calForAutoCompleteDao = calForAutoCompleteDao;
	}
}
