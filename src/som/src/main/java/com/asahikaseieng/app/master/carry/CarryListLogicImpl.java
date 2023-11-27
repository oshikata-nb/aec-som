/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.carry;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.common.commonproc.CommonProcDao;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryList;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryListDao;
import com.asahikaseieng.dao.nonentity.master.carrylist.CarryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListForReport;
import com.asahikaseieng.dao.nonentity.master.carrylistforreport.CarryListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 運送会社一覧ロジック 実装クラス.
 * @author t0011036
 */
public class CarryListLogicImpl implements CarryListLogic {

	private CarryListDao carryListDao;

	private CarryListForReportDao carryListForReportDao;
	
	private CommonProcDao commonProcDao;

	/**
	 * コンストラクタ.
	 */
	public CarryListLogicImpl() {
	}

	/**
	 * 運送会社一覧検索
	 * @param condition 検索条件
	 * @return List<CarryList>
	 * @throws NoDataException NoDataException
	 */
	public List<CarryList> getList(final CarryListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<CarryList> list = carryListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final CarryListPagerCondition condition) {
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
	public List<CarryListForReport> getListForReport(
			final CarryListConditionForReport condition) {
		List<CarryListForReport> list = carryListForReportDao
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
	public void setCarryListDao(final CarryListDao carryListDao) {
		this.carryListDao = carryListDao;
	}

	/**
	 * carryListForReportDaoを設定します。
	 * @param carryListForReportDao carryListForReportDao
	 */
	public void setCarryListForReportDao(
			final CarryListForReportDao carryListForReportDao) {
		this.carryListForReportDao = carryListForReportDao;
	}

	/**
	 * commonProcDaoを設定します。
	 * @param commonProcDao commonProcDao
	 */
	public void setCommonProcDao(CommonProcDao commonProcDao) {
		this.commonProcDao = commonProcDao;
	}
	
}
