/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;

/**
 * ログインロジック Mockクラス.
 * @author jbd
 */
public class MockLoginLogicImpl implements LoginLogic {

	/**
	 * コンストラクタ.
	 */
	public MockLoginLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public LoginInfo login(final String tantoCd, final String password)
			throws NoDataException {

		if (StringUtils.isEmpty(tantoCd) || StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty");
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			throw new NoDataException();
		}

		LoginInfo bean = new LoginInfo();
		bean.setTantoCd(tantoCd);
		bean.setTantoNm("モック太郎");

		return bean;
	}

	/**
	 * ログインマスタ検索処理を行う.
	 * @param tantocd 担当者コード
	 * 
	 * @return ログイン情報
	 * @throws NoDataException NoDataException
	 */
	public List<LoginForAutoComplete> getAutoUserList(final String tantocd)
			throws NoDataException {

		if (StringUtils.isEmpty(tantocd)) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty");
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(tantocd)) {
			throw new NoDataException();
		}

		LoginForAutoComplete login = new LoginForAutoComplete();
		login.setTantoCd(tantocd);
		login.setTantoNm("TEST_USER");

		List<LoginForAutoComplete> list = new ArrayList<LoginForAutoComplete>();
		list.add(login);

		return list;
	}
}
