/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.login;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoCompleteDao;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLogin;
import com.asahikaseieng.dao.nonentity.master.loginlogin.LoginLoginDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.model.LoginInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ログインロジック 実装クラス.
 * @author jbd
 */
public class LoginLogicImpl implements LoginLogic {

	private LoginLoginDao loginLoginDao;

	private LoginForAutoCompleteDao loginForAutoCompleteDao;

	/**
	 * コンストラクタ.
	 */
	public LoginLogicImpl() {
	}

	/**
	 * ログイン情報取得
	 * @param tantocd ユーザーコード
	 * @param password パスワード
	 * @return LoginInfo LoginInfo
	 * @throws NoDataException NoDataException
	 */
	public LoginInfo login(final String tantocd, final String password)
			throws NoDataException {

		checkParams(tantocd, password);

		LoginLogin bean = loginLoginDao.getEntity(tantocd);

		if (null == bean) {
			throw new NoDataException();
		}

		/* ログイン情報作成 */
		LoginInfo loginBean = new LoginInfo();
		loginBean.setTantoCd(bean.getTantoCd());
		loginBean.setTantoNm(bean.getTantoNm());
		loginBean.setOrganizationCd(bean.getOrganizationCd());
		loginBean.setOrganizationName(bean.getOrganizationName());
		loginBean.setRoleId(bean.getPostId());
		loginBean.setRoleName(bean.getPostName());
		loginBean.setLoginDate(AecDateUtils.getCurrentTimestamp());

		loginBean.setPassword(bean.getPassword());
		loginBean.setActiveFlg(bean.getActiveFlg());
		loginBean.setUpdatePass(bean.getUpdatePass());
		loginBean.setAdminFlg(bean.getAdminFlg());

		return loginBean;
	}

	/**
	 * ログインマスタ検索処理を行う.
	 * @param tantocd 担当者コード
	 * @return ログイン情報
	 * @throws NoDataException NoDataException
	 */
	public List<LoginForAutoComplete> getAutoUserList(final String tantocd)
			throws NoDataException {
		List<LoginForAutoComplete> bean = loginForAutoCompleteDao
				.getSearchList(tantocd, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (null == bean) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * パラメータチェック.
	 * @param userId ログインID
	 * @param password パスワード
	 */
	private void checkParams(final String tantocd, final String password)
			throws IllegalArgumentException {

		if (StringUtils.isEmpty(tantocd) || StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException("Paramater is empty");
		}
	}

	/**
	 * loginForAutoCompleteDaoを設定します。
	 * @param loginForAutoCompleteDao loginForAutoCompleteDao
	 */
	public void setLoginForAutoCompleteDao(
			final LoginForAutoCompleteDao loginForAutoCompleteDao) {
		this.loginForAutoCompleteDao = loginForAutoCompleteDao;
	}

	/**
	 * loginLoginDaoを設定します。
	 * @param loginLoginDao loginLoginDao
	 */
	public void setLoginLoginDao(final LoginLoginDao loginLoginDao) {
		this.loginLoginDao = loginLoginDao;
	}
}
