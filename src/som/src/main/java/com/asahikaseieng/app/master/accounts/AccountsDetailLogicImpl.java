/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import org.apache.commons.lang.StringUtils;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.accounts.Accounts;
import com.asahikaseieng.dao.entity.master.accounts.AccountsDao;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetail;
import com.asahikaseieng.dao.nonentity.master.accountsdetail.AccountsDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 勘定科目詳細ロジック 実装クラス.
 * @author t0011036
 */
public class AccountsDetailLogicImpl implements AccountsDetailLogic {

	private AccountsDao accountsDao;

	private AccountsDetailDao accountsDetailDao;

	/**
	 * コンストラクタ.
	 */
	public AccountsDetailLogicImpl() {
	}

	/**
	 * 勘定科目検索（登録用）
	 * @param accountsCd 勘定科目コード
	 * @return Accounts
	 * @throws NoDataException NoDataException
	 */
	public Accounts getEntity(final String accountsCd) throws NoDataException {
		if (StringUtils.isEmpty(accountsCd)) {
			throw new IllegalArgumentException("accountsCd is empty");
		}

		Accounts bean = accountsDao.getEntity(accountsCd);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 勘定科目検索（詳細用）
	 * @param accountsCd 勘定科目コード
	 * @return AccountsDetail
	 */
	public AccountsDetail getDetailEntity(final String accountsCd) {
		AccountsDetail bean = accountsDetailDao.getEntity(accountsCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Accounts bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			accountsDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Accounts bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			accountsDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final Accounts bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			accountsDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * accountsDaoを設定します。
	 * @param accountsDao accountsDao
	 */
	public void setAccountsDao(final AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	/**
	 * accountsDetailDaoを設定します。
	 * @param accountsDetailDao accountsDetailDao
	 */
	public void setAccountsDetailDao(final AccountsDetailDao accountsDetailDao) {
		this.accountsDetailDao = accountsDetailDao;
	}
}
