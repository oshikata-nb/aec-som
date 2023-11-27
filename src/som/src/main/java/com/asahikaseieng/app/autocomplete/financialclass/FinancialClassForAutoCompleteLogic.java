/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.financialclass;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.financialclass.FinancialClassForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 財務分類のAuto Complete用ロジック
 * @author t0011036
 */
public interface FinancialClassForAutoCompleteLogic {
	/**
	 * 検索画面用財務分類のオートコンプリート用データの取得
	 * @param financialClassCd 財務分類コードまたは財務分類名称
	 * @return List<FinancialClassForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<FinancialClassForAutoComplete> getSearchList(String financialClassCd)
			throws NoDataException;
}
