/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.carrylist.CarryList;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社一覧ロジック interface.
 * @author t0011036
 */
public interface CarryListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<CarryList>
	 */
	List<CarryList> getList(final CarryListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<CarryListForReport>
	 */
	List<CarryListForReport> getListForReport(
			final CarryListConditionForReport condition);
	
}
