/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belong;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belonglist.BelongList;
import com.asahikaseieng.dao.nonentity.master.belonglist.BelongListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belonglistforreport.BelongListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属一覧ロジック interface.
 * @author t0011036
 */
public interface BelongListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<BelongList>
	 */
	List<BelongList> getList(final BelongListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<BelongListForReport>
	 */
	List<BelongListForReport> getListForReport(
			final BelongListConditionForReport condition);
}
