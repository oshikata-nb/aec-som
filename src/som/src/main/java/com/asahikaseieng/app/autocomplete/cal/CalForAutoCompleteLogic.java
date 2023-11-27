/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.cal;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.cal.CalForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * カレンダーのAuto Complete用ロジック
 * @author t0011036
 */
public interface CalForAutoCompleteLogic {
	/**
	 * 検索画面用カレンダーのオートコンプリート用データの取得
	 * @param calCd カレンダーコードまたはカレンダー名称
	 * @return List<CalForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<CalForAutoComplete> getSearchList(String calCd)
			throws NoDataException;
}
