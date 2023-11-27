/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentList;
import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 成分一覧ロジック interface.
 * @author t0011036
 */
public interface ComponentListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<ComponentList>
	 */
	List<ComponentList> getList(final ComponentListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ComponentListForReport>
	 */
	List<ComponentListForReport> getListForReport(
			final ComponentListConditionForReport condition);
}
