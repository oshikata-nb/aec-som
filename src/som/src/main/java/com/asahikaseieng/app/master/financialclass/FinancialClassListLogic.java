/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.financialclass;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassList;
import com.asahikaseieng.dao.nonentity.master.financialclasslist.FinancialClassListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.financialclasslistforreport.FinancialClassListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 財務分類一覧ロジック interface.
 * @author t0011036
 */
public interface FinancialClassListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<FinancialClassList>
	 */
	List<FinancialClassList> getList(
			final FinancialClassListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<FinancialClassListForReport>
	 */
	List<FinancialClassListForReport> getListForReport(
			final FinancialClassListConditionForReport condition);
}
