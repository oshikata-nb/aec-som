/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.organization;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationList;
import com.asahikaseieng.dao.nonentity.master.organizationlist.OrganizationListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.organizationlistforreport.OrganizationListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 部署一覧ロジック interface.
 * @author t0011036
 */
public interface OrganizationListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<OrganizationList>
	 */
	List<OrganizationList> getList(
			final OrganizationListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OrganizationListForReport>
	 */
	List<OrganizationListForReport> getListForReport(
			final OrganizationListConditionForReport condition);
}
