/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleList;
import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属・ロール組合せ一覧ロジック interface.
 * @author t0011036
 */
public interface BelongRoleListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<BelongRoleList>
	 */
	List<BelongRoleList> getList(final BelongRoleListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<BelongRoleListForReport>
	 */
	List<BelongRoleListForReport> getListForReport(
			final BelongRoleListConditionForReport condition);
}
