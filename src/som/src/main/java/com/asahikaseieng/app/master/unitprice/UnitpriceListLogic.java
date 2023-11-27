/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListForReport;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入先別単価一覧ロジック interface.
 * @author t0011036
 */
public interface UnitpriceListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<UnitpriceList>
	 */
	List<UnitpriceList> getList(final UnitpriceListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<UnitpriceListForReport>
	 */
	List<UnitpriceListForReport> getListForReport(
			final UnitpriceListConditionForReport condition);
}
