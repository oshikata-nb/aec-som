/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.carry;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.carry.CarryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社のAuto Complete用ロジック
 * @author t0011036
 */
public interface CarryForAutoCompleteLogic {
	/**
	 * 検索画面用運送会社のオートコンプリート用データの取得
	 * @param carryCd 運送会社コードまたは運送会社名称
	 * @return List<CarryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<CarryForAutoComplete> getSearchList(String carryCd)
			throws NoDataException;
}
