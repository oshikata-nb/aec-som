/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.cal;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.callist.CalList;
import com.asahikaseieng.dao.nonentity.master.callist.CalListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.callistforreport.CalListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * カレンダー一覧ロジック interface.
 * @author tosco
 */
public interface CalListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<CalList>
	 */
	List<CalList> getList(final CalListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<CalListForReport>
	 */
	List<CalListForReport> getListForReport(
			final CalListConditionForReport condition);
}
