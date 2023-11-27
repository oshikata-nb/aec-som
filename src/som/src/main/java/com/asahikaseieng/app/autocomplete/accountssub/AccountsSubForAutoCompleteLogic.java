/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accountssub;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.accountssub.AccountsSubForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 補助科目のAuto Complete用ロジック
 * @author t0011036
 */
public interface AccountsSubForAutoCompleteLogic {
	/**
	 * 検索画面用補助科目のオートコンプリート用データの取得
	 * @param accountsSubCd 補助科目コードまたは補助科目名称
	 * @return List<AccountsSubForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<AccountsSubForAutoComplete> getSearchList(String accountsSubCd)
			throws NoDataException;
}
