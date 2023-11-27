/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.companylist.CompanyList;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 自社一覧ロジック interface.
 * @author t0011036
 */
public interface CompanyListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<CompanyList>
	 */
	List<CompanyList> getList(final CompanyListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<CompanyListForReport>
	 */
	List<CompanyListForReport> getListForReport(
			final CompanyListConditionForReport condition);

	/**
	 * 登録チェック
	 * @return true:登録済み false:未登録
	 */
	boolean checkUpdateData();
}
