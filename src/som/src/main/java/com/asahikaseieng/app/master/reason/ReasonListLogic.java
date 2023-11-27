/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.reason;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonList;
import com.asahikaseieng.dao.nonentity.master.reasonlist.ReasonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.reasonlistforreport.ReasonListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 理由一覧ロジック interface.
 * @author t0011036
 */
public interface ReasonListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ReasonList>
	 */
	List<ReasonList> getList(final ReasonListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ReasonListForReport>
	 */
	List<ReasonListForReport> getListForReport(
			final ReasonListConditionForReport condition);
}
