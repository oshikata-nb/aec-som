/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.line;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.linelist.LineList;
import com.asahikaseieng.dao.nonentity.master.linelist.LineListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.linelistforreport.LineListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産ライン一覧ロジック interface.
 * @author t0011036
 */
public interface LineListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<LineList>
	 */
	List<LineList> getList(final LineListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<LineListForReport>
	 */
	List<LineListForReport> getListForReport(
			final LineListConditionForReport condition);
}
