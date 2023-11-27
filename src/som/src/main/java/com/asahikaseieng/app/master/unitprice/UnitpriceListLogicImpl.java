/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListForReport;
import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListForReportDao;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceList;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListDao;
import com.asahikaseieng.dao.nonentity.master.unitpricelist.UnitpriceListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入先別単価一覧ロジック 実装クラス.
 * @author t0011036
 */
public class UnitpriceListLogicImpl implements UnitpriceListLogic {

	private UnitpriceListDao unitpriceListDao;

	private UnitpriceListForReportDao unitpriceListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceListLogicImpl() {
	}

	/**
	 * 仕入先別単価一覧検索
	 * @param condition 検索条件
	 * @return List<UnitpriceList>
	 * @throws NoDataException NoDataException
	 */
	public List<UnitpriceList> getList(
			final UnitpriceListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<UnitpriceList> list = unitpriceListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final UnitpriceListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 仕入先別単価一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<UnitpriceListForReport>
	 */
	public List<UnitpriceListForReport> getListForReport(
			final UnitpriceListConditionForReport condition) {
		List<UnitpriceListForReport> list = unitpriceListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * unitpriceListDaoを設定します。
	 * @param unitpriceListDao unitpriceListDao
	 */
	public void setUnitpriceListDao(final UnitpriceListDao unitpriceListDao) {
		this.unitpriceListDao = unitpriceListDao;
	}

	/**
	 * unitpriceListForReportDaoを設定します。
	 * @param unitpriceListForReportDao unitpriceListForReportDao
	 */
	public void setUnitpriceListForReportDao(
			final UnitpriceListForReportDao unitpriceListForReportDao) {
		this.unitpriceListForReportDao = unitpriceListForReportDao;
	}
}
