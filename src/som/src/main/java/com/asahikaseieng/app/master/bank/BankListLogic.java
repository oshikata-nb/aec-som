/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bank;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.banklist.BankList;
import com.asahikaseieng.dao.nonentity.master.banklist.BankListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.banklistforreport.BankListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 銀行一覧ロジック interface.
 * @author t0011036
 */
public interface BankListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<BankList>
	 */
	List<BankList> getList(final BankListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<BankListForReport>
	 */
	List<BankListForReport> getListForReport(
			final BankListConditionForReport condition);
}
