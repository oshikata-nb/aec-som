/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.chgpwd;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.login.Login;
import com.asahikaseieng.dao.entity.master.login.LoginDao;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetail;
import com.asahikaseieng.dao.nonentity.master.logindetail.LoginDetailDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 担当者パスワード変更 ロジック実装クラス
 * @author t0011036
 */
public class ChgPwdLogicImpl implements ChgPwdLogic {

	private LoginDao loginDao;

	private LoginDetailDao loginDetailDao;

	/**
	 * コンストラクタ.
	 */
	public ChgPwdLogicImpl() {
	}

	/**
	 * 担当者検索（登録用）
	 * @param tantoCd 担当者コード
	 * @return Login
	 * @throws NoDataException NoDataException
	 */
	public Login getEntity(final String tantoCd) throws NoDataException {
		if (StringUtils.isEmpty(tantoCd)) {
			throw new IllegalArgumentException("tantoCd is empty");
		}

		Login bean = loginDao.getEntity(tantoCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 担当者検索（詳細用）
	 * @param tantoCd 担当者コード
	 * @return TantoDetail
	 */
	public LoginDetail getDetailEntity(final String tantoCd) {
		LoginDetail bean = loginDetailDao.getEntity(tantoCd);
		return bean;
	}

	/**
	 * 担当者更新登録
	 * @param bean 登録データ
	 */
	public void updateLogin(final Login bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			loginDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * loginDaoを設定します。
	 * @param loginDao loginDao
	 */
	public void setLoginDao(final LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * loginDetailDaoを設定します。
	 * @param loginDetailDao loginDetailDao
	 */
	public void setLoginDetailDao(final LoginDetailDao loginDetailDao) {
		this.loginDetailDao = loginDetailDao;
	}
}
