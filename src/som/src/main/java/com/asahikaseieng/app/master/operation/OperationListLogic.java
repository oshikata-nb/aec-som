/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.operation;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.operationlist.OperationList;
import com.asahikaseieng.dao.nonentity.master.operationlist.OperationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.operationlistforreport.OperationListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 工程一覧ロジック interface.
 * @author t0011036
 */
public interface OperationListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<OperationList>
	 */
	List<OperationList> getList(final OperationListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OperationListForReport>
	 */
	List<OperationListForReport> getListForReport(
			final OperationListConditionForReport condition);
}
