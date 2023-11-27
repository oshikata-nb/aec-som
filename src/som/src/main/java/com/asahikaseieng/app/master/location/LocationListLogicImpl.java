/*
 * Created on 2009/01/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.location;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.locationlist.LocationList;
import com.asahikaseieng.dao.nonentity.master.locationlist.LocationListDao;
import com.asahikaseieng.dao.nonentity.master.locationlist.LocationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListForReport;
import com.asahikaseieng.dao.nonentity.master.locationlistforreport.LocationListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロケーション一覧ロジック 実装クラス.
 * @author t0011036
 */
public class LocationListLogicImpl implements LocationListLogic {

	private LocationListDao locationListDao;

	private LocationListForReportDao locationListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public LocationListLogicImpl() {
	}

	/**
	 * ロケーション一覧検索
	 * @param condition 検索条件
	 * @return List<LocationList>
	 * @throws NoDataException NoDataException
	 */
	public List<LocationList> getList(final LocationListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<LocationList> list = locationListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final LocationListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * ロケーション一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<LocationListForReport>
	 */
	public List<LocationListForReport> getListForReport(
			final LocationListConditionForReport condition) {
		List<LocationListForReport> list = locationListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * locationListDaoを設定します。
	 * @param locationListDao LocationListDao
	 */
	public void setLocationListDao(final LocationListDao locationListDao) {
		this.locationListDao = locationListDao;
	}

	/**
	 * locationListForReportDaoを設定します。
	 * @param locationListForReportDao locationListForReportDao
	 */
	public void setLocationListForReportDao(
			final LocationListForReportDao locationListForReportDao) {
		this.locationListForReportDao = locationListForReportDao;
	}
}
