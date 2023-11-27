/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonList;
import com.asahikaseieng.dao.nonentity.master.bumonlist.BumonListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 会計部門マスタ一覧 ロジッククラス interface.
 * @author TanakaSatoru
 */
public interface BumonListLogic {

	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<BumonList>
	 */
	List<BumonList> getList(final BumonListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<BumonListForReport>
	 */
	List<BumonListForReport> getListForReport(
			final BumonListConditionForReport condition);
}
