/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.component;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentList;
import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentListDao;
import com.asahikaseieng.dao.nonentity.master.componentlist.ComponentListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListForReport;
import com.asahikaseieng.dao.nonentity.master.componentlistforreport.ComponentListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 成分一覧ロジック 実装クラス.
 * @author t0011036
 */
public class ComponentListLogicImpl implements ComponentListLogic {

	private ComponentListDao componentListDao;

	private ComponentListForReportDao componentListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ComponentListLogicImpl() {
	}

	/**
	 * 成分一覧検索
	 * @param condition 検索条件
	 * @return List<ComponentList>
	 * @throws NoDataException NoDataException
	 */
	public List<ComponentList> getList(
			final ComponentListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<ComponentList> list = componentListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ComponentListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 成分一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ComponentListForReport>
	 */
	public List<ComponentListForReport> getListForReport(
			final ComponentListConditionForReport condition) {
		List<ComponentListForReport> list = componentListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * componentListDaoを設定します。
	 * @param componentListDao componentListDao
	 */
	public void setComponentListDao(final ComponentListDao componentListDao) {
		this.componentListDao = componentListDao;
	}

	/**
	 * componentListForReportDaoを設定します。
	 * @param componentListForReportDao componentListForReportDao
	 */
	public void setComponentListForReportDao(
			final ComponentListForReportDao componentListForReportDao) {
		this.componentListForReportDao = componentListForReportDao;
	}
}
