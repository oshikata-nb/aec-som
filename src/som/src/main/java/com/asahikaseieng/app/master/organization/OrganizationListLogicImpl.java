/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationList;
import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationListDao;
import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListForReport;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 部署一覧ロジック 実装クラス.
 * @author t0011036
 */
public class OrganizationListLogicImpl implements OrganizationListLogic {

	private OrganizationListDao organizationListDao;

	private OrganizationListForReportDao organizationListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public OrganizationListLogicImpl() {
	}

	/**
	 * 部署一覧検索
	 * @param condition 検索条件
	 * @return List<OrganizationList>
	 * @throws NoDataException NoDataException
	 */
	public List<OrganizationList> getList(
			final OrganizationListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<OrganizationList> list = organizationListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final OrganizationListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 部署一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<OrganizationListForReport>
	 */
	public List<OrganizationListForReport> getListForReport(
			final OrganizationListConditionForReport condition) {
		List<OrganizationListForReport> list = organizationListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * organizationListDaoを設定します。
	 * @param organizationListDao organizationListDao
	 */
	public void setOrganizationListDao(
			final OrganizationListDao organizationListDao) {
		this.organizationListDao = organizationListDao;
	}

	/**
	 * organizationListForReportDaoを設定します。
	 * @param organizationListForReportDao organizationListForReportDao
	 */
	public void setOrganizationListForReportDao(
			final OrganizationListForReportDao organizationListForReportDao) {
		this.organizationListForReportDao = organizationListForReportDao;
	}
}
