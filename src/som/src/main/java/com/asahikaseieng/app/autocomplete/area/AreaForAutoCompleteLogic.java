/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.area;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.area.AreaForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 地区のAuto Complete用ロジック
 * @author t0011036
 */
public interface AreaForAutoCompleteLogic {
	/**
	 * 検索画面用地区のオートコンプリート用データの取得
	 * @param areaCd 地区コードまたは地区名称
	 * @return List<AreaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<AreaForAutoComplete> getSearchList(String areaCd)
			throws NoDataException;
}
