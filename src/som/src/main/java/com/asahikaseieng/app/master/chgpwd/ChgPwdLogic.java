/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.chgpwd;

import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 担当者パスワード変更 ロジッククラス interface.
 * @author t0011036
 */
public interface ChgPwdLogic {
	/**
	 * 検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 * @return Login
	 */
	Login getEntity(final String tantoCd) throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param tantoCd 担当者コード
	 * @return LoginDetail
	 */
	LoginDetail getDetailEntity(final String tantoCd);

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象データ
	 */
	void updateLogin(final Login bean);
}
