/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.locationlist.LocationList;
import com.asahikaseieng.dao.nonentity.master.locationlist.LocationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロケーション一覧ロジック interface.
 * @author t0011036
 */
public interface LocationListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<LocationList>
	 */
	List<LocationList> getList(final LocationListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<LocationListForReport>
	 */
	List<LocationListForReport> getListForReport(
			final LocationListConditionForReport condition);
}
