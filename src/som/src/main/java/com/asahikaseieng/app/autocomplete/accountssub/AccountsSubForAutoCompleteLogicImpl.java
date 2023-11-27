/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accountssub;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.accountssub.AccountsSubForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.accountssub.AccountsSubForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 補助科目のAuto Complete用ロジック
 * @author t0011036
 */
public class AccountsSubForAutoCompleteLogicImpl implements
		AccountsSubForAutoCompleteLogic {
	/* 補助科目操作DAO */
	private AccountsSubForAutoCompleteDao accountsSubForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public AccountsSubForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用補助科目のオートコンプリート用データの取得
	 * @param accountsSubCd 補助科目コードまたは補助科目名称
	 * @return List<AccountsSubForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<AccountsSubForAutoComplete> getSearchList(
			final String accountsSubCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(accountsSubCd);
		List<AccountsSubForAutoComplete> list = accountsSubForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * accountsSubForAutoCompleteDaoを設定します。
	 * @param accountsSubForAutoCompleteDao accountsSubForAutoCompleteDao
	 */
	public void setAccountsSubForAutoCompleteDao(
			final AccountsSubForAutoCompleteDao accountsSubForAutoCompleteDao) {
		this.accountsSubForAutoCompleteDao = accountsSubForAutoCompleteDao;
	}
}
