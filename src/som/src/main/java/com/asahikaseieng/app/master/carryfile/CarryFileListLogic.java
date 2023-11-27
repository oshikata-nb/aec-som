/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileList;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社一覧ロジック interface.
 * @author t0011036
 */
public interface CarryFileListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<CarryList>
	 */
	List<CarryFileList> getList(final CarryFileListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<CarryListForReport>
	 */
	List<CarryFileListForReport> getListForReport(
			final CarryFileListConditionForReport condition);
	
}
