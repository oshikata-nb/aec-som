/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.line;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.line.LineForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産ラインのAuto Complete用ロジック
 * @author t0011036
 */
public interface LineForAutoCompleteLogic {
	/**
	 * 検索画面用生産ラインのオートコンプリート用データの取得
	 * @param lineCd 生産ラインコードまたは生産ライン名称
	 * @return List<LineForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<LineForAutoComplete> getSearchList(String lineCd)
			throws NoDataException;
}
