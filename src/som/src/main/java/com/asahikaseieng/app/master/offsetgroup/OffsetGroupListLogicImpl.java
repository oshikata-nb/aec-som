/*
 * Created on 2008/06/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.master.offsetgroup;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupList;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupListDao;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplist.OffsetGroupListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListForReport;
import com.asahikaseieng.dao.nonentity.master.offsetgrouplistforreport.OffsetGroupListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 相殺グループマスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class OffsetGroupListLogicImpl implements OffsetGroupListLogic {

	private OffsetGroupListDao offsetGroupListDao;

	private OffsetGroupListForReportDao offsetGroupListForReportDao;

	/**
	 * コンストラクタ.相殺グループマスタ
	 */
	public OffsetGroupListLogicImpl() {
	}

	/**
	 * 検索条件.全検索 {@inheritDoc}
	 */
	public List<OffsetGroupList> getSearchList(
			final OffsetGroupListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);

		List<OffsetGroupList> list = offsetGroupListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件.
	 */
	private void checkParams(final OffsetGroupListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getList");
		}
	}

	/**
	 * 相殺グループ一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<OffsetGroupListForReport>
	 */
	public List<OffsetGroupListForReport> getListForReport(
			final OffsetGroupListConditionForReport condition) {
		List<OffsetGroupListForReport> list = offsetGroupListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * offsetGroupListDaoを設定します。
	 * @param offsetGroupListDao offsetGroupListDao
	 */
	public void setOffsetGroupListDao(
			final OffsetGroupListDao offsetGroupListDao) {
		this.offsetGroupListDao = offsetGroupListDao;
	}

	/**
	 * offsetGroupListForReportDaoを設定します。
	 * @param offsetGroupListForReportDao offsetGroupListForReportDao
	 */
	public void setOffsetGroupListForReportDao(
			final OffsetGroupListForReportDao offsetGroupListForReportDao) {
		this.offsetGroupListForReportDao = offsetGroupListForReportDao;
	}
}
