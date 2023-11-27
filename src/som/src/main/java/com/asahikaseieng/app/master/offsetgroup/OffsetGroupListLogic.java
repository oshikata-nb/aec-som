/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupList;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 相殺グループマスタ一覧 ロジッククラス interface.
 * @author tosco
 */
public interface OffsetGroupListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<OffsetGroupList>
	 */
	List<OffsetGroupList> getSearchList(
			final OffsetGroupListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OffsetGroupListForReport>
	 */
	List<OffsetGroupListForReport> getListForReport(
			final OffsetGroupListConditionForReport condition);
}
