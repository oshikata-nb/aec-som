/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.offsetgroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup.OffsetGroupForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 相殺グループのAuto Complete用ロジック
 * @author t0011036
 */
public interface OffsetGroupForAutoCompleteLogic {
	/**
	 * 検索画面用相殺グループのオートコンプリート用データの取得
	 * @param offsetGroupCd 相殺グループコードまたは相殺グループ名称
	 * @return List<OffsetGroupForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<OffsetGroupForAutoComplete> getSearchList(String offsetGroupCd)
			throws NoDataException;
}
