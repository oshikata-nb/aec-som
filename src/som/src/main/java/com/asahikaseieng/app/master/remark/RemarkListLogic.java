/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.remark;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkList;
import com.asahikaseieng.dao.nonentity.master.remarklist.RemarkListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.remarklistforreport.RemarkListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 備考一覧ロジック interface.
 * @author t0011036
 */
public interface RemarkListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<RemarkList>
	 */
	List<RemarkList> getList(final RemarkListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<RemarkListForReport>
	 */
	List<RemarkListForReport> getListForReport(
			final RemarkListConditionForReport condition);
}
