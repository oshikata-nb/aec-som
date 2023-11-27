/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.component;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.component.ComponentForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 成分のAuto Complete用ロジック
 * @author t0011036
 */
public interface ComponentForAutoCompleteLogic {
	/**
	 * 検索画面用成分のオートコンプリート用データの取得
	 * @param componentCd 成分コードまたは成分名称
	 * @return List<ComponentForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ComponentForAutoComplete> getSearchList(String componentCd)
			throws NoDataException;
}
