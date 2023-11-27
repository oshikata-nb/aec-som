/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.names;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.nameslist.NamesList;
import com.asahikaseieng.dao.nonentity.master.nameslist.NamesListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.nameslistforreport.NamesListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 名称一覧ロジック interface.
 * @author t0011036
 */
public interface NamesListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<NamesList>
	 */
	List<NamesList> getList(final NamesListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<NamesListForReport>
	 */
	List<NamesListForReport> getListForReport(
			final NamesListConditionForReport condition);
}
