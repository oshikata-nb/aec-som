/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carryfile;

import java.util.List;


import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileList;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileListDao;
import com.asahikaseieng.dao.nonentity.master.carryfilelist.CarryFileListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListForReport;
import com.asahikaseieng.dao.nonentity.master.carryfilelistforreport.CarryFileListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社一覧ロジック 実装クラス.
 * @author t0011036
 */
public class CarryFileListLogicImpl implements CarryFileListLogic {

	private CarryFileListDao carryFileListDao;

	private CarryFileListForReportDao carryFileListForReportDao;
	
	/**
	 * コンストラクタ.
	 */
	public CarryFileListLogicImpl() {
	}

	/**
	 * 運送会社一覧検索
	 * @param condition 検索条件
	 * @return List<CarryList>
	 * @throws NoDataException NoDataException
	 */
	public List<CarryFileList> getList(final CarryFileListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<CarryFileList> list = carryFileListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final CarryFileListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 運送会社一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<CarryListForReport>
	 */
	public List<CarryFileListForReport> getListForReport(
			final CarryFileListConditionForReport condition) {
		List<CarryFileListForReport> list = carryFileListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}


	/* -------------------- setter -------------------- */

	/**
	 * carryListDaoを設定します。
	 * @param carryListDao carryListDao
	 */
	public void setCarryListDao(final CarryFileListDao carryFileListDao) {
		this.carryFileListDao = carryFileListDao;
	}

	/**
	 * carryListForReportDaoを設定します。
	 * @param carryListForReportDao carryListForReportDao
	 */
	public void setCarryFileListForReportDao(
			final CarryFileListForReportDao carryFileListForReportDao) {
		this.carryFileListForReportDao = carryFileListForReportDao;
	}

	
}
