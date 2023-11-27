/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.rolelist.RoleList;
import com.asahikaseieng.dao.nonentity.master.rolelist.RoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロール一覧ロジック interface.
 * @author t0011036
 */
public interface RoleListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<RoleList>
	 */
	List<RoleList> getList(final RoleListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<RoleListForReport>
	 */
	List<RoleListForReport> getListForReport(
			final RoleListConditionForReport condition);
}
