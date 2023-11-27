/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.area;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.arealist.AreaList;
import com.asahikaseieng.dao.nonentity.master.arealist.AreaListDao;
import com.asahikaseieng.dao.nonentity.master.arealist.AreaListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListForReport;
import com.asahikaseieng.dao.nonentity.master.arealistforreport.AreaListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 地区一覧ロジック 実装クラス.
 * @author t0011036
 */
public class AreaListLogicImpl implements AreaListLogic {

	private AreaListDao areaListDao;

	private AreaListForReportDao areaListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public AreaListLogicImpl() {
	}

	/**
	 * 地区一覧検索
	 * @param condition 検索条件
	 * @return List<AreaList>
	 * @throws NoDataException NoDataException
	 */
	public List<AreaList> getList(final AreaListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<AreaList> list = areaListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final AreaListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 地区一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<AreaListForReport>
	 */
	public List<AreaListForReport> getListForReport(
			final AreaListConditionForReport condition) {
		List<AreaListForReport> list = areaListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * areaListDaoを設定します。
	 * @param areaListDao areaListDao
	 */
	public void setAreaListDao(final AreaListDao areaListDao) {
		this.areaListDao = areaListDao;
	}

	/**
	 * areaListForReportDaoを設定します。
	 * @param areaListForReportDao areaListForReportDao
	 */
	public void setAreaListForReportDao(
			final AreaListForReportDao areaListForReportDao) {
		this.areaListForReportDao = areaListForReportDao;
	}
}
