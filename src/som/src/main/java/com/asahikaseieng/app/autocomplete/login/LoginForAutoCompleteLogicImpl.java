/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.login;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * ログインマスタのAuto Complete用ロジック
 * @author tosco
 */
public class LoginForAutoCompleteLogicImpl implements LoginForAutoCompleteLogic {
	/** ログインマスタ操作DAO */
	private LoginForAutoCompleteDao loginForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public LoginForAutoCompleteLogicImpl() {
	}

	// ログインコードまたは、ログイン名称で検索--------------------------------
	/**
	 * 検索画面用ログインマスタのオートコンプリート用データの取得
	 * @param itemCd ログインコードまたはログイン名称
	 * @return List<LoginForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<LoginForAutoComplete> getSearchList(final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(itemCd);
		List<LoginForAutoComplete> list = loginForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * ログインマスタ操作DAOを設定します。
	 * @param loginForAutoCompleteDao ログインマスタ操作DAO
	 */
	public void setLoginForAutoCompleteDao(
			final LoginForAutoCompleteDao loginForAutoCompleteDao) {
		this.loginForAutoCompleteDao = loginForAutoCompleteDao;
	}

}
