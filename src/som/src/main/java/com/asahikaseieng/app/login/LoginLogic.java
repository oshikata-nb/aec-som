/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;

/**
 * ログインロジック interface.
 * @author jbd
 */
public interface LoginLogic {

	/**
	 * ログイン処理を行う.
	 * @param loginId ログインID
	 * @param password パスワード
	 * @return ログイン情報情報
	 * @throws NoDataException NoDataException
	 */
	LoginInfo login(final String loginId, final String password)
			throws NoDataException;

	/**
	 * ログインマスタ検索処理を行う.
	 * @param tantocd 担当者コード
	 * @return List<LoginForAutoComplete> ログイン情報
	 * @throws NoDataException NoDataException
	 */
	List<LoginForAutoComplete> getAutoUserList(final String tantocd)
			throws NoDataException;
}
