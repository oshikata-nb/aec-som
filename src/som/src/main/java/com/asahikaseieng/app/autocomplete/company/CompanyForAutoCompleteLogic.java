/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.company;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.company.CompanyForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 自社のAuto Complete用ロジック
 * @author t0011036
 */
public interface CompanyForAutoCompleteLogic {
	/**
	 * 検索画面用自社のオートコンプリート用データの取得
	 * @param companyCd 自社コードまたは自社名称
	 * @return List<CompanyForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<CompanyForAutoComplete> getSearchList(String companyCd)
			throws NoDataException;
}
