/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.accounts;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsList;
import com.asahikaseieng.dao.nonentity.master.accountslist.AccountsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.accountslistforreport.AccountsListForReport;
import com.asahikaseieng.dao.nonentity.master.accountsnamelist.AccountsNameList;
import com.asahikaseieng.exception.NoDataException;

/**
 * 勘定科目一覧ロジック interface.
 * @author t0011036
 */
public interface AccountsListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<AccountsList>
	 */
	List<AccountsList> getList(final AccountsListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<AccountsListForReport>
	 */
	List<AccountsListForReport> getListForReport(
			final AccountsListConditionForReport condition);

	/**
	 * 検索処理を行う.
	 * @param accountsCd 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<AccountsNameList>
	 */
	List<AccountsNameList> getNameList(final String accountsCd)
			throws NoDataException;
}
