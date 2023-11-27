/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.arealist.AreaList;
import com.asahikaseieng.dao.nonentity.master.arealist.AreaListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 地区一覧ロジック interface.
 * @author t0011036
 */
public interface AreaListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<AreaList>
	 */
	List<AreaList> getList(final AreaListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<AreaListForReport>
	 */
	List<AreaListForReport> getListForReport(
			final AreaListConditionForReport condition);
}
