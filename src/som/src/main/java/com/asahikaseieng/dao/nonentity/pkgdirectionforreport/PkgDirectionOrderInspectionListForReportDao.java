/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirectionforreport;

import java.util.List;

/**
 * PkgDirectionOrderInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgDirectionOrderInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderInspectionListForReport.class;

	/** ARGSアノテーション getInspectionList */
	String getInspectionList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgDirectionOrderInspectionListForReport>
	 */
	List<PkgDirectionOrderInspectionListForReport> getInspectionReportList(
			PkgDirectionOrderListConditionForReport condition);
}
