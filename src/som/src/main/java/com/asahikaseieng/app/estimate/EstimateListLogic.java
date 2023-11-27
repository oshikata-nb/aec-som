/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.util.List;

import com.asahikaseieng.dao.nonentity.estimatelist.EstimateList;
import com.asahikaseieng.dao.nonentity.estimatelist.EstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.estimatelistforreport.EstimateListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 見積/単価一覧ロジック interface.
 * @author t0011036
 */
public interface EstimateListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<EstimateList>
	 */
	List<EstimateList> getList(final EstimateListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<EstimateListForReport>
	 */
	List<EstimateListForReport> getListForReport(
			final EstimateListConditionForReport condition);
}
