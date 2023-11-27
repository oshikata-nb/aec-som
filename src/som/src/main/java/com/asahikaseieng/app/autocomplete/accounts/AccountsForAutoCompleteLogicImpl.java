/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accounts;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.accounts.AccountsForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.accounts.AccountsForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 科目マスタのAuto Complete用ロジック
 * @author tosco
 */
public class AccountsForAutoCompleteLogicImpl implements
		AccountsForAutoCompleteLogic {

	/** 科目マスタ操作DAO */
	private AccountsForAutoCompleteDao accountsForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public AccountsForAutoCompleteLogicImpl() {
	}

	// 科目コードまたは、科目名称で検索--------------------------------
	/**
	 * 科目マスタのオートコンプリート用データの取得
	 * @param accountsCd 科目コードまたは科目名称
	 * @return List<AccountsForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<AccountsForAutoComplete> getSearchList(final String accountsCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(accountsCd);
		List<AccountsForAutoComplete> list = accountsForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 科目マスタ操作DAOを設定します。
	 * @param accountsForAutoCompleteDao 科目マスタ操作DAO
	 */
	public void setAccountsForAutoCompleteDao(
			final AccountsForAutoCompleteDao accountsForAutoCompleteDao) {
		this.accountsForAutoCompleteDao = accountsForAutoCompleteDao;
	}

}
