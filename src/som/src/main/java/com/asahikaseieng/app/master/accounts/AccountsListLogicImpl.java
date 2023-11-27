/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsList;
import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsListDao;
import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListForReport;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListForReportDao;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameListDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 勘定科目一覧ロジック 実装クラス.
 * @author t0011036
 */
public class AccountsListLogicImpl implements AccountsListLogic {

	private AccountsListDao accountsListDao;

	private AccountsListForReportDao accountsListForReportDao;

	private AccountsNameListDao accountsNameListDao;

	/**
	 * コンストラクタ.
	 */
	public AccountsListLogicImpl() {
	}

	/**
	 * 勘定科目一覧検索
	 * @param condition 検索条件
	 * @return List<AccountsList>
	 * @throws NoDataException NoDataException
	 */
	public List<AccountsList> getList(final AccountsListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<AccountsList> list = accountsListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final AccountsListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 勘定科目一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<AccountsListForReport>
	 */
	public List<AccountsListForReport> getListForReport(
			final AccountsListConditionForReport condition) {
		List<AccountsListForReport> list = accountsListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AccountsNameList> getNameList(final String accountsCd)
			throws NoDataException {

		List<AccountsNameList> list = accountsNameListDao
				.getNameList(accountsCd);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * accountsListDaoを設定します。
	 * @param accountsListDao accountsListDao
	 */
	public void setAccountsListDao(final AccountsListDao accountsListDao) {
		this.accountsListDao = accountsListDao;
	}

	/**
	 * accountsListForReportDaoを設定します。
	 * @param accountsListForReportDao accountsListForReportDao
	 */
	public void setAccountsListForReportDao(
			final AccountsListForReportDao accountsListForReportDao) {
		this.accountsListForReportDao = accountsListForReportDao;
	}

	/**
	 * accountsNameListDaoを設定します。
	 * @param accountsNameListDao accountsNameListDao
	 */
	public void setAccountsNameListDao(
			final AccountsNameListDao accountsNameListDao) {
		this.accountsNameListDao = accountsNameListDao;
	}
}
